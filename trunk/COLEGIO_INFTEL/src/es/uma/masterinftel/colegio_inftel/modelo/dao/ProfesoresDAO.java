/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.modelo.dao;

import com.mysql.jdbc.PreparedStatement;
import es.uma.masterinftel.colegio_inftel.modelo.dto.*;
import es.uma.masterinftel.colegio_inftel.utilidades.*;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jesus
 */
public class ProfesoresDAO extends GenericDAO {

    public static final String SQL_SELECT_PROFESORES =
            "SELECT * FROM PROFESORES;";

    public ArrayList<ProfesoresDTO> obtenerProfesores() throws SQLException {

        Connection conn = Conexion.conectar();

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList profesores = new ArrayList();

        try {
            if (conn != null) {
                ps = (PreparedStatement) conn.prepareStatement(SQL_SELECT_PROFESORES);
                rs = ps.executeQuery();

                //Creamos la lista con todos los objetos cursos
                while (rs.next()) {

                    ProfesoresDTO dto = new ProfesoresDTO();
                    dto.setId(rs.getInt("Id"));
                    dto.setDni_doc(rs.getString("dni_doc"));
                    dto.setNombre(rs.getString("nombre"));
                    dto.setApellido1(rs.getString("apellido1"));
                    dto.setApellido2(rs.getString("apellido2"));
                    dto.setTelfcontacto(rs.getString("telfcontacto"));
                    dto.setObservaciones(rs.getString("observaciones"));
                    dto.setUsuario(rs.getString("usuario"));
                    dto.setPassword(rs.getString("password"));
                    dto.setEmail(rs.getString("email"));
                    profesores.add(dto);

                }

            }

        } finally {
            cerrar(rs);
            cerrar(ps);
        }

        if (profesores.size() > 0) {
            return profesores;
        } else {
            return null;
        }

    }

    public ProfesoresDTO findProfesorByUsuario(String usuario) {

        ProfesoresDTO profesor = new ProfesoresDTO();
        ResultSet rs = null;
        Connection conexion = Conexion.conectar();

        String cadenaConsulta = "SELECT * from PROFESORES where usuario='"
                + usuario + "';";

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

    public static void main(String[] args) throws SQLException {

        ArrayList res;

        System.out.println("Probando profesores DAO...\n");

        ProfesoresDAO profDAO = new ProfesoresDAO();

        /*
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
        */

        res = profDAO.obtenerProfesores();

        Iterator i = res.listIterator();
        while (i.hasNext()) {
            ProfesoresDTO profesor = (ProfesoresDTO) i.next();
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
}
