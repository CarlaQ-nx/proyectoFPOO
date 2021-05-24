/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;

/**
 *Clase Partido la cual tiene atributos como id, fecha, goles del local y visitante
 * Tiene relación con un árbitro, con un estadio con los equipos involucrados en el partido y con su respectiva jornada
 * @author Carla & Jassy
 */
public class Partido implements Serializable{
    private String id;
    private String fecha;
    private int golesLocal;
    private int golesVisitante; 
    private Arbitro miArbitro;
    private Estadio miEstadio;
    private Equipo refEquipoLocal;
    private Equipo refEquipoVisitante;
    private Jornada refJornada; 

    public Partido() {
    }

    public Partido(String id, String fecha, int golesLocal, int golesVisitante) {
        this.id = id;
        this.fecha = fecha;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
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
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the golesLocal
     */
    public int getGolesLocal() {
        return golesLocal;
    }

    /**
     * @param golesLocal the golesLocal to set
     */
    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    /**
     * @return the golesVisitante
     */
    public int getGolesVisitante() {
        return golesVisitante;
    }

    /**
     * @param golesVisitante the golesVisitante to set
     */
    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    /**
     * @return the miArbitro
     */
    public Arbitro getMiArbitro() {
        return miArbitro;
    }

    /**
     * @param miArbitro the miArbitro to set
     */
    public void setMiArbitro(Arbitro miArbitro) {
        this.miArbitro = miArbitro;
    }

    /**
     * @return the miEstadio
     */
    public Estadio getMiEstadio() {
        return miEstadio;
    }

    /**
     * @param miEstadio the miEstadio to set
     */
    public void setMiEstadio(Estadio miEstadio) {
        this.miEstadio = miEstadio;
    }

    /**
     * @return the refJornada
     */
    public Jornada getRefJornada() {
        return refJornada;
    }

    /**
     * @param refJornada the refJornada to set
     */
    public void setRefJornada(Jornada refJornada) {
        this.refJornada = refJornada;
    }

    /**
     * @return the refEquipoLocal
     */
    public Equipo getRefEquipoLocal() {
        return refEquipoLocal;
    }

    /**
     * @param refEquipoLocal the refEquipoLocal to set
     */
    public void setRefEquipoLocal(Equipo refEquipoLocal) {
        this.refEquipoLocal = refEquipoLocal;
    }

    /**
     * @return the refEquipoVisitante
     */
    public Equipo getRefEquipoVisitante() {
        return refEquipoVisitante;
    }

    /**
     * @param refEquipoVisitante the refEquipoVisitante to set
     */
    public void setRefEquipoVisitante(Equipo refEquipoVisitante) {
        this.refEquipoVisitante = refEquipoVisitante;
    }
}
