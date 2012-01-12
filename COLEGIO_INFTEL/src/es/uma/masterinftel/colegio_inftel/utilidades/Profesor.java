/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package es.uma.masterinftel.colegio_inftel.utilidades;

/**
 *
 * @author BlackCrystal™
 */
public class Profesor {

    public Integer id;
        public String nombre;

        public boolean equals(Profesor p) {
            if (p.nombre.compareTo(this.nombre) == 0) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return nombre;
        }

}
