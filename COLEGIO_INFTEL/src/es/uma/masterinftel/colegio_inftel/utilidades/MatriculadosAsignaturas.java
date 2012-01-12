/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.masterinftel.colegio_inftel.utilidades;

/**
 *
 * @author BlackCrystal™
 */
public class MatriculadosAsignaturas {

    private int codasignatura;
    private int alumnosMatriculados;

    
    public int getCodasignatura(){
        return codasignatura;
    }
    public int getAlumnosMatricualdos(){
        return alumnosMatriculados;
    }
    public void setCodasignatura(int codigo){
        codasignatura = codigo;
    }
    public void setAlumnosMatriculados(int mat){
        alumnosMatriculados = mat;
    }
}
