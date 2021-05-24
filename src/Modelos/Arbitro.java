/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *Clase Árbitro heredada de persona la cual tiene atributos como si es árbitro fifa
 * Tiene relación con el partido al que se asigne.
 * @author Carla & Jassy
 */
public class Arbitro extends Persona implements Serializable{
    private boolean fifa;
    private Partido miPartido;
    private Date fechaCreacion;

    public Arbitro() {
    }

    public Arbitro(String cedula, String nombre, String apellido, int edad, boolean fifa) 
    {
        super(cedula, nombre, apellido, edad);
        this.fifa = fifa;
        Calendar miCalendario;
        miCalendario=Calendar.getInstance();
        this.fechaCreacion=miCalendario.getTime();
    }
    
    /**
     * Método void para eliminar partido de la clase árbitro
     */
    public void eliminarpartido ()
    {
        this.setMiPartido(null); 
    }

    /**
     * @return the fifa
     */
    public boolean isFifa() {
        return fifa;
    }

    /**
     * @param fifa the fifa to set
     */
    public void setFifa(boolean fifa) {
        this.fifa = fifa;
    }

    /**
     * @return the miPartido
     */
    public Partido getMiPartido() {
        return miPartido;
    }

    /**
     * @param miPartido the miPartido to set
     */
    public void setMiPartido(Partido miPartido) {
        this.miPartido = miPartido;
    }

    /**
     * @return the fechaCreacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * @param fechaCreacion the fechaCreacion to set
     */
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    
}
