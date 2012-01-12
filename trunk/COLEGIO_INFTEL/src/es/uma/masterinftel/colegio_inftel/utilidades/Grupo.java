/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.utilidades;

/**
 *
 * @author inftel
 */
public class Grupo {
     private String nombre; //1
    private String id;
 
    public Grupo(String nombre, String id){ //2
        this.nombre=nombre;
        this.id=id;
    }
 
    public String getId(){ //3
        return id;
    }
 
    @Override
    public String toString(){ //4
        return nombre;
    }  
    
}
