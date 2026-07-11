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
public class Sancion {
    
    
    private String codigo;
    private LocalDate fechaDeSancion;
    private String descripción;
    private double monto;
    private boolean estado;
    private Prestamo prestamo;

    public Sancion(String codigo, LocalDate fechaDeSancion, String descripción, double monto, Prestamo prestamo) {
        this.codigo = codigo;
        this.fechaDeSancion = fechaDeSancion;
        this.descripción = descripción;
        this.monto = monto;
        this.estado=true;
        this.prestamo = prestamo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFechaDeSancion() {
        return fechaDeSancion;
    }

    public void setFechaDeSancion(LocalDate fechaDeSancion) {
        this.fechaDeSancion = fechaDeSancion;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.codigo);
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
        final Sancion other = (Sancion) obj;
        return Objects.equals(this.codigo, other.codigo);
    }
}
