/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VistaControlador;

import Modelos.Aficionado;
import Modelos.Arbitro;
import Modelos.Equipo;
import Modelos.Estadio;
import Modelos.Jornada;
import Modelos.Jugador;
import Modelos.Liga;
import Modelos.Manager;
import Modelos.Partido;
import Modelos.Persona;
import Modelos.Tecnico;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carla Q y Jassy P.
 */
public class InterfazLiga extends javax.swing.JFrame {

    Liga miliga;

    LinkedList <Persona> misPersonasTemporales;
    LinkedList <Jugador> misjugadoresTemporales;
    Estadio miestadiotemporal;
    Arbitro miarbitrotemporal;
    Jornada mijornadatemporal;
    Equipo miequipoLocalTemporal; 
    Equipo miequipoVisitanteTemporal; 
    

    /**
     * Creates new form InterfazLiga
     */

    public InterfazLiga() {
        initComponents();
        //this.miliga = new Liga("92381D093", "CHAMPIONS LEAGUE");
        this.esjugador();
        this.cargarSesion();
        this.metodo1seleccionado();
        this.misPersonasTemporales = new LinkedList<>();
        this.misjugadoresTemporales = new LinkedList<>();
        this.miarbitrotemporal = miarbitrotemporal;
        this.miestadiotemporal = miestadiotemporal;
        this.mijornadatemporal = mijornadatemporal;
        this.miequipoVisitanteTemporal = miequipoVisitanteTemporal;
        this.miequipoLocalTemporal = miequipoLocalTemporal;
        
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                eventoCierre();
            }
        });
    }
    
    /**
     * Este metodo avisa el momento de cierre 
     */
    public void eventoCierre ()
    {
        System.out.println("Cerrando");
        
        try {
            ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("taller.obj"));
            salida.writeObject(this.miliga);
            salida.close();
        } catch (Exception ex) {
            System.out.println("" + ex);
        }
    }
    
    /**
     * Este metodo carga la sesión actual con los datos y las relaciones establecidas en la sesión 
     * anterior
     */
    public void cargarSesion ()
    {
        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("taller.obj"));
            this.miliga = (Liga) entrada.readObject();
            this.actualizarcomboPersona();
            this.actualizarcomboJugadoresparaManager();
            this.actualiazarcomboArbitroParaPartido();
            this.actualizarcomboEquiposdePartido();
            this.actualizarcomboJornadasparaPartidos();
            this.actualizarcomboEstadioparaPartidos();
            this.actualizarTablaEquipos();
            this.actualizarTablaJornadas();
            this.actualizarTablaPersonas();
            this.actualizarTabladeEstadios();
            this.actualizarTablaPartidos();
        } catch (Exception ex) {
            System.out.println(""+ex);
        }
    }
    
    /**
     * Este método oculta los paneles y deselecciona los botones de las persona que no son jugadores,
     * a su vez, visibiliza el panel de jugadores y selecciona el botón del mismo 
     */
    public void esjugador() {
        this.RBjugador.setSelected(true);
        this.jPanelJugador.setVisible(true);
        this.jPanelArbitro.setVisible(false);
        this.RBaficionado.setSelected(false);
        this.jPanelAficionado.setVisible(false);
        this.RBarbitro.setSelected(false);
        this.jPanelArbitro.setVisible(false);
        this.RBmanager.setSelected(false);
        this.jPanelManager.setVisible(false);
        this.jPanelTecnico.setVisible(false);
        this.RBtecnico.setSelected(false);
    }

    /**
     * Este método oculta los paneles y deselecciona los botones de las persona que no son managers,
     * a su vez, visibiliza el panel de manager y selecciona el botón del mismo 
     */
    public void esmanager() {
        this.RBmanager.setSelected(true);
        this.jPanelManager.setVisible(true);
        this.RBaficionado.setSelected(false);
        this.jPanelAficionado.setVisible(false);
        this.RBarbitro.setSelected(false);
        this.jPanelArbitro.setVisible(false);
        this.RBjugador.setSelected(false);
        this.jPanelJugador.setVisible(false);
        this.jPanelTecnico.setVisible(false);
        this.RBtecnico.setSelected(false);
    }
    
    /**
     * Este método oculta los paneles y deselecciona los botones de las persona que no son técnicos,
     * a su vez, visibiliza el panel de técnico y selecciona el botón del mismo 
     */
    public void estecnico() {
        this.RBmanager.setSelected(false);
        this.jPanelManager.setVisible(false);
        this.RBaficionado.setSelected(false);
        this.jPanelAficionado.setVisible(false);
        this.RBarbitro.setSelected(false);
        this.jPanelArbitro.setVisible(false);
        this.RBjugador.setSelected(false);
        this.jPanelJugador.setVisible(false);
        this.jPanelTecnico.setVisible(true);
        this.RBtecnico.setSelected(true);
    }
    
    /**
     * Este método oculta los paneles y deselecciona los botónes de las persona que no son árbitros,
     * a su vez, visibiliza el panel de árbitro y selecciona el botón del mismo 
     */
    public void esarbitro() {
        this.RBmanager.setSelected(false);
        this.jPanelManager.setVisible(false);
        this.RBaficionado.setSelected(false);
        this.jPanelAficionado.setVisible(false);
        this.RBarbitro.setSelected(true);
        this.jPanelArbitro.setVisible(true);
        this.RBjugador.setSelected(false);
        this.jPanelJugador.setVisible(false);
        this.jPanelTecnico.setVisible(false);
        this.RBtecnico.setSelected(false);
    }
    
    /**
     * Este método oculta los paneles y deselecciona los botónes de las persona que no son aficionados,
     * a su vez, visibiliza el panel de aficionado y selecciona el botón del mismo 
     */
    public void esaficionado() {
        this.RBmanager.setSelected(false);
        this.jPanelManager.setVisible(false);
        this.RBaficionado.setSelected(true);
        this.jPanelAficionado.setVisible(true);
        this.RBarbitro.setSelected(false);
        this.jPanelArbitro.setVisible(false);
        this.RBjugador.setSelected(false);
        this.jPanelJugador.setVisible(false);
        this.jPanelTecnico.setVisible(false);
        this.RBtecnico.setSelected(false);
    }
    
    /**
     * Este método oculta los paneles y deselecciona los botónes de los metodos que no son el
     * que estoy solicitando  
     */
    public void metodo1seleccionado(){
            this.RBmetodo1.setSelected(true);
            this.jPanelmetodo1.setVisible(true);
            this.RBmetodo2.setSelected(false);
            this.jPanelmetodo2.setVisible(false);
            this.RBmetodo3.setSelected(false);
            this.jPanelmetodo3.setVisible(false);
            this.RBmetodo4.setSelected(false);
            this.jPanelmetodo4.setVisible(false);
            this.RBmetodo5.setSelected(false);
            this.jPanelmetodo5.setVisible(false);
            this.RBmetodo6.setSelected(false);
            this.jPanelmetodo6.setVisible(false);
            this.RBmetodo7.setSelected(false);
            this.jPanelmetodo7.setVisible(false);
            this.RBmetodo8.setSelected(false);
            this.jPanelmetodo8.setVisible(false);
            this.RBmetodo9.setSelected(false);
            this.jPanelmetodo9.setVisible(false);
    }
    
    /**
     * Método que permite crear un archivo plano
     * @param contenido String el cual contiene la información que quiera ser adjuntada en el archivo plano
     * @param extension Formato en el cual se desea el archivo plano
     */
    public void escribirArchivoPlano (String contenido, String extension)
    {
        try {
            JFileChooser file = new JFileChooser();
            
            file.showSaveDialog(this);
            
            //String ruta = ""+ file.getCurrentDirectory();
            
            try (BufferedWriter save = new BufferedWriter (new FileWriter(file.getSelectedFile().toString()+"."+extension)))
            {
                save.write(contenido);
            }
            
            JOptionPane.showMessageDialog(null, "El archivo se ha guardado exitosamente", 
                                                "información", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "El archivo no se ha guardado", 
                                                "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Este método actualiza la tabla de personas, con cada una de las que son creadas e 
     * ingresadas en la liga, teniendo en cuenta que pueden ser aficionados, árbitros
     * jugadores, managers o técnicos 
     */
    public void actualizarTablaPersonas() 
    {
        String nombresColumnas[] = {"Cedula", "Nombre", "Apellido", "Edad", "Tipo de persona", "ID Partido", "Cédula jugadores", "ID equipo", "Cedula de manager"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblPersonas.setModel(miModelo);

        for (Persona personaActual : this.miliga.getMisPersonas()) 
        {
            String fila[] = new String[nombresColumnas.length];
            fila[0] = personaActual.getCedula();
            fila[1] = personaActual.getNombre();
            fila[2] = personaActual.getApellido();
            fila[3] = String.valueOf(personaActual.getEdad());
            
            if (personaActual instanceof Jugador) 
            {
                fila[4] = "Jugador";

                if (((Jugador) personaActual).getMiManager() != null) 
                {
                    String idManager = ((Jugador) personaActual).getMiManager().getCedula();

                    fila[8] = idManager;
                }
                
                                            
                this.actualizarcomboJugadoresparaManager();

            } else if (personaActual instanceof Tecnico) {
                fila[4] = "Tecnico";

            } else if (personaActual instanceof Manager) {
                fila[4] = "Manager";

                String palabra = ((Manager) personaActual).jugadoresmanager();

                fila[6] = palabra;

                if (((Manager) personaActual).getMiEquipo() != null) {
                    fila[7] = ((Manager) personaActual).getMiEquipo().getId();
                }
            } else if (personaActual instanceof Aficionado) {
                fila[4] = "Aficionado";

                if (((Aficionado) personaActual).getMiEquipo() != null) {
                    fila[6] = ((Aficionado) personaActual).getMiEquipo().getId();
                }
            } else if (personaActual instanceof Arbitro) 
            {
                fila[4] = "Arbitro";
            }
            miModelo.addRow(fila);
        }
    }

    /**
     * Este método limpia la información (en la interfaz gráfica) de la persona que 
     * ha sido creada, permitiendo ingresar datos nuevos de otra persona 
     */
    public void limpiarPersona(String cedula) {
        Persona personaActual = this.miliga.buscarpersonaNueva(cedula);

        if (personaActual != null) {
            this.Textcedulapersona.setText("");
            this.Textnombrepersona.setText("");
            this.Textapellidopersona.setText("");
            this.jSedadpersona.setValue(0);

            if (personaActual instanceof Jugador) {
                this.esjugador();
                this.TextNacionalidadJugador.setText("");
                this.TextPosicionJugador.setText("");
                this.jSNumerogolesJugador.setValue(0);
                this.TextSalarioJugador.setText("");
                this.actualizarTablaManagerdeJugador(null);

            } else if (personaActual instanceof Manager) {
                this.esmanager();
                this.jSañosafiliacionManager.setValue(0);
                this.misjugadoresTemporales = new LinkedList<>();
                this.actualizarTablaJugadoresSeleccionados();

            } else if (personaActual instanceof Tecnico) {
                this.estecnico();
                this.jSAñosexperienciaTecnico.setValue(0);
                this.TextSalarioTecnico.setText("");
            } else if (personaActual instanceof Aficionado) {
                this.esaficionado();
                this.jSañosfidelidadAficionado.setValue(0);
                this.RBAbonadosi.setSelected(false);
                this.RBabonadono.setSelected(false);

            } else if (personaActual instanceof Arbitro) {
                this.esarbitro();
                this.RBFIFAsi.setSelected(false);
                this.RBFIFAno.setSelected(false);
                this.actualizarPartidoparaArbitro(null);
            }
        }
    }

    /**
     * Este método actualiza la tabla de las personas seleccionadas para ser 
     * ingresadas en un equipo x, teniendo en cuenta que pueden ser jugadores, 
     * managers, técnicos o aficionados 
     */
    public void actualizarTablaPersonasSeleccionadas() {
        String nombresColumnas[] = {"Cedula", "Nombre", "Apellido", "Edad", "Tipo de persona", "Nacionalidad", "Posición", "N° goles", "Salario", "Años de experiencia", "Año afiliación", "Años de fidelidad"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblPersonasEquipo.setModel(miModelo);

        for (Persona personaActual : this.misPersonasTemporales) 
        {
            if (personaActual != null)
            {
                String fila[] = new String[nombresColumnas.length];

                fila[0] = personaActual.getCedula();

                fila[1] = personaActual.getNombre();

                fila[2] = personaActual.getApellido();

                fila[3] = String.valueOf(personaActual.getEdad());

                if (personaActual instanceof Jugador) {
                    fila[4] = "Jugador";

                    fila[5] = ((Jugador) personaActual).getNacionalidad();

                    fila[6] = ((Jugador) personaActual).getPosicion();

                    fila[7] = String.valueOf(((Jugador) personaActual).getNumeroGoles());

                    fila[8] = String.valueOf(((Jugador) personaActual).getSalario());
                } else if (personaActual instanceof Tecnico) {
                    fila[4] = "Tecnico";

                    fila[8] = String.valueOf(((Tecnico) personaActual).getSalario());

                    fila[9] = String.valueOf(((Tecnico) personaActual).getAñosExp());
                } else if (personaActual instanceof Manager) {
                    fila[4] = "Manager";

                    fila[10] = String.valueOf(((Manager) personaActual).getAñosAfiliacion());
                } else if (personaActual instanceof Aficionado) {
                    fila[4] = "Aficionado";

                    fila[11] = String.valueOf(((Aficionado) personaActual).getAñosFidelidad());
                }

                miModelo.addRow(fila);
            }
            
        }
    }

    /**
     * Este método actualiza la tabla de equipos con los que han sido creados y 
     * agregados a la liga
     */
    public void actualizarTablaEquipos() {
        String nombresColumnas[] = {"Id", "Nombre", "Año de fundación", "N° títulos nacionales", "N° títulos internacionales", "Puntos", "Goles a favor", "Goles en contra", "Partidos jugados", "Manager", "Aficionados", "Técnico", "ID jugador"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblEquipos.setModel(miModelo);

        for (Equipo equipoActual : this.miliga.getMisEquipos()) {
            String fila[] = new String[nombresColumnas.length];

            fila[0] = equipoActual.getId();

            fila[1] = equipoActual.getNombre();

            String añofundacion = String.valueOf(equipoActual.getAñoFundacion());

            fila[2] = añofundacion;

            String titulosNaciones = String.valueOf(equipoActual.getNumeroTitulosN());

            fila[3] = titulosNaciones;

            String titulosInternaciones = String.valueOf(equipoActual.getNumeroTitulosI());

            fila[4] = titulosInternaciones;

            String Puntos = String.valueOf(equipoActual.getPuntos());

            fila[5] = Puntos;

            String GolesAFavor = String.valueOf(equipoActual.getGolesAFavor());

            fila[6] = GolesAFavor;

            String GolesEnContra = String.valueOf(equipoActual.getGolesEnContra());

            fila[7] = GolesEnContra;

            String PartidosJugados = String.valueOf(equipoActual.getPartidosJugados());

            fila[8] = PartidosJugados;
            
            if (equipoActual.getMiManager() != null)
            {
                fila[9] = equipoActual.getMiManager().getCedula();
            }

            String palabra1 = "";

            for (Aficionado aficionadoActual : equipoActual.getMisAficionados()) {
                palabra1 = palabra1 + aficionadoActual.getCedula() + ", ";
            }

            fila[10] = palabra1;

            String palabra = "";
            
            if (equipoActual.getMiTecnico() != null)
            {
                fila[11] = equipoActual.getMiTecnico().getCedula();
            }
            
            for (Jugador jugadorActual : equipoActual.getMisJugadores()) 
            {
                palabra = palabra + jugadorActual.getCedula() + ",";
            }

            fila[12] = palabra;

            miModelo.addRow(fila);
        }
    }
    
    /**
     * Este método actualiza la tabla del equipo visitante en partido, con toda
     * la información del equipo respectivo
     */
    public void actualizarTablaEquiposdePartidoV () 
    {
        String nombresColumnas[] = {"Id", "Nombre", "Año de fundación", "N° títulos nacionales", "N° títulos internacionales", "Puntos", "Goles a favor", "Goles en contra", "Partidos jugados"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblEquiposdePartido.setModel(miModelo);
        
        Equipo equipoActual = this.miequipoVisitanteTemporal;

        try 
        {
            String fila[] = new String[nombresColumnas.length];
            
            if (equipoActual != null)
            {
                fila[0] = equipoActual.getId();

                fila[1] = equipoActual.getNombre();

                String añofundacion = String.valueOf(equipoActual.getAñoFundacion());

                fila[2] = añofundacion;

                String titulosNaciones = String.valueOf(equipoActual.getNumeroTitulosN());

                fila[3] = titulosNaciones;

                String titulosInternaciones = String.valueOf(equipoActual.getNumeroTitulosI());

                fila[4] = titulosInternaciones;

                String Puntos = String.valueOf(equipoActual.getPuntos());

                fila[5] = Puntos;

                String GolesAFavor = String.valueOf(equipoActual.getGolesAFavor());

                fila[6] = GolesAFavor;

                String GolesEnContra = String.valueOf(equipoActual.getGolesEnContra());

                fila[7] = GolesEnContra;

                String PartidosJugados = String.valueOf(equipoActual.getMisPartidos().size());

                fila[8] = PartidosJugados;
            }else 
            {
                fila = this.porsinohaynada(nombresColumnas.length);
            }
            
            miModelo.addRow(fila);
        } catch (Exception e) 
        {
            System.out.println(e);
        }
    }
    
    /**
     * Este método actualiza la tabla del equipo local en partido, con toda
     * la información del equipo respectivo
     */
    public void actualizarTablaEquiposdePartidoL () 
    {
        String nombresColumnas[] = {"Id", "Nombre", "Año de fundación", "N° títulos nacionales", "N° títulos internacionales", "Puntos", "Goles a favor", "Goles en contra", "Partidos jugados"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblEquiposdePartido1.setModel(miModelo);
        
        Equipo equipoActual = this.miequipoLocalTemporal;

        try 
        {
            String fila[] = new String[nombresColumnas.length];
            
            if (equipoActual != null)
            {
                fila[0] = equipoActual.getId();

                fila[1] = equipoActual.getNombre();

                String añofundacion = String.valueOf(equipoActual.getAñoFundacion());

                fila[2] = añofundacion;

                String titulosNaciones = String.valueOf(equipoActual.getNumeroTitulosN());

                fila[3] = titulosNaciones;

                String titulosInternaciones = String.valueOf(equipoActual.getNumeroTitulosI());

                fila[4] = titulosInternaciones;

                String Puntos = String.valueOf(equipoActual.getPuntos());

                fila[5] = Puntos;

                String GolesAFavor = String.valueOf(equipoActual.getGolesAFavor());

                fila[6] = GolesAFavor;

                String GolesEnContra = String.valueOf(equipoActual.getGolesEnContra());

                fila[7] = GolesEnContra;

                String PartidosJugados = String.valueOf(equipoActual.getMisPartidos().size());

                fila[8] = PartidosJugados;
            }else 
            {
                fila = this.porsinohaynada(nombresColumnas.length);
            }
            
            miModelo.addRow(fila);
        } catch (Exception e) 
        {
            System.out.println(e);
        }
    }


    /**
     * Este método actualiza el ccombo box en equipo, con  la información de los 
     * jugadores, managers, técnicos y aficionado que se van creando y agregando a 
     * la liga
     */
    public void actualizarcomboPersona() {
        this.ComboJugador.removeAllItems();

        for (Persona personaActual : this.miliga.getMisPersonas()) {
            if (personaActual instanceof Jugador) {
                String palabra = ((Jugador) personaActual).getCedula() + ", Jugador";

                this.ComboJugador.addItem(palabra);
            } else if (personaActual instanceof Tecnico) {
                String palabra = ((Tecnico) personaActual).getCedula() + ", Tecnico";

                this.ComboJugador.addItem(palabra);
            } else if (personaActual instanceof Manager) {
                String palabra = ((Manager) personaActual).getCedula() + ", Manager";

                this.ComboJugador.addItem(palabra);
            } else if (personaActual instanceof Aficionado) {
                String palabra = ((Aficionado) personaActual).getCedula() + ", Aficionado";

                this.ComboJugador.addItem(palabra);
            }
        }
    }

    /**
     * Este método actualiza la tabla de los partidos a medida que van siendo creados
     */
    public void actualizarTablaPartidos() 
    {
        String nombresColumnas[] = {"Id", "Fecha", "Goles del local", "Goles de los visitante", "Cédula del arbitro", "Id del estadio", "Id de la jornada", "Id equipo local","Puntos", "Id equipo visitante", "Puntos"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblPartidos.setModel(miModelo);

        for (Partido partidoActual : this.miliga.getMispartidos()) 
        {
            if (partidoActual != null)
            {
                 String fila[] = new String[nombresColumnas.length];

                
                    fila[0] = partidoActual.getId();

                    fila[1] = partidoActual.getFecha();

                    fila[2] = String.valueOf(partidoActual.getGolesLocal());

                    fila[3] = String.valueOf(partidoActual.getGolesVisitante());

                    if (partidoActual.getMiArbitro() != null) {
                        fila[4] = partidoActual.getMiArbitro().getCedula();
                    }
                    
                    if(partidoActual.getMiEstadio() != null)
                    {
                        fila[5] = partidoActual.getMiEstadio().getId();
                    }
                    
                    if (partidoActual.getRefJornada() != null)
                    {
                        fila[6] = partidoActual.getRefJornada().getId();
                    }

                    if (partidoActual.getRefEquipoLocal() != null) {
                        fila[7] = partidoActual.getRefEquipoLocal().getId();
                        fila[8] = String.valueOf(partidoActual.getRefEquipoLocal().getPuntos());
                    }

                    if (partidoActual.getRefEquipoVisitante() != null) {
                        fila[9] = partidoActual.getRefEquipoVisitante().getId();
                        fila[10] = String.valueOf(partidoActual.getRefEquipoVisitante().getPuntos());
                    }

                    miModelo.addRow(fila);
            }  
           
        }
    }

    /**
     * Este método actualiza la tabla del árbitro en partido, con toda la información de la persona
     */
    public void actualizartablaArbitrodePartido() 
    {
        String nombresColumnas[] = {"Cédula", "Nombre", "Apellido", "Edad", "FIFA"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblArbitrodePartido.setModel(miModelo);
        
        String fila[] = new String[nombresColumnas.length];

        try 
        {
            if (this.miarbitrotemporal != null) 
            {
                fila[0] = this.miarbitrotemporal.getCedula();

                fila[1] = this.miarbitrotemporal.getNombre();

                fila[2] = this.miarbitrotemporal.getApellido();

                String edad = String.valueOf(this.miarbitrotemporal.getEdad());

                fila[3] = edad;

                if (this.miarbitrotemporal.isFifa()) {
                    fila[4] = "true";

                    System.out.println(this.miarbitrotemporal.getCedula() + "5");
                } else {
                    System.out.println(this.miarbitrotemporal.getCedula() + "6");

                    fila[4] = "false";
                }
            }else 
            {
                fila = this.porsinohaynada(nombresColumnas.length);
            }
            
            miModelo.addRow(fila);
        } catch (Exception e) 
        {
            System.out.println(e);
        }

    }

    /**
     * Este método actualiza la tabla del estadio en partido, con toda la información del estadio
     */
    public void actualizartablaEstadiodePartido() {
        String nombresColumnas[] = {"Id", "Nombre", "Ciudad", "Capacidad"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblEstadioPartido.setModel(miModelo);
        
        if (this.miestadiotemporal != null)
        {
            String fila[] = new String[nombresColumnas.length];

            fila[0] = this.miestadiotemporal.getId();

            fila[1] = this.miestadiotemporal.getNombre();

            fila[2] = this.miestadiotemporal.getCiudad();

            fila[3] = String.valueOf(this.miestadiotemporal.getCapacidad());

            miModelo.addRow(fila);
        }
    }
    
    /**
     * Este método actualiza el ccombo box en partido, con  la información de los 
     * árbitros que se van creando y agregando a la liga
     */
    public void actualiazarcomboArbitroParaPartido() 
    {
        this.ComboArbitros.removeAllItems();

        for (Persona personaActual : this.miliga.getMisPersonas()) 
        {
            if (personaActual instanceof Arbitro) 
            {
                String palabra = ((Arbitro) personaActual).getCedula() + ", " + ((Arbitro) personaActual).getNombre();

                this.ComboArbitros.addItem(palabra);
            }
        }
    }

    /**
     * Este método actualiza el ccombo box en partido, con  la información de los 
     * estadios que van siendo creados y agregados a la liga
     */
    public void actualizarcomboEstadioparaPartidos() {
        this.ComboEstadioparaPartido.removeAllItems();

        for (Estadio estadioActual : this.miliga.getMisEstadios()) {
            String palabra = estadioActual.getId() + ", " + estadioActual.getNombre();

            this.ComboEstadioparaPartido.addItem(palabra);
        }
    }

    /**
     * Este método actualiza el ccombo box en partido, con  la información de las 
     * jornadas que van siendo creadas y agregadas a la liga
     */
    public void actualizarcomboJornadasparaPartidos() {
        this.ComboJornadaparaPartido.removeAllItems();

        for (Jornada jornadaActual : this.miliga.getMisJornadas()) {
            String palabra = jornadaActual.getId() + ", " + jornadaActual.getNumero();

            this.ComboJornadaparaPartido.addItem(palabra);
        }
    }

    /**
     * Este método actualiza la tabla de las jornadas a medida que van siendo creadas
     */
    public void actualizarTablaJornadas() {
        String nombresColumnas[] = {"ID", "Número", "Eslogan", "Id partidos"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblJornadas.setModel(miModelo);

        for (Jornada jornadaActual : this.miliga.getMisJornadas()) 
        {
            String fila[] = new String[nombresColumnas.length];

            fila[0] = jornadaActual.getId();

            fila[1] = String.valueOf(jornadaActual.getNumero());

            fila[2] = jornadaActual.getEslogan();

            String palabra = jornadaActual.publicarpatidos();

            fila[3] = palabra;

            miModelo.addRow(fila);
        }
    }

    /**
     * Este método actualiza la tabla con la información del partiddo que tiene el
     * árbitro correspondiente
     * @param arbitroActual 
     */
    public void actualizarPartidoparaArbitro(Arbitro arbitroActual) {
        String nombresColumnas[] = {"Id", "Fecha", "Goles del local", "Goles de los visitante"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblParidodeArbitro.setModel(miModelo);
        
        String fila[] = new String[nombresColumnas.length];

        if (arbitroActual.getMiPartido() != null) 
        {
            fila[0] = arbitroActual.getMiPartido().getId();

            fila[1] = arbitroActual.getMiPartido().getFecha();

            fila[2] = String.valueOf(arbitroActual.getMiPartido().getGolesLocal());

            fila[3] = String.valueOf(arbitroActual.getMiPartido().getRefEquipoVisitante());
        }else
        {
            fila = this.porsinohaynada(nombresColumnas.length);
        }
        
        miModelo.addRow(fila);
    }

    /**
     * Este método actualiza la tabla con los estadios que van siendo creados y agregados
     * a la liga 
     */
    public void actualizarTabladeEstadios() 
    {
        String nombresColumnas[] = {"ID", "Nombre", "Ciudad", "Capacidad", "Id partidos"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblEstadios.setModel(miModelo);

        for (Estadio estadioActual : this.miliga.getMisEstadios()) 
        {
            String fila[] = new String[nombresColumnas.length];

            fila[0] = estadioActual.getId();

            fila[1] = estadioActual.getNombre();

            fila[2] = estadioActual.getCiudad();

            fila[3] = String.valueOf(estadioActual.getCapacidad());

            String palabra = estadioActual.publicarpartidos();

            fila[4] = palabra;

            miModelo.addRow(fila);
        }
    }
    
    /**
     * Este método actualiza el ccombo box en manager, con  la información de los 
     * jugadores que se van creando y agregando a la liga
     */
    public void actualizarcomboJugadoresparaManager() 
    {
        this.ComboJugadoresManager.removeAllItems();

        for (int i = 0; i < this.miliga.getMisPersonas().size(); i++) 
        {
            if (this.miliga.getMisPersonas().get(i) instanceof Jugador) 
            {
                Jugador nuevoJugador = (Jugador) this.miliga.getMisPersonas().get(i);
                        
                String palabra = nuevoJugador.getCedula();

                this.ComboJugadoresManager.addItem(palabra);
                
                System.out.println(this.ComboJugadoresManager.getSelectedIndex());
            }
        }
    }

    /**
     * Este método actualiza el ccombo box en manager, con  la información de los 
     * jugadores que han sido seleccionados por el manager 
     */
    public void actualizarTablaJugadoresSeleccionados() {
        String nombresColumnas[] = {"Cédula", "Nombre", "Apellido", "Edad", "Nacionalidad", "Posición", "N° goles", "Salario"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblJugadoresManager.setModel(miModelo);

        for (Jugador jugadorActual : this.misjugadoresTemporales) {
            String fila[] = new String[nombresColumnas.length];

            fila[0] = jugadorActual.getCedula();

            fila[1] = jugadorActual.getNombre();

            fila[2] = jugadorActual.getApellido();

            fila[3] = String.valueOf(jugadorActual.getEdad());

            fila[4] = jugadorActual.getNacionalidad();

            fila[5] = jugadorActual.getPosicion();

            fila[6] = String.valueOf(jugadorActual.getNumeroGoles());

            fila[7] = String.valueOf(jugadorActual.getSalario());

            miModelo.addRow(fila);
        }
    }

    /**
     * Este método actualiza la tabla de manager en el jugador correspondiente
     * @param jugadorActual 
     */
    public void actualizarTablaManagerdeJugador(Jugador jugadorActual) {
        String nombresColumnas[] = {"Cedula", "Nombre", "Apellido", "Edad", "Años de afiliación"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblManagerJugador.setModel(miModelo);

        String fila[] = new String[nombresColumnas.length];

        try {
            if (jugadorActual != null) {
                fila[0] = jugadorActual.getMiManager().getCedula();

                fila[1] = jugadorActual.getMiManager().getNombre();

                fila[2] = jugadorActual.getMiManager().getApellido();;

                fila[3] = String.valueOf(jugadorActual.getMiManager().getEdad());

                fila[4] = String.valueOf(jugadorActual.getMiManager().getAñosAfiliacion());
            } else {
                fila = this.porsinohaynada(nombresColumnas.length);
            }
            miModelo.addRow(fila);

        } catch (Exception e) {

            System.out.println(e);
        }

    }

    /**
     * Este método deja devulve un String del tamaño del valor de la entrada, con 
     * cada casilla vacia, es útil en caso de que se necesite vaciar la tabla 
     * @param exten
     * @return vector de String
     */
    public String[] porsinohaynada(int exten) {
        String fila[] = new String[exten];

        for (int i = 0; i < exten; i++) {
            fila[i] = "";
        }

        return fila;
    }

    /**
     * Este método limpia las entradas en la interfaz de Estadio para que poder 
     * ingresar datos nuevos posteriormente 
     */
    public void limpliarEstadio() {
        this.TextIDEstadio.setText("");
        this.TextNombreCiudadEstadio.setText("");
        this.TextNombreEstadio.setText("");
        this.jSCapacidadEstadio.setValue(0);
        this.actualizarTablaPartidosparaestadio(null);
    }

    /**
     * Este método limpia las entradas en la interfaz de Jornada para que poder 
     * ingresar datos nuevos posteriormente 
     */
    public void limpiarJornada() {
        this.TextIDJornada.setText("");
        this.TextEsloganJornada.setText("");
        this.jSNumeroJornada.setValue(0);
        this.actualizarTablaPartidodeJornada(null);
    }

    /**
     * Este método actualiza la tabla en el partido correspondiente con la 
     * información de la jornada elegida
     */
    public void actualizarTabladeJornadaparaPartido() {
        String nombresColumnas[] = {"Id", "Número", "Eslogan"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblJornadadePartido.setModel(miModelo);

        String fila[] = new String[nombresColumnas.length];

        try {
            if (this.mijornadatemporal != null) {
                fila[0] = mijornadatemporal.getId();

                fila[1] = String.valueOf(mijornadatemporal.getNumero());

                fila[2] = mijornadatemporal.getEslogan();
            } else {
                fila = this.porsinohaynada(nombresColumnas.length);
            }
            miModelo.addRow(fila);

        } catch (Exception e) {

            System.out.println(e);
        }
    }

     /**
      * Este método actualiza la tabla en la jornada correspondiente con la 
      * información de su partido
      * @param nuevajornada 
      */
    public void actualizarTablaPartidodeJornada(Jornada nuevajornada) {
        String nombresColumnas[] = {"Id", "Fecha", "Goles equipo local", "Goles equipo visitante"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblPartidosporJornada.setModel(miModelo);

        String fila[] = new String[nombresColumnas.length];

        if (nuevajornada != null) {
            for (Partido partidoActual : nuevajornada.getMisPartidos()) {
                try {
                    if (partidoActual != null) {
                        fila[0] = partidoActual.getId();

                        fila[1] = partidoActual.getFecha();

                        fila[2] = String.valueOf(partidoActual.getGolesLocal());

                        fila[3] = String.valueOf(partidoActual.getGolesVisitante());
                    }
                } catch (Exception e) {

                    System.out.println(e);
                }
            }
        } else {
            fila = this.porsinohaynada(nombresColumnas.length);
        }

        miModelo.addRow(fila);
    }

    /**
     * Este método actualiza la tabla en el estadio correspondiente con la 
     * información de su partido
     * @param nuevoEstadio 
     */
    public void actualizarTablaPartidosparaestadio(Estadio nuevoEstadio) 
    {
        String nombresColumnas[] = {"Id", "Fecha", "Goles equipo local", "Goles equipo visitante"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblPartidosdeEstadio.setModel(miModelo);

        String fila[] = new String[nombresColumnas.length];

        if (nuevoEstadio != null) 
        {
            for (Partido partidoActual : nuevoEstadio.getMisPartidos()) 
            {
                try 
                {
                    if (partidoActual != null) 
                    {
                        fila[0] = partidoActual.getId();

                        fila[1] = partidoActual.getFecha();

                        fila[2] = String.valueOf(partidoActual.getGolesLocal());

                        fila[3] = String.valueOf(partidoActual.getGolesVisitante());
                    }
                } catch (Exception e) {

                    System.out.println(e);
                }
            }
        } else {
            fila = this.porsinohaynada(nombresColumnas.length);
        }

        miModelo.addRow(fila);
    }

    /**
     * Este método limpia las entradas en la interfaz de Partido para que poder 
     * ingresar datos nuevos posteriormente 
     */
    public void limpiarpartido() {
        this.TextIDPartido.setText("");
        this.TextFechaPartido.setText("");
        this.jSGolesdeEquipoLocal.setValue(0);
        this.jSGolesEquipoVisitante.setValue(0);
        this.mijornadatemporal = null;
        this.actualizarTabladeJornadaparaPartido();
        this.miarbitrotemporal = null;
        this.actualizartablaArbitrodePartido();
        this.miestadiotemporal = null;
        this.actualizartablaEstadiodePartido();
        this.miequipoLocalTemporal = null;
        this.actualizarTablaEquiposdePartidoL();
        this.actualizarTablaJugadoresdeEquipoenPartidoL(miequipoLocalTemporal);
        this.miequipoVisitanteTemporal = null; 
        this.actualizarTablaEquiposdePartidoV();
        this.actualizarTablaJugadoresenPartidoV(miequipoLocalTemporal);
        this.TextIdjugadorEquipoL.setText("");
        this.TextIdjugadorEquipoV.setText("");
        this.jSGolesJugadorEquipoV.setValue(0);
        this.jSGolesJugadorL.setValue(0);
    }

    /**
     * Este método actualiza la tabla en el manager corrspondiente con la 
     * información de su equipo
     * @param managerActual 
     */
    public void actualTablaEquipodeManager(Manager managerActual) {
        String nombresColumnas[] = {"Id", "Nombre", "Año de fundación", "N° títulos nacionales", "N° títulos internacionales", "Puntos", "Goles a favor", "Goles en contra", "Partidos jugados"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblEquipodeManager.setModel(miModelo);

        try {
            String fila[] = new String[nombresColumnas.length];

            fila[0] = managerActual.getMiEquipo().getId();
            fila[1] = managerActual.getMiEquipo().getNombre();
            fila[2] = String.valueOf(managerActual.getMiEquipo().getAñoFundacion());
            fila[3] = String.valueOf(managerActual.getMiEquipo().getNumeroTitulosN());
            fila[4] = String.valueOf(managerActual.getMiEquipo().getNumeroTitulosI());
            fila[5] = String.valueOf(managerActual.getMiEquipo().getPuntos());
            fila[6] = String.valueOf(managerActual.getMiEquipo().getGolesAFavor());
            fila[7] = String.valueOf(managerActual.getMiEquipo().getGolesEnContra());
            fila[8] = String.valueOf(managerActual.getMiEquipo().getPartidosJugados());
            miModelo.addRow(fila);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Este método actualiza la tabla en el Tecnico corrspondiente con la 
     * información de su equipo
     * @param equipoActual 
     */
    public void actualTablaEquipodeTecnico(Equipo equipoActual) {
        String nombresColumnas[] = {"Id", "Nombre", "Año de fundación", "N° títulos nacionales", "N° títulos internacionales", "Puntos", "Goles a favor", "Goles en contra", "Partidos jugados"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblEquipodeTecnico.setModel(miModelo);

        try {
            String fila[] = new String[nombresColumnas.length];

            fila[0] = equipoActual.getId();
            fila[1] = equipoActual.getNombre();
            fila[2] = String.valueOf(equipoActual.getAñoFundacion());
            fila[3] = String.valueOf(equipoActual.getNumeroTitulosN());
            fila[4] = String.valueOf(equipoActual.getNumeroTitulosI());
            fila[5] = String.valueOf(equipoActual.getPuntos());
            fila[6] = String.valueOf(equipoActual.getGolesAFavor());
            fila[7] = String.valueOf(equipoActual.getGolesEnContra());
            fila[8] = String.valueOf(equipoActual.getPartidosJugados());
            miModelo.addRow(fila);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Este método actualiza la tabla en el aficionado correspondiente con la 
     * información de su equipo
     * @param nuevoaficionado 
     */
    public void actualTablaEquipodeAficionado(Aficionado nuevoaficionado) {
        String nombresColumnas[] = {"Id", "Nombre", "Año de fundación", "N° títulos nacionales", "N° títulos internacionales", "Puntos", "Goles a favor", "Goles en contra", "Partidos jugados"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblEquipodeAficionado.setModel(miModelo);

        try {
            String fila[] = new String[nombresColumnas.length];

            fila[0] = nuevoaficionado.getMiEquipo().getId();
            fila[1] = nuevoaficionado.getMiEquipo().getNombre();
            fila[2] = String.valueOf(nuevoaficionado.getMiEquipo().getAñoFundacion());
            fila[3] = String.valueOf(nuevoaficionado.getMiEquipo().getNumeroTitulosN());
            fila[4] = String.valueOf(nuevoaficionado.getMiEquipo().getNumeroTitulosI());
            fila[5] = String.valueOf(nuevoaficionado.getMiEquipo().getPuntos());
            fila[6] = String.valueOf(nuevoaficionado.getMiEquipo().getGolesAFavor());
            fila[7] = String.valueOf(nuevoaficionado.getMiEquipo().getGolesEnContra());
            fila[8] = String.valueOf(nuevoaficionado.getMiEquipo().getPartidosJugados());
            miModelo.addRow(fila);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Este método actualiza el combo box en Partido, con la información de los 
     * equipos, a medida que van siendo creados y a la liga
     */
    public void actualizarcomboEquiposdePartido() {
        this.ComboEquiposdePartido.removeAllItems();

        for (Equipo equipoActual : this.miliga.getMisEquipos()) {
            String palabra = equipoActual.getId() + "," + equipoActual.getNombre();

            this.ComboEquiposdePartido.addItem(palabra);
        }
    }
    
    /**
     * Este método actualiza la tabla en el equipo correspondiente con la 
     * información de sus partidos
     * @param nuevoEquipo 
     */
    public void actualizartablaPartidodeEquipo (Equipo nuevoEquipo)
    {
        String nombresColumnas[] = {"Id", "Fecha", "Goles equipo local", "Goles equipo visitante"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblPartidosdeEquipo.setModel(miModelo);

        String fila[] = new String[nombresColumnas.length];
        
        if (nuevoEquipo != null)
        {
            for (Partido partidoActual : nuevoEquipo.getMisPartidos()) 
            {
                if (partidoActual != null) 
                {
                    try {
                        fila[0] = partidoActual.getId();

                        fila[1] = partidoActual.getFecha();

                        fila[2] = String.valueOf(partidoActual.getGolesLocal());

                        fila[3] = String.valueOf(partidoActual.getGolesVisitante());

                    } catch (Exception e) {

                        System.out.println(e);
                    }
                    
                    miModelo.addRow(fila);
                } 
            }
        }    
    }

    /**
     * Este método limpia las entradas en la interfaz de Equipo para que poder 
     * ingresar datos nuevos posteriormente 
     */
    public void limpiarEquipo ()
    {
        this.TextIDEquipo.setText("");
        this.TextNombreEquipo.setText("");
        this.jSañofundacionEquipo.setValue(0);
        this.jStitulosnacionalesEquipo.setValue(0);
        this.jStitulosinternacionesEquipo.setValue(0);
        this.jSPartidosJugados.setValue(0);
        this.jSGolesAfavor.setValue(0);
        this.jSGolesEncontra.setValue(0);
        this.jSPuntosEquipo.setValue(0);
        
        this.misPersonasTemporales = new LinkedList <> ();
        this.actualizarTablaPersonasSeleccionadas();
        this.actualizartablaPartidodeEquipo(null);
    }
    
    /**
     * Este método actualiza la tabla en el partido correspondiente con la 
     * información de los jugadores en el equipo Visitante
     * @param nuevoEquipo 
     */
    public void actualizarTablaJugadoresenPartidoV (Equipo nuevoEquipo)
    {
         String nombresColumnas[] = {"Cédula", "Nombre", "Apellido", "Edad", "Nacionalidad", "Posición", "N° goles", "Salario"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblJugadoresEquipoV.setModel(miModelo);
        
        if(nuevoEquipo != null)
        {
            for (Jugador jugadorActual : nuevoEquipo.getMisJugadores()) {
                
                String fila[] = new String[nombresColumnas.length];

                fila[0] = jugadorActual.getCedula();

                fila[1] = jugadorActual.getNombre();

                fila[2] = jugadorActual.getApellido();

                fila[3] = String.valueOf(jugadorActual.getEdad());

                fila[4] = jugadorActual.getNacionalidad();

                fila[5] = jugadorActual.getPosicion();

                fila[6] = String.valueOf(jugadorActual.getNumeroGoles());

                fila[7] = String.valueOf(jugadorActual.getSalario());

                miModelo.addRow(fila);
            }
        }else 
        {
            String fila[] = new String[nombresColumnas.length];
            fila = this.porsinohaynada(nombresColumnas.length);
            miModelo.addRow(fila);
        }
    }
    
    /**
     * Este método actualiza la tabla en el partido correspondiente con la 
     * información de los jugadores en el equipo Local
     * @param nuevoEquipo 
     */
    public void actualizarTablaJugadoresdeEquipoenPartidoL (Equipo nuevoEquipo)
    {
         String nombresColumnas[] = {"Cédula", "Nombre", "Apellido", "Edad", "Nacionalidad", "Posición", "N° goles", "Salario"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.TblJugadoresEquipoL.setModel(miModelo);

        if(nuevoEquipo != null)
        {
            for (Jugador jugadorActual : nuevoEquipo.getMisJugadores()) 
            {
                String fila[] = new String[nombresColumnas.length];
                
                fila[0] = jugadorActual.getCedula();

                fila[1] = jugadorActual.getNombre();

                fila[2] = jugadorActual.getApellido();

                fila[3] = String.valueOf(jugadorActual.getEdad());

                fila[4] = jugadorActual.getNacionalidad();

                fila[5] = jugadorActual.getPosicion();

                fila[6] = String.valueOf(jugadorActual.getNumeroGoles());

                fila[7] = String.valueOf(jugadorActual.getSalario());
                
                miModelo.addRow(fila);
            }
        }
        else
        {
            String fila[] = new String[nombresColumnas.length];
            fila = this.porsinohaynada(nombresColumnas.length);
            miModelo.addRow(fila);
        }

    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane23 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTable21 = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane10 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Textcedulapersona = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Textnombrepersona = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Textapellidopersona = new javax.swing.JTextField();
        jSedadpersona = new javax.swing.JSpinner();
        RBarbitro = new javax.swing.JRadioButton();
        RBaficionado = new javax.swing.JRadioButton();
        RBtecnico = new javax.swing.JRadioButton();
        RBmanager = new javax.swing.JRadioButton();
        RBjugador = new javax.swing.JRadioButton();
        jPanelArbitro = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        RBFIFAsi = new javax.swing.JRadioButton();
        RBFIFAno = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblParidodeArbitro = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jPanelAficionado = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        RBAbonadosi = new javax.swing.JRadioButton();
        RBabonadono = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TblEquipodeAficionado = new javax.swing.JTable();
        jSañosfidelidadAficionado = new javax.swing.JSpinner();
        jLabel52 = new javax.swing.JLabel();
        jPanelTecnico = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TblEquipodeTecnico = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSAñosexperienciaTecnico = new javax.swing.JSpinner();
        TextSalarioTecnico = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        jPanelManager = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jSañosafiliacionManager = new javax.swing.JSpinner();
        jScrollPane4 = new javax.swing.JScrollPane();
        TblEquipodeManager = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TblJugadoresManager = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        ComboJugadoresManager = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jPanelJugador = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        TextNacionalidadJugador = new javax.swing.JTextField();
        TextPosicionJugador = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jSNumerogolesJugador = new javax.swing.JSpinner();
        jLabel24 = new javax.swing.JLabel();
        TextSalarioJugador = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        TblManagerJugador = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        TblPersonas = new javax.swing.JTable();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        TextIDEstadio = new javax.swing.JTextField();
        TextNombreEstadio = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        TextNombreCiudadEstadio = new javax.swing.JTextField();
        jSCapacidadEstadio = new javax.swing.JSpinner();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        TblPartidosdeEstadio = new javax.swing.JTable();
        jScrollPane12 = new javax.swing.JScrollPane();
        TblEstadios = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        TextNombreEquipo = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jSañofundacionEquipo = new javax.swing.JSpinner();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        TextIDEquipo = new javax.swing.JTextField();
        jSPartidosJugados = new javax.swing.JSpinner();
        jLabel53 = new javax.swing.JLabel();
        jStitulosnacionalesEquipo = new javax.swing.JSpinner();
        jLabel54 = new javax.swing.JLabel();
        jStitulosinternacionesEquipo = new javax.swing.JSpinner();
        jLabel55 = new javax.swing.JLabel();
        jSGolesAfavor = new javax.swing.JSpinner();
        jLabel56 = new javax.swing.JLabel();
        jSGolesEncontra = new javax.swing.JSpinner();
        jLabel58 = new javax.swing.JLabel();
        jSPuntosEquipo = new javax.swing.JSpinner();
        jPanelAggjugadoresEquipo = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        ComboJugador = new javax.swing.JComboBox<>();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jScrollPane17 = new javax.swing.JScrollPane();
        TblPersonasEquipo = new javax.swing.JTable();
        jScrollPane19 = new javax.swing.JScrollPane();
        TblEquipos = new javax.swing.JTable();
        jLabel64 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        TblPartidosdeEquipo = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        TextIDJornada = new javax.swing.JTextField();
        TextEsloganJornada = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        TblPartidosporJornada = new javax.swing.JTable();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jScrollPane22 = new javax.swing.JScrollPane();
        TblJornadas = new javax.swing.JTable();
        jSNumeroJornada = new javax.swing.JSpinner();
        jLabel51 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        TextIDPartido = new javax.swing.JTextField();
        TextFechaPartido = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jSGolesdeEquipoLocal = new javax.swing.JSpinner();
        jLabel40 = new javax.swing.JLabel();
        ComboArbitros = new javax.swing.JComboBox<>();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        TblArbitrodePartido = new javax.swing.JTable();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jScrollPane14 = new javax.swing.JScrollPane();
        TblPartidos = new javax.swing.JTable();
        jSGolesEquipoVisitante = new javax.swing.JSpinner();
        jScrollPane15 = new javax.swing.JScrollPane();
        TblEstadioPartido = new javax.swing.JTable();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        ComboEstadioparaPartido = new javax.swing.JComboBox<>();
        jButton15 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        ComboJornadaparaPartido = new javax.swing.JComboBox<>();
        jButton16 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        TblJornadadePartido = new javax.swing.JTable();
        jScrollPane25 = new javax.swing.JScrollPane();
        TblEquiposdePartido = new javax.swing.JTable();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        ComboEquiposdePartido = new javax.swing.JComboBox<>();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jScrollPane26 = new javax.swing.JScrollPane();
        TblEquiposdePartido1 = new javax.swing.JTable();
        jLabel69 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jScrollPane28 = new javax.swing.JScrollPane();
        TblJugadoresEquipoV = new javax.swing.JTable();
        jLabel73 = new javax.swing.JLabel();
        TextIdjugadorEquipoV = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        jSGolesJugadorEquipoV = new javax.swing.JSpinner();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jLabel79 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        TextIdjugadorEquipoL = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jSGolesJugadorL = new javax.swing.JSpinner();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jScrollPane29 = new javax.swing.JScrollPane();
        TblJugadoresEquipoL = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        RBmetodo1 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        RBmetodo2 = new javax.swing.JRadioButton();
        RBmetodo3 = new javax.swing.JRadioButton();
        RBmetodo4 = new javax.swing.JRadioButton();
        RBmetodo5 = new javax.swing.JRadioButton();
        RBmetodo6 = new javax.swing.JRadioButton();
        RBmetodo7 = new javax.swing.JRadioButton();
        RBmetodo8 = new javax.swing.JRadioButton();
        RBmetodo9 = new javax.swing.JRadioButton();
        jPanelmetodo1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabeljugadorJoven = new javax.swing.JLabel();
        jPanelmetodo2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane27 = new javax.swing.JScrollPane();
        jTablepromedioEdadEquipo = new javax.swing.JTable();
        jPanelmetodo3 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabelEquipoNominaAlta = new javax.swing.JLabel();
        jPanelmetodo5 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabelPartidoMasGoles = new javax.swing.JLabel();
        jPanelmetodo6 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabelpartidosGoleada = new javax.swing.JLabel();
        jPanelmetodo4 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jLabelEquipoMasAficionados = new javax.swing.JLabel();
        jPanelmetodo7 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabeljugadorGoles = new javax.swing.JLabel();
        jPanelmetodo8 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jLabelarquero = new javax.swing.JLabel();
        jPanelmetodo9 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        jButton35 = new javax.swing.JButton();
        jLabelestadioMasGoles = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jButton36 = new javax.swing.JButton();
        jScrollPane36 = new javax.swing.JScrollPane();
        jTableOrdenEquipos = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jTextcedulajugadortransferir = new javax.swing.JTextField();
        jTextidequipotransferir = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane23.setViewportView(jTable8);

        jTable21.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane24.setViewportView(jTable21);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane9.setBackground(new java.awt.Color(153, 153, 153));
        jScrollPane9.setPreferredSize(new java.awt.Dimension(712, 2655));
        jScrollPane9.setRequestFocusEnabled(false);

        jTabbedPane6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jPanel1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel1.setText("PERSONA");

        jLabel4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel4.setText("Cédula");

        Textcedulapersona.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        Textcedulapersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextcedulapersonaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel5.setText("Edad");

        jLabel6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel6.setText("Nombre");

        Textnombrepersona.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        Textnombrepersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextnombrepersonaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel7.setText("Apellido");

        Textapellidopersona.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        Textapellidopersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextapellidopersonaActionPerformed(evt);
            }
        });

        jSedadpersona.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        RBarbitro.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBarbitro.setText("Árbitro");
        RBarbitro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBarbitroActionPerformed(evt);
            }
        });

        RBaficionado.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBaficionado.setText("Aficionado");
        RBaficionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBaficionadoActionPerformed(evt);
            }
        });

        RBtecnico.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBtecnico.setText("Técnico");
        RBtecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBtecnicoActionPerformed(evt);
            }
        });

        RBmanager.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBmanager.setText("Manager");
        RBmanager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBmanagerActionPerformed(evt);
            }
        });

        RBjugador.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBjugador.setText("Jugador");
        RBjugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBjugadorActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel8.setText("¿Es un árbitro FIFA?");

        RBFIFAsi.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBFIFAsi.setText("Sí");
        RBFIFAsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBFIFAsiActionPerformed(evt);
            }
        });

        RBFIFAno.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBFIFAno.setText("No");
        RBFIFAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBFIFAnoActionPerformed(evt);
            }
        });

        TblParidodeArbitro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TblParidodeArbitro);

        jLabel25.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel25.setText("Datos del partido");

        javax.swing.GroupLayout jPanelArbitroLayout = new javax.swing.GroupLayout(jPanelArbitro);
        jPanelArbitro.setLayout(jPanelArbitroLayout);
        jPanelArbitroLayout.setHorizontalGroup(
            jPanelArbitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
            .addGroup(jPanelArbitroLayout.createSequentialGroup()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelArbitroLayout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RBFIFAsi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(RBFIFAno)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelArbitroLayout.setVerticalGroup(
            jPanelArbitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelArbitroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelArbitroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(RBFIFAsi)
                    .addComponent(RBFIFAno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel10.setText("¿Es Abonado?");

        RBAbonadosi.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBAbonadosi.setText("Sí");
        RBAbonadosi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBAbonadosiActionPerformed(evt);
            }
        });

        RBabonadono.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBabonadono.setText("No");
        RBabonadono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBabonadonoActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel11.setText("Años de fidelidad");

        TblEquipodeAficionado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TblEquipodeAficionado);

        jLabel52.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel52.setText("Datos del equipo");

        javax.swing.GroupLayout jPanelAficionadoLayout = new javax.swing.GroupLayout(jPanelAficionado);
        jPanelAficionado.setLayout(jPanelAficionadoLayout);
        jPanelAficionadoLayout.setHorizontalGroup(
            jPanelAficionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAficionadoLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RBAbonadosi, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(RBabonadono)
                .addGap(122, 122, 122)
                .addComponent(jLabel11)
                .addGap(27, 27, 27)
                .addComponent(jSañosfidelidadAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(162, Short.MAX_VALUE))
            .addGroup(jPanelAficionadoLayout.createSequentialGroup()
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanelAficionadoLayout.setVerticalGroup(
            jPanelAficionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAficionadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelAficionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAficionadoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanelAficionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jSañosfidelidadAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAficionadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(RBAbonadosi)
                        .addComponent(RBabonadono)))
                .addGap(18, 18, 18)
                .addComponent(jLabel52)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TblEquipodeTecnico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(TblEquipodeTecnico);

        jLabel13.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel13.setText("Años de experiencia");

        jLabel14.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel14.setText("Salario");

        TextSalarioTecnico.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextSalarioTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextSalarioTecnicoActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel63.setText("Datos del equipo");

        javax.swing.GroupLayout jPanelTecnicoLayout = new javax.swing.GroupLayout(jPanelTecnico);
        jPanelTecnico.setLayout(jPanelTecnicoLayout);
        jPanelTecnicoLayout.setHorizontalGroup(
            jPanelTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
            .addGroup(jPanelTecnicoLayout.createSequentialGroup()
                .addGroup(jPanelTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelTecnicoLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSAñosexperienciaTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(117, 117, 117)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(TextSalarioTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelTecnicoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTecnicoLayout.setVerticalGroup(
            jPanelTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTecnicoLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanelTecnicoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jSAñosexperienciaTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(TextSalarioTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel16.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel16.setText("Años de afiliación");

        TblEquipodeManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(TblEquipodeManager);

        jLabel18.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel18.setText("Datos del equipo");

        TblJugadoresManager.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(TblJugadoresManager);

        jLabel20.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel20.setText("Jugador");

        ComboJugadoresManager.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        ComboJugadoresManager.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboJugadoresManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboJugadoresManagerActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton9.setText("Asignar");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton10.setText("Eliminar");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel19.setText("Datos de los jugadores");

        javax.swing.GroupLayout jPanelManagerLayout = new javax.swing.GroupLayout(jPanelManager);
        jPanelManager.setLayout(jPanelManagerLayout);
        jPanelManagerLayout.setHorizontalGroup(
            jPanelManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelManagerLayout.createSequentialGroup()
                .addGroup(jPanelManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelManagerLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSañosafiliacionManager, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelManagerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(552, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
            .addComponent(jScrollPane5)
            .addGroup(jPanelManagerLayout.createSequentialGroup()
                .addGroup(jPanelManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelManagerLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(ComboJugadoresManager, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton9)
                        .addGap(34, 34, 34)
                        .addComponent(jButton10))
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelManagerLayout.setVerticalGroup(
            jPanelManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelManagerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jSañosafiliacionManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelManagerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(ComboJugadoresManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel21.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel21.setText("Nacionalidad");

        jLabel22.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel22.setText("Posición");

        TextNacionalidadJugador.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextNacionalidadJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNacionalidadJugadorActionPerformed(evt);
            }
        });

        TextPosicionJugador.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextPosicionJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPosicionJugadorActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel23.setText("Número de goles");

        jSNumerogolesJugador.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel24.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel24.setText("Salario");

        TextSalarioJugador.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextSalarioJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextSalarioJugadorActionPerformed(evt);
            }
        });

        TblManagerJugador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(TblManagerJugador);

        jLabel26.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel26.setText("Datos del Manager");

        javax.swing.GroupLayout jPanelJugadorLayout = new javax.swing.GroupLayout(jPanelJugador);
        jPanelJugador.setLayout(jPanelJugadorLayout);
        jPanelJugadorLayout.setHorizontalGroup(
            jPanelJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJugadorLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanelJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextSalarioJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextNacionalidadJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jLabel23)
                .addGap(32, 32, 32)
                .addComponent(jSNumerogolesJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(18, 18, 18)
                .addComponent(TextPosicionJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addComponent(jScrollPane6)
            .addGroup(jPanelJugadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(622, Short.MAX_VALUE))
        );
        jPanelJugadorLayout.setVerticalGroup(
            jPanelJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJugadorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(TextNacionalidadJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jSNumerogolesJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(TextPosicionJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(TextSalarioJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        TblPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane18.setViewportView(TblPersonas);

        jButton58.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton58.setText("Agregar");
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton58ActionPerformed(evt);
            }
        });

        jButton59.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton59.setText("Buscar");
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton59ActionPerformed(evt);
            }
        });

        jButton60.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton60.setText("Editar");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });

        jButton61.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton61.setText("Eliminar");
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton61ActionPerformed(evt);
            }
        });

        jButton62.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton62.setText("Limpiar datos");
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton62ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton1.setText("Extraer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(650, 650, 650)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanelManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane18)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanelAficionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanelArbitro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanelTecnico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jButton59, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jButton61, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jButton62)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(330, 330, 330)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(RBjugador)
                                        .addGap(48, 48, 48)
                                        .addComponent(RBaficionado)
                                        .addGap(38, 38, 38)
                                        .addComponent(RBtecnico)
                                        .addGap(33, 33, 33)
                                        .addComponent(RBmanager)
                                        .addGap(55, 55, 55)
                                        .addComponent(RBarbitro))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(Textcedulapersona, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Textnombrepersona, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(175, 175, 175)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jSedadpersona)
                                            .addComponent(Textapellidopersona, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(117, 117, 117))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Textcedulapersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jSedadpersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Textnombrepersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(Textapellidopersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RBaficionado)
                    .addComponent(RBtecnico)
                    .addComponent(RBmanager)
                    .addComponent(RBjugador)
                    .addComponent(RBarbitro))
                .addGap(43, 43, 43)
                .addComponent(jPanelArbitro, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelAficionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelManager, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton59)
                    .addComponent(jButton58)
                    .addComponent(jButton60)
                    .addComponent(jButton61)
                    .addComponent(jButton62)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(416, 416, 416)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Persona", jPanel1);

        jLabel29.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel29.setText("ESTADIO");

        jLabel30.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel30.setText("ID");

        jLabel31.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel31.setText("Nombre");

        TextIDEstadio.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextIDEstadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextIDEstadioActionPerformed(evt);
            }
        });

        TextNombreEstadio.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextNombreEstadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNombreEstadioActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel32.setText("Ciudad");

        TextNombreCiudadEstadio.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextNombreCiudadEstadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNombreCiudadEstadioActionPerformed(evt);
            }
        });

        jSCapacidadEstadio.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel33.setText("Capacidad");

        TblPartidosdeEstadio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(TblPartidosdeEstadio);

        TblEstadios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane12.setViewportView(TblEstadios);

        jButton22.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton22.setText("Agregar");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton23.setText("Buscar");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton24.setText("Editar");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton25.setText("Eliminar");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton26.setText("Limpiar datos");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel50.setText("Datos de los partidos");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30))
                .addGap(90, 90, 90)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextIDEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextNombreEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextNombreCiudadEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSCapacidadEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jButton26)
                .addGap(82, 82, 82))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(jLabel29))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane11)
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 947, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel29)
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(TextIDEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jSCapacidadEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(TextNombreEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(TextNombreCiudadEstadio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton23)
                    .addComponent(jButton22)
                    .addComponent(jButton24)
                    .addComponent(jButton25)
                    .addComponent(jButton26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1565, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Estadio", jPanel2);

        jLabel44.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel44.setText("EQUIPO");

        jLabel45.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel45.setText("ID");

        jLabel46.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel46.setText("Año de fundación");

        TextNombreEquipo.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextNombreEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNombreEquipoActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel47.setText("Nombre");

        jLabel48.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel48.setText("Partidos jugados");

        jSañofundacionEquipo.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jButton40.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton40.setText("Agregar");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton41.setText("Buscar");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        jButton42.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton42.setText("Editar");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });

        jButton43.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton43.setText("Eliminar");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });

        jButton44.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton44.setText("Limpiar datos");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });

        TextIDEquipo.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextIDEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextIDEquipoActionPerformed(evt);
            }
        });

        jSPartidosJugados.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel53.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel53.setText("N. títulos nacionales");

        jStitulosnacionalesEquipo.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel54.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel54.setText("N. títulos internacionales");

        jStitulosinternacionesEquipo.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel55.setText("Goles a favor");

        jSGolesAfavor.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel56.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel56.setText("Goles en contra");

        jSGolesEncontra.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel58.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel58.setText("Puntos");

        jSPuntosEquipo.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel57.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel57.setText("Datos de los jugadores");

        jLabel65.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel65.setText("Jugador");

        ComboJugador.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        ComboJugador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboJugadorActionPerformed(evt);
            }
        });

        jButton52.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton52.setText("Asignar");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });

        jButton53.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton53.setText("Eliminar");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });

        TblPersonasEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10"
            }
        ));
        jScrollPane17.setViewportView(TblPersonasEquipo);

        javax.swing.GroupLayout jPanelAggjugadoresEquipoLayout = new javax.swing.GroupLayout(jPanelAggjugadoresEquipo);
        jPanelAggjugadoresEquipo.setLayout(jPanelAggjugadoresEquipoLayout);
        jPanelAggjugadoresEquipoLayout.setHorizontalGroup(
            jPanelAggjugadoresEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAggjugadoresEquipoLayout.createSequentialGroup()
                .addGroup(jPanelAggjugadoresEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelAggjugadoresEquipoLayout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addGap(103, 103, 103))
                    .addGroup(jPanelAggjugadoresEquipoLayout.createSequentialGroup()
                        .addComponent(jLabel65)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ComboJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addComponent(jButton52)
                .addGap(60, 60, 60)
                .addComponent(jButton53))
            .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 943, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanelAggjugadoresEquipoLayout.setVerticalGroup(
            jPanelAggjugadoresEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAggjugadoresEquipoLayout.createSequentialGroup()
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanelAggjugadoresEquipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65)
                    .addComponent(jButton52)
                    .addComponent(jButton53))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TblEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11"
            }
        ));
        jScrollPane19.setViewportView(TblEquipos);

        jLabel64.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel64.setText("Datos de los partidos");

        TblPartidosdeEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane20.setViewportView(TblPartidosdeEquipo);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addGap(374, 374, 374)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel48)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jSPartidosJugados, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel47)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(TextNombreEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel56)
                                            .addComponent(jLabel54)
                                            .addComponent(jLabel58))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jStitulosinternacionesEquipo, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                            .addComponent(jSGolesEncontra)
                                            .addComponent(jSPuntosEquipo)))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53)
                                    .addComponent(jLabel55)
                                    .addComponent(jLabel45))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TextIDEquipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jStitulosnacionalesEquipo, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jSañofundacionEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jSGolesAfavor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(553, 553, 553)))
                        .addGap(52, 52, 52))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 944, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelAggjugadoresEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 965, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jButton40, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jButton42, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jButton43, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton44))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(462, 462, 462)
                        .addComponent(jLabel44)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel44)
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel47)
                    .addComponent(TextIDEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextNombreEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(jLabel48)
                    .addComponent(jSPartidosJugados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSañofundacionEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jStitulosnacionalesEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(jStitulosinternacionesEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jLabel56)
                    .addComponent(jSGolesEncontra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSGolesAfavor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jSPuntosEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jPanelAggjugadoresEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton41)
                    .addComponent(jButton40)
                    .addComponent(jButton42)
                    .addComponent(jButton43)
                    .addComponent(jButton44))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1149, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Equipo", jPanel4);

        jLabel59.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel59.setText("JORNADA");

        jLabel60.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel60.setText("ID");

        jLabel61.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel61.setText("Número");

        TextIDJornada.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextIDJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextIDJornadaActionPerformed(evt);
            }
        });

        TextEsloganJornada.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextEsloganJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextEsloganJornadaActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel62.setText("Eslogan");

        TblPartidosporJornada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane21.setViewportView(TblPartidosporJornada);

        jButton47.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton47.setText("Agregar");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton48.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton48.setText("Buscar");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });

        jButton49.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton49.setText("Editar");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });

        jButton50.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton50.setText("Eliminar");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });

        jButton51.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton51.setText("Limpiar datos");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });

        TblJornadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane22.setViewportView(TblJornadas);

        jSNumeroJornada.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel51.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel51.setText("Datos de los partidos");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addGap(35, 35, 35)
                        .addComponent(jSNumeroJornada, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(400, 400, 400))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addGap(453, 453, 453))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel60)
                        .addGap(34, 34, 34)
                        .addComponent(TextIDJornada, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(311, 311, 311)
                        .addComponent(jLabel62)
                        .addGap(40, 40, 40)
                        .addComponent(TextEsloganJornada, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jButton47, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jButton48, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jButton50, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton51)
                        .addGap(63, 63, 63)
                        .addComponent(jButton49, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel59)
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60)
                    .addComponent(TextIDJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62)
                    .addComponent(TextEsloganJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSNumeroJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addGap(31, 31, 31)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton47)
                    .addComponent(jButton48)
                    .addComponent(jButton50)
                    .addComponent(jButton51)
                    .addComponent(jButton49))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1511, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Jornada", jPanel5);

        jLabel35.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel35.setText("PARTIDO");

        jLabel36.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel36.setText("ID");

        jLabel37.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel37.setText("Fecha");

        TextIDPartido.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextIDPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextIDPartidoActionPerformed(evt);
            }
        });

        TextFechaPartido.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextFechaPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFechaPartidoActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel38.setText("Goles del equipo local");

        jLabel39.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel39.setText("Goles del equipo visitante");

        jSGolesdeEquipoLocal.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel40.setText("Árbitro");

        ComboArbitros.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        ComboArbitros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboArbitros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboArbitrosActionPerformed(evt);
            }
        });

        jButton27.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton27.setText("Asignar");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton28.setText("Eliminar");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        TblArbitrodePartido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(TblArbitrodePartido);

        jButton29.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton29.setText("Agregar");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton30.setText("Buscar");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton31.setText("Editar");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton32.setText("Eliminar");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton33.setText("Limpiar datos");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        TblPartidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane14.setViewportView(TblPartidos);

        jSGolesEquipoVisitante.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        TblEstadioPartido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane15.setViewportView(TblEstadioPartido);

        jLabel42.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel42.setText("Datos del estadio");

        jLabel43.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel43.setText("Datos del árbitro");

        jLabel34.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel34.setText("Estadio");

        ComboEstadioparaPartido.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        ComboEstadioparaPartido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboEstadioparaPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboEstadioparaPartidoActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton15.setText("Asignar");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton17.setText("Eliminar");

        jLabel49.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel49.setText("Datos de la jornada");

        jLabel41.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel41.setText("Jornada");

        ComboJornadaparaPartido.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        ComboJornadaparaPartido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboJornadaparaPartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboJornadaparaPartidoActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton16.setText("Asignar");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton18.setText("Eliminar");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        TblJornadadePartido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane16.setViewportView(TblJornadadePartido);

        TblEquiposdePartido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane25.setViewportView(TblEquiposdePartido);

        jLabel66.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel66.setText("Datos del equipo");

        jLabel67.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel67.setText("Visitante");

        ComboEquiposdePartido.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        ComboEquiposdePartido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboEquiposdePartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboEquiposdePartidoActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton19.setText("Asignar");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton20.setText("Eliminar");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel68.setText("Local");

        jButton21.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton21.setText("Asignar");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton34.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton34.setText("Eliminar");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        TblEquiposdePartido1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane26.setViewportView(TblEquiposdePartido1);

        jLabel69.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel69.setText("Equipos");

        jLabel72.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel72.setText("Equipo visitante");

        TblJugadoresEquipoV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane28.setViewportView(TblJugadoresEquipoV);

        jLabel73.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel73.setText("Id jugador ");

        TextIdjugadorEquipoV.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextIdjugadorEquipoV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextIdjugadorEquipoVActionPerformed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel77.setText("Goles");

        jButton38.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton38.setText("Asignar");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton39.setText("Eliminar");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel79.setText("Equipo local");

        jLabel86.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel86.setText("Id jugador ");

        TextIdjugadorEquipoL.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        TextIdjugadorEquipoL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextIdjugadorEquipoLActionPerformed(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel87.setText("Goles");

        jButton45.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton45.setText("Asignar");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jButton46.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton46.setText("Eliminar");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        TblJugadoresEquipoL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane29.setViewportView(TblJugadoresEquipoL);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addGap(480, 480, 480))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                            .addComponent(jScrollPane13)
                            .addComponent(jScrollPane15)
                            .addComponent(jScrollPane16)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ComboEquiposdePartido, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel72))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel39)
                                        .addGap(51, 51, 51)
                                        .addComponent(jSGolesEquipoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(75, 75, 75)
                                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(87, 87, 87))
                            .addComponent(jScrollPane25, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                            .addComponent(jScrollPane28, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel66)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(248, 248, 248)
                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(146, 146, 146))
                            .addComponent(jScrollPane26, javax.swing.GroupLayout.DEFAULT_SIZE, 930, Short.MAX_VALUE)
                            .addComponent(jScrollPane29, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel79)
                                        .addGap(217, 217, 217)
                                        .addComponent(jLabel38)
                                        .addGap(75, 75, 75)
                                        .addComponent(jSGolesdeEquipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel49)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ComboJornadaparaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(73, 73, 73)
                                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(jButton18))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel42)
                                        .addGap(47, 47, 47)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ComboEstadioparaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 71, 71)
                                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)
                                        .addComponent(jButton17))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel36)
                                                .addGap(49, 49, 49)
                                                .addComponent(TextIDPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel43)
                                                .addGap(49, 49, 49)
                                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(ComboArbitros, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(70, 70, 70)
                                                .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(jButton28))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(150, 150, 150)
                                                .addComponent(jLabel37)
                                                .addGap(36, 36, 36)
                                                .addComponent(TextFechaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel73)
                                        .addGap(18, 18, 18)
                                        .addComponent(TextIdjugadorEquipoV, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)
                                        .addComponent(jLabel77)
                                        .addGap(18, 18, 18)
                                        .addComponent(jSGolesJugadorEquipoV, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(78, 78, 78)
                                        .addComponent(jButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton39, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel86)
                                        .addGap(18, 18, 18)
                                        .addComponent(TextIdjugadorEquipoL, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)
                                        .addComponent(jLabel87)
                                        .addGap(18, 18, 18)
                                        .addComponent(jSGolesJugadorL, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(78, 78, 78)
                                        .addComponent(jButton45, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton46, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 115, Short.MAX_VALUE)))))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboArbitros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton28)
                            .addComponent(jButton27)
                            .addComponent(jLabel43))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ComboEstadioparaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34)
                                    .addComponent(jLabel42)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton17)
                                .addComponent(jButton15)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ComboJornadaparaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel49)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton18)
                                .addComponent(jButton16)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextIDPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(TextFechaPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67)
                    .addComponent(jLabel68))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboEquiposdePartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69)
                    .addComponent(jButton19)
                    .addComponent(jButton20)
                    .addComponent(jButton21)
                    .addComponent(jButton34))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel72, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39)
                        .addComponent(jSGolesEquipoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel73)
                    .addComponent(TextIdjugadorEquipoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77)
                    .addComponent(jSGolesJugadorEquipoV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton38)
                    .addComponent(jButton39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(jLabel38)
                    .addComponent(jSGolesdeEquipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(TextIdjugadorEquipoL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel87)
                    .addComponent(jSGolesJugadorL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton45)
                    .addComponent(jButton46))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton33)
                    .addComponent(jButton32)
                    .addComponent(jButton31)
                    .addComponent(jButton30)
                    .addComponent(jButton29))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(523, 523, 523))
        );

        jTabbedPane6.addTab("Partido", jPanel3);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        RBmetodo1.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBmetodo1.setText("Jugador más joven de la liga");
        RBmetodo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBmetodo1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel9.setText("FUNCIONES ESTADÍSTICAS");

        RBmetodo2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBmetodo2.setText("Promedio de edad por cada equipo");
        RBmetodo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBmetodo2ActionPerformed(evt);
            }
        });

        RBmetodo3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBmetodo3.setText("Equipo con nómina de jugadores y técnico más alta");
        RBmetodo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBmetodo3ActionPerformed(evt);
            }
        });

        RBmetodo4.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBmetodo4.setText("Equipo con mayor cantidad de aficionados");
        RBmetodo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBmetodo4ActionPerformed(evt);
            }
        });

        RBmetodo5.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBmetodo5.setText("Identificador del partido el cual tuvo más goles");
        RBmetodo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBmetodo5ActionPerformed(evt);
            }
        });

        RBmetodo6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBmetodo6.setText("Cantidad de partidos en el que un equipo ganó por goleada");
        RBmetodo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBmetodo6ActionPerformed(evt);
            }
        });

        RBmetodo7.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBmetodo7.setText("Jugador que ha marcado más goles en la liga");
        RBmetodo7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBmetodo7ActionPerformed(evt);
            }
        });

        RBmetodo8.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBmetodo8.setText("Arquero al que le han marcado menos goles en la liga");
        RBmetodo8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBmetodo8ActionPerformed(evt);
            }
        });

        RBmetodo9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        RBmetodo9.setText("Nombre del estadio donde se marcaron más goles en toda la liga");
        RBmetodo9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RBmetodo9ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel12.setText("Jugador más joven de la liga");

        jLabeljugadorJoven.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabeljugadorJoven.setText("El jugador más joven es...");

        javax.swing.GroupLayout jPanelmetodo1Layout = new javax.swing.GroupLayout(jPanelmetodo1);
        jPanelmetodo1.setLayout(jPanelmetodo1Layout);
        jPanelmetodo1Layout.setHorizontalGroup(
            jPanelmetodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelmetodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabeljugadorJoven)
                    .addGroup(jPanelmetodo1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(35, 35, 35)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelmetodo1Layout.setVerticalGroup(
            jPanelmetodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanelmetodo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jButton2))
                .addGap(34, 34, 34)
                .addComponent(jLabeljugadorJoven)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jLabel15.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel15.setText("Promedio de edad por cada equipo");

        jButton3.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTablepromedioEdadEquipo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane27.setViewportView(jTablepromedioEdadEquipo);

        javax.swing.GroupLayout jPanelmetodo2Layout = new javax.swing.GroupLayout(jPanelmetodo2);
        jPanelmetodo2.setLayout(jPanelmetodo2Layout);
        jPanelmetodo2Layout.setHorizontalGroup(
            jPanelmetodo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelmetodo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelmetodo2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(35, 35, 35)
                        .addComponent(jButton3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelmetodo2Layout.setVerticalGroup(
            jPanelmetodo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelmetodo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jButton3))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel28.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel28.setText("Equipo con nómina de jugadores y técnico más alta");

        jButton6.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabelEquipoNominaAlta.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabelEquipoNominaAlta.setText("El equipo con la nómina de jugadores y técnico más alta es el equipo...");

        javax.swing.GroupLayout jPanelmetodo3Layout = new javax.swing.GroupLayout(jPanelmetodo3);
        jPanelmetodo3.setLayout(jPanelmetodo3Layout);
        jPanelmetodo3Layout.setHorizontalGroup(
            jPanelmetodo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelmetodo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEquipoNominaAlta)
                    .addGroup(jPanelmetodo3Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(35, 35, 35)
                        .addComponent(jButton6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelmetodo3Layout.setVerticalGroup(
            jPanelmetodo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelmetodo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jButton6))
                .addGap(32, 32, 32)
                .addComponent(jLabelEquipoNominaAlta)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jLabel70.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel70.setText("Identificador del partido el cual tuvo más goles");

        jButton7.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton7.setText("Buscar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabelPartidoMasGoles.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabelPartidoMasGoles.setText("El identificador del partido el cual tuvo más goles es: ");

        javax.swing.GroupLayout jPanelmetodo5Layout = new javax.swing.GroupLayout(jPanelmetodo5);
        jPanelmetodo5.setLayout(jPanelmetodo5Layout);
        jPanelmetodo5Layout.setHorizontalGroup(
            jPanelmetodo5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelmetodo5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPartidoMasGoles, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelmetodo5Layout.createSequentialGroup()
                        .addComponent(jLabel70)
                        .addGap(35, 35, 35)
                        .addComponent(jButton7)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelmetodo5Layout.setVerticalGroup(
            jPanelmetodo5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelmetodo5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel70)
                    .addComponent(jButton7))
                .addGap(18, 18, 18)
                .addComponent(jLabelPartidoMasGoles)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel71.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel71.setText("Cantidad de partidos en los que un equipo ganó por goleada");

        jButton8.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton8.setText("Buscar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabelpartidosGoleada.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabelpartidosGoleada.setText("La cantidad de partidos en los que un equipo ganó por goleada es de: ");

        javax.swing.GroupLayout jPanelmetodo6Layout = new javax.swing.GroupLayout(jPanelmetodo6);
        jPanelmetodo6.setLayout(jPanelmetodo6Layout);
        jPanelmetodo6Layout.setHorizontalGroup(
            jPanelmetodo6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo6Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelmetodo6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelpartidosGoleada)
                    .addGroup(jPanelmetodo6Layout.createSequentialGroup()
                        .addComponent(jLabel71)
                        .addGap(35, 35, 35)
                        .addComponent(jButton8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelmetodo6Layout.setVerticalGroup(
            jPanelmetodo6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelmetodo6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(jButton8))
                .addGap(31, 31, 31)
                .addComponent(jLabelpartidosGoleada)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jLabel74.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel74.setText("Equipo con mayor cantidad de aficionados");

        jButton11.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton11.setText("Buscar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabelEquipoMasAficionados.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabelEquipoMasAficionados.setText("El equipo con la mayor cantidad de aficionados es el equipo...");

        javax.swing.GroupLayout jPanelmetodo4Layout = new javax.swing.GroupLayout(jPanelmetodo4);
        jPanelmetodo4.setLayout(jPanelmetodo4Layout);
        jPanelmetodo4Layout.setHorizontalGroup(
            jPanelmetodo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelmetodo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEquipoMasAficionados)
                    .addGroup(jPanelmetodo4Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addGap(35, 35, 35)
                        .addComponent(jButton11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelmetodo4Layout.setVerticalGroup(
            jPanelmetodo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelmetodo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(jButton11))
                .addGap(43, 43, 43)
                .addComponent(jLabelEquipoMasAficionados)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jLabel75.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel75.setText("Jugador que ha marcado más goles en la liga");

        jButton12.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton12.setText("Buscar");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabeljugadorGoles.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabeljugadorGoles.setText("El jugador que ha marcado más goles en la liga es...");

        javax.swing.GroupLayout jPanelmetodo7Layout = new javax.swing.GroupLayout(jPanelmetodo7);
        jPanelmetodo7.setLayout(jPanelmetodo7Layout);
        jPanelmetodo7Layout.setHorizontalGroup(
            jPanelmetodo7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelmetodo7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabeljugadorGoles)
                    .addGroup(jPanelmetodo7Layout.createSequentialGroup()
                        .addComponent(jLabel75)
                        .addGap(35, 35, 35)
                        .addComponent(jButton12)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelmetodo7Layout.setVerticalGroup(
            jPanelmetodo7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelmetodo7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(jButton12))
                .addGap(42, 42, 42)
                .addComponent(jLabeljugadorGoles)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        jLabel76.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel76.setText("Arquero al que le han marcado menos goles en la liga");

        jButton13.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton13.setText("Buscar");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabelarquero.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabelarquero.setText("El arquero al que le han marcado menos goles en la liga es...");

        javax.swing.GroupLayout jPanelmetodo8Layout = new javax.swing.GroupLayout(jPanelmetodo8);
        jPanelmetodo8.setLayout(jPanelmetodo8Layout);
        jPanelmetodo8Layout.setHorizontalGroup(
            jPanelmetodo8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo8Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelmetodo8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelarquero)
                    .addGroup(jPanelmetodo8Layout.createSequentialGroup()
                        .addComponent(jLabel76)
                        .addGap(35, 35, 35)
                        .addComponent(jButton13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelmetodo8Layout.setVerticalGroup(
            jPanelmetodo8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelmetodo8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(jButton13))
                .addGap(33, 33, 33)
                .addComponent(jLabelarquero)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jLabel78.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel78.setText("Nombre del estadio donde se marcaron más goles en toda la liga");

        jButton35.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton35.setText("Buscar");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jLabelestadioMasGoles.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabelestadioMasGoles.setText("El nombre del estadio donde se marcaron más goles en toda la liga es:");

        javax.swing.GroupLayout jPanelmetodo9Layout = new javax.swing.GroupLayout(jPanelmetodo9);
        jPanelmetodo9.setLayout(jPanelmetodo9Layout);
        jPanelmetodo9Layout.setHorizontalGroup(
            jPanelmetodo9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo9Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelmetodo9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelestadioMasGoles, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelmetodo9Layout.createSequentialGroup()
                        .addComponent(jLabel78)
                        .addGap(35, 35, 35)
                        .addComponent(jButton35)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelmetodo9Layout.setVerticalGroup(
            jPanelmetodo9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelmetodo9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelmetodo9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(jButton35))
                .addGap(34, 34, 34)
                .addComponent(jLabelestadioMasGoles)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(412, 412, 412)
                        .addComponent(jLabel9))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelmetodo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelmetodo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelmetodo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelmetodo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelmetodo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelmetodo7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelmetodo8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelmetodo9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelmetodo4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RBmetodo4)
                                    .addComponent(RBmetodo6)
                                    .addComponent(RBmetodo8)
                                    .addComponent(RBmetodo9))
                                .addGap(131, 131, 131)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(RBmetodo1)
                                    .addComponent(RBmetodo7)
                                    .addComponent(RBmetodo5)
                                    .addComponent(RBmetodo3)
                                    .addComponent(RBmetodo2))))))
                .addContainerGap(1006, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel9)
                .addGap(53, 53, 53)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RBmetodo3)
                    .addComponent(RBmetodo9))
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RBmetodo4)
                    .addComponent(RBmetodo5))
                .addGap(55, 55, 55)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RBmetodo6)
                    .addComponent(RBmetodo7))
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RBmetodo8)
                    .addComponent(RBmetodo1))
                .addGap(48, 48, 48)
                .addComponent(RBmetodo2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelmetodo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelmetodo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelmetodo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelmetodo4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelmetodo5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelmetodo6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelmetodo7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelmetodo8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelmetodo9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1292, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Estadísticas", jPanel6);

        jLabel80.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel80.setText("FUNCIONES DE ORDENAMIENTOS");

        jLabel81.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel81.setText("Tabla de posiciones de la liga, de los equipos ordenados de mayor a menor teniendo en cuenta la cantidad de puntos.");

        jButton36.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton36.setText("Buscar");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jTableOrdenEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane36.setViewportView(jTableOrdenEquipos);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(379, 379, 379)
                        .addComponent(jLabel80))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton36)
                            .addComponent(jLabel81)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1185, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel80)
                .addGap(36, 36, 36)
                .addComponent(jLabel81)
                .addGap(34, 34, 34)
                .addComponent(jButton36)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane36, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2697, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ordenamientos", jPanel8);

        jLabel82.setFont(new java.awt.Font("Segoe UI Emoji", 0, 18)); // NOI18N
        jLabel82.setText("FUNCIONES DE TRANSFERENCIAS");

        jLabel83.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel83.setText("Transferir un jugador a otro equipo");

        jLabel84.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel84.setText("Ingrese la cédula del jugador");

        jLabel85.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jLabel85.setText("Ingrese el identificador del equipo al que desea ser transferido");

        jTextcedulajugadortransferir.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jTextidequipotransferir.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N

        jButton37.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        jButton37.setText("ACEPTAR");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(357, 357, 357)
                        .addComponent(jLabel82))
                    .addComponent(jLabel83)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel85)
                        .addGap(29, 29, 29)
                        .addComponent(jTextidequipotransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel84)
                        .addGap(29, 29, 29)
                        .addComponent(jTextcedulajugadortransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton37))
                .addContainerGap(1292, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel82)
                .addGap(36, 36, 36)
                .addComponent(jLabel83)
                .addGap(42, 42, 42)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(jTextcedulajugadortransferir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(jTextidequipotransferir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(jButton37)
                .addContainerGap(2775, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Transferencias", jPanel9);

        jScrollPane7.setViewportView(jTabbedPane1);

        jTabbedPane6.addTab("Funciones", jScrollPane7);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1008, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 2112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 464, Short.MAX_VALUE))
        );

        jScrollPane9.setViewportView(jPanel11);

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 0, 11)); // NOI18N
        jLabel2.setText("92381D093");

        jLabel3.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        jLabel3.setText("CHAMPIONS LEAGUE");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(453, 453, 453)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(487, 487, 487))
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 2139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(538, Short.MAX_VALUE))
        );

        jScrollPane10.setViewportView(jPanel12);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 2226, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        // TODO add your handling code here:

        int indiceSeleccionado = this.ComboEquiposdePartido.getSelectedIndex();
        Equipo equipoSeleccionado = this.miliga.getMisEquipos().get(indiceSeleccionado);
        this.miequipoLocalTemporal = null;
        this.jSGolesdeEquipoLocal.setValue(0);
        this.actualizarTablaEquiposdePartidoL();
        this.actualizarTablaJugadoresdeEquipoenPartidoL(null);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        // TODO add your handling code here:

        JOptionPane.showMessageDialog(rootPane, "Solo elija a un equipo");
        int indiceSeleccionado = this.ComboEquiposdePartido.getSelectedIndex();
        Equipo equipoSeleccionado = this.miliga.getMisEquipos().get(indiceSeleccionado);

        if(equipoSeleccionado != miequipoVisitanteTemporal)
        {
            this.miequipoLocalTemporal = equipoSeleccionado;
            this.actualizarTablaEquiposdePartidoL();
            this.actualizarTablaJugadoresdeEquipoenPartidoL(equipoSeleccionado);
        }else
        {
            JOptionPane.showMessageDialog(rootPane, "Elige un equipo diferente y da click en asignar");
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        // TODO add your handling code here:

        int indiceSeleccionado = this.ComboEquiposdePartido.getSelectedIndex();
        Equipo equipoSeleccionado = this.miliga.getMisEquipos().get(indiceSeleccionado);
        this.miequipoVisitanteTemporal = null;
        this.jSGolesJugadorEquipoV.setValue(0);
        this.actualizarTablaEquiposdePartidoV();
        this.actualizarTablaJugadoresenPartidoV(null);
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:

        JOptionPane.showMessageDialog(rootPane, "Solo elija a un equipo");
        int indiceSeleccionado = this.ComboEquiposdePartido.getSelectedIndex();
        Equipo equipoSeleccionado = this.miliga.getMisEquipos().get(indiceSeleccionado);
        this.miequipoVisitanteTemporal = equipoSeleccionado;
        this.actualizarTablaEquiposdePartidoV();
        this.actualizarTablaJugadoresenPartidoV(equipoSeleccionado);
    }//GEN-LAST:event_jButton19ActionPerformed

    private void ComboEquiposdePartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboEquiposdePartidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboEquiposdePartidoActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:

        JOptionPane.showMessageDialog(rootPane, "Solo elija a un estadio");
        int indiceSeleccionado = this.ComboJornadaparaPartido.getSelectedIndex();
        Jornada jornadaSeleccionado = this.miliga.getMisJornadas().get(indiceSeleccionado);
        this.mijornadatemporal = null;
        this.actualizarTabladeJornadaparaPartido();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:

        JOptionPane.showMessageDialog(rootPane, "Solo elija a una jornada");
        int indiceSeleccionado = this.ComboJornadaparaPartido.getSelectedIndex();
        Jornada jornadaSeleccionado = this.miliga.getMisJornadas().get(indiceSeleccionado);
        this.mijornadatemporal = jornadaSeleccionado;
        this.actualizarTabladeJornadaparaPartido();
    }//GEN-LAST:event_jButton16ActionPerformed

    private void ComboJornadaparaPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboJornadaparaPartidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboJornadaparaPartidoActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:

        JOptionPane.showMessageDialog(rootPane, "Solo elija a un estadio");
        int indiceSeleccionado = this.ComboEstadioparaPartido.getSelectedIndex();
        Estadio estadioSeleccionado = this.miliga.getMisEstadios().get(indiceSeleccionado);
        this.miestadiotemporal = estadioSeleccionado;
        this.actualizartablaEstadiodePartido();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void ComboEstadioparaPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboEstadioparaPartidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboEstadioparaPartidoActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        // TODO add your handling code here:

        this.limpiarpartido();
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDPartido.getText();

        for (Partido partidoActual : this.miliga.getMispartidos())
        {
            if (partidoActual != null)
            {
                if(partidoActual.getId().equals(id))
                {
                    Arbitro arbitroactual = partidoActual.getMiArbitro();
                    if (arbitroactual != null)
                    {
                        arbitroactual.eliminarpartido();
                    }

                    Equipo equipoL = partidoActual.getRefEquipoLocal();
                    if(equipoL != null)
                    {
                        equipoL.eliminarPartido(id);
                    }

                    Equipo equipoV = partidoActual.getRefEquipoVisitante();
                    if(equipoV != null)
                    {
                        equipoV.eliminarPartido(id);
                    }

                    Jornada nuevaJornada = partidoActual.getRefJornada();
                    if (nuevaJornada != null)
                    {
                        nuevaJornada.eliminarPartido(id);
                    }

                    Estadio nuevoEstadio = partidoActual.getMiEstadio();
                    if (nuevoEstadio != null)
                    {
                        nuevoEstadio.eliminarpartido(id);
                    }

                    this.miliga.getMispartidos().remove(partidoActual);
                    this.actualizarTablaPersonas();
                    this.actualizarTablaJornadas();
                    this.actualizarTablaEquipos();
                    this.actualizarTabladeEstadios();
                    this.actualizarTablaPartidos();
                    this.limpiarpartido();
                }

                JOptionPane.showMessageDialog(rootPane, "Partido eliminado correctamente");
            }else
            {
                JOptionPane.showMessageDialog(rootPane, "Este partido no existe");
            }
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDPartido.getText();

        for (Partido partidoActual : this.miliga.getMispartidos())
        {
            if (partidoActual != null)
            {
                if(partidoActual.getId().equals(id))
                {
                    partidoActual.setFecha(this.TextFechaPartido.getText());
                    partidoActual.setGolesLocal((Integer) this.jSGolesdeEquipoLocal.getValue());
                    partidoActual.setGolesVisitante((Integer) this.jSGolesEquipoVisitante.getValue());
                    partidoActual.setMiArbitro(this.miarbitrotemporal);
                    partidoActual.setMiEstadio(miestadiotemporal);
                    
                    if((Integer) this.jSGolesdeEquipoLocal.getValue() == (Integer) this.jSGolesEquipoVisitante.getValue())          
                    {
                        this.miequipoVisitanteTemporal.setPuntos(1);
                        this.miequipoLocalTemporal.setPuntos(1);
                    } else if ((Integer) this.jSGolesdeEquipoLocal.getValue() > (Integer) this.jSGolesEquipoVisitante.getValue()) {
                        this.miequipoLocalTemporal.setPuntos(3);
                        this.miequipoVisitanteTemporal.setPuntos(0);
                    } else if ((Integer) this.jSGolesdeEquipoLocal.getValue() < (Integer) this.jSGolesEquipoVisitante.getValue()) {
                        this.miequipoLocalTemporal.setPuntos(0);
                        this.miequipoVisitanteTemporal.setPuntos(3);
                    }
                    
                    partidoActual.setRefEquipoLocal(miequipoLocalTemporal);
                    partidoActual.setRefEquipoVisitante(miequipoVisitanteTemporal);

                    JOptionPane.showMessageDialog(rootPane, "Partido editado correctamente");
                }
            }
        }

        this.actualizarTablaPartidos();
        this.actualizarTablaPersonas();
        this.actualizarTablaJornadas();
        this.actualizarTablaEquipos();
        this.actualizarTabladeEstadios();
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDPartido.getText();

        for (Partido partidoActual : this.miliga.getMispartidos())
        {
            if (partidoActual != null)
            {
                if (partidoActual.getId().equals(id)) {
                    this.TextFechaPartido.setText(partidoActual.getFecha());
                    this.jSGolesEquipoVisitante.setValue(partidoActual.getGolesVisitante());
                    this.jSGolesdeEquipoLocal.setValue(partidoActual.getGolesLocal());
                    this.miarbitrotemporal = partidoActual.getMiArbitro();
                    this.actualizartablaArbitrodePartido();
                    this.miarbitrotemporal = new Arbitro();
                    this.miestadiotemporal = partidoActual.getMiEstadio();
                    this.actualizartablaEstadiodePartido();
                    this.miestadiotemporal = new Estadio();
                    this.mijornadatemporal = partidoActual.getRefJornada();
                    this.actualizarTabladeJornadaparaPartido();
                    this.mijornadatemporal = new Jornada();
                    this.miequipoLocalTemporal = partidoActual.getRefEquipoLocal();
                    this.miequipoVisitanteTemporal = partidoActual.getRefEquipoVisitante();
                    this.actualizarTablaEquiposdePartidoL();
                    this.actualizarTablaEquiposdePartidoV();
                    this.actualizarTablaJugadoresenPartidoV(miequipoVisitanteTemporal);
                    this.actualizarTablaJugadoresdeEquipoenPartidoL(miequipoLocalTemporal);                 
                }
            }
        }
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDPartido.getText();
        String fecha = this.TextFechaPartido.getText();
        int golesEquipolocal = (Integer) this.jSGolesdeEquipoLocal.getValue();
        int golesEquipoVisitante = (Integer) this.jSGolesEquipoVisitante.getValue();

        Partido nuevoPartido = new Partido(id, fecha, golesEquipolocal, golesEquipoVisitante);

        for (Estadio estadioActual : this.miliga.getMisEstadios())
        {
            if (estadioActual == this.miestadiotemporal)
            {
                nuevoPartido.setMiEstadio(estadioActual);
                estadioActual.getMisPartidos().add(nuevoPartido);
            }
        }

        this.miequipoVisitanteTemporal.setGolesAFavor(golesEquipoVisitante);
        this.miequipoVisitanteTemporal.setGolesEnContra(golesEquipolocal);
        
        this.miequipoLocalTemporal.setGolesAFavor(golesEquipolocal);
        this.miequipoLocalTemporal.setGolesEnContra(golesEquipoVisitante);
        
        if(golesEquipolocal == golesEquipoVisitante)
        {
            this.miequipoVisitanteTemporal.setPuntos(1);
            this.miequipoLocalTemporal.setPuntos(1);
        }else if(golesEquipolocal > golesEquipoVisitante)
        {
            this.miequipoLocalTemporal.setPuntos(3);
            this.miequipoVisitanteTemporal.setPuntos(0);
        }else if (golesEquipolocal < golesEquipoVisitante)
        {
            this.miequipoLocalTemporal.setPuntos(0);
            this.miequipoVisitanteTemporal.setPuntos(3);
        }
        
        nuevoPartido.setRefJornada(mijornadatemporal);
        nuevoPartido.setRefEquipoVisitante(this.miequipoVisitanteTemporal);
        nuevoPartido.setRefEquipoLocal(this.miequipoLocalTemporal);
        nuevoPartido.setMiArbitro(this.miarbitrotemporal);

        for (Persona personaActual : this.miliga.getMisPersonas())
        {
            if (personaActual instanceof Arbitro)
            {
                if (((Arbitro) personaActual) == this.miarbitrotemporal)
                {
                    ((Arbitro) personaActual).setMiPartido(nuevoPartido);
                }
            }
        }

        for (Jornada jornadaActual : this.miliga.getMisJornadas())
        {
            if (jornadaActual.getId().equals(this.mijornadatemporal.getId()))
            {
                jornadaActual.getMisPartidos().add(nuevoPartido);
            }
        }
       
        this.miliga.buscarequipoyagregarpartido(nuevoPartido, this.miequipoLocalTemporal.getId());
        this.miliga.buscarequipoyagregarpartido(nuevoPartido, this.miequipoVisitanteTemporal.getId());

        JOptionPane.showMessageDialog(rootPane, "El partido ha sido creado exitosamente");

        this.miliga.getMispartidos().add(nuevoPartido);
        this.actualizartablaArbitrodePartido();
        this.actualizarTablaJornadas();
        this.actualizarTabladeEstadios();
        this.actualizarTablaPersonas();
        this.actualizarTablaPartidos();
        this.limpiarpartido();
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:

        try
        {
            int indiceSeleccionado = this.ComboArbitros.getSelectedIndex();
            Persona personaSeleccionada = this.miliga.getMisPersonas().get(indiceSeleccionado);
            this.miarbitrotemporal = null;
            this.actualizartablaArbitrodePartido();
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(rootPane, "Solo elija a un arbitro");

        String sinseparar = (String) this.ComboArbitros.getSelectedItem();
        String[] separada = sinseparar.split(",");
        Persona personaSeleccionada = this.miliga.buscarpersonaNueva(separada[0]);
        this.miarbitrotemporal = (Arbitro) personaSeleccionada;
        this.actualizartablaArbitrodePartido();
    }//GEN-LAST:event_jButton27ActionPerformed

    private void ComboArbitrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboArbitrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboArbitrosActionPerformed

    private void TextFechaPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFechaPartidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFechaPartidoActionPerformed

    private void TextIDPartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextIDPartidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextIDPartidoActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        this.limpiarJornada();
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDJornada.getText();

        boolean eliminado = this.miliga.eliminarJornada(id);

        if (eliminado == true) {
            JOptionPane.showMessageDialog(rootPane, "Jornada eliminada");
            this.limpiarJornada();

            this.actualizarTablaPartidos();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Jornada no encontrada");
        }

        this.actualizarTablaJornadas();
        this.actualizarcomboJornadasparaPartidos();
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDJornada.getText();
        int numero = (Integer) this.jSNumeroJornada.getValue();
        String Eslogan = this.TextEsloganJornada.getText();

        for (Jornada jornadaActual : this.miliga.getMisJornadas())
        {
            if (jornadaActual.getId().equals(id))
            {
                jornadaActual.setNumero(numero);
                jornadaActual.setEslogan(Eslogan);
                JOptionPane.showMessageDialog(rootPane, "Jornada ha sido modificada exitosamente");
                this.actualizarcomboJornadasparaPartidos();
                this.actualizarTablaJornadas();
                this.actualizarTablaPartidos();
            }
        }
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDJornada.getText();

        Jornada nuevaJornada = this.miliga.buscarJornada(id);

        if (nuevaJornada != null) {
            JOptionPane.showMessageDialog(rootPane, "Jornada encontrada exitosamente");
            this.jSNumeroJornada.setValue(nuevaJornada.getNumero());
            this.TextEsloganJornada.setText(nuevaJornada.getEslogan());
            this.actualizarTablaPartidodeJornada(nuevaJornada);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Jornada no encontrada");
        }
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        // TODO add your handling code here:
        String id = this.TextIDJornada.getText();
        String Eslogan = this.TextEsloganJornada.getText();
        int numero = (Integer) this.jSNumeroJornada.getValue();

        Jornada nuevaJornada = new Jornada(id, numero, Eslogan);

        Jornada jornadaActual = this.miliga.buscarJornada(id);

        if (jornadaActual == null) {
            this.miliga.getMisJornadas().add(nuevaJornada);
            JOptionPane.showMessageDialog(rootPane, "Jornada creada exitosamente");
            this.actualizarcomboJornadasparaPartidos();
            this.actualizarTablaJornadas();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Jornada ya existe");
        }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void TextEsloganJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextEsloganJornadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextEsloganJornadaActionPerformed

    private void TextIDJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextIDJornadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextIDJornadaActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        // TODO add your handling code here:
        int indiceSeleccionado = this.ComboJugador.getSelectedIndex();
        Persona personaSeleccionada = this.miliga.getMisPersonas().get(indiceSeleccionado);
        this.misPersonasTemporales.remove(personaSeleccionada);
        this.actualizarTablaPersonasSeleccionadas();
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        // TODO add your handling code here:
        String sinseparar = (String) this.ComboJugador.getSelectedItem();
        String[] separada = sinseparar.split(",");

        Persona personaSeleccionada = this.miliga.buscarpersonaNueva(separada[0]);
        this.misPersonasTemporales.add(personaSeleccionada);
        this.actualizarTablaPersonasSeleccionadas();
    }//GEN-LAST:event_jButton52ActionPerformed

    private void ComboJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboJugadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboJugadorActionPerformed

    private void TextIDEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextIDEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextIDEquipoActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        // TODO add your handling code here:

        this.limpiarEquipo();
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDEquipo.getText();

        boolean eliminado = this.miliga.eliminarEquipo(id);

        if(eliminado == true)
        {
            this.actualizarTablaEquipos();
            this.actualizarTablaPersonas();
            this.actualizarcomboEquiposdePartido();
        }
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDEquipo.getText();

        for(Equipo nuevoEquipo : this.miliga.getMisEquipos())
        {
            if(nuevoEquipo.getId().equals(id))
            {
                nuevoEquipo.setAñoFundacion((int) this.jSañofundacionEquipo.getValue());
                nuevoEquipo.setGolesAFavor((int) this.jSGolesAfavor.getValue());
                nuevoEquipo.setGolesEnContra((int) this.jSGolesEncontra.getValue());
                nuevoEquipo.setNombre(this.TextNombreEquipo.getText());
                nuevoEquipo.setNumeroTitulosI((int) this.jStitulosinternacionesEquipo.getValue());
                nuevoEquipo.setNumeroTitulosN((int) this.jStitulosnacionalesEquipo.getValue());
                nuevoEquipo.setPuntos((int) this.jSPuntosEquipo.getValue());

                nuevoEquipo.eliminaraficionados();
                nuevoEquipo.eliminarjugadores();
                nuevoEquipo.setMiManager(null);
                nuevoEquipo.setMiTecnico(null);

                for (Persona personaActual : this.misPersonasTemporales)
                {
                    if(personaActual instanceof Jugador)
                    {
                        nuevoEquipo.getMisJugadores().add((Jugador) personaActual);
                    }else if(personaActual instanceof Manager)
                    {
                        nuevoEquipo.setMiManager(null);
                        nuevoEquipo.setMiManager((Manager) personaActual);
                    }else if (personaActual instanceof Aficionado)
                    {
                        nuevoEquipo.getMisAficionados().add((Aficionado) personaActual);
                    }else if (personaActual instanceof Tecnico)
                    {
                        nuevoEquipo.setMiTecnico(null);
                        nuevoEquipo.setMiTecnico((Tecnico) personaActual);
                    }
                }

                JOptionPane.showMessageDialog(rootPane, "Equipo modificado exitosamente");
            }
        }

        this.actualizarTablaPersonas();
        this.actualizarcomboEquiposdePartido();
        this.actualizarTablaEquipos();
        this.misPersonasTemporales = new LinkedList <> ();
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDEquipo.getText();

        Equipo nuevoEquipo = this.miliga.buscarEquipo(id);

        if (nuevoEquipo != null)
        {
            JOptionPane.showMessageDialog(rootPane, "Equipo encuentrado exitosamente");

            this.TextNombreEquipo.setText(nuevoEquipo.getNombre());
            this.jSañofundacionEquipo.setValue(nuevoEquipo.getAñoFundacion());
            this.jStitulosnacionalesEquipo.setValue(nuevoEquipo.getNumeroTitulosN());
            this.jStitulosinternacionesEquipo.setValue(nuevoEquipo.getNumeroTitulosI());
            this.jSPartidosJugados.setValue(nuevoEquipo.getMisPartidos().size());
            this.jSGolesAfavor.setValue(nuevoEquipo.getGolesAFavor());
            this.jSGolesEncontra.setValue(nuevoEquipo.getGolesEnContra());
            this.jSPuntosEquipo.setValue(nuevoEquipo.getPuntos());

            for (Jugador nuevojugador : nuevoEquipo.getMisJugadores()) {
                if (nuevojugador != null) {
                    this.misPersonasTemporales.add(nuevojugador);
                }
            }

            for (Aficionado nuevoaficionado : nuevoEquipo.getMisAficionados()) {
                if (nuevoaficionado != null) {
                    this.misPersonasTemporales.add(nuevoaficionado);
                }
            }

            if (nuevoEquipo.getMiManager() != null) {
                this.misPersonasTemporales.add(nuevoEquipo.getMiManager());
            }

            if (nuevoEquipo.getMiTecnico() != null) {
                this.misPersonasTemporales.add(nuevoEquipo.getMiTecnico());
            }

            this.actualizarTablaPersonasSeleccionadas();
            this.actualizartablaPartidodeEquipo(nuevoEquipo);
        }else
        {
            JOptionPane.showMessageDialog(rootPane, "Este equipo no se encuentra en la liga");
        }

    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        // TODO add your handling code here:
        String id = this.TextIDEquipo.getText();
        String nombre = this.TextNombreEquipo.getText();
        int añofundacion = (Integer) this.jSañofundacionEquipo.getValue();
        int NgolesN = (Integer) this.jStitulosnacionalesEquipo.getValue();
        int NgolesI = (Integer) this.jStitulosinternacionesEquipo.getValue();
        int puntos = (Integer) this.jSPuntosEquipo.getValue();
        int GolesA = (Integer) this.jSGolesAfavor.getValue();
        int golesenContra = (Integer) this.jSGolesEncontra.getValue();
        int patidosJugados = (Integer) this.jSPartidosJugados.getValue();

        Equipo nuevoEquipo = this.miliga.buscarEquipo(id);

        if (nuevoEquipo == null)
        {
            this.miliga.crearEquipo(id, nombre, añofundacion, NgolesN, NgolesI, puntos, GolesA, golesenContra, patidosJugados);

            for (Equipo equipoActual : this.miliga.getMisEquipos()) {
                if (equipoActual.getId().equals(id)) {
                    for (Persona nuevapersona : this.misPersonasTemporales) {
                        if (nuevapersona instanceof Jugador)
                        {
                            boolean esta = this.miliga.buscarjugadorEquipos(nuevapersona.getCedula());

                            boolean otra = equipoActual.buscarjugador(nuevapersona.getCedula());

                            if (esta == false && otra == false)
                            {
                                equipoActual.agregarJugador((Jugador) nuevapersona);
                            }
                        } else if (nuevapersona instanceof Manager) {
                            boolean esta = false;

                            for (Equipo equipoNuevo : this.miliga.getMisEquipos()) {
                                if (equipoNuevo.getMiManager() != null)
                                {
                                    if (equipoNuevo.getMiManager().getCedula().equals(nuevapersona.getCedula()))
                                    {
                                        esta = true;
                                    }
                                }
                            }

                            if (esta == false)
                            {
                                if (equipoActual.getMiManager() == null) {
                                    equipoActual.setMiManager((Manager) nuevapersona);

                                    ((Manager) nuevapersona).setMiEquipo(equipoActual);
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Este equipo ya tiene un manager");
                                }
                            }

                            this.actualTablaEquipodeManager((Manager) nuevapersona);
                        } else if (nuevapersona instanceof Tecnico) {
                            boolean esta = false;

                            for (Equipo equipoNuevo : this.miliga.getMisEquipos()) {
                                if (equipoNuevo.getMiTecnico() != null) {
                                    if (equipoNuevo.getMiTecnico().getCedula().equals(nuevapersona.getCedula())) {
                                        esta = true;
                                    }
                                }
                            }

                            if (esta == false) {
                                if (equipoActual.getMiTecnico() == null) {
                                    equipoActual.agregarTecnico((Tecnico) nuevapersona);
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Este equipo ya tiene un Tecnico");
                                }
                            }

                            this.actualTablaEquipodeTecnico(equipoActual);

                        } else if (nuevapersona instanceof Aficionado) {
                            boolean esta = false;

                            for (Equipo equipoNuevo : this.miliga.getMisEquipos()) {
                                for (Aficionado aficionadoActual : equipoNuevo.getMisAficionados()) {
                                    if (aficionadoActual.getCedula().equals(nuevapersona.getCedula())) {
                                        esta = true;
                                    }
                                }
                            }

                            boolean otra = equipoActual.buscaraficionado(nuevapersona.getCedula());

                            if (otra == false && esta == false) {
                                System.out.println(esta);

                                equipoActual.getMisAficionados().add((Aficionado) nuevapersona);

                                ((Aficionado) nuevapersona).setMiEquipo(equipoActual);

                                this.actualTablaEquipodeAficionado((Aficionado) nuevapersona);
                            }
                        }
                    }

                    this.limpiarEquipo();
                }
            }

            this.actualizarTablaPersonas();
            JOptionPane.showMessageDialog(rootPane, "Equipo creado");
            this.actualizarTablaEquipos();
            this.actualizarcomboEquiposdePartido();
            this.misPersonasTemporales = new LinkedList<>();
            this.actualizarTablaPersonasSeleccionadas();
        }

    }//GEN-LAST:event_jButton40ActionPerformed

    private void TextNombreEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNombreEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNombreEquipoActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        // TODO add your handling code here:

        this.limpliarEstadio();
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDEstadio.getText();

        boolean eliminado = this.miliga.eliminarEstadio(id);

        if (eliminado == true) {
            this.limpliarEstadio();
            JOptionPane.showMessageDialog(rootPane, "Estadio eliminado");
            this.actualizarTabladeEstadios();
            this.actualizarcomboEstadioparaPartidos();
            this.actualizarTablaPartidos();
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDEstadio.getText();
        String nombre = this.TextNombreEstadio.getText();
        int capacidad = (Integer) this.jSCapacidadEstadio.getValue();
        String ciudad = this.TextNombreCiudadEstadio.getText();

        for (Estadio estadioActual : this.miliga.getMisEstadios()) {
            if (estadioActual.getId().equals(id)) {
                estadioActual.setCiudad(ciudad);
                estadioActual.setCapacidad(capacidad);
                estadioActual.setNombre(nombre);
                JOptionPane.showMessageDialog(rootPane, "Estadio ha sido modificado exitosamente");
                this.actualizarTabladeEstadios();
                this.actualizarcomboEstadioparaPartidos();
            }
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDEstadio.getText();
        Estadio estadioActual = this.miliga.buscarEstadio(id);

        if (estadioActual != null) {
            JOptionPane.showMessageDialog(rootPane, "Estadio ha sido encontrado");
            this.TextNombreCiudadEstadio.setText(estadioActual.getCiudad());
            this.TextNombreEstadio.setText(estadioActual.getNombre());
            this.jSCapacidadEstadio.setValue(estadioActual.getCapacidad());
            this.actualizarTablaPartidosparaestadio(estadioActual);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Estadio no existe");
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // TODO add your handling code here:

        String id = this.TextIDEstadio.getText();
        String nombte = this.TextNombreEstadio.getText();
        int capacidad = (Integer) this.jSCapacidadEstadio.getValue();
        String ciudad = this.TextNombreCiudadEstadio.getText();

        Estadio estadioActual = this.miliga.buscarEstadio(id);

        if (estadioActual == null)
        {
            Estadio nuevoEstadio = new Estadio(id, ciudad, nombte, capacidad);
            this.miliga.getMisEstadios().add(nuevoEstadio);
            this.actualizarcomboEstadioparaPartidos();
            this.actualizarTabladeEstadios();
            JOptionPane.showMessageDialog(rootPane, "Estadio ha sido creado exitosamente");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Estadio ya existe");
        }

        this.limpliarEstadio();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void TextNombreCiudadEstadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNombreCiudadEstadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNombreCiudadEstadioActionPerformed

    private void TextNombreEstadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNombreEstadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNombreEstadioActionPerformed

    private void TextIDEstadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextIDEstadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextIDEstadioActionPerformed

    private void jButton62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton62ActionPerformed
        // TODO add your handling code here:

        String Cedula = this.Textcedulapersona.getText();

        this.limpiarPersona(Cedula);
    }//GEN-LAST:event_jButton62ActionPerformed

    private void jButton61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton61ActionPerformed
        // TODO add your handling code here:

        String cedula = this.Textcedulapersona.getText();

        Persona nuevaPersona = this.miliga.buscarpersonaNueva(cedula);

        if (nuevaPersona instanceof Jugador)
        {
            if (((Jugador) nuevaPersona).getMiManager() != null)
            {
                String id = ((Jugador) nuevaPersona).getMiManager().getCedula();

                for (Persona personaActual : this.miliga.getMisPersonas())
                {
                    if (personaActual instanceof Manager) {
                        if (personaActual.getCedula().equals(id)) {
                            ((Manager) personaActual).getMisJugadores().remove(nuevaPersona);
                        }
                    }
                }
            }

            for(Equipo equipoActual : this.miliga.getMisEquipos())
            {
                for(Jugador jugadorActual : equipoActual.getMisJugadores())
                {
                    if (jugadorActual.getCedula().equals(cedula))
                    {
                        equipoActual.getMisJugadores().remove(jugadorActual);
                    }
                }
            }

        }else if (nuevaPersona instanceof Manager)
        {
            for (Jugador jugadorActual : ((Manager) nuevaPersona).getMisJugadores())
            {
                jugadorActual.setMiManager(null);
            }

            if (((Manager) nuevaPersona).getMiEquipo() != null)
            {
                for(Equipo equipoActual : this.miliga.getMisEquipos())
                {
                    if (equipoActual.getMiManager().getCedula().equals(cedula))
                    {
                        equipoActual.setMiManager(null);
                    }
                }
            }
        }else if (nuevaPersona instanceof Aficionado)
        {
            if (((Aficionado) nuevaPersona).getMiEquipo() != null)
            {
                for(Equipo equipoActual : this.miliga.getMisEquipos())
                {
                    for(Aficionado aficionadoActual : equipoActual.getMisAficionados())
                    {
                        if (aficionadoActual.getCedula().equals(cedula))
                        {
                            equipoActual.getMisAficionados().remove(aficionadoActual);
                        }
                    }
                }
            }
        }else if (nuevaPersona instanceof Tecnico)
        {
            for(Equipo equipoActual : this.miliga.getMisEquipos())
            {
                if(equipoActual.getMiTecnico().getCedula().equals(cedula))
                {
                    equipoActual.setMiTecnico(null);
                }
            }
        }

        this.limpiarPersona(cedula);

        boolean eliminado = this.miliga.eliminarpersona(cedula);

        if (eliminado == true)
        {
            JOptionPane.showMessageDialog(rootPane, "Persona eliminada");
            this.actualiazarcomboArbitroParaPartido();
            this.actualizarcomboPersona();
            this.actualizarTablaPersonas();
            this.actualizarTablaEquipos();
        }
    }//GEN-LAST:event_jButton61ActionPerformed

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        // TODO add your handling code here:

        String cedula = this.Textcedulapersona.getText();
        String nombre = this.Textnombrepersona.getText();
        String apellido = this.Textapellidopersona.getText();
        int edad = (Integer) this.jSedadpersona.getValue();

        Persona personaActual = this.miliga.buscarpersonaNueva(cedula);

        personaActual.setNombre(nombre);
        personaActual.setApellido(apellido);
        personaActual.setEdad(edad);

        if (personaActual instanceof Jugador) {
            this.esjugador();
            ((Jugador) personaActual).setNacionalidad(this.TextNacionalidadJugador.getText());
            ((Jugador) personaActual).setNumeroGoles((Integer) this.jSNumerogolesJugador.getValue());
            ((Jugador) personaActual).setPosicion(this.TextPosicionJugador.getText());
            ((Jugador) personaActual).setSalario(Double.parseDouble(this.TextSalarioJugador.getText()));
        } else if (personaActual instanceof Manager) {
            this.esmanager();
            ((Manager) personaActual).setAñosAfiliacion((Integer) this.jSañosafiliacionManager.getValue());

            for (Jugador jugadorActual : ((Manager) personaActual).getMisJugadores()) {
                jugadorActual.setMiManager(null);
                this.actualizarTablaManagerdeJugador(jugadorActual);
                ((Manager) personaActual).getMisJugadores().remove(jugadorActual);
            }

            for (Jugador jugadorActual : this.misjugadoresTemporales) {
                this.miliga.buscarJugadoryeliminar(jugadorActual.getCedula());
                ((Manager) personaActual).getMisJugadores().add(jugadorActual);
                jugadorActual.setMiManager((Manager) personaActual);
                this.actualizarTablaManagerdeJugador(jugadorActual);
            }

            this.actualizarcomboJugadoresparaManager();
            this.misjugadoresTemporales = new LinkedList<>();
        } else if (personaActual instanceof Aficionado) {
            this.esaficionado();
            ((Aficionado) personaActual).setAñosFidelidad((Integer) this.jSañosfidelidadAficionado.getValue());
            if (this.RBabonadono.isSelected()) {
                ((Aficionado) personaActual).setAbonado(false);
            } else if (this.RBAbonadosi.isSelected()) {
                ((Aficionado) personaActual).setAbonado(true);
            }
        } else if (personaActual instanceof Tecnico) {
            ((Tecnico) personaActual).setAñosExp((Integer) this.jSAñosexperienciaTecnico.getValue());
            ((Tecnico) personaActual).setSalario(Double.parseDouble(this.TextSalarioTecnico.getText()));
        } else if (personaActual instanceof Arbitro) {
            if (this.RBFIFAno.isSelected()) {
                ((Arbitro) personaActual).setFifa(false);
            } else if (this.RBFIFAsi.isSelected()) {
                ((Arbitro) personaActual).setFifa(true);
            }

            this.actualizartablaArbitrodePartido();
        }
        JOptionPane.showMessageDialog(rootPane, "Esta persona ha sido editada");
        this.actualizarTablaPersonas();
        this.actualizarcomboPersona();
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton59ActionPerformed
        // TODO add your handling code here:

        String Cedula = this.Textcedulapersona.getText();

        Persona personaActual = this.miliga.buscarpersonaNueva(Cedula);

        if (personaActual != null) {
            JOptionPane.showMessageDialog(rootPane, "Esta persona ha sido encontrada exitosamente");

            this.Textnombrepersona.setText(personaActual.getNombre());
            this.Textapellidopersona.setText(personaActual.getApellido());
            this.jSedadpersona.setValue(personaActual.getEdad());

            if (personaActual instanceof Jugador) {
                this.esjugador();
                this.TextNacionalidadJugador.setText(((Jugador) personaActual).getNacionalidad());
                this.TextPosicionJugador.setText(((Jugador) personaActual).getPosicion());
                this.jSNumerogolesJugador.setValue(((Jugador) personaActual).getNumeroGoles());
                this.TextSalarioJugador.setText(String.valueOf(((Jugador) personaActual).getSalario()));
                this.actualizarTablaManagerdeJugador((Jugador) personaActual);
            } else if (personaActual instanceof Manager) {
                this.esmanager();
                this.jSañosafiliacionManager.setValue(((Manager) personaActual).getAñosAfiliacion());

                for (int i = 0; i < ((Manager) personaActual).getMisJugadores().size(); i++)
                {
                    this.misjugadoresTemporales.add(((Manager) personaActual).getMisJugadores().get(i));
                }

                this.actualizarTablaJugadoresSeleccionados();

            } else if (personaActual instanceof Tecnico) {
                this.estecnico();
                this.jSAñosexperienciaTecnico.setValue(((Tecnico) personaActual).getAñosExp());
                this.TextSalarioTecnico.setText(String.valueOf(((Tecnico) personaActual).getSalario()));
            } else if (personaActual instanceof Aficionado) {
                this.esaficionado();
                this.jSañosfidelidadAficionado.setValue(((Aficionado) personaActual).getAñosFidelidad());

                if (((Aficionado) personaActual).isAbonado()) {
                    this.RBAbonadosi.setSelected(true);
                    this.RBabonadono.setSelected(false);
                } else {
                    this.RBabonadono.setSelected(true);
                    this.RBAbonadosi.setSelected(false);
                }
            } else if (personaActual instanceof Arbitro) {
                this.esarbitro();
                if (((Arbitro) personaActual).isFifa()) {
                    this.RBFIFAsi.setSelected(true);
                    this.RBFIFAno.setSelected(false);
                } else {
                    this.RBFIFAno.setSelected(true);
                    this.RBFIFAsi.setSelected(false);
                }

                this.actualizarPartidoparaArbitro((Arbitro) personaActual);
            }
        }else
        {
            JOptionPane.showMessageDialog(rootPane, "Esta persona no existe");
        }
    }//GEN-LAST:event_jButton59ActionPerformed

    private void jButton58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton58ActionPerformed
        // TODO add your handling code here:

        String nombre = this.Textnombrepersona.getText();
        String cedula = this.Textcedulapersona.getText();
        String apellido = this.Textapellidopersona.getText();
        int edad = (Integer) this.jSedadpersona.getValue();

        if(this.RBjugador.isSelected())
        {
            try
            {
                Double Salario = Double.parseDouble(this.TextSalarioJugador.getText());

                boolean existe = this.miliga.buscarpersonaActual(cedula);

                if (existe == false)
                {
                    Jugador nuevojugador = new Jugador(cedula, nombre, apellido, edad, this.TextNacionalidadJugador.getText(), this.TextPosicionJugador.getText(), (Integer) this.jSNumerogolesJugador.getValue(), Salario);

                    this.miliga.getMisPersonas().add(nuevojugador);

                    JOptionPane.showMessageDialog(rootPane, "Persona creada");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Esta persona ya existe");
                }
            } catch (Exception e)
            {
                JOptionPane.showMessageDialog(rootPane, "Inserte valor en salario y de click en agregar");
            }
        }

        if (this.RBarbitro.isSelected())
        {
            boolean existe = this.miliga.buscarpersonaActual(cedula);

            if (existe == false)
            {
                if (this.RBFIFAno.isSelected())
                {
                    this.RBFIFAsi.setSelected(false);

                    Arbitro nuevoarbitro = new Arbitro(cedula, nombre, apellido, edad, false);

                    this.miliga.getMisPersonas().add(nuevoarbitro);

                    this.actualiazarcomboArbitroParaPartido();

                    JOptionPane.showMessageDialog(rootPane, "Persona creada");

                } else if (this.RBFIFAsi.isSelected())
                {
                    this.RBFIFAno.setSelected(false);

                    Arbitro nuevoarbitro = new Arbitro(cedula, nombre, apellido, edad, true);

                    this.miliga.getMisPersonas().add(nuevoarbitro);

                    this.actualiazarcomboArbitroParaPartido();

                    JOptionPane.showMessageDialog(rootPane, "Persona creada");
                } else
                {
                    JOptionPane.showMessageDialog(rootPane, "¿Es un arbitro fifa o no?");
                }
            } else
            {
                JOptionPane.showMessageDialog(rootPane, "Esta persona ya existe");
            }

        }

        if (this.RBaficionado.isSelected())
        {
            int añosfidelidad = (Integer) this.jSañosfidelidadAficionado.getValue();

            boolean existe = this.miliga.buscarpersonaActual(cedula);

            if (existe == false)
            {
                if (this.RBAbonadosi.isSelected())
                {
                    this.RBabonadono.setSelected(false);

                    Aficionado nuevoaficionado = new Aficionado(añosfidelidad, true, cedula, nombre, apellido, edad);

                    this.miliga.getMisPersonas().add(nuevoaficionado);

                    JOptionPane.showMessageDialog(rootPane, "Persona creada");
                } else if (this.RBabonadono.isSelected())
                {
                    this.RBAbonadosi.setSelected(false);

                    Aficionado nuevoaficionado = new Aficionado(añosfidelidad, false, cedula, nombre, apellido, edad);

                    this.miliga.getMisPersonas().add(nuevoaficionado);

                    JOptionPane.showMessageDialog(rootPane, "Persona creada");
                }else
                {
                    JOptionPane.showMessageDialog(rootPane, "¿Abonado o no?");
                }
            } else
            {
                JOptionPane.showMessageDialog(rootPane, "Esta persona ya existe");
            }
        }

        if (this.RBtecnico.isSelected())
        {
            try {
                Double Salario = Double.parseDouble(this.TextSalarioTecnico.getText());

                boolean existe = this.miliga.buscarpersonaActual(cedula);

                if (existe == false) {
                    Tecnico nuevotecnico = new Tecnico(cedula, nombre, apellido, edad, (Integer) this.jSAñosexperienciaTecnico.getValue(), Salario);

                    this.miliga.getMisPersonas().add(nuevotecnico);

                    JOptionPane.showMessageDialog(rootPane, "Persona creada");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Esta persona ya existe");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Inserte valor en salario y de click en agregar");
            }
        }

        if (this.RBmanager.isSelected())
        {
            boolean existe = this.miliga.buscarpersonaActual(cedula);

            if (existe == false)
            {
                Manager nuevomanager = new Manager(cedula, nombre, apellido, edad, (Integer) this.jSañosafiliacionManager.getValue());

                for (Jugador jugadorActual : this.misjugadoresTemporales)
                {
                    if (jugadorActual.getMiManager() == null)
                    {
                        nuevomanager.getMisJugadores().add(jugadorActual);

                        for (Persona nuevaPersona : this.miliga.getMisPersonas())
                        {
                            if (nuevaPersona instanceof Jugador)
                            {
                                if (nuevaPersona.getCedula().equals(jugadorActual.getCedula()))
                                {
                                    ((Jugador) nuevaPersona).setMiManager(nuevomanager);
                                }
                            }
                        }
                    } else
                    {
                        JOptionPane.showMessageDialog(rootPane, "Asigna otro jugador y da click en agregar");
                    }
                }

                this.miliga.getMisPersonas().add(nuevomanager);
                this.misjugadoresTemporales = new LinkedList<>();
                this.actualizarcomboJugadoresparaManager();
                JOptionPane.showMessageDialog(rootPane, "Persona creada");
            } else
            {
                JOptionPane.showMessageDialog(rootPane, "Esta persona ya existe");
            }
        }

        this.actualizarcomboPersona();
        this.actualizarTablaPersonas();
    }//GEN-LAST:event_jButton58ActionPerformed

    private void TextSalarioJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextSalarioJugadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextSalarioJugadorActionPerformed

    private void TextPosicionJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPosicionJugadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPosicionJugadorActionPerformed

    private void TextNacionalidadJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNacionalidadJugadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNacionalidadJugadorActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:

        int indiceSeleccionado = this.ComboJugadoresManager.getSelectedIndex();
        Persona personaSeleccionada = this.miliga.getMisPersonas().get(indiceSeleccionado);
        this.misjugadoresTemporales.remove(personaSeleccionada);
        this.actualizarTablaJugadoresSeleccionados();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        try {
            String indiceSeleccionado = (String) this.ComboJugadoresManager.getSelectedItem();
            Persona personaSeleccionada = this.miliga.buscarpersonaNueva(indiceSeleccionado);

            System.out.println(indiceSeleccionado);
            this.misjugadoresTemporales.add((Jugador) personaSeleccionada);
            this.actualizarTablaJugadoresSeleccionados();
        } catch (Exception e)
        {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void ComboJugadoresManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboJugadoresManagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboJugadoresManagerActionPerformed

    private void TextSalarioTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextSalarioTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextSalarioTecnicoActionPerformed

    private void RBabonadonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBabonadonoActionPerformed
        // TODO add your handling code here:

        if (this.RBabonadono.isSelected()) {
            this.RBAbonadosi.setSelected(false);
        }
    }//GEN-LAST:event_RBabonadonoActionPerformed

    private void RBAbonadosiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBAbonadosiActionPerformed
        // TODO add your handling code here:

        if (this.RBAbonadosi.isSelected()) {
            this.RBabonadono.setSelected(false);
        }
    }//GEN-LAST:event_RBAbonadosiActionPerformed

    private void RBFIFAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBFIFAnoActionPerformed
        // TODO add your handling code here:

        if (this.RBFIFAno.isSelected()) {
            this.RBFIFAsi.setSelected(false);
        }
    }//GEN-LAST:event_RBFIFAnoActionPerformed

    private void RBFIFAsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBFIFAsiActionPerformed
        // TODO add your handling code here:
        if (this.RBFIFAsi.isSelected()) {
            this.RBFIFAno.setSelected(false);
        }
    }//GEN-LAST:event_RBFIFAsiActionPerformed

    private void RBjugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBjugadorActionPerformed
        // TODO add your handling code here:

        if (this.RBjugador.isSelected()) {
            this.jPanelJugador.setVisible(true);
            this.RBaficionado.setSelected(false);
            this.jPanelAficionado.setVisible(false);
            this.RBmanager.setSelected(false);
            this.jPanelManager.setVisible(false);
            this.RBtecnico.setSelected(false);
            this.jPanelTecnico.setVisible(false);
            this.jPanelArbitro.setVisible(false);
            this.RBarbitro.setSelected(false);
        }
    }//GEN-LAST:event_RBjugadorActionPerformed

    private void RBmanagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBmanagerActionPerformed
        // TODO add your handling code here:
        if (this.RBmanager.isSelected()) {
            this.jPanelManager.setVisible(true);
            this.RBaficionado.setSelected(false);
            this.jPanelAficionado.setVisible(false);
            this.RBjugador.setSelected(false);
            this.jPanelJugador.setVisible(false);
            this.RBtecnico.setSelected(false);
            this.jPanelTecnico.setVisible(false);
            this.jPanelArbitro.setVisible(false);
            this.RBarbitro.setSelected(false);
        }
    }//GEN-LAST:event_RBmanagerActionPerformed

    private void RBtecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBtecnicoActionPerformed
        // TODO add your handling code here:

        if (this.RBtecnico.isSelected()) {
            this.jPanelTecnico.setVisible(true);
            this.RBaficionado.setSelected(false);
            this.jPanelAficionado.setVisible(false);
            this.RBjugador.setSelected(false);
            this.jPanelJugador.setVisible(false);
            this.RBmanager.setSelected(false);
            this.jPanelManager.setVisible(false);
            this.jPanelArbitro.setVisible(false);
            this.RBarbitro.setSelected(false);
        }
    }//GEN-LAST:event_RBtecnicoActionPerformed

    private void RBaficionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBaficionadoActionPerformed
        // TODO add your handling code here:
        if (this.RBaficionado.isSelected())
        {
            this.jPanelAficionado.setVisible(true);
            this.RBarbitro.setSelected(false);
            this.jPanelArbitro.setVisible(false);
            this.RBjugador.setSelected(false);
            this.jPanelJugador.setVisible(false);
            this.RBmanager.setSelected(false);
            this.jPanelManager.setVisible(false);
            this.jPanelTecnico.setVisible(false);
            this.RBtecnico.setSelected(false);
        }
    }//GEN-LAST:event_RBaficionadoActionPerformed

    private void RBarbitroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBarbitroActionPerformed
        // TODO add your handling code here:
        if (this.RBarbitro.isSelected()) {
            this.jPanelArbitro.setVisible(true);
            this.RBaficionado.setSelected(false);
            this.jPanelAficionado.setVisible(false);
            this.RBjugador.setSelected(false);
            this.jPanelJugador.setVisible(false);
            this.RBmanager.setSelected(false);
            this.jPanelManager.setVisible(false);
            this.jPanelTecnico.setVisible(false);
            this.RBtecnico.setSelected(false);
        }
    }//GEN-LAST:event_RBarbitroActionPerformed

    private void TextapellidopersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextapellidopersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextapellidopersonaActionPerformed

    private void TextnombrepersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextnombrepersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextnombrepersonaActionPerformed

    private void TextcedulapersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextcedulapersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextcedulapersonaActionPerformed

    private void RBmetodo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBmetodo1ActionPerformed
        // TODO add your handling code here:
        if (this.RBmetodo1.isSelected()) {
            this.jPanelmetodo1.setVisible(true);
            this.RBmetodo2.setSelected(false);
            this.jPanelmetodo2.setVisible(false);
            this.RBmetodo3.setSelected(false);
            this.jPanelmetodo3.setVisible(false);
            this.RBmetodo4.setSelected(false);
            this.jPanelmetodo4.setVisible(false);
            this.RBmetodo5.setSelected(false);
            this.jPanelmetodo5.setVisible(false);
            this.RBmetodo6.setSelected(false);
            this.jPanelmetodo6.setVisible(false);
            this.RBmetodo7.setSelected(false);
            this.jPanelmetodo7.setVisible(false);
            this.RBmetodo8.setSelected(false);
            this.jPanelmetodo8.setVisible(false);
            this.RBmetodo9.setSelected(false);
            this.jPanelmetodo9.setVisible(false);
        }
    }//GEN-LAST:event_RBmetodo1ActionPerformed

    private void RBmetodo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBmetodo2ActionPerformed
        // TODO add your handling code here:
        if (this.RBmetodo2.isSelected()) {
            this.jPanelmetodo2.setVisible(true);
            this.RBmetodo1.setSelected(false);
            this.jPanelmetodo1.setVisible(false);
            this.RBmetodo3.setSelected(false);
            this.jPanelmetodo3.setVisible(false);
            this.RBmetodo4.setSelected(false);
            this.jPanelmetodo4.setVisible(false);
            this.RBmetodo5.setSelected(false);
            this.jPanelmetodo5.setVisible(false);
            this.RBmetodo6.setSelected(false);
            this.jPanelmetodo6.setVisible(false);
            this.RBmetodo7.setSelected(false);
            this.jPanelmetodo7.setVisible(false);
            this.RBmetodo8.setSelected(false);
            this.jPanelmetodo8.setVisible(false);
            this.RBmetodo9.setSelected(false);
            this.jPanelmetodo9.setVisible(false);
        }
    }//GEN-LAST:event_RBmetodo2ActionPerformed

    private void RBmetodo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBmetodo3ActionPerformed
        if (this.RBmetodo3.isSelected()) {
            this.jPanelmetodo3.setVisible(true);
            this.RBmetodo1.setSelected(false);
            this.jPanelmetodo1.setVisible(false);
            this.RBmetodo2.setSelected(false);
            this.jPanelmetodo2.setVisible(false);
            this.RBmetodo4.setSelected(false);
            this.jPanelmetodo4.setVisible(false);
            this.RBmetodo5.setSelected(false);
            this.jPanelmetodo5.setVisible(false);
            this.RBmetodo6.setSelected(false);
            this.jPanelmetodo6.setVisible(false);
            this.RBmetodo7.setSelected(false);
            this.jPanelmetodo7.setVisible(false);
            this.RBmetodo8.setSelected(false);
            this.jPanelmetodo8.setVisible(false);
            this.RBmetodo9.setSelected(false);
            this.jPanelmetodo9.setVisible(false);
        }
    }//GEN-LAST:event_RBmetodo3ActionPerformed

    private void RBmetodo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBmetodo4ActionPerformed
        // TODO add your handling code here:
        if (this.RBmetodo4.isSelected()) {
            this.jPanelmetodo4.setVisible(true);
            this.RBmetodo1.setSelected(false);
            this.jPanelmetodo1.setVisible(false);
            this.RBmetodo3.setSelected(false);
            this.jPanelmetodo3.setVisible(false);
            this.RBmetodo2.setSelected(false);
            this.jPanelmetodo2.setVisible(false);
            this.RBmetodo5.setSelected(false);
            this.jPanelmetodo5.setVisible(false);
            this.RBmetodo6.setSelected(false);
            this.jPanelmetodo6.setVisible(false);
            this.RBmetodo7.setSelected(false);
            this.jPanelmetodo7.setVisible(false);
            this.RBmetodo8.setSelected(false);
            this.jPanelmetodo8.setVisible(false);
            this.RBmetodo9.setSelected(false);
            this.jPanelmetodo9.setVisible(false);
        }
    }//GEN-LAST:event_RBmetodo4ActionPerformed

    private void RBmetodo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBmetodo5ActionPerformed
        // TODO add your handling code here:
        if (this.RBmetodo5.isSelected()) {
            this.jPanelmetodo5.setVisible(true);
            this.RBmetodo2.setSelected(false);
            this.jPanelmetodo2.setVisible(false);
            this.RBmetodo3.setSelected(false);
            this.jPanelmetodo3.setVisible(false);
            this.RBmetodo4.setSelected(false);
            this.jPanelmetodo4.setVisible(false);
            this.RBmetodo1.setSelected(false);
            this.jPanelmetodo1.setVisible(false);
            this.RBmetodo6.setSelected(false);
            this.jPanelmetodo6.setVisible(false);
            this.RBmetodo7.setSelected(false);
            this.jPanelmetodo7.setVisible(false);
            this.RBmetodo8.setSelected(false);
            this.jPanelmetodo8.setVisible(false);
            this.RBmetodo9.setSelected(false);
            this.jPanelmetodo9.setVisible(false);
        }
    }//GEN-LAST:event_RBmetodo5ActionPerformed

    private void RBmetodo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBmetodo6ActionPerformed
        // TODO add your handling code here:
        if (this.RBmetodo6.isSelected()) {
            this.jPanelmetodo6.setVisible(true);
            this.RBmetodo2.setSelected(false);
            this.jPanelmetodo2.setVisible(false);
            this.RBmetodo3.setSelected(false);
            this.jPanelmetodo3.setVisible(false);
            this.RBmetodo4.setSelected(false);
            this.jPanelmetodo4.setVisible(false);
            this.RBmetodo5.setSelected(false);
            this.jPanelmetodo5.setVisible(false);
            this.RBmetodo1.setSelected(false);
            this.jPanelmetodo1.setVisible(false);
            this.RBmetodo7.setSelected(false);
            this.jPanelmetodo7.setVisible(false);
            this.RBmetodo8.setSelected(false);
            this.jPanelmetodo8.setVisible(false);
            this.RBmetodo9.setSelected(false);
            this.jPanelmetodo9.setVisible(false);
        }
    }//GEN-LAST:event_RBmetodo6ActionPerformed

    private void RBmetodo7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBmetodo7ActionPerformed
        // TODO add your handling code here:
        if (this.RBmetodo7.isSelected()) {
            this.jPanelmetodo7.setVisible(true);
            this.RBmetodo2.setSelected(false);
            this.jPanelmetodo2.setVisible(false);
            this.RBmetodo3.setSelected(false);
            this.jPanelmetodo3.setVisible(false);
            this.RBmetodo4.setSelected(false);
            this.jPanelmetodo4.setVisible(false);
            this.RBmetodo5.setSelected(false);
            this.jPanelmetodo5.setVisible(false);
            this.RBmetodo6.setSelected(false);
            this.jPanelmetodo6.setVisible(false);
            this.RBmetodo1.setSelected(false);
            this.jPanelmetodo1.setVisible(false);
            this.RBmetodo8.setSelected(false);
            this.jPanelmetodo8.setVisible(false);
            this.RBmetodo9.setSelected(false);
            this.jPanelmetodo9.setVisible(false);
        }
    }//GEN-LAST:event_RBmetodo7ActionPerformed

    private void RBmetodo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBmetodo8ActionPerformed
        // TODO add your handling code here:
        if (this.RBmetodo8.isSelected()) {
            this.jPanelmetodo8.setVisible(true);
            this.RBmetodo2.setSelected(false);
            this.jPanelmetodo2.setVisible(false);
            this.RBmetodo3.setSelected(false);
            this.jPanelmetodo3.setVisible(false);
            this.RBmetodo4.setSelected(false);
            this.jPanelmetodo4.setVisible(false);
            this.RBmetodo5.setSelected(false);
            this.jPanelmetodo5.setVisible(false);
            this.RBmetodo6.setSelected(false);
            this.jPanelmetodo6.setVisible(false);
            this.RBmetodo7.setSelected(false);
            this.jPanelmetodo7.setVisible(false);
            this.RBmetodo1.setSelected(false);
            this.jPanelmetodo1.setVisible(false);
            this.RBmetodo9.setSelected(false);
            this.jPanelmetodo9.setVisible(false);
        }
    }//GEN-LAST:event_RBmetodo8ActionPerformed

    private void RBmetodo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RBmetodo9ActionPerformed
        // TODO add your handling code here:
        if (this.RBmetodo9.isSelected()) {
            this.jPanelmetodo9.setVisible(true);
            this.RBmetodo2.setSelected(false);
            this.jPanelmetodo2.setVisible(false);
            this.RBmetodo3.setSelected(false);
            this.jPanelmetodo3.setVisible(false);
            this.RBmetodo4.setSelected(false);
            this.jPanelmetodo4.setVisible(false);
            this.RBmetodo5.setSelected(false);
            this.jPanelmetodo5.setVisible(false);
            this.RBmetodo6.setSelected(false);
            this.jPanelmetodo6.setVisible(false);
            this.RBmetodo7.setSelected(false);
            this.jPanelmetodo7.setVisible(false);
            this.RBmetodo8.setSelected(false);
            this.jPanelmetodo8.setVisible(false);
            this.RBmetodo1.setSelected(false);
            this.jPanelmetodo1.setVisible(false);
        }
    }//GEN-LAST:event_RBmetodo9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Jugador joven=this.miliga.jugadorMasJoven();
        
        if (joven != null)
        {
            this.jLabeljugadorJoven.setText("El jugador más joven es "+joven.getNombre()+" con "+joven.getEdad()+" años de edad");
        }else
        {
            JOptionPane.showMessageDialog(rootPane, "No está en la base de datos");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String nombresColumnas[] = {"ID Equipo","Nombre Equipo","Promedio de edad"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.jTablepromedioEdadEquipo.setModel(miModelo);

        for (Equipo equipoActual : this.miliga.getMisEquipos()) 
        {
            if (equipoActual != null)
            {
                String fila[] = new String[nombresColumnas.length];

                fila[0] = equipoActual.getId();

                fila[1] = equipoActual.getNombre();

                fila[2] = String.valueOf(equipoActual.promedioEdad());

                miModelo.addRow(fila);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Equipo equipoNomina=null;
        equipoNomina = this.miliga.equipoNominaMasAlta();
        
        if (equipoNomina != null)
        {
            this.jLabelEquipoNominaAlta.setText("El equipo con la nómina de jugadores y técnico más alta es el equipo "+equipoNomina.getNombre()+" identificado con la id "+equipoNomina.getId());
        }else
        {
            JOptionPane.showMessageDialog(rootPane, "No está en la base de datos");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String id="";
        id=this.miliga.identificadorPartidoMasGoles();
        
        if (id != "")
        {
            this.jLabelPartidoMasGoles.setText("El identificador del partido el cual tuvo más goles es: "+id);
        }else
        {
            JOptionPane.showMessageDialog(rootPane, "No está en la base de datos");
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        int num=0;
        num=this.miliga.cantidadEquiposGoleada();
        this.jLabelpartidosGoleada.setText("La cantidad de partidos en los que un equipo ganó por goleada es de: "+num);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        Equipo equipoAficionados=null;
        equipoAficionados=this.miliga.equipoMayorCantidadAficionados();
        
        if (equipoAficionados != null)
        {
            this.jLabelEquipoMasAficionados.setText("El equipo con la mayor cantidad de aficionados es el equipo "+equipoAficionados.getNombre()+" identificado con la id "+equipoAficionados.getId());
        }else
        {
            JOptionPane.showMessageDialog(rootPane, "No está en la base de datos");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        Jugador jugadorGoles=null;
        jugadorGoles=this.miliga.jugadorMasGoles();
        
        if (jugadorGoles != null)
        {
            this.jLabeljugadorGoles.setText("El jugador que ha marcado más goles en la liga es "+jugadorGoles.getNombre()+" con "+jugadorGoles.getNumeroGoles()+" goles");
        }else
        {
            JOptionPane.showMessageDialog(rootPane, "No está en la base de datos");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        Jugador arquero=null;
        arquero=this.miliga.arqueroMenosGoles();
        
        if (arquero != null)
        {
            this.jLabelarquero.setText("El arquero al que le han marcado menos goles en la liga es "+arquero.getNombre()+" identificado con la cédula "+arquero.getCedula());
        }else
        {
            JOptionPane.showMessageDialog(rootPane, "No está en la base de datos");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        // TODO add your handling code here:
        String nombre="";
        nombre=this.miliga.nombreEstadioMasGoles();
        
        if (nombre != "")
        {
            this.jLabelestadioMasGoles.setText("El nombre del estadio donde se marcaron más goles en toda la liga es: "+nombre);
        }else
        {
            JOptionPane.showMessageDialog(rootPane, "No está en la base de datos");
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String cedula=this.Textcedulapersona.getText();
            Persona buscado=this.miliga.buscarpersonaNueva(cedula);
            
            Date fecha=new Date();
            int dia=24;
            int año=fecha.getYear()+1900;
            String mes="Mayo";
            String nombre=null;
            
            if(buscado==null){
                JOptionPane.showMessageDialog(this,"La persona buscada no existe");
            }else if(buscado instanceof Tecnico){
                
                for(Equipo equipoActual: this.miliga.getMisEquipos()){
                    if(equipoActual != null)
                    {
                        if(equipoActual.getMiTecnico() != null)
                        {
                            if(equipoActual.getMiTecnico().getCedula().equals(buscado.getCedula()))
                            {
                                nombre=equipoActual.getNombre();
                            }
                        }
                    }
                }
                
                System.out.println("Generando certificado...");
                String contenido="Colombia, "+dia+" de "+mes+" de "+año+"\n "
                        + "\n"
                        + "\n"
                        + "La Liga Nacional de Futbol certifica a quien le interese que\n"
                        + "\n"
                        + "La persona "+buscado.getNombre()+" "+buscado.getApellido()+" identificado con "+buscado.getCedula()+" se encuentra afiliado a la Liga Nacional de Fútbol desde "+((Tecnico) buscado).getFechaCreacion()+" y actúa en calidad de técnico del equipo "+nombre+"\n"
                        + "\n"
                        + "Dado en Colombia a los "+dia+" días del mes de "+mes+" de "+año+"\n"
                        + "\n"
                        + "Presidencia de la confederación";
                this.escribirArchivoPlano(contenido, "doc");
                
            }else if(buscado instanceof Jugador){
                
                for(Equipo equipoActual: this.miliga.getMisEquipos()){
                    for(Jugador jugadorActual: equipoActual.getMisJugadores()){
                        if(jugadorActual.getCedula().equals(buscado.getCedula())){
                            nombre=equipoActual.getNombre();
                        }
                    }
                }
                
                System.out.println("Generando certificado...");
                String contenido="Colombia, "+dia+" de "+mes+" de "+año+"\n "
                        + "\n"
                        + "\n"
                        + "La liga Nacional de Fútbol certifica a quien le interese que\n"
                        + "\n"
                        + "La persona "+buscado.getNombre()+" "+buscado.getApellido()+" identificado con "+buscado.getCedula()+" se encuentra afiliado a la Liga Nacional de Fútbol desde "+((Jugador) buscado).getFechaCreacion()+" y actúa en calidad de jugador del equipo "+nombre+" ocupando la posición de "+((Jugador) buscado).getPosicion()+"\n"
                        + "\n"
                        + "Dado en Colombia a los "+dia+" días del mes de "+mes+" de "+año+"\n"
                        + "\n"
                        + "Presidencia de la confederación";
                this.escribirArchivoPlano(contenido, "doc");
                
            }else if(buscado instanceof Manager){
                
                if(((Manager) buscado).getMiEquipo() != null)
                {
                    nombre=((Manager) buscado).getMiEquipo().getNombre();
                }
                
                int cantidad=((Manager) buscado).getMisJugadores().size();
                
                System.out.println("Generando certificado...");
                String contenido="Colombia, "+dia+" de "+mes+" de "+año+"\n "
                        + "\n"
                        + "\n"
                        + "La liga Nacional de Fútbol certifica a quien le interese que\n"
                        + "\n"
                        + "La persona "+buscado.getNombre()+" "+buscado.getApellido()+" identificado con "+buscado.getCedula()+" se encuentra afiliado a la Liga Nacional de Fútbil desde "+((Manager) buscado).getFechaCreacion()+" y actúa en calidad de manager (del equipo "+nombre+") y de "+cantidad+" jugadores \n"
                        + "\n"
                        + "Dado en Colombia a los "+dia+" días del mes de "+mes+" de "+año+"\n"
                        + "\n"
                        + "Presidencia de la confederación";
                this.escribirArchivoPlano(contenido, "doc");
                
            }else if(buscado instanceof Arbitro){
                
                System.out.println("Generando certificado...");
                String contenido="Colombia, "+dia+" de "+mes+" de "+año+"\n"
                        + "\n"
                        + "\n"
                        + "La liga Nacional de Fútbol certifica a quien le interese que\n"
                        + "\n"
                        + "La persona "+buscado.getNombre()+" "+buscado.getApellido()+" identificado con "+buscado.getCedula()+" se encuentra afiliado a la Liga Nacional de Fútbol desde "+((Arbitro) buscado).getFechaCreacion()+" y actúa en calidad de árbitro\n"
                        + "\n"
                        + "Dado en Colombia a los "+dia+" días del mes de "+mes+" de "+año+"\n"
                        + "\n"
                        + "Presidencia de la confederación";
                this.escribirArchivoPlano(contenido, "doc");
            }else if(buscado instanceof Aficionado){
                
                if(((Aficionado) buscado).getMiEquipo() != null)
                {
                   nombre=((Aficionado) buscado).getMiEquipo().getNombre(); 
                }
                
                String contenido="Colombia, "+dia+" de "+mes+" de "+año+"\n"
                        + "\n"
                        + "\n"
                        + "La liga Nacional de Fútbol certifica a quien le interese que\n"
                        + "\n"
                        + "La persona "+buscado.getNombre()+" "+buscado.getApellido()+" identificado con "+buscado.getCedula()+" se encuentra afiliado a la Liga Nacional de Fútbol desde "+((Aficionado) buscado).getFechaCreacion()+" y actúa en calidad de aficionado para el equipo "+nombre+" afirmando que lleva "+((Aficionado) buscado).getAñosFidelidad()+" años acompañándolo en las buenas y en las malas campañas\n"
                        + "\n"
                        + "Ddo en Colombia a los "+dia+" días del mes de "+mes+" de "+año+"\n"
                        + "\n"
                        + "Presidencia de la confederación";
                this.escribirArchivoPlano(contenido, "doc");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Método el cual ordena los equipos de mayor a menor según su cantidad de puntos y los imprime en una tabla
     * @param evt 
     */
    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        // TODO add your handling code here:
        LinkedList<Equipo> copiaEquipos=this.miliga.getMisEquipos();
        Equipo aux = null;
        for (int i = 0; i <copiaEquipos.size(); i++) {
            for (int j =0; j <copiaEquipos.size(); j++) {
                if(copiaEquipos.get(i).getPuntos()>copiaEquipos.get(j).getPuntos()){
                    aux=copiaEquipos.get(i);
                    copiaEquipos.set(i, copiaEquipos.get(j));
                    copiaEquipos.set(j,aux);
                }else if(copiaEquipos.get(i).getPuntos()==copiaEquipos.get(j).getPuntos()){
                    int suma1=copiaEquipos.get(i).getGolesAFavor()-copiaEquipos.get(i).getGolesEnContra();
                    int suma2=copiaEquipos.get(j).getGolesAFavor()-copiaEquipos.get(j).getGolesEnContra();
                    if(suma1>suma2){
                        aux=copiaEquipos.get(i);
                        copiaEquipos.set(i, copiaEquipos.get(j));
                        copiaEquipos.set(j,aux);
                    }
                }
            }
        }
        
        String nombresColumnas[] = {"ID Equipo","Nombre Equipo","Puntos"};

        DefaultTableModel miModelo = new DefaultTableModel(null, nombresColumnas);

        this.jTableOrdenEquipos.setModel(miModelo);

        for (Equipo equipoActual : copiaEquipos) 
        {
            if (equipoActual != null)
            {
                String fila[] = new String[nombresColumnas.length];

                fila[0] = equipoActual.getId();

                fila[1] = equipoActual.getNombre();

                fila[2] = String.valueOf(equipoActual.getPuntos());

                miModelo.addRow(fila);
            }
        }
        
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        // TODO add your handling code here:
        try{
        String cedula=jTextcedulajugadortransferir.getText();
        String idEquipo=jTextidequipotransferir.getText();
        this.miliga.transferirJugador(cedula, idEquipo);
        this.actualizarTablaPersonasSeleccionadas();
        this.actualizarTablaEquipos();
        JOptionPane.showMessageDialog(this,"Jugador transferido exitosamente");
        }catch(Exception e){
            System.out.println("Something went wrong");
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void TextIdjugadorEquipoVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextIdjugadorEquipoVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextIdjugadorEquipoVActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        // TODO add your handling code here:
        
        String cedula = this.TextIdjugadorEquipoV.getText();
        int v = (int) this.jSGolesEquipoVisitante.getValue();
        int n = 0; 
        
        if (this.miequipoVisitanteTemporal != null)
        {
            for (Equipo equipoActual : this.miliga.getMisEquipos()) 
            {
                if(equipoActual == this.miequipoVisitanteTemporal)
                {
                    for (Jugador jugadorActual : equipoActual.getMisJugadores()) 
                    {
                        if (jugadorActual != null) 
                        {
                            if (jugadorActual.getCedula().equals(cedula)) 
                            {
                                jugadorActual.setNumeroGoles((int) this.jSGolesJugadorEquipoV.getValue());
                                n = n + (int) this.jSGolesJugadorEquipoV.getValue();
                            }
                        }
                    }
                }
            }
        }
        
        v = v+n;
        this.jSGolesEquipoVisitante.setValue(v);
        this.actualizarTablaJugadoresenPartidoV(this.miequipoVisitanteTemporal);
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        // TODO add your handling code here:
        
       String cedula = this.TextIdjugadorEquipoV.getText();
        int v = (int) this.jSGolesEquipoVisitante.getValue();

        if (this.miequipoVisitanteTemporal != null)
        {
            for (Equipo equipoActual : this.miliga.getMisEquipos()) 
            {
                if(equipoActual == this.miequipoVisitanteTemporal)
                {
                    for (Jugador jugadorActual : equipoActual.getMisJugadores()) 
                    {
                        if (jugadorActual != null) 
                        {
                            if (jugadorActual.getCedula().equals(cedula)) 
                            {
                                int r = jugadorActual.getNumeroGoles();
                                jugadorActual.setNumeroGoles(r-(int) this.jSGolesJugadorEquipoV.getValue());
                            }
                        }
                    }
                }
            }
        }
        
        v = v-(int) this.jSGolesJugadorEquipoV.getValue();
        
        this.jSGolesEquipoVisitante.setValue(v);
        this.actualizarTablaJugadoresenPartidoV(this.miequipoVisitanteTemporal);
    }//GEN-LAST:event_jButton39ActionPerformed

    private void TextIdjugadorEquipoLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextIdjugadorEquipoLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextIdjugadorEquipoLActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        // TODO add your handling code here:
        
        String cedula = this.TextIdjugadorEquipoL.getText();
        int v = (int) this.jSGolesdeEquipoLocal.getValue();
        int n = 0; 
        
        if (this.miequipoLocalTemporal != null)
        {
            for (Equipo equipoActual : this.miliga.getMisEquipos()) 
            {
                if(equipoActual == this.miequipoLocalTemporal)
                {
                    for (Jugador jugadorActual : equipoActual.getMisJugadores()) 
                    {
                        if (jugadorActual != null) 
                        {
                            if (jugadorActual.getCedula().equals(cedula)) 
                            {
                                jugadorActual.setNumeroGoles((int) this.jSGolesJugadorL.getValue());
                                n = (int) this.jSGolesJugadorL.getValue();
                            }
                        }
                    }
                }
            }
        }
        
        v = v+n;
        
        this.jSGolesdeEquipoLocal.setValue(v);
        this.actualizarTablaJugadoresdeEquipoenPartidoL(this.miequipoLocalTemporal);
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        // TODO add your handling code here:
        
        String cedula = this.TextIdjugadorEquipoL.getText();
        int v = (int) this.jSGolesdeEquipoLocal.getValue();
 
        if (this.miequipoLocalTemporal != null)
        {
            for (Equipo equipoActual : this.miliga.getMisEquipos()) 
            {
                if(equipoActual == this.miequipoLocalTemporal)
                {
                    for (Jugador jugadorActual : equipoActual.getMisJugadores()) 
                    {
                        if (jugadorActual != null) 
                        {
                            if (jugadorActual.getCedula().equals(cedula)) 
                            {
                                int r = jugadorActual.getNumeroGoles();
                                jugadorActual.setNumeroGoles(r - (int) this.jSGolesJugadorL.getValue());
                            }
                        }
                    }
                }
            }
        }
        
        v = v-(int) this.jSGolesJugadorL.getValue();
        
        this.jSGolesdeEquipoLocal.setValue(v);
        this.actualizarTablaJugadoresdeEquipoenPartidoL(this.miequipoLocalTemporal);
    }//GEN-LAST:event_jButton46ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazLiga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazLiga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazLiga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazLiga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazLiga().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboArbitros;
    private javax.swing.JComboBox<String> ComboEquiposdePartido;
    private javax.swing.JComboBox<String> ComboEstadioparaPartido;
    private javax.swing.JComboBox<String> ComboJornadaparaPartido;
    private javax.swing.JComboBox<String> ComboJugador;
    private javax.swing.JComboBox<String> ComboJugadoresManager;
    private javax.swing.JRadioButton RBAbonadosi;
    private javax.swing.JRadioButton RBFIFAno;
    private javax.swing.JRadioButton RBFIFAsi;
    private javax.swing.JRadioButton RBabonadono;
    private javax.swing.JRadioButton RBaficionado;
    private javax.swing.JRadioButton RBarbitro;
    private javax.swing.JRadioButton RBjugador;
    private javax.swing.JRadioButton RBmanager;
    private javax.swing.JRadioButton RBmetodo1;
    private javax.swing.JRadioButton RBmetodo2;
    private javax.swing.JRadioButton RBmetodo3;
    private javax.swing.JRadioButton RBmetodo4;
    private javax.swing.JRadioButton RBmetodo5;
    private javax.swing.JRadioButton RBmetodo6;
    private javax.swing.JRadioButton RBmetodo7;
    private javax.swing.JRadioButton RBmetodo8;
    private javax.swing.JRadioButton RBmetodo9;
    private javax.swing.JRadioButton RBtecnico;
    private javax.swing.JTable TblArbitrodePartido;
    private javax.swing.JTable TblEquipodeAficionado;
    private javax.swing.JTable TblEquipodeManager;
    private javax.swing.JTable TblEquipodeTecnico;
    private javax.swing.JTable TblEquipos;
    private javax.swing.JTable TblEquiposdePartido;
    private javax.swing.JTable TblEquiposdePartido1;
    private javax.swing.JTable TblEstadioPartido;
    private javax.swing.JTable TblEstadios;
    private javax.swing.JTable TblJornadadePartido;
    private javax.swing.JTable TblJornadas;
    private javax.swing.JTable TblJugadoresEquipoL;
    private javax.swing.JTable TblJugadoresEquipoV;
    private javax.swing.JTable TblJugadoresManager;
    private javax.swing.JTable TblManagerJugador;
    private javax.swing.JTable TblParidodeArbitro;
    private javax.swing.JTable TblPartidos;
    private javax.swing.JTable TblPartidosdeEquipo;
    private javax.swing.JTable TblPartidosdeEstadio;
    private javax.swing.JTable TblPartidosporJornada;
    private javax.swing.JTable TblPersonas;
    private javax.swing.JTable TblPersonasEquipo;
    private javax.swing.JTextField TextEsloganJornada;
    private javax.swing.JTextField TextFechaPartido;
    private javax.swing.JTextField TextIDEquipo;
    private javax.swing.JTextField TextIDEstadio;
    private javax.swing.JTextField TextIDJornada;
    private javax.swing.JTextField TextIDPartido;
    private javax.swing.JTextField TextIdjugadorEquipoL;
    private javax.swing.JTextField TextIdjugadorEquipoV;
    private javax.swing.JTextField TextNacionalidadJugador;
    private javax.swing.JTextField TextNombreCiudadEstadio;
    private javax.swing.JTextField TextNombreEquipo;
    private javax.swing.JTextField TextNombreEstadio;
    private javax.swing.JTextField TextPosicionJugador;
    private javax.swing.JTextField TextSalarioJugador;
    private javax.swing.JTextField TextSalarioTecnico;
    private javax.swing.JTextField Textapellidopersona;
    private javax.swing.JTextField Textcedulapersona;
    private javax.swing.JTextField Textnombrepersona;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelEquipoMasAficionados;
    private javax.swing.JLabel jLabelEquipoNominaAlta;
    private javax.swing.JLabel jLabelPartidoMasGoles;
    private javax.swing.JLabel jLabelarquero;
    private javax.swing.JLabel jLabelestadioMasGoles;
    private javax.swing.JLabel jLabeljugadorGoles;
    private javax.swing.JLabel jLabeljugadorJoven;
    private javax.swing.JLabel jLabelpartidosGoleada;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAficionado;
    private javax.swing.JPanel jPanelAggjugadoresEquipo;
    private javax.swing.JPanel jPanelArbitro;
    private javax.swing.JPanel jPanelJugador;
    private javax.swing.JPanel jPanelManager;
    private javax.swing.JPanel jPanelTecnico;
    private javax.swing.JPanel jPanelmetodo1;
    private javax.swing.JPanel jPanelmetodo2;
    private javax.swing.JPanel jPanelmetodo3;
    private javax.swing.JPanel jPanelmetodo4;
    private javax.swing.JPanel jPanelmetodo5;
    private javax.swing.JPanel jPanelmetodo6;
    private javax.swing.JPanel jPanelmetodo7;
    private javax.swing.JPanel jPanelmetodo8;
    private javax.swing.JPanel jPanelmetodo9;
    private javax.swing.JSpinner jSAñosexperienciaTecnico;
    private javax.swing.JSpinner jSCapacidadEstadio;
    private javax.swing.JSpinner jSGolesAfavor;
    private javax.swing.JSpinner jSGolesEncontra;
    private javax.swing.JSpinner jSGolesEquipoVisitante;
    private javax.swing.JSpinner jSGolesJugadorEquipoV;
    private javax.swing.JSpinner jSGolesJugadorL;
    private javax.swing.JSpinner jSGolesdeEquipoLocal;
    private javax.swing.JSpinner jSNumeroJornada;
    private javax.swing.JSpinner jSNumerogolesJugador;
    private javax.swing.JSpinner jSPartidosJugados;
    private javax.swing.JSpinner jSPuntosEquipo;
    private javax.swing.JSpinner jSañofundacionEquipo;
    private javax.swing.JSpinner jSañosafiliacionManager;
    private javax.swing.JSpinner jSañosfidelidadAficionado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane36;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSedadpersona;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jStitulosinternacionesEquipo;
    private javax.swing.JSpinner jStitulosnacionalesEquipo;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTable jTable21;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTableOrdenEquipos;
    private javax.swing.JTable jTablepromedioEdadEquipo;
    private javax.swing.JTextField jTextcedulajugadortransferir;
    private javax.swing.JTextField jTextidequipotransferir;
    // End of variables declaration//GEN-END:variables
}
