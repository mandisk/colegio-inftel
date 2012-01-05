

package colegio_inftel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
        m_vista.addValidarNotasKeyTyped(new ValidarNotasListener());

    }


    private boolean notaValida(String text) {

        boolean ok=false;
        int posPuntoDecimal=text.indexOf(".");
        String decimales = "";


        if (posPuntoDecimal>0){
            decimales=text.substring(posPuntoDecimal+1);
        }

        try{
            if(text.length()<=5 && decimales.length()<3){
                Float nota = Float.parseFloat(text);
                if ((nota<=10 && nota >=0)){
                    ok=true;
                }
            }
        }catch (NumberFormatException e){
            ok=false;
        }

        return ok;
    }






    class GuardarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Boton Guardar pulsado");
        }

    }

    class CerrarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Boton Cerrar pulsado");
        }

    }


    class ValidarNotasListener implements KeyListener {

        public void keyTyped(KeyEvent evt) {

            if (!notaValida(m_vista.getNota1()+evt.getKeyChar())){
                evt.consume(); //ignorar la tecla pulsada
            }
        }

        public void keyPressed(KeyEvent arg0) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }

        public void keyReleased(KeyEvent arg0) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }

    }


}
