/**
 * MatriculacionesDTO.java
 *
 */

package es.uma.masterinftel.colegio_inftel.modelo.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import es.uma.masterinftel.colegio_inftel.modelo.dto.MatriculacionesDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import es.uma.masterinftel.colegio_inftel.utilidades.MatriculadosAsignaturas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public static final String SQL_ANIOS_MATRICULACIONES =
            "SELECT DISTINCT(anio_mat) FROM MATRICULACIONES;";

    public static final String SQL_MATRICULADOS_ASIGNATURAS =
            "SELECT B.CODASIGNATURA AS 'ASIGNATURA', COUNT(A.ID_ALUMNO_FK) AS 'ALUMNOS' "+
            "FROM MATRICULACIONES A, ASIGNATURAS B "+
            "WHERE A.ID_CURSOS_FK = B.IMPARTE_CURSOS_ID_FK "+
            "AND A.ANIO_MAT = ? "+
            "GROUP BY B.CODASIGNATURA;";


    public ArrayList<MatriculadosAsignaturas> obtenerNumMatriculadosAsignatura(Connection cnn, Integer anio_mat)
                                throws SQLException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MatriculadosAsignaturas> listaMatriculados = new ArrayList<MatriculadosAsignaturas>();

        try {
            ps = (PreparedStatement) cnn.prepareStatement(SQL_MATRICULADOS_ASIGNATURAS);
            ps.setInt(1, anio_mat);
            rs = ps.executeQuery();

            if( rs.next() ){
                MatriculadosAsignaturas matriculados = new MatriculadosAsignaturas();
                matriculados.setCodasignatura(rs.getInt(1));
                matriculados.setAlumnosMatriculados(rs.getInt(2));
                listaMatriculados.add(matriculados);
            }
        } finally {
            cerrar(ps);
            cerrar(rs);
        }

        return listaMatriculados;
    }
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


    public List<Integer> obtener_anios_matriculaciones(Connection conexion) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Integer> anios = new ArrayList<Integer>();

        try {
            ps = (PreparedStatement) conexion.prepareStatement(SQL_ANIOS_MATRICULACIONES);

            rs = ps.executeQuery();

            while(rs.next()){
                anios.add(rs.getInt(1));
            }

  
        } finally {
            cerrar(ps);
            cerrar(rs);
        }

        return anios;
        
    }




    public static void main(String[] args) throws SQLException{
        System.out.println("Probando MatriculacionesDAO....");

        ArrayList<Integer> prueba;
        
        Connection cnn = (Connection) Conexion.conectar();

        MatriculacionesDAO mat = new MatriculacionesDAO();

        prueba=(ArrayList) mat.obtener_anios_matriculaciones(cnn);

        System.out.println("Tamaño: "+prueba.size());
        for(Integer i: prueba){
            System.out.println(i);
        }
  
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
