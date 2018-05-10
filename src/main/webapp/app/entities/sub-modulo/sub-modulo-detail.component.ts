import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { SubModulo } from './sub-modulo.model';
import { SubModuloService } from './sub-modulo.service';

@Component({
    selector: 'jhi-sub-modulo-detail',
    templateUrl: './sub-modulo-detail.component.html'
})
export class SubModuloDetailComponent implements OnInit, OnDestroy {

    subModulo: SubModulo;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private subModuloService: SubModuloService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSubModulos();
    }

    load(id) {
        this.subModuloService.find(id)
            .subscribe((subModuloResponse: HttpResponse<SubModulo>) => {
                this.subModulo = subModuloResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSubModulos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'subModuloListModification',
            (response) => this.load(this.subModulo.id)
        );
    }
}
