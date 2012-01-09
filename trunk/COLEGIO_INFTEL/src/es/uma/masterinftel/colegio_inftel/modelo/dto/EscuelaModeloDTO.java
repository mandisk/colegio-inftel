/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.modelo.dto;

/**
 *
 * @author Proyectos
 */
public class EscuelaModeloDTO {
    
    private Integer codasignatura_fk;
    private Integer id_cursos_fk;
    private Integer id_grupo_fk;
    
     public Integer getCodasignatura_fk() {
        return codasignatura_fk;
    }
     
     public Integer getId_cursos_fk(){
        return id_cursos_fk;
     }
     
     public Integer getId_grupo_fk(){
     return id_grupo_fk;
     }
     
     public void setCodasignatura_fk(Integer codasignatura_fk) {
        this.codasignatura_fk = codasignatura_fk;
    }
     
     public void setId_cursos_fk(Integer id_cursos_fk){
     this.id_cursos_fk = id_cursos_fk;
     }
     
     public void setId_grupo_kf(Integer id_grupo_fk){
     this.id_grupo_fk = id_grupo_fk;
     }

}
