/**
 * AnotarNotasControlador.java
 *
 */

package es.uma.masterinftel.colegio_inftel.control;

import es.uma.masterinftel.colegio_inftel.vistas.AnotarNotasVista;
import com.mysql.jdbc.Connection;
import es.uma.masterinftel.colegio_inftel.modelo.dao.CalificacionesDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dto.CalificacionesDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador para la vista/modelo Anotar Notas
 *
 * @author Agustín Pereña
 */
public class AnotarNotasControlador {

    private CalificacionesDAO   m_modelo;
    private AnotarNotasVista    m_vista;

    public AnotarNotasControlador(CalificacionesDAO modelo, AnotarNotasVista vista){

        m_modelo = modelo;
        m_vista  = vista;

        m_vista.addGuardarListener(new GuardarListener());
        m_vista.addCerrarListener(new CerrarListener());
        m_vista.addValidarNotasKeyTyped(new ValidarNotasListener());
        m_vista.addFormatearNota( new FormatearNotas());

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

    private Double calcularMedia(ArrayList<Double> notas){
        Double media= new Double(0.0);

        for(Double i:notas){
            media+=i;
        }
        media=media/notas.size();

        return media;
    }


    class GuardarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            Runnable miRunnable = new Runnable(){

                public void run() {

                    try{
                       
                        CalificacionesDTO dto = new CalificacionesDTO();
                        Connection cnn = (Connection) Conexion.conectar();

                        dto.setAnio_mat_fk(m_vista.getAnio_mat());
                        dto.setId_alumno_fk(m_vista.getId_alumno());
                        dto.setCodasignatura_fk(m_vista.getCodasignatura());

                        dto.setNota_p1(m_vista.getN1());
                        dto.setNota_p2(m_vista.getN2());
                        dto.setNota_p3(m_vista.getN3());
                        dto.setNota_final(m_vista.getNFinal());

                        try {
                            System.out.println(dto.toString());
                            m_modelo.update(dto, cnn);
                        } catch (SQLException ex) {
                            Logger.getLogger(AnotarNotasControlador.class.
                                        getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };

            Thread hilo = new Thread(miRunnable);
            hilo.start();
        }
    }

    class CerrarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            m_vista.setVisible(false);
        }

    }


    class ValidarNotasListener implements KeyListener {

        public void keyTyped(KeyEvent evt) {

            Object origen = evt.getSource();
            Double media;

            if(origen==m_vista.nota1){
                if (!notaValida(m_vista.getNota1()+evt.getKeyChar())){
                    evt.consume(); //ignorar la tecla pulsada
                }
            } else if (origen==m_vista.nota2) {
                if (!notaValida(m_vista.getNota2()+evt.getKeyChar())){
                    evt.consume(); //ignorar la tecla pulsada
                }
            } else if (origen==m_vista.nota3 ){
                if (!notaValida(m_vista.getNota3()+evt.getKeyChar())){
                    evt.consume(); //ignorar la tecla pulsada
                }
            }

        }

        public void keyPressed(KeyEvent arg0) {
           // throw new UnsupportedOperationException("No soportado");
            
        }

        public void keyReleased(KeyEvent arg0) {
            
            Double media = calcularMedia(m_vista.getNotas());
            DecimalFormat formateador = new DecimalFormat("##.##");
            DecimalFormatSymbols dfs = formateador.getDecimalFormatSymbols();
            dfs.setDecimalSeparator('.');
            formateador.setDecimalFormatSymbols(dfs);

            m_vista.setNotaFinal(formateador.format(media).toString());

        }

    }


    class FormatearNotas implements FocusListener {

        public void focusGained(FocusEvent e) {
            //throw new UnsupportedOperationException("No Soportado");
        }

        public void focusLost(FocusEvent e) {
           Object origen = e.getSource();

           if (origen==m_vista.nota1){
                m_vista.setNota1(m_vista.getN1().toString());
           } else if (origen==m_vista.nota2){
                m_vista.setNota2(m_vista.getN2().toString());
           } else if (origen==m_vista.nota3){
                m_vista.setNota3(m_vista.getN3().toString());
           }
           
        }

    }


}
