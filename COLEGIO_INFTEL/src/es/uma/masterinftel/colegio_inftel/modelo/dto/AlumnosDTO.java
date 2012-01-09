/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.masterinftel.colegio_inftel.modelo.dto;

import java.util.Date;

/**
 *
 * @author Agustin Pereña
 */
public class AlumnosDTO {

    private Integer id;
    private String  nombre;
    private String  apellido1;
    private String  apellido2;
    private String  direccion;
    private String  poblacion;
    private String  provincia;
    private String  codpostal;
    private String  telfcontacto;
    private Date    fecnacimiento;
    private String  observaciones;

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getCodpostal() {
        return codpostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public Date getFecnacimiento() {
        return fecnacimiento;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getTelfcontacto() {
        return telfcontacto;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setCodpostal(String codpostal) {
        this.codpostal = codpostal;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFecnacimiento(Date fecnacimiento) {
        this.fecnacimiento = fecnacimiento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setTelfcontacto(String telfcontacto) {
        this.telfcontacto = telfcontacto;
    }

    

}
