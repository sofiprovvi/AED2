package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    //estos son los atributos de mi clase listaEnlazada
    private Nodo primero;
    private int s;

    //como Nodo es una clase que no definí, la defino ahora.
    //sería una subclase 
    private class Nodo {
        //estos son los atributos de mi subclase Nodo, q serían el 
        //valor al que hace referencia el nodo + el nodo siguiente.
        //el nodo sería como señalar con el dedo(? es una REFERENCIA
        T valor;
        Nodo siguiente;

        Nodo (T p) {
        valor = p;
        }
    }
        

    public ListaEnlazada() {
        primero = null;
        s = 0;
    }

    public int longitud() {
        return s;
    }


    public void agregarAdelante(T elem) { 
        Nodo nuevo = new Nodo (elem);
        //lo único que tengo que hacer es reordenar los nodos: el primero ahora es el nuevo y el siguiente es el primero
        //y los otros le van a seguir a nuevo.siguiente. el resto de los nodos se reordenan solos!!! 
        //recordar que siempre un nodo apunta a otro nodo a no ser que lo que siga a ese nodo sea nulo. 
        nuevo.siguiente = primero;
        primero = nuevo;
        s+=1;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo (elem); 
        //si primero es null, lo único q tengo q hacer es que el nodo primero apunte al nuevo.
        if (primero == null){
            primero = nuevo;
        }
        //sino, me invento un nodo q recorra todos los nodos hasta llegar al
        //último, y cuando llegue a ser nulo, le asigna esa posición al nodo nuevo
        else {
        Nodo recorriendo = primero;
        while (recorriendo.siguiente!=null) {
              recorriendo = recorriendo.siguiente;
              } 
              recorriendo.siguiente = nuevo;
            }
        s+=1;
    }

    public T obtener(int i) {
        int n=0;
        Nodo actual = primero; 
        //cuando invento un Nodo y lo llamo actual, no estoy modificando el valor
        //de nada. el Nodo "actual" es una REFERENCIA al nodo "primero" y lo uso para recorrer
        //todos los nodos con actual.siguiente. lo que hice acá fue recorrer todos los nodos hasta llegar al 
        //nodo i, o sea apreté actual.siguiente i veces. 
            while (n<i) {
                 actual = actual.siguiente;
                 n+=1;
           } 
        return actual.valor;  
    }

    public void eliminar(int i) {
        Nodo actual = primero; 
        int n = 0;
        if (i==0){
           primero = primero.siguiente;
        }
        else {
        while (n<(i-1)) {
            actual = actual.siguiente; 
            n+=1;
            }
        actual.siguiente = actual.siguiente.siguiente;
        }
        s-=1;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero; 
        int n = 0;
        while (n<indice) {
            actual = actual.siguiente; 
            n+=1;
            }
        actual.valor = elem;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        primero = new Nodo (lista.primero.valor); //le asigno a primero una copia del primero de la lista.
        Nodo actual = primero; //armo un nodo que recorra los nodos de mi copia.
        Nodo recorro = lista.primero.siguiente; //armo un nodo que recorra los nodos de la lista q quiero copiar.
        while (recorro!=null) {
            Nodo nuevoNodo = new Nodo(recorro.valor); //armo un nodo q *copie los valores* de los nodos de la lista. 
            actual.siguiente = nuevoNodo; //esto del "siguiente" lo hago para hacer que mi nodo actual APUNTE al nuevoNodo,
                                          // pq sin esto los nodos no están APUNTANDO a nada. yo necesito q cada nodo apunte a algo,
                                          //además de asignarles su valor.
                                          //recordar q los nodos tienen dos elementos: el nodo siguiente y su valor. 
            actual = nuevoNodo; // y esto es para ir recorriendo cada nodo que fui asignando en el renglón anterior.
            recorro = recorro.siguiente;
        }
        s = lista.s;
    }
    
    @Override
    public String toString() {
        String res = "[" + primero.valor;
        Nodo actual = primero.siguiente;
        while (actual!=null) {
              res += ", " + actual.valor;
              actual = actual.siguiente;     
              }
        return res + "]";      

    }


    private class ListaIterador implements Iterador<T> {
        private int dedo; //no es una posición. Está *entre* posiciones. 

        public ListaIterador() {
            dedo = 0; //le asigno su posición inicial 0, que estaría entre el vacio y el primer elemento de mi lista.
        }

        public boolean haySiguiente() {
            int indices = 0;
            boolean res = true;
            if (primero==null){ //si la lista es vacia, entonces no hay siguiente. 
                res = false;
            }
            else {
            Nodo recorro = primero;
            while (recorro.siguiente!=null){ //averiguo la cantidad de posiciones que hay en mi lista
                recorro = recorro.siguiente;
                indices+=1;
                }  
            if (dedo>indices){ //mi dedo siempre va a tener una posición a su derecha,
                               //a no ser que se encuentre a la derecha del último elemento,
                               //es decir, a no ser que mi dedo sea igual o mayor a la longitud de la lista. 
                res = false;
               }      
            }
            return res;
        }
        
        public boolean hayAnterior() {
            boolean res = true;
            if ((dedo<=0) || (primero==null)){ //mi dedo siempre va a tener una posición a su izquierda,
                                               //a no ser que la lista sea vacia; o que la lista no sea
                                               //vacia y mi dedo justo se encuentre entre el vacio y el primer elemento 
                                               //de la lista, es decir, que sea menor o igual a 0. 
                 res = false;
                } 
            return res;   
        }

        public T siguiente() {  //devuelvo el elemento que se encuentra a la derecha de mi dedo
                                //y luego aumento +1 la posición de mi dedo.
            dedo+=1; 
            return obtener(dedo-1);
        }
      

        public T anterior() { //devuelvo el elemento que se encuentra a la izquierda de mi dedo
                              //y luego retrocedo -1 la posición de mi dedo.
            dedo-=1;     
            return obtener(dedo);
        }

    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }
}
    
