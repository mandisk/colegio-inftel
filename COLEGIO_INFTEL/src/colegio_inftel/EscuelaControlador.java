/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio_inftel;
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
    
    /** Constructor */
    public EscuelaControlador(EscuelaModelo modelo, EscuelaVistaPrincipal vista){
    m_modelo = modelo;
    m_vista = vista;
    
    //Aquí se localizan los métodos de escucha
    vista.addSearchListener(new SearchListener());
    vista.addFilterName(new FilterListener());
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
}
