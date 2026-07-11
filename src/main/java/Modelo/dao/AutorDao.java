/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.dao;

import Modelo.dominio.Autor;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AutorDao implements InterfazDao<Autor>{
    
    private ArrayList<Autor> autores;

    public AutorDao() {
        this.autores = generarAutoresIniciales();
    }
    
    @Override
    public Autor buscar(String identificador) {
        for(Autor a : autores)
            if(a.getIdentificador().equals(identificador))
                return a;
        return null;
    }

    @Override
    public void eliminar(String identificador) {
        autores.removeIf((a) -> a.getIdentificador().equals(identificador));
    }

    @Override
    public void agregar(Autor agregar) {
        autores.add(agregar);
    }

    @Override
    public void actualizar(Autor otro) {
        for(Autor a : autores){
            if(a.getIdentificador().equals(otro.getIdentificador())){
                a.setFechaDeNacimiento(otro.getFechaDeNacimiento());
                a.setEstiloLiterario(otro.getEstiloLiterario());
                a.setNacionalidad(otro.getNacionalidad());
                a.setNombre(otro.getNombre());
                return;
            }
        }
    }

    @Override
    public boolean existe(String identificador) {
       for(Autor a : autores)
            if(a.getIdentificador().equals(identificador))
                return true;
       return false;
    }

    @Override
    public ArrayList obtenerLista() {
       return autores;
    }
    

    // METODO PRIVADO QUE GENERA AUTORES INICIALES
    
    private ArrayList<Autor> generarAutoresIniciales(){
        ArrayList<Autor> autores = new ArrayList<>();

        autores.add(new Autor("1", "J. K. Rowling", "Británica", LocalDate.of(1965, 7, 31), "Narrativa fantástica"));
        autores.add(new Autor("2", "J. R. R. Tolkien", "Británica", LocalDate.of(1892, 1, 3), "Fantasía épica"));
        autores.add(new Autor("3", "Miguel de Cervantes", "Española", LocalDate.of(1547, 9, 29), "Novela clásica"));
        autores.add(new Autor("4", "George Orwell", "Británica", LocalDate.of(1903, 6, 25), "Novela distópica"));
        autores.add(new Autor("5", "Harper Lee", "Estadounidense", LocalDate.of(1926, 4, 28), "Realismo social"));
        autores.add(new Autor("6", "Gabriel García Márquez", "Colombiana", LocalDate.of(1927, 3, 6), "Realismo mágico"));
        autores.add(new Autor("7", "Antoine de Saint-Exupéry", "Francesa", LocalDate.of(1900, 6, 29), "Narrativa poética"));
        autores.add(new Autor("8", "Fiódor Dostoyevski", "Rusa", LocalDate.of(1821, 11, 11), "Novela psicológica"));
        autores.add(new Autor("9", "Jane Austen", "Británica", LocalDate.of(1775, 12, 16), "Novela de costumbres"));
        autores.add(new Autor("10", "Julio Verne", "Francesa", LocalDate.of(1828, 2, 8), "Aventura científica"));
        
        return autores;
    }

}
