package es.uma.masterinftel.colegio_inftel.modelo.dao;

import es.uma.masterinftel.colegio_inftel.modelo.dto.IncidenciasDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BlackCrystal™
 */
public class IncidenciasDAO {

     public static final String SQL_UPDATE_INCIDENCIAS =
           "UPDATE MATRICULACIONES SET " +
           "faltas_acumuladas = ?, retardos = ?, saciones = ?, observaciones = ? " +
           "WHERE " +
           "anio_mat = ? AND " +
           "id_alumno_fk = ?";


    public void update(IncidenciasDTO dto, Connection conexion) throws SQLException{
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) conexion.prepareStatement(SQL_UPDATE_INCIDENCIAS);
            ps.setInt(1,dto.getFaltas_acumuladas());
            ps.setInt(2, dto.getRetardos());
            ps.setInt(3, dto.getSaciones());
            ps.setString(4, dto.getObservaciones());
            ps.setInt(5, dto.getAnio_mat());
            ps.setInt(6, dto.getId_alumno_fk());
            ps.executeUpdate();
        } finally {
            cerrar(ps);
        }

    }


    private void cerrar(PreparedStatement ps) throws SQLException {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println("Problema al cerrar PreparedStatement: "+e.getMessage());
            }
        }
    }


    public static void main(String[] args) throws SQLException{
        System.out.println("Probando CalificacionesDAO....");

        IncidenciasDTO dto = new IncidenciasDTO();
        Connection cnn = (Connection) Conexion.conectar();

        IncidenciasDAO incidencia = new IncidenciasDAO();

        //creacion de un DTO de prueba existente en BD
        dto.setAnio_mat(2011);
        dto.setId_alumno_fk(60);
        dto.setFaltasAcumuladas(2);
        dto.setRetardos(1);
        dto.setSaciones(1);
        dto.setObservaciones("No hace los deberes frecuentemente");
        incidencia.update(dto, cnn);

    }
}
