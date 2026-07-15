/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao.daoArchivos;

import Modelo.dao.InterfazDao;
import Modelo.dominio.Libro;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class LibroArchivosDao implements InterfazDao<Libro>{

    private static final String RUTA = "//RutaEjemplo";

    private static final int TAM_ISBN = 10;
    private static final int TAM_NOMBRE = 80;
    private static final int TAM_FECHA_PUBLICACION = 10;
    private static final int TAM_CANTIDAD_DISPONIBLE = 10;
    private static final int TAM_CANTIDAD_TOTAL = 10;
    private static final int TAM_GENERO_LITERARIO = 80;
    private static final int TAM_IDENTIFICADOR_AUTOR = 10;
    private static final int TAM_REGISTRO = 600;

    public LibroArchivosDao() {
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

    private void escribirRegistro(RandomAccessFile raf, Libro dato) throws IOException {

        long inicio = raf.getFilePointer();

        escribirCadena(raf, dato.getISBN(), TAM_ISBN);
        escribirCadena(raf, dato.getNombre(), TAM_NOMBRE);
        escribirCadena(raf, dato.getFechaDePublicacion() == null ? "" : dato.getFechaDePublicacion().toString(), TAM_FECHA_PUBLICACION);
        escribirCadena(raf, String.valueOf(dato.getCantidadDisponible()), TAM_CANTIDAD_DISPONIBLE);
        escribirCadena(raf, String.valueOf(dato.getCantidadTotal()), TAM_CANTIDAD_TOTAL);
        escribirCadena(raf, dato.getGeneroLiterario(), TAM_GENERO_LITERARIO);
        escribirCadena(raf, dato.getAutor(), TAM_IDENTIFICADOR_AUTOR);

        long bytesEscritos = raf.getFilePointer() - inicio;

        while (bytesEscritos < TAM_REGISTRO) {
            raf.writeByte(0);
            bytesEscritos++;
        }
    }

    private Libro leerRegistro(RandomAccessFile raf) throws IOException {
        String isbn = leerCadena(raf, TAM_ISBN);
        String nombre = leerCadena(raf, TAM_NOMBRE);
        String fechaTexto = leerCadena(raf, TAM_FECHA_PUBLICACION);
        LocalDate fechaPublicacion = fechaTexto.isEmpty() ? null : LocalDate.parse(fechaTexto);
        int cantidadDisponible = Integer.parseInt(leerCadena(raf, TAM_CANTIDAD_DISPONIBLE));
        int cantidadTotal = Integer.parseInt(leerCadena(raf, TAM_CANTIDAD_TOTAL));
        String generoLiterario = leerCadena(raf, TAM_GENERO_LITERARIO);
        String identificadorAutor = leerCadena(raf, TAM_IDENTIFICADOR_AUTOR);

        Libro libro = new Libro(isbn, nombre, fechaPublicacion, cantidadTotal, identificadorAutor, generoLiterario);
        return libro;
    }

    private long buscarPosicion(RandomAccessFile raf, String isbn) throws IOException {
        long cantidadRegistros = raf.length() / TAM_REGISTRO;
        for (long i = 0; i < cantidadRegistros; i++) {
            long posicion = i * TAM_REGISTRO;
            raf.seek(posicion);
            String isbnLeido = leerCadena(raf, TAM_ISBN);
            if (isbnLeido.equals(isbn)) {
                return posicion;
            }
        }
        return -1;
    }

    @Override
    public Libro buscar(String parametro) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            long posicion = buscarPosicion(raf, parametro);
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
    public void eliminar(String parametro) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            long posicion = buscarPosicion(raf, parametro);
            if (posicion == -1) {
                return;
            }
            long ultimaPosicion = raf.length() - TAM_REGISTRO;
            if (posicion != ultimaPosicion) {
                raf.seek(ultimaPosicion);
                Libro ultimo = leerRegistro(raf);
                raf.seek(posicion);
                escribirRegistro(raf, ultimo);
            }
            raf.setLength(ultimaPosicion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregar(Libro dato) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            raf.seek(raf.length());
            escribirRegistro(raf, dato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(Libro dato) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            long posicion = buscarPosicion(raf, dato.getISBN());
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
    public boolean existe(String parametro) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            return buscarPosicion(raf, parametro) != -1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList obtenerLista() {
        ArrayList<Libro> lista = new ArrayList<>();
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
}
