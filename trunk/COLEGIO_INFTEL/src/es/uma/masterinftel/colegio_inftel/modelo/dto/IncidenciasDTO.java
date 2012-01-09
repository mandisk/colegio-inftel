package es.uma.masterinftel.colegio_inftel.modelo.dto;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BlackCrystal™
 */
public class IncidenciasDTO {

    private Integer anio_mat;
    private Integer id_alumno_fk;
    private Integer faltas_acumuladas;
    private Integer retardos;
    private Integer saciones;
    private String observaciones;

    public Integer getAnio_mat(){
        return anio_mat;
    }
    public Integer getId_alumno_fk(){
        return id_alumno_fk;
    }
    public Integer getFaltas_acumuladas(){
        return faltas_acumuladas;
    }
    public Integer getRetardos(){
        return retardos;
    }
    public Integer getSaciones(){
        return saciones;
    }
    public String getObservaciones(){
        return observaciones;
    }
    public void setAnio_mat(Integer anio_mat){
        this.anio_mat = anio_mat;
    }
    public void setId_alumno_fk(Integer id_alumno_fk){
        this.id_alumno_fk = id_alumno_fk;
    }
    public void setFaltasAcumuladas(Integer faltas_acumuladas){
        this.faltas_acumuladas = faltas_acumuladas;
    }
    public void setRetardos(Integer retardos){
        this.retardos = retardos;
    }
    public void setSaciones(Integer saciones){
        this.saciones = saciones;
    }
    public void setObservaciones(String observaciones){
        this.observaciones = observaciones;
    }
}
