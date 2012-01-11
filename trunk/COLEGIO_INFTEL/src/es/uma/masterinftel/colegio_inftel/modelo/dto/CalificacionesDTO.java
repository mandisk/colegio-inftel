/**
 * CalificacionesDTO.java
 *
 */

package es.uma.masterinftel.colegio_inftel.modelo.dto;

/**
 *
 * @author Agustin Pereña
 */
public class CalificacionesDTO {

    private Integer anio_mat_fk;
    private Integer id_alumno_fk;
    private Integer codasignatura_fk;
    private Double  nota_p1;
    private Double  nota_p2;
    private Double  nota_p3;
    private Double  nota_final;

    public Integer getAnio_mat_fk() {
        return anio_mat_fk;
    }

    public Integer getCodasignatura_fk() {
        return codasignatura_fk;
    }

    public Integer getId_alumno_fk() {
        return id_alumno_fk;
    }

    public Double getNota_final() {
        return nota_final;
    }

    public Double getNota_p1() {
        return nota_p1;
    }

    public Double getNota_p2() {
        return nota_p2;
    }

    public Double getNota_p3() {
        return nota_p3;
    }

    public void setAnio_mat_fk(Integer anio_mat_fk) {
        this.anio_mat_fk = anio_mat_fk;
    }

    public void setCodasignatura_fk(Integer codasignatura_fk) {
        this.codasignatura_fk = codasignatura_fk;
    }

    public void setId_alumno_fk(Integer id_alumno_fk) {
        this.id_alumno_fk = id_alumno_fk;
    }

    public void setNota_final(Double nota_final) {
        this.nota_final = nota_final;
    }

    public void setNota_p1(Double nota_p1) {
        this.nota_p1 = nota_p1;
    }

    public void setNota_p2(Double nota_p2) {
        this.nota_p2 = nota_p2;
    }

    public void setNota_p3(Double nota_p3) {
        this.nota_p3 = nota_p3;
    }


    

}
