/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterAppTestModule } from '../../../test.module';
import { SubModuloDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/sub-modulo/sub-modulo-delete-dialog.component';
import { SubModuloService } from '../../../../../../main/webapp/app/entities/sub-modulo/sub-modulo.service';

describe('Component Tests', () => {

    describe('SubModulo Management Delete Component', () => {
        let comp: SubModuloDeleteDialogComponent;
        let fixture: ComponentFixture<SubModuloDeleteDialogComponent>;
        let service: SubModuloService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterAppTestModule],
                declarations: [SubModuloDeleteDialogComponent],
                providers: [
                    SubModuloService
                ]
            })
            .overrideTemplate(SubModuloDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubModuloDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubModuloService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
