package com.examen3java.desarrolloweb.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "proveedores") // Nombre de tabla corregido
public class Proveedor { // Nombre de clase corregido

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistribuidor; // Nombre de campo corregido

    @Column(name = "nombre_distribuidor", length = 50, nullable = false)
    @NotNull
    @Size(max = 50)
    private String nombreDistribuidor; // Nombre de campo corregido

    @Column(name = "contacto", length = 50, nullable = false)
    @NotNull
    @Size(max = 50)
    private String contacto;

    @Column(name = "email", length = 50, nullable = false)
    @NotNull
    @Size(max = 50)
    private String email;

    @Column(name = "telefono", length = 20, nullable = false) // Cambiado a String
    @NotNull
    @Size(max = 20)
    private String telefono;

    @Column(name = "direccion", length = 100, nullable = false) // Aumentado el tamaño
    @NotNull
    @Size(max = 100)
    private String direccion;

    @Column(name = "pais", length = 50, nullable = false)
    @NotNull
    @Size(max = 50)
    private String pais;

    @Column(name = "tipo_proveedor", length = 50, nullable = false)
    @NotNull
    @Size(max = 50)
    private String tipoProveedor; // Nombre de campo corregido

    @Column(name = "ruc", length = 20, nullable = false) // Ajustado tamaño para RUC
    @NotNull
    @Size(max = 20)
    private String ruc;

    @Column(name = "estado", length = 20, nullable = false)
    @Size(max = 20)
    private String estado = "ACTIVO"; // Valor por defecto en mayúsculas

    /**
     * Fecha de registro del proveedor. Se establece automáticamente al crear el
     * proveedor y no puede modificarse.
     */
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDate fechaRegistro = LocalDate.now();

    @Column(name = "monto_credito", precision = 15, scale = 2, nullable = false)
    @NotNull
    private BigDecimal montoCredito;

    // Constructores
    public Proveedor() {
    }

    public Proveedor(String nombreDistribuidor, String contacto, String email,
            String telefono, String direccion, String pais,
            String tipoProveedor, String ruc, BigDecimal montoCredito) {
        this.nombreDistribuidor = nombreDistribuidor;
        this.contacto = contacto;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.pais = pais;
        this.tipoProveedor = tipoProveedor;
        this.ruc = ruc;
        this.montoCredito = montoCredito;
    }

    // Getters and Setters (manteniendo nombres consistentes)
    public Long getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(Long idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public String getNombreDistribuidor() {
        return nombreDistribuidor;
    }

    public void setNombreDistribuidor(String nombreDistribuidor) {
        this.nombreDistribuidor = nombreDistribuidor;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    // Eliminado el setter para fechaRegistro para mantenerla inmutable
    // después de la creación
    public BigDecimal getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(BigDecimal montoCredito) {
        this.montoCredito = montoCredito;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTipoProveedor() {
        return tipoProveedor;
    }

    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
