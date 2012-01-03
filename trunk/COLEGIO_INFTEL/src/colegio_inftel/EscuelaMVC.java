/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio_inftel;

/**
 *
 * @author Proyectos
 */
public class EscuelaMVC {
   
    public static void main(String[] args) {
       
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            EscuelaModelo modelo = new EscuelaModelo();
            EscuelaVistaPrincipal vista = new EscuelaVistaPrincipal(modelo);
            public void run() {
                vista.setVisible(true);
            }
            EscuelaControlador controlador = new EscuelaControlador(modelo,vista);
        });
        
       
    }
    
}
