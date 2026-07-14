/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao;

import Modelo.dominio.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class UsuarioDao implements InterfazDao<Usuario>{

    private static final String RUTA = "//RutaEjemplo";

    private static final int TAM_CEDULA = 10;
    private static final int TAM_NOMBRE = 80;
    private static final int TAM_EMAIL = 80;
    private static final int TAM_TELEFONO = 10;
    private static final int TAM_REGISTRO = 400;

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

    private void escribirRegistro(RandomAccessFile raf, Usuario dato) throws IOException {

        long inicioRegistro = raf.getFilePointer();

        escribirCadena(raf, dato.getCedula(), TAM_CEDULA);
        escribirCadena(raf, dato.getNombre(), TAM_NOMBRE);
        escribirCadena(raf, dato.getEmail(), TAM_EMAIL);
        escribirCadena(raf, dato.getTelefono(), TAM_TELEFONO);
        raf.writeBoolean(dato.esValido());

        long bytesEscritos = raf.getFilePointer() - inicioRegistro;

        while (bytesEscritos < TAM_REGISTRO) {
            raf.writeByte(0);
            bytesEscritos++;
        }
    }

    private Usuario leerRegistro(RandomAccessFile raf) throws IOException {
        String cedula = leerCadena(raf, TAM_CEDULA);
        String nombre = leerCadena(raf, TAM_NOMBRE);
        String email = leerCadena(raf, TAM_EMAIL);
        String telefono = leerCadena(raf, TAM_TELEFONO);
        boolean esValido = raf.readBoolean();

        Usuario usuario = new Usuario(cedula, nombre, email, telefono);
        if (!esValido) {
            usuario.cambiarEstadoAInvalido();
        }
        return usuario;
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
    public Usuario buscar(String parametro) {
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
                Usuario ultimo = leerRegistro(raf);
                raf.seek(posicion);
                escribirRegistro(raf, ultimo);
            }
            raf.setLength(ultimaPosicion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregar(Usuario dato) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            raf.seek(raf.length());
            escribirRegistro(raf, dato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(Usuario dato) {
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
        ArrayList<Usuario> lista = new ArrayList<>();
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
