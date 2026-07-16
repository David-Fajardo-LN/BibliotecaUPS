package Modelo.dao;

import Modelo.dominio.Prestamo;
import java.util.ArrayList;

public interface PrestamoDao extends InterfazDao<Prestamo> {

    ArrayList<Prestamo> obtenerPorUsuario(String cedula);
    ArrayList<Prestamo> obtenerPorBibliotecario(String cedula);

}
