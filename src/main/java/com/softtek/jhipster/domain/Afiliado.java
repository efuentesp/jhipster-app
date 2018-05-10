package com.softtek.jhipster.domain;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Afiliado.
 */
@Entity
@Table(name = "afiliado")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Afiliado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "apellidopaterno", nullable = false)
    private String apellidopaterno;

    @Column(name = "apellidomaterno")
    private String apellidomaterno;

    @NotNull
    @Column(name = "nss", nullable = false)
    private Integer nss;

    @Column(name = "salario")
    private Long salario;

    @Column(name = "peso")
    private Float peso;

    @Column(name = "dato_double")
    private Double datoDouble;

    @Column(name = "dato_big_decimal", precision=10, scale=2)
    private BigDecimal datoBigDecimal;

    @Column(name = "fecha_afiliacion")
    private LocalDate fechaAfiliacion;

    @Column(name = "dato_instant")
    private Instant datoInstant;

    /**
     * No es soportado por REST
     */
    @ApiModelProperty(value = "No es soportado por REST")
    @Column(name = "dato_zone")
    private ZonedDateTime datoZone;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "fotografia")
    private byte[] fotografia;

    @Column(name = "fotografia_content_type")
    private String fotografiaContentType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Afiliado nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public Afiliado apellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
        return this;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public Afiliado apellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
        return this;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public Integer getNss() {
        return nss;
    }

    public Afiliado nss(Integer nss) {
        this.nss = nss;
        return this;
    }

    public void setNss(Integer nss) {
        this.nss = nss;
    }

    public Long getSalario() {
        return salario;
    }

    public Afiliado salario(Long salario) {
        this.salario = salario;
        return this;
    }

    public void setSalario(Long salario) {
        this.salario = salario;
    }

    public Float getPeso() {
        return peso;
    }

    public Afiliado peso(Float peso) {
        this.peso = peso;
        return this;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Double getDatoDouble() {
        return datoDouble;
    }

    public Afiliado datoDouble(Double datoDouble) {
        this.datoDouble = datoDouble;
        return this;
    }

    public void setDatoDouble(Double datoDouble) {
        this.datoDouble = datoDouble;
    }

    public BigDecimal getDatoBigDecimal() {
        return datoBigDecimal;
    }

    public Afiliado datoBigDecimal(BigDecimal datoBigDecimal) {
        this.datoBigDecimal = datoBigDecimal;
        return this;
    }

    public void setDatoBigDecimal(BigDecimal datoBigDecimal) {
        this.datoBigDecimal = datoBigDecimal;
    }

    public LocalDate getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public Afiliado fechaAfiliacion(LocalDate fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
        return this;
    }

    public void setFechaAfiliacion(LocalDate fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public Instant getDatoInstant() {
        return datoInstant;
    }

    public Afiliado datoInstant(Instant datoInstant) {
        this.datoInstant = datoInstant;
        return this;
    }

    public void setDatoInstant(Instant datoInstant) {
        this.datoInstant = datoInstant;
    }

    public ZonedDateTime getDatoZone() {
        return datoZone;
    }

    public Afiliado datoZone(ZonedDateTime datoZone) {
        this.datoZone = datoZone;
        return this;
    }

    public void setDatoZone(ZonedDateTime datoZone) {
        this.datoZone = datoZone;
    }

    public Boolean isActivo() {
        return activo;
    }

    public Afiliado activo(Boolean activo) {
        this.activo = activo;
        return this;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public Afiliado fotografia(byte[] fotografia) {
        this.fotografia = fotografia;
        return this;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public String getFotografiaContentType() {
        return fotografiaContentType;
    }

    public Afiliado fotografiaContentType(String fotografiaContentType) {
        this.fotografiaContentType = fotografiaContentType;
        return this;
    }

    public void setFotografiaContentType(String fotografiaContentType) {
        this.fotografiaContentType = fotografiaContentType;
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
        Afiliado afiliado = (Afiliado) o;
        if (afiliado.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), afiliado.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Afiliado{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellidopaterno='" + getApellidopaterno() + "'" +
            ", apellidomaterno='" + getApellidomaterno() + "'" +
            ", nss=" + getNss() +
            ", salario=" + getSalario() +
            ", peso=" + getPeso() +
            ", datoDouble=" + getDatoDouble() +
            ", datoBigDecimal=" + getDatoBigDecimal() +
            ", fechaAfiliacion='" + getFechaAfiliacion() + "'" +
            ", datoInstant='" + getDatoInstant() + "'" +
            ", datoZone='" + getDatoZone() + "'" +
            ", activo='" + isActivo() + "'" +
            ", fotografia='" + getFotografia() + "'" +
            ", fotografiaContentType='" + getFotografiaContentType() + "'" +
            "}";
    }
}
