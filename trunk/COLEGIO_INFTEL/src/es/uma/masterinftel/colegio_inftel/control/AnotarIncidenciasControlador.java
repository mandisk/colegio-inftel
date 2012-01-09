package es.uma.masterinftel.colegio_inftel.control;


import es.uma.masterinftel.colegio_inftel.modelo.dto.IncidenciasDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import es.uma.masterinftel.colegio_inftel.vistas.AnotarIncidenciasVista;
import es.uma.masterinftel.colegio_inftel.modelo.dao.IncidenciasDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BlackCrystal™
 */
public class AnotarIncidenciasControlador {

    private IncidenciasDAO modelo;
    private AnotarIncidenciasVista vista;
    private int anio_mat=2007;
    private int id_alumno=31;

    public AnotarIncidenciasControlador(IncidenciasDAO modelo, AnotarIncidenciasVista vista){

        this.modelo=modelo;
        this.vista=vista;

        vista.addCancelarListener(new CerrarListener());
        vista.addConfirmarListener(new ConfirmarListener());
    }
        public class ConfirmarListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
             Runnable miRunnable = new Runnable(){

                public void run() {

                    try{

                        IncidenciasDTO dto = new IncidenciasDTO();
                        Connection cnn = (Connection) Conexion.conectar();

                        dto.setAnio_mat(anio_mat);
                        dto.setId_alumno_fk(id_alumno);

                        dto.setFaltasAcumuladas(vista.getFaltas());
                        dto.setRetardos(vista.getRetrasos());
                        dto.setSaciones(vista.getSanciones());
                        dto.setObservaciones(vista.getComentarios());
                        try {
                            System.out.println(dto.toString());
                            modelo.update(dto,cnn);
                        } catch (SQLException ex) {
                            Logger.getLogger(AnotarIncidenciasControlador.class.
                                        getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };

            Thread hilo = new Thread(miRunnable);
            hilo.start();
            vista.setVisible(false);
        }

    }
    public class CerrarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
           vista.setVisible(false);
        }

    }

}
