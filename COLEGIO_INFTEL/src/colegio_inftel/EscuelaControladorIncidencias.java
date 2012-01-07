/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package colegio_inftel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author BlackCrystal™
 */
public class EscuelaControladorIncidencias {

    private EscuelaModeloIncidencias modelo;
    private  EscuelaVistaIncidencias vista;

    public EscuelaControladorIncidencias(EscuelaModeloIncidencias modelo, final EscuelaVistaIncidencias vista){

        this.modelo = modelo;
        this.vista = vista;

        vista.addConfirmarListener(new ConfirmarListener());
        vista.addCancelarListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {

                vista.setVisible(false);
                System.exit(0);
            }

        });
    }
    
    public class ConfirmarListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            modelo.modificarIncidenciasAlumno(vista.getFaltas(), vista.getRetrasos(), vista.getSanciones(), vista.getComentarios());
            vista.setVisible(false);
        }

    }
    
}
