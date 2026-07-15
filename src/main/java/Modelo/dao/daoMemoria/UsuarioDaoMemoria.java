/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao.daoMemoria;

import Modelo.dao.InterfazDao;
import Modelo.dominio.Usuario;
import java.util.ArrayList;

/**
 *
 * @author User
 */
import java.util.ArrayList;

public class UsuarioDaoMemoria implements InterfazDao<Usuario> {

    private ArrayList<Usuario> usuarios;

    public UsuarioDaoMemoria() {
        this.usuarios = generarUsuariosIniciales();
    }

    @Override
    public void agregar(Usuario u) {
        usuarios.add(u);
    }

    @Override
    public Usuario buscar(String cedula) {
        for (Usuario u : usuarios) {
            if (u.getCedula().equals(cedula)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Usuario otro) {
        for (Usuario u : usuarios) {
            if (u.getCedula().equals(otro.getCedula())) {
                u.setEmail(otro.getEmail());
                u.setNombre(otro.getNombre());
                u.setTelefono(otro.getTelefono());
                return;
            }
        }
    }

    @Override
    public void eliminar(String cedula) {
        usuarios.removeIf(u -> u.getCedula().equals(cedula));
    }

    @Override
    public boolean existe(String cedula) {
        for (Usuario u : usuarios) {
            if (u.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Usuario> obtenerLista() {
        return usuarios;
    }

    private ArrayList<Usuario> generarUsuariosIniciales() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        usuarios.add(new Usuario("1", "Juan Pérez", "juan.perez@gmail.com", "0991112233"));
        usuarios.add(new Usuario("2", "María González", "maria.gonzalez@gmail.com", "0982223344"));
        usuarios.add(new Usuario("3", "Carlos Rodríguez", "carlos.rodriguez@gmail.com", "0973334455"));
        usuarios.add(new Usuario("4", "Ana Torres", "ana.torres@gmail.com", "0964445566"));
        usuarios.add(new Usuario("5", "Luis Herrera", "luis.herrera@gmail.com", "0955556677"));
        usuarios.add(new Usuario("6", "Sofía Castillo", "sofia.castillo@gmail.com", "0946667788"));
        usuarios.add(new Usuario("7", "Pedro Morales", "pedro.morales@gmail.com", "0937778899"));
        usuarios.add(new Usuario("8", "Valentina Romero", "valentina.romero@gmail.com", "0928889900"));
        usuarios.add(new Usuario("9", "Diego Sánchez", "diego.sanchez@gmail.com", "0911234567"));
        usuarios.add(new Usuario("10", "Camila Flores", "camila.flores@gmail.com", "0992345678"));
        usuarios.add(new Usuario("11", "Andrés Vega", "andres.vega@gmail.com", "0983456789"));
        usuarios.add(new Usuario("12", "Daniela Cruz", "daniela.cruz@gmail.com", "0974567890"));
        usuarios.add(new Usuario("13", "José Mendoza", "jose.mendoza@gmail.com", "0965678901"));
        usuarios.add(new Usuario("14", "Gabriela León", "gabriela.leon@gmail.com", "0956789012"));
        usuarios.add(new Usuario("15", "Fernando Ortiz", "fernando.ortiz@gmail.com", "0947890123"));

        return usuarios;
    }
}
