package aed;

public class Recordatorio {
    private String msj;
    private Fecha f;
    private Horario h;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        msj = mensaje;
        f = new Fecha (fecha.dia(),fecha.mes());
        h = horario;
    }

    public Horario horario() {;
        return h;
    }

    public Fecha fecha() {
        return new Fecha (f.dia(),f.mes());
    }
    //otro problema que tuve acá fue el aliasing, en donde se me modificaba el valor de todo,
    //porque todo estaba en la misma posición de memoria. entonces tuve que asignar copias para todas las 
    //fechas en vez de asignarle la fecha directamente. De esta forma, cuando se modificaba el valor de f, 
    //por poner un ejemplo, el valor de la copia que yo había hecho de f no se modificaba.

    public String mensaje() {
        return msj;
    }

    @Override
    public String toString() {
        return (msj + " @ " + f + " " + h);
    }

    @Override
    public boolean equals(Object otro) {
        boolean otroEsNull = (otro == null);
		boolean claseDistinta = otro.getClass() != this.getClass();

		if ((otroEsNull) || (claseDistinta)) {
			return false;
        }
        
        //en este equals tuve problemas porque no tuve en cuenta que los atributos de Recordatorio eran
        //TADs. Para comparar la igualdad de un tad tengo que ir comparando por
        //atributo, o sea con los tipos de datos ya existentes, o aplicarle el equals que había armado
        //en los archivos anteriores.
        Recordatorio otroRecordatorio = (Recordatorio) otro;
        if  ( ((f.dia() != otroRecordatorio.fecha().dia()) || (f.mes() != otroRecordatorio.fecha().mes())) || 
             ((h.hora() != otroRecordatorio.horario().hora()) || (h.minutos() != otroRecordatorio.horario().minutos())) || 
             (msj != otroRecordatorio.mensaje()) ) {

            return false;
        }

        return true;
    }

}
