import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { Afiliado } from './afiliado.model';
import { AfiliadoPopupService } from './afiliado-popup.service';
import { AfiliadoService } from './afiliado.service';

@Component({
    selector: 'jhi-afiliado-dialog',
    templateUrl: './afiliado-dialog.component.html'
})
export class AfiliadoDialogComponent implements OnInit {

    afiliado: Afiliado;
    isSaving: boolean;
    fechaAfiliacionDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private afiliadoService: AfiliadoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.afiliado.id !== undefined) {
            this.subscribeToSaveResponse(
                this.afiliadoService.update(this.afiliado));
        } else {
            this.subscribeToSaveResponse(
                this.afiliadoService.create(this.afiliado));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Afiliado>>) {
        result.subscribe((res: HttpResponse<Afiliado>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Afiliado) {
        this.eventManager.broadcast({ name: 'afiliadoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-afiliado-popup',
    template: ''
})
export class AfiliadoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private afiliadoPopupService: AfiliadoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.afiliadoPopupService
                    .open(AfiliadoDialogComponent as Component, params['id']);
            } else {
                this.afiliadoPopupService
                    .open(AfiliadoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
