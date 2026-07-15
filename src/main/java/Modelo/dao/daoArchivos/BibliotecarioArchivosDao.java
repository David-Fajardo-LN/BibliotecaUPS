/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao;

import Modelo.dominio.Bibliotecario;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class BibliotecarioArchivosDao implements InterfazDao<Bibliotecario>{

    private static final String RUTA = "//RutaEjemplo";

    private static final int TAM_CEDULA = 10;
    private static final int TAM_NOMBRE = 80;
    private static final int TAM_EMAIL = 80;
    private static final int TAM_TELEFONO = 10;
    private static final int TAM_SECTOR = 80;
    private static final int TAM_CARGO = 80;
    private static final int TAM_REGISTRO = 750;

    public BibliotecarioArchivosDao() {
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

    private void escribirRegistro(RandomAccessFile raf, Bibliotecario dato) throws IOException {
        long inicio = raf.getFilePointer();

        escribirCadena(raf, dato.getCedula(), TAM_CEDULA);
        escribirCadena(raf, dato.getNombre(), TAM_NOMBRE);
        escribirCadena(raf, dato.getEmail(), TAM_EMAIL);
        escribirCadena(raf, dato.getTelefono(), TAM_TELEFONO);
        escribirCadena(raf, dato.getSector(), TAM_SECTOR);
        escribirCadena(raf, dato.getCargo(), TAM_CARGO);
        raf.writeBoolean(dato.isTienePermisoAvanzado());

        long escritos = raf.getFilePointer() - inicio;

        while (escritos < TAM_REGISTRO) {
            raf.writeByte(0);
            escritos++;
        }
    }

    private Bibliotecario leerRegistro(RandomAccessFile raf) throws IOException {
        String cedula = leerCadena(raf, TAM_CEDULA);
        String nombre = leerCadena(raf, TAM_NOMBRE);
        String email = leerCadena(raf, TAM_EMAIL);
        String telefono = leerCadena(raf, TAM_TELEFONO);
        String sector = leerCadena(raf, TAM_SECTOR);
        String cargo = leerCadena(raf, TAM_CARGO);
        boolean permisoAvanzado = raf.readBoolean();

        return new Bibliotecario(sector, cargo, permisoAvanzado, cedula, nombre, email, telefono);
    }

    private long buscarPosicion(RandomAccessFile raf, String cedula) throws IOException {
        long cantidadRegistros = raf.length() / TAM_REGISTRO;
        for (long i = 0; i < cantidadRegistros; i++) {
            long posicion = i * TAM_REGISTRO;
            raf.seek(posicion);
            String cedulaLeida = leerCadena(raf, TAM_CEDULA);
            if (cedulaLeida.equals(cedula)) {
                return posicion;
            }
        }
        return -1;
    }

    @Override
    public Bibliotecario buscar(String parametro) {
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
                Bibliotecario ultimo = leerRegistro(raf);
                raf.seek(posicion);
                escribirRegistro(raf, ultimo);
            }
            raf.setLength(ultimaPosicion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregar(Bibliotecario dato) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            raf.seek(raf.length());
            escribirRegistro(raf, dato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(Bibliotecario dato) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            long posicion = buscarPosicion(raf, dato.getCedula());
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
        ArrayList<Bibliotecario> lista = new ArrayList<>();
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
