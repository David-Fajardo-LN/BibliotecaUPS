/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.dao.AutorArchivosDao;
import Modelo.dao.BibliotecarioArchivosDao;
import Modelo.dao.InterfazDao;
import Modelo.dao.daoArchivos.LibroArchivosDao;
import Modelo.dao.SancionArchivosDao;
import Modelo.dao.UsuarioArchivosDao;
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
import javax.swing.Action;

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

    public ControladorPrincipal() {
        this.principalView = new PrincipalView();
        this.localeActual = new Locale("es", "EC");
        this.bundle = ResourceBundle.getBundle("mensajes", localeActual);
        
        this.autorDao = new AutorArchivosDao();
        this.bibliotecarioDao = new BibliotecarioArchivosDao();
        this.libroDao = new LibroArchivosDao();
        
        this.controladorUsuario = new ControladorUsuario(usuarioDao, bibliotecarioDao, bundle);

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
        configurarEventosTiposDePersistenciaDao();
    }
    
    public void iniciarPrograma(){
        tipoDePersistenciaDao.setVisible(true);
    }
    
    private void cambiarIdioma(String idioma, String pais){
        localeActual = new Locale(idioma, pais);
        bundle = ResourceBundle.getBundle("mensajes", localeActual);
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
                tipoDePersistenciaDao.dispose();
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
                tipoDePersistenciaDao.dispose();
                principalView.setVisible(true);
            }
        });
    }
    
    private void configurarEventosAccesosRapidos(){
        
        // Botones de acceso rapido
        
        principalView.getBtnBuscarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // COMPLETAR CODIGO 777777777777777777777777
            }
        });
        
        principalView.getBtnPagarMulta().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // COMPLETAR CODIGO 777777777777777777777777
            }
        });
        
       principalView.getBtnRegistrarDevolucion().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // COMPLETAR CODIGO 777777777777777777777777
            }
       });
       
       principalView.getBtnRegistrarPrestamo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               // COMPLETAR CODIGO 777777777777777777777777
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
                
            }
        });
        
        principalView.getItemBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        principalView.getItemEliminarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        principalView.getItemModificarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        principalView.getItemListarUsuarios().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
    }
    
    private void configurarEventosMenuBibliotecarios(){
        
        principalView.getItemAgregarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        principalView.getItemBuscarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        principalView.getItemEliminarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        principalView.getItemModificarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        principalView.getItemListarBibliotecarios().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });
        
    }
    
    private void configurarEventosMenuAutores(){
        principalView.getItemAgregarAutor().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });
        
        principalView.getItemBuscarAutor().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        principalView.getItemEliminarAutor().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        principalView.getItemModificarAutor().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        principalView.getItemListarAutores().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
    }
    
    private void configurarEventosMenuLibros(){
        principalView.getItemAgregarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });
        
        principalView.getItemBuscarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        principalView.getItemEliminarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        principalView.getItemModificarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });
        
        principalView.getItemListarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });
    }
    
    private void configurarEventosMenuPrestamos(){
        principalView.getItemBuscarPrestamo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
             
            }
        });
        
        principalView.getItemRegistrarPrestamo().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            
            }
        });
        
        principalView.getItemRegistrarDevolucion().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });
        
        principalView.getItemListarPrestamos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });
    }
    
    private void configurarEventosMenuSanciones(){
        principalView.getItemBuscarSancion().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
              
            }
        });
        
        principalView.getItemPagarMulta().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
        principalView.getItemListarSanciones().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
