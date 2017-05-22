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
public class Calc {
    
    public Calc(){}
    public double calc(String expresion) throws Exception{
     
        Programa prog= new Programa(expresion);
        Escaner esc= new Escaner();
        Parser parser = new Parser();
        Interprete interprete = new Interprete();
        prog=esc.escanear(prog);
        prog= parser.parse(prog);
        double resultado= interprete.interpr(prog);
        return resultado;
            }
    
}
