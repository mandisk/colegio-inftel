/*
 * MatriculacionesDTO.java
 * 
 */

package es.uma.masterinftel.colegio_inftel.modelo.dto;

/**
 *
 * @author Agustin Pereña
 */

public class MatriculacionesDTO {

    private Integer anio_mat;
    private Integer id_alumno_fk;
    private Integer id_grupo_fk;
    private Integer id_cursos_fk;
    private String  repetidor;
    private Integer faltas_acumuladas;
    private Integer retardos;
    private Integer sanciones;
    private String  observaciones;

    public Integer getAnio_mat() {
        return anio_mat;
    }

    public Integer getFaltas_acumuladas() {
        return faltas_acumuladas;
    }

    public Integer getId_alumno_fk() {
        return id_alumno_fk;
    }

    public Integer getId_cursos_fk() {
        return id_cursos_fk;
    }

    public Integer getId_grupo_fk() {
        return id_grupo_fk;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getRepetidor() {
        return repetidor;
    }

    public Integer getRetardos() {
        return retardos;
    }

    public Integer getSanciones() {
        return sanciones;
    }

    public void setAnio_mat(Integer anio_mat) {
        this.anio_mat = anio_mat;
    }

    public void setFaltas_acumuladas(Integer faltas_acumuladas) {
        this.faltas_acumuladas = faltas_acumuladas;
    }

    public void setId_alumno_fk(Integer id_alumno_fk) {
        this.id_alumno_fk = id_alumno_fk;
    }

    public void setId_cursos_fk(Integer id_cursos_fk) {
        this.id_cursos_fk = id_cursos_fk;
    }

    public void setId_grupo_fk(Integer id_grupo_fk) {
        this.id_grupo_fk = id_grupo_fk;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setRepetidor(String repetidor) {
        this.repetidor = repetidor;
    }

    public void setRetardos(Integer retardos) {
        this.retardos = retardos;
    }

    public void setSanciones(Integer sanciones) {
        this.sanciones = sanciones;
    }

    




}
