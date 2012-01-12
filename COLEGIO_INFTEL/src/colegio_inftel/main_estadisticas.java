/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * main_estadisticas.java
 *
 * Created on 12-ene-2012, 0:28:39
 */

package colegio_inftel;

import com.mysql.jdbc.Connection;
import es.uma.masterinftel.colegio_inftel.modelo.dao.MatriculacionesDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dao.ProfesoresDAO;
import es.uma.masterinftel.colegio_inftel.modelo.dto.ProfesoresDTO;
import es.uma.masterinftel.colegio_inftel.utilidades.Conexion;
import java.lang.Integer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author agumpg
 */
public class main_estadisticas extends javax.swing.JFrame {

    /**
     * Clase para poder cargar el combo con objetos profesor
     */
    class Profesor {
        public Integer id;
        public String  nombre;


        public boolean equals(Profesor p){
          if(p.nombre.compareTo(this.nombre) == 0){
              return true;
          }else{
              return false;
          }
        }


        @Override
        public String toString(){
            return nombre;
        }

    }

    /** Creates new form main_estadisticas */
    public main_estadisticas() throws SQLException {
        ArrayList anios = new ArrayList();
        ArrayList profesores = new ArrayList();

        initComponents();

        //CARGA DEL COMBO AÑOS MATRICULACION
        MatriculacionesDAO matriculacionesDAO = new MatriculacionesDAO();
        Connection cnn = (Connection) Conexion.conectar();

        anios=(ArrayList) matriculacionesDAO.obtener_anios_matriculaciones(cnn);

        
        Iterator i = anios.iterator();
        while(i.hasNext()){
            Integer x = (Integer) i.next();
            comboAnios.addItem(new Integer(x));
        }


        //Seleccionamos por defecto el año en curso
        comboAnios.setSelectedIndex(anios.size()-1);



        // CARGA DEL COMBO PROFESORES
        ProfesoresDAO profesoresDAO = new ProfesoresDAO();
        profesores = profesoresDAO.obtenerProfesores();
        Iterator j = profesores.iterator();
        ProfesoresDTO profesoresDTO = new ProfesoresDTO();

        while(j.hasNext()){
            Profesor profesor = new Profesor();
            profesoresDTO =  (ProfesoresDTO) j.next();
            profesor.nombre = profesoresDTO.getNombre()+" "+profesoresDTO.getApellido1()+" "+profesoresDTO.getApellido2();
            profesor.id = profesoresDTO.getId();
            comboProfesores.addItem(profesor);
        }

        Profesor profesor = (Profesor) comboProfesores.getSelectedItem();
        System.out.println("ID: "+profesor.id);


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        comboAnios = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        comboProfesores = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Ver Estadísticas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Año Matriculación");

        comboProfesores.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboProfesoresItemStateChanged(evt);
            }
        });

        jLabel2.setText("Profesores");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(comboAnios, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboProfesores, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel2)))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboAnios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboProfesores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jButton1)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
                                //Crear un dataset
        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Aprobados",43.2);
        data.setValue("Suspensos",27.9);
       
        //Creamos un Chart
        JFreeChart chart = ChartFactory.createPieChart(
                           "(%) Aprobados y Suspensos en Matemáticas de 1º E.S.O ", //Títrulo del gráfico
                           data,
                           true,//Leyenda
                           true,//ToolTips
                           true);
        //Creamos una especie de frame y mostramos el JFreeChart en él
        //Este constructor nos pide el título del Chart y el chart creado
        ChartFrame frame=new ChartFrame("Primer Chart para javax0711",chart);
        frame.pack();
        frame.setVisible(true);




    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboProfesoresItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboProfesoresItemStateChanged
        // TODO add your handling code here:

        Profesor profesor = (Profesor) comboProfesores.getSelectedItem();
        System.out.println("ID: "+profesor.id);

    }//GEN-LAST:event_comboProfesoresItemStateChanged

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new main_estadisticas().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(main_estadisticas.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox comboAnios;
    private javax.swing.JComboBox comboProfesores;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

}
