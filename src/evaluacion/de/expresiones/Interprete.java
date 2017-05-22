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
public class Interprete {
     public Simbolos[] simbolos;
    public Interprete(){}
    public double interpr(Programa programa){
        simbolos=programa.simbolos;
        return eval(programa.arbol);
    }
    
    private double eval(Arbol arbol){
        switch(arbol.getTipo()){
            case Arbol.id: return eval((Arbol.Id) arbol);
            case Arbol.cor: return eval((Arbol.Bracket)arbol);
            case Arbol.add: return eval((Arbol.Add)arbol);
            case Arbol.sub: return eval((Arbol.Sub)arbol);
            case Arbol.mul: return eval((Arbol.Multiplicacion)arbol);
            case Arbol.div: return eval((Arbol.Division)arbol);
            case Arbol.pot: return eval((Arbol.Potencia)arbol);
            default:
                System.out.println("Arbol de tipo desconocido");
                System.exit(0);
        }
        return 0;
    }
    private double eval(Arbol.Id t){
        return simbolos[t.ref].valor;
    }
    private double eval(Arbol.Bracket t){
        return eval(t.expr);
    }
    private double eval(Arbol.Add t){
        return eval(t.izquierdo) + eval(t.derecho);
    }
    private double eval(Arbol.Sub t){
        return eval(t.izquierdo) - eval(t.derecho);
    }
    
    
    
    
}
