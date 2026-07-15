/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao.daoMemoria;

import Modelo.dao.InterfazDao;
import Modelo.dominio.Prestamo;
import java.util.ArrayList;

/**
 *
 * @author User
 */
import java.time.LocalDate;
import java.util.ArrayList;

public class PrestamoDaoMemoria implements InterfazDao<Prestamo> {

    private ArrayList<Prestamo> prestamos;

    public PrestamoDaoMemoria() {
        this.prestamos = generarPrestamosIniciales();
    }

    @Override
    public Prestamo buscar(String codigo) {
        for (Prestamo p : prestamos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
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
        for (Prestamo p : prestamos) {
            if (p.getCodigo().equals(otro.getCodigo())) {
                p.setFechaDeDevolucion(otro.getFechaDeDevolucion());
                p.setFechaDePrestamo(otro.getFechaDePrestamo());
                p.setFechaLimiteDePrestamo(otro.getFechaLimiteDePrestamo());
                return;
            }
        }
    }

    @Override
    public boolean existe(String codigo) {
        for (Prestamo p : prestamos) {
            if (p.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Prestamo> obtenerLista() {
        return prestamos;
    }

    public void registrarDevolucion(String codigo) {
        Prestamo p = buscar(codigo);
        if (p != null) {
            p.cambiarEstadoFalse();
        }
    }

    private ArrayList<Prestamo> generarPrestamosIniciales() {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        prestamos.add(new Prestamo("1", LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 24), LocalDate.of(2026, 1, 22), null, null, null));
        prestamos.add(new Prestamo("2", LocalDate.of(2026, 2, 3), LocalDate.of(2026, 2, 17), LocalDate.of(2026, 2, 16), null, null, null));
        prestamos.add(new Prestamo("3", LocalDate.of(2026, 2, 18), LocalDate.of(2026, 3, 4), LocalDate.of(2026, 3, 6), null, null, null));
        prestamos.add(new Prestamo("4", LocalDate.of(2026, 3, 9), LocalDate.of(2026, 3, 23), LocalDate.of(2026, 3, 21), null, null, null));
        
        return prestamos;
    }
}
