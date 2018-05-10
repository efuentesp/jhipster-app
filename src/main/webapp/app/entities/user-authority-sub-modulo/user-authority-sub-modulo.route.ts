import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { UserAuthoritySubModuloComponent } from './user-authority-sub-modulo.component';
import { UserAuthoritySubModuloDetailComponent } from './user-authority-sub-modulo-detail.component';
import { UserAuthoritySubModuloPopupComponent } from './user-authority-sub-modulo-dialog.component';
import { UserAuthoritySubModuloDeletePopupComponent } from './user-authority-sub-modulo-delete-dialog.component';

@Injectable()
export class UserAuthoritySubModuloResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const userAuthoritySubModuloRoute: Routes = [
    {
        path: 'user-authority-sub-modulo',
        component: UserAuthoritySubModuloComponent,
        resolve: {
            'pagingParams': UserAuthoritySubModuloResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.userAuthoritySubModulo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'user-authority-sub-modulo/:id',
        component: UserAuthoritySubModuloDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.userAuthoritySubModulo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userAuthoritySubModuloPopupRoute: Routes = [
    {
        path: 'user-authority-sub-modulo-new',
        component: UserAuthoritySubModuloPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.userAuthoritySubModulo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-authority-sub-modulo/:id/edit',
        component: UserAuthoritySubModuloPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.userAuthoritySubModulo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'user-authority-sub-modulo/:id/delete',
        component: UserAuthoritySubModuloDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.userAuthoritySubModulo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
