/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *La clase equipo es aquella clase donde se instancian los equipos que participan en la liga
 * Tiene atributos como nombre, año de fundación, número de títulos, goles a favor y en contra y partidos jugados
 * Tiene relación con un manager, con sus respectivos jugadores y aficionadios, su respectivo técnico y los partidos en los que participa
 * @author Carla & Jassy
 */
public class Equipo implements Serializable{
    private String id;
    private String nombre;
    private int añoFundacion;
    private int numeroTitulosN;
    private int numeroTitulosI;
    private int puntos;
    private int golesAFavor;
    private int golesEnContra;
    private int partidosJugados;
    private Manager miManager;
    private LinkedList<Jugador> misJugadores;
    private LinkedList<Partido> misPartidos;
    private Tecnico miTecnico;
    private LinkedList<Aficionado> misAficionados; 

    public Equipo() {
    }

    public Equipo(String id, String nombre, int añoFundacion, int numeroTitulosN, int numeroTitulosI, int puntos, int golesAFavor, int golesEnContra, int partidosJugados) {
        this.id = id;
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.numeroTitulosN = numeroTitulosN;
        this.numeroTitulosI = numeroTitulosI;
        this.puntos = puntos;
        this.golesAFavor = golesAFavor;
        this.golesEnContra = golesEnContra;
        this.partidosJugados = partidosJugados;
        this.misPartidos=new LinkedList<>();
        this.misJugadores=new LinkedList<>();
        this.misAficionados=new LinkedList<>();
    }
    
    /**
     * Este método tiene la finalidad de promediar la edad de todas las personas que hacen
     * parte de este.
     * @return Se retorna una variable double la cual corresponde a la edad promedio.
     */
    public double promedioEdad(){
            int sumaEdades=0;
            int n=this.getMisJugadores().size();
            for(Persona personaActual: this.getMisJugadores()){
                    sumaEdades=sumaEdades+personaActual.getEdad();
                }
            double promedio=((double)sumaEdades)/n;
            return promedio;
        }
    
    /**
     * El presente método calcula el promedio del salario de todos los jugadores y el técnico de un equipo
     * @return Retorna una variable double, la cual es el promedio calculado
     */
    public double promedioNomina(){
        double promedio = 0.0;
        double suma = 0.0;
        
        if (this.getMiTecnico() != null)
        {
            suma = this.getMiTecnico().getSalario();
        }
        
        int n = this.getMisJugadores().size()+1;
        for(Jugador jugadorActual: this.getMisJugadores()){
            if(jugadorActual != null)
            {
                suma=suma+jugadorActual.getSalario();
            }
        }
        promedio=suma/((double) n);
        return promedio;
    }
    
    /**
     * Método void para agregar un jugador a una lista de estos, por relación de asociación
     * @param nuevoJugador Objeto jugador que será agregado al equipo
     */
    public void agregarJugador(Jugador nuevoJugador){
        this.getMisJugadores().add(nuevoJugador);
    }
    
    /**
     * Método void para agregar un técnico por relación de asociación
     * @param nuevoTecnico Objeto técnico que será agregado al equipo
     */
    public void agregarTecnico(Tecnico nuevoTecnico){
        this.setMiTecnico(nuevoTecnico);
    }
    
    /**
     * Buscar jugador dentro del equipo
     * @param id variable String de la cédula necesaria para encontrar al jugador
     * @return retorna una variable booleana, la cual es falsa si no aparece y verdadera si lo hace
     */
    public boolean buscarjugador (String id)
    {
        boolean aparece = false;
        
        for (Jugador jugadorActual : this.getMisJugadores()) 
        {
            if(jugadorActual.getCedula().equals(id))
            {
                aparece = true;
            }
        }
        
        return aparece;
    }
    
    /**
     * Buscar aficionado dentro del equipo
     * @param id variable String de la cédula necesaria para encontrar el aficionado
     * @return retorna una variable booleana, la cual es falsa si no aparece y verdadera si lo hace
     */
    public boolean buscaraficionado (String id)
    {
        boolean aparece = false;
        
        for (Aficionado aficionadoActual : this.getMisAficionados()) 
        {
            if(aficionadoActual.getCedula().equals(id))
            {
                aparece = true;
            }
        }
        
        return aparece;
    }
    
    /**
     * Método void que elimina jugadores dentro del equipo
     */
    public void eliminarjugadores ()
    {
        this.setMisJugadores(new LinkedList <> ());
    }
    
    /**
     * Método void que elimina aficionados dentro del equipo
     */
    public void eliminaraficionados ()
    {
        for(Aficionado aficionadoActual : this.getMisAficionados())
        {
            this.getMisAficionados().remove();
        }
    }
    
    /**
     * Método void el cual tiene la finalidad de eliminar un partido que el equipo haya tenido
     * @param identificador variable string del identificador necesario para encontrar el partido
     */
    public void eliminarPartido (String identificador)
    {
        for (Partido partidoActual: this.getMisPartidos()) 
        {
            if(partidoActual.getId().equals(identificador))
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
     * @return the añoFundacion
     */
    public int getAñoFundacion() {
        return añoFundacion;
    }

    /**
     * @param añoFundacion the añoFundacion to set
     */
    public void setAñoFundacion(int añoFundacion) {
        this.añoFundacion = añoFundacion;
    }

    /**
     * @return the numeroTitulosN
     */
    public int getNumeroTitulosN() {
        return numeroTitulosN;
    }

    /**
     * @param numeroTitulosN the numeroTitulosN to set
     */
    public void setNumeroTitulosN(int numeroTitulosN) {
        this.numeroTitulosN = numeroTitulosN;
    }

    /**
     * @return the numeroTitulosI
     */
    public int getNumeroTitulosI() {
        return numeroTitulosI;
    }

    /**
     * @param numeroTitulosI the numeroTitulosI to set
     */
    public void setNumeroTitulosI(int numeroTitulosI) {
        this.numeroTitulosI = numeroTitulosI;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the golesAFavor
     */
    public int getGolesAFavor() {
        return golesAFavor;
    }

    /**
     * @param golesAFavor the golesAFavor to set
     */
    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    /**
     * @return the golesEnContra
     */
    public int getGolesEnContra() {
        return golesEnContra;
    }

    /**
     * @param golesEnContra the golesEnContra to set
     */
    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    /**
     * @return the partidosJugados
     */
    public int getPartidosJugados() {
        return partidosJugados;
    }

    /**
     * @param partidosJugados the partidosJugados to set
     */
    public void setPartidosJugados(int partidosJugados) {
        this.partidosJugados = partidosJugados;
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

    /**
     * @return the miTecnico
     */
    public Tecnico getMiTecnico() {
        return miTecnico;
    }

    /**
     * @param miTecnico the miTecnico to set
     */
    public void setMiTecnico(Tecnico miTecnico) {
        this.miTecnico = miTecnico;
    }

    /**
     * @return the misAficionados
     */
    public LinkedList<Aficionado> getMisAficionados() {
        return misAficionados;
    }

    /**
     * @param misAficionados the misAficionados to set
     */
    public void setMisAficionados(LinkedList<Aficionado> misAficionados) {
        this.misAficionados = misAficionados;
    }
}
