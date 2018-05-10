import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { UserAuthoritySubModulo } from './user-authority-sub-modulo.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UserAuthoritySubModulo>;

@Injectable()
export class UserAuthoritySubModuloService {

    private resourceUrl =  SERVER_API_URL + 'api/user-authority-sub-modulos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(userAuthoritySubModulo: UserAuthoritySubModulo): Observable<EntityResponseType> {
        const copy = this.convert(userAuthoritySubModulo);
        return this.http.post<UserAuthoritySubModulo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(userAuthoritySubModulo: UserAuthoritySubModulo): Observable<EntityResponseType> {
        const copy = this.convert(userAuthoritySubModulo);
        return this.http.put<UserAuthoritySubModulo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UserAuthoritySubModulo>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UserAuthoritySubModulo[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserAuthoritySubModulo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserAuthoritySubModulo[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UserAuthoritySubModulo = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UserAuthoritySubModulo[]>): HttpResponse<UserAuthoritySubModulo[]> {
        const jsonResponse: UserAuthoritySubModulo[] = res.body;
        const body: UserAuthoritySubModulo[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UserAuthoritySubModulo.
     */
    private convertItemFromServer(userAuthoritySubModulo: UserAuthoritySubModulo): UserAuthoritySubModulo {
        const copy: UserAuthoritySubModulo = Object.assign({}, userAuthoritySubModulo);
        copy.fechaCreacion = this.dateUtils
            .convertLocalDateFromServer(userAuthoritySubModulo.fechaCreacion);
        copy.fechaModificacion = this.dateUtils
            .convertLocalDateFromServer(userAuthoritySubModulo.fechaModificacion);
        return copy;
    }

    /**
     * Convert a UserAuthoritySubModulo to a JSON which can be sent to the server.
     */
    private convert(userAuthoritySubModulo: UserAuthoritySubModulo): UserAuthoritySubModulo {
        const copy: UserAuthoritySubModulo = Object.assign({}, userAuthoritySubModulo);
        copy.fechaCreacion = this.dateUtils
            .convertLocalDateToServer(userAuthoritySubModulo.fechaCreacion);
        copy.fechaModificacion = this.dateUtils
            .convertLocalDateToServer(userAuthoritySubModulo.fechaModificacion);
        return copy;
    }
}
