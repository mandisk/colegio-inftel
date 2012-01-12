/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.control;

import colegio_inftel.*;
import com.mysql.jdbc.Connection;
import es.uma.masterinftel.colegio_inftel.modelo.dao.AsignaturasDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dao.CalificacionesDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dao.CursosDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dao.MatriculacionesDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dao.ProfesoresDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dto.AsignaturasDTO;
import es.uma.masterinftel.colegio_inftel.modelo.dto.CursosDTO;
import es.uma.masterinftel.colegio_inftel.modelo.dto.ProfesoresDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Asignatura;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import es.uma.masterinftel.colegio_inftel.utilidades.Curso;
import es.uma.masterinftel.colegio_inftel.utilidades.Profesor;
import es.uma.masterinftel.colegio_inftel.vistas.EstadisticasVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Proyectos
 */
public class EstadisticasControlador {
     //Necesitamos que el controlador interactue con el Modelo y la vista
    //private EstadisticasModelo m_modelo;
    private EstadisticasVista m_vista;
    
    /** Constructor */
    public EstadisticasControlador( EstadisticasVista vista) throws SQLException{
        //m_modelo = modelo;
        m_vista = vista;

        //Aquí se localizan los métodos de escucha
        m_vista.addEstadistica1Listener(new Estadistica1Listener());
        m_vista.addEstadistica2Listener(new Estadistica2Listener());
        m_vista.addEstadistica3Listener(new Estadistica3Listener());
        m_vista.addEstadistica4Listener(new Estadistica4Listener());

        cargarCombos();
    }


    private void cargarCombos() throws SQLException{
        ArrayList anios = new ArrayList();
        ArrayList profesores = new ArrayList();
        ArrayList asignaturas = new ArrayList();
        ArrayList cursos = new ArrayList();

        cargarCombosAnio(anios);
        cargarComboProfesores(profesores);
        cargarComboAsignaturas(asignaturas);
        cargarComboCursos(cursos);
    }
     //método para cargar combos

    //método para cargar combo años matriculacion
    public void cargarCombosAnio( ArrayList anios) throws SQLException, SQLException{
        //CARGA DEL COMBO AÑOS MATRICULACION
        MatriculacionesDAO matriculacionesDAO = new MatriculacionesDAO();
        Connection cnn = (Connection) Conexion.conectar();
        anios = (ArrayList) matriculacionesDAO.obtener_anios_matriculaciones(cnn);

        Iterator i = anios.iterator();
        while (i.hasNext()) {

            Integer x = (Integer) i.next();
            m_vista.getAnioMatriculadosComboBox().addItem(new Integer(x));
        }

        //Seleccionamos por defecto el año en curso
        m_vista.getAnioMatriculadosComboBox().setSelectedIndex(anios.size() - 1);

    }
    //método para cargar combo profesores
    public void cargarComboProfesores(ArrayList profesores) throws SQLException{

         // CARGA DEL COMBO PROFESORES
        ProfesoresDAO profesoresDAO = new ProfesoresDAO();
        profesores = profesoresDAO.obtenerProfesores();
        Iterator j = profesores.iterator();
        ProfesoresDTO profesoresDTO = new ProfesoresDTO();

        while (j.hasNext()) {
            Profesor profesor = new Profesor();
            profesoresDTO = (ProfesoresDTO) j.next();
            profesor.nombre = profesoresDTO.getNombre() + " " + profesoresDTO.getApellido1() + " " + profesoresDTO.getApellido2();
            profesor.id = profesoresDTO.getId();
            m_vista.getProfesorComboBox().addItem(profesor);
        }

        Profesor profesor = (Profesor) m_vista.getProfesorComboBox().getSelectedItem();
        System.out.println("ID: " + profesor.id);
    }
    //método para cargar combo asignaturas
    public void cargarComboAsignaturas(ArrayList asignaturas) throws SQLException{

        AsignaturasDAO asignaturasDAO = new AsignaturasDAO();
        asignaturas = asignaturasDAO.obtenerAsignaturas(Conexion.conectar());
        Iterator j = asignaturas.iterator();
        AsignaturasDTO asignaturasDTO = new AsignaturasDTO();

        while (j.hasNext()) {
            
            asignaturasDTO = (AsignaturasDTO) j.next();
            Asignatura asignatura = new Asignatura(asignaturasDTO.getDesc(),asignaturasDTO.getCodasignatura());
            m_vista.asignaturaComboBox.addItem(asignatura);
        }
        Asignatura asignatura = (Asignatura) m_vista.asignaturaComboBox.getSelectedItem();
        System.out.println("ID: " + asignatura.getId());

    }
    //método para cargar combo cursos
    public void cargarComboCursos(ArrayList cursos){

        CursosDAO cursoDAO = new CursosDAO();
        cursos = cursoDAO.obtenerCursos(Conexion.conectar());
        Iterator j = cursos.iterator();
        CursosDTO cursosDTO = new CursosDTO();

        while (j.hasNext()) {

            cursosDTO = (CursosDTO) j.next();
            Curso curso = new Curso(cursosDTO.getDesc(),cursosDTO.getId());
            m_vista.cursoComboBox.addItem(curso);
        }
        Curso curso = (Curso) m_vista.asignaturaComboBox.getSelectedItem();
        System.out.println("ID: " + curso.getId());

    }

    public class Estadistica1Listener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            Connection cnn = (Connection) Conexion.conectar();
            CalificacionesDAO calificacion = new CalificacionesDAO();

        try {

            Asignatura asignatura = (Asignatura) m_vista.asignaturaComboBox.getSelectedItem();
            Curso curso = (Curso) m_vista.cursoComboBox.getSelectedItem();
            Integer numAprobados = null;
                try {
                    numAprobados = calificacion.numAprobados(cnn, asignatura.getId(),
                                                            (Integer) m_vista.getAnioMatriculadosComboBox().getSelectedItem(),
                                                            curso.getId());
                } catch (SQLException ex) {
                    Logger.getLogger(EstadisticasControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            Integer numMatriculados = calificacion.numMatriculados(cnn,asignatura.getId(),
                                                                  (Integer) m_vista.getAnioMatriculadosComboBox().getSelectedItem(),
                                                                  (Integer) curso.getId());

            float porcAprobados = ((float) numAprobados / (float) numMatriculados) * 100;
            float porcSuspensos = (((float) numMatriculados - (float) numAprobados) / (float) numMatriculados) * 100;

            //Crear un dataset
            DefaultPieDataset data = new DefaultPieDataset();
            data.setValue("Aprobados", porcAprobados);
            data.setValue("Suspensos", porcSuspensos);

            //Creamos un Chart
            JFreeChart chart = ChartFactory.createPieChart(
                    "(%) Aprobados y Suspensos en "
                    + m_vista.asignaturaComboBox.getSelectedItem()
                    + " de "+ m_vista.cursoComboBox.getSelectedItem()+" ("
                    + m_vista.getAnioMatriculadosComboBox().getSelectedItem() + ")", //Títrulo del gráfico
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
    public class Estadistica2Listener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            Connection cnn = (Connection) Conexion.conectar();
            CalificacionesDAO calificacion = new CalificacionesDAO();

        try {

            Profesor profesor = (Profesor) m_vista.getProfesorComboBox().getSelectedItem();

            Integer numAprobados = null;
                try {
                    numAprobados = calificacion.numAprobados(cnn, profesor.id, (Integer) m_vista.getAnioMatriculadosComboBox().getSelectedItem());
                } catch (SQLException ex) {
                    Logger.getLogger(EstadisticasControlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            Integer numMatriculados = calificacion.numMatriculados(cnn, profesor.id, (Integer) m_vista.getAnioMatriculadosComboBox().getSelectedItem());

            float porcAprobados = ((float) numAprobados / (float) numMatriculados) * 100;
            float porcSuspensos = (((float) numMatriculados - (float) numAprobados) / (float) numMatriculados) * 100;

            //Crear un dataset
            DefaultPieDataset data = new DefaultPieDataset();
            data.setValue("Aprobados", porcAprobados);
            data.setValue("Suspensos", porcSuspensos);

            //Creamos un Chart
            JFreeChart chart = ChartFactory.createPieChart(
                    "(%) Aprobados y Suspensos de "
                    + m_vista.getProfesorComboBox().getSelectedItem() + " ("
                    + m_vista.getAnioMatriculadosComboBox().getSelectedItem() + ")", //Títrulo del gráfico
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
