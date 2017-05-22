/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluacion.de.expresiones;

/**
 *
 * @author Fimbres
 */
public abstract class Reglas {
    public static final int vacio = 0, salto =1 , aceptado =2, construido=3,
            entonces =4,or =5;
    abstract int getTipo();
    public static class Vacio extends Reglas{
        Vacio(){}
    int getTipo(){
        return vacio;
        
             }//llave getTipo
    
        }//llave vacio
    
    public static class Salto extends Reglas{
        int tipoSimbolo;//atributo
        ///constructor
        Salto(int t){
            this.tipoSimbolo=t;
        }//llave salto
        //getter del tipo
        int getTipo(){
           return this.aceptado; 
        }
        
        }//llave Salto clase
    
    public static class Aceptar extends Reglas{
        int tipoSimbolo;
        Aceptar(int t){
            this.tipoSimbolo=t;
            
        
        }//llave ace
        int getTipo(){
            return this.tipoSimbolo;
        }
    }//llave aceptar
    public static class Construir extends Reglas{
        int tipoSimbolo, tamano;
        Construir(int t, int ta){
            this.tipoSimbolo=t;
            this.tamano=ta;
        }
        int getTipo(){
            return construido;
        }//llave getipo
    }//llave construir
    public static class Entonces extends Reglas{
        int derecho,izquierdo;
        Entonces(int d, int i){
            this.derecho=d;this.izquierdo=i;
        }
        int getTipo(){
            return entonces;
        }//llave entonces
    }//llave entonces
    public static class Or extends Reglas{
    int derecho,izquierdo;
    Or(int d , int i){
        this.derecho=d;
        this.izquierdo=i;
        }
    int getTipo(){return or;}
            }
    
}//llave reglas
