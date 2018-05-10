import { BaseEntity } from './../../shared';

export class Afiliado implements BaseEntity {
    constructor(
        public id?: number,
        public nombre?: string,
        public apellidopaterno?: string,
        public apellidomaterno?: string,
        public nss?: number,
        public salario?: number,
        public peso?: number,
        public datoDouble?: number,
        public datoBigDecimal?: number,
        public fechaAfiliacion?: any,
        public datoInstant?: any,
        public datoZone?: any,
        public activo?: boolean,
        public fotografiaContentType?: string,
        public fotografia?: any,
    ) {
        this.activo = false;
    }
}
