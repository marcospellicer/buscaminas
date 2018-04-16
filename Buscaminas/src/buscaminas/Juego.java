/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Juego {
    private Tablero tablero;
    private int numMinas;
    private int numFilas;
    private int numColumnas;

    public Juego() {
        this.numMinas = 0;
        this.numFilas = 0;
        this.numColumnas = 0;
    }

    public void configurarJuego(int numMinas, int numFilas, int numColumnas) {
        this.numMinas = numMinas;
        this.numFilas = numFilas;
        this.numColumnas = numColumnas;
    }
    public void iniciarJuego(){
        tablero=new Tablero(numFilas, numColumnas);
        tablero.insertarMinas(numMinas);
        tablero.calcularTablero();
    }
    public void jugar(){
       boolean salir = false;
       Scanner s = new Scanner(System.in);
        System.out.println("dime el numero de filas");
        int fi=s.nextInt();
        System.out.println("dime el numero de columnas");
        int co=s.nextInt();
        System.out.println("dime el numero de minas");
        int mi=s.nextInt();
        configurarJuego(mi, fi, co);
        iniciarJuego();
        while(!salir){  
        switch(elegirOperacion()){
            case 1:{
                System.out.println("dime la fila");
                int fil=s.nextInt();
                System.out.println("dime la columna");
                int col=s.nextInt();
                if(coordenadasCorrectas(fil, col)){
                    
                    
                    if(descubrirCasilla(fil, col)){
                            decubrirBlanco(fil, col);
                    }else{
                        finalizarJuego();
                        salir=true;
                    }
                }else{
                    System.out.println("coordenadas incorrectas");
                }
                break;
            }
            case 2:{
                System.out.println("dime la fila");
                int fil=s.nextInt();
                System.out.println("dime la columna");
                int col=s.nextInt();
                int min=0;
                for (int i = 0; i < tablero.getTabla().length; i++) {
                for (int j = 0; j < tablero.getTabla()[i].length; j++) {
                if(tablero.getTabla()[i][j].isBandera()){
                    min++;
                }
            }
        }
                if(coordenadasCorrectas(fil, col)){
                   if(!tablero.getTabla()[fil][col].isBandera()){
                       if(min<numMinas){
                         tablero.getTabla()[fil][col].setBandera(true);   
                       }else{
                           System.out.println("no hay tantas minas");
                       }
                       
                   }else{
                       System.out.println("ya hay bandera");
                   } 
                   
                }else{
                    System.out.println("coordenadas incorrectas");
                }
                
                break;
            }
            case 3:{
                System.out.println("dime la fila");
                int fil=s.nextInt();
                System.out.println("dime la columna");
                int col=s.nextInt();
                if(coordenadasCorrectas(fil, col)){
                   if(tablero.getTabla()[fil][col].isBandera()){
                      tablero.getTabla()[fil][col].setBandera(false);  
                   }else{
                       System.out.println("no hay bandera");
                   } 
                   
                }else{
                    System.out.println("coordenadas incorrectas");
                }
                break;
            }
        }
        if(partidaGanada()){
        System.out.println("********************************************");
        System.out.println("*              ENHORABUENA                 *");
        System.out.println("********************************************");
        salir=true;
        }
        }
        
        
    }
    private void imprimirJuego(){
        System.out.print(" ");
        for (int i = 0; i < numColumnas; i++) {
            System.out.print(" "+i+" ");
        }
        System.out.println("");
 
        for (int i = 0; i < tablero.getTabla().length; i++) {
            System.out.print(""+i);
            for (int j = 0; j < tablero.getTabla()[i].length; j++) {
                    if(tablero.getTabla()[i][j].isBandera()){
                        System.out.print(" B ");
                    }else if(tablero.getTabla()[i][j].isMina()){
                     if(!tablero.getTabla()[i][j].isVisible()){
                        System.out.print(" . ");
                    }else{
                        System.out.print(" M ");
                    }   
                }else{
                    if(tablero.getTabla()[i][j].isBlanco()){
                    if(tablero.getTabla()[i][j].isVisible()){
                        System.out.print("   ");
                    }else{
                        System.out.print(" . ");
                    }     
                    }else{
                    if(tablero.getTabla()[i][j].isVisible()){
                        System.out.print(" "+tablero.getTabla()[i][j].getNumero()+" ");
                    }else{
                        System.out.print(" . ");
                    }    
                         
                    }
                
            }
            
        }
        System.out.print(""+i);
        System.out.println("");    
        
        }
        System.out.print(" ");
        for (int i = 0; i < numColumnas; i++) {
            System.out.print(" "+i+" ");
        }
        System.out.println("");
    }
    private int elegirOperacion(){
        int op=-1;
        while(op==-1){
         imprimirJuego();
        Scanner s = new Scanner(System.in);
        System.out.println("***********************");
        System.out.println("1- Descubrir           ");
        System.out.println("2- Poner Bandera");
        System.out.println("3- Quitar Bandera");
        System.out.println("***********************");
        System.out.println("dime una opcion");
        op=s.nextInt();
        if(op<1||op>3){
            op=-1;
        }
        }
        return op;
    }
    private boolean coordenadasCorrectas(int fila , int columna){
        if(fila<tablero.getTabla().length && fila>=0 && columna<tablero.getTabla()[0].length && columna>=0){
            if(!tablero.getTabla()[fila][columna].isVisible()){
                return true;
            }
        }
    return false;
    }
    private void finalizarJuego(){
        System.out.println("********************************************");
        System.out.println("   juego finalizado has pisado una mina");
        System.out.println("********************************************");
        System.out.print(" ");
        for (int i = 0; i < numColumnas; i++) {
            System.out.print(" "+i+" ");
        }
        System.out.println("");
 
        for (int i = 0; i < tablero.getTabla().length; i++) {
            System.out.print(""+i);
            for (int j = 0; j < tablero.getTabla()[i].length; j++) {
                if(tablero.getTabla()[i][j].isMina()){
                    System.out.print(" M ");   
                }else{
                    if(tablero.getTabla()[i][j].isBlanco()){
                    System.out.print("   ");     
                    }else{
                    System.out.print(" "+tablero.getTabla()[i][j].getNumero()+" ");     
                    }
                
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
    private boolean descubrirCasilla(int fila , int columna){
       if(!tablero.getTabla()[fila][columna].isMina()){
           return true;
       }
       return false;
    }

private void decubrirBlanco(int fila , int columna){
        tablero.getTabla()[fila][columna].setVisible(true);
        boolean hay = false;
        int axu=0;
        for (int i = fila-1; i <= fila+1; i++) {
            for (int j = columna-1; j <= columna+1; j++) {
                if(coordenadasCorrectas(i, j)){
                    if(descubrirCasilla(i, j)){
                        if(tablero.getTabla()[i][j].isBlanco()){
                            decubrirBlanco(i, j);
                        }
                        
                       tablero.getTabla()[i][j].setVisible(true);
                        
                    }
                }
            }
        }
        
        
    }
    private boolean  partidaGanada(){
        int aux=0;
        for (int i = 0; i < tablero.getTabla().length; i++) {
            for (int j = 0; j < tablero.getTabla()[i].length; j++) {
                if(tablero.getTabla()[i][j].isMina()&&tablero.getTabla()[i][j].isBandera()){
                    aux++;
                }
            }
        }
        if(aux==numMinas){
            return true;
        }
        return false;
    }
    
    }

