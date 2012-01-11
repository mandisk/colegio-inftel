/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio_inftel;
import es.uma.masterinftel.colegio_inftel.utilidades.Aula;
import es.uma.masterinftel.colegio_inftel.modelo.dao.AsignaturasDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dao.CursosDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dto.AsignaturasDTO;
import es.uma.masterinftel.colegio_inftel.modelo.dto.CursosDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import com.mysql.jdbc.Connection;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartPanel;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.sql.*;
/**
 *
 * @author Proyectos
 */
public class EstadisticasModelo {
    AsignaturasDAO modeloAsignatura;
    CursosDAO modeloCurso;

    //Consulta Aprobados
    public static final String SQL_SELECT_APROBADOS = "SELECT COUNT(C.nota_final)" +
                                                        "FROM CALIFICACIONES C,ASIGNATURAS A, Matriculaciones M" +
                                                        "WHERE C.nota_final > = 5 AND C.codasignatura_fk= ?" +
                                                        "AND A.imparte_cursos_id_fk =? AND C.anio_mat_fk = MAX(M.anio_mat);";
    //Consulta Suspensos
    public static final String SQL_SELECT_SUSPENSOS = "SELECT COUNT(C.nota_final)" +
                                                        "FROM CALIFICACIONES C,ASIGNATURAS A, Matriculaciones M" +
                                                        "WHERE C.nota_final < 5 AND C.codasignatura_fk= ?" +
                                                        "AND A.imparte_cursos_id_fk =? AND C.anio_mat_fk = MAX(M.anio_mat);";

    public ArrayList<Aula> obtenerAprobadosSuspensosByAsignaturaCurso(){ //inacabada

        ArrayList<Aula> resultado = new ArrayList<Aula>();

        //obtener codasignatura de la asignatura(array)
        ArrayList<AsignaturasDTO> asignaturas = modeloAsignatura.obtenerAsignaturas(Conexion.conn);
        //obtener id de curso del curso(array)
        ArrayList<CursosDTO> cursos = modeloCurso.obtenerCursos(Conexion.conn);

        //consulta aprobados y suspensos

        return resultado;
    }
    
}
