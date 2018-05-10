import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterAppSharedModule } from '../../shared';
import {
    SubModuloService,
    SubModuloPopupService,
    SubModuloComponent,
    SubModuloDetailComponent,
    SubModuloDialogComponent,
    SubModuloPopupComponent,
    SubModuloDeletePopupComponent,
    SubModuloDeleteDialogComponent,
    subModuloRoute,
    subModuloPopupRoute,
    SubModuloResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...subModuloRoute,
    ...subModuloPopupRoute,
];

@NgModule({
    imports: [
        JhipsterAppSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SubModuloComponent,
        SubModuloDetailComponent,
        SubModuloDialogComponent,
        SubModuloDeleteDialogComponent,
        SubModuloPopupComponent,
        SubModuloDeletePopupComponent,
    ],
    entryComponents: [
        SubModuloComponent,
        SubModuloDialogComponent,
        SubModuloPopupComponent,
        SubModuloDeleteDialogComponent,
        SubModuloDeletePopupComponent,
    ],
    providers: [
        SubModuloService,
        SubModuloPopupService,
        SubModuloResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterAppSubModuloModule {}
