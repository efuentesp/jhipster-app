/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterAppTestModule } from '../../../test.module';
import { ModuloComponent } from '../../../../../../main/webapp/app/entities/modulo/modulo.component';
import { ModuloService } from '../../../../../../main/webapp/app/entities/modulo/modulo.service';
import { Modulo } from '../../../../../../main/webapp/app/entities/modulo/modulo.model';

describe('Component Tests', () => {

    describe('Modulo Management Component', () => {
        let comp: ModuloComponent;
        let fixture: ComponentFixture<ModuloComponent>;
        let service: ModuloService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterAppTestModule],
                declarations: [ModuloComponent],
                providers: [
                    ModuloService
                ]
            })
            .overrideTemplate(ModuloComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ModuloComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ModuloService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Modulo(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.modulos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
