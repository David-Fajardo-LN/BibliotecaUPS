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
    
    private String sector;
    private String cargo;

    public Bibliotecario(String sector, String cargo, String cedula, String nombre, String email, String telefono) {
        super(cedula, nombre, email, telefono);
        this.sector = sector;
        this.cargo = cargo;
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
}
