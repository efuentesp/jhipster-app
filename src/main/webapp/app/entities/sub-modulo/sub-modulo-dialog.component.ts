import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SubModulo } from './sub-modulo.model';
import { SubModuloPopupService } from './sub-modulo-popup.service';
import { SubModuloService } from './sub-modulo.service';

@Component({
    selector: 'jhi-sub-modulo-dialog',
    templateUrl: './sub-modulo-dialog.component.html'
})
export class SubModuloDialogComponent implements OnInit {

    subModulo: SubModulo;
    isSaving: boolean;
    fechaCreacionDp: any;
    fechaModificacionDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private subModuloService: SubModuloService,
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
        if (this.subModulo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.subModuloService.update(this.subModulo));
        } else {
            this.subscribeToSaveResponse(
                this.subModuloService.create(this.subModulo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<SubModulo>>) {
        result.subscribe((res: HttpResponse<SubModulo>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: SubModulo) {
        this.eventManager.broadcast({ name: 'subModuloListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-sub-modulo-popup',
    template: ''
})
export class SubModuloPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private subModuloPopupService: SubModuloPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.subModuloPopupService
                    .open(SubModuloDialogComponent as Component, params['id']);
            } else {
                this.subModuloPopupService
                    .open(SubModuloDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
