/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 *Clase tipo Manager la cual es heredada de persona
 * contiene atributos como años de afiliación, y relación con jugadores y un equipo.
 * @author Carla & Jassy
 */
public class Manager extends Persona implements Serializable{
    private int añosAfiliacion;
    private LinkedList<Jugador> misJugadores;
    private Equipo miEquipo;
    private Date fechaCreacion;
    
    public Manager() {
    }

    public Manager(String cedula, String nombre, String apellido, int edad, int añosAfiliacion) {
        super(cedula, nombre, apellido, edad);
        this.añosAfiliacion = añosAfiliacion;
        this.misJugadores = new LinkedList<>();
        Calendar miCalendario;
        miCalendario=Calendar.getInstance();
        this.fechaCreacion=miCalendario.getTime();
    }
    
    /**
     * método que retorna un string con las cédulas de los jugadores de un manager
     * @return String palabra con cédulas de jugadores
     */
    public String jugadoresmanager ()
    {
        String palabra = "";
        
        for (Jugador nuevoJugador : this.getMisJugadores()) 
        {
            palabra = nuevoJugador.getCedula()+", "+palabra; 
        }
        
        return palabra; 
    }
    
    /**
     * Método void para eliminar relación con manager de jugadores
     */
    public void eliminarjugadores()
    {
        for (Jugador jugadorActual : this.getMisJugadores()) 
        {
            jugadorActual.setMiManager(null);
            this.getMisJugadores().remove(jugadorActual);
        }
    }

    /**
     * @return the añosAfiliacion
     */
    public int getAñosAfiliacion() {
        return añosAfiliacion;
    }

    /**
     * @param añosAfiliacion the añosAfiliacion to set
     */
    public void setAñosAfiliacion(int añosAfiliacion) {
        this.añosAfiliacion = añosAfiliacion;
    }

    /**
     * @return the misJugadores
     */
    public LinkedList<Jugador> getMisJugadores() {
        return misJugadores;
    }

    /**
     * @param misJugadores the misJugadores to set
     */
    public void setMisJugadores(LinkedList<Jugador> misJugadores) {
        this.misJugadores = misJugadores;
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
