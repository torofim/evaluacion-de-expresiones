/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluacion.de.expresiones;

import java.util.Stack;

/**
 *
 * @author Fimbres
 */
public class Parser {
    private Reglas[] reglas;
    private Token[] tokens;
    private Simbolos[] simbolos;
    private int sig,sigTipo;
    private Stack nodos;
    
    public Parser(){
        Gramatica gramatica=new Gramatica();
        reglas=gramatica.reglas;
    }//llave parser
    public Programa parse(Programa programa) throws Exception{
        tokens=programa.tokens;
        simbolos=programa.simbolos;
        sig=0;
        sigTipo=simbolos[tokens[sig].referencia].tipo;
        nodos=new Stack();
        if(nodos.size() != 1){
           // throws new Exception("Error con los nodos");
        }
        programa.arbol=(Arbol) nodos.pop();
        return programa;
    }
    private void parse(Reglas re) throws Exception{
        switch (re.getTipo()){
            case Reglas.entonces: parse( (Reglas.Entonces) re); break;
            case Reglas.or: parse( (Reglas.Or) re); break;
            case Reglas.vacio: parse( (Reglas.Vacio) re); break;
            case Reglas.salto: parse( (Reglas.Salto) re); break;
            case Reglas.aceptado: parse( (Reglas.Aceptar) re); break;
            case Reglas.construido: parse( (Reglas.Construir) re); break;
        }
    }
   
    private void parse(Reglas.Entonces r) throws Exception {  
        parse(reglas[r.izquierdo]);
        parse(reglas[r.derecho]);
    }

    private void parse(Reglas.Or r) throws Exception {  
        if (comienzosWith(reglas[r.izquierdo], sigTipo)) {  
            parse(reglas[r.izquierdo]);
        }
        else {
            parse(reglas[r.derecho]);
        }
    }

    private void parse(Reglas.Salto r) throws Exception {  
        if (sigTipo == r.tipoSimbolo) {  sig++;
            if (sigTipo != Simbolos.Fin) {
                sigTipo = simbolos[tokens[sig].referencia].tipo;
            }
        }
        else {
            report(tokens[sig].comienzo, sigTipo, r.tipoSimbolo);
        }
    }

    private void parse(Reglas.Aceptar r) throws Exception {  
        if (sigTipo == r.tipoSimbolo) {  
            nodos.add(new Arbol.Id(tokens[sig].referencia, tokens[sig].comienzo));
            sig++;
            sigTipo = simbolos[tokens[sig].referencia].tipo;
        } 
        else {
            report(tokens[sig].comienzo, sigTipo, r.tipoSimbolo);
        }
    }

    private void parse(Reglas.Construir r) throws Exception {  
        Arbol node1, node2;
        if (r.tamano == 1) {  
            node1 = (Arbol) nodos.pop();
            nodos.add(Arbol.build1(r.tipoSimbolo, node1));
        }
        else if (r.tamano == 2) {  
            node2 = (Arbol) nodos.pop();
            node1 = (Arbol) nodos.pop();
            nodos.add(Arbol.build2(r.tipoSimbolo, node1, node2));
        }
        else {  
            throw new Exception("Internal error: unimplemented node size");         
        }
    }
         
    private boolean comienzosWith(Reglas r, int symbolKind) {  
        int ruleKind = r.getTipo();
        boolean result;
        switch (ruleKind) {
            case Reglas.entonces: {  Reglas.Entonces rt = (Reglas.Entonces) r;
                    result = comienzosWith(reglas[rt.izquierdo], symbolKind);
                }
                break;
            case Reglas.salto: {  Reglas.Salto rk = (Reglas.Salto) r;
                    result = rk.tipoSimbolo == symbolKind;
                }
                break;
            case Reglas.aceptado: {  Reglas.Aceptar rs = (Reglas.Aceptar) r;
                    result = rs.tipoSimbolo == symbolKind;
                }
                break;
            default: result = false;
                break;
        }
        return result;
    }

    private void report(int pos, int found, int expecting) throws Exception {  
        System.out.println("Parse error at position " + pos);
        String message;
        if (found == Simbolos.CaracterInv) {
            message = "Caracter Ilegal";
        }
        else if (found == Simbolos.NumeroMal) {
            message = "Numero Incompleto";
        }
        else if (expecting == Simbolos.Fin) {
            message = "Expecting end of input";
        }
        else if (expecting == Simbolos.Numero) {
            message = "Se espera un Numero";
        }
        else {  
            int n = -1;
            for (int i = 0; i < Simbolos.clave.length; i++) {  
                if (expecting == Simbolos.clave[i].tipo) n = i;
            }
            message = "Expecting (e.g.) " + Simbolos.clave[n].simbolo;
        }
        throw new Exception(message);      
    }
    
}
