/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dominio;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author User
 */
public class Prestamo {
    
    private String codigo;
    private boolean estado;
    private LocalDate fechaDePrestamo;
    private LocalDate fechaLimiteDePrestamo;
    private LocalDate fechaDeDevolucion;
    private Bibliotecario bibliotecario;
    private Usuario usuario;
    private Libro libro;
    
    private Sancion sancion;

    //ESTE CONSTRUCTOR SE USO SOLO PARA GENERAR LOS PRESTAMOS HISTORICOS ----- NO USAR -----
    public Prestamo(String codigo, LocalDate fechaDePrestamo, LocalDate fechaLimiteDePrestamo, LocalDate fechaDeDevolucion, Bibliotecario bibliotecario, Usuario usuario, Libro libro) {
        this.codigo = codigo;
        this.fechaDePrestamo = fechaDePrestamo;
        this.fechaLimiteDePrestamo = fechaLimiteDePrestamo;
        this.fechaDeDevolucion = fechaDeDevolucion;
        this.bibliotecario = bibliotecario;
        this.usuario = usuario;
        this.libro = libro;
        this.sancion=null;
    }

    // SOLO FUE USADO PARA GENERAR UN PRESTAMO TARDIO NO USAR
    public Prestamo(String codigo, LocalDate fechaDePrestamo, Bibliotecario bibliotecario, Usuario usuario, Libro libro, Sancion sancion) {
        this.codigo = codigo;
        this.estado = true;
        this.fechaDePrestamo = fechaDePrestamo;
        this.fechaLimiteDePrestamo = LocalDate.now().minusDays(6);
        this.bibliotecario = bibliotecario;
        this.usuario = usuario;
        this.libro = libro;
        this.sancion = sancion;
    }

    public Prestamo(String codigo, Bibliotecario bibliotecario, Usuario usuario, Libro libro) {
        this.estado = true;
        this.codigo = codigo;
        this.bibliotecario = bibliotecario;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaDePrestamo = LocalDate.now();
        this.fechaLimiteDePrestamo = fechaDePrestamo.plusDays(3);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaDePrestamo() {
        return fechaDePrestamo;
    }

    public void setFechaDePrestamo(LocalDate fechaDePrestamo) {
        this.fechaDePrestamo = fechaDePrestamo;
    }

    public LocalDate getFechaLimiteDePrestamo() {
        return fechaLimiteDePrestamo;
    }

    public void setFechaLimiteDePrestamo(LocalDate fechaLimiteDePrestamo) {
        this.fechaLimiteDePrestamo = fechaLimiteDePrestamo;
    }

    public LocalDate getFechaDeDevolucion() {
        return fechaDeDevolucion;
    }

    public void setFechaDeDevolucion(LocalDate fechaDeDevolucion) {
        this.fechaDeDevolucion = fechaDeDevolucion;
    }

    public Bibliotecario getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(Bibliotecario bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Sancion tieneSancion() {
        return sancion;
    }

    public void setSancion(Sancion sancion) {
        this.sancion = sancion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void cambiarEstadoFalse() {
        this.estado = false;
    }
    
    

    public Sancion getSancion() {
        return sancion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prestamo other = (Prestamo) obj;
        return Objects.equals(this.codigo, other.codigo);
    }
}
