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
 * @author jsbaes
 */
public class RolProfesorDAO {

    public RolProfesorDTO findRolByProfesorId(Integer idProfesor) {
        RolProfesorDTO rolProfesor = new RolProfesorDTO();
        ResultSet rs = null;
        Connection conexion = Conexion.conectar();

        String cadenaConsulta = "SELECT * FROM ROL_PROFESOR "
                + "WHERE id_profesor_fk='" + idProfesor + "';";

        try {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            rs = s.executeQuery(cadenaConsulta);

            // Debería recuperar un solo registro. Si recupera más, devolverá
            // siempre el último.
            while (rs.next()) {
                rolProfesor.setId_profesor_fk(rs.getInt("id_profesor_fk"));
                rolProfesor.setId_rol_fk(rs.getInt("id_rol_fk"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rolProfesor;
    }

    public static void main(String[] args) {

        System.out.println("Probando el DAO...\n");

        RolProfesorDAO rolProfDAO = new RolProfesorDAO();

        RolProfesorDTO rolProfesor = rolProfDAO.findRolByProfesorId(5);

        System.out.println("Rol recuperado:");
        System.out.println("Id_rol_fk: " + rolProfesor.getId_rol_fk());
        System.out.println("Id_profesor_fk: " + rolProfesor.getId_profesor_fk());
    }
}
