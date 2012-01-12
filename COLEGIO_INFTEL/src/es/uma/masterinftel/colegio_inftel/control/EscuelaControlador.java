/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package colegio_inftel;

package es.uma.masterinftel.colegio_inftel.control;

import es.uma.masterinftel.colegio_inftel.modelo.dao.MatriculacionesDAO;
//import java.sql.Connection;
import com.mysql.jdbc.Connection;
import es.uma.masterinftel.colegio_inftel.modelo.dao.CalificacionesDAO;
import es.uma.masterinftel.colegio_inftel.vistas.AnotarNotasVista;
import es.uma.masterinftel.colegio_inftel.modelo.dao.EscuelaModeloDAO;
import es.uma.masterinftel.colegio_inftel.vistas.EscuelaVistaPrincipal;
import java.awt.event.*;
import javax.swing.table.TableModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import es.uma.masterinftel.colegio_inftel.modelo.dto.EscuelaModeloDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
//import es.uma.masterinftel.colegio_inftel.modelo.dao.NotasDAO;
/**
 *
 * @author Proyectos
 */
public class EscuelaControlador {
  
    //Necesitamos que el controlador interactue con el Modelo y la vista
    private EscuelaModeloDAO m_modelo;
    private EscuelaVistaPrincipal m_vista;
    
    //Variables inicializadas
    int curso=1;
    int grupo=1;
    int asignatura=1;

    //Formulario Anotar Calificaciones
    protected CalificacionesDAO        m_calificaciones;
    protected AnotarNotasVista         v_calificaciones;
    protected AnotarNotasControlador   c_calificaciones;

    
    /** Constructor */
    public EscuelaControlador(EscuelaModeloDAO modelo, EscuelaVistaPrincipal vista){
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
      //  vista.addTableListener(new GetTableListener());
    }
    
    class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int[] valasig = {0,5,10,15,20,25};
            EscuelaModeloDTO dto = new EscuelaModeloDTO();
           //Hay que utilizar getItem()
            curso = m_vista.getCurso();
            grupo = m_vista.getGrupo();
            asignatura = m_vista.getAsignatura();
            dto.setId_cursos_fk(curso);
            dto.setId_grupo_kf(grupo);
            dto.setCodasignatura_fk(valasig[curso-1]+asignatura);
            m_modelo.rellenaTabla(dto);
            //Ocultamos columna id
            m_vista.jTable1.getColumnModel().getColumn(10).setMaxWidth(0);
            m_vista.jTable1.getColumnModel().getColumn(10).setMinWidth(0);
            m_vista.jTable1.getColumnModel().getColumn(10).setPreferredWidth(0);
            //Hay que activar rellenaTabla
            // m_modelo.rellenaTabla(curso, grupo,valasig[curso-1]+asignatura);     
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
        try {
            rf = RowFilter.regexFilter(m_vista.getNombre(),0,1,2);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        m_modelo.sorter.setRowFilter(rf);
    }
      
  }
    
    /**
     * Clase listener para ejecutar la acción del botón Editar Calificaiones
     * 
     */

    class CalificacionesListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Connection cnn = (Connection) Conexion.conectar();
            int filaselect = m_vista.getFila();
            int idAlumno = (Integer)m_vista.getTableData(filaselect, 10);
            String nombre = (String)m_vista.getTableData(filaselect, 0);
            String apellido1 = (String)m_vista.getTableData(filaselect, 1) ;
            String apellido2 = (String)m_vista.getTableData(filaselect, 2);
            String nota1 = (m_vista.getTableData(filaselect, 6)).toString();
            String nota2 = (m_vista.getTableData(filaselect, 7)).toString();
            String nota3 = (m_vista.getTableData(filaselect, 8)).toString();
            String notaFinal = (m_vista.getTableData(filaselect, 9)).toString();
            MatriculacionesDAO objMatricula = new MatriculacionesDAO();
            int anioMatricula;
            try{
            anioMatricula = objMatricula.obtener_anio_matricula(cnn);
            System.out.println("Pulsado");
            
            v_calificaciones.setAnio_mat(anioMatricula);
            v_calificaciones.setId_alumno(idAlumno);
      //      v_calificaciones.setCodasignatura(dto.getCodasignatura_fk());

            v_calificaciones.setAlumno(nombre+" "+apellido1);
            v_calificaciones.setGrupo("C");
            v_calificaciones.setAsignatura("Matemáticas");
            v_calificaciones.setCurso("4º de ESO");
            v_calificaciones.setNota1(nota1);
            v_calificaciones.setNota2(nota2);
            v_calificaciones.setNota3(nota3);
            v_calificaciones.setNotaFinal(notaFinal);

            v_calificaciones.setVisible(true);
             } catch (Exception ev)
        {
            ev.printStackTrace();
        }
        }

    }




}
