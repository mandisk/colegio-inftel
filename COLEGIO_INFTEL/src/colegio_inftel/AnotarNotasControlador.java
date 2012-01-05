

package colegio_inftel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.print.attribute.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

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


    class LimitadorCaracteres extends PlainDocument
    {
       /**
        * Método al que llama el editor cada vez que se intenta insertar caracteres.
        * Sólo debemos verificar arg1, que es la cadena que se quiere insertar en el JTextField
        */
       public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException
       {
           for (int i=0;i<arg1.length();i++)
              // si no es digit, volvemos
              if (!Character.isDigit(arg1.charAt(i)))
                 return;

           // Si todos son digit, insertamos el texto en el JTextField
           super.insertString(arg0, arg1,(javax.swing.text.AttributeSet) arg2);
       }
    }



}
