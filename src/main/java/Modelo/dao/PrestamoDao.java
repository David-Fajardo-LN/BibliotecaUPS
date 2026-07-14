/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao;

import Modelo.dominio.Bibliotecario;
import Modelo.dominio.Libro;
import Modelo.dominio.Prestamo;
import Modelo.dominio.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PrestamoDao implements InterfazDao<Prestamo>{
    private ArrayList<Prestamo> prestamos;

    public PrestamoDao(ArrayList<Bibliotecario> bibliotecarios, ArrayList<Usuario> usuarios, ArrayList<Libro> libros) {
        this.prestamos = new ArrayList<>();
    }
    
    
    @Override
    public Prestamo buscar(String codigo) {
        for(Prestamo p : prestamos)
            if(p.getCodigo().equals(codigo))
                return p;
        return null;
    }

    @Override
    public void eliminar(String codigo) {
       prestamos.removeIf(p -> p.getCodigo().equals(codigo));
    }

    @Override
    public void agregar(Prestamo p) {
       prestamos.add(p);
    }

    @Override
    public void actualizar(Prestamo otro) {
        for(Prestamo p : prestamos){
            if(p.getCodigo().equals(otro.getCodigo())){
                p.setFechaDeDevolucion(otro.getFechaDeDevolucion());
                p.setFechaDePrestamo(otro.getFechaDePrestamo());
                p.setFechaLimiteDePrestamo(otro.getFechaLimiteDePrestamo());
                return;
            }
        }
    }

    @Override
    public boolean existe(String codigo) {
       for(Prestamo p : prestamos)
            if(p.getCodigo().equals(codigo))
                return true;
        return false;
    }

    @Override
    public ArrayList obtenerLista() {
        return prestamos;
    }
}
