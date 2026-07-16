package Modelo.dao;

import Modelo.dominio.Libro;
import java.util.ArrayList;

public interface LibroDao extends InterfazDao<Libro> {

    ArrayList<Libro> obtenerPorAutor(String identificador);

}
