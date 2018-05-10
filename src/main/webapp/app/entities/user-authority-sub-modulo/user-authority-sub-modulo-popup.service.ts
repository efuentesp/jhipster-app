import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { UserAuthoritySubModulo } from './user-authority-sub-modulo.model';
import { UserAuthoritySubModuloService } from './user-authority-sub-modulo.service';

@Injectable()
export class UserAuthoritySubModuloPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private userAuthoritySubModuloService: UserAuthoritySubModuloService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.userAuthoritySubModuloService.find(id)
                    .subscribe((userAuthoritySubModuloResponse: HttpResponse<UserAuthoritySubModulo>) => {
                        const userAuthoritySubModulo: UserAuthoritySubModulo = userAuthoritySubModuloResponse.body;
                        if (userAuthoritySubModulo.fechaCreacion) {
                            userAuthoritySubModulo.fechaCreacion = {
                                year: userAuthoritySubModulo.fechaCreacion.getFullYear(),
                                month: userAuthoritySubModulo.fechaCreacion.getMonth() + 1,
                                day: userAuthoritySubModulo.fechaCreacion.getDate()
                            };
                        }
                        if (userAuthoritySubModulo.fechaModificacion) {
                            userAuthoritySubModulo.fechaModificacion = {
                                year: userAuthoritySubModulo.fechaModificacion.getFullYear(),
                                month: userAuthoritySubModulo.fechaModificacion.getMonth() + 1,
                                day: userAuthoritySubModulo.fechaModificacion.getDate()
                            };
                        }
                        this.ngbModalRef = this.userAuthoritySubModuloModalRef(component, userAuthoritySubModulo);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.userAuthoritySubModuloModalRef(component, new UserAuthoritySubModulo());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    userAuthoritySubModuloModalRef(component: Component, userAuthoritySubModulo: UserAuthoritySubModulo): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.userAuthoritySubModulo = userAuthoritySubModulo;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
