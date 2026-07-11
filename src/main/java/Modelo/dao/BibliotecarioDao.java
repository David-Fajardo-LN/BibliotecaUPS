/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao;

import Modelo.dominio.Bibliotecario;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class BibliotecarioDao implements InterfazDao<Bibliotecario>{
    
    private ArrayList<Bibliotecario> bibliotecarios;

    public BibliotecarioDao() {
        this.bibliotecarios = generarBibliotecariosIniciales();
    }
    
    @Override
    public Bibliotecario buscar(String cedulaBuscada) {
        for(Bibliotecario b : bibliotecarios)
            if(b.getCedula().equals(cedulaBuscada))
                return b;
        return null;
    }

    @Override
    public void eliminar(String cedulaAEliminar) {
        bibliotecarios.removeIf(b -> b.getCedula().equals(cedulaAEliminar));
    }

    @Override
    public void agregar(Bibliotecario nuevoBibliotecario) {
        bibliotecarios.add(nuevoBibliotecario);
    }

    @Override
    public void actualizar(Bibliotecario otro) {
        for(Bibliotecario b : bibliotecarios){
            if(b.getCedula().equals(otro.getCedula())){
                b.setEmail(otro.getEmail());
                b.setNombre(otro.getNombre());
                b.setSector(otro.getSector());
                b.setTelefono(otro.getTelefono());
                return;
            }
        }
    }

    @Override
    public boolean existe(String otraCedula) {
       for(Bibliotecario b : bibliotecarios)
            if(b.getCedula().equals(otraCedula))
                return true;
        return false;
    }

    @Override
    public ArrayList obtenerLista() {
        return bibliotecarios;
    }
    
    private ArrayList<Bibliotecario> generarBibliotecariosIniciales(){
        
        ArrayList<Bibliotecario> bibliotecarios = new ArrayList<>();
        bibliotecarios.add(new Bibliotecario("Planta A",true,"1","Carlos Herrera","carlos.herrera@biblioteca.com","0992345678","Supervisor"));
        bibliotecarios.add(new Bibliotecario("Planta B",false,"2","María Cordero","maria.cordero@biblioteca.com","0983456789","Archivador"));
        bibliotecarios.add(new Bibliotecario("Planta C",true,"3","Andrés Salazar","andres.salazar@biblioteca.com","0974567890","Supervisor"));
        bibliotecarios.add(new Bibliotecario("Planta A",false,"4","Gabriela Torres","gabriela.torres@biblioteca.com","0965678901","Archivador"));
        bibliotecarios.add(new Bibliotecario("Planta B",false,"5","Luis Mendoza","luis.mendoza@biblioteca.com","0956789012","Archivador"));
        return bibliotecarios;
    }    
}
