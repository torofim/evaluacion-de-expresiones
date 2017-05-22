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
public class Programa {
    
    String texto;
    Token[] tokens;
    Simbolos[] simbolos;
    Arbol arbol;
    public Programa(String t){
    this.texto=t;
    this.tokens=null;
    this.simbolos=null;
    this.arbol=null;
    }
    
    public static class Rango{int start; int end;}
    
    
    public Rango findRango(Arbol arbol){System.out.println("Rango indefinido");  return new Rango();}
    
}


