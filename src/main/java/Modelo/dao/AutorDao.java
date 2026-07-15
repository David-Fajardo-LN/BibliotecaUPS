/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao;

import Modelo.dominio.Autor;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AutorDao implements InterfazDao<Autor>{

    private static final String RUTA = "//RutaEjemplo";

    private static final int TAM_IDENTIFICADOR = 10;
    private static final int TAM_NOMBRE = 80;
    private static final int TAM_NACIONALIDAD = 80;
    private static final int TAM_ESTILO_LITERARIO = 80;
    private static final int TAM_FECHA_NACIMIENTO = 10;
    private static final int TAM_REGISTRO = 700;

    public AutorDao() {
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

    private void escribirRegistro(RandomAccessFile raf, Autor dato) throws IOException {

        long inicioRegistro = raf.getFilePointer();

        escribirCadena(raf, dato.getIdentificador(), TAM_IDENTIFICADOR);
        escribirCadena(raf, dato.getNombre(), TAM_NOMBRE);
        escribirCadena(raf, dato.getNacionalidad(), TAM_NACIONALIDAD);
        escribirCadena(raf, dato.getEstiloLiterario(), TAM_ESTILO_LITERARIO);
        escribirCadena(raf,
                dato.getFechaDeNacimiento() == null? "": dato.getFechaDeNacimiento().toString(),TAM_FECHA_NACIMIENTO);

        long bytesEscritos = raf.getFilePointer() - inicioRegistro;

        while (bytesEscritos < TAM_REGISTRO) {
            raf.writeByte(0);
            bytesEscritos++;
        }
    }

    private Autor leerRegistro(RandomAccessFile raf) throws IOException {
        String identificador = leerCadena(raf, TAM_IDENTIFICADOR);
        String nombre = leerCadena(raf, TAM_NOMBRE);
        String nacionalidad = leerCadena(raf, TAM_NACIONALIDAD);
        String estiloLiterario = leerCadena(raf, TAM_ESTILO_LITERARIO);
        String fechaTexto = leerCadena(raf, TAM_FECHA_NACIMIENTO);
        LocalDate fechaNacimiento = fechaTexto.isEmpty() ? null : LocalDate.parse(fechaTexto);

        return new Autor(identificador, nombre, nacionalidad, fechaNacimiento, estiloLiterario);
    }

    private long buscarPosicion(RandomAccessFile raf, String identificador) throws IOException {
        long cantidadRegistros = raf.length() / TAM_REGISTRO;
        for (long i = 0; i < cantidadRegistros; i++) {
            long posicion = i * TAM_REGISTRO;
            raf.seek(posicion);
            String identificadorLeido = leerCadena(raf, TAM_IDENTIFICADOR);
            if (identificadorLeido.equals(identificador)) {
                return posicion;
            }
        }
        return -1;
    }

    @Override
    public Autor buscar(String parametro) {
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
                Autor ultimo = leerRegistro(raf);
                raf.seek(posicion);
                escribirRegistro(raf, ultimo);
            }
            raf.setLength(ultimaPosicion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregar(Autor dato) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            raf.seek(raf.length());
            escribirRegistro(raf, dato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(Autor dato) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            long posicion = buscarPosicion(raf, dato.getIdentificador());
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
    public ArrayList<Autor> obtenerLista() {
        ArrayList<Autor> lista = new ArrayList<>();
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
