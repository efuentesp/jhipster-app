import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserAuthoritySubModulo } from './user-authority-sub-modulo.model';
import { UserAuthoritySubModuloPopupService } from './user-authority-sub-modulo-popup.service';
import { UserAuthoritySubModuloService } from './user-authority-sub-modulo.service';

@Component({
    selector: 'jhi-user-authority-sub-modulo-delete-dialog',
    templateUrl: './user-authority-sub-modulo-delete-dialog.component.html'
})
export class UserAuthoritySubModuloDeleteDialogComponent {

    userAuthoritySubModulo: UserAuthoritySubModulo;

    constructor(
        private userAuthoritySubModuloService: UserAuthoritySubModuloService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userAuthoritySubModuloService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userAuthoritySubModuloListModification',
                content: 'Deleted an userAuthoritySubModulo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-authority-sub-modulo-delete-popup',
    template: ''
})
export class UserAuthoritySubModuloDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userAuthoritySubModuloPopupService: UserAuthoritySubModuloPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userAuthoritySubModuloPopupService
                .open(UserAuthoritySubModuloDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
