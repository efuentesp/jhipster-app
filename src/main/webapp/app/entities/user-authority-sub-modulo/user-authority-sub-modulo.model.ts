import { BaseEntity } from './../../shared';

export class UserAuthoritySubModulo implements BaseEntity {
    constructor(
        public id?: number,
        public idSubModulo?: number,
        public idUserAuthority?: number,
        public estatus?: boolean,
        public fechaCreacion?: any,
        public fechaModificacion?: any,
    ) {
        this.estatus = false;
    }
}
