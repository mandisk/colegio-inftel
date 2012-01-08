/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package colegio_inftel;

/**
 *
 * @author agustinpg
 */
public class AnotarNotasMVC {

        public static void main(String[] args) {

            java.awt.EventQueue.invokeLater(new Runnable() {



                AnotarNotasModelo modelo = new AnotarNotasModelo();
                AnotarNotasVista vista = new AnotarNotasVista(modelo);
                public void run() {
                    vista.setVisible(true);
                }
               // AnotarNotasControlador controlador = new AnotarNotasControlador(modelo,vista);





            });

        }
}





