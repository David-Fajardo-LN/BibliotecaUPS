/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Excepciones.UsuarioExcepcion;
import Modelo.dao.InterfazDao;
import Modelo.dominio.Usuario;
import Vista.principal.PrincipalView;
import Vista.usuario.AgregarUsuarioView;
import Vista.usuario.BuscarUsuarioView;
import Vista.usuario.EliminarUsuarioView;
import Vista.usuario.ListarUsuariosView;
import Vista.usuario.ModificarUsuarioView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorUsuario {
    
    private AgregarUsuarioView agregarUsuarioView;
    private BuscarUsuarioView buscarUsuarioView;
    private EliminarUsuarioView eliminarUsuarioView;
    private ModificarUsuarioView modificarUsuarioView;
    private ListarUsuariosView listarUsuarioView;
    private PrincipalView principalView;
    private ResourceBundle bundle;
    
    private InterfazDao usuarioDao;
    private InterfazDao bibliotecarioDao;
    
    private Usuario usuarioAuxiliar;

    public ControladorUsuario(InterfazDao usuarioDao,InterfazDao bibliotecarioDao, ResourceBundle bundle, PrincipalView principalView) {
        
        agregarUsuarioView = new AgregarUsuarioView();
        buscarUsuarioView = new BuscarUsuarioView();
        eliminarUsuarioView = new EliminarUsuarioView();
        modificarUsuarioView = new ModificarUsuarioView();
        listarUsuarioView = new ListarUsuariosView();
        this.principalView = principalView;
        this.usuarioDao = usuarioDao;
        this.bibliotecarioDao = bibliotecarioDao;
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
        
        agregarUsuarioView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarUsuarioView.limpiarTextos();
                agregarUsuarioView.setVisible(false);
            }
        });
    }
    
    public void configurarEventosBuscarUsuario(){
        buscarUsuarioView.getBtnBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarUsuario();
                }catch(UsuarioExcepcion ex){
                    buscarUsuarioView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });
        
        buscarUsuarioView.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarUsuarioView.limpiarTextos();
                buscarUsuarioView.setVisible(false);
            }
        });
    }
    
    public void configurarEventosEliminarUsuario(){
        eliminarUsuarioView.getBtnBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarUsuarioAEliminar();
                }catch(UsuarioExcepcion ex){
                    eliminarUsuarioView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });
        
        eliminarUsuarioView.getBtnEliminar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usuarioAuxiliar == null){
                    eliminarUsuarioView.mostrarMensaje(bundle.getString("error.BuscarUsuarioPrimero"));
                    return;
                }
                
                try{
                    eliminarUsuario();
                    eliminarUsuarioView.mostrarMensaje(bundle.getString("exito.EliminarUsuario"));
                }catch(UsuarioExcepcion ex){
                    eliminarUsuarioView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });
        
        eliminarUsuarioView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioAuxiliar = null;
                eliminarUsuarioView.limpiarTextos();
                eliminarUsuarioView.setVisible(false);
            }
        });
    }
    
    public void configurarEventosModificarUsuario(){
        modificarUsuarioView.getBtnBuscarUsuarioAModificar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               try{
                   buscarUsuarioAModificar();
               }catch(UsuarioExcepcion ex){
                   modificarUsuarioView.mostrarMensaje(bundle.getString("error.UsuarioNoExiste"));
               }
            }
        });
        
        modificarUsuarioView.getBtnModificar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usuarioAuxiliar == null){
                    modificarUsuarioView.mostrarMensaje(bundle.getString("error.BuscarUsuarioPrimero"));
                    return;
                }
                
                try{
                    modificarusuario();
                }catch(UsuarioExcepcion ex){
                    modificarUsuarioView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
                
            }
        });
        
        modificarUsuarioView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioAuxiliar = null;
                modificarUsuarioView.limpiarTextos();
                modificarUsuarioView.setVisible(false);
            }
        });
    }
    
    public void configurarEventosListarUsuarios(){
        
        listarUsuarioView.getBtnListar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Usuario> usuarios = usuarioDao.obtenerLista();
                ArrayList<Object[]> filas = new ArrayList<>();
                for(Usuario u : usuarios){
                    filas.add(new Object[]{u.getCedula(), u.getNombre(), u.getEmail(), u.getTelefono()});
                }
                listarUsuarioView.cargarDatosTabla(filas);
            }
        });
        
        listarUsuarioView.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuarioView.setVisible(false);
            }
        });
        
    }
    
    
    public void activarVentanaAgregarUsuario(){
        agregarUsuarioView.actualizarIdioma(bundle);
        principalView.abrirVentana(agregarUsuarioView);
    }
    
    public void activarVentanaBuscarUsuario(){
        buscarUsuarioView.actualizarIdioma(bundle);
        principalView.abrirVentana(buscarUsuarioView);
    }
    
    public void activarVentanaEliminarUsuario(){
        eliminarUsuarioView.actualizarIdioma(bundle);
        principalView.abrirVentana(eliminarUsuarioView);
    }
    
    public void activarVentanaModificarUsuario(){
        modificarUsuarioView.actualizarIdioma(bundle);
        principalView.abrirVentana(modificarUsuarioView);
    }
    
    public void activarVentanaListarUsuario(){
        listarUsuarioView.actualizarIdioma(bundle);
        principalView.abrirVentana(listarUsuarioView);
    }
    
    private void agregarUsuario() throws UsuarioExcepcion{
        String cedula = agregarUsuarioView.getTxtCedulaUsuarioNuevo().getText();
        String nombres = agregarUsuarioView.getTxtNombreUsuarioNuevo().getText();
        String numeroTelefonico = agregarUsuarioView.getTxtTelefonoUsuarioNuevo().getText();
        String correoElectronico = agregarUsuarioView.getTxtCorreoUsuarioNuevo().getText();
        
        if(cedula.isBlank())
            throw new UsuarioExcepcion("campoVacio.Cedula");
        if(nombres.isBlank())
            throw new UsuarioExcepcion("campoVacio.Nombres");
        if(numeroTelefonico.isBlank())
            throw new UsuarioExcepcion("campoVacio.NumeroTelefonico");
        if(correoElectronico.isBlank())
            throw new UsuarioExcepcion("campoVacio.CorreoElectronico");
        if(!validarCedula(cedula))
            throw new UsuarioExcepcion("error.CedulaInvalida");
        if(!validarNumeroTelefonico(numeroTelefonico))
            throw new UsuarioExcepcion("error.NumeroTelefonoInvalido");
        if(usuarioDao.existe(cedula))
            throw new UsuarioExcepcion("error.UsuarioYaExiste");
        
        Usuario usuarioNuevo = new Usuario(cedula, nombres, correoElectronico, numeroTelefonico);
        usuarioDao.agregar(usuarioNuevo);       
    }
    
    private void buscarUsuario() throws UsuarioExcepcion{
        String cedula = buscarUsuarioView.getTxtCedulaUsuarioBuscar().getText();
        if(cedula.isBlank())
            throw new UsuarioExcepcion("campoVacio.Cedula");
        
        Usuario usuario = (Usuario) usuarioDao.buscar(cedula);
        
        if(usuario == null)
            throw new UsuarioExcepcion("error.UsuarioNoExiste");
        
        buscarUsuarioView.mostrarInformacion(usuario.getNombre(), usuario.getTelefono(), usuario.getEmail());
    }
    
    private void buscarUsuarioAEliminar() throws UsuarioExcepcion{
        String cedula = eliminarUsuarioView.getTxtCedulaUsuarioAEliminar().getText();
        if(cedula.isBlank())
            throw new UsuarioExcepcion("campoVacio.Cedula");
        
        usuarioAuxiliar = (Usuario) usuarioDao.buscar(cedula);
        
        if(usuarioAuxiliar == null)
            throw new UsuarioExcepcion("error.UsuarioNoExiste");
        
        eliminarUsuarioView.mostrarInformacion(usuarioAuxiliar.getEmail(), usuarioAuxiliar.getNombre(), usuarioAuxiliar.getTelefono());
    }
    
    private void eliminarUsuario() throws UsuarioExcepcion{
        String cedulaSupervisor = eliminarUsuarioView.getTxtCedulaSupervisor().getText();
        
        if(cedulaSupervisor.isBlank())
            throw new UsuarioExcepcion("campoVacio.CedulaSupervisor");
        if(!bibliotecarioDao.existe(cedulaSupervisor))
            throw new UsuarioExcepcion("error.CedulaSupervisorNoExiste");
        
        usuarioDao.eliminar(usuarioAuxiliar.getCedula());
        usuarioAuxiliar = null;
    }
    
    private void buscarUsuarioAModificar() throws UsuarioExcepcion{
        String cedula = modificarUsuarioView.getTxtCedulaUsuarioModificar().getText();
        if(cedula.isBlank())
            throw new UsuarioExcepcion("campoVacio.Cedula");
        
        usuarioAuxiliar = (Usuario) usuarioDao.buscar(cedula);
        
        if(usuarioAuxiliar == null)
            throw new UsuarioExcepcion("error.UsuarioNoExiste");
        
        modificarUsuarioView.getTxtCedulaUsuarioModificar().setText(usuarioAuxiliar.getCedula());
        modificarUsuarioView.getTxtCorreoUsuarioModificar().setText(usuarioAuxiliar.getEmail());
        modificarUsuarioView.getTxtNombreUsuarioModificar().setText(usuarioAuxiliar.getNombre());
        modificarUsuarioView.getTxtNumeroUsuarioModificar().setText(usuarioAuxiliar.getNombre());
    }
    
    private void modificarusuario() throws UsuarioExcepcion{
        String cedulaSupervisor = modificarUsuarioView.getTxtCedulaSupervisor().getText();
        
        if(cedulaSupervisor.isBlank())
            throw new UsuarioExcepcion("campoVacio.CedulaSupervisor");
        if(!bibliotecarioDao.existe(cedulaSupervisor))
            throw new UsuarioExcepcion("error.CedulaSupervisorNoExiste");
        
        String nuevoNombre = modificarUsuarioView.getTxtNombreUsuarioModificar().getText();
        String nuevoCorreo = modificarUsuarioView.getTxtCorreoUsuarioModificar().getText();
        String nuevoTelefono = modificarUsuarioView.getTxtNumeroUsuarioModificar().getText();
        
        if(nuevoNombre.isBlank())
            throw new UsuarioExcepcion("campoVacio.Nombres");
        if(nuevoTelefono.isBlank())
            throw new UsuarioExcepcion("campoVacio.NumeroTelefonico");
        if(nuevoCorreo.isBlank())
            throw new UsuarioExcepcion("campoVacio.CorreoElectronico");
        if(!validarNumeroTelefonico(nuevoTelefono))
            throw new UsuarioExcepcion("error.NumeroTelefonoInvalido");
        
        Usuario modificado = new Usuario(usuarioAuxiliar.getCedula(), nuevoNombre, nuevoCorreo, nuevoTelefono);
        usuarioDao.actualizar(modificado);
        usuarioAuxiliar = null;
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
    
    private boolean validarNumeroTelefonico(String telefono){
        if(telefono.length()>10)
            return false;
        if(telefono.length()<10)
            return false;
        if(!telefono.substring(0,2).equals("09"))
            return false;
        return true;
    }
    
}
