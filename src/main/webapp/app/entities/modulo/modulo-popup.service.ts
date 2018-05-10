import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { Modulo } from './modulo.model';
import { ModuloService } from './modulo.service';

@Injectable()
export class ModuloPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private moduloService: ModuloService

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
                this.moduloService.find(id)
                    .subscribe((moduloResponse: HttpResponse<Modulo>) => {
                        const modulo: Modulo = moduloResponse.body;
                        if (modulo.fechaCreacion) {
                            modulo.fechaCreacion = {
                                year: modulo.fechaCreacion.getFullYear(),
                                month: modulo.fechaCreacion.getMonth() + 1,
                                day: modulo.fechaCreacion.getDate()
                            };
                        }
                        if (modulo.fechaModificacion) {
                            modulo.fechaModificacion = {
                                year: modulo.fechaModificacion.getFullYear(),
                                month: modulo.fechaModificacion.getMonth() + 1,
                                day: modulo.fechaModificacion.getDate()
                            };
                        }
                        this.ngbModalRef = this.moduloModalRef(component, modulo);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.moduloModalRef(component, new Modulo());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    moduloModalRef(component: Component, modulo: Modulo): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.modulo = modulo;
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
