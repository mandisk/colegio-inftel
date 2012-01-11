/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.modelo.dto;

/**
 *
 * @author jsbaes
 */
public class RolProfesorDTO {
   private Integer id_rol_fk;
   private Integer id_profesor_fk;

    public Integer getId_profesor_fk() {
        return id_profesor_fk;
    }

    public Integer getId_rol_fk() {
        return id_rol_fk;
    }

    public void setId_profesor_fk(Integer id_profesor_fk) {
        this.id_profesor_fk = id_profesor_fk;
    }

    public void setId_rol_fk(Integer id_rol_fk) {
        this.id_rol_fk = id_rol_fk;
    }
    
    
}
