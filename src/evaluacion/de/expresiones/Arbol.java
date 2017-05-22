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
public abstract class  Arbol {
    
    public static final int error=0,id=1,add=2,cor=3,sub=4,mul=5,div=6,pot=7;
    
    static Arbol build1(int tipo, Arbol nodo){
    if(tipo!=cor){
        System.out.println("Error:arbol indefinido");
        System.exit(0);
                }
    return new Bracket(nodo);
    }
    public static Arbol build2(int tipo, Arbol nodo1, Arbol nodo2){
    switch(tipo){
        case add: return new Add(nodo1,nodo2);
        case sub: return new Sub(nodo1, nodo2);
        case mul: return new Multiplicacion(nodo1, nodo2);
        case div: return new Division(nodo1,nodo2);
        case pot: return new Potencia(nodo1,nodo2);
        
        default:
            System.out.println("error en el arbol");
            System.exit(1);
        }
    return null;
    }
    /*los tipos de datos abstractos no se intancian solamente se utilizan para hacer regerencia a ellos
    como si fuera otro tipo de dato, no un objeto
    */
    
    abstract int getTipo();
    abstract int getPrefix();
    abstract int getPostfix();
    
    
    public static class Error extends Arbol{
        String mensaje;
        int start, end;
        Error(int s, int e){this.start=s;this.end=e;}
        int getTipo(){return error;}
        int getPrefix(){return 0;}
        int getPostfix(){return 0;}
    
    }
    
    public static class Id extends Arbol{
    int ref, start;
    Id(int r , int s){
        this.ref=r; this.start=s;
                }
    int getTipo(){return id;}
    int getPrefix(){return 0;}
    int getPostfix(){return 0;}
    }
    
    public static class Bracket extends Arbol{
    Arbol expr;
    Bracket(Arbol a){
        expr=a;
    }
    int getTipo(){return cor;}
    int getPrefix(){return 1;}
    int getPostfix(){return 1;}
    
        }
    
    public static class Add extends Arbol{
    Arbol izquierdo, derecho;
    Add(Arbol i , Arbol d){
        this.izquierdo=i;
        this.derecho=d;
            }
    int getTipo(){return mul;}
    int getPrefix(){return(0);}
    int getPostfix(){return(0);}
    }
    
    public static class Sub extends Arbol{
    Arbol izquierdo, derecho;
    Sub(Arbol i, Arbol d){
        this.izquierdo =i;
        this.derecho=d;
        }
    int getTipo(){
        return sub;
        }
    int getPrefix(){
          return (0);
               }
    int getPostfix(){
        return(0);
        }
    
    }
    
    public static class Multiplicacion extends Arbol {
    Arbol izquierdo, derecho;
    Multiplicacion(Arbol i, Arbol d){
        this.izquierdo=i;
        this.derecho=d;
    }
    int getTipo(){
        return mul;
            }
    int getPrefix(){
        return (0);
            }
    int getPostfix(){
        return (0);
            }
    
        
    }
 
    public static class Division extends Arbol{
    Arbol izquierdo, derecho;
    Division(Arbol i, Arbol d){
        this.izquierdo=i;
        this.derecho=d;
    }
    int getTipo(){
        return div;
            }
    int getPrefix(){
        return (0);
            }
    int getPostfix(){
        return (0);
            }
    
    
    }
    
    public static class Potencia extends Arbol{
    Arbol izquierdo, derecho;
    Potencia(Arbol _i, Arbol _d){
        this.izquierdo=_i;
        this.derecho=_d;
        
        
                }
    int getTipo(){
        return pot;
                }
    int getPrefix(){
            return(0);
            }
    int getPostfix(){
            return(0);
            }
    }
    
}
