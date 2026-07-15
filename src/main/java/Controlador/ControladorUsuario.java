/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Excepciones.UsuarioExcepcion;
import Modelo.dao.InterfazDao;
import Modelo.dao.UsuarioArchivosDao;
import Modelo.dominio.Usuario;
import Vista.usuario.AgregarUsuarioView;
import Vista.usuario.BuscarUsuarioView;
import Vista.usuario.EliminarUsuarioView;
import Vista.usuario.ListarUsuariosView;
import Vista.usuario.ModificarUsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public void configurarEventosAgregarUsuario(){
        agregarUsuarioView.getBtnAgregarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    agregarUsuario();
                    agregarUsuarioView.mostrarMensaje(bundle.getString("exito.AgregarUsuario"));
                }catch(UsuarioExcepcion ex){
                    agregarUsuarioView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });
    }
    
    public void configurarEventosBuscarUsuario(){
        
    }
    
    public void configurarEventosEliminarUsuario(){
    
    }
    
    public void configurarEventosModificarUsuario(){
        modificarUsuarioView.getBtnBuscarUsuarioAModificar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        modificarUsuarioView.getBtnModificar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        modificarUsuarioView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
    }
    
    public void configurarEventosListarUsuarios(){
        
        listarUsuarioView.getBtnListar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        listarUsuarioView.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        
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
    
    private void agregarUsuario() throws UsuarioExcepcion{
        String cedula = agregarUsuarioView.getTxtCedulaUsuarioNuevo().getText();
        String nombres = agregarUsuarioView.getTxtNombreUsuarioNuevo().getText();
        String numeroTelefonico = agregarUsuarioView.getTxtTelefonoUsuarioNuevo().getText();
        String correoElectronico = agregarUsuarioView.getTxtCorreoUsuarioNuevo().getText();
        
        if(cedula.isBlank())
            throw new UsuarioExcepcion(bundle.getString("campoVacio.Cedula"));
        if(nombres.isBlank())
            throw new UsuarioExcepcion(bundle.getString("campoVacio.Nombres"));
        if(numeroTelefonico.isBlank())
            throw new UsuarioExcepcion(bundle.getString("campoVacio.NumeroTelefonico"));
        if(correoElectronico.isBlank())
            throw new UsuarioExcepcion(bundle.getString("campoVacio.CorreoElectronico"));
        if(!validarCedula(cedula))
            throw new UsuarioExcepcion(bundle.getString("error.CedulaInvalida"));
        if(usuarioDao.existe(cedula))
            throw new UsuarioExcepcion(bundle.getString("error.UsuarioYaExiste"));
        
        Usuario usuarioNuevo = new Usuario(cedula, nombres, correoElectronico, numeroTelefonico);
        usuarioDao.agregar(usuarioNuevo);       
    }
    
    private boolean validarCedula(String cedula){
        if (cedula == null || cedula.length() != 10) {
            return false;
        }

        if (!cedula.matches("\\d+")) {
            return false;
        }

        int provincia = Integer.parseInt(cedula.substring(0, 2));
        if (provincia < 1 || provincia > 24) {
            return false;
        }

        int total = 0;
        int[] coeficientes = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int digitoVerificador = Character.getNumericValue(cedula.charAt(9));

        for (int i = 0; i < 9; i++) {
            int valor = Character.getNumericValue(cedula.charAt(i)) * coeficientes[i];
            if (valor > 9) {
                valor -= 9;
            }
            total += valor;
        }

        int residuo = total % 10;
        int resultadoCalculado = (residuo == 0) ? 0 : (10 - residuo);

        return resultadoCalculado == digitoVerificador;
    }
    
}
