package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo raiz;
    private int tamaño;

    private class Nodo {
            Nodo izquierdo;
            Nodo derecho;
            Nodo padre;
            T valor;

            Nodo (T v) {
            valor = v;
            izquierdo = null;
            derecho = null;
            padre = null;
            }

    }

    public ABB() {
        raiz = null;
        tamaño = 0;
    }

    public int cardinal() {
        return tamaño;
    }

    public T minimo(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public T maximo(){
        throw new UnsupportedOperationException("No implementada aun");
    }


    public void insertar(T elem){ 
        //el tamaño lo tengo que poner dentro de cada condición porque cuando el elemento ya esté dentro del árbol,
        //no voy a insertar ningún elemento y el tamaño no aumentaría
        Nodo ultimo = buscar_ultimo(raiz,null,elem); //me devuelve el padre del elemento que tengo que insertar
        if (ultimo==null){ //si el padre es null, le asigno el valor del elemento a la raiz del árbol
            raiz = new Nodo (elem); 
            tamaño+=1;    
        }

        else if (ultimo.valor!=elem){
        Nodo nuevo = new Nodo (elem); 
        if ((ultimo.valor.compareTo(elem))>0) {
                ultimo.izquierdo = nuevo;
                }
    
        else if ((ultimo.valor.compareTo(elem))<0) {
                ultimo.derecho = nuevo;      
                } 
        tamaño+=1;        
        }  
                
       
    }

    //esta función busca el padre del elemento que voy a insertar en mi árbol:
    private Nodo buscar_ultimo (Nodo puntero, Nodo _padre, T elem) {
        if (puntero==null) {
            return _padre; //cuando mi puntero es nulo, devuelvo el padre 
                           //para después asignarle mi elemento al hijo de ese padre en mi función principal
           }
        else if ((puntero.valor.compareTo(elem))==0) {
            return puntero; //cuando el elemento ya esté en el árbol, ya voy a saber que no tengo que hacer nada
            }
        //si esto no ocurre, sigo buscando el padre con recursión:
        else if ((puntero.valor.compareTo(elem))>0) {
            return buscar_ultimo(puntero.izquierdo,puntero,elem);
            }

        else {
            return buscar_ultimo(puntero.derecho,puntero,elem);      
            }       
        }


    public boolean pertenece(T elem) {
        return busqueda_recursiva(raiz,elem);
    }    

    private boolean busqueda_recursiva (Nodo puntero, T elem) {
        boolean res=false;

        if (puntero==null){
            res=false;
           }

        else if ((puntero.valor.compareTo(elem))==0){
            res = true;
            }

        else if ((puntero.valor.compareTo(elem))>0) {
            res = (busqueda_recursiva(puntero.izquierdo,elem));
            }

        else if ((puntero.valor.compareTo(elem))<0) {
            res = (busqueda_recursiva(puntero.derecho,elem));      
            }

        return res;
        }


    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
