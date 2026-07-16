/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Excepciones.LibroExcepcion;
import Modelo.dao.InterfazDao;
import Modelo.dominio.Autor;
import Modelo.dominio.Libro;
import Modelo.enums.GeneroLiterario;
import Vista.libro.AgregarLibroView;
import Vista.libro.BuscarLibroView;
import Vista.libro.EliminarLibro;
import Vista.libro.ListarLibrosView;
import Vista.libro.ModificarLibroView;
import Vista.principal.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorLibro {

    private AgregarLibroView agregarLibroView;
    private BuscarLibroView buscarLibroView;
    private EliminarLibro eliminarLibroView;
    private ModificarLibroView modificarLibroView;
    private ListarLibrosView listarLibroView;
    private PrincipalView principalView;
    private ResourceBundle bundle;
    

    private InterfazDao libroDao;
    private InterfazDao autorDao;
    private InterfazDao bibliotecarioDao;

    private Libro libroAuxiliar;

    public ControladorLibro(InterfazDao libroDao, InterfazDao autorDao, InterfazDao bibliotecarioDao, ResourceBundle bundle, PrincipalView principalView) {

        agregarLibroView = new AgregarLibroView();
        buscarLibroView = new BuscarLibroView();
        eliminarLibroView = new EliminarLibro();
        modificarLibroView = new ModificarLibroView();
        listarLibroView = new ListarLibrosView();
        this.principalView = principalView;

        this.libroDao = libroDao;
        this.autorDao = autorDao;
        this.bibliotecarioDao = bibliotecarioDao;
        this.bundle = bundle;
    }

    public void configurarEventosAgregarLibro(){
        agregarLibroView.getBtnAgregarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    agregarLibro();
                    agregarLibroView.limpiarTextos();
                    agregarLibroView.mostrarMensaje(bundle.getString("exito.AgregarLibro"));
                }catch(LibroExcepcion ex){
                    agregarLibroView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        agregarLibroView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarLibroView.limpiarTextos();
                agregarLibroView.setVisible(false);
            }
        });
    }

    public void configurarEventosBuscarLibro(){
        buscarLibroView.getBtnBuscarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarLibro();
                }catch(LibroExcepcion ex){
                    buscarLibroView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        buscarLibroView.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLibroView.limpiarTextos();
                buscarLibroView.setVisible(false);
            }
        });
    }

    public void configurarEventosEliminarLibro(){
        eliminarLibroView.getBtnBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarLibroAEliminar();
                    eliminarLibroView.limpiarTextos();
                    eliminarLibroView.mostrarMensaje(bundle.getString("exito.EliminarBibliotecario"));
                }catch(LibroExcepcion ex){
                    eliminarLibroView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        eliminarLibroView.getBtnEliminar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(libroAuxiliar == null){
                    eliminarLibroView.mostrarMensaje(bundle.getString("error.BuscarLibroPrimero"));
                    return;
                }

                try{
                    eliminarLibro();
                    eliminarLibroView.mostrarMensaje(bundle.getString("exito.EliminarLibro"));
                }catch(LibroExcepcion ex){
                    eliminarLibroView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        eliminarLibroView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                libroAuxiliar = null;
                eliminarLibroView.limpiarTextos();
                eliminarLibroView.setVisible(false);
            }
        });
    }

    public void configurarEventosModificarLibro(){
        modificarLibroView.getBtnBuscarLibro().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarLibroAModificar();
                }catch(LibroExcepcion ex){
                    modificarLibroView.mostrarMensaje(bundle.getString("error.LibroNoExiste"));
                }
            }
        });

        modificarLibroView.getBtnModificar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(libroAuxiliar == null){
                    modificarLibroView.mostrarMensaje(bundle.getString("error.BuscarLibroPrimero"));
                    return;
                }

                try{
                    modificarLibro();
                    modificarLibroView.mostrarMensaje(bundle.getString("exito.ModificarLibro"));
                }catch(LibroExcepcion ex){
                    modificarLibroView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        modificarLibroView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                libroAuxiliar = null;
                modificarLibroView.limpiarTextos();
                modificarLibroView.setVisible(false);
            }
        });
    }

    public void configurarEventosListarLibros(){
        listarLibroView.getBtnListarLibros().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Libro> libros = libroDao.obtenerLista();
                ArrayList<Object[]> filas = new ArrayList<>();
                for(Libro l : libros){
                    Autor autor = (Autor) autorDao.buscar(l.getAutor());
                    String nombreAutor = autor != null ? autor.getNombre() : "";
                    filas.add(new Object[]{l.getISBN(), l.getNombre(), l.getFechaDePublicacion(), l.getCantidadDisponible(), l.getCantidadTotal(), l.getGeneroLiterario(), nombreAutor});
                }
                listarLibroView.cargarDatosTabla(filas);
            }
        });

        listarLibroView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                listarLibroView.limpiarTabla();
                listarLibroView.setVisible(false);
            }
        });
    }


    public void activarVentanaAgregarLibro(){
        cargarGeneros();
        principalView.abrirVentana(agregarLibroView);
    }

    public void activarVentanaBuscarLibro(){
        principalView.abrirVentana(buscarLibroView);
    }

    public void activarVentanaEliminarLibro(){
        principalView.abrirVentana(eliminarLibroView);
    }

    public void activarVentanaModificarLibro(){
        modificarLibroView.cargarGeneros(cargarGeneros());
        principalView.abrirVentana(modificarLibroView);
    }

    public void activarVentanaListarLibro(){
        listarLibroView.actualizarIdioma(bundle);
        principalView.abrirVentana(listarLibroView);
    }

    private void agregarLibro() throws LibroExcepcion{
        String isbn = agregarLibroView.getTxtISBNlibroAgregar().getText();
        String nombre = agregarLibroView.getTxtNombreLibroAgregar().getText();
        String cantidadTotalTexto = agregarLibroView.getTxtCantidadTotalLibroAgregar().getText();
        String identificadorAutor = agregarLibroView.getTxtAutorDeLbroAgregar().getText();
        String generoLiterario = (String) agregarLibroView.getComboBOXGeneroLiterarioLibroAgregar().getSelectedItem();
        Date fechaPublicacion = agregarLibroView.getTxtFechaDePublicacionLibroAgregar().getDate();

        if(isbn.isBlank())
            throw new LibroExcepcion("campoVacio.ISBN");
        if(nombre.isBlank())
            throw new LibroExcepcion("campoVacio.NombreLibro");
        if(cantidadTotalTexto.isBlank())
            throw new LibroExcepcion("campoVacio.CantidadTotal");
        if(identificadorAutor.isBlank())
            throw new LibroExcepcion("campoVacio.IdentificadorAutor");
        if(generoLiterario.isBlank())
            throw new LibroExcepcion("campoVacio.GeneroLiterario");
        if(fechaPublicacion == null)
            throw new LibroExcepcion("campoVacio.FechaPublicacion");

        int cantidadTotal = validarCantidad(cantidadTotalTexto);

        if(libroDao.existe(isbn))
            throw new LibroExcepcion("error.LibroYaExiste");
        if(!autorDao.existe(identificadorAutor))
            throw new LibroExcepcion("error.AutorNoExiste");

        LocalDate fecha = fechaPublicacion.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Libro libroNuevo = new Libro(isbn, nombre, fecha, cantidadTotal, identificadorAutor, generoLiterario);
        libroDao.agregar(libroNuevo);
    }

    private void buscarLibro() throws LibroExcepcion{
        String isbn = buscarLibroView.getTxtISBNLibroBuscar().getText();
        if(isbn.isBlank())
            throw new LibroExcepcion("campoVacio.ISBN");

        Libro libro = (Libro) libroDao.buscar(isbn);

        if(libro == null)
            throw new LibroExcepcion("error.LibroNoExiste");

        Autor autor = (Autor) autorDao.buscar(libro.getAutor());
        String nombreAutor = autor != null ? autor.getNombre() : "";

        buscarLibroView.mostrarInformacion(libro.getNombre(), libro.getGeneroLiterario(), String.valueOf(libro.getCantidadTotal()), String.valueOf(libro.getCantidadDisponible()), libro.getFechaDePublicacion().toString(), nombreAutor);
    }

    private void buscarLibroAEliminar() throws LibroExcepcion{
        String isbn = eliminarLibroView.getTxtCedulaAutorAEliminar().getText();
        if(isbn.isBlank())
            throw new LibroExcepcion("campoVacio.ISBN");

        libroAuxiliar = (Libro) libroDao.buscar(isbn);

        if(libroAuxiliar == null)
            throw new LibroExcepcion("error.LibroNoExiste");

        Autor autor = (Autor) autorDao.buscar(libroAuxiliar.getAutor());
        String nombreAutor = autor != null ? autor.getNombre() : "";

        eliminarLibroView.mostrarInformacion(libroAuxiliar.getNombre(), nombreAutor, libroAuxiliar.getGeneroLiterario(), libroAuxiliar.getFechaDePublicacion().toString(), String.valueOf(libroAuxiliar.getCantidadTotal()), String.valueOf(libroAuxiliar.getCantidadDisponible()));
    }

    private void eliminarLibro() throws LibroExcepcion{
        String cedulaSupervisor = eliminarLibroView.getTxtCedulaSupervisor().getText();

        if(cedulaSupervisor.isBlank())
            throw new LibroExcepcion("campoVacio.CedulaSupervisor");
        if(!bibliotecarioDao.existe(cedulaSupervisor))
            throw new LibroExcepcion("error.CedulaSupervisorNoExiste");

        libroDao.eliminar(libroAuxiliar.getISBN());
        libroAuxiliar = null;
    }

    private void buscarLibroAModificar() throws LibroExcepcion{
        String isbn = modificarLibroView.getTxtISBNLibroABuscar().getText();
        if(isbn.isBlank())
            throw new LibroExcepcion("campoVacio.ISBN");

        libroAuxiliar = (Libro) libroDao.buscar(isbn);

        if(libroAuxiliar == null)
            throw new LibroExcepcion("error.LibroNoExiste");

        Date fecha = Date.from(libroAuxiliar.getFechaDePublicacion().atStartOfDay(ZoneId.systemDefault()).toInstant());
        modificarLibroView.mostrarInformacionLibro(libroAuxiliar.getNombre(), libroAuxiliar.getGeneroLiterario(), String.valueOf(libroAuxiliar.getCantidadTotal()), String.valueOf(libroAuxiliar.getCantidadDisponible()), fecha);
    }

    private void modificarLibro() throws LibroExcepcion{
        String cedulaSupervisor = modificarLibroView.getTxtCedulaDeSupervisor().getText();

        if(cedulaSupervisor.isBlank())
            throw new LibroExcepcion("campoVacio.CedulaSupervisor");
        if(!bibliotecarioDao.existe(cedulaSupervisor))
            throw new LibroExcepcion("error.CedulaSupervisorNoExiste");

        String nuevoNombre = modificarLibroView.getTxtNombreLibroAModificar().getText();
        String nuevoGenero = (String) modificarLibroView.getComboBOXGenerosLiterarios().getSelectedItem();
        String nuevaCantidadTotalTexto = modificarLibroView.getTxtCantidadTotalLibroAModificar().getText();
        String nuevaCantidadDisponibleTexto = modificarLibroView.getTxtCantidadDisponibleLibroAModificar().getText();
        Date nuevaFecha = modificarLibroView.getTxtFechaPublicacionLibroAModificar().getDate();

        if(nuevoNombre.isBlank())
            throw new LibroExcepcion("campoVacio.NombreLibro");
        if(nuevoGenero.isBlank())
            throw new LibroExcepcion("campoVacio.GeneroLiterario");
        if(nuevaCantidadTotalTexto.isBlank())
            throw new LibroExcepcion("campoVacio.CantidadTotal");
        if(nuevaFecha == null)
            throw new LibroExcepcion("campoVacio.FechaPublicacion");

        int nuevaCantidadTotal = validarCantidad(nuevaCantidadTotalTexto);
        int nuevaCantidadDisponible = validarCantidad(nuevaCantidadDisponibleTexto);

        LocalDate fecha = nuevaFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Libro modificado = new Libro(libroAuxiliar.getISBN(), nuevoNombre, fecha, nuevaCantidadTotal, libroAuxiliar.getAutor(), nuevoGenero);
        modificado.setCantidadDisponible(nuevaCantidadDisponible);
        libroDao.actualizar(modificado);
        libroAuxiliar = null;
        modificarLibroView.limpiarTextos();
    }

    private int validarCantidad(String texto) throws LibroExcepcion{
        try{
            int cantidad = Integer.parseInt(texto.trim());
            if(cantidad < 0)
                throw new LibroExcepcion("error.CantidadInvalida");
            return cantidad;
        }catch(NumberFormatException ex){
            throw new LibroExcepcion("error.CantidadInvalida");
        }
    }
    
    public void actualizarIdiomaLibro(ResourceBundle bundle){
        this.bundle=bundle;
        agregarLibroView.actualizarIdioma(bundle);
        buscarLibroView.actualizarIdioma(bundle);
        eliminarLibroView.actualizarIdioma(bundle);
        modificarLibroView.actualizarIdioma(bundle);
        listarLibroView.actualizarIdioma(bundle);
    }
    
    public List<String> cargarGeneros() {

        List<String> generos = new ArrayList<>();

        for (GeneroLiterario genero : GeneroLiterario.values()) {
            generos.add(bundle.getString(genero.name()));
        }
        return generos;
    }

}
