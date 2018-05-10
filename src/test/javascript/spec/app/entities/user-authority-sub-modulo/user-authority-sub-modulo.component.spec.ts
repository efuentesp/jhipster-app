/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterAppTestModule } from '../../../test.module';
import { UserAuthoritySubModuloComponent } from '../../../../../../main/webapp/app/entities/user-authority-sub-modulo/user-authority-sub-modulo.component';
import { UserAuthoritySubModuloService } from '../../../../../../main/webapp/app/entities/user-authority-sub-modulo/user-authority-sub-modulo.service';
import { UserAuthoritySubModulo } from '../../../../../../main/webapp/app/entities/user-authority-sub-modulo/user-authority-sub-modulo.model';

describe('Component Tests', () => {

    describe('UserAuthoritySubModulo Management Component', () => {
        let comp: UserAuthoritySubModuloComponent;
        let fixture: ComponentFixture<UserAuthoritySubModuloComponent>;
        let service: UserAuthoritySubModuloService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterAppTestModule],
                declarations: [UserAuthoritySubModuloComponent],
                providers: [
                    UserAuthoritySubModuloService
                ]
            })
            .overrideTemplate(UserAuthoritySubModuloComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UserAuthoritySubModuloComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserAuthoritySubModuloService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new UserAuthoritySubModulo(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.userAuthoritySubModulos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
