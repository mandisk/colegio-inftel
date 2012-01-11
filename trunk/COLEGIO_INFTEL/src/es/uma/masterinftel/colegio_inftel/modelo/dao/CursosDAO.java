/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.masterinftel.colegio_inftel.modelo.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import es.uma.masterinftel.colegio_inftel.modelo.dto.CursosDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Agustin Pereña
 */
public class CursosDAO extends GenericDAO {

    public static final String SQL_SELECT_CURSOS =
           "SELECT * FROM CURSOS;";

    public static final String SQL_SELECT_CURSOS_PROFESOR =
            "SELECT A.ID, A.DESC FROM CURSOS A, ASIGNATURAS B WHERE A.ID = B.IMPARTE_CURSOS_ID_FK AND B.PROFESOR_ID_FK = ?;";

    
    public ArrayList<CursosDTO> obtenerCursos(Connection conn) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList cursos = new ArrayList();
        
        try {
            if (conn!=null){
                ps = (PreparedStatement) conn.prepareStatement(SQL_SELECT_CURSOS);
                rs = ps.executeQuery();

                //Creamos la lista con todos los objetos cursos
                while (rs.next()){

                    CursosDTO dto = new CursosDTO();
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


    public ArrayList<CursosDTO> obtenerCursosByProfesor(Connection conn, Integer id_profesor) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList cursos = new ArrayList();

        try {
            if (conn!=null){
                ps = (PreparedStatement) conn.prepareStatement(SQL_SELECT_CURSOS_PROFESOR);
                ps.setInt(1, id_profesor);

                rs = ps.executeQuery();

                //Creamos la lista con todos los objetos cursos
                while (rs.next()){

                    CursosDTO dto = new CursosDTO();
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




    public static void main(String[] args) throws SQLException{
        ArrayList res;

        System.out.println("Probando CursosDAO....");


        Connection cnn = (Connection) Conexion.conectar();
        CursosDAO dao = new CursosDAO();

        //res = dao.obtenerCursos(cnn);
        res= dao.obtenerCursosByProfesor(cnn, 1);

        Iterator i = res.listIterator();
        while(i.hasNext()){
            CursosDTO c = (CursosDTO) i.next();
            System.out.println(c.getId()+","+c.getDesc());
        }


    }
    
}
