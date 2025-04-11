package pe.idat.semana12.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
@Entity
@Table(name="contactos")
@NamedQuery(name="Contacto.findAll",query = "SELECT c from Contacto c")
public class Contacto {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContacto;
    @Column
    private int edad;
    @Column
    private String email;
    @Column
    private String nombre;
    //Getters and setters
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Long getIdContacto() {
        return idContacto;
    }
    public void setIdContacto(Long idContacto) {
        this.idContacto = idContacto;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
