/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio_inftel;

/**
 *
 * @author Proyectos
 */
public class EstadisticasControlador {
     //Necesitamos que el controlador interactue con el Modelo y la vista
    private EstadisticasModelo m_modelo;
    private EstadisticasVista m_vista;
    
    /** Constructor */
    public EstadisticasControlador(EstadisticasModelo modelo, EstadisticasVista vista){
    m_modelo = modelo;
    m_vista = vista;
    
    //Aquí se localizan los métodos de escucha
    }
}
