

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao.daoArchivos;

import Modelo.dao.PrestamoDao;
import Modelo.dominio.Prestamo;
import Modelo.dominio.Sancion;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class PrestamoArchivosDao implements PrestamoDao{

    private static final String RUTA = "datos/prestamos.dat";

    private static final int TAM_CODIGO = 10;
    private static final int TAM_FECHA_PRESTAMO = 10;
    private static final int TAM_FECHA_DEVOLUCION = 10;
    private static final int TAM_FECHA_LIMITE = 10;
    private static final int TAM_CEDULA_BIBLIOTECARIO = 10;
    private static final int TAM_CEDULA_USUARIO = 10;
    private static final int TAM_ISBN_LIBRO = 10;
    private static final int TAM_CODIGO_SANCION = 10;
    private static final int TAM_REGISTRO = 200;

    private final SancionArchivosDao sancionDao = new SancionArchivosDao();

    public PrestamoArchivosDao() {
        try {
            File archivo = new File(RUTA);

            File carpeta = archivo.getParentFile();
            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs();
            }

            if (!archivo.exists()) {
                archivo.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el archivo de préstamos.", e);
        }
    }

    private String leerCadena(RandomAccessFile raf, int longitud) throws IOException {
        char[] valor = new char[longitud];
        for (int i = 0; i < longitud; i++) {
            valor[i] = raf.readChar();
        }
        return new String(valor).trim();
    }

    private void escribirCadena(RandomAccessFile raf, String valor, int longitud) throws IOException {
        if (valor == null) {
            valor = "";
        }
        StringBuilder sb = new StringBuilder(valor);
        if (sb.length() > longitud) {
            sb.setLength(longitud);
        } else {
            while (sb.length() < longitud) {
                sb.append(' ');
            }
        }
        raf.writeChars(sb.toString());
    }

    private void escribirRegistro(RandomAccessFile raf, Prestamo dato) throws IOException {

        long inicioRegistro = raf.getFilePointer();

        escribirCadena(raf, dato.getCodigo(), TAM_CODIGO);
        raf.writeBoolean(dato.getEstado());
        escribirCadena(raf, dato.getFechaDePrestamo() == null ? "" : dato.getFechaDePrestamo().toString(), TAM_FECHA_PRESTAMO);
        escribirCadena(raf, dato.getFechaDeDevolucion() == null ? "" : dato.getFechaDeDevolucion().toString(), TAM_FECHA_DEVOLUCION);
        escribirCadena(raf, dato.getFechaLimiteDePrestamo() == null ? "" : dato.getFechaLimiteDePrestamo().toString(), TAM_FECHA_LIMITE);
        escribirCadena(raf, dato.getBibliotecarioCedula(), TAM_CEDULA_BIBLIOTECARIO);
        escribirCadena(raf, dato.getUsuarioCedula(), TAM_CEDULA_USUARIO);
        escribirCadena(raf, dato.getLibroISBN(), TAM_ISBN_LIBRO);
        escribirCadena(raf, dato.getSancion() == null ? "" : dato.getSancion().getCodigo(), TAM_CODIGO_SANCION);

        long bytesEscritos = raf.getFilePointer() - inicioRegistro;

        while (bytesEscritos < TAM_REGISTRO) {
            raf.writeByte(0);
            bytesEscritos++;
        }
    }

    private Prestamo leerRegistro(RandomAccessFile raf) throws IOException {
        String codigo = leerCadena(raf, TAM_CODIGO);
        boolean estado = raf.readBoolean();
        String fechaPrestamoTexto = leerCadena(raf, TAM_FECHA_PRESTAMO);
        String fechaDevolucionTexto = leerCadena(raf, TAM_FECHA_DEVOLUCION);
        String fechaLimiteTexto = leerCadena(raf, TAM_FECHA_LIMITE);
        String cedulaBibliotecario = leerCadena(raf, TAM_CEDULA_BIBLIOTECARIO);
        String cedulaUsuario = leerCadena(raf, TAM_CEDULA_USUARIO);
        String isbnLibro = leerCadena(raf, TAM_ISBN_LIBRO);
        String codigoSancion = leerCadena(raf, TAM_CODIGO_SANCION);

        Sancion sancion = codigoSancion.isEmpty() ? null : sancionDao.buscar(codigoSancion);
        LocalDate fechaPrestamo = fechaPrestamoTexto.isEmpty() ? null : LocalDate.parse(fechaPrestamoTexto);
        LocalDate fechaLimite = fechaLimiteTexto.isEmpty() ? null : LocalDate.parse(fechaLimiteTexto);
        LocalDate fechaDevolucion = fechaDevolucionTexto.isEmpty() ? null : LocalDate.parse(fechaDevolucionTexto);
        String cedulaBib = cedulaBibliotecario.isEmpty() ? null : cedulaBibliotecario;
        String cedulaUsu = cedulaUsuario.isEmpty() ? null : cedulaUsuario;
        String isbn = isbnLibro.isEmpty() ? null : isbnLibro;

        Prestamo prestamo = new Prestamo(codigo, fechaPrestamo, fechaLimite, fechaDevolucion, cedulaBib, cedulaUsu, isbn);
        prestamo.setSancion(sancion);
        if (!estado) {
            prestamo.cambiarEstadoFalse();
        }
        return prestamo;
    }

    private long buscarPosicion(RandomAccessFile raf, String codigo) throws IOException {
        long cantidadRegistros = raf.length() / TAM_REGISTRO;
        for (long i = 0; i < cantidadRegistros; i++) {
            long posicion = i * TAM_REGISTRO;
            raf.seek(posicion);
            String codigoLeido = leerCadena(raf, TAM_CODIGO);
            if (codigoLeido.equals(codigo)) {
                return posicion;
            }
        }
        return -1;
    }

    @Override
    public Prestamo buscar(String codigo) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            long posicion = buscarPosicion(raf, codigo);
            if (posicion == -1) {
                return null;
            }
            raf.seek(posicion);
            return leerRegistro(raf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(String codigo) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            long posicion = buscarPosicion(raf, codigo);
            if (posicion == -1) {
                return;
            }
            long ultimaPosicion = raf.length() - TAM_REGISTRO;
            if (posicion != ultimaPosicion) {
                raf.seek(ultimaPosicion);
                Prestamo ultimo = leerRegistro(raf);
                raf.seek(posicion);
                escribirRegistro(raf, ultimo);
            }
            raf.setLength(ultimaPosicion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregar(Prestamo dato) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            raf.seek(raf.length());
            escribirRegistro(raf, dato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(Prestamo dato) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            long posicion = buscarPosicion(raf, dato.getCodigo());
            if (posicion == -1) {
                return;
            }
            raf.seek(posicion);
            escribirRegistro(raf, dato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existe(String codigo) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            return buscarPosicion(raf, codigo) != -1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList obtenerLista() {
        ArrayList<Prestamo> lista = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            long cantidadRegistros = raf.length() / TAM_REGISTRO;
            for (long i = 0; i < cantidadRegistros; i++) {
                raf.seek(i * TAM_REGISTRO);
                lista.add(leerRegistro(raf));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }
    
    public void registrarDevolucion(){
        
    }

    @Override
    public ArrayList<Prestamo> obtenerPorUsuario(String cedula) {
        ArrayList<Prestamo> resultado = new ArrayList<>();
        for (Object o : obtenerLista()) {
            Prestamo p = (Prestamo) o;
            if (p.getUsuarioCedula() != null && p.getUsuarioCedula().equals(cedula)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    @Override
    public ArrayList<Prestamo> obtenerPorBibliotecario(String cedula) {
        ArrayList<Prestamo> resultado = new ArrayList<>();
        for (Object o : obtenerLista()) {
            Prestamo p = (Prestamo) o;
            if (p.getBibliotecarioCedula() != null && p.getBibliotecarioCedula().equals(cedula)) {
                resultado.add(p);
            }
        }
        return resultado;
    }
    
}
