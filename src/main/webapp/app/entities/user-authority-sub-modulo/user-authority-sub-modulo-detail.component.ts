import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { UserAuthoritySubModulo } from './user-authority-sub-modulo.model';
import { UserAuthoritySubModuloService } from './user-authority-sub-modulo.service';

@Component({
    selector: 'jhi-user-authority-sub-modulo-detail',
    templateUrl: './user-authority-sub-modulo-detail.component.html'
})
export class UserAuthoritySubModuloDetailComponent implements OnInit, OnDestroy {

    userAuthoritySubModulo: UserAuthoritySubModulo;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private userAuthoritySubModuloService: UserAuthoritySubModuloService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUserAuthoritySubModulos();
    }

    load(id) {
        this.userAuthoritySubModuloService.find(id)
            .subscribe((userAuthoritySubModuloResponse: HttpResponse<UserAuthoritySubModulo>) => {
                this.userAuthoritySubModulo = userAuthoritySubModuloResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUserAuthoritySubModulos() {
        this.eventSubscriber = this.eventManager.subscribe(
            'userAuthoritySubModuloListModification',
            (response) => this.load(this.userAuthoritySubModulo.id)
        );
    }
}
