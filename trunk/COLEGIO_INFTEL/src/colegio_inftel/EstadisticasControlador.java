/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio_inftel;

import es.uma.masterinftel.colegio_inftel.vistas.EstadisticasVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Proyectos
 */
public class EstadisticasControlador {
     //Necesitamos que el controlador interactue con el Modelo y la vista
    private EstadisticasModelo m_modelo;
    private EstadisticasVista m_vista;
    
    /** Constructor */
    public EstadisticasControlador(EstadisticasModelo modelo, EstadisticasVista vista){
        m_modelo = modelo;
        m_vista = vista;

        //Aquí se localizan los métodos de escucha
        m_vista.addEstadistica1Listener(new Estadistica1Listener());
        m_vista.addEstadistica2Listener(new Estadistica2Listener());
        m_vista.addEstadistica3Listener(new Estadistica3Listener());
        m_vista.addEstadistica4Listener(new Estadistica4Listener());

        cargarCombos();
    }


    private void cargarCombos(){





    }


    public class Estadistica1Listener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
    public class Estadistica2Listener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
    public class Estadistica3Listener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
    public class Estadistica4Listener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }
}
