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
public class Usuario extends Persona{
    
    private boolean esValido;

    public Usuario(String cedula, String nombre, String email, String telefono) {
        super(cedula, nombre, email, telefono);
        this.esValido = true;
    }

    public boolean esValido() {
        return esValido;
    }

    public void cambiarEstadoAInvalido() {
        this.esValido = false;
    }
    
    public void cambiarEstadoAValido(){
        this.esValido=true;
    }
}
