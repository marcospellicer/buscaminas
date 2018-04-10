/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

/**
 *
 * @author alumno
 */
public class Buscaminas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      Tablero t = new Tablero(5, 5);
      t.insertarMinas(5);
      t.imprimirPrueba();
      System.out.println(t.calcularMina(4, 4));
    }
    
}
