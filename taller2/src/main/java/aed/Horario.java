package aed;

public class Horario {
    private int hs;
    private int min;


    public Horario(int hora, int minutos) {
        hs = hora;
        min = minutos;
    }

    public int hora() {
        return hs;
    }

    public int minutos() {
        return min;
    }

    @Override
    public String toString() {
        return (hs + ":" + min);
    }

    @Override
    public boolean equals(Object otro) {
        boolean otroEsNull = (otro == null);
		boolean claseDistinta = otro.getClass() != this.getClass();

		if ((otroEsNull) || (claseDistinta)) {
			return false;
        }

        Horario otroHorario = (Horario) otro;
        if ((hs != otroHorario.hora()) || (min != otroHorario.minutos())) {
          return false;
        } 

        return true;
    }

}
