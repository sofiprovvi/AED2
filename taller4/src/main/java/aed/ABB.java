package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo raiz;
    private int size;

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
        size = 0;
    }

    public int cardinal() {
        return size;
    }

    public T minimo(){
        if (size==0){
            return null;
        }
        else {
            return (busco_el_minimo(raiz, null)).valor;
        }  
    }

    private Nodo busco_el_minimo (Nodo recorro, Nodo _padre){
        if (recorro.izquierdo==null){
            return recorro;
        }
        else {
            return busco_el_minimo(recorro.izquierdo,recorro);
        }
    }

    public T maximo(){
        if (size==0){
            return null;
        }
        else {
            return (busco_el_maximo(raiz, null)).valor;
        } 
    }

    private Nodo busco_el_maximo (Nodo recorro, Nodo _padre){
        if (recorro.derecho==null){
            return recorro;
        }
        else {
            return busco_el_maximo(recorro.derecho,recorro);
        }
    }

    public void insertar(T elem){
        if (pertenece(elem)==false){
            size+=1;
        } 
        //el tamaño lo tengo que poner dentro de cada condición porque cuando el elemento ya esté dentro del árbol,
        //no voy a insertar ningún elemento y el tamaño no aumentaría
        Nodo ultimo = buscar_ultimo(raiz,null,elem); //me devuelve el padre del elemento que tengo que insertar
        if (ultimo==null){ //si el padre es null, le asigno el valor del elemento a la raiz del árbol
            raiz = new Nodo (elem);     
        }

        else if (ultimo.valor!=elem){
        Nodo nuevo = new Nodo (elem); 
        if ((ultimo.valor.compareTo(elem))>0) {
                ultimo.izquierdo = nuevo;
                }
    
        else if ((ultimo.valor.compareTo(elem))<0) {
                ultimo.derecho = nuevo;      
                }         
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
            res = false;
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
        if (pertenece(elem)){
            size-=1;
            Nodo padre_del_objetivo = buscar_objetivo(raiz,null,elem); //padre de mi objetivo


            //CASO ESPECIAL. Objetivo=raiz
            if (padre_del_objetivo==null) {
                Nodo objetivo = raiz;
                //raiz sin hijos:
                if (objetivo.izquierdo==null && objetivo.derecho==null){
                    objetivo=null;
                }  
                //raiz con hijo izquierdo
                else if (objetivo.izquierdo!=null && objetivo.derecho==null){
                    Nodo siguiente = objetivo.izquierdo;
                    Nodo izq = objetivo.izquierdo.izquierdo;
                    Nodo der = objetivo.izquierdo.derecho;
                    objetivo.valor=siguiente.valor;
                    objetivo.izquierdo = izq;
                    objetivo.derecho =der;
                }
                //raiz con hijo derecho
                else if (objetivo.izquierdo==null && objetivo.derecho!=null){
                    Nodo siguiente = objetivo.derecho;
                    Nodo izq = objetivo.derecho.izquierdo;
                    Nodo der = objetivo.derecho.derecho;
                    objetivo.valor=siguiente.valor;
                    objetivo.izquierdo = izq;
                    objetivo.derecho =der;
                }
                //raiz con dos hijos
                else if (objetivo.izquierdo!=null && objetivo.derecho!=null){
                    Nodo izq = objetivo.derecho.izquierdo;
                    Nodo der = objetivo.derecho.derecho;
                    Nodo reemplazo = sucesor(objetivo);
                    objetivo.valor=reemplazo.valor;
                    if (reemplazo.derecho!=null){
                        reemplazo = reemplazo.derecho;
                    objetivo.izquierdo = izq;
                    objetivo.derecho = der;
                    
                    }
                }


            }
            //RESTO DE CASOS: Objetivo != raiz:
            else {
            //Si el objetivo es el hijo izquierdo
                if (padre_del_objetivo.izquierdo != null && padre_del_objetivo.izquierdo.valor==elem){
                    Nodo objetivo = padre_del_objetivo.izquierdo;
                    //Si el objetivo no tiene descendencia:
                    if (objetivo.izquierdo==null && objetivo.derecho==null){
                        objetivo=null;
                    }  
                    //Si el objetivo tiene solo hijo izquierdo:
                    else if (objetivo.izquierdo!=null && objetivo.derecho==null){
                        objetivo=objetivo.izquierdo;
                    }
                    //Si el objetivo tiene solo hijo derecho:
                    else if (objetivo.izquierdo==null && objetivo.derecho!=null){
                        objetivo=objetivo.derecho;
                    }
                    //Si el objetivo tiene dos hijos:
                    else if (objetivo.izquierdo!=null && objetivo.derecho!=null){
                        Nodo reemplazo = sucesor(objetivo);
                        objetivo.valor=reemplazo.valor;
                        if (reemplazo.derecho!=null){
                            reemplazo = reemplazo.derecho;
                        }
                    }     
                }
                //Si el objetivo es el hijo derecho:
                else if (padre_del_objetivo.derecho != null && padre_del_objetivo.derecho.valor==elem){
                    Nodo objetivo = padre_del_objetivo.derecho;
                    //Si el objetivo no tiene descendencia:
                    if (objetivo.izquierdo==null && objetivo.derecho==null){
                        objetivo=null;
                    }   
                    //Si el objetivo tiene solo hijo izquierdo:
                    else if (objetivo.izquierdo!=null && objetivo.derecho==null){
                        objetivo=objetivo.izquierdo;
                    }
                    //Si el objetivo tiene solo hijo derecho:
                    else if (objetivo.izquierdo==null && objetivo.derecho!=null){
                        objetivo=objetivo.derecho;
                    }
                    //Si el objetivo tiene dos hijos:
                    else if (objetivo.izquierdo!=null && objetivo.derecho!=null){
                        Nodo reemplazo = sucesor(objetivo);
                        objetivo.valor=reemplazo.valor;
                        if (reemplazo.derecho!=null){
                           reemplazo = reemplazo.derecho;
                        }
                    }
                }
            }
        }
    }

    private Nodo buscar_objetivo (Nodo puntero, Nodo _padre, T elem) {
        Nodo res;
        if ((puntero.valor.compareTo(elem))==0) {
            res = _padre;
            }
        else if ((puntero.valor.compareTo(elem))>0) {
            res = buscar_objetivo(puntero.izquierdo,puntero,elem);
            }
        else {
            res =  buscar_objetivo(puntero.derecho,puntero,elem);      
            }   
        return res;          
    }
 
    private Nodo sucesor (Nodo puntero){
        return busco_el_minimo (puntero.derecho,puntero);       
    }




    public String toString(){
       return "HOLA";
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
