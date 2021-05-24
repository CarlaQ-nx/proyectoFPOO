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
 *Clase heredada de Persona la cual consta de atributos como años de 
 *fidelidad, abonado y tiene relación con el equipo al cual sigue.
 * @author Carla & Jassy
 */
public class Aficionado extends Persona implements Serializable{
    private int añosFidelidad;
    private boolean Abonado; 
    private Equipo miEquipo;
    private Date fechaCreacion;
    
    public Aficionado() {
    }

    public Aficionado(int añosFidelidad, boolean Abonado, String cedula, String nombre, String apellido, int edad) {
        super(cedula, nombre, apellido, edad);
        this.añosFidelidad = añosFidelidad;
        this.Abonado = Abonado;
        this.miEquipo = new Equipo ();
        Calendar miCalendario;
        miCalendario=Calendar.getInstance();
        this.fechaCreacion=miCalendario.getTime();
    }
    
    

    /**
     * @return the añosFidelidad
     */
    public int getAñosFidelidad() {
        return añosFidelidad;
    }

    /**
     * @param añosFidelidad the añosFidelidad to set
     */
    public void setAñosFidelidad(int añosFidelidad) {
        this.añosFidelidad = añosFidelidad;
    }

    /**
     * @return the miEquipo
     */
    public Equipo getMiEquipo() {
        return miEquipo;
    }

    /**
     * @param miEquipo the miEquipo to set
     */
    public void setMiEquipo(Equipo miEquipo) {
        this.miEquipo = miEquipo;
    }

    /**
     * @return the Abonado
     */
    public boolean isAbonado() {
        return Abonado;
    }

    /**
     * @param Abonado the Abonado to set
     */
    public void setAbonado(boolean Abonado) {
        this.Abonado = Abonado;
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
