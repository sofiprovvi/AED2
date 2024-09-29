package aed;

class ArregloRedimensionableDeRecordatorios {
    private Recordatorio[] arreglo; 

    public ArregloRedimensionableDeRecordatorios() {
        arreglo = new Recordatorio[0]; //inicio un arreglo vacio
    }

    public int longitud() {
        return arreglo.length;
    }

    public void agregarAtras(Recordatorio i) {
        Recordatorio[] nuevoArreglo = new Recordatorio[longitud()+1];
        //como los arreglos en java no son mutables, tengo que construir un arreglo nuevo, 
        //asignarle la longitud que yo quiero y después asignarle ese nuevo arreglo a mi arreglo original,
        //que es el que quiero modificar
        for (int n = 0; n < arreglo.length; n++) {
            nuevoArreglo[n]=arreglo[n];
            }
        nuevoArreglo[longitud()]=i;        
        arreglo = nuevoArreglo;    
    }

    public Recordatorio obtener(int i) {
        return (arreglo[i]);
    }

    public void quitarAtras() {
        Recordatorio[] nuevoArreglo = new Recordatorio[longitud()-1];
        for (int n = 0; n < (arreglo.length -1); n++) {
            nuevoArreglo[n]=arreglo[n];
            }        
        arreglo = nuevoArreglo;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        arreglo[indice]=valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        Recordatorio[] copiaArreglo = new Recordatorio[vector.longitud()];
        for (int n = 0; n < vector.longitud(); n++) {
            copiaArreglo[n]= new Recordatorio (vector.arreglo[n].mensaje(),vector.arreglo[n].fecha(),vector.arreglo[n].horario());
            }
        arreglo = copiaArreglo;    
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        return new ArregloRedimensionableDeRecordatorios(this);  
        //este this lo pongo porque necesito que le aplique a este objeto los datos de mi constructor por copia
        //si no lo pongo, le cambio el valor a "vector", en vez de aplicarle los datos al objeto que yo necesito
    }
}
