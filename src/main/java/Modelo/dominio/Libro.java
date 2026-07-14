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
public class Libro {
    private String ISBN;
    private String nombre;
    private LocalDate fechaDePublicacion;
    private int cantidadDisponible;
    private int cantidadTotal;
    private String generoLiterario;
    private String identificadorAutor;

    public Libro(String ISBN, String nombre, LocalDate fechaDePublicacion, int cantidadTotal, String identificadorAutor, String generoL) {
        this.ISBN = ISBN;
        this.nombre = nombre;
        this.fechaDePublicacion = fechaDePublicacion;
        this.cantidadTotal = cantidadTotal;
        this.cantidadDisponible = cantidadTotal;
        this.generoLiterario= generoL;
        this.identificadorAutor = identificadorAutor;
    }

    public Libro(String ISBN, String nombre, LocalDate fechaDePublicacion, int cantidadDisponible, int cantidadTotal, String generoLiterario) {
        this.ISBN = ISBN;
        this.nombre = nombre;
        this.fechaDePublicacion = fechaDePublicacion;
        this.cantidadDisponible = cantidadDisponible;
        this.cantidadTotal = cantidadTotal;
        this.generoLiterario = generoLiterario;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(LocalDate fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public String getAutor() {
        return identificadorAutor;
    }

    public void setAutor(String identificadorAutor) {
        this.identificadorAutor = identificadorAutor;
    }

    public String getGeneroLiterario() {
        return generoLiterario;
    }

    public void setGeneroLiterario(String generoLiterario) {
        this.generoLiterario = generoLiterario;
    }
    
    public void restarDisponibilidad(){
        this.cantidadDisponible -=1; 
    }
    
    public void sumarDisponibilidad(){
        this.cantidadDisponible +=1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.ISBN);
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
        final Libro other = (Libro) obj;
        return Objects.equals(this.ISBN, other.ISBN);
    }  
}
