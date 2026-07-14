/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dominio;

/**
 *
 * @author User
 */
public class Bibliotecario extends Persona{
    // permiso comun -> false      permiso avanzado -> true
    
    private String sector;
    private String cargo;
    private boolean tienePermisoAvanzado;

    public Bibliotecario(String sector, String cargo, boolean tienePermisoAvanzado, String cedula, String nombre, String email, String telefono) {
        super(cedula, nombre, email, telefono);
        this.sector = sector;
        this.cargo = cargo;
        this.tienePermisoAvanzado = tienePermisoAvanzado;
    }

    

    public boolean isTienePermisoAvanzado() {
        return tienePermisoAvanzado;
    }

    public void setTienePermisoAvanzado(boolean tienePermisoAvanzado) {
        this.tienePermisoAvanzado = tienePermisoAvanzado;
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
}
