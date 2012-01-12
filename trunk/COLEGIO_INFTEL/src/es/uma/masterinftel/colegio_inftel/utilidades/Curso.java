/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.utilidades;

/**
 *
 * @author Proyectos
 */
public class Curso {
  private String nombre; //1
    private Integer id;
 
    public Curso(String nombre, Integer id){ //2
        this.nombre=nombre;
        this.id=id;
    }
 
    public Integer getId(){ //3
        return id;
    }
 
    @Override
    public String toString(){ //4
        return nombre;
    }  
}
