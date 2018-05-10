import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { SubModulo } from './sub-modulo.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<SubModulo>;

@Injectable()
export class SubModuloService {

    private resourceUrl =  SERVER_API_URL + 'api/sub-modulos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(subModulo: SubModulo): Observable<EntityResponseType> {
        const copy = this.convert(subModulo);
        return this.http.post<SubModulo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(subModulo: SubModulo): Observable<EntityResponseType> {
        const copy = this.convert(subModulo);
        return this.http.put<SubModulo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<SubModulo>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<SubModulo[]>> {
        const options = createRequestOption(req);
        return this.http.get<SubModulo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<SubModulo[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: SubModulo = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<SubModulo[]>): HttpResponse<SubModulo[]> {
        const jsonResponse: SubModulo[] = res.body;
        const body: SubModulo[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to SubModulo.
     */
    private convertItemFromServer(subModulo: SubModulo): SubModulo {
        const copy: SubModulo = Object.assign({}, subModulo);
        copy.fechaCreacion = this.dateUtils
            .convertLocalDateFromServer(subModulo.fechaCreacion);
        copy.fechaModificacion = this.dateUtils
            .convertLocalDateFromServer(subModulo.fechaModificacion);
        return copy;
    }

    /**
     * Convert a SubModulo to a JSON which can be sent to the server.
     */
    private convert(subModulo: SubModulo): SubModulo {
        const copy: SubModulo = Object.assign({}, subModulo);
        copy.fechaCreacion = this.dateUtils
            .convertLocalDateToServer(subModulo.fechaCreacion);
        copy.fechaModificacion = this.dateUtils
            .convertLocalDateToServer(subModulo.fechaModificacion);
        return copy;
    }
}
