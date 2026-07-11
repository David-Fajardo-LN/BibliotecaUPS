/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author User
 */
public class Autor {
    private String codigoIdentificador;
    private String nombre;
    private String nacionalidad;
    private String estiloLiterario;
    private LocalDate fechaDeNacimiento;
    
    private ArrayList<Libro> libros;

    public Autor(String identificador, String nombre, String nacionalidad, LocalDate fechaDeNacimiento, String estiloL) {
        this.codigoIdentificador = identificador;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.estiloLiterario = estiloL;
        this.libros = new ArrayList<>();
    }

    public String getIdentificador() {
        return codigoIdentificador;
    }

    public void setIdentificador(String identificador) {
        this.codigoIdentificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public String getEstiloLiterario() {
        return estiloLiterario;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public void agregarLibro(Libro l) {
        this.libros.add(l);
    }

    public void setEstiloLiterario(String estiloLiterario) {
        this.estiloLiterario = estiloLiterario;
    }
    
    public int librosActivos(){
        return libros.size();
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.codigoIdentificador);
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
        final Autor other = (Autor) obj;
        return Objects.equals(this.codigoIdentificador, other.codigoIdentificador);
    }
}
