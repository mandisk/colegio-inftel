/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio_inftel;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
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
    
}
