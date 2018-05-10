/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterAppTestModule } from '../../../test.module';
import { UserAuthoritySubModuloDialogComponent } from '../../../../../../main/webapp/app/entities/user-authority-sub-modulo/user-authority-sub-modulo-dialog.component';
import { UserAuthoritySubModuloService } from '../../../../../../main/webapp/app/entities/user-authority-sub-modulo/user-authority-sub-modulo.service';
import { UserAuthoritySubModulo } from '../../../../../../main/webapp/app/entities/user-authority-sub-modulo/user-authority-sub-modulo.model';

describe('Component Tests', () => {

    describe('UserAuthoritySubModulo Management Dialog Component', () => {
        let comp: UserAuthoritySubModuloDialogComponent;
        let fixture: ComponentFixture<UserAuthoritySubModuloDialogComponent>;
        let service: UserAuthoritySubModuloService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterAppTestModule],
                declarations: [UserAuthoritySubModuloDialogComponent],
                providers: [
                    UserAuthoritySubModuloService
                ]
            })
            .overrideTemplate(UserAuthoritySubModuloDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserAuthoritySubModuloDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserAuthoritySubModuloService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UserAuthoritySubModulo(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.userAuthoritySubModulo = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'userAuthoritySubModuloListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UserAuthoritySubModulo();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.userAuthoritySubModulo = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'userAuthoritySubModuloListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
