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

    private static final int CEDULA_BYTES = 20;
    private static final int NOMBRE_BYTES = 160;
    private static final int EMAIL_BYTES = 160;
    private static final int TELEFONO_BYTES = 20;
    private static final int ESVALIDO_BYTES = 2;

    private static final int TAMANO_REGISTRO = 400;
 
    // Desplazamiento (offset) del campo esValido dentro del registro
    private static final int OFFSET_ESVALIDO = CEDULA_BYTES + NOMBRE_BYTES + EMAIL_BYTES + TELEFONO_BYTES;   
    
    // Ruta inicial del archivo
    private static final String RUTA_ARCHIVO = "//rutaUsuariosEjemeplo.dat";
 
    @Override
    public Usuario buscar(String cedula) {
        try (RandomAccessFile raf = obtenerArchivo("r")) {
            long totalRegistros = raf.length() / TAMANO_REGISTRO;
            for (long i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANO_REGISTRO);
                Usuario usuario = leerRegistro(raf);
                if (usuario.esValido() && usuario.getCedula().equals(cedula)) {
                    return usuario;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al buscar el usuario: " + e.getMessage(), e);
        }
        return null;
    }
 
    @Override
    public void eliminar(String cedula) {
        try (RandomAccessFile raf = obtenerArchivo("rw")) {
            long posicion = obtenerPosicionRegistro(raf, cedula);
            if (posicion == -1) {
                throw new RuntimeException("No existe un usuario con la cedula: " + cedula);
            }
            // Eliminación lógica: solo se marca el registro como inválido
            raf.seek(posicion + OFFSET_ESVALIDO);
            raf.writeShort(0);
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar el usuario: " + e.getMessage(), e);
        }
    }
 
    @Override
    public void agregar(Usuario u) {
        if (existe(u.getCedula())) {
            throw new RuntimeException("Ya existe un usuario con la cedula: " + u.getCedula());
        }
        try (RandomAccessFile raf = obtenerArchivo("rw")) {
            raf.seek(raf.length());
            escribirRegistro(raf, u);
        } catch (IOException e) {
            throw new RuntimeException("Error al agregar el usuario: " + e.getMessage(), e);
        }
    }
 
    @Override
    public void actualizar(Usuario u) {
        try (RandomAccessFile raf = obtenerArchivo("rw")) {
            long posicion = obtenerPosicionRegistro(raf, u.getCedula());
            if (posicion == -1) {
                throw new RuntimeException("No existe un usuario con la cedula: " + u.getCedula());
            }
            raf.seek(posicion);
            escribirRegistro(raf, u);
        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar el usuario: " + e.getMessage(), e);
        }
    }
 
    @Override
    public boolean existe(String cedula) {
        return buscar(cedula) != null;
    }
 
    @Override
    public ArrayList obtenerLista() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try (RandomAccessFile raf = obtenerArchivo("r")) {
            long totalRegistros = raf.length() / TAMANO_REGISTRO;
            for (long i = 0; i < totalRegistros; i++) {
                raf.seek(i * TAMANO_REGISTRO);
                Usuario usuario = leerRegistro(raf);
                if (usuario.esValido()) {
                    lista.add(usuario);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al obtener la lista de usuarios: " + e.getMessage(), e);
        }
        return lista;
    }
 
    // ==================== Métodos de apoyo ====================
 
    /**
     * Abre (creándolo si no existe) el archivo de acceso aleatorio con el modo indicado.
     */
    private RandomAccessFile obtenerArchivo(String modo) throws IOException {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
        return new RandomAccessFile(archivo, modo);
    }
 
    /**
     * Recorre el archivo buscando la posición (byte de inicio) del registro
     * cuya cedula coincide con el parametro. Retorna -1 si no se encuentra
     * o si el registro está marcado como inválido.
     */
    private long obtenerPosicionRegistro(RandomAccessFile raf, String parametro) throws IOException {
        long totalRegistros = raf.length() / TAMANO_REGISTRO;
        for (long i = 0; i < totalRegistros; i++) {
            long posicion = i * TAMANO_REGISTRO;
            raf.seek(posicion);
            Usuario usuario = leerRegistro(raf);
            if (usuario.esValido() && usuario.getCedula().equals(parametro)) {
                return posicion;
            }
        }
        return -1;
    }
 
    /**
     * Lee un registro completo a partir de la posición actual del puntero del archivo.
     */
    private Usuario leerRegistro(RandomAccessFile raf) throws IOException {
        String cedula = leerCadena(raf, CEDULA_BYTES);
        String nombre = leerCadena(raf, NOMBRE_BYTES);
        String email = leerCadena(raf, EMAIL_BYTES);
        String telefono = leerCadena(raf, TELEFONO_BYTES);
        boolean esValido = raf.readShort() == 1;
 
        Usuario usuario = new Usuario(cedula, nombre, email, telefono);
        if (!esValido) {
            usuario.cambiarEstadoAInvalido();
        }
        return usuario;
    }
 
    /**
     * Escribe un registro completo a partir de la posición actual del puntero del archivo,
     * dejando el registro con el tamaño fijo de 400 bytes.
     */
    private void escribirRegistro(RandomAccessFile raf, Usuario usuario) throws IOException {
        escribirCadena(raf, usuario.getCedula(), CEDULA_BYTES);
        escribirCadena(raf, usuario.getNombre(), NOMBRE_BYTES);
        escribirCadena(raf, usuario.getEmail(), EMAIL_BYTES);
        escribirCadena(raf, usuario.getTelefono(), TELEFONO_BYTES);
        raf.writeShort(usuario.esValido() ? 1 : 0);
 
        // Relleno para completar el tamaño fijo de 400 bytes por registro
        int bytesEscritos = CEDULA_BYTES + NOMBRE_BYTES + EMAIL_BYTES + TELEFONO_BYTES + ESVALIDO_BYTES;
        int relleno = TAMANO_REGISTRO - bytesEscritos;
        if (relleno > 0) {
            raf.write(new byte[relleno]);
        }
    }
 
    /**
     * Escribe una cadena en un campo de tamaño fijo, rellenando con ceros si es más corta.
     */
    private void escribirCadena(RandomAccessFile raf, String valor, int longitud) throws IOException {
        byte[] destino = new byte[longitud];
        if (valor != null) {
            byte[] origen = valor.getBytes("UTF-8");
            int cantidad = Math.min(origen.length, longitud);
            System.arraycopy(origen, 0, destino, 0, cantidad);
        }
        raf.write(destino);
    }
 
    /**
     * Lee una cadena de un campo de tamaño fijo, eliminando el relleno de ceros.
     */
    private String leerCadena(RandomAccessFile raf, int longitud) throws IOException {
        byte[] datos = new byte[longitud];
        raf.readFully(datos);
        return new String(datos, "UTF-8").replace("\0", "").trim();
    }
}
