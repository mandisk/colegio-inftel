package es.uma.masterinftel.colegio_inftel.control;


import com.mysql.jdbc.Connection;
import es.uma.masterinftel.colegio_inftel.modelo.dto.IncidenciasDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import es.uma.masterinftel.colegio_inftel.vistas.AnotarIncidenciasVista;
import es.uma.masterinftel.colegio_inftel.modelo.dao.IncidenciasDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dao.MatriculacionesDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dto.MatriculacionesDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private MatriculacionesDAO modelo;
    private AnotarIncidenciasVista vista;
    private int anio_mat=2007;
    private int id_alumno=31;

    public AnotarIncidenciasControlador(MatriculacionesDAO modelo, AnotarIncidenciasVista vista){

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

                        MatriculacionesDTO dto = new MatriculacionesDTO();
                        Connection cnn = (Connection) Conexion.conectar();

                        dto.setAnio_mat(anio_mat);
                        dto.setId_alumno_fk(id_alumno);

                        dto.setFaltas_acumuladas(vista.getFaltas());
                        dto.setRetardos(vista.getRetrasos());
                        dto.setSanciones(vista.getSanciones());
                        dto.setObservaciones(vista.getComentarios());
                        //TODO volver a vista Principal
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
           //TODO volver a vista Principal
        }

    }

}
