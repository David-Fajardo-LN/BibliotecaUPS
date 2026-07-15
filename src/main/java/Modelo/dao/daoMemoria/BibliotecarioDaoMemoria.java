/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao.daoMemoria;

import Modelo.dao.InterfazDao;
import Modelo.dominio.Bibliotecario;
import java.util.ArrayList;

/**
 *
 * @author User
 */
import java.util.ArrayList;

public class BibliotecarioDaoMemoria implements InterfazDao<Bibliotecario> {

    private ArrayList<Bibliotecario> bibliotecarios;

    public BibliotecarioDaoMemoria() {
        this.bibliotecarios = generarBibliotecariosIniciales();
    }

    @Override
    public Bibliotecario buscar(String cedula) {
        for (Bibliotecario b : bibliotecarios) {
            if (b.getCedula().equals(cedula)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String cedula) {
        bibliotecarios.removeIf(b -> b.getCedula().equals(cedula));
    }

    @Override
    public void agregar(Bibliotecario b) {
        bibliotecarios.add(b);
    }

    @Override
    public void actualizar(Bibliotecario otro) {
        for (Bibliotecario b : bibliotecarios) {
            if (b.getCedula().equals(otro.getCedula())) {
                b.setEmail(otro.getEmail());
                b.setNombre(otro.getNombre());
                b.setSector(otro.getSector());
                b.setTelefono(otro.getTelefono());
                b.setCargo(otro.getCargo());
                return;
            }
        }
    }

    @Override
    public boolean existe(String cedula) {
        for (Bibliotecario b : bibliotecarios) {
            if (b.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Bibliotecario> obtenerLista() {
        return bibliotecarios;
    }

    private ArrayList<Bibliotecario> generarBibliotecariosIniciales() {
        ArrayList<Bibliotecario> lista = new ArrayList<>();
        
        // Constructor: (sector, cargo, tienePermisoAvanzado, cedula, nombre, email, telefono)
        lista.add(new Bibliotecario("Planta A", "Supervisor",  "1", "Carlos Herrera", "carlos.herrera@biblioteca.com", "0992345678"));
        lista.add(new Bibliotecario("Planta B", "Archivador", "2", "María Cordero", "maria.cordero@biblioteca.com", "0983456789"));
        lista.add(new Bibliotecario("Planta C", "Supervisor",  "3", "Andrés Salazar", "andres.salazar@biblioteca.com", "0974567890"));
        lista.add(new Bibliotecario("Planta A", "Archivador", "4", "Gabriela Torres", "gabriela.torres@biblioteca.com", "0965678901"));
        lista.add(new Bibliotecario("Planta B", "Archivador", "5", "Luis Mendoza", "luis.mendoza@biblioteca.com", "0956789012"));
        
        return lista;
    }
}
