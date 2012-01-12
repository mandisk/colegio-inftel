/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.control;

import colegio_inftel.*;
import com.mysql.jdbc.Connection;
import es.uma.masterinftel.colegio_inftel.modelo.dao.CalificacionesDAO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import es.uma.masterinftel.colegio_inftel.utilidades.Profesor;
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
            Connection cnn = (Connection) Conexion.conectar();
            CalificacionesDAO calificacion = new CalificacionesDAO();

        try {

            Profesor profesor = (Profesor) m_vista.getProfesorComboBox().getSelectedItem();

            Integer numAprobados = calificacion.numAprobados(cnn, profesor.id, (Integer) comboAnios.getSelectedItem());
            Integer numMatriculados = calificacion.numMatriculados(cnn, profesor.id, (Integer) comboAnios.getSelectedItem());

            float porcAprobados = ((float) numAprobados / (float) numMatriculados) * 100;
            float porcSuspensos = (((float) numMatriculados - (float) numAprobados) / (float) numMatriculados) * 100;

            //Crear un dataset
            DefaultPieDataset data = new DefaultPieDataset();
            data.setValue("Aprobados", porcAprobados);
            data.setValue("Suspensos", porcSuspensos);

            //Creamos un Chart
            JFreeChart chart = ChartFactory.createPieChart(
                    "(%) Aprobados y Suspensos de "
                    + comboProfesores.getSelectedItem() + " ("
                    + comboAnios.getSelectedItem() + ")", //Títrulo del gráfico
                    data,
                    true,//Leyenda
                    true,//ToolTips
                    true);
            //Creamos una especie de frame y mostramos el JFreeChart en él
            //Este constructor nos pide el título del Chart y el chart creado
            ChartFrame frame = new ChartFrame("Primer Chart para javax0711", chart);
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Se ha producido un error de Base de Datos");
        }
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
