/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Excepciones.BibliotecarioExcepcion;
import Modelo.dao.InterfazDao;
import Modelo.dao.PrestamoDao;
import Modelo.dominio.Bibliotecario;
import Modelo.dominio.Prestamo;
import Vista.bibliotecario.AgregarBibliotecarioView;
import Vista.bibliotecario.BuscarBibliotecarioView;
import Vista.bibliotecario.EliminarBibliotecarioView;
import Vista.bibliotecario.ListarBibliotecariosView;
import Vista.bibliotecario.ModificarBibliotecarioView;
import Vista.principal.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorBibliotecario {

    private AgregarBibliotecarioView agregarBibliotecarioView;
    private BuscarBibliotecarioView buscarBibliotecarioView;
    private EliminarBibliotecarioView eliminarBibliotecarioView;
    private ModificarBibliotecarioView modificarBibliotecarioView;
    private ListarBibliotecariosView listarBibliotecarioView;
    private PrincipalView principalView;
    private ResourceBundle bundle;

    private InterfazDao<Bibliotecario> bibliotecarioDao;
    private PrestamoDao prestamoDao;

    private Bibliotecario bibliotecarioAuxiliar;

    public ControladorBibliotecario(InterfazDao bibliotecarioDao, PrestamoDao prestamoDao, ResourceBundle bundle, PrincipalView principalView) {

        agregarBibliotecarioView = new AgregarBibliotecarioView();
        buscarBibliotecarioView = new BuscarBibliotecarioView();
        eliminarBibliotecarioView = new EliminarBibliotecarioView();
        modificarBibliotecarioView = new ModificarBibliotecarioView();
        listarBibliotecarioView = new ListarBibliotecariosView();
        this.principalView = principalView;

        this.bibliotecarioDao = bibliotecarioDao;
        this.prestamoDao = prestamoDao;
        this.bundle = bundle;
    }

    public void configurarEventosAgregarBibliotecario(){
        agregarBibliotecarioView.getBtnAgregarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    agregarBibliotecario();
                    agregarBibliotecarioView.limpiarTextos();
                    agregarBibliotecarioView.mostrarMensaje(bundle.getString("exito.AgregarBibliotecario"));
                }catch(BibliotecarioExcepcion ex){
                    agregarBibliotecarioView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        agregarBibliotecarioView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarBibliotecarioView.limpiarTextos();
                agregarBibliotecarioView.setVisible(false);
            }
        });
    }

    public void configurarEventosBuscarBibliotecario(){
        buscarBibliotecarioView.getBtnBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarBibliotecario();
                }catch(BibliotecarioExcepcion ex){
                    buscarBibliotecarioView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        buscarBibliotecarioView.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarBibliotecarioView.limpiarTextos();
                buscarBibliotecarioView.limpiarTabla();
                buscarBibliotecarioView.setVisible(false);
            }
        });
    }

    public void configurarEventosEliminarBibliotecario(){
        eliminarBibliotecarioView.getBtnBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarBibliotecarioAEliminar();
                }catch(BibliotecarioExcepcion ex){
                    eliminarBibliotecarioView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        eliminarBibliotecarioView.getBtnEliminarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bibliotecarioAuxiliar == null){
                    eliminarBibliotecarioView.mostrarMensaje(bundle.getString("error.BuscarBibliotecarioPrimero"));
                    return;
                }

                try{
                    eliminarBibliotecario();
                    eliminarBibliotecarioView.limpiarTextos();
                    eliminarBibliotecarioView.mostrarMensaje(bundle.getString("exito.EliminarBibliotecario"));
                }catch(BibliotecarioExcepcion ex){
                    eliminarBibliotecarioView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        eliminarBibliotecarioView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bibliotecarioAuxiliar = null;
                eliminarBibliotecarioView.limpiarTextos();
                eliminarBibliotecarioView.setVisible(false);
            }
        });
    }

    public void configurarEventosModificarBibliotecario(){
        modificarBibliotecarioView.getBtnBuscarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarBibliotecarioAModificar();
                }catch(BibliotecarioExcepcion ex){
                    modificarBibliotecarioView.mostrarMensaje(bundle.getString("error.BibliotecarioNoExiste"));
                }
            }
        });

        modificarBibliotecarioView.getBtnModificar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bibliotecarioAuxiliar == null){
                    modificarBibliotecarioView.mostrarMensaje(bundle.getString("error.BuscarBibliotecarioPrimero"));
                    return;
                }

                try{
                    modificarBibliotecario();
                    modificarBibliotecarioView.limpiarTextos();
                    modificarBibliotecarioView.mostrarMensaje(bundle.getString("exito.ModificarBibliotecario"));
                }catch(BibliotecarioExcepcion ex){
                    modificarBibliotecarioView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        modificarBibliotecarioView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                bibliotecarioAuxiliar = null;
                modificarBibliotecarioView.limpiarTextos();
                modificarBibliotecarioView.setVisible(false);
            }
        });
    }

    public void configurarEventosListarBibliotecarios(){
        listarBibliotecarioView.getBtnListarBibliotecarios().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Bibliotecario> bibliotecarios = bibliotecarioDao.obtenerLista();
                ArrayList<Object[]> filas = new ArrayList<>();
                for(Bibliotecario b : bibliotecarios){
                    filas.add(new Object[]{b.getCedula(), b.getNombre(), b.getEmail(), b.getTelefono(), b.getSector(), b.getCargo()});
                }
                listarBibliotecarioView.cargarDatosTabla(filas);
            }
        });

        listarBibliotecarioView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                listarBibliotecarioView.limpiarTabla();
                listarBibliotecarioView.setVisible(false);
            }
        });
    }


    public void activarVentanaAgregarBibliotecario(){
        principalView.abrirVentana(agregarBibliotecarioView);
    }

    public void activarVentanaBuscarBibliotecario(){
        principalView.abrirVentana(buscarBibliotecarioView);
    }

    public void activarVentanaEliminarBibliotecario(){
        principalView.abrirVentana(eliminarBibliotecarioView);
    }

    public void activarVentanaModificarBibliotecario(){
        principalView.abrirVentana(modificarBibliotecarioView);
    }

    public void activarVentanaListarBibliotecario(){
        listarBibliotecarioView.actualizarIdioma(bundle);
        principalView.abrirVentana(listarBibliotecarioView);
    }

    private void agregarBibliotecario() throws BibliotecarioExcepcion{
        String cedula = agregarBibliotecarioView.getTxtCedulaBibliotecarioNuevo().getText();
        String nombres = agregarBibliotecarioView.getTxtNombreBibliotecarioNuevo().getText();
        String numeroTelefonico = agregarBibliotecarioView.getTxtNumeroBibliotecarioNuevo().getText();
        String correoElectronico = agregarBibliotecarioView.getTxtCorreoBibliotecarioNuevo().getText();
        String sector = agregarBibliotecarioView.getTxtSectorBibliotecarioNuevo().getText();

        if(cedula.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.Cedula");
        if(nombres.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.Nombres");
        if(numeroTelefonico.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.NumeroTelefonico");
        if(correoElectronico.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.CorreoElectronico");
        if(sector.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.Sector");
        if(!validarCedula(cedula))
            throw new BibliotecarioExcepcion("error.CedulaInvalida");
        if(!validarNumeroTelefonico(numeroTelefonico))
            throw new BibliotecarioExcepcion("error.NumeroTelefonoInvalido");
        if(bibliotecarioDao.existe(cedula))
            throw new BibliotecarioExcepcion("error.BibliotecarioYaExiste");

        Bibliotecario bibliotecarioNuevo = new Bibliotecario(sector, "Basico", cedula, nombres, correoElectronico, numeroTelefonico);
        bibliotecarioDao.agregar(bibliotecarioNuevo);
    }

    private void buscarBibliotecario() throws BibliotecarioExcepcion{
        String cedula = buscarBibliotecarioView.getTxtCedulaBibliotecarioBuscar().getText();
        if(cedula.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.Cedula");

        Bibliotecario bibliotecario = bibliotecarioDao.buscar(cedula);

        if(bibliotecario == null)
            throw new BibliotecarioExcepcion("error.BibliotecarioNoExiste");

        buscarBibliotecarioView.mostrarInformacion(bibliotecario.getNombre(), bibliotecario.getTelefono(), bibliotecario.getEmail(), bibliotecario.getSector());

        ArrayList<Prestamo> prestamos = prestamoDao.obtenerPorBibliotecario(cedula);
        ArrayList<Object[]> filas = new ArrayList<>();
        for(Prestamo p : prestamos){
            String sancion = p.getSancion() != null ? p.getSancion().getCodigo() : "-";
            filas.add(new Object[]{p.getCodigo(), p.getFechaDePrestamo(), p.getFechaDeDevolucion(), p.getUsuarioCedula(), sancion});
        }
        buscarBibliotecarioView.cargarDatosTabla(filas);
    }

    private void buscarBibliotecarioAEliminar() throws BibliotecarioExcepcion{
        String cedula = eliminarBibliotecarioView.getTxtCedulaBibliotecarioAEliminar().getText();
        if(cedula.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.Cedula");

        bibliotecarioAuxiliar =  bibliotecarioDao.buscar(cedula);

        if(bibliotecarioAuxiliar == null)
            throw new BibliotecarioExcepcion("error.BibliotecarioNoExiste");

        eliminarBibliotecarioView.mostrarInformacion(bibliotecarioAuxiliar.getEmail(), bibliotecarioAuxiliar.getNombre(), bibliotecarioAuxiliar.getTelefono(), bibliotecarioAuxiliar.getSector());
    }

    private void eliminarBibliotecario() throws BibliotecarioExcepcion{
        String cedulaSupervisor = eliminarBibliotecarioView.getTxtCedulaBibliotecario().getText();

        if(cedulaSupervisor.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.CedulaSupervisor");
        if(!bibliotecarioDao.existe(cedulaSupervisor))
            throw new BibliotecarioExcepcion("error.CedulaSupervisorNoExiste");

        bibliotecarioDao.eliminar(bibliotecarioAuxiliar.getCedula());
        bibliotecarioAuxiliar = null;
    }

    private void buscarBibliotecarioAModificar() throws BibliotecarioExcepcion{
        String cedula = modificarBibliotecarioView.getTxtCedulaUsuarioNuevo().getText();
        if(cedula.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.Cedula");

        bibliotecarioAuxiliar = bibliotecarioDao.buscar(cedula);

        if(bibliotecarioAuxiliar == null)
            throw new BibliotecarioExcepcion("error.BibliotecarioNoExiste");

        modificarBibliotecarioView.mostrarInformacionBibliotecario(bibliotecarioAuxiliar.getNombre(), bibliotecarioAuxiliar.getTelefono(), bibliotecarioAuxiliar.getEmail(), bibliotecarioAuxiliar.getSector(), bibliotecarioAuxiliar.getCargo());
    }

    private void modificarBibliotecario() throws BibliotecarioExcepcion{
        String cedulaSupervisor = modificarBibliotecarioView.getTxtCedulaUsuarioNuevo1().getText();

        if(cedulaSupervisor.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.CedulaSupervisor");
        if(!bibliotecarioDao.existe(cedulaSupervisor))
            throw new BibliotecarioExcepcion("error.CedulaSupervisorNoExiste");

        String nuevoNombre = modificarBibliotecarioView.getTxtNombreBibliotecarioModificar().getText();
        String nuevoCorreo = modificarBibliotecarioView.getTxtCorreoBibliotecarioModificar().getText();
        String nuevoTelefono = modificarBibliotecarioView.getTxtNumeroBibliotecarioModificar().getText();
        String nuevoSector = modificarBibliotecarioView.getTxtSectorArea().getText();
        String nuevoCargo = modificarBibliotecarioView.getTxtNuevoCargo().getText();

        if(nuevoNombre.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.Nombres");
        if(nuevoTelefono.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.NumeroTelefonico");
        if(nuevoCorreo.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.CorreoElectronico");
        if(nuevoSector.isBlank())
            throw new BibliotecarioExcepcion("campoVacio.Sector");
        if(nuevoCargo.isBlank())
            throw new BibliotecarioExcepcion(bundle.getString("errorCargo.Vacio"));
        if(!validarNumeroTelefonico(nuevoTelefono))
            throw new BibliotecarioExcepcion("error.NumeroTelefonoInvalido");

        Bibliotecario modificado = new Bibliotecario(nuevoSector, nuevoCargo, bibliotecarioAuxiliar.getCedula(), nuevoNombre, nuevoCorreo, nuevoTelefono);
        bibliotecarioDao.actualizar(modificado);
        modificarBibliotecarioView.limpiarTextos();
        bibliotecarioAuxiliar = null;
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
    
    public void actualisarIdiomaBibliotecario(ResourceBundle bundle){
        this.bundle=bundle;
        agregarBibliotecarioView.actualizarIdioma(bundle);
        buscarBibliotecarioView.actualizarIdioma(bundle);
        eliminarBibliotecarioView.actualizarIdioma(bundle);
        modificarBibliotecarioView.actualizarIdioma(bundle);
        listarBibliotecarioView.actualizarIdioma(bundle);
    }

}
