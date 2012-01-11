/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package colegio_inftel;



/**
 *
 * @author BlackCrystal™
 */
public class Aula {

    protected int aprobados;
    protected int suspensos;
    protected String grupo;

    public Aula(int aprobados,int suspensos ){
        this.aprobados = aprobados;
        this.suspensos = suspensos;
    }
    public float getAprobados(){
        return aprobados;
    }
    public float getSuspensos(){
        return suspensos;
    }
    public String getGrupo(){
        return grupo;
    }
    public void setAprobados(int aprobados){
        this.aprobados = aprobados;
    }
    public void setSuspensos(int suspensos){
        this.suspensos = suspensos;
    }
    public void setGrupo(String grupo){
        this.grupo = grupo;
    }
    public  int getNumAlumnos(){
        return aprobados+suspensos;
    }
    public float obtenerPorcentajeAprobados(){
        return (aprobados / getNumAlumnos())*100;
    }
    public float obtenerPorcentajeSuspensos(){
        return (suspensos / getNumAlumnos())*100;
    }
    
}
