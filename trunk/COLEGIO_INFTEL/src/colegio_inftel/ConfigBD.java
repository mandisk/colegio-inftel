/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package colegio_inftel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author agumpg
 */
public class ConfigBD {

    private Properties p;

    private String uri;
    private String usuario;
    private String clave;

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

    public String getClave() {
        return clave;
    }

    public String getUri() {
        return uri;
    }


    public String getUsuario() {
        return usuario;
    }


}
