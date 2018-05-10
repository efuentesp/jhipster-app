/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterAppTestModule } from '../../../test.module';
import { UserAuthoritySubModuloDetailComponent } from '../../../../../../main/webapp/app/entities/user-authority-sub-modulo/user-authority-sub-modulo-detail.component';
import { UserAuthoritySubModuloService } from '../../../../../../main/webapp/app/entities/user-authority-sub-modulo/user-authority-sub-modulo.service';
import { UserAuthoritySubModulo } from '../../../../../../main/webapp/app/entities/user-authority-sub-modulo/user-authority-sub-modulo.model';

describe('Component Tests', () => {

    describe('UserAuthoritySubModulo Management Detail Component', () => {
        let comp: UserAuthoritySubModuloDetailComponent;
        let fixture: ComponentFixture<UserAuthoritySubModuloDetailComponent>;
        let service: UserAuthoritySubModuloService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterAppTestModule],
                declarations: [UserAuthoritySubModuloDetailComponent],
                providers: [
                    UserAuthoritySubModuloService
                ]
            })
            .overrideTemplate(UserAuthoritySubModuloDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserAuthoritySubModuloDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserAuthoritySubModuloService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new UserAuthoritySubModulo(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.userAuthoritySubModulo).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
