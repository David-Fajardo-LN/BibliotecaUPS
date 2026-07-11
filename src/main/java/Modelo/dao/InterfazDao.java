/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Modelo.dao;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface InterfazDao<T> {
    
    T buscar(String parametro);
    void eliminar(String parametro);
    void agregar(T dato);
    void actualizar(T dato);
    boolean existe(String parametro);
    ArrayList obtenerLista();
    
}
