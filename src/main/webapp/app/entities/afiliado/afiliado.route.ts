import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { AfiliadoComponent } from './afiliado.component';
import { AfiliadoDetailComponent } from './afiliado-detail.component';
import { AfiliadoPopupComponent } from './afiliado-dialog.component';
import { AfiliadoDeletePopupComponent } from './afiliado-delete-dialog.component';

@Injectable()
export class AfiliadoResolvePagingParams implements Resolve<any> {

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

export const afiliadoRoute: Routes = [
    {
        path: 'afiliado',
        component: AfiliadoComponent,
        resolve: {
            'pagingParams': AfiliadoResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.afiliado.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'afiliado/:id',
        component: AfiliadoDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.afiliado.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const afiliadoPopupRoute: Routes = [
    {
        path: 'afiliado-new',
        component: AfiliadoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.afiliado.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'afiliado/:id/edit',
        component: AfiliadoPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.afiliado.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'afiliado/:id/delete',
        component: AfiliadoDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterAppApp.afiliado.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
