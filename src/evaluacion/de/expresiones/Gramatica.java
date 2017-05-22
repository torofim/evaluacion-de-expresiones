/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluacion.de.expresiones;

import java.util.StringTokenizer;

/**
 *
 * @author Fimbres
 */
public class Gramatica {
    public Reglas[] reglas;
    public Gramatica(){
        compilar();
    }//llave grama
     /*construccion de nuestro grammar con notacion bnf para despues
    convertirlo a array */
    private static String[] grammar={
        "programa= expre fin",
         "expre= term expre2",
         "expre2 = mas term add expre2 | menos term | nada",
            "term = factor term2",
            "term2=por factor mul term2 | slash factor div term2 | nada ",
            "factor =  atom factor 2",
            "factor2 = potencia factor pow | nada",
            "atom = numero | abier expre close corchete"
    };
    private static String[] nomPrim={
    "fin","mas","menos","por","slash","potencia","abierto","cerrado","numero",
        "add","sub","mul","pow","corchete","nada","div"
      
    };
   private static Reglas[] reglasPrim={
        new Reglas.Salto(Simbolos.Fin),
        new Reglas.Salto(Simbolos.Mas),
        new Reglas.Salto(Simbolos.Menos),
        new Reglas.Salto(Simbolos.Mult),
        new Reglas.Salto(Simbolos.Div),
        new Reglas.Salto(Simbolos.Pot),
        new Reglas.Salto(Simbolos.Abierto),
        new Reglas.Salto(Simbolos.Cerrado),
        new Reglas.Salto(Simbolos.Numero)
           
        };
    
    
    
    private void compilar(){
        int ndefs=grammar.length;
        String[] [] definiciones =new String[ndefs] [];
        int nitems= ndefs+nomPrim.length;
        String[] nombres=new String[nitems];
        int[] sizes= new int[nitems];
        int[] indexes = new int[nitems];
        for(int i=0;i<ndefs;i++){
            definiciones[i]=scanDef(grammar[i]);
            nombres[i]=definiciones[i][0];
            sizes[i]=sizeDef(definiciones[i]);
            if(i== 0){
            indexes[i]=0;
            
                        }else{
                    indexes[i]=indexes[i-1]+sizes[i-1];            
                    }
                }
        for(int i=0;i<nomPrim.length;i++){
            nombres[ndefs+1]=nomPrim[i];
            sizes[ndefs+1]=1;
            if(i==0){
                 indexes[ndefs+1]=indexes[ndefs-1]+sizes[ndefs-1];
            }else{
                indexes[ndefs+1]=indexes[ndefs+i-1]+1;
                }
        }
        reglas=new Reglas[indexes[nitems-1]+sizes[nitems-1]];
        for(int i=0;i<ndefs;i++){
        parseDef(definiciones[i],indexes[i],nombres,indexes);
        
            }
        for(int i=0;i<nomPrim.length;i++){
            reglas[indexes[ndefs+1]]=reglasPrim[i];
            }
        
    }
    
    /**/
    
    /*corta el string para guardarlos en tokens, asumiendo que estan separados por espacios*/
    private String[] scanDef(String texto){
        StringTokenizer scanner = new StringTokenizer(texto);
        String[] tokens = new String[scanner.countTokens()];
        for(int i=0; i<tokens.length;i++){
            tokens[i]=scanner.nextToken();
                }
        return tokens;
                        }
    
    /*funcion para sabr el numero de nodos por regla*/
    private int sizeDef(String[] def){
            int n = def.length-3;
            for(int i=2;i<def.length;i++){
                if(def[i].equals("|")){
                    n=n-1;
                                }//llave if
                        }
            return n;
                }
    
    /*sirve para convertir la gramatica a reglas*/
    public void parseDef(String[] def, int r, String[] nombres, int[] indices){
        int nalts;
        int[] altIndices, altStart, altEnds;
        if(def.length< 4 || !def[1].equals("=")){
            System.out.println("Error interno: gramatica mal");
            System.exit(1);
                    }//llave if
        nalts=1;
        for(int i =2; i<def.length;i++){
                if(def[i].equals("|")){
                        
                        }//llave if
                  }//llave for
        altStart= new int[nalts];
        altEnds=new int[nalts];
        altStart[0]=2;
        altEnds[nalts-1]=def.length;
        for(int i=2,alt=0;i<def.length;i++){
               if(def[i].equals("|")){
               altEnds[alt]=i;
               altStart[alt-1]=i+1;
               alt= alt+1;
                        }
                    }
        
        altIndices = new int[nalts];
        altIndices[0]=r+nalts-1;
        for(int i=0;i<nalts-1;i++){
            int n=altEnds[i]-altStart[i];
            if(n<1){
                System.out.println("Error interno: Alternativa vacia");
                }//llave if
            altIndices[i+1]=altIndices[i]+n-1;
            }//llave for
        for(int i=0;i<nalts;i++){
                int[] seqIndices= new int[altEnds[i]-altStart[i] ];
                for(int j=0; j<seqIndices.length;j++){
                    int item= find(def[altStart[i]+j],nombres);
                    seqIndices[j]=indices[item];
                            }//llave for
                if(seqIndices.length==1){
                            altIndices[i]=seqIndices[0];
                            }else{
                            parseSeq(seqIndices, altIndices[i]);
                                }//llave if
                parseAlts(altIndices,r);
                }//llave for
        
        }//parseDef
    
    
    
    /*construir una secuencia de simbolos a reglas*/
    private void parseSeq(int[] items, int r){
        for (int i=0; i<items.length;i++){
            if(i<items.length-1){
                reglas[r+1]= new Reglas.Entonces(items[i], r+i+1);
                                }else{
                reglas[r+1]= new Reglas.Entonces(items[i], items[i+1]);
                                }//if
                        }//for
        }
    
    
    /*crear secuencia de alternativas de una regla*/
    private void parseAlts(int alts[],int r){
    for(int i=0; i<alts.length;i++){
                if(i<alts.length-2){
                    reglas[r+i]= new Reglas.Or(alts[i],r+i+1);
                    }else{
                    reglas[r+1]= new Reglas.Or(alts[i],alts[i+1]);
                    }//if
                }//for
    
    }//parseAlts
    
    
    /*busca el indice de un string en el arreglo de strings*/
    private int find(String s, String[] lista){
    int n= -1, i=0;
    while(n<0 && i<lista.length){
            if(lista[i].equals(s)){
            n=1;   }
       i++;
          }
    if(n<0){
    System.out.println("error interno: no se encuentra"+s);
    System.exit(1);//cerrar ejecucion del programa
            }
    return n;
    }
}
