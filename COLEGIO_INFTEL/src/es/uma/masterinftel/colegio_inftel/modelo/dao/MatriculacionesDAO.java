/**
 * MatriculacionesDTO.java
 *
 */

package es.uma.masterinftel.colegio_inftel.modelo.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import es.uma.masterinftel.colegio_inftel.modelo.dto.MatriculacionesDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Agustin Pereña
 */
public class MatriculacionesDAO extends GenericDAO {

    public static final String SQL_SELECT_ANIO_MATRICULACION =
           "SELECT MAX(anio_mat) FROM MATRICULACIONES;";

     public static final String SQL_UPDATE_INCIDENCIAS =
           "UPDATE MATRICULACIONES SET " +
           "faltas_acumuladas = ?, retardos = ?, saciones = ?, observaciones = ? " +
           "WHERE " +
           "anio_mat = ? AND " +
           "id_alumno_fk = ?";


    public void update(MatriculacionesDTO dto, Connection conexion) throws SQLException{
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) conexion.prepareStatement(SQL_UPDATE_INCIDENCIAS);
            ps.setInt(1,dto.getFaltas_acumuladas());
            ps.setInt(2, dto.getRetardos());
            ps.setInt(3, dto.getSanciones());
            ps.setString(4, dto.getObservaciones());
            ps.setInt(5, dto.getAnio_mat());
            ps.setInt(6, dto.getId_alumno_fk());
            ps.executeUpdate();
        } finally {
            cerrar(ps);
        }

    }




    public Integer obtener_anio_matricula(Connection conexion) throws SQLException {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer anio = 0;

        try {
            ps = (PreparedStatement) conexion.prepareStatement(SQL_SELECT_ANIO_MATRICULACION);

            rs = ps.executeQuery();

            if( rs.next() ){
                anio = rs.getInt(1);
            }

        } finally {
            cerrar(ps);
            cerrar(rs);
        }

        return anio;
    }




    public static void main(String[] args) throws SQLException{
        System.out.println("Probando MatriculacionesDAO....");

        
        Connection cnn = (Connection) Conexion.conectar();

        MatriculacionesDAO mat = new MatriculacionesDAO();

        System.out.println("AÑO ESCOLAR: "+mat.obtener_anio_matricula(cnn));


        //Probar actualizacion MatriculacionesDAO

        System.out.println("Probando MatriculacionesDAO....");

        MatriculacionesDTO dto = new MatriculacionesDTO();
        MatriculacionesDAO incidencia = new MatriculacionesDAO();

        //creacion de un DTO de prueba existente en BD
        dto.setAnio_mat(2011);
        dto.setId_alumno_fk(60);
        dto.setFaltas_acumuladas(2);
        dto.setRetardos(1);
        dto.setSanciones(1);
        dto.setObservaciones("No hace los deberes frecuentemente");
        incidencia.update(dto, cnn);

    }


}
