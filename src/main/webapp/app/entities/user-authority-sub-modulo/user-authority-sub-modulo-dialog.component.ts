import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserAuthoritySubModulo } from './user-authority-sub-modulo.model';
import { UserAuthoritySubModuloPopupService } from './user-authority-sub-modulo-popup.service';
import { UserAuthoritySubModuloService } from './user-authority-sub-modulo.service';

@Component({
    selector: 'jhi-user-authority-sub-modulo-dialog',
    templateUrl: './user-authority-sub-modulo-dialog.component.html'
})
export class UserAuthoritySubModuloDialogComponent implements OnInit {

    userAuthoritySubModulo: UserAuthoritySubModulo;
    isSaving: boolean;
    fechaCreacionDp: any;
    fechaModificacionDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private userAuthoritySubModuloService: UserAuthoritySubModuloService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.userAuthoritySubModulo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userAuthoritySubModuloService.update(this.userAuthoritySubModulo));
        } else {
            this.subscribeToSaveResponse(
                this.userAuthoritySubModuloService.create(this.userAuthoritySubModulo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserAuthoritySubModulo>>) {
        result.subscribe((res: HttpResponse<UserAuthoritySubModulo>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UserAuthoritySubModulo) {
        this.eventManager.broadcast({ name: 'userAuthoritySubModuloListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-user-authority-sub-modulo-popup',
    template: ''
})
export class UserAuthoritySubModuloPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userAuthoritySubModuloPopupService: UserAuthoritySubModuloPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.userAuthoritySubModuloPopupService
                    .open(UserAuthoritySubModuloDialogComponent as Component, params['id']);
            } else {
                this.userAuthoritySubModuloPopupService
                    .open(UserAuthoritySubModuloDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
