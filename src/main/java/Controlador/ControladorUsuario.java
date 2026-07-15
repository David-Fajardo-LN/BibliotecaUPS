/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.dao.InterfazDao;
import Modelo.dao.UsuarioArchivosDao;
import Vista.usuario.AgregarUsuarioView;
import Vista.usuario.BuscarUsuarioView;
import Vista.usuario.EliminarUsuarioView;
import Vista.usuario.ListarUsuariosView;
import Vista.usuario.ModificarUsuarioView;
import java.util.ResourceBundle;

public class ControladorUsuario {
    
    private AgregarUsuarioView agregarUsuarioView;
    private BuscarUsuarioView buscarUsuarioView;
    private EliminarUsuarioView eliminarUsuarioView;
    private ModificarUsuarioView modificarUsuarioView;
    private ListarUsuariosView listarUsuarioView;
    private ResourceBundle bundle;
    
    private InterfazDao usuarioDao;

    public ControladorUsuario(InterfazDao usuarioDao, ResourceBundle bundle) {
        
        agregarUsuarioView = new AgregarUsuarioView();
        buscarUsuarioView = new BuscarUsuarioView();
        eliminarUsuarioView = new EliminarUsuarioView();
        modificarUsuarioView = new ModificarUsuarioView();
        listarUsuarioView = new ListarUsuariosView();
        
        this.usuarioDao = usuarioDao;
        this.bundle = bundle;
    }
    
    
    
    
    public void activarVentanaAgregarUsuario(){
        agregarUsuarioView.actualizarIdioma(bundle);
        agregarUsuarioView.setVisible(true);
    }
    
    public void activarVentanaBuscarUsuario(){
        buscarUsuarioView.actualizarIdioma(bundle);
        buscarUsuarioView.setVisible(true);
    }
    
    public void activarVentanaEliminarUsuario(){
        eliminarUsuarioView.actualizarIdioma(bundle);
        eliminarUsuarioView.setVisible(true);
    }
    
    public void activarVentanaModificarUsuario(){
        modificarUsuarioView.actualizarIdioma(bundle);
        modificarUsuarioView.setVisible(true);
    }
    
    public void activarVentanaListarUsuario(){
        listarUsuarioView.actualizarIdioma(bundle);
        listarUsuarioView.setVisible(true);
    }
    
}
