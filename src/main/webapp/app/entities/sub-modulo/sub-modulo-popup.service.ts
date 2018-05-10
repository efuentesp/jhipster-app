import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { SubModulo } from './sub-modulo.model';
import { SubModuloService } from './sub-modulo.service';

@Injectable()
export class SubModuloPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private subModuloService: SubModuloService

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
                this.subModuloService.find(id)
                    .subscribe((subModuloResponse: HttpResponse<SubModulo>) => {
                        const subModulo: SubModulo = subModuloResponse.body;
                        if (subModulo.fechaCreacion) {
                            subModulo.fechaCreacion = {
                                year: subModulo.fechaCreacion.getFullYear(),
                                month: subModulo.fechaCreacion.getMonth() + 1,
                                day: subModulo.fechaCreacion.getDate()
                            };
                        }
                        if (subModulo.fechaModificacion) {
                            subModulo.fechaModificacion = {
                                year: subModulo.fechaModificacion.getFullYear(),
                                month: subModulo.fechaModificacion.getMonth() + 1,
                                day: subModulo.fechaModificacion.getDate()
                            };
                        }
                        this.ngbModalRef = this.subModuloModalRef(component, subModulo);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.subModuloModalRef(component, new SubModulo());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    subModuloModalRef(component: Component, subModulo: SubModulo): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.subModulo = subModulo;
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
