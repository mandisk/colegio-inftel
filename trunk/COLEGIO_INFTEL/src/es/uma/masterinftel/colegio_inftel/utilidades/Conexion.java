/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.utilidades;

import es.uma.masterinftel.colegio_inftel.utilidades.ConfigBD;
import java.sql.*;


/**
 *
 * @author Jesús Barriga
 */
public class Conexion {

    private static Connection conn = null;
    
    private Conexion() { };   //Evita que se creen objetos desde fuera
    
    public static Connection conectar() {

        if (conn == null) {
            try {

                Class.forName("org.gjt.mm.mysql.Driver");
                //conn = DriverManager.getConnection(url, login, password);

                ConfigBD.Configurar(Constantes.FICHERO_CONFIGURACION_DB);
                conn = DriverManager.getConnection(ConfigBD.uri,ConfigBD.usuario,ConfigBD.clave);

                //Lo siguiente se debe quitar después:
                if (conn != null) {
                    //System.out.println("Conexión a base de datos " + bd.getUri() + " ... Ok");
                    System.out.println("Conexión a base de datos " + ConfigBD.uri + " ... Ok");
                }
            } catch (SQLException ex) {
                System.out.println("Hubo un problema al intentar conectarse con la base de datos " + ConfigBD.uri);
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