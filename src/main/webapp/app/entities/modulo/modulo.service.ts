import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Modulo } from './modulo.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Modulo>;

@Injectable()
export class ModuloService {

    private resourceUrl =  SERVER_API_URL + 'api/modulos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(modulo: Modulo): Observable<EntityResponseType> {
        const copy = this.convert(modulo);
        return this.http.post<Modulo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(modulo: Modulo): Observable<EntityResponseType> {
        const copy = this.convert(modulo);
        return this.http.put<Modulo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Modulo>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Modulo[]>> {
        const options = createRequestOption(req);
        return this.http.get<Modulo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Modulo[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Modulo = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Modulo[]>): HttpResponse<Modulo[]> {
        const jsonResponse: Modulo[] = res.body;
        const body: Modulo[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Modulo.
     */
    private convertItemFromServer(modulo: Modulo): Modulo {
        const copy: Modulo = Object.assign({}, modulo);
        copy.fechaCreacion = this.dateUtils
            .convertLocalDateFromServer(modulo.fechaCreacion);
        copy.fechaModificacion = this.dateUtils
            .convertLocalDateFromServer(modulo.fechaModificacion);
        return copy;
    }

    /**
     * Convert a Modulo to a JSON which can be sent to the server.
     */
    private convert(modulo: Modulo): Modulo {
        const copy: Modulo = Object.assign({}, modulo);
        copy.fechaCreacion = this.dateUtils
            .convertLocalDateToServer(modulo.fechaCreacion);
        copy.fechaModificacion = this.dateUtils
            .convertLocalDateToServer(modulo.fechaModificacion);
        return copy;
    }
}
