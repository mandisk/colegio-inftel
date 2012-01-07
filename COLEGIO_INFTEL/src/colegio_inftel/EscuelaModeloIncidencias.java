/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package colegio_inftel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author BlackCrystal™
 */
public class EscuelaModeloIncidencias {//inacabada, estoy acabandola...

    private Connection conexion = null;
    protected Object[] datosAlumno;

    /** Se establece la conexion con la base de datos */
    public EscuelaModeloIncidencias(Object[] datos){
        datosAlumno=datos;
    }
    public void estableceConexion()
    {
        if (conexion != null)
            return;

        try
        {
            // Se registra el Driver de MySQL
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            // Se obtiene una conexion con la base de datos. Hay que
            // cambiar el usuario "usuario" y la clave "" por las
            // adecuadas a la base de datos que estemos usando.
            conexion = DriverManager.getConnection("jdbc:mysql://174.132.76.188 :3306/coolfm_java","coolfm_javacole","inftelinftel");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
     /** Cierra la conexion con la base de datos */
    public void cierraConexion()
    {
        try
        {
            conexion.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public int getIdAlumno(){
        int idAlumno=-1;
        String nombre = datosAlumno[0].toString();
        String apellido1 = datosAlumno[0].toString();
        String apellido2 = datosAlumno[0].toString();
        ResultSet rs=null;
        String sql="SELECT id FROM ALUMNOS WHERE nombre="+nombre+" AND apellido1="+
                apellido1+" AND apellido2="+apellido2;

         estableceConexion();
          try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            //actualización de los datos de incidencias
            rs= s.executeQuery(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            cierraConexion();
        }

        return idAlumno;
    }


    public String getNombreAlumno(){

        String nombreAlumno="";
       
        //Obtener nombre del alumno de la BD/JTable Listado alumnos

        return nombreAlumno;
    }
    public String getAsignatura(){
        String asignatura="";
        //Obtener asignatura de la BD
        return asignatura;
    }
    public String getExpediente(){
        String expediente="";
        //Obtener expediente de la BD
        return expediente;
    }
    public String getCurso(){
        String curso="";
        //Obtener curso de la BD
        return curso;
    }
    public String getGrupo(){
        String grupo="";
        //Obtener grupo de la BD
        return grupo;
    }
    public void setFaltas(int faltas){
        // Introducir faltas en BD
    }
    public void setRetrasos(int retrasos){
        // Introducir retrasos en BD
    }
    public void setSanciones(int sanciones){
        // Introducir sanciones en BD
    }
    public void setComentarios(String comentarios){
        // Introducir comentarios en BD
    }
    public void modificarIncidenciasAlumno(int faltas, int retrasos, int sanciones, String comentarios){

        String sql="UPDATE MATRICULACIONES SET faltas_acumualdas="+faltas+", retardos="+retrasos+
                ", saciones="+sanciones+", observaciones="+comentarios+"WHERE id_grupo_fk="+getGrupo()+
                "AND id_curso_fk ="+getCurso()+"AND id_alumno_fk= "+getIdAlumno();
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            //actualización de los datos de incidencias
            int numDatos= s.executeUpdate(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
