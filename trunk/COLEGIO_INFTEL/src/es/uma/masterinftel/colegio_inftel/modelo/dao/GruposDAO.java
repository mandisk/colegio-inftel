/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.modelo.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import es.uma.masterinftel.colegio_inftel.modelo.dto.*;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jsbaes
 */
public class GruposDAO extends GenericDAO {
    
    public static final String SQL_SELECT_GRUPOS_CURSO =
        "SELECT A.ID, A.DESC FROM " +
        "GRUPOS A, CURSOS_GRUPOS B " +
        "WHERE A.ID = B.ID_GRUPO_FK " +
        "AND B.ID_CURSO_FK=?;";
    
    public ArrayList<GruposDTO> obtenerGruposByCurso(Connection conn, Integer idCurso) 
            throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList grupos = new ArrayList();
        
        try {
            if (conn!=null){
                ps = (PreparedStatement) conn.prepareStatement(SQL_SELECT_GRUPOS_CURSO);
                ps.setInt(1, idCurso);
                rs = ps.executeQuery();

                //Creamos la lista con todos los objetos cursos
                while (rs.next()){

                    GruposDTO dto = new GruposDTO();
                    dto.setId(rs.getInt("id"));
                    dto.setDesc(rs.getString("desc"));
                    grupos.add(dto);

                }

            }

        } finally {
            cerrar(rs);
            cerrar(ps);
        }

        if (grupos.size() > 0) {
            return grupos;
        } else {
            return null;
        }
        
    }
    
   


    public static void main(String[] args) throws SQLException{
        ArrayList res;

        System.out.println("Probando GruposDAO....");


        Connection cnn = (Connection) Conexion.conectar();
        GruposDAO dao = new GruposDAO();

        res = dao.obtenerGruposByCurso(cnn, 3);

        Iterator i = res.listIterator();
        while(i.hasNext()){
            GruposDTO a = (GruposDTO) i.next();

            System.out.println(a.getId()+","+a.getDesc());

        }


    }
    
}

