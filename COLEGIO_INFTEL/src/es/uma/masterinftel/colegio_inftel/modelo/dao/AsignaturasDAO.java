/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.masterinftel.colegio_inftel.modelo.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import es.uma.masterinftel.colegio_inftel.modelo.dto.AsignaturasDTO;
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
public class AsignaturasDAO extends GenericDAO {

    public static final String SQL_SELECT_ASIGNATURAS =
           "SELECT * FROM ASIGNATURAS;";

    public static final String SQL_SELECT_ASIGNATURAS_PROFESOR =
           "SELECT * FROM ASIGNATURAS WHERE profesor_id_fk=?;";


    public ArrayList<AsignaturasDTO> obtenerAsignaturas(Connection conn) throws SQLException {

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
                    
                    dto.setCodasignatura(rs.getInt("codasignatura"));
                    dto.setDesc(rs.getString("desc"));
                    dto.setProfesor_id_fk(rs.getInt("profesor_id_fk"));
                    dto.setImparte_cursos_id_fk(rs.getInt("imparte_cursos_id_fk"));

                    asignaturas.add(dto);

                }

            }

        } finally {
            cerrar(rs);
            cerrar(ps);
        }

        if (asignaturas.size() > 0) {
            return asignaturas;
        } else {
            return null;
        }

    }



    public ArrayList<AsignaturasDTO> obtenerAsignaturasByProfesor(Connection conn, Integer id_profesor) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList asignaturas = new ArrayList();

        try {
            if (conn!=null){
                ps = (PreparedStatement) conn.prepareStatement(SQL_SELECT_ASIGNATURAS_PROFESOR);
                ps.setInt(1, id_profesor);
                rs = ps.executeQuery();

                //Creamos la lista con todos los objetos asignaturas
                while (rs.next()){

                    AsignaturasDTO dto = new AsignaturasDTO();

                    dto.setCodasignatura(rs.getInt("codasignatura"));
                    dto.setDesc(rs.getString("desc"));
                    dto.setProfesor_id_fk(rs.getInt("profesor_id_fk"));
                    dto.setImparte_cursos_id_fk(rs.getInt("imparte_cursos_id_fk"));

                    asignaturas.add(dto);

                }

            }

        } finally {
            cerrar(rs);
            cerrar(ps);
        }

        if (asignaturas.size() > 0) {
            return asignaturas;
        } else {
            return null;
        }

    }




    public static void main(String[] args) throws SQLException{
        ArrayList res;

        System.out.println("Probando AsignaturasDAO....");


        Connection cnn = (Connection) Conexion.conectar();
        AsignaturasDAO dao = new AsignaturasDAO();

        //res = dao.obtenerAsignaturas(cnn);
        res = dao.obtenerAsignaturasByProfesor(cnn, 1);

        Iterator i = res.listIterator();
        while(i.hasNext()){
            AsignaturasDTO a = (AsignaturasDTO) i.next();

            System.out.println(a.getCodasignatura()+","+a.getDesc()+","+a.getProfesor_id_fk()+","+a.getImparte_cursos_id_fk());

        }


    }






}
