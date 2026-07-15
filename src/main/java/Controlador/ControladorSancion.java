/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Excepciones.SancionExcepcion;
import Modelo.dao.InterfazDao;
import Modelo.dominio.Prestamo;
import Modelo.dominio.Sancion;
import Vista.principal.PrincipalView;
import Vista.sancion.BuscarSancionView;
import Vista.sancion.ListarSancionesView;
import Vista.sancion.PagarMultaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorSancion {

    private BuscarSancionView buscarSancionView;
    private PagarMultaView pagarMultaView;
    private ListarSancionesView listarSancionView;
    private PrincipalView principalView;
    private ResourceBundle bundle;

    private InterfazDao sancionDao;
    private InterfazDao prestamoDao;

    private Sancion sancionAuxiliar;

    public ControladorSancion(InterfazDao sancionDao, InterfazDao prestamoDao, ResourceBundle bundle, PrincipalView principalView) {

        buscarSancionView = new BuscarSancionView();
        pagarMultaView = new PagarMultaView();
        listarSancionView = new ListarSancionesView();
        this.principalView= principalView;
        this.sancionDao = sancionDao;
        this.prestamoDao = prestamoDao;
        this.bundle = bundle;
    }

    public void configurarEventosBuscarSancion(){
        buscarSancionView.getBtnBuscarSancion().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarSancion();
                }catch(SancionExcepcion ex){
                    buscarSancionView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        buscarSancionView.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarSancionView.limpiarTextos();
                buscarSancionView.setVisible(false);
            }
        });
    }

    public void configurarEventosPagarMulta(){
        pagarMultaView.getBtnBuscarSancion().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarSancionAPagar();
                }catch(SancionExcepcion ex){
                    pagarMultaView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        pagarMultaView.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sancionAuxiliar == null){
                    pagarMultaView.mostrarMensaje(bundle.getString("error.BuscarSancionPrimero"));
                    return;
                }

                try{
                    pagarMulta();
                    pagarMultaView.mostrarMensaje(bundle.getString("exito.PagarMulta"));
                    pagarMultaView.limpiarTextos();
                }catch(SancionExcepcion ex){
                    pagarMultaView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        pagarMultaView.getBtnRegresar1().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sancionAuxiliar = null;
                pagarMultaView.limpiarTextos();
                pagarMultaView.setVisible(false);
            }
        });
    }

    public void configurarEventosListarSanciones(){
        listarSancionView.getBtnListarSanciones().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Sancion> sanciones = sancionDao.obtenerLista();
                ArrayList<Object[]> filas = new ArrayList<>();
                for(Sancion s : sanciones){
                    String estado = s.getEstado() ? bundle.getString("estado.Pendiente") : bundle.getString("estado.Pagada");
                    filas.add(new Object[]{s.getCodigo(), s.getFechaDeSancion(), s.getDescripción(), s.getMonto(), estado, s.getPrestamo()});
                }
                listarSancionView.cargarDatosTabla(filas);
            }
        });

        listarSancionView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                listarSancionView.setVisible(false);
            }
        });
    }


    public void activarVentanaBuscarSancion(){
        buscarSancionView.actualizarIdioma(bundle);
        principalView.abrirVentana(buscarSancionView);
    }

    public void activarVentanaPagarMulta(){
        pagarMultaView.actualizarIdioma(bundle);
        principalView.abrirVentana(pagarMultaView);
    }

    public void activarVentanaListarSancion(){
        listarSancionView.actualizarIdioma(bundle);
        principalView.abrirVentana(listarSancionView);
    }

    private void buscarSancion() throws SancionExcepcion{
        String codigo = buscarSancionView.getTxtCedulaBibliotecarioBuscar().getText();
        if(codigo.isBlank())
            throw new SancionExcepcion("campoVacio.CodigoSancion");

        Sancion sancion = (Sancion) sancionDao.buscar(codigo);
        if(sancion == null)
            throw new SancionExcepcion("error.SancionNoExiste");

        Prestamo prestamo = (Prestamo) prestamoDao.buscar(sancion.getPrestamo());
        String usuario = prestamo != null ? prestamo.getUsuario().getNombre() : "";
        String estado = sancion.getEstado() ? bundle.getString("estado.Pendiente") : bundle.getString("estado.Pagada");

        buscarSancionView.mostrarInformacion(sancion.getFechaDeSancion().toString(), sancion.getDescripción(), String.valueOf(sancion.getMonto()), sancion.getPrestamo(), estado, usuario);
    }

    private void buscarSancionAPagar() throws SancionExcepcion{
        String codigo = pagarMultaView.getTxtCedulaBibliotecarioBuscar().getText();
        if(codigo.isBlank())
            throw new SancionExcepcion("campoVacio.CodigoSancion");

        sancionAuxiliar = (Sancion) sancionDao.buscar(codigo);
        if(sancionAuxiliar == null)
            throw new SancionExcepcion("error.SancionNoExiste");
        if(!sancionAuxiliar.getEstado())
            throw new SancionExcepcion("error.SancionYaPagada");

        String estado = bundle.getString("estado.Pendiente");
        pagarMultaView.mostrarInformacion(sancionAuxiliar.getFechaDeSancion().toString(), sancionAuxiliar.getDescripción(), String.valueOf(sancionAuxiliar.getMonto()), sancionAuxiliar.getPrestamo(), estado, String.valueOf(sancionAuxiliar.getMonto()));
    }

    private void pagarMulta() throws SancionExcepcion{
        if(!sancionAuxiliar.getEstado())
            throw new SancionExcepcion("error.SancionYaPagada");

        sancionAuxiliar.setEstado(false);
        sancionDao.actualizar(sancionAuxiliar);
        sancionAuxiliar = null;
    }

}
