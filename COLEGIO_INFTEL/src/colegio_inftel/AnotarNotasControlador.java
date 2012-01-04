

package colegio_inftel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador para la vista/modelo Anotar Notas
 *
 * @author agumpg
 */
public class AnotarNotasControlador {

    private AnotarNotasModelo   m_modelo;
    private AnotarNotasVista    m_vista;


    public AnotarNotasControlador(AnotarNotasModelo modelo, AnotarNotasVista vista){

        m_modelo = modelo;
        m_vista = vista;

        m_vista.addGuardarListener(new GuardarListener());
        m_vista.addCerrarListener(new CerrarListener());
        
    }


    class GuardarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //codigo
        }

    }


    class CerrarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //codigo
        }

    }





}
