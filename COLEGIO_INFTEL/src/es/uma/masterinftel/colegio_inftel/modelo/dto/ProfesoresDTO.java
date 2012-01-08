/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.masterinftel.colegio_inftel.modelo.dto;

/**
 *
 * @author jesus
 */
public class ProfesoresDTO {

   private Integer id;
   private String dni_doc;
   private String nombre;
   private String apellido1;
   private String apellido2;
   private String telfcontacto;
   private String observaciones;
   private String usuario;
   private String password;
   private String email;
   
   
   public String getApellido1() {
      return apellido1;
   }

   public String getApellido2() {
      return apellido2;
   }

   public String getDni_doc() {
      return dni_doc;
   }

   public String getEmail() {
      return email;
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

   public String getPassword() {
      return password;
   }

   public String getTelfcontacto() {
      return telfcontacto;
   }

   public String getUsuario() {
      return usuario;
   }

   public void setApellido1(String apellido1) {
      this.apellido1 = apellido1;
   }

   public void setApellido2(String apellido2) {
      this.apellido2 = apellido2;
   }

   public void setDni_doc(String dni_doc) {
      this.dni_doc = dni_doc;
   }

   public void setEmail(String email) {
      this.email = email;
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

   public void setPassword(String password) {
      this.password = password;
   }

   public void setTelfcontacto(String telfcontacto) {
      this.telfcontacto = telfcontacto;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   
}
