/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package colegio_inftel;


/**
 *
 * @author BlackCrystal™
 */
public class EscuelaMVCIncidencias {


     /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            EscuelaModeloIncidencias modelo = new EscuelaModeloIncidencias();
            EscuelaVistaIncidencias vista= new EscuelaVistaIncidencias(modelo);
            public void run() {
                vista.setVisible(true);
            }
            EscuelaControladorIncidencias controlador = new EscuelaControladorIncidencias(modelo, vista);
        });
    }
}
