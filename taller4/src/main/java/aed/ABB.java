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

    public T minimo(){ //el más a la izquierda de todo el árbol
        if (size==0){
            return null;
        }
        else {
            return (buscar_minimo(raiz).valor);
        }  
    }
        
    private Nodo buscar_minimo(Nodo nodo) {
        if (cardinal()==0) { //si el árbol está vacio, devuelve null
            return null;
        }
        while (nodo.izquierdo != null) { //voy lo más a la izquierda que puedo y 
                                         //devuelvo el nodo más a la izquierda
            nodo = nodo.izquierdo;
        }
        return nodo;
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
        if (recorro.derecho==null){ //si el árbol está vacio, devuelve null
            return recorro;
        }
        else { //voy lo más a la izquierda que puedo y 
               //devuelvo el nodo más a la izquierda
            return busco_el_maximo(recorro.derecho,recorro);
        }
    }

  
    
    public boolean pertenece(T elem) {
        return busqueda_recursiva(raiz,elem); //empiezo buscando elem en el principio del árbol: la raiz
    }    

    private boolean busqueda_recursiva (Nodo puntero, T elem) {
        boolean res=false;

        if (puntero==null){ //si recorro todo el árbol hasta que llego a un nodo null, quiere decir
                            //que elem nunca fue igual a ningun puntero, por lo que res=false
            res = false;
           }

        else if ((puntero.valor.compareTo(elem))==0){ //confirmo que elem efectivamente está en el árbol,
                                                      //por lo que la función termina y res=true
            res = true;
            }
        
        //si no pasa ninguna de las dos anteriores, sigo recorriendo el árbol. 
        //si puntero<elem, sigo comparando elem con el hijo izquierdo de puntero. sino, con el derecho. 
        else if ((puntero.valor.compareTo(elem))>0) {
            res = (busqueda_recursiva(puntero.izquierdo,elem));
            }

        else if ((puntero.valor.compareTo(elem))<0) {
            res = (busqueda_recursiva(puntero.derecho,elem));      
            }

        return res;
    }


    public void insertar(T elem) {
        if (pertenece(elem)){ //si el elem ya está en el árbol, no hago nada
            return;
        }
        size+=1;
        Nodo nuevo = new Nodo(elem);
        if (raiz == null) { //si el árbol está vacio, le asigno elem a la raiz y listo
            raiz = nuevo;
        } 
        else {
            Nodo recorro = raiz;
            Nodo padre_recorro = null;
            
            while (recorro != null) { //busco al que va a ser el padre de mi elem, una vez que lo inserte 
                                      //recordar que, para agregar un elemento con nodos, 
                                      //yo tengo que asignarle el valor a algun hijo: o nodo.izq o nodo.der = elem
                                      //no puedo poner directamente nodo=elem 
                padre_recorro = recorro;
                if (elem.compareTo(recorro.valor) < 0) {
                    recorro = recorro.izquierdo; 
                } else {
                    recorro = recorro.derecho;
                }
            }
            nuevo.padre = padre_recorro;
            
            if (elem.compareTo(padre_recorro.valor) < 0) { //una vez que encontré al padre
                                                           //veo si le asigno elem al hijo izq o der
                padre_recorro.izquierdo = nuevo; 
            } else {
                padre_recorro.derecho = nuevo;
            }
        }
    }
   
    public void eliminar(T elem) {
        if (pertenece(elem)) {
            size -= 1;
            this.raiz = eliminarRecursivo(raiz, elem);
            //acá tengo que poner sí o sí el this porque estoy trabajando con una instancia de raiz
        }
    }
    
    private Nodo eliminarRecursivo(Nodo nodo, T elem) { //devuelve el nodo que tendría que reacomodar una vez que elimine a elem
        if (nodo == null) {
            return null;}
        int n = elem.compareTo(nodo.valor);
        if (n < 0){
            nodo.izquierdo = eliminarRecursivo(nodo.izquierdo, elem);} 
        else if (n > 0) {
            nodo.derecho = eliminarRecursivo(nodo.derecho, elem);} 
        else { //nodo==elem
            if (nodo.izquierdo == null && nodo.derecho == null) {
                return null;
            } else if (nodo.izquierdo == null) {
                return nodo.derecho;  //por el this, reacomodo lo que haya quedado mal en el árbol
            } else if (nodo.derecho == null) {
                return nodo.izquierdo; //por el this, reacomodo lo que haya quedado mal en el árbol
            } else { //si ninguno de los hijos es null, reemplazo a mi elem por el sucesor, es decir, el mínimo a la derecha
                Nodo sucesor = encontrarMinimo(nodo.derecho); //busco el sucesor
                nodo.valor = sucesor.valor; //elimino elem y lo reemplazo por el sucesor que encontré
                nodo.derecho = eliminarRecursivo(nodo.derecho, sucesor.valor); 
                //reacomodo lo que haya quedado mal en el árbol. 
                //pensar que voy reacomodando subárboles. this.raiz va a ir cambiando, es la raiz del árbol/subárbol que tendría que reacomodar
            }
        }
        return nodo;
    }
    
    private Nodo encontrarMinimo(Nodo nodo) { //hace lo mismo que buscar_minimo. está al pedo. 
                                              //la puse pq no queria tocar nada una vez que me dieran bien los tests
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }
    

    public String toString(){
        Iterador<T> i = iterador();
        if (cardinal()==0) {
            return "{}";
        }
        else if (cardinal()==1){
            return  "{" + maximo() + "}";}
        
        else {
            String res = "{";
            if (i.haySiguiente()){
               res+= i.siguiente();
        
           while (i.haySiguiente()) {
              res+= "," + i.siguiente();    
           }
        res+= "," + maximo();
        res+="}";} 

        return res;
        }}


 
    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual = buscar_minimo(raiz); //es un iterador. recorre los elementos en orden. empiezo por el minimo

        public boolean haySiguiente() { //deja de haber siguiente cuando actual=maximo o el árbol es vacio
            return (_actual != null && _actual != busco_el_maximo(raiz,null) );
        }
    
        public T siguiente() { //devuelve el actual y después le asigna a actual su sucesor
            T res = _actual.valor;
            _actual = sucesor(_actual);
            return res;
        }
    
        private Nodo sucesor(Nodo nodo) { 
            if (nodo.derecho!=null) { //si tiene hijo der, busco el mínimo a la derecha y ese va a ser el siguiente a mi actual
                return buscar_minimo(nodo.derecho);
               } 
            else {
                Nodo hijo = nodo;
                Nodo res = nodo.padre;
                //este algoritmo de abajo lo explicaron en clase y nunca me lo acuerdo y se entiende mejor con ejemplo y dibujos.
                //basicamente, si el nodo hijo es un hijo derecho de su padre, continuo subiendo en el árbol 
                //hasta que encuentro un nodo donde actual esté en el subárbol izquierdo. ese va a ser mi sucesor, o va 
                //a devolver null en caso de que mi nodo sea juusto el máximo
                while (res != null && hijo == res.derecho) {
                    hijo = res;
                    res = res.padre;
                }
                return res;
            }
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
        }
}
