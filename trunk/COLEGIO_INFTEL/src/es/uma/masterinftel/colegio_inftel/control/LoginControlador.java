/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.control;

import es.uma.masterinftel.colegio_inftel.modelo.dao.*;
import es.uma.masterinftel.colegio_inftel.modelo.dto.*;
import es.uma.masterinftel.colegio_inftel.vistas.*;
import es.uma.masterinftel.colegio_inftel.utilidades.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;

/**
 *
 * @author jesus
 */
public class LoginControlador {
    
    private ProfesoresDAO  m_modelo;
    private LoginVista     m_vista;
    
    private RolProfesorDAO rol = new RolProfesorDAO();
    
    
    public LoginControlador(ProfesoresDAO modelo, LoginVista vista){

        m_modelo = modelo;
        m_vista  = vista;

        m_vista.addAceptarListener(new AceptarListener());
        m_vista.addCancelarListener(new CancelarListener());
        
        m_vista.focoInicial();

    }
   
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
     
    private void navegacion(Integer rol) {
        EscuelaModeloDAO next_modelo = new EscuelaModeloDAO();
        EscuelaVistaPrincipal next_vista = new EscuelaVistaPrincipal(next_modelo);
        EscuelaControlador next_controlador = new EscuelaControlador(next_modelo,next_vista);
        
        m_vista.setVisible(false);
        next_vista.setRolJefeDeEstudios(rol);               
        next_vista.setVisible(true);
        
        
    }
        
    class AceptarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            Runnable miRunnable = new Runnable() {

                public void run() {

                    // Obtenemos el usuario
                    String user = m_vista.getUsuario();

                    // Obtener el password
                    char passArray[] = m_vista.getClave();

                    // Se inician las validaciones:

                    // Revisar que sean letras y numeros
                    boolean clave_correcta = true;
                    boolean bTest;

                    for (int i = 0; i < passArray.length; i++) {
                        char c = passArray[i];
                        // Si no es letra o numero entonces no es valido
                        if (!Character.isLetterOrDigit(c)) {
                            clave_correcta = false;
                        }
                    }

                    // Convertir el password a String
                    String pass = new String(passArray);

                    if (!clave_correcta) {
                        m_vista.printMensajeUserPassIncorrectos();
                    } else if (user.isEmpty() || pass.isEmpty()) {
                        m_vista.printMensajeUserPassVacios();
                    } else {  // Las validaciones de formato son correctas
                        try {
                            ProfesoresDTO profesor = m_modelo.findProfesorByUsuario(user);
                            if (profesor != null) {
                                bTest = md5(pass).equals(profesor.getPassword());
                                if (bTest) {
                                    // El usuario es válido
                                    RolProfesorDTO rolProfesor = rol.findRolByProfesorId(profesor.getId());
                                    navegacion(rolProfesor.getId_rol_fk());
                                }
                                else
                                    m_vista.printMensajeUserPassIncorrectos();
                            } else {
                                m_vista.printMensajeUserPassIncorrectos();
                            }
                        } catch (Exception e) {
                            m_vista.printMensajeErrorValidacion();
                            e.printStackTrace();
                        }

                    }
                }
            };

            Thread hilo = new Thread(miRunnable);
            hilo.start();
        }
    }

    class CancelarListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            m_vista.salir();
        }

    }

   
    public static void main(String[] args) throws IOException, Exception {

      BufferedReader entrada = new BufferedReader (new InputStreamReader(System.in));
      System.out.println("Palabra a codificar: \n");
      String palabra = entrada.readLine();
      System.out.println("Calculando código ...\n");
      String clave = LoginControlador.md5(palabra); 
      System.out.println("Código md5: " + clave + "\n");

    }
}