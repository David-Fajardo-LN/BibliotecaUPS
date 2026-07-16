/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Excepciones.AutorExcepcion;
import Modelo.dao.InterfazDao;
import Modelo.dao.LibroDao;
import Modelo.dominio.Autor;
import Modelo.dominio.Bibliotecario;
import Modelo.dominio.Libro;
import Modelo.enums.EstiloLiterario;
import Modelo.enums.Nacionalidad;
import Vista.autor.AgregarAutorView;
import Vista.autor.BuscarAutorView;
import Vista.autor.EliminarAutorView;
import Vista.autor.ListarAutoresView;
import Vista.autor.ModificarAutorView;
import Vista.principal.PrincipalView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ControladorAutor {

    private AgregarAutorView agregarAutorView;
    private BuscarAutorView buscarAutorView;
    private EliminarAutorView eliminarAutorView;
    private ModificarAutorView modificarAutorView;
    private ListarAutoresView listarAutorView;
    private PrincipalView principalView;
    private ResourceBundle bundle;

    private InterfazDao<Autor> autorDao;
    private InterfazDao<Bibliotecario> bibliotecarioDao;
    private LibroDao libroDao;

    private Autor autorAuxiliar;

    public ControladorAutor(InterfazDao autorDao, InterfazDao bibliotecarioDao, LibroDao libroDao, ResourceBundle bundle, PrincipalView principalView) {

        agregarAutorView = new AgregarAutorView();
        buscarAutorView = new BuscarAutorView();
        eliminarAutorView = new EliminarAutorView();
        modificarAutorView = new ModificarAutorView();
        listarAutorView = new ListarAutoresView();
        this.principalView = principalView;

        this.autorDao = autorDao;
        this.bibliotecarioDao = bibliotecarioDao;
        this.libroDao = libroDao;
        this.bundle = bundle;
    }

    public void configurarEventosAgregarAutor(){
        agregarAutorView.getBtnAgregarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    agregarAutor();
                    agregarAutorView.limpiarTextos();
                    agregarAutorView.mostrarMensaje(bundle.getString("exito.AgregarAutor"));
                }catch(AutorExcepcion ex){
                    agregarAutorView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        agregarAutorView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarAutorView.limpiarTextos();
                agregarAutorView.setVisible(false);
            }
        });
    }

    public void configurarEventosBuscarAutor(){
        buscarAutorView.getBtnBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarAutor();
                }catch(AutorExcepcion ex){
                    buscarAutorView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        buscarAutorView.getBtnRegresar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarAutorView.limpiarTextos();
                buscarAutorView.setVisible(false);
            }
        });
    }

    public void configurarEventosEliminarAutor(){
        eliminarAutorView.getBtnBuscarUsuario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarAutorAEliminar();
                }catch(AutorExcepcion ex){
                    eliminarAutorView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        eliminarAutorView.getBtnEliminarBibliotecario().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(autorAuxiliar == null){
                    eliminarAutorView.mostrarMensaje(bundle.getString("error.BuscarAutorPrimero"));
                    return;
                }

                try{
                    eliminarAutor();
                    eliminarAutorView.limpiarTextos();
                    eliminarAutorView.mostrarMensaje(bundle.getString("exito.EliminarAutor"));
                }catch(AutorExcepcion ex){
                    eliminarAutorView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        eliminarAutorView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                autorAuxiliar = null;
                eliminarAutorView.limpiarTextos();
                eliminarAutorView.setVisible(false);
            }
        });
    }

    public void configurarEventosModificarAutor(){
        modificarAutorView.getBtnBuscarAutor().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    buscarAutorAModificar();
                }catch(AutorExcepcion ex){
                    modificarAutorView.mostrarMensaje(bundle.getString("error.AutorNoExiste"));
                }
            }
        });

        modificarAutorView.getBtnModificar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(autorAuxiliar == null){
                    modificarAutorView.mostrarMensaje(bundle.getString("error.BuscarAutorPrimero"));
                    return;
                }

                try{
                    modificarAutor();
                    modificarAutorView.limpiarTextos();
                    modificarAutorView.mostrarMensaje(bundle.getString("exito.AutorModificado"));
                }catch(AutorExcepcion ex){
                    modificarAutorView.mostrarMensaje(bundle.getString(ex.getMessage()));
                }
            }
        });

        modificarAutorView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                autorAuxiliar = null;
                modificarAutorView.limpiarTextos();
                modificarAutorView.setVisible(false);
            }
        });
    }

    public void configurarEventosListarAutores(){
        listarAutorView.getBtnListarAutores().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Autor> autores = autorDao.obtenerLista();
                ArrayList<Object[]> filas = new ArrayList<>();
                for(Autor a : autores){
                    filas.add(new Object[]{a.getCodigoIdentificador(), a.getNombre(), a.getNacionalidad(), a.getEstiloLiterario(), a.getFechaDeNacimiento()});
                }
                listarAutorView.cargarDatosTabla(filas);
            }
        });

        listarAutorView.getBtnCancelar().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                listarAutorView.limpiarTabla();
                listarAutorView.setVisible(false);
            }
        });
    }


    public void activarVentanaAgregarAutor(){
        agregarAutorView.cargarEstilosLiterario(obtenerEstilosLiterarios());
        agregarAutorView.cargarNacionalidades(obtenerNacionalidades());
        principalView.abrirVentana(agregarAutorView);
    }

    public void activarVentanaBuscarAutor(){
        principalView.abrirVentana(buscarAutorView);
    }

    public void activarVentanaEliminarAutor(){
        principalView.abrirVentana(eliminarAutorView);
    }

    public void activarVentanaModificarAutor(){
        modificarAutorView.cargarEstilosLiterario(obtenerEstilosLiterarios());
        modificarAutorView.cargarNacionalidades(obtenerNacionalidades());
        principalView.abrirVentana(modificarAutorView);
    }

    public void activarVentanaListarAutor(){
        listarAutorView.actualizarIdioma(bundle);
        principalView.abrirVentana(listarAutorView);
    }
    
    public void actualizarIdiomaAutor(ResourceBundle nuevo){
        this.bundle = nuevo;
        agregarAutorView.actualizarIdioma(obtenerEstilosLiterarios(), obtenerNacionalidades(), bundle);
        buscarAutorView.actualizarIdioma(nuevo);
        eliminarAutorView.actualizarIdioma(nuevo);
        modificarAutorView.actualizarIdioma(obtenerEstilosLiterarios(), obtenerNacionalidades(), bundle);
        listarAutorView.actualizarIdioma(nuevo);
    }

    private void agregarAutor() throws AutorExcepcion{
        String identificador = agregarAutorView.getTxtIdentificadorAutorNuevo().getText();
        String nombres = agregarAutorView.getTxtNombreAutorNuevo().getText();
        String nacionalidad = (String) agregarAutorView.getComboBOXNacionalidad().getSelectedItem();
        String estiloLiterario = (String) agregarAutorView.getComboBOXEstiloLiterario().getSelectedItem();
        Date fechaNacimiento = agregarAutorView.getFechaNacimiento().getDate();

        if(identificador.isBlank())
            throw new AutorExcepcion("campoVacio.Identificador");
        if(nombres.isBlank())
            throw new AutorExcepcion("campoVacio.Nombres");
        if(nacionalidad.isBlank())
            throw new AutorExcepcion("campoVacio.Nacionalidad");
        if(estiloLiterario.isBlank())
            throw new AutorExcepcion("campoVacio.EstiloLiterario");
        if(fechaNacimiento == null)
            throw new AutorExcepcion("campoVacio.FechaNacimiento");
        if(autorDao.existe(identificador))
            throw new AutorExcepcion("error.AutorYaExiste");

        LocalDate fecha = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Autor autorNuevo = new Autor(identificador, nombres, nacionalidad, fecha, estiloLiterario);
        autorDao.agregar(autorNuevo);
        modificarAutorView.limpiarTextos();
    }

    private void buscarAutor() throws AutorExcepcion{
        String identificador = buscarAutorView.getTxtCedulaBibliotecarioBuscar().getText();
        if(identificador.isBlank())
            throw new AutorExcepcion("campoVacio.Identificador");

        Autor autor = autorDao.buscar(identificador);

        if(autor == null)
            throw new AutorExcepcion("error.AutorNoExiste");

        buscarAutorView.mostrarInformacion(autor.getNombre(), autor.getNacionalidad(), autor.getEstiloLiterario(), autor.getFechaDeNacimiento().toString());

        ArrayList<Libro> libros = libroDao.obtenerPorAutor(identificador);
        ArrayList<Object[]> filas = new ArrayList<>();
        for(Libro l : libros){
            filas.add(new Object[]{l.getISBN(), l.getNombre(), l.getFechaDePublicacion(), l.getCantidadDisponible(), l.getCantidadTotal()});
        }
        buscarAutorView.cargarDatosTabla(filas);
    }

    private void buscarAutorAEliminar() throws AutorExcepcion{
        String identificador = eliminarAutorView.getTxtCedulaAutorAEliminar().getText();
        if(identificador.isBlank())
            throw new AutorExcepcion("campoVacio.Identificador");

        autorAuxiliar = autorDao.buscar(identificador);

        if(autorAuxiliar == null)
            throw new AutorExcepcion("error.AutorNoExiste");

        eliminarAutorView.mostrarInformacion(autorAuxiliar.getNombre(), autorAuxiliar.getNacionalidad(), autorAuxiliar.getEstiloLiterario(), autorAuxiliar.getFechaDeNacimiento().toString());
    }

    private void eliminarAutor() throws AutorExcepcion{
        String cedulaSupervisor = eliminarAutorView.getTxtCedulaSupervisor().getText();

        if(cedulaSupervisor.isBlank())
            throw new AutorExcepcion("campoVacio.CedulaSupervisor");
        if(!bibliotecarioDao.existe(cedulaSupervisor))
            throw new AutorExcepcion("error.CedulaSupervisorNoExiste");

        autorDao.eliminar(autorAuxiliar.getCodigoIdentificador());
        autorAuxiliar = null;
    }

    private void buscarAutorAModificar() throws AutorExcepcion{
        String identificador = modificarAutorView.getTxtIdentificadorAutor().getText();
        if(identificador.isBlank())
            throw new AutorExcepcion("campoVacio.Identificador");

        autorAuxiliar = autorDao.buscar(identificador);

        if(autorAuxiliar == null)
            throw new AutorExcepcion("error.AutorNoExiste");

        Date fecha = Date.from(autorAuxiliar.getFechaDeNacimiento().atStartOfDay(ZoneId.systemDefault()).toInstant());
        modificarAutorView.mostrarInformacionAutor(autorAuxiliar.getNombre(), autorAuxiliar.getNacionalidad(), autorAuxiliar.getEstiloLiterario(), fecha);
    }

    private void modificarAutor() throws AutorExcepcion{
        String cedulaSupervisor = modificarAutorView.getTxtCedulaDeSupervisor().getText();

        if(cedulaSupervisor.isBlank())
            throw new AutorExcepcion("campoVacio.CedulaSupervisor");
        if(!bibliotecarioDao.existe(cedulaSupervisor))
            throw new AutorExcepcion("error.CedulaSupervisorNoExiste");

        String nuevoNombre = modificarAutorView.getTxtNombreAutorAModificar().getText();
        String nuevaNacionalidad = (String) modificarAutorView.getComboBOXNacionalidad().getSelectedItem();
        String nuevoEstilo = (String) modificarAutorView.getComboBOXEstilosLiterario().getSelectedItem();
        Date nuevaFecha = modificarAutorView.getFechaNacimientoAutorAModificar().getDate();

        if(nuevoNombre.isBlank())
            throw new AutorExcepcion("campoVacio.Nombres");
        if(nuevaNacionalidad.isBlank())
            throw new AutorExcepcion("campoVacio.Nacionalidad");
        if(nuevoEstilo.isBlank())
            throw new AutorExcepcion("campoVacio.EstiloLiterario");
        if(nuevaFecha == null)
            throw new AutorExcepcion("campoVacio.FechaNacimiento");

        LocalDate fecha = nuevaFecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Autor modificado = new Autor(autorAuxiliar.getCodigoIdentificador(), nuevoNombre, nuevaNacionalidad, fecha, nuevoEstilo);
        autorDao.actualizar(modificado);
        autorAuxiliar = null;
        modificarAutorView.limpiarTextos();
    }
    
    public List<String> obtenerNacionalidades() {

        List<String> nacionalidades = new ArrayList<>();

        for (Nacionalidad nacionalidad : Nacionalidad.values()) {
            nacionalidades.add(bundle.getString(nacionalidad.name()));
        }

        return nacionalidades;
    }
    
    public List<String> obtenerEstilosLiterarios(){
        List<String> estilos = new ArrayList<>();
        for(EstiloLiterario estilo: EstiloLiterario.values()){
            estilos.add(bundle.getString(estilo.name()));
        }
        return estilos;
    }

}
