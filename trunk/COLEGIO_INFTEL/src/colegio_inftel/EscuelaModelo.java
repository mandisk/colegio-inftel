/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package colegio_inftel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



/**
 *
 * @author Proyectos
 */
public class EscuelaModelo {
       /** La conexion con la base de datos */
    private Connection conexion = null;
    
    //Para meter los datos en el JTable, usaremos la clase DefaultTableModel. Para ello basta con instanciar el JTable como se muestra en el codigo
public DefaultTableModel modelo = new DefaultTableModel();
public JTable tabla = new JTable(modelo);
 TableRowSorter sorter = new TableRowSorter(modelo);
//public  TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(modelo);


    
    /** Constructor */
    public EscuelaModelo(){
    
    }
 
   
    /** Se establece la conexion con la base de datos */
    public void estableceConexion()
    {
        if (conexion != null)
            return;

        try
        {
            // Se registra el Driver de MySQL
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            // Se obtiene una conexion con la base de datos. Hay que
            // cambiar el usuario "usuario" y la clave "" por las
            // adecuadas a la base de datos que estemos usando.
            conexion = DriverManager.getConnection("jdbc:mysql://174.132.76.188 :3306/coolfm_java","coolfm_javacole","inftelinftel");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Realiza la consulta de personas en la tabla y devuelve el ResultSet
     * correspondiente.
     * @return El resultado de la consulta
     */
    public ResultSet dameListaPersonas(int curso,int grupo,int asignatura)
    {
       
        ResultSet rs = null;
        String cadenaConsulta="SELECT t1.nombre,t1.apellido1,t1.apellido2,t2.faltas_acumuladas,"+
            "t2.retardos,t2.saciones,t3.nota_p1,t3.nota_p2,t3.nota_p3,t3.nota_final\n"+
"FROM ALUMNOS AS t1,MATRICULACIONES AS t2,CALIFICACIONES AS t3\n"+
"WHERE t1.id=t2.id_alumno_fk\n AND t3.codasignatura_fk="+asignatura+"\n AND t2.id_alumno_fk=t3.id_alumno_fk\n"+
"AND t2.id_cursos_fk="+curso+"\n AND t2.id_grupo_fk="+grupo+
                "\nAND t3.anio_mat_fk=t2.anio_mat\n"+
"AND t2.anio_mat=(SELECT MAX(anio_mat)\n"+ 
"FROM MATRICULACIONES) ";
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = s.executeQuery(cadenaConsulta);
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
 
    public void rellenaTabla(int curso,int grupo, int asignatura){
    //Primero se obtiene la conexión a la base de datos con un código como este:
estableceConexion();
vaciaFilasModelo();
//El siguiente paso es realizar la consulta y obtener el ResultSet. El código es el siguiente
try{
 ResultSet rs = dameListaPersonas(curso,grupo,asignatura);
 
//Para meter los datos en el JTable, usaremos la clase DefaultTableModel. Para ello basta con instanciar el JTable como se muestra en el codigo
 
//Ahora sólo hay que rellenar el DefaultTableModel con los datos del ResultSet.
 

// Creamos las columnas.
//modelo.addColumn("Nombre");
//modelo.addColumn("Primer Apellido");
//modelo.addColumn("Segundo Apellido");
//modelo.addColumn("Faltas");
//modelo.addColumn("Retrasos");
//modelo.addColumn("Sanciones");
//modelo.addColumn("Nota 1ºT");
//modelo.addColumn("Nota 2ºT");
//modelo.addColumn("Nota 3ºT");
//modelo.addColumn("Nota final");
Object[] columnas = new Object[10];
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
modelo.setColumnIdentifiers(columnas);

 


// Recorremos los registros con un ciclo while
// Se hace en un invokeAndWait para que este c�digo se ejecute
            // en el hilo de refresco de ventanas, evitando que salten
            // excepciones.
        //   SwingUtilities.invokeAndWait(new Runnable()
        //    {

          //      public void run()
            //    {
                  //  try
                 //   {
while (rs.next())
{
   // Se crea un array que será una de las filas de la tabla.
   Object [] fila = new Object[10]; // Hay tres columnas en la tabla
 
   // Se rellena cada posición del array con una de las columnas de la tabla en base de datos.
   for (int i=0;i<10;i++)
      fila[i] = rs.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.
                                          

   // Se añade al modelo la fila completa.
   modelo.addRow(fila);
    
         
}
//}catch (Exception e)
  //      {
    //        e.printStackTrace();
      //  }
        //        }
          //  });

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
       // La llamada se hace in un invokeAndWait para que se ejecute en el
        // hilo de refresco de ventanas y evitar que salten excepciones
        // durante dicho refresco.
        try
        {
    //    if (SwingUtilities.isEventDispatchThread()) {
   // Aqui llamas al metodo
             while (modelo.getRowCount() > 0)
                        modelo.removeRow(0);
     //   }
     //   else{
    //        SwingUtilities.invokeAndWait(new Runnable()
     //       {

       //         public void run()
         //       {
           //         while (modelo.getRowCount() > 0)
//                        modelo.removeRow(0);
           //     }

         //   });
          
       // }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

   
 

        
  
}
