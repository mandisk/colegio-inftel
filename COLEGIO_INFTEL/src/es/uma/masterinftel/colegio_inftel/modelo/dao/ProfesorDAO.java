/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.modelo.dao;

import es.uma.masterinftel.colegio_inftel.modelo.dto.*;
import java.sql.SQLException;

/**
 *
 * @author jesus
 */
public class ProfesorDAO {
   ProfesorDTO profesor;
   
   public ProfesorDTO findUsuario ( String codigo ) throws SQLException {
      
      return new ProfesorDTO();
      
   };
   
}
