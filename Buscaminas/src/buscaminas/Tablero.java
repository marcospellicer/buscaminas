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
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
               tabla[i][j]=new Casilla();
            }
        }
    }
    public void insertarMinas(int nMinas){
        Random r = new Random();
        while (nMinas>0) {
           int aux = r.nextInt(numFilas);
          int aux2 = r.nextInt(numColumnas);
            if(!tabla[aux][aux2].isMina()){
               tabla[aux][aux2].setMina(true);
               tabla[aux][aux2].setVisible(true);
               nMinas--;
            }
        }
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                if(!tabla[i][j].isMina()){
                    tabla[i][j].setBlanco(true);
                    
                }
            }
        }
    }
    public void imprimirPrueba(){
        System.out.print(" ");
        for (int i = 0; i < numColumnas; i++) {
            System.out.print(" "+i+" ");
        }
        System.out.println("");
 
        for (int i = 0; i < tabla.length; i++) {
            System.out.print(""+i);
            for (int j = 0; j < tabla[i].length; j++) {
                if(tabla[i][j].isBandera()){
                    System.out.print(" B "); 
                }else if(tabla[i][j].isMina()&&tabla[i][j].isVisible()){
                    System.out.print(" M ");   
                }else if(tabla[i][j].isMina()&&!tabla[i][j].isVisible()){
                    System.out.print(" . "); 
                }else if(tabla[i][j].isBlanco()&&!tabla[i][j].isVisible()){
                    System.out.print(" . "); 
                }else if(tabla[i][j].isBlanco()&&tabla[i][j].isVisible()){
                    System.out.print("   "); 
                }
            }
            System.out.print(""+i);
            System.out.println("");
        }
        System.out.print(" ");
        for (int i = 0; i < numColumnas; i++) {
            System.out.print(" "+i+" ");
        }
    }
    public int calcularMina(int fila , int columna){
        int aux=0;
        if(fila==0&&columna==0){
            for (int i = fila ; i <= fila+1; i++) {
                for (int j = columna; j <= columna+1; j++) {
                    if(tabla[i][j].isMina()){
                    aux++;
                }
                }
            }
            
        }else if(fila>0&&columna==0){
            for (int i = fila-1 ; i <= fila+1; i++) {
                for (int j = columna; j <= columna+1; j++) {
                   if(tabla[i][j].isMina()){
                    aux++;
                }
                }
            }
        }else if(fila==0&&columna>0){
            for (int i = fila ; i <= fila+1; i++) {
                for (int j = columna-1; j <= columna+1; j++) {
                    if(tabla[i][j].isMina()){
                    aux++;
                }
                }
            }
        }else if(fila==(numFilas-1)&&columna==(numColumnas-1)){
            for (int i = fila-1 ; i <= fila; i++) {
                for (int j = columna-1; j <= columna; j++) {
                    if(tabla[i][j].isMina()){
                    aux++;
                }
                }
            }
        }else if(fila<(numFilas-1)&&columna==(numColumnas-1)){
            for (int i = fila-1 ; i <= fila+1; i++) {
                for (int j = columna-1; j <= columna; j++) {
                   if(tabla[i][j].isMina()){
                    aux++;
                }
                }
            }
        }else if(fila==(numFilas-1)&&columna<(numColumnas-1)){
            for (int i = fila-1 ; i <= fila; i++) {
                for (int j = columna-1; j <= columna++; j++) {
                    if(tabla[i][j].isMina()){
                    aux++;
                }
                }
            }
        }else{    
        for (int i = fila-1; i <= fila+1 ; i++) {
            for (int j = columna-1 ; j <= columna+1; j++) {
                if(tabla[i][j].isMina()){
                    aux++;
                }
            }
        }
        }
        
        return aux;
    }
    public Casilla getCasiilla(int fila , int columna){
        return tabla[fila][columna];
    }
    public void contarCasillas(int nMinas){
       int aux = 0;
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                if(!tabla[i][j].isMina()){
                    tabla[i][j].setNumero(calcularMina(i, j));  
                }
            }
        }
        System.out.println(aux);
    }
    
    
    
}
