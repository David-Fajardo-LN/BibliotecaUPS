/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dominio;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Bibliotecario extends Persona{
    // permiso comun -> false      permiso avanzado -> true
    
    private String sector;
    private String cargo;
    private boolean tienePermisoAvanzado;
    private ArrayList<Prestamo> prestamos;

    // ESTE CONSTRUCTOR NO SE USA
    
    public Bibliotecario(String sector, boolean permiso, String cedula, String nombre, String email, String telefono, String cargo) {
        super(cedula, nombre, email, telefono);
        this.sector = sector;
        this.tienePermisoAvanzado = permiso;
        this.cargo=cargo;
        this.prestamos = new ArrayList<>();
    }

    public Bibliotecario(String sector, String cedula, String nombre, String email, String telefono) {
        super(cedula, nombre, email, telefono);
        this.sector = sector;
        this.prestamos = new ArrayList<>();
    }

    public boolean isTienePermisoAvanzado() {
        return tienePermisoAvanzado;
    }

    public void setTienePermisoAvanzado(boolean tienePermisoAvanzado) {
        this.tienePermisoAvanzado = tienePermisoAvanzado;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void sePrestamo(Prestamo p) {
        this.prestamos.add(p);
    }
    

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean tienePermisoAvanzado() {
        return tienePermisoAvanzado;
    }

    public void quitarPermisoAvanzado() {
        this.tienePermisoAvanzado = false;
    }
    
    public void eliminarPrestamoDeBibliotecario(String codigoPrestamo){
        for(Prestamo p : prestamos){
            if(p.getCodigo().equals(codigoPrestamo)){
                prestamos.remove(p);
                return;
            }
        }
    }
    
    public void agregarPrestamo(Prestamo p){
        prestamos.add(p);
    }
}
