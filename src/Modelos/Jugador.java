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
 *Clase jugador la cual es heredada de la clase abstracta de persona, los jugadores consisten de 
 * nacionalidad, posición, número de goles y salario
 * Tiene relación con su respectivo manager
 * @author Carla & Jassy
 */
public class Jugador extends Persona implements Serializable{
    private String nacionalidad;
    private String posicion;
    private int numeroGoles;
    private double salario;
    private Manager miManager;
    private Date fechaCreacion; 

    public Jugador() {
    }

    public Jugador(String cedula, String nombre, String apellido, int edad,String nacionalidad, String posicion, int numeroGoles, double salario) {
        super(cedula, nombre, apellido, edad);
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.numeroGoles = numeroGoles;
        Calendar miCalendario; 
        miCalendario = Calendar.getInstance();
        this.fechaCreacion = miCalendario.getTime();
        this.salario = salario;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @return the posicion
     */
    public String getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the numeroGoles
     */
    public int getNumeroGoles() {
        return numeroGoles;
    }

    /**
     * @param numeroGoles the numeroGoles to set
     */
    public void setNumeroGoles(int numeroGoles) {
        this.numeroGoles = numeroGoles;
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * @return the miManager
     */
    public Manager getMiManager() {
        return miManager;
    }

    /**
     * @param miManager the miManager to set
     */
    public void setMiManager(Manager miManager) {
        this.miManager = miManager;
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
