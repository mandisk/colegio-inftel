/**
 * ConfigDB.java
 *
 */

package colegio_inftel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    private Properties p;

    /**
     * Atributos miembros para la carga y lectura posterior de los datos
     * de configuración de la Base de Datos
     */
    private String uri;
    private String usuario;
    private String clave;

    /**
     * Constructor de la clase
     * @param fileName nombre del fichero properties con los datos de conexión
     *                 a la Base de Datos
     */
    public ConfigBD(String fileName) {

        p = new Properties();
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
        usuario =p.getProperty("usuario");
        clave   =p.getProperty("clave");

    }

    /**
     * Getter del atributo miembro clave
     * @return (String) clave de conexión a la BD
     */
    public String getClave() {
        return clave;
    }

    /**
     * Getter del atributo miembro URI
     * @return (String) uri de conexión a la BD
     */
    public String getUri() {
        return uri;
    }

    /**
     * Getter del atributo miembro usuario
     * @return (String) usuario de la BD
     */
    public String getUsuario() {
        return usuario;
    }


}
