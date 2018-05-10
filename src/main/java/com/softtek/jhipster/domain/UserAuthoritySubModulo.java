package com.softtek.jhipster.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A UserAuthoritySubModulo.
 */
@Entity
@Table(name = "user_authority_sub_modulo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserAuthoritySubModulo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "id_sub_modulo", nullable = false)
    private Integer idSubModulo;

    @NotNull
    @Column(name = "id_user_authority", nullable = false)
    private Integer idUserAuthority;

    @Column(name = "estatus")
    private Boolean estatus;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdSubModulo() {
        return idSubModulo;
    }

    public UserAuthoritySubModulo idSubModulo(Integer idSubModulo) {
        this.idSubModulo = idSubModulo;
        return this;
    }

    public void setIdSubModulo(Integer idSubModulo) {
        this.idSubModulo = idSubModulo;
    }

    public Integer getIdUserAuthority() {
        return idUserAuthority;
    }

    public UserAuthoritySubModulo idUserAuthority(Integer idUserAuthority) {
        this.idUserAuthority = idUserAuthority;
        return this;
    }

    public void setIdUserAuthority(Integer idUserAuthority) {
        this.idUserAuthority = idUserAuthority;
    }

    public Boolean isEstatus() {
        return estatus;
    }

    public UserAuthoritySubModulo estatus(Boolean estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public UserAuthoritySubModulo fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public UserAuthoritySubModulo fechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserAuthoritySubModulo userAuthoritySubModulo = (UserAuthoritySubModulo) o;
        if (userAuthoritySubModulo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userAuthoritySubModulo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserAuthoritySubModulo{" +
            "id=" + getId() +
            ", idSubModulo=" + getIdSubModulo() +
            ", idUserAuthority=" + getIdUserAuthority() +
            ", estatus='" + isEstatus() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", fechaModificacion='" + getFechaModificacion() + "'" +
            "}";
    }
}
