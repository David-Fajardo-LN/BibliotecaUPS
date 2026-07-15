/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.dao.AutorArchivosDao;
import Modelo.dao.InterfazDao;
import Modelo.dao.daoArchivos.LibroArchivosDao;
import Modelo.dao.SancionArchivosDao;
import Modelo.dao.UsuarioArchivosDao;
import Modelo.dao.daoArchivos.BibliotecarioArchivosDao;
import Modelo.dao.daoArchivos.PrestamoArchivosDao;
import Modelo.dao.daoMemoria.AutorDaoMemoria;
import Modelo.dao.daoMemoria.BibliotecarioDaoMemoria;
import Modelo.dao.daoMemoria.LibroDaoMemoria;
import Modelo.dao.daoMemoria.PrestamoDaoMemoria;
import Modelo.dao.daoMemoria.SancionDaoMemoria;
import Modelo.dao.daoMemoria.UsuarioDaoMemoria;
import Vista.principal.PrincipalView;
import Vista.principal.TipoDePersistenciaDaoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author User
 */
public class ControladorPrincipal {
    private PrincipalView principalView;
    private TipoDePersistenciaDaoView tipoDePersistenciaDao;
    
    private static ResourceBundle bundle;
    private static Locale localeActual;
    
    private InterfazDao autorDao;
    private InterfazDao bibliotecarioDao;
    private InterfazDao libroDao;
    private InterfazDao prestamoDao;
    private InterfazDao usuarioDao;
    private InterfazDao sancionDao;
    
    private ControladorUsuario controladorUsuario;
    private ControladorBibliotecario controladorBibliotecario;
    private ControladorAutor controladorAutor;
    private ControladorLibro controladorLibro;
    private ControladorPrestamo controladorPrestamo;
    private ControladorSancion controladorSancion;

    public ControladorPrincipal() {
        this.principalView = new PrincipalView();
        this.tipoDePersistenciaDao = new TipoDePersistenciaDaoView();
        this.localeActual = new Locale("es", "EC");
        this.bundle = ResourceBundle.getBundle("idioma.mensajes", localeActual);

        configuradorEventos();
    }
    
    
    
    
    private void configuradorEventos(){
        configurarEventosAccesosRapidos();
        configurarEventosMenuAutores();
        configurarEventosMenuBibliotecarios();
        configurarEventosMenuLibros();
        configurarEventosMenuPrestamos();
        configurarEventosMenuSanciones();
        configurarEventosMenuUsuarios();
        configurarEventosMenuIdioma();
        configurarEventosTiposDePersistenciaDao();
    }
    
    public void iniciarPrograma(){
        tipoDePersistenciaDao.setVisible(true);
    }
    
    private void cambiarIdioma(String idioma, String pais){
        localeActual = new Locale(idioma, pais);
        bundle = ResourceBundle.getBundle("idioma.mensajes", localeActual);
        principalView.actualizarIdioma(bundle);
    }
    
    private void inicializarControladores(){
        controladorUsuario = new ControladorUsuario(usuarioDao, bibliotecarioDao, bundle,principalView);
        controladorBibliotecario = new ControladorBibliotecario(bibliotecarioDao, bundle,principalView);
        controladorAutor = new ControladorAutor(autorDao, bibliotecarioDao, bundle,principalView);
        controladorLibro = new ControladorLibro(libroDao, autorDao, bibliotecarioDao, bundle,principalView);
        controladorPrestamo = new ControladorPrestamo(prestamoDao, libroDao, usuarioDao, bibliotecarioDao, sancionDao, bundle,principalView);
        controladorSancion = new ControladorSancion(sancionDao, prestamoDao, bundle,principalView);

        controladorUsuario.configurarEventosAgregarUsuario();
        controladorUsuario.configurarEventosBuscarUsuario();
        controladorUsuario.configurarEventosEliminarUsuario();
        controladorUsuario.configurarEventosModificarUsuario();
        controladorUsuario.configurarEventosListarUsuarios();

        controladorBibliotecario.configurarEventosAgregarBibliotecario();
        controladorBibliotecario.configurarEventosBuscarBibliotecario();
        controladorBibliotecario.configurarEventosEliminarBibliotecario();
        controladorBibliotecario.configurarEventosModificarBibliotecario();
        controladorBibliotecario.configurarEventosListarBibliotecarios();

        controladorAutor.configurarEventosAgregarAutor();
        controladorAutor.configurarEventosBuscarAutor();
        controladorAutor.configurarEventosEliminarAutor();
        controladorAutor.configurarEventosModificarAutor();
        controladorAutor.configurarEventosListarAutores();

        controladorLibro.configurarEventosAgregarLibro();
        controladorLibro.configurarEventosBuscarLibro();
        controladorLibro.configurarEventosEliminarLibro();
        controladorLibro.configurarEventosModificarLibro();
        controladorLibro.configurarEventosListarLibros();

        controladorPrestamo.configurarEventosRegistrarPrestamo();
        controladorPrestamo.configurarEventosBuscarPrestamo();
        controladorPrestamo.configurarEventosRegistrarDevolucion();
        controladorPrestamo.configurarEventosListarPrestamos();

        controladorSancion.configurarEventosBuscarSancion();
        controladorSancion.configurarEventosPagarMulta();
        controladorSancion.configurarEventosListarSanciones();
    }
    
    private void configurarEventosTiposDePersistenciaDao(){
        tipoDePersistenciaDao.getBtnMemoriaArchivos().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                autorDao = new AutorArchivosDao();
                bibliotecarioDao = new BibliotecarioArchivosDao();
                libroDao = new LibroArchivosDao();
                usuarioDao = new UsuarioArchivosDao();
                prestamoDao = new PrestamoArchivosDao();
                sancionDao = new SancionArchivosDao();
                inicializarControladores();
                tipoDePersistenciaDao.dispose();
                principalView.actualizarIdioma(bundle);
                principalView.setVisible(true);
            }
        });
        
        tipoDePersistenciaDao.getBtnMemoriaRAM().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                autorDao = new AutorDaoMemoria();
                bibliotecarioDao = new BibliotecarioDaoMemoria();
                libroDao = new LibroDaoMemoria();
                usuarioDao = new UsuarioDaoMemoria();
                prestamoDao = new PrestamoDaoMemoria();
                sancionDao = new SancionDaoMemoria();
                inicializarControladores();
                tipoDePersistenciaDao.dispose();
                principalView.actualizarIdioma(bundle);
                principalView.setVisible(true);
            }
        });
    }
    
    private void configurarEventosAccesosRapidos(){
        
        // Botones de acceso rapido
        
        principalView.getBtnBuscarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLibro.activarVentanaBuscarLibro();
            }
        });
        
        principalView.getBtnPagarMulta().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorSancion.activarVentanaPagarMulta();
            }
        });
        
       principalView.getBtnRegistrarDevolucion().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorPrestamo.activarVentanaRegistrarDevolucion();
            }
       });
       
       principalView.getBtnRegistrarPrestamo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorPrestamo.activarVentanaRegistrarPrestamo();
            }
       });
       
    }
    
    private void configurarEventosMenuIdioma(){
        principalView.getItemMenuIdiomaEspañol().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarIdioma("es", "EC");
            }
        });
        
        principalView.getItemMenuIdiomaIngles().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cambiarIdioma("en", "US");
            }
        });
    }
    
    private void configurarEventosMenuUsuarios(){
        
        principalView.getItemAgregarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorUsuario.activarVentanaAgregarUsuario();
            }
        });
        
        principalView.getItemBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorUsuario.activarVentanaBuscarUsuario();
            }
        });
        
        principalView.getItemEliminarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorUsuario.activarVentanaEliminarUsuario();
            }
        });
        
        principalView.getItemModificarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorUsuario.activarVentanaModificarUsuario();
            }
        });
        
        principalView.getItemListarUsuarios().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorUsuario.activarVentanaListarUsuario();
            }
        });
    }
    
    private void configurarEventosMenuBibliotecarios(){
        
        principalView.getItemAgregarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorBibliotecario.activarVentanaAgregarBibliotecario();
            }
        });
        
        principalView.getItemBuscarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorBibliotecario.activarVentanaBuscarBibliotecario();
            }
        });
        
        principalView.getItemEliminarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorBibliotecario.activarVentanaEliminarBibliotecario();
            }
        });
        
        principalView.getItemModificarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorBibliotecario.activarVentanaModificarBibliotecario();
            }
        });
        
        principalView.getItemListarBibliotecarios().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorBibliotecario.activarVentanaListarBibliotecario();
            }
        });
        
    }
    
    private void configurarEventosMenuAutores(){
        principalView.getItemAgregarAutor().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorAutor.activarVentanaAgregarAutor();
            }
        });
        
        principalView.getItemBuscarAutor().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorAutor.activarVentanaBuscarAutor();
            }
        });
        
        principalView.getItemEliminarAutor().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorAutor.activarVentanaEliminarAutor();
            }
        });
        
        principalView.getItemModificarAutor().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorAutor.activarVentanaModificarAutor();
            }
        });
        
        principalView.getItemListarAutores().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorAutor.activarVentanaListarAutor();
            }
        });
    }
    
    private void configurarEventosMenuLibros(){
        principalView.getItemAgregarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLibro.activarVentanaAgregarLibro();
            }
        });
        
        principalView.getItemBuscarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLibro.activarVentanaBuscarLibro();
            }
        });
        
        principalView.getItemEliminarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLibro.activarVentanaEliminarLibro();
            }
        });
        
        principalView.getItemModificarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLibro.activarVentanaModificarLibro();
            }
        });
        
        principalView.getItemListarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorLibro.activarVentanaListarLibro();
            }
        });
    }
    
    private void configurarEventosMenuPrestamos(){
        principalView.getItemBuscarPrestamo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorPrestamo.activarVentanaBuscarPrestamo();
            }
        });
        
        principalView.getItemRegistrarPrestamo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorPrestamo.activarVentanaRegistrarPrestamo();
            }
        });
        
        principalView.getItemRegistrarDevolucion().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorPrestamo.activarVentanaRegistrarDevolucion();
            }
        });
        
        principalView.getItemListarPrestamos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorPrestamo.activarVentanaListarPrestamo();
            }
        });
    }
    
    private void configurarEventosMenuSanciones(){
        principalView.getItemBuscarSancion().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorSancion.activarVentanaBuscarSancion();
            }
        });
        
        principalView.getItemPagarMulta().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorSancion.activarVentanaPagarMulta();
            }
        });
        
        principalView.getItemListarSanciones().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorSancion.activarVentanaListarSancion();
            }
        });
    }
    
    public static void main(String[] args) {
    System.out.println("A");

    java.awt.EventQueue.invokeLater(() -> {
        System.out.println("B");

        ControladorPrincipal controlador = new ControladorPrincipal();

        System.out.println("C");

        controlador.iniciarPrograma();

        System.out.println("D");
    });
}
}
