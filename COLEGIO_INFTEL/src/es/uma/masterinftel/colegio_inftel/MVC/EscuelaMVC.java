/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.MVC;

import es.uma.masterinftel.colegio_inftel.modelo.dao.EscuelaModeloDAO;
import es.uma.masterinftel.colegio_inftel.control.*;
import es.uma.masterinftel.colegio_inftel.vistas.EscuelaVistaPrincipal;


/**
 *
 * @author Proyectos
 */
public class EscuelaMVC {
   
    public static void main(String[] args) {
       
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            EscuelaModeloDAO modelo = new EscuelaModeloDAO();
            EscuelaVistaPrincipal vista = new EscuelaVistaPrincipal(modelo);
            public void run() {
                vista.setVisible(true);
            }
            EscuelaControlador controlador = new EscuelaControlador(modelo,vista);
        });
        
       
    }
    
}
