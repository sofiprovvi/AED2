package aed;

public class Agenda {
    private Fecha hoy;
    private ArregloRedimensionableDeRecordatorios recordatorios;


    public Agenda (Fecha fechaActual) {
        hoy = fechaActual;
        recordatorios = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        //antes en este renglón había puesto "recordatorios = new ArregloRedimensionableDeRecordatorios()",
        //y entonces cuando quería agregar más de un recordatorio a recordatorios, el recordatorio
        //se me vaciaba cada vez que lo hacía. No entendía qué pasaba porqie
        // el test de agregarRecordatorio corría bien porque la función era correcta,
        //pero lo que no me daba cuenta es que cada vez que yo quería agregar algo nuevo 
        //se me iba vaciando el recordatorio.
        //Por eso, lo que hice fue asignar un recordatorio vacio en el constructor de 
        //Agenda que está arriba, y ahí pude agregar los recordatorios sin modificar 
        //a mi ArregloRedimensionableDeRecordatorios.
        recordatorios.agregarAtras(recordatorio);
    }

    @Override
    public String toString() { 
        String res = ""; 
        for (int n = 0; n<recordatorios.longitud();n++) {
            if (recordatorios.obtener(n).fecha().equals(fechaActual())){
               res+= recordatorios.obtener(n).toString() + "\n"; 
               }
            }
        return fechaActual() + "\n=====\n" + res;
    } 
      

    public void incrementarDia() {
        fechaActual().incrementarDia();
    }

    public Fecha fechaActual() {
        return hoy;
    }

}
