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
 *Clase Técnico heredada de la clase de personas, los técnicos poseen atributos como años de experiencia y salario
 * 
 * @author Carla & Jassy
 */
public class Tecnico extends Persona implements Serializable{
    private int añosExp;
    private double salario; 
    private Date fechaCreacion;
    
    public Tecnico() {
    }

    public Tecnico(String cedula, String nombre, String apellido, int edad,int añosExp, double salario) {
        super(cedula, nombre, apellido, edad);
        this.añosExp = añosExp;
        this.salario = salario;
        Calendar miCalendario;
        miCalendario=Calendar.getInstance();
        this.fechaCreacion=miCalendario.getTime();
    }

    /**
     * @return the añosExp
     */
    public int getAñosExp() {
        return añosExp;
    }

    /**
     * @param añosExp the añosExp to set
     */
    public void setAñosExp(int añosExp) {
        this.añosExp = añosExp;
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
