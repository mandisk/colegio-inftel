/**
 * ConfigDB.java
 *
 */

package es.uma.masterinftel.colegio_inftel.utilidades;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utilidad para obtener los datos de configuración de la Base de Datos
 * a partir de un fichero de propiedades
 *
 * @author Agustín Pereña
 * @author Manuel Valls
 * @author Jesús Barriga
 * @author Luis Jarén
 * @version v1.0 Diciembre-2011
 */
public class ConfigBD {

    /**
     * Atributos de clase para la carga y lectura posterior de los datos
     * de configuración de la Base de Datos
     */
    public static String uri;
    public static String usuario;
    public static String clave;

    /**
     * Método de clase para la lectura del fichero de propiedades
     * @param fileName nombre del fichero properties con los datos de conexión
     *                 a la Base de Datos
     */
    public static void Configurar(String fileName) {

        Properties p = new Properties();
        try {
            FileInputStream config = new FileInputStream(fileName);
            //InputStream config = ClassLoader.getSystemResourceAsStream(ConfigBD.class.getPackage().getName() + "/" + fileName);
            
            p.load(config);
            config.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Fichero de configuración NO ENCONTRADO: "+fileName);
        } catch (IOException ex){
            System.out.println("Error leyendo fichero configuración: "+ ex.getMessage());
        }

        uri     =p.getProperty("uri");
        usuario =p.getProperty("user");
        clave   =p.getProperty("password");

    }

}
