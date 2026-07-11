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
        this.prestamos = generarPrestamosIniciales( bibliotecarios, usuarios, libros);
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
    
    
    
    
    
    private ArrayList<Prestamo> generarPrestamosIniciales(ArrayList<Bibliotecario> bibliotecarios, ArrayList<Usuario> usuarios, ArrayList<Libro> libros){

        ArrayList<Prestamo> prestamos = new ArrayList<>();

        Prestamo p1 = new Prestamo("1", LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 24), LocalDate.of(2026, 1, 22), bibliotecarios.get(0), usuarios.get(0), libros.get(0));
        usuarios.get(0).agregarPrestamo(p1);
        prestamos.add(p1);
        bibliotecarios.get(0).sePrestamo(p1);


        Prestamo p2 = new Prestamo("2", LocalDate.of(2026, 2, 3), LocalDate.of(2026, 2, 17), LocalDate.of(2026, 2, 16), bibliotecarios.get(2), usuarios.get(4), libros.get(3));
        prestamos.add(p2);
        bibliotecarios.get(2).sePrestamo(p2);
        usuarios.get(0).agregarPrestamo(p2);


        Prestamo p3 = new Prestamo("3", LocalDate.of(2026, 2, 18), LocalDate.of(2026, 3, 4), LocalDate.of(2026, 3, 6), bibliotecarios.get(1), usuarios.get(7), libros.get(6));
        prestamos.add(p3);
        bibliotecarios.get(1).sePrestamo(p3);
        usuarios.get(1).agregarPrestamo(p3);


        Prestamo p4 = new Prestamo("4", LocalDate.of(2026, 3, 9), LocalDate.of(2026, 3, 23), LocalDate.of(2026, 3, 21), bibliotecarios.get(4), usuarios.get(10), libros.get(9));
        prestamos.add(p4);
        bibliotecarios.get(4).sePrestamo(p4);
        usuarios.get(2).agregarPrestamo(p4);


        Prestamo p5 = new Prestamo("5", LocalDate.now().minusDays(6), bibliotecarios.get(0), usuarios.get(13), libros.get(5), null);
        prestamos.add(p5);
        bibliotecarios.get(0).sePrestamo(p5);
        usuarios.get(2).agregarPrestamo(p4);

        return prestamos;
    }
}
