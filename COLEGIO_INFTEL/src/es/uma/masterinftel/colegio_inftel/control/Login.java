/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;

/**
 *
 * @author jesus
 */
public class Login {
   
     private static String md5(String clear) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(clear.getBytes());

        int size = b.length;
        StringBuilder h = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
            if (u < 16) {
                h.append("0").append(Integer.toHexString(u));
            } else {
                h.append(Integer.toHexString(u));
            }
        }
        return h.toString();
    }
    
    public static void main(String[] args) throws IOException, Exception {
      
      BufferedReader entrada = new BufferedReader (new InputStreamReader(System.in));
      System.out.println("Palabra a codificar: \n");
      String palabra = entrada.readLine();
      System.out.println("Calculando código ...\n");
      String clave = Login.md5(palabra); 
      System.out.println("Código md5: " + clave + "\n");
    }
   
}
