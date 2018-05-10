import { BaseEntity } from './../../shared';

export class Modulo implements BaseEntity {
    constructor(
        public id?: number,
        public idModulo?: number,
        public nombre?: string,
        public estatus?: boolean,
        public fechaCreacion?: any,
        public fechaModificacion?: any,
    ) {
        this.estatus = false;
    }
}
