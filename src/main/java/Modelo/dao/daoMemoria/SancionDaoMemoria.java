/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao.daoMemoria;

import Modelo.dao.InterfazDao;
import Modelo.dominio.Sancion;
import java.util.ArrayList;

/**
 *
 * @author User
 */
import java.time.LocalDate;
import java.util.ArrayList;

public class SancionDaoMemoria implements InterfazDao<Sancion> {

    private ArrayList<Sancion> sanciones;

    public SancionDaoMemoria() {
        this.sanciones = generarSancionesIniciales();
    }

    @Override
    public Sancion buscar(String codigo) {
        for (Sancion s : sanciones) {
            if (s.getCodigo().equals(codigo)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void eliminar(String codigo) {
        sanciones.removeIf(s -> s.getCodigo().equals(codigo));
    }

    @Override
    public void agregar(Sancion s) {
        sanciones.add(s);
    }

    @Override
    public void actualizar(Sancion otro) {
        for (Sancion s : sanciones) {
            if (s.getCodigo().equals(otro.getCodigo())) {
                s.setDescripción(otro.getDescripción());
                s.setFechaDeSancion(otro.getFechaDeSancion());
                s.setMonto(otro.getMonto());
                return;
            }
        }
    }

    @Override
    public boolean existe(String codigo) {
        for (Sancion s : sanciones) {
            if (s.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Sancion> obtenerLista() {
        return sanciones;
    }

    private ArrayList<Sancion> generarSancionesIniciales() {
        ArrayList<Sancion> lista = new ArrayList<>();
        lista.add(new Sancion("1", LocalDate.of(2026, 3, 6), "Devolución tardía de 2 dias.", 34.00, null));
        lista.add(new Sancion("2", LocalDate.of(2026, 4, 19), "Devolución tardía de 4 días.", 34.00, null));

        return lista;
    }
}
