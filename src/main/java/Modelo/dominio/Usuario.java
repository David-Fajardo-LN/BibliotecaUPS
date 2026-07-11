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
    private ArrayList<Prestamo> prestamos;
    private ArrayList<Sancion> sanciones;

    public Usuario(String cedula, String nombre, String email, String telefono) {
        super(cedula, nombre, email, telefono);
        this.esValido = true;
        this.prestamos = new ArrayList<>();
        this.sanciones = new ArrayList<>();
    }

    public boolean esValido() {
        return esValido;
    }

    public void noEsValido() {
        this.esValido = false;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void agregarPrestamo(Prestamo p) {
        this.prestamos.add(p);
    }

    public ArrayList<Sancion> getSanciones() {
        return sanciones;
    }

    public void setSancione(Sancion s) {
        this.sanciones.add(s);
    }
    
    public void eliminarPrestamoDeUsuario(String codigoPrestamo){
        for(Prestamo p : prestamos){
            if(p.getCodigo().equals(codigoPrestamo)){
                prestamos.remove(p);
                return;
            }
        }
    }
}
