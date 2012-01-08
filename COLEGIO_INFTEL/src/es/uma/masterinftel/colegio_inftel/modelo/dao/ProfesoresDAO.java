/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.modelo.dao;

import es.uma.masterinftel.colegio_inftel.modelo.dto.*;
import es.uma.masterinftel.colegio_inftel.utilidades.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

/**
 *
 * @author jesus
 */
public class ProfesoresDAO {

    

    public ProfesoresDTO findProfesorByUsuario(String usuario) {

        ProfesoresDTO profesor = new ProfesoresDTO();
        ResultSet rs = null;
        Connection conexion = Conexion.conectar();
        
        String cadenaConsulta = "SELECT * from PROFESORES where usuario='" + 
                usuario + "';";
        
        try {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            rs = s.executeQuery(cadenaConsulta);
            
            // Debería recuperar un solo registro. Si recupera más, devolverá
            // siempre el último.
            while (rs.next()) {
                profesor.setId(rs.getInt("Id"));
                profesor.setDni_doc(rs.getString("dni_doc"));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setApellido1(rs.getString("apellido1"));
                profesor.setApellido2(rs.getString("apellido2"));
                profesor.setTelfcontacto(rs.getString("telfcontacto"));
                profesor.setObservaciones(rs.getString("observaciones"));
                profesor.setUsuario(rs.getString("usuario"));
                profesor.setPassword(rs.getString("password"));
                profesor.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profesor;

    }
    
   public static void main(String[] args) {
      
      System.out.println("Probando el DTO...\n");
      
      ProfesoresDAO profDAO = new ProfesoresDAO();
       
      ProfesoresDTO profesor = profDAO.findProfesorByUsuario("jesus");
      
      System.out.println("Profesor recuperado:");
      System.out.println("Id: " + profesor.getId());
      System.out.println("DNI: " + profesor.getDni_doc());
      System.out.println("Nombre: " + profesor.getNombre());
      System.out.println("Apellido1: " + profesor.getApellido1());
      System.out.println("Apellido2: " + profesor.getApellido2());
      System.out.println("Telefono: " + profesor.getTelfcontacto());
      System.out.println("Observaciones: " + profesor.getObservaciones());
      System.out.println("Usuario: " + profesor.getUsuario());
      System.out.println("Password: " + profesor.getPassword());
      System.out.println("email: " + profesor.getEmail());
    }
}
