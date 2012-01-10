/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio_inftel;
import es.uma.masterinftel.colegio_inftel.control.AnotarNotasControlador;
import es.uma.masterinftel.colegio_inftel.modelo.dao.CalificacionesDAO;
import es.uma.masterinftel.colegio_inftel.vistas.AnotarNotasVista;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
/**
 *
 * @author Proyectos
 */
public class EscuelaControlador {
  
    //Necesitamos que el controlador interactue con el Modelo y la vista
    private EscuelaModelo m_modelo;
    private EscuelaVistaPrincipal m_vista;

    //Formulario Anotar Calificaciones
    protected CalificacionesDAO        m_calificaciones;
    protected AnotarNotasVista         v_calificaciones;
    protected AnotarNotasControlador   c_calificaciones;

    
    /** Constructor */
    public EscuelaControlador(EscuelaModelo modelo, EscuelaVistaPrincipal vista){
        m_modelo = modelo;
        m_vista = vista;

        //Creación del formulario Anotar Calificaciones
        m_calificaciones = new CalificacionesDAO();
        v_calificaciones = new AnotarNotasVista(m_calificaciones, m_vista, true);
        c_calificaciones = new AnotarNotasControlador(m_calificaciones,v_calificaciones);



        //Aquí se localizan los métodos de escucha
        vista.addSearchListener(new SearchListener());
        vista.addFilterName(new FilterListener());
        vista.addAnotarNotasListener(new CalificacionesListener());
    }
    
    class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int curso;
            int grupo;
            int asignatura;
            int[] valasig = {0,5,10,15,20,25};
            //DefaultTableModel modelo = new DefaultTableModel();
           
            curso = m_vista.getCurso();
            grupo = m_vista.getGrupo();
            asignatura = m_vista.getAsignatura();
            m_modelo.rellenaTabla(curso, grupo,valasig[curso-1]+asignatura);
            //m_modelo.rellenaTabla(2,1,6);
            
            
        }
    }
     class FilterListener implements DocumentListener {
         public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
     private void newFilter() {
       m_vista.jTable1.setRowSorter(m_modelo.sorter);
         RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(m_vista.getNombre(),0,1,2);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        m_modelo.sorter.setRowFilter(rf);
    }
         // public void actionPerformed(ActionEvent e) {
       //     TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(m_modelo.modelo);
       //     m_vista.jTable1.setRowSorter(sorter); 
       //     String nombre = m_vista.getNombre();
      //  if (nombre.length() == 0) {
       //   sorter.setRowFilter(null);
      //  } else {
      //    sorter.setRowFilter(RowFilter.regexFilter(nombre));
     //   }
     //    }
        }

    /**
     * Clase listener para ejecutar la acción del botón Editar Calificaiones
     * 
     */

    class CalificacionesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("Pulsado");

            v_calificaciones.setAnio_mat(2007);
            v_calificaciones.setId_alumno(31);
            v_calificaciones.setCodasignatura(1);

            v_calificaciones.setAlumno("Agustín Pereña");
            v_calificaciones.setGrupo("C");
            v_calificaciones.setAsignatura("Matemáticas");
            v_calificaciones.setCurso("4º de ESO");
            v_calificaciones.setNota1("7.0");
            v_calificaciones.setNota2("7.0");
            v_calificaciones.setNota3("7.0");
            v_calificaciones.setNotaFinal("7.0");

            v_calificaciones.setVisible(true);
        }

    }




}
