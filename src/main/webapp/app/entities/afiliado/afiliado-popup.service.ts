import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Afiliado } from './afiliado.model';
import { AfiliadoService } from './afiliado.service';

@Injectable()
export class AfiliadoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private afiliadoService: AfiliadoService

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
                this.afiliadoService.find(id)
                    .subscribe((afiliadoResponse: HttpResponse<Afiliado>) => {
                        const afiliado: Afiliado = afiliadoResponse.body;
                        if (afiliado.fechaAfiliacion) {
                            afiliado.fechaAfiliacion = {
                                year: afiliado.fechaAfiliacion.getFullYear(),
                                month: afiliado.fechaAfiliacion.getMonth() + 1,
                                day: afiliado.fechaAfiliacion.getDate()
                            };
                        }
                        afiliado.datoInstant = this.datePipe
                            .transform(afiliado.datoInstant, 'yyyy-MM-ddTHH:mm:ss');
                        afiliado.datoZone = this.datePipe
                            .transform(afiliado.datoZone, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.afiliadoModalRef(component, afiliado);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.afiliadoModalRef(component, new Afiliado());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    afiliadoModalRef(component: Component, afiliado: Afiliado): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.afiliado = afiliado;
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
