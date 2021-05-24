/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.io.Serializable;
import java.util.LinkedList;

/**
 *La liga es la clase que unifica todas las clases, ya que es la main 
 * Consiste de una id y un nombre
 * Se relaciona con personas, jornadas, equipos, estadios y partidos.
 * @author Carla & Jassy
 */
public class Liga implements Serializable{
    private String id;
    private String nombre;
    private LinkedList<Persona> misPersonas;
    private LinkedList<Jornada> misJornadas; 
    private LinkedList<Equipo> misEquipos;
    private LinkedList<Estadio> misEstadios;
    private LinkedList<Partido> mispartidos;
    
    public Liga() {
    }

    public Liga(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.misPersonas=new LinkedList<>();
        this.misJornadas=new LinkedList<>();
        this.misEquipos=new LinkedList<>();
        this.misEstadios=new LinkedList<>();
        this.mispartidos = new LinkedList<>();
    }
    
    /**
     * Método funcional con la finalidad de retornar al jugador con menor edad en toda la liga
     * @return Se retorna el objeto jugador, el cual es heredado de la clase abstracta de Persona
     */
    public Jugador jugadorMasJoven(){
        Jugador jugador=null;
        int menor=Integer.MAX_VALUE;
        for(Persona personaActual: this.getMisPersonas()){
                if(personaActual instanceof Jugador){
                    if(personaActual.getEdad()<menor){
                        menor=personaActual.getEdad();
                        jugador=(Jugador) personaActual;
                    }
                }
            }
        return jugador;
    }
    
    /**
     * Este método tiene la finalidad de retornar el equipo con 
     * el promedio más alto de los salarios de jugadores y el técnico.
     * @return Retorna un objeto tipo equipo
     */
    public Equipo equipoNominaMasAlta(){
        Equipo equipo=null;
        double mayor=Double.MIN_VALUE;
        for(Equipo equipoActual: this.getMisEquipos()){
            if(equipoActual.promedioNomina()>mayor){
                mayor=equipoActual.promedioNomina();
                equipo=equipoActual;
            }
        }
        return equipo;
    }
    
    /**
     * Este método tiene la finalidad de retornar el equipo con mayor cantidad de aficionados en la liga
     * @return Retorna un objeto tipo equipo
     */
    public Equipo equipoMayorCantidadAficionados(){
        Equipo equipo=null;
        int mayor=Integer.MIN_VALUE;
        for(Equipo equipoActual: this.getMisEquipos()){
            if(equipoActual.getMisAficionados().size()>mayor){
                mayor=equipoActual.getMisAficionados().size();
                equipo=equipoActual;
            }
        }
        return equipo;
    }
    
    /**
     * El método tiene la finalidad de retornar el identificador del partido con más goles en la liga
     * @return Retorna una variable tipo String la cual corresponde al identificador del partido
     */
    public String identificadorPartidoMasGoles(){
        String id="";
        int mayor=Integer.MIN_VALUE;
            for(Partido partidoActual: this.getMispartidos()){
                int suma=partidoActual.getGolesLocal()+partidoActual.getGolesVisitante();
                if(suma>mayor){
                    mayor=suma;
                    id=partidoActual.getId();
                }
            }
        return id;
    }
    
    /**
     * Este método tipo int tiene la finalidad de retornar la cantidad de partidos en los cuales un equipo ha perdido por goleada,
     * es decir, que cualquiera de los equipos gane con 4 goles de diferencia
     * @return variable tipo int que contiene la cantidad de partidos que cumplen con esa condición
     */
    public int cantidadEquiposGoleada(){
        int cantidad=0;
        for(Partido partidoActual: this.getMispartidos()){
            int resta=partidoActual.getGolesLocal()-partidoActual.getGolesVisitante();
            if(resta==-4||resta==4){
                cantidad++;
            }
        }
        return cantidad;
    }
    
    /**
     * El método cumple con la función de retornar al jugador que ha marcado más goles en la liga
     * @return Objeto jugador
     */
    public Jugador jugadorMasGoles(){
        Jugador jugador=null;
        int mayor=Integer.MIN_VALUE;
        for(Persona jugadorActual: this.getMisPersonas()){
            if(jugadorActual instanceof Jugador){
                if(((Jugador) jugadorActual).getNumeroGoles()>mayor){
                    mayor=((Jugador) jugadorActual).getNumeroGoles();
                    jugador=(Jugador) jugadorActual;
                }
            }
        }
        return jugador;
    }
    
    /**
     * Método tipo Jugador que tiene la finalidad de buscar el arquero con menos goles en la liga
     * @return un jugador, el cual tiene la posición de arquero
     */
    public Jugador arqueroMenosGoles(){
        Jugador arquero=null;
        int menor=Integer.MAX_VALUE;
        for(Equipo equipoActual: this.getMisEquipos()){
            if(equipoActual.getGolesEnContra()<menor){
                menor=equipoActual.getGolesEnContra();
                for(Jugador jugadorActual: equipoActual.getMisJugadores()){
                    if(jugadorActual.getPosicion().equalsIgnoreCase("arquero")){
                        arquero=jugadorActual;
                    }
                }
            }
        }
        return arquero;
    }
    
    /**
     * Método con la finalidad de retornar el estadio donde se marcaron más goles en toda la liga
     * @return String con el nombre del estadio
     */
    public String nombreEstadioMasGoles(){
        String nombre="";
        int mayor=Integer.MIN_VALUE;
        for(Estadio estadioActual: this.getMisEstadios()){
            int suma=0;
            for(Partido partidoActual: estadioActual.getMisPartidos()){
                suma=suma+(partidoActual.getGolesLocal()+partidoActual.getGolesVisitante());
                }
            if(suma>mayor){
                mayor=suma;
                nombre=estadioActual.getNombre();
            }
        }
        return nombre;
    }
    
   
    
    /**
     * Este método tiene la finalidad de transferir un jugador de un equipo a otro
     * @param cedula Variable necesaria para buscar al jugador que se desea transferir
     * @param idEquipo Variable necesaria para buscar el equipo al que desea ser transferido
     */
    public void transferirJugador(String cedula,String idEquipo){
    try{
        Persona nuevaPersona = this.buscarpersonaNueva(cedula);
        Equipo nuevoEquipo = this.buscarEquipo(idEquipo);
 
        if (nuevaPersona instanceof Jugador){
            for(Equipo equipoActual : this.getMisEquipos()){
                for(Jugador jugadorActual : equipoActual.getMisJugadores()){
                    if (jugadorActual.getCedula().equals(cedula)){
                        equipoActual.getMisJugadores().remove(jugadorActual);
                    }
                }
            }
        }
        
        for (Equipo equipoActual : this.getMisEquipos()) {
            if (equipoActual.getId().equals(idEquipo)) {
                if (nuevaPersona instanceof Jugador){
                    boolean esta=this.buscarjugadorEquipos(nuevaPersona.getCedula());
                    boolean otra=equipoActual.buscarjugador(nuevaPersona.getCedula());
                    if (esta==false&&otra==false){
                    equipoActual.agregarJugador((Jugador) nuevaPersona);
                    }
                } 
            }
        }
    }catch(Exception e){
            System.out.println("Something went wrong");
        }
    }
    
    /**
     * Método para crear un equipo debido a la relación de asociación
     * @param id Parámetro necesario para crear un equipo
     * @param nombre Parámetro necesario para crear un equipo
     * @param añoFundacion Parámetro necesario para crear un equipo
     * @param numeroTitulosN Parámetro necesario para crear un equipo
     * @param numeroTitulosI Parámetro necesario para crear un equipo
     * @param puntos Parámetro necesario para crear un equipo
     * @param golesAFavor Parámetro necesario para crear un equipo
     * @param golesEnContra Parámetro necesario para crear un equipo
     * @param partidosJugados Parámetro necesario para crear un equipo
     */
    public void crearEquipo(String id,String nombre,int añoFundacion,int numeroTitulosN,int numeroTitulosI,int puntos,int golesAFavor,int golesEnContra,int partidosJugados)
    {
        Equipo nuevoEquipo = new Equipo(id, nombre, añoFundacion, numeroTitulosN, numeroTitulosI, puntos, golesAFavor, golesEnContra, partidosJugados);
        this.getMisEquipos().add(nuevoEquipo);
    }
    
    /**
     * Método booleano para buscar una persona dentro de la liga
     * @param id Se requiere el parámetro para buscar la persona
     * @return se retorna una variable falsa si no existe y verdadera si sí existe la persona buscada
     */
    public boolean buscarpersona (String id)
    {
        boolean existe = false; 
        
        for(Equipo equipoActual : this.getMisEquipos())
        {
            for (Jugador nuevojugador: equipoActual.getMisJugadores()) 
            {
                if (nuevojugador.getCedula().equals(id))
                {
                    existe = true; 
                }
            }
        }
        
        return existe; 
    }
    
    /**
     * Método el cual tiene la finalidad de buscar un mánager y eliminar su relación con un jugador 
     * @param cedulaM Cédula del manager para poder encontrarlo
     * @param nuevoJugador El jugador el cual ya no tendrá relación con el manager
     */
    public void buscarmanageryeliminarjugador (String cedulaM, Jugador nuevoJugador)
    {
        for(Persona nuevaPersona : this.getMisPersonas())
        {
            if (nuevaPersona instanceof Manager)
            {
                if(nuevaPersona.getCedula().equals(cedulaM))
                {
                    ((Manager) nuevaPersona).getMisJugadores().remove(nuevoJugador);
                }
            }
        }
    }
  
    /**
     * Método funcional para buscar una persona
     * @param id parámetro requerido para encontrar la persona
     * @return variable booleana, falsa si no existe y verdadera si existe
     */
    public boolean buscarpersonaActual (String id)
    {
        boolean existe = false; 
        
        for(Persona personaActual : this.getMisPersonas())
        {
            if (personaActual.getCedula().equals(id)) 
            {
                existe = true;
            }
        }
        return existe; 
    }
    
    /**
     * Método tipo persona para buscar una persona nueva
     * @param id parámetro necesario para buscar la persona
     * @return variable tipo persona
     */
    public Persona buscarpersonaNueva (String id)
    {
        Persona personaNueva = null; 
        
        for(Persona personaActual : this.getMisPersonas())
        {
            if (personaActual.getCedula().equals(id)) 
            {
                personaNueva = personaActual; 
            }
        }
        return personaNueva; 
    }
    
    /**
     * Método funcional para eliminar una persona de la liga
     * @param cedula cédula de la persona para buscarla
     * @return variable booleana, verdadera si se eliminó, falsa si no
     */
    public boolean eliminarpersona (String cedula)
    {
        boolean hecho = false; 
        
        Persona nuevapersona = this.buscarpersonaNueva(cedula);
        
        if (nuevapersona != null)
        {
            this.getMisPersonas().remove(nuevapersona);
            
            hecho = true; 
        }
        
        return hecho; 
    }
    
    /**
     * Método para buscar un estadio en la liga
     * @param identificador del estadio necesaria para encontrarlo  
     * @return objeto tipo estadio 
     */
    public Estadio buscarEstadio (String identificador)
    {
        Estadio nuevoEstadio = null; 
        
        for (Estadio estadioActual : this.getMisEstadios()) 
        {
            if (estadioActual.getId().equals(identificador))
            {
                nuevoEstadio = estadioActual; 
            }
        }
        
        return nuevoEstadio; 
    }
    
    /**
     * Método para eliminar el equipo de la liga
     * @param identificador del equipo necesario para buscarlo
     * @return boolean, falso si no se elimino, verdadero si se pudo
     */
    public boolean eliminarEquipo(String identificador)
    {
        boolean eliminado = false; 
        
        for (Equipo equipoActual : this.getMisEquipos()) 
        {
            if(equipoActual.getId().equals(identificador))
            {
                this.getMisEquipos().remove(equipoActual);
                
                eliminado = true; 
            }
        }
        
        return eliminado;
    }
    
    /**
     * Método para buscar un equipo en la liga
     * @param identificador del equipo necesaria para encontrarlo
     * @return objeto tipo equipo
     */
    public Equipo buscarEquipo (String identificador)
    {
        Equipo equipoActual = null; 
        
        for (Equipo equipoActual2 : this.getMisEquipos()) 
        {
            if(equipoActual2.getId().equals(identificador))
            {
                equipoActual = equipoActual2; 
            }
        }
        
        return equipoActual;
    }
    
    /**
     * Método funcional para eliminar un estadio de la liga
     * @param identificador necesario para buscar el estadio
     * @return boolean, verdadero si se pudo eliminar, falso si no
     */
    public boolean eliminarEstadio (String identificador)
    {
        boolean eliminado = false; 
        
        for (Estadio estadioActual : this.getMisEstadios()) 
        {
            if (estadioActual.getId().equals(identificador))
            {
                this.getMisEstadios().remove(estadioActual);
                
                eliminado = true; 
            }
        }
        
        return eliminado; 
    }
    
    /**
     * Método void para buscar un equipo y agregar un pártido a este
     * @param nuevopartido clase partido para agregar al equipo
     * @param identificador del equipo para poder encontrarlo
     */
    public void buscarequipoyagregarpartido(Partido nuevopartido, String identificador)
    {
        for (Equipo equipoActual : this.getMisEquipos()) 
        {
            if(equipoActual.getId().equals(identificador))
            {
                equipoActual.getMisPartidos().add(nuevopartido);
            }
        }
    }
         
    /**
     * Método void para buscar un jugador y eliminarlo de la liga
     * @param identificador del jugador necesario para buscarlo
     */
    public void buscarJugadoryeliminar (String identificador)
    {
        for (Persona personaActual : this.getMisPersonas()) 
        {
            if(personaActual instanceof Manager)
            {
                for (Jugador jugadorActual : ((Manager) personaActual).getMisJugadores()) 
                {
                    if(jugadorActual.getCedula().equals(identificador))
                    {
                        ((Manager) personaActual).getMisJugadores().remove(jugadorActual);
                    }
                }
            }
        } 
    }
    
    /**
     * Método para buscar una jornada en la liga 
     * @param identificador de la jornada necesario para buscarla
     * @return objeto tipo Jornada
     */
    public Jornada buscarJornada (String identificador)
    {
        Jornada nuevaJornada = null; 
        
        for (Jornada jornadaActual : this.getMisJornadas()) 
        {
            if(jornadaActual.getId().equals(identificador))
            {
                nuevaJornada = jornadaActual; 
            }
        }
        
        return nuevaJornada; 
    }
    
    /**
     * Método boolean para eliminar una jornada de la liga
     * @param identificador necesario para buscar la jornada
     * @return retorna un booleano, falso si no se eliminó y verdadero si se pudo eliminar
     */
    public boolean eliminarJornada (String identificador)
    {
        boolean eliminado = false; 
        
        for (Jornada jornadaActual : this.getMisJornadas()) 
        {
            if(jornadaActual.getId().equals(identificador))
            {
                this.getMisJornadas().remove(jornadaActual);
                
                eliminado = true; 
            }
        }
        
        for (Partido partidoActual : this.getMispartidos())
        {
            if(partidoActual.getRefJornada().getId().equals(identificador))
            {
                partidoActual.setRefJornada(null);
            }
        }
        
        return eliminado; 
    }
    
    /**
     * Método boolean para buscar un jugador en los equipos
     * @param cedula cédula del jugador necesaria para buscarlo 
     * @return boolean, falso si no se encontró y verdadero si se pudo
     */
    public boolean buscarjugadorEquipos (String cedula)
    {
        boolean esta = false; 
        
        for (Equipo equipoNuevo : this.getMisEquipos()) 
        {
            for (Jugador jugadorActual : equipoNuevo.getMisJugadores()) 
            {
                if (jugadorActual != null)
                {
                    if (jugadorActual.getCedula().equals(cedula)) 
                    {
                        esta = true;
                    }
                }
            }
        }
        
        return esta; 
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
     * @return the misPersonas
     */
    public LinkedList<Persona> getMisPersonas() {
        return misPersonas;
    }

    /**
     * @param misPersonas the misPersonas to set
     */
    public void setMisPersonas(LinkedList<Persona> misPersonas) {
        this.misPersonas = misPersonas;
    }

    /**
     * @return the misJornadas
     */
    public LinkedList<Jornada> getMisJornadas() {
        return misJornadas;
    }

    /**
     * @param misJornadas the misJornadas to set
     */
    public void setMisJornadas(LinkedList<Jornada> misJornadas) {
        this.misJornadas = misJornadas;
    }

    /**
     * @return the misEquipos
     */
    public LinkedList<Equipo> getMisEquipos() {
        return misEquipos;
    }

    /**
     * @param misEquipos the misEquipos to set
     */
    public void setMisEquipos(LinkedList<Equipo> misEquipos) {
        this.misEquipos = misEquipos;
    }

    /**
     * @return the misEstadios
     */
    public LinkedList<Estadio> getMisEstadios() {
        return misEstadios;
    }

    /**
     * @param misEstadios the misEstadios to set
     */
    public void setMisEstadios(LinkedList<Estadio> misEstadios) {
        this.misEstadios = misEstadios;
    }

    /**
     * @return the mispartidos
     */
    public LinkedList<Partido> getMispartidos() {
        return mispartidos;
    }

    /**
     * @param mispartidos the mispartidos to set
     */
    public void setMispartidos(LinkedList<Partido> mispartidos) {
        this.mispartidos = mispartidos;
    }
}
