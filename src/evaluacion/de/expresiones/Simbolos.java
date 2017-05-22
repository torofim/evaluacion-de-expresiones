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
public class Simbolos {
    String simbolo; //guardara el simbolo
    int tipo;//guardara por numeros los simbolos
    double valor;
    
    //constructor//
    //quien quiera hacer un simbolo me tiene que enviar eso//
    
    public Simbolos(String _simb,int _tipo, double _valor){
    this.simbolo = _simb;
    this.tipo = _tipo;
    this.valor = _valor;
    }//fin Constructor
    
    //tabla de simobolos//
    public static int Mas=0,Menos=1,Mult=2,Div=3,Pot=4,Abierto=5,Cerrado=6,
            Fin=7,Numero=8,Mal=9,NumeroMal=10,CaracterInv=11;
    
    public static Simbolos[]clave={
    new Simbolos("+",Mas,0.0),
    new Simbolos("-",Menos,0.0),
    new Simbolos("*",Mult,0.0),
    new Simbolos("/",Div,0.0),
    new Simbolos("^",Pot,0.0),
    new Simbolos("(",Abierto,0.0),
    new Simbolos(")",Cerrado,0.0),
    new Simbolos("",Fin,0.0)
    
    };
    public String getTipo(){
        switch(this.tipo){
            case 0:{return "suma";}
            case 1 :{return "resta";}
            case 2: {return "multiplicacion";}
            case 3:{return "division";}
            case 4:{return "potencia";}
            case 5: {return "parentesis ( ";}
            case 6: {return "parentesis ) ";}
            default:{return "invalido";}
        }
    }
}
