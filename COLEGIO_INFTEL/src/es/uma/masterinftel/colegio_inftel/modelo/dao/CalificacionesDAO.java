/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.masterinftel.colegio_inftel.modelo.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import es.uma.masterinftel.colegio_inftel.modelo.dto.CalificacionesDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import java.sql.SQLException;

/**
 *
 * @author agumpg
 */
public class CalificacionesDAO extends GenericDAO {

    public static final String SQL_UPDATE_CALIFICACIONES =
           "UPDATE CALIFICACIONES SET " +
           "nota_p1 = ?, nota_p2 = ?, nota_p3 = ?, nota_final = ? " +
           "WHERE " +
           "anio_mat_fk = ? AND " +
           "id_alumno_fk = ? AND " +
           "codasignatura_fk = ?";


    public void update(CalificacionesDTO dto, Connection conexion) throws SQLException{
        PreparedStatement ps = null;
        try {
            if (conexion!=null){
                ps = (PreparedStatement) conexion.prepareStatement(SQL_UPDATE_CALIFICACIONES);
                ps.setDouble(1, dto.getNota_p1());
                ps.setDouble(2, dto.getNota_p2());
                ps.setDouble(3, dto.getNota_p3());
                ps.setDouble(4, dto.getNota_final());
                ps.setInt(5, dto.getAnio_mat_fk());
                ps.setInt(6, dto.getId_alumno_fk());
                ps.setInt(7, dto.getCodasignatura_fk());
                ps.executeUpdate();
            }
        } finally {
            cerrar(ps);
        }

    }


    public static void main(String[] args) throws SQLException{
        System.out.println("Probando CalificacionesDAO....");

        CalificacionesDTO dto = new CalificacionesDTO();
        Connection cnn = (Connection) Conexion.conectar();

        CalificacionesDAO calificacion = new CalificacionesDAO();

        //creacion de un DTO de prueba existente en BD
        dto.setAnio_mat_fk(2011);
        dto.setId_alumno_fk(60);
        dto.setCodasignatura_fk(21);
        dto.setNota_p1(9.5);
        dto.setNota_p2(9.5);
        dto.setNota_p3(9.5);
        dto.setNota_final(9.5);

        calificacion.update(dto, cnn);


    }

}
