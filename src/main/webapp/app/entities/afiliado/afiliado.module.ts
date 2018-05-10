import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterAppSharedModule } from '../../shared';
import {
    AfiliadoService,
    AfiliadoPopupService,
    AfiliadoComponent,
    AfiliadoDetailComponent,
    AfiliadoDialogComponent,
    AfiliadoPopupComponent,
    AfiliadoDeletePopupComponent,
    AfiliadoDeleteDialogComponent,
    afiliadoRoute,
    afiliadoPopupRoute,
    AfiliadoResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...afiliadoRoute,
    ...afiliadoPopupRoute,
];

@NgModule({
    imports: [
        JhipsterAppSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AfiliadoComponent,
        AfiliadoDetailComponent,
        AfiliadoDialogComponent,
        AfiliadoDeleteDialogComponent,
        AfiliadoPopupComponent,
        AfiliadoDeletePopupComponent,
    ],
    entryComponents: [
        AfiliadoComponent,
        AfiliadoDialogComponent,
        AfiliadoPopupComponent,
        AfiliadoDeleteDialogComponent,
        AfiliadoDeletePopupComponent,
    ],
    providers: [
        AfiliadoService,
        AfiliadoPopupService,
        AfiliadoResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterAppAfiliadoModule {}
