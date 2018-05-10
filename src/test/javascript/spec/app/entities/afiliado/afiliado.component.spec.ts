/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterAppTestModule } from '../../../test.module';
import { AfiliadoComponent } from '../../../../../../main/webapp/app/entities/afiliado/afiliado.component';
import { AfiliadoService } from '../../../../../../main/webapp/app/entities/afiliado/afiliado.service';
import { Afiliado } from '../../../../../../main/webapp/app/entities/afiliado/afiliado.model';

describe('Component Tests', () => {

    describe('Afiliado Management Component', () => {
        let comp: AfiliadoComponent;
        let fixture: ComponentFixture<AfiliadoComponent>;
        let service: AfiliadoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterAppTestModule],
                declarations: [AfiliadoComponent],
                providers: [
                    AfiliadoService
                ]
            })
            .overrideTemplate(AfiliadoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AfiliadoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AfiliadoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Afiliado(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.afiliados[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
