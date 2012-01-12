/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.masterinftel.colegio_inftel.modelo.dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import es.uma.masterinftel.colegio_inftel.modelo.dto.CalificacionesDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import java.sql.ResultSet;
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

    public static final String SQL_APROBADOS =
            "SELECT COUNT(DISTINCT A.ID_ALUMNO_FK) AS APROBADOS "+
            "FROM  MATRICULACIONES A, CALIFICACIONES D "+
            "WHERE A.ID_ALUMNO_FK = D.ID_ALUMNO_FK "+
            "AND   A.ID_CURSOS_FK = ? "+
            "AND   D.CODASIGNATURA_FK = ? "+
            "AND   D.NOTA_FINAL >= 5 "+
            "AND   D.ANIO_MAT_FK = ? "+
            "ORDER BY A.ID_ALUMNO_FK; ";

    public static final String SQL_MATRICULADOS =
            "SELECT COUNT(DISTINCT A.ID_ALUMNO_FK) AS APROBADOS "+
            "FROM  MATRICULACIONES A, CALIFICACIONES D "+
            "WHERE A.ID_ALUMNO_FK = D.ID_ALUMNO_FK "+
            "AND   A.ID_CURSOS_FK = ? "+
            "AND   D.CODASIGNATURA_FK = ? "+
            "AND   D.ANIO_MAT_FK = ? "+
            "ORDER BY A.ID_ALUMNO_FK;";

    public static final String SQL_APROBADOS_PROFESOR =
            "SELECT COUNT(B.ID_ALUMNO_FK) AS APROBADOS " +
            "FROM ASIGNATURAS A, CALIFICACIONES B "+
            "WHERE A.CODASIGNATURA = B.CODASIGNATURA_FK "+
            "AND A.PROFESOR_ID_FK = ? "+
            "AND B.ANIO_MAT_FK = ? "+
            "AND B.NOTA_FINAL >= 5 "+
            "ORDER BY B.ID_ALUMNO_FK; ";


    public static final String SQL_MATRICULADOS_ASIG_PROFESOR =
            "SELECT COUNT(B.ID_ALUMNO_FK) AS APROBADOS " +
            "FROM ASIGNATURAS A, CALIFICACIONES B "+
            "WHERE A.CODASIGNATURA = B.CODASIGNATURA_FK "+
            "AND A.PROFESOR_ID_FK = ? "+
            "AND B.ANIO_MAT_FK = ? "+
            "ORDER BY B.ID_ALUMNO_FK; ";


    public Integer numAprobados(Connection cnn, Integer codasignatura, Integer anio_mat, Integer id_curso)
                                throws SQLException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer aprobados = 0;

        try {
            ps = (PreparedStatement) cnn.prepareStatement(SQL_APROBADOS);
            ps.setInt(1, id_curso);
            ps.setInt(2, codasignatura);
            ps.setInt(3, anio_mat);
            rs = ps.executeQuery();

            if( rs.next() ){
                aprobados = rs.getInt(1);
            }
        } finally {
            cerrar(ps);
            cerrar(rs);
        }

        return aprobados;
    }


    public Integer numAprobados(Connection cnn, Integer id_profesor, Integer anio_mat)
                                throws SQLException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer aprobados = 0;

        try {
            ps = (PreparedStatement) cnn.prepareStatement(SQL_APROBADOS_PROFESOR);
            ps.setInt(1, id_profesor);
            ps.setInt(2, anio_mat);
            rs = ps.executeQuery();

            if( rs.next() ){
                aprobados = rs.getInt(1);
            }
        } finally {
            cerrar(ps);
            cerrar(rs);
        }

        return aprobados;
    }


    public Integer numMatriculados(Connection cnn, Integer codasignatura, Integer anio_mat, Integer id_curso)
                                throws SQLException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer aprobados = 0;

        try {
            ps = (PreparedStatement) cnn.prepareStatement(SQL_MATRICULADOS);
            ps.setInt(1, id_curso);
            ps.setInt(2, codasignatura);
            ps.setInt(3, anio_mat);
            rs = ps.executeQuery();

            if( rs.next() ){
                aprobados = rs.getInt(1);
            }
        } finally {
            cerrar(ps);
            cerrar(rs);
        }

        return aprobados;
    }



    public Integer numMatriculados(Connection cnn, Integer id_profesor, Integer anio_mat)
                                throws SQLException{

        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer aprobados = 0;

        try {
            ps = (PreparedStatement) cnn.prepareStatement(SQL_MATRICULADOS_ASIG_PROFESOR);
            ps.setInt(1, id_profesor);
            ps.setInt(2, anio_mat);
            rs = ps.executeQuery();

            if( rs.next() ){
                aprobados = rs.getInt(1);
            }
        } finally {
            cerrar(ps);
            cerrar(rs);
        }

        return aprobados;
    }


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

        System.out.println("APROBADOS: "+calificacion.numAprobados(cnn, 1, 2012, 1));
        System.out.println("MATRICULADOS: "+calificacion.numMatriculados(cnn, 1, 2012, 1));

        System.out.println("APROBADOSxPROFESOR(1): "+calificacion.numAprobados(cnn, 1, 2012)  );
        System.out.println("MATRICULADOSxPROFESOR(1) "+calificacion.numMatriculados(cnn, 1, 2012));


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
