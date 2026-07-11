/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao;

import Modelo.dominio.Autor;
import Modelo.dominio.Libro;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class LibroDao implements InterfazDao<Libro>{
    
    private ArrayList<Libro> libros;

    public LibroDao(ArrayList<Autor> autores) {
        this.libros= generarLibrosIniciales(autores);
    }

    @Override
    public Libro buscar(String isbn) {
        for(Libro l : libros)
            if(l.getISBN().equals(isbn))
                return l;
        return null;
    }

    @Override
    public void eliminar(String isbn) {
        libros.removeIf(l -> l.getISBN().equals(isbn));
    }

    @Override
    public void agregar(Libro l) {
       libros.add(l);
    }

    @Override
    public void actualizar(Libro otro) {
        for(Libro l : libros){
            if(l.getISBN().equals(otro.getISBN())){
                l.setCantidadDisponible(otro.getCantidadDisponible());
                l.setCantidadTotal(otro.getCantidadTotal());
                l.setFechaDePublicacion(otro.getFechaDePublicacion());
                l.setNombre(otro.getNombre());
                return;
            }
        }
    }

    @Override
    public boolean existe(String isbn) {
        for(Libro l : libros)
            if(l.getISBN().equals(isbn))
                return true;
        return false;
    }

    @Override
    public ArrayList obtenerLista() {
        return libros;
    }
    
    
    private ArrayList<Libro> generarLibrosIniciales(ArrayList<Autor> autores){
        ArrayList<Libro> libros = new ArrayList<>();

        libros.add(new Libro("1", "Harry Potter y la piedra filosofal", LocalDate.of(1997, 6, 26), 5, autores.get(0), "Fantasía"));
        autores.get(0).agregarLibro(libros.get(libros.size() - 1));

        libros.add(new Libro("2", "El Señor de los Anillos", LocalDate.of(1954, 7, 29), 4, autores.get(1), "Fantasía épica"));
        autores.get(1).agregarLibro(libros.get(libros.size() - 1));

        libros.add(new Libro("3", "Don Quijote de la Mancha", LocalDate.of(1605, 1, 16), 3, autores.get(2), "Novela"));
        autores.get(2).agregarLibro(libros.get(libros.size() - 1));

        libros.add(new Libro("4", "1984", LocalDate.of(1949, 6, 8), 6, autores.get(3), "Ciencia ficción"));
        autores.get(3).agregarLibro(libros.get(libros.size() - 1));

        libros.add(new Libro("5", "Matar a un ruiseñor", LocalDate.of(1960, 7, 11), 4, autores.get(4), "Drama"));
        autores.get(4).agregarLibro(libros.get(libros.size() - 1));

        libros.add(new Libro("6", "Cien años de soledad", LocalDate.of(1967, 5, 30), 5, autores.get(5), "Realismo mágico"));
        autores.get(5).agregarLibro(libros.get(libros.size() - 1));

        libros.add(new Libro("7", "El principito", LocalDate.of(1943, 4, 6), 7, autores.get(6), "Fábula"));
        autores.get(6).agregarLibro(libros.get(libros.size() - 1));

        libros.add(new Libro("8", "Crimen y castigo", LocalDate.of(1866, 1, 1), 3, autores.get(7), "Novela psicológica"));
        autores.get(7).agregarLibro(libros.get(libros.size() - 1));

        libros.add(new Libro("9", "Orgullo y prejuicio", LocalDate.of(1813, 1, 28), 5, autores.get(8), "Romance"));
        autores.get(8).agregarLibro(libros.get(libros.size() - 1));

        libros.add(new Libro("10", "Viaje al centro de la Tierra", LocalDate.of(1864, 11, 25), 4, autores.get(9), "Aventura"));
        autores.get(9).agregarLibro(libros.get(libros.size() - 1));

        return libros;
    }
    
}
