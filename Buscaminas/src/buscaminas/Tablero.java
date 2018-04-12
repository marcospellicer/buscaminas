/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Random;
import javax.swing.text.TabableView;

/**
 *
 * @author alumno
 */
public class Tablero {
    private int numFilas;
    private int numColumnas;
    private int numMinas;
    private Casilla[][] tabla;

    public Tablero(int numFilas, int numColumnas) {
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
        this.tabla = new Casilla[numFilas][numColumnas];
       rellenar();
    }
    private void rellenar(){
        for (int i = 0; i < getTabla().length; i++) {
            for (int j = 0; j < getTabla()[i].length; j++) {
                getTabla()[i][j]=new Casilla();
            }
        }
    }
    public void insertarMinas(int nMinas){
        Random r = new Random();
        setNumMinas(nMinas);
        while (nMinas>0) {
           int aux = r.nextInt(getNumFilas());
          int aux2 = r.nextInt(getNumColumnas());
            if(!tabla[aux][aux2].isMina()){
                getTabla()[aux][aux2].setMina(true);
                getTabla()[aux][aux2].setBlanco(false);
               nMinas--;
            }
        }
    }
    public void imprimirPrueba(){
        System.out.print(" ");
        for (int i = 0; i < getNumColumnas(); i++) {
            System.out.print(" "+i+" ");
        }
        System.out.println("");
 
        for (int i = 0; i < getTabla().length; i++) {
            System.out.print(""+i);
            for (int j = 0; j < getTabla()[i].length; j++) {
                if(getTabla()[i][j].isBandera()){
                    System.out.print(" B "); 
                }else if(getTabla()[i][j].isMina()&&getTabla()[i][j].isVisible()){
                    System.out.print(" M ");   
                }else if(getTabla()[i][j].isMina()&&!tabla[i][j].isVisible()){
                    System.out.print(" . "); 
                }else if(getTabla()[i][j].isBlanco()&&!tabla[i][j].isVisible()){
                    System.out.print(" . "); 
                }else if(getTabla()[i][j].isBlanco()&&getTabla()[i][j].isVisible()){
                    System.out.print("   "); 
                }
            }
            System.out.print(""+i);
            System.out.println("");
        }
        System.out.print(" ");
        for (int i = 0; i < getNumColumnas(); i++) {
            System.out.print(" "+i+" ");
        }
    }
    public void imprimirPruebaa(){
      System.out.println("");
        System.out.print(" ");
        for (int i = 0; i < getNumColumnas(); i++) {
            System.out.print(" "+i+" ");
        }
        System.out.println("");
 
        for (int i = 0; i < getTabla().length; i++) {
            System.out.print(""+i);
            for (int j = 0; j < getTabla()[i].length; j++) {
                
                if(getTabla()[i][j].isMina()&&getTabla()[i][j].isVisible()){
                    System.out.print(" M ");   
                }else if(getTabla()[i][j].isMina()&&!tabla[i][j].isVisible()){
                    System.out.print(" . "); 
                }else if(getTabla()[i][j].isBlanco()){
                    System.out.print(" "+getTabla()[i][j].getNumero()+" "); 
                }
            }
            System.out.print(""+i);
            System.out.println("");
        }
        System.out.print(" ");
        for (int i = 0; i < getNumColumnas(); i++) {
            System.out.print(" "+i+" ");
        }
    }
    public int calcularMina(int fila , int columna){
        int aux=0;
        for (int i = fila-1; i <= fila+1; i++) {
            for (int j = columna-1; j <= columna+1; j++) {
                if(i < getTabla().length && i>=0 ){
                   if(j < getTabla()[i].length && j >=0){
                       if(getTabla()[i][j].isMina()){
                           aux++;
                       }
                   } 
                }
            }
        }
        return aux;
    }
    public Casilla getCasiilla(int fila , int columna){
        return getTabla()[fila][columna];
    }
    public void calcularTablero(){

        for (int i = 0; i < getTabla().length; i++) {
            for (int j = 0; j < getTabla()[i].length; j++) {
                if(!tabla[i][j].isMina()){
                  getTabla()[i][j].setNumero(calcularMina(i, j)); 
                  if(tabla[i][j].getNumero()>0){
                      tabla[i][j].setBlanco(false);
                  }
                    
                }
            }
        }
        
    }

    /**
     * @return the numFilas
     */
    public int getNumFilas() {
        return numFilas;
    }

    /**
     * @param numFilas the numFilas to set
     */
    public void setNumFilas(int numFilas) {
        this.numFilas = numFilas;
    }

    /**
     * @return the numColumnas
     */
    public int getNumColumnas() {
        return numColumnas;
    }

    /**
     * @param numColumnas the numColumnas to set
     */
    public void setNumColumnas(int numColumnas) {
        this.numColumnas = numColumnas;
    }

    /**
     * @return the numMinas
     */
    public int getNumMinas() {
        return numMinas;
    }

    /**
     * @param numMinas the numMinas to set
     */
    public void setNumMinas(int numMinas) {
        this.numMinas = numMinas;
    }

    /**
     * @return the tabla
     */
    public Casilla[][] getTabla() {
        return tabla;
    }

    /**
     * @param tabla the tabla to set
     */
    public void setTabla(Casilla[][] tabla) {
        this.tabla = tabla;
    }
    
    
    
}
