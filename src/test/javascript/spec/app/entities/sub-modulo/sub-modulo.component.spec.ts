/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterAppTestModule } from '../../../test.module';
import { SubModuloComponent } from '../../../../../../main/webapp/app/entities/sub-modulo/sub-modulo.component';
import { SubModuloService } from '../../../../../../main/webapp/app/entities/sub-modulo/sub-modulo.service';
import { SubModulo } from '../../../../../../main/webapp/app/entities/sub-modulo/sub-modulo.model';

describe('Component Tests', () => {

    describe('SubModulo Management Component', () => {
        let comp: SubModuloComponent;
        let fixture: ComponentFixture<SubModuloComponent>;
        let service: SubModuloService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterAppTestModule],
                declarations: [SubModuloComponent],
                providers: [
                    SubModuloService
                ]
            })
            .overrideTemplate(SubModuloComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubModuloComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubModuloService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new SubModulo(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.subModulos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
