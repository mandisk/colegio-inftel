/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.utilidades;

import colegio_inftel.ConfigBD;
import java.sql.*;
import colegio_inftel.*;

/**
 *
 * @author Jesús Barriga
 */
public class Conexion {

    private static Connection conn = null;
    
    private static ConfigBD bd = new ConfigBD("colegio_inftel/database.properties");
    
    public static String nombre_bd = "coolfm_java";
    public static String login = "coolfm_javacole";
    public static String password = "inftelinftel";
    public static String url = "jdbc:mysql://174.132.76.188/" + nombre_bd;
    
    private Conexion() { };   //Evita que se creen objetos desde fuera
    
    public static Connection conectar() {

        if (conn == null) {
            try {

                Class.forName("org.gjt.mm.mysql.Driver");
                conn = DriverManager.getConnection(url, login, password);
                //conn = DriverManager.getConnection(bd.getUri(),bd.getUsuario(),bd.getClave());
                //Lo siguiente se debe quitar después:
                if (conn != null) {
                    //System.out.println("Conexión a base de datos " + bd.getUri() + " ... Ok");
                    System.out.println("Conexión a base de datos " + url + " ... Ok");
                }
            } catch (SQLException ex) {
                System.out.println("Hubo un problema al intentar conectarse con la base de datos " + bd.getUri());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
        }
        return conn;
    }
   
   public static void main(String[] args) {
      
      System.out.println("Probando la conexión...\n");
       
      Connection miConexion = Conexion.conectar();
    }
   
}