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

    //almacena los datos de la fila seleccionada por el usuario del
    //JTable de la clase EscuelaVistaPrincipal
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
        String apellido1 = datosAlumno[1].toString();
        String apellido2 = datosAlumno[2].toString();
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
            if(rs.isFirst()){

                idAlumno= rs.getInt("id");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            cierraConexion();
        }
        if(idAlumno==-1){
            System.out.println("El alumno: "+nombre+" "+apellido1+" "+apellido2+" no existe en la Base de Datos");
            //hacer una llamada a una clase q dibuje ventanas de error
        }

        return idAlumno;
    }
     public int getIdGrupo(){
        int idGrupo=-1;
        ResultSet rs=null;
        String sql="SELECT id FROM GRUPOS WHERE desc ="+getGrupo();

         estableceConexion();
          try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            //actualización de los datos de incidencias
            rs= s.executeQuery(sql);
            if(rs.isFirst()){

                idGrupo= rs.getInt("id");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            cierraConexion();
        }
        if(idGrupo==-1){
            System.out.println("El grupo"+getGrupo()+"no existe en la Base de Datos");
            //hacer una llamada a una clase q dibuje ventanas de error
        }

         return idGrupo;
     }
     public int getIdCurso(){
         int idCurso=-1;
         ResultSet rs=null;
        String sql="SELECT id FROM CURSOS WHERE desc ="+getCurso();

         estableceConexion();
          try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            //actualización de los datos de incidencias
            rs= s.executeQuery(sql);
            if(rs.isFirst()){

               idCurso = rs.getInt("id");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            cierraConexion();
        }
        if(idCurso==-1){
            System.out.println("El curso "+getCurso()+"no existe en la Base de Datos");
            //hacer una llamada a una clase q dibuje ventanas de error
        }

         return idCurso;
     }


    public String getNombreAlumno(){
       //Obtener nombre del alumno de la BD/JTable Listado alumnos
       
        return datosAlumno[0].toString()+datosAlumno[1].toString()+datosAlumno[2].toString();
    }
    public String getAsignatura(){
        //Obtener asignatura de la clase vista principal
        return datosAlumno[3].toString() ;
    }
    
    public String getCurso(){
        
        //Obtener curso de la clase vista principal
        return datosAlumno[4].toString();
    }

    public String getGrupo(){
        
        //Obtener grupo de la clase vista principal
        return datosAlumno[5].toString();
    }
   
    public void modificarIncidenciasAlumno(int faltas, int retrasos, int sanciones, String comentarios){

        String sql="UPDATE MATRICULACIONES SET faltas_acumualdas="+faltas+", retardos="+retrasos+
                ", saciones="+sanciones+", observaciones="+comentarios+"WHERE id_grupo_fk="+getIdGrupo()+
                "AND id_curso_fk ="+getIdCurso()+"AND id_alumno_fk= "+getIdAlumno();
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            //actualización de los datos de incidencias
            int numDatos= s.executeUpdate(sql);
            if (numDatos==0){
                System.out.println("Fallo al actualizar incidencias en la Base de Datos");
                //hacer una llamada a una clase q dibuje ventanas de error
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
