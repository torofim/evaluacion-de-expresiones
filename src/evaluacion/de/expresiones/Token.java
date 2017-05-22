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
public class Token {
    
    int comienzo,referencia;
    Token(int c , int r){
        this.comienzo=c;
        this.referencia=r;
    }//llave ctoken
}
