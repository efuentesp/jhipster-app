import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Afiliado } from './afiliado.model';
import { AfiliadoService } from './afiliado.service';

@Component({
    selector: 'jhi-afiliado-detail',
    templateUrl: './afiliado-detail.component.html'
})
export class AfiliadoDetailComponent implements OnInit, OnDestroy {

    afiliado: Afiliado;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private afiliadoService: AfiliadoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAfiliados();
    }

    load(id) {
        this.afiliadoService.find(id)
            .subscribe((afiliadoResponse: HttpResponse<Afiliado>) => {
                this.afiliado = afiliadoResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAfiliados() {
        this.eventSubscriber = this.eventManager.subscribe(
            'afiliadoListModification',
            (response) => this.load(this.afiliado.id)
        );
    }
}
