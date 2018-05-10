import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Afiliado } from './afiliado.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Afiliado>;

@Injectable()
export class AfiliadoService {

    private resourceUrl =  SERVER_API_URL + 'api/afiliados';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(afiliado: Afiliado): Observable<EntityResponseType> {
        const copy = this.convert(afiliado);
        return this.http.post<Afiliado>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(afiliado: Afiliado): Observable<EntityResponseType> {
        const copy = this.convert(afiliado);
        return this.http.put<Afiliado>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Afiliado>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Afiliado[]>> {
        const options = createRequestOption(req);
        return this.http.get<Afiliado[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Afiliado[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Afiliado = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Afiliado[]>): HttpResponse<Afiliado[]> {
        const jsonResponse: Afiliado[] = res.body;
        const body: Afiliado[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Afiliado.
     */
    private convertItemFromServer(afiliado: Afiliado): Afiliado {
        const copy: Afiliado = Object.assign({}, afiliado);
        copy.fechaAfiliacion = this.dateUtils
            .convertLocalDateFromServer(afiliado.fechaAfiliacion);
        copy.datoInstant = this.dateUtils
            .convertDateTimeFromServer(afiliado.datoInstant);
        copy.datoZone = this.dateUtils
            .convertDateTimeFromServer(afiliado.datoZone);
        return copy;
    }

    /**
     * Convert a Afiliado to a JSON which can be sent to the server.
     */
    private convert(afiliado: Afiliado): Afiliado {
        const copy: Afiliado = Object.assign({}, afiliado);
        copy.fechaAfiliacion = this.dateUtils
            .convertLocalDateToServer(afiliado.fechaAfiliacion);

        copy.datoInstant = this.dateUtils.toDate(afiliado.datoInstant);

        copy.datoZone = this.dateUtils.toDate(afiliado.datoZone);
        return copy;
    }
}
