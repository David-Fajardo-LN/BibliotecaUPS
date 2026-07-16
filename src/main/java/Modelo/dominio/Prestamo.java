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
    private String bibliotecarioCedula;
    private String usuarioCedula;
    private String libroISBN;
    
    private Sancion sancion;

    //ESTE CONSTRUCTOR SE USO SOLO PARA GENERAR LOS PRESTAMOS HISTORICOS ----- NO USAR -----
    public Prestamo(String codigo, LocalDate fechaDePrestamo, LocalDate fechaLimiteDePrestamo, LocalDate fechaDeDevolucion, String bibliotecarioCedula, String usuarioCedula, String libroISBN) {
        this.codigo = codigo;
        this.fechaDePrestamo = fechaDePrestamo;
        this.fechaLimiteDePrestamo = fechaLimiteDePrestamo;
        this.fechaDeDevolucion = fechaDeDevolucion;
        this.bibliotecarioCedula = bibliotecarioCedula;
        this.usuarioCedula = usuarioCedula;
        this.libroISBN = libroISBN;
        this.sancion=null;
    }

    public Prestamo(String codigo, String bibliotecarioCedula, String usuarioCedula, String libroISBN) {
        this.codigo = codigo;
        this.fechaDePrestamo = LocalDate.now();
        this.fechaLimiteDePrestamo = LocalDate.now().plusDays(14);
        this.fechaDeDevolucion = null;
        this.bibliotecarioCedula = bibliotecarioCedula;
        this.usuarioCedula = usuarioCedula;
        this.libroISBN = libroISBN;
        this.sancion = null;
        this.estado = true;
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

    public String getBibliotecarioCedula() {
        return bibliotecarioCedula;
    }

    public String getUsuarioCedula() {
        return usuarioCedula;
    }

    public String getLibroISBN() {
        return libroISBN;
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
