package com.example.agendaContactos.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contactos")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Debe ingresar un nombre")
    private String nombre;

    @NotEmpty(message = "Debe ingresar un correo electrónico")
    @Email(message = "La casilla de correo debe contener @")
    private String email;

    @NotBlank(message = "Debe ingresar un nro de teléfono móvil")
    private String celular;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "No puede ser una fecha posterior a la actual")
    @NotNull(message = "Debe ingresar una fecha")
    private LocalDate fechaNacimiento;

    private LocalDateTime fechaRegistro;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @PrePersist
    public void asignarFechaDeRegistro(){
        fechaRegistro = LocalDateTime.now();
    }
}
