/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.masterinftel.colegio_inftel.modelo.dto;

/**
 *
 * @author Agustin Pereña
 */
public class AsignaturasDTO {

    private Integer codasignatura;
    private String  desc;
    private Integer profesor_id_fk;
    private Integer imparte_cursos_id_fk;

    public Integer getCodasignatura() {
        return codasignatura;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getImparte_cursos_id_fk() {
        return imparte_cursos_id_fk;
    }

    public Integer getProfesor_id_fk() {
        return profesor_id_fk;
    }

    public void setCodasignatura(Integer codasignatura) {
        this.codasignatura = codasignatura;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImparte_cursos_id_fk(Integer imparte_cursos_id_fk) {
        this.imparte_cursos_id_fk = imparte_cursos_id_fk;
    }

    public void setProfesor_id_fk(Integer profesor_id_fk) {
        this.profesor_id_fk = profesor_id_fk;
    }



}
