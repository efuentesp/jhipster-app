/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterAppTestModule } from '../../../test.module';
import { SubModuloDetailComponent } from '../../../../../../main/webapp/app/entities/sub-modulo/sub-modulo-detail.component';
import { SubModuloService } from '../../../../../../main/webapp/app/entities/sub-modulo/sub-modulo.service';
import { SubModulo } from '../../../../../../main/webapp/app/entities/sub-modulo/sub-modulo.model';

describe('Component Tests', () => {

    describe('SubModulo Management Detail Component', () => {
        let comp: SubModuloDetailComponent;
        let fixture: ComponentFixture<SubModuloDetailComponent>;
        let service: SubModuloService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterAppTestModule],
                declarations: [SubModuloDetailComponent],
                providers: [
                    SubModuloService
                ]
            })
            .overrideTemplate(SubModuloDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SubModuloDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SubModuloService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new SubModulo(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.subModulo).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
