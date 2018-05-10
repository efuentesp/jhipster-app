import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SubModuloComponent } from './sub-modulo.component';
import { SubModuloDetailComponent } from './sub-modulo-detail.component';
import { SubModuloPopupComponent } from './sub-modulo-dialog.component';
import { SubModuloDeletePopupComponent } from './sub-modulo-delete-dialog.component';

@Injectable()
export class SubModuloResolvePagingParams implements Resolve<any> {

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

export const subModuloRoute: Routes = [
    {
        path: 'sub-modulo',
        component: SubModuloComponent,
        resolve: {
            'pagingParams': SubModuloResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.subModulo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sub-modulo/:id',
        component: SubModuloDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.subModulo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const subModuloPopupRoute: Routes = [
    {
        path: 'sub-modulo-new',
        component: SubModuloPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.subModulo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sub-modulo/:id/edit',
        component: SubModuloPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.subModulo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sub-modulo/:id/delete',
        component: SubModuloDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.subModulo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
