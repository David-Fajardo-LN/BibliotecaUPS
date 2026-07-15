/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao;

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
public class SancionArchivosDao implements InterfazDao<Sancion>{
    private static final String RUTA = "//RutaEjemplo";

    private static final int TAM_CODIGO = 10;
    private static final int TAM_FECHA_SANCION = 10;
    private static final int TAM_DESCRIPCION = 80;
    private static final int TAM_MONTO = 10;
    private static final int TAM_CODIGO_PRESTAMO = 10;
    private static final int TAM_REGISTRO = 300;

    public SancionArchivosDao() {
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

    private void escribirRegistro(RandomAccessFile raf, Sancion dato) throws IOException {

        long inicioRegistro = raf.getFilePointer();

        escribirCadena(raf, dato.getCodigo(), TAM_CODIGO);
        escribirCadena(raf, dato.getFechaDeSancion() == null ? "" : dato.getFechaDeSancion().toString(), TAM_FECHA_SANCION);
        escribirCadena(raf, dato.getDescripción(), TAM_DESCRIPCION);
        escribirCadena(raf, String.valueOf(dato.getMonto()), TAM_MONTO);
        raf.writeBoolean(dato.getEstado());
        escribirCadena(raf, dato.getPrestamo(), TAM_CODIGO_PRESTAMO);

        long bytesEscritos = raf.getFilePointer() - inicioRegistro;

        while (bytesEscritos < TAM_REGISTRO) {
            raf.writeByte(0);
            bytesEscritos++;
        }
    }

    private Sancion leerRegistro(RandomAccessFile raf) throws IOException {
        String codigo = leerCadena(raf, TAM_CODIGO);
        String fechaTexto = leerCadena(raf, TAM_FECHA_SANCION);
        LocalDate fechaSancion = fechaTexto.isEmpty() ? null : LocalDate.parse(fechaTexto);
        String descripcion = leerCadena(raf, TAM_DESCRIPCION);
        double monto = Double.parseDouble(leerCadena(raf, TAM_MONTO));
        boolean estado = raf.readBoolean();
        String codigoPrestamo = leerCadena(raf, TAM_CODIGO_PRESTAMO);

        Sancion sancion = new Sancion(codigo, fechaSancion, descripcion, monto, codigoPrestamo);
        sancion.setEstado(estado);
        return sancion;
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
    public Sancion buscar(String parametro) {
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
                Sancion ultimo = leerRegistro(raf);
                raf.seek(posicion);
                escribirRegistro(raf, ultimo);
            }
            raf.setLength(ultimaPosicion);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void agregar(Sancion dato) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            raf.seek(raf.length());
            escribirRegistro(raf, dato);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizar(Sancion dato) {
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
    public boolean existe(String parametro) {
        try (RandomAccessFile raf = new RandomAccessFile(new File(RUTA), "rw")) {
            return buscarPosicion(raf, parametro) != -1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList obtenerLista() {
        ArrayList<Sancion> lista = new ArrayList<>();
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
