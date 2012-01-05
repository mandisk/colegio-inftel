

package colegio_inftel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
        m_vista.addCambioValorNotaListener(new CambioNotaListener());
    }


    class GuardarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //codigo
            System.out.println("Boton Guardar pulsado");
        }

    }

    class CerrarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //codigo
            System.out.println("Boton Cerrar pulsado");
        }

    }


    class CambioNotaListener implements PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent evt) {
            System.out.println("el valor ha cambiado");
        }
    }



}
