import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterAppAfiliadoModule } from './afiliado/afiliado.module';
import { JhipsterAppModuloModule } from './modulo/modulo.module';
import { JhipsterAppSubModuloModule } from './sub-modulo/sub-modulo.module';
import { JhipsterAppUserAuthoritySubModuloModule } from './user-authority-sub-modulo/user-authority-sub-modulo.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhipsterAppAfiliadoModule,
        JhipsterAppModuloModule,
        JhipsterAppSubModuloModule,
        JhipsterAppUserAuthoritySubModuloModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterAppEntityModule {}
