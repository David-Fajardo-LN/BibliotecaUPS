/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Excepciones.PrestamoExcepcion;
import Modelo.dao.InterfazDao;
import Modelo.dominio.Bibliotecario;
import Modelo.dominio.Libro;
import Modelo.dominio.Prestamo;
import Modelo.dominio.Sancion;
import Modelo.dominio.Usuario;
import Vista.prestamo.BuscarPrestamoView;
import Vista.prestamo.ListarPrestamosView;
import Vista.prestamo.RegistrarDevolucionView;
import Vista.prestamo.RegistrarPrestamoView;
import Vista.principal.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorPrestamo {

    private BuscarPrestamoView buscarPrestamoView;
    private RegistrarPrestamoView registrarPrestamoView;
    private RegistrarDevolucionView registrarDevolucionView;
    private ListarPrestamosView listarPrestamoView;
    private PrincipalView principalView;
    private ResourceBundle bundle;

    private InterfazDao<Prestamo> prestamoDao;
    private InterfazDao<Libro> libroDao;
    private InterfazDao<Usuario> usuarioDao;
    private InterfazDao<Bibliotecario> bibliotecarioDao;
    private InterfazDao<Sancion> sancionDao;

    private Prestamo prestamoAuxiliar;

    private static final double MONTO_POR_DIA_DE_ATRASO = 1.0;

    public ControladorPrestamo(InterfazDao prestamoDao, InterfazDao libroDao, InterfazDao usuarioDao, InterfazDao bibliotecarioDao, InterfazDao sancionDao, ResourceBundle bundle, PrincipalView principalView) {

        buscarPrestamoView = new BuscarPrestamoView();
        registrarPrestamoView = new RegistrarPrestamoView();
        registrarDevolucionView = new RegistrarDevolucionView();
        listarPrestamoView = new ListarPrestamosView();
        this.principalView = principalView;
        this.prestamoDao = prestamoDao;
        this.libroDao = libroDao;
        this.usuarioDao = usuarioDao;
        this.bibliotecarioDao = bibliotecarioDao;
        this.sancionDao = sancionDao;
        this.bundle = bundle;
    }

    public void configurarEventosRegistrarPrestamo(){
        registrarPrestamoView.getJButton1().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    verificarInformacionPrestamo();
                }catch(PrestamoExcepcion ex){
                    registrarPrestamoView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        registrarPrestamoView.getBtnRegistrarPrestamo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    registrarPrestamo();
                    registrarPrestamoView.mostrarMensaje(bundle.getString("exito.RegistrarPrestamo"));
                    registrarPrestamoView.limpiarTextos();
                }catch(PrestamoExcepcion ex){
                    registrarPrestamoView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        registrarPrestamoView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPrestamoView.limpiarTextos();
                registrarPrestamoView.setVisible(false);
            }
        });
    }

    public void configurarEventosBuscarPrestamo(){
        buscarPrestamoView.getBtnBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarPrestamo();
                }catch(PrestamoExcepcion ex){
                    buscarPrestamoView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        buscarPrestamoView.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarPrestamoView.limpiarTextos();
                buscarPrestamoView.setVisible(false);
            }
        });
    }

    public void configurarEventosRegistrarDevolucion(){
        registrarDevolucionView.getBtnBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarPrestamoADevolver();
                }catch(PrestamoExcepcion ex){
                    registrarDevolucionView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        registrarDevolucionView.getBtnRegistrarDevolucion().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(prestamoAuxiliar == null){
                    registrarDevolucionView.mostrarMensaje(bundle.getString("error.BuscarPrestamoPrimero"));
                    return;
                }

                try{
                    registrarDevolucion();
                    registrarDevolucionView.limpiarTextos();
                    registrarDevolucionView.mostrarMensaje(bundle.getString("exito.RegistrarDevolucion"));
                }catch(PrestamoExcepcion ex){
                    registrarDevolucionView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        registrarDevolucionView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                prestamoAuxiliar = null;
                registrarDevolucionView.limpiarTextos();
                registrarDevolucionView.setVisible(false);
            }
        });
    }

    public void configurarEventosListarPrestamos(){
        listarPrestamoView.getBtnListarPrestamos().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Prestamo> prestamos = prestamoDao.obtenerLista();
                ArrayList<Object[]> filas = new ArrayList<>();
                for(Prestamo p : prestamos){
                    filas.add(new Object[]{p.getCodigo(), p.getFechaDePrestamo(), p.getFechaLimiteDePrestamo(), p.getFechaDeDevolucion(), p.getUsuarioCedula(), p.getLibroISBN()});
                }
                listarPrestamoView.cargarDatosTabla(filas);
            }
        });

        listarPrestamoView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPrestamoView.limpiarTabla();
                listarPrestamoView.setVisible(false);
            }
        });
    }


    public void activarVentanaRegistrarPrestamo(){
        principalView.abrirVentana(registrarPrestamoView);
    }

    public void activarVentanaBuscarPrestamo(){
        principalView.abrirVentana(buscarPrestamoView);
    }

    public void activarVentanaRegistrarDevolucion(){
        principalView.abrirVentana(registrarDevolucionView);
    }

    public void activarVentanaListarPrestamo(){
        listarPrestamoView.actualizarIdioma(bundle);
        principalView.abrirVentana(listarPrestamoView);
    }

    private void verificarInformacionPrestamo() throws PrestamoExcepcion{
        String isbn = registrarPrestamoView.getTxtISBNDeLibroAPrestar().getText();
        String cedulaBibliotecario = registrarPrestamoView.getTxtCedulaDeBibliotecario().getText();
        String cedulaUsuario = registrarPrestamoView.getTxtCedulaDeUsuario().getText();

        if(isbn.isBlank())
            throw new PrestamoExcepcion("campoVacio.ISBNLibro");
        if(cedulaBibliotecario.isBlank())
            throw new PrestamoExcepcion("campoVacio.CedulaBibliotecario");
        if(cedulaUsuario.isBlank())
            throw new PrestamoExcepcion("campoVacio.CedulaUsuario");

        Libro libro = libroDao.buscar(isbn);
        if(libro == null)
            throw new PrestamoExcepcion("error.LibroNoExiste");

        Bibliotecario bibliotecario = (Bibliotecario) bibliotecarioDao.buscar(cedulaBibliotecario);
        if(bibliotecario == null)
            throw new PrestamoExcepcion("error.BibliotecarioNoExiste");

        Usuario usuario = (Usuario) usuarioDao.buscar(cedulaUsuario);
        if(usuario == null)
            throw new PrestamoExcepcion("error.UsuarioNoExiste");

        registrarPrestamoView.getTxtNombreLibro().setText(libro.getNombre());
        registrarPrestamoView.getTxtNombreBibliotecario().setText(bibliotecario.getNombre());
        registrarPrestamoView.getTxtNombreUsuario().setText(usuario.getNombre());
    }

    private void registrarPrestamo() throws PrestamoExcepcion{
        String codigo = registrarPrestamoView.getTxtCodigoPrestamo().getText();
        String isbn = registrarPrestamoView.getTxtISBNDeLibroAPrestar().getText();
        String cedulaBibliotecario = registrarPrestamoView.getTxtCedulaDeBibliotecario().getText();
        String cedulaUsuario = registrarPrestamoView.getTxtCedulaDeUsuario().getText();

        if(codigo.isBlank())
            throw new PrestamoExcepcion("campoVacio.CodigoPrestamo");
        if(isbn.isBlank())
            throw new PrestamoExcepcion("campoVacio.ISBNLibro");
        if(cedulaBibliotecario.isBlank())
            throw new PrestamoExcepcion("campoVacio.CedulaBibliotecario");
        if(cedulaUsuario.isBlank())
            throw new PrestamoExcepcion("campoVacio.CedulaUsuario");
        if(prestamoDao.existe(codigo))
            throw new PrestamoExcepcion("error.PrestamoYaExiste");

        Libro libro = (Libro) libroDao.buscar(isbn);
        if(libro == null)
            throw new PrestamoExcepcion("error.LibroNoExiste");
        if(libro.getCantidadDisponible() <= 0)
            throw new PrestamoExcepcion("error.LibroSinDisponibilidad");

        Bibliotecario bibliotecario = bibliotecarioDao.buscar(cedulaBibliotecario);
        if(bibliotecario == null)
            throw new PrestamoExcepcion("error.BibliotecarioNoExiste");

        Usuario usuario = (Usuario) usuarioDao.buscar(cedulaUsuario);
        if(usuario == null)
            throw new PrestamoExcepcion("error.UsuarioNoExiste");

        Prestamo prestamoNuevo = new Prestamo(codigo, bibliotecario.getCedula(), usuario.getCedula(), libro.getISBN());
        prestamoDao.agregar(prestamoNuevo);

        libro.restarDisponibilidad();
        libroDao.actualizar(libro);
    }

    private void buscarPrestamo() throws PrestamoExcepcion{
        String codigo = buscarPrestamoView.getTxtCedulaBibliotecarioBuscar().getText();
        if(codigo.isBlank())
            throw new PrestamoExcepcion("campoVacio.CodigoPrestamo");

        Prestamo prestamo = (Prestamo) prestamoDao.buscar(codigo);
        if(prestamo == null)
            throw new PrestamoExcepcion("error.PrestamoNoExiste");

        Usuario usuario = usuarioDao.buscar(prestamo.getUsuarioCedula());
        Libro libro = (Libro) libroDao.buscar(prestamo.getLibroISBN());

        String fechaDevolucion = prestamo.getFechaDeDevolucion() != null ? prestamo.getFechaDeDevolucion().toString() : "-";
        String sancion = prestamo.getSancion() != null ? prestamo.getSancion().getCodigo() : "-";

        buscarPrestamoView.mostrarInformacion(usuario.getNombre(), libro.getNombre(), prestamo.getFechaDePrestamo().toString(), prestamo.getFechaLimiteDePrestamo().toString(), fechaDevolucion, sancion);
    }

    private void buscarPrestamoADevolver() throws PrestamoExcepcion{
        String codigo = registrarDevolucionView.getTxtCedulaBibliotecarioBuscar().getText();
        if(codigo.isBlank())
            throw new PrestamoExcepcion("campoVacio.CodigoPrestamo");

        prestamoAuxiliar = (Prestamo) prestamoDao.buscar(codigo);
        if(prestamoAuxiliar == null)
            throw new PrestamoExcepcion("error.PrestamoNoExiste");
        if(!prestamoAuxiliar.getEstado())
            throw new PrestamoExcepcion("error.PrestamoYaDevuelto");

        LocalDate fechaDevolucion = LocalDate.now();
        long diasAtraso = ChronoUnit.DAYS.between(prestamoAuxiliar.getFechaLimiteDePrestamo(), fechaDevolucion);
        String sancion = diasAtraso > 0 ? bundle.getString("sancion.SiAplica") : bundle.getString("sancion.NoAplica");
        double monto = diasAtraso > 0 ? diasAtraso * MONTO_POR_DIA_DE_ATRASO : 0.0;

        Usuario usuario = usuarioDao.buscar(prestamoAuxiliar.getUsuarioCedula());
        Libro libro = libroDao.buscar(prestamoAuxiliar.getLibroISBN());

        registrarDevolucionView.mostrarInformacion(usuario.getNombre(), libro.getNombre(), prestamoAuxiliar.getFechaDePrestamo().toString(), prestamoAuxiliar.getFechaLimiteDePrestamo().toString(), fechaDevolucion.toString(), sancion, String.valueOf(monto));
    }

    private void registrarDevolucion() throws PrestamoExcepcion{
        LocalDate fechaDevolucion = LocalDate.now();
        long diasAtraso = ChronoUnit.DAYS.between(prestamoAuxiliar.getFechaLimiteDePrestamo(), fechaDevolucion);

        prestamoAuxiliar.setFechaDeDevolucion(fechaDevolucion);
        prestamoAuxiliar.cambiarEstadoFalse();

        if(diasAtraso > 0){
            double monto = diasAtraso * MONTO_POR_DIA_DE_ATRASO;
            String codigoSancion = "S-" + prestamoAuxiliar.getCodigo();
            Sancion sancion = new Sancion(codigoSancion, fechaDevolucion, bundle.getString("sancion.MotivoRetraso"), monto, prestamoAuxiliar.getCodigo());
            sancionDao.agregar(sancion);
            prestamoAuxiliar.setSancion(sancion);
        }

        prestamoDao.actualizar(prestamoAuxiliar);

        Libro libro = (Libro) libroDao.buscar(prestamoAuxiliar.getLibroISBN());
        libro.sumarDisponibilidad();
        libroDao.actualizar(libro);

        prestamoAuxiliar = null;
    }
    
    public void actualizarIdiomaPrestamo(ResourceBundle bundle){
        this.bundle = bundle;
        registrarDevolucionView.actualizarIdioma(bundle);
        registrarPrestamoView.actualizarIdioma(bundle);
        buscarPrestamoView.actualizarIdioma(bundle);
        listarPrestamoView.actualizarIdioma(bundle);
    }

}
