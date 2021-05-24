/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *Clase Jornada la cual consta de una id, un número y un eslogan
 * Una jornada está relacionada con varios partidos 
 * @author Carla & Jassy
 */
public class Jornada implements Serializable{
    private String id;
    private int numero;
    private String eslogan;
    private LinkedList<Partido> misPartidos;
    
    public Jornada() {
    }

    public Jornada(String id, int numero, String eslogan) {
        this.id = id;
        this.numero = numero;
        this.eslogan = eslogan;
        this.misPartidos=new LinkedList<>();
    }  
    
    /**
     * Método string el cual cumple con la finalidad de devolver los identificadores de los partidos de la jornada      
     * @return variable tipo String la cual contiene los identificadores de los partidos
     */
    public String publicarpatidos ()
    {
        String palabra = "";
        
        for(Partido partidoActual : this.getMisPartidos())
        {
            if (partidoActual != null)
            {
                palabra = palabra+partidoActual.getId()+", "; 
            }
        }
        
        return palabra; 
    }
    
    /**
     * Método void para eliminar un partido de la jornada
     * @param identificador Se requiere este parámetro para encontrar el partido a eliminar
     */
    public void eliminarPartido (String identificador)
    {
        for (Partido partidoActual : this.getMisPartidos()) 
        {
            if (partidoActual.getId().equals(identificador))
            {
                this.getMisPartidos().remove(partidoActual);
            }
        }
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the eslogan
     */
    public String getEslogan() {
        return eslogan;
    }

    /**
     * @param eslogan the eslogan to set
     */
    public void setEslogan(String eslogan) {
        this.eslogan = eslogan;
    }

    /**
     * @return the misPartidos
     */
    public LinkedList<Partido> getMisPartidos() {
        return misPartidos;
    }

    /**
     * @param misPartidos the misPartidos to set
     */
    public void setMisPartidos(LinkedList<Partido> misPartidos) {
        this.misPartidos = misPartidos;
    }
}
