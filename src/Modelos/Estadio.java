/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *Clase Estadio la cual instancia los estadios los cuales constan de una id, de una ciudad, nombre y capacidad
 * Tiene relación con los partidos ya que como es un estadio, los partidos se realizan en este.
 * @author Carla & Jassy
 */
public class Estadio implements Serializable{
    private String id;
    private String ciudad;
    private String nombre;
    private int capacidad; 
    private LinkedList<Partido> misPartidos;

    public Estadio() {
    }

    public Estadio(String id, String ciudad, String nombre, int capacidad) {
        this.id = id;
        this.ciudad = ciudad;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.misPartidos=new LinkedList<>();
    }
    
    /**
     * Método String el cual tiene la finalidad de devolver las identificaciones de los partidos
     * @return String palabra, la cual contiene las identificaciones de los partidos
     */
    public String publicarpartidos ()
    {
        String palabra = "";
        
        for (Partido partidoActual : this.getMisPartidos()) 
        {
            palabra = palabra+partidoActual.getId()+", ";
        }
        
        return palabra; 
    }
    
    /**
     * Método void para eliminar partidos de un estadio
     * @param identificador Se requiere del parámetro identificador para encontrar el partido a eliminar
     */
    public void eliminarpartido (String identificador)
    {
        for (Partido partidoActual : this.getMisPartidos()) 
        {
            if (partidoActual != null)
            {
                if (partidoActual.getId().equals(identificador))
                {
                    this.getMisPartidos().remove(partidoActual);
                }
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
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * @param capacidad the capacidad to set
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
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
