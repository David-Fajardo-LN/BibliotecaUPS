/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao.daoMemoria;

import Modelo.dao.InterfazDao;
import Modelo.dominio.Libro;
import java.util.ArrayList;

/**
 *
 * @author User
 */
import java.time.LocalDate;
import java.util.ArrayList;

public class LibroDaoMemoria implements InterfazDao<Libro> {

    private ArrayList<Libro> libros;

    public LibroDaoMemoria() {
        this.libros = generarLibrosIniciales();
    }
    
    

    @Override
    public Libro buscar(String isbn) {
        for (Libro l : libros) {
            if (l.getISBN().equals(isbn)) {
                return l;
            }
        }
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
        for (Libro l : libros) {
            if (l.getISBN().equals(otro.getISBN())) {
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
        for (Libro l : libros) {
            if (l.getISBN().equals(isbn)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Libro> obtenerLista() {
        return libros;
    }

    private ArrayList<Libro> generarLibrosIniciales() {
        ArrayList<Libro> libros = new ArrayList<>();

        // El constructor ahora usa el identificador (String) en lugar del objeto Autor
        libros.add(new Libro("1", "Harry Potter y la piedra filosofal", LocalDate.of(1997, 6, 26), 5, "1", "Fantasía"));
        libros.add(new Libro("2", "El Señor de los Anillos", LocalDate.of(1954, 7, 29), 4, "2", "Fantasía épica"));
        libros.add(new Libro("3", "Don Quijote de la Mancha", LocalDate.of(1605, 1, 16), 3, "3", "Novela"));
        libros.add(new Libro("4", "1984", LocalDate.of(1949, 6, 8), 6, "4", "Ciencia ficción"));
        libros.add(new Libro("5", "Matar a un ruiseñor", LocalDate.of(1960, 7, 11), 4, "5", "Drama"));
        libros.add(new Libro("6", "Cien años de soledad", LocalDate.of(1967, 5, 30), 5, "6", "Realismo mágico"));
        libros.add(new Libro("7", "El principito", LocalDate.of(1943, 4, 6), 7, "7", "Fábula"));
        libros.add(new Libro("8", "Crimen y castigo", LocalDate.of(1866, 1, 1), 3, "8", "Novela psicológica"));
        libros.add(new Libro("9", "Orgullo y prejuicio", LocalDate.of(1813, 1, 28), 5, "9", "Romance"));
        libros.add(new Libro("10", "Viaje al centro de la Tierra", LocalDate.of(1864, 11, 25), 4, "10", "Aventura"));

        return libros;
    }
}
