import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterAppSharedModule } from '../../shared';
import {
    UserAuthoritySubModuloService,
    UserAuthoritySubModuloPopupService,
    UserAuthoritySubModuloComponent,
    UserAuthoritySubModuloDetailComponent,
    UserAuthoritySubModuloDialogComponent,
    UserAuthoritySubModuloPopupComponent,
    UserAuthoritySubModuloDeletePopupComponent,
    UserAuthoritySubModuloDeleteDialogComponent,
    userAuthoritySubModuloRoute,
    userAuthoritySubModuloPopupRoute,
    UserAuthoritySubModuloResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...userAuthoritySubModuloRoute,
    ...userAuthoritySubModuloPopupRoute,
];

@NgModule({
    imports: [
        JhipsterAppSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UserAuthoritySubModuloComponent,
        UserAuthoritySubModuloDetailComponent,
        UserAuthoritySubModuloDialogComponent,
        UserAuthoritySubModuloDeleteDialogComponent,
        UserAuthoritySubModuloPopupComponent,
        UserAuthoritySubModuloDeletePopupComponent,
    ],
    entryComponents: [
        UserAuthoritySubModuloComponent,
        UserAuthoritySubModuloDialogComponent,
        UserAuthoritySubModuloPopupComponent,
        UserAuthoritySubModuloDeleteDialogComponent,
        UserAuthoritySubModuloDeletePopupComponent,
    ],
    providers: [
        UserAuthoritySubModuloService,
        UserAuthoritySubModuloPopupService,
        UserAuthoritySubModuloResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterAppUserAuthoritySubModuloModule {}
