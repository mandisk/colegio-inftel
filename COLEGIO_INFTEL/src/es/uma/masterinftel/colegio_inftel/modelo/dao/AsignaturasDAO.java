/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.masterinftel.colegio_inftel.modelo.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import es.uma.masterinftel.colegio_inftel.modelo.dto.AsignaturasDTO;
import es.uma.masterinftel.colegio_inftel.modelo.dto.CursosDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Agustin Pereña
 */
public class AsignaturasDAO {

    public static final String SQL_SELECT_ASIGNATURAS =
           "SELECT * FROM ASIGNATURAS;";

    public static final String SQL_SELECT_ASIGNATURAS_PROFESOR =
           "SELECT * FROM ASIGNATURAS WHERE profesor_id_fk=?;";


    public ArrayList<CursosDTO> obtenerAsignaturas(Connection conn) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList asignaturas = new ArrayList();

        try {
            if (conn!=null){
                ps = (PreparedStatement) conn.prepareStatement(SQL_SELECT_ASIGNATURAS);
                rs = ps.executeQuery();

                //Creamos la lista con todos los objetos asignaturas
                while (rs.next()){

                    AsignaturasDTO dto = new AsignaturasDTO();
                    

                    dto.setId(rs.getInt("id"));
                    dto.setDesc(rs.getString("desc"));
                    cursos.add(dto);

                }

            }

        } finally {
            cerrar(rs);
            cerrar(ps);
        }

        if (cursos.size() > 0) {
            return cursos;
        } else {
            return null;
        }

    }


}
