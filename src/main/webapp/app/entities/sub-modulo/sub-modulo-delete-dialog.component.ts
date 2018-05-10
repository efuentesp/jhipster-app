import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { SubModulo } from './sub-modulo.model';
import { SubModuloPopupService } from './sub-modulo-popup.service';
import { SubModuloService } from './sub-modulo.service';

@Component({
    selector: 'jhi-sub-modulo-delete-dialog',
    templateUrl: './sub-modulo-delete-dialog.component.html'
})
export class SubModuloDeleteDialogComponent {

    subModulo: SubModulo;

    constructor(
        private subModuloService: SubModuloService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.subModuloService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'subModuloListModification',
                content: 'Deleted an subModulo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sub-modulo-delete-popup',
    template: ''
})
export class SubModuloDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private subModuloPopupService: SubModuloPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.subModuloPopupService
                .open(SubModuloDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
