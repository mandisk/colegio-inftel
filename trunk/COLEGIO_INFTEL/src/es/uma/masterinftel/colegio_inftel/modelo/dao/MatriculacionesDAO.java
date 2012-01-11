/**
 * MatriculacionesDTO.java
 *
 */

package es.uma.masterinftel.colegio_inftel.modelo.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
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


    }


}
