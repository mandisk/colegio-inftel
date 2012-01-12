/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.modelo.dao;

import es.uma.masterinftel.colegio_inftel.modelo.dto.EscuelaModeloDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;

//import com.mysql.jdbc.Connection;



/**
 *
 * @author Manuel Valls
 */
public class EscuelaModeloDAO extends GenericDAO {
       /** La conexion con la base de datos */
    private Connection conexion = null;
    //Cadena SELECT a consultar
    public static final String SQL_SELECT_ALUMNOS ="SELECT t1.nombre,t1.apellido1,t1.apellido2,t2.faltas_acumuladas,"+
            "t2.retardos,t2.saciones,t3.nota_p1,t3.nota_p2,t3.nota_p3,t3.nota_final,t1.id\n"+
"FROM ALUMNOS AS t1,MATRICULACIONES AS t2,CALIFICACIONES AS t3\n"+
"WHERE t1.id=t2.id_alumno_fk\n AND t3.codasignatura_fk= ?\n AND t2.id_alumno_fk=t3.id_alumno_fk\n"+
"AND t2.id_cursos_fk= ?\n AND t2.id_grupo_fk= ?\nAND t3.anio_mat_fk=t2.anio_mat\n"+
"AND t2.anio_mat=(SELECT MAX(anio_mat)\n"+ 
"FROM MATRICULACIONES) ";
    
    //Para meter los datos en el JTable, usaremos la clase DefaultTableModel. 
    //Para ello basta con instanciar el JTable como se muestra en el codigo
public DefaultTableModel modelo = new DefaultTableModel();
public JTable tabla = new JTable(modelo);
public TableRowSorter sorter = new TableRowSorter(modelo);
 


 //   public Integer obtenerIdAlumno(Connection conexion) throws SQLException {
   //     PreparedStatement ps = null;
    //    ResultSet rs = null;
    //    Integer idAlumno = 0;

     //   try {
     //      ps = (PreparedStatement) conexion.prepareStatement(SQL_SELECT_ALUMNOS);

     //       rs = ps.executeQuery();

    //        if( rs.next() ){
    //            idAlumno = rs.getInt(11);
    //        }

  //      } finally {
       //     cerrar(ps);
    //        cerrar(rs);
   //     }

   //     return idAlumno;
 //   }
    //}
    /**
     * Realiza la consulta de personas en la tabla y devuelve el ResultSet
     * correspondiente.
     * @return El resultado de la consulta
     */
    
    public ResultSet dameListaPersonas(EscuelaModeloDTO dto)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            // Se crea un Statement, para realizar la consulta
            ps = (PreparedStatement) conexion.prepareStatement(SQL_SELECT_ALUMNOS);
            ps.setInt(1, dto.getCodasignatura_fk());
            ps.setInt(2, dto.getId_cursos_fk());
            ps.setInt(3, dto.getId_grupo_fk());

            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = ps.executeQuery();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }

    /** Cierra la conexion con la base de datos */
    public void cierraConexion()
    {
        try
        {
            conexion.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
 
    public void rellenaTabla(EscuelaModeloDTO dto){
        //Primero se obtiene la conexión a la base de datos con un código como este:
        //estableceConexion();
        
        
        conexion = (Connection) Conexion.conectar();
        vaciaFilasModelo();
        //El siguiente paso es realizar la consulta y obtener el ResultSet. 
        try{
                ResultSet rs = dameListaPersonas(dto);  

                //Ahora sólo hay que rellenar el DefaultTableModel con los datos del ResultSet.
                Object[] columnas = new Object[11];
                columnas[0]="Nombre";
                columnas[1]="Primer Apellido";
                columnas[2]="Segundo Apellido";
                columnas[3]="Faltas";
                columnas[4]="Retrasos";
                columnas[5]="Sanciones";
                columnas[6]="Nota 1ºT";
                columnas[7]="Nota 2ºT";
                columnas[8]="Nota 3ºT";
                columnas[9]="Nota final";
                columnas[10]="id";

                modelo.setColumnIdentifiers(columnas);

                while (rs.next())
                {
                    // Se crea un array que será una de las filas de la tabla.
                    Object [] fila = new Object[11]; // Hay tres columnas en la tabla
 
                    // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
                    for (int i=0;i<11;i++)
                    fila[i] = rs.getObject(i+1); 
                    // El primer indice en rs es el 1, no el cero, por eso se suma 1.
                                          
                    // Se añade al modelo la fila completa.
                    modelo.addRow(fila);
                }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
     }   

  
    /**
     * Borra todas las filas del modelo.
     * @param modelo El modelo para la tabla.
     */
    public void vaciaFilasModelo()
    {
        try
        {
             while (modelo.getRowCount() > 0)
                    modelo.removeRow(0);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
