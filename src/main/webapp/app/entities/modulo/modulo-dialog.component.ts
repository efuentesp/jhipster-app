import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Modulo } from './modulo.model';
import { ModuloPopupService } from './modulo-popup.service';
import { ModuloService } from './modulo.service';

@Component({
    selector: 'jhi-modulo-dialog',
    templateUrl: './modulo-dialog.component.html'
})
export class ModuloDialogComponent implements OnInit {

    modulo: Modulo;
    isSaving: boolean;
    fechaCreacionDp: any;
    fechaModificacionDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private moduloService: ModuloService,
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
        if (this.modulo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.moduloService.update(this.modulo));
        } else {
            this.subscribeToSaveResponse(
                this.moduloService.create(this.modulo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Modulo>>) {
        result.subscribe((res: HttpResponse<Modulo>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Modulo) {
        this.eventManager.broadcast({ name: 'moduloListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-modulo-popup',
    template: ''
})
export class ModuloPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private moduloPopupService: ModuloPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.moduloPopupService
                    .open(ModuloDialogComponent as Component, params['id']);
            } else {
                this.moduloPopupService
                    .open(ModuloDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
