/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.principal;

import Modelo.dao.AutorDao;
import Modelo.dao.BibliotecarioDao;
import Modelo.dao.LibroDao;
import Modelo.dao.PrestamoDao;
import Modelo.dao.SancionDao;
import Modelo.dao.UsuarioDao;
import Vista.principal.PrincipalView;
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
    private static ResourceBundle bundle;
    private static Locale localeActual;
    
    private AutorDao autorDao;
    private BibliotecarioDao bibliotecarioDao;
    private LibroDao libroDao;
    private PrestamoDao prestamoDao;
    private UsuarioDao usuarioDao;
    private SancionDao sancionDao;

    public ControladorPrincipal() {
        this.principalView = new PrincipalView();
        this.localeActual = new Locale("es", "EC");
        this.bundle = ResourceBundle.getBundle("mensajes", localeActual);
        
        this.autorDao = new AutorDao();
        this.bibliotecarioDao = new BibliotecarioDao();
        this.libroDao = new LibroDao();
        this.prestamoDao = new PrestamoDao();
        this.sancionDao = new SancionDao();
        this.usuarioDao = new UsuarioDao();

        configuradorEventos();
    }
    
    
    
    
    public void configuradorEventos(){
        configurarEventosAccesosRapidos();
        configurarEventosMenuAutores();
        configurarEventosMenuBibliotecarios();
        configurarEventosMenuLibros();
        configurarEventosMenuPrestamos();
        configurarEventosMenuSanciones();
        configurarEventosMenuUsuarios();
    }
    
    
    private void cambiarIdioma(String idioma, String pais){
        localeActual = new Locale(idioma, pais);
        bundle = ResourceBundle.getBundle("mensajes", localeActual);
    }
    
    public void configurarEventosAccesosRapidos(){
        
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
    
    public void configurarEventosMenuIdioma(){
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
    
    public void configurarEventosMenuUsuarios(){
        
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
    
    public void configurarEventosMenuBibliotecarios(){
        
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
    
    public void configurarEventosMenuAutores(){
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
    
    public void configurarEventosMenuLibros(){
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
    
    public void configurarEventosMenuPrestamos(){
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
    
    public void configurarEventosMenuSanciones(){
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
