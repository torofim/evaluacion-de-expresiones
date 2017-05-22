/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluacion.de.expresiones;

import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Fimbres
 */
public class Escaner {
    
    public Programa escanear(Programa p){
        String e=p.texto;
        e=lineas(e);
        e=tabs(e);
        return p;
        
    }
    
    
    public String lineas(String expresion){
    StringBuffer buffer = new StringBuffer();
    for(int i=0;i<expresion.length();i++){
       char c = expresion.charAt(i);
       if(c=='\n'){
           if(i<expresion.length()-1 && expresion.charAt(i+1)== '\n'){
               i++;
           }//llave if dentro
           buffer.append('\n');
       }else{
           buffer.append(c);
            }//llave if
        }//llave for
        return buffer.toString();
    }//lineas
    public String tabs(String expresion){
    StringBuffer buffer = new StringBuffer();
    int col =0;
    for(int i=0; i<expresion.length();i++){
        char c = expresion.charAt(i);
        if(c=='\t'){
            int salto =8 - (col%8);
            for(int j=0;j<salto;j++) buffer.append(' ');
            col=col+salto;
        }else{
            buffer.append(c);
            if(c=='\n'){
                col=0;
            }else{
            col++;
        }//llave if      
        
               
              }//llave else
    
        }//llave for
    return buffer.toString();
    }//llave tabs  
    private preToken[] cortar(String exp){
        Vector tokens=new Vector();
        for(int x=0;x<exp.length();x++){
            char c=exp.charAt(x);
            switch(c){
                case ' ':case '\n':case '\t':break;
                case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':{
                    preToken t=numero(exp,x);
                    tokens.add(t);
                    x=t.fin-1;
                    break;
                }
                default:{
                    tokens.add(new preToken(x,x+1,Simbolos.CaracterInv));
                }
            }//llave switch
        }//llave for
        
        tokens.add(new preToken(exp.length(),exp.length(),Simbolos.Fin));
        return (preToken[]) tokens.toArray(new preToken[0]);
    }//llave cortar
    private preToken numero(String expresion,int indice){
        preToken num=null;
        int j=indice,ancho=expresion.length();
        while(j<ancho && Character.isDigit(expresion.charAt(j))){
            j++;
        }
        if(j<ancho && expresion.charAt(j)=='.'){
            j++;
            if(j<ancho && Character.isDigit(j)){
                while(j<ancho && Character.isDigit(expresion.charAt(j))){
                    j++;
                }
                num=new preToken(indice,j,Simbolos.Numero);
            }else{
                num= new preToken(indice,j,Simbolos.NumeroMal);
            }
        }else{
            num=new preToken(indice,j,Simbolos.Numero);
        }
        return num;
    }// llave numero
    private static class preToken{
        int comienzo,fin,tipo;
        preToken(int c, int f, int t){
            this.comienzo=c;
            this.fin=f;
            this.tipo=t;
        }
    }//llave pretoken
    //convierte la expresion a simbolo
    public void simbolizar(Programa pro,preToken[] tok){
    String ex= pro.texto;
    HashMap tabla =new HashMap();
    Vector sim = new Vector();
    Token[] tokens = new Token[tok.length];
    for(int i=0 ; i < Simbolos.clave.length;i++){
        sim.add(Simbolos.clave[i]);
        tabla.put(Simbolos.clave[i].simbolo,new Integer(i));
        
        }
    for(int i=0;i<tok.length;i++){
            preToken token= tok[i];
            String expre;
            Integer ref;
            if(token.comienzo == ex.length() ){
                    expre="";
            }else{
                expre=ex.substring(token.comienzo, token.fin);
                }
            ref= (Integer) tabla.get(expre);
            if (ref== 0){
                int r = sim.size();
                if(token.tipo == Simbolos.Numero){
                    double valo=Double.parseDouble(expre);
                    sim.add(new Simbolos(expre,token.tipo,valo));
                    
                    }else if(token.tipo== Simbolos.CaracterInv){
                            sim.add(new Simbolos(expre,token.tipo,0));
                        }else{
                            sim.add(new Simbolos(expre,token.tipo,0));
                    
                            }
                tabla.put(expre, new Integer(r));
                tokens[i]= new Token(token.comienzo,r);
            
            }else{
                tokens[i] = new Token(token.comienzo,ref.intValue());    
                
                }
        }
    
    }
    
}
    
