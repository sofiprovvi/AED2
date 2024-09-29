package aed;

public class Fecha {
    private int d;
    private int m;

    public Fecha(int dia, int mes) { //le asigno los valores a los atributos de mi objeto Fecha
       d = dia;
       m = mes;
    }

    public Fecha(Fecha fecha) { //estoy construyendo a mi objeto Fecha
        fecha.m = m;
        fecha.d = d;  
    }

    public Integer dia() { //me devuelve el 1er atributo dia
        return d;
    }

    public Integer mes() { //me devuelve el 2do atributo mes
        return m;
    }

    public String toString() {
        return (d +"/"+ m);
    }

    @Override
    public boolean equals(Object otra) {
        boolean otroEsNull = (otra == null);
		boolean claseDistinta = otra.getClass() != this.getClass();

		if ((otroEsNull) || (claseDistinta)) {
			return false;
        }

        Fecha otro = (Fecha) otra;
        if ((d != otro.d) || (m != otro.m)) {
          return false;
        } 

        return true;
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

    public void incrementarDia() { 
        //detalle importante de esta función: el error que yo cometía era
        //que ponía siempre un "if" después del primer if, en vez de "else if", entonces cuando se cumplía
        //en un if, se modificaba el valor de d, por poner un ejemplo, y entonces evaluaba en los otros ifs
        //y de repente se cumplía en más de una condición y el test me devolvía cualquier cosa, cuando
        //en realidad, una vez que se cumple en un if ya termina el programa, y si no se cumple en ese if 
        //va pasando al siguiente y así sucesivamente. 

        if (diasEnMes(m)==30 && d<30){
           d+=1;
        }

        else if (diasEnMes(m)==30 && d==30){
            d=1;
            m+=1; 
        }
        
        else if (diasEnMes(m)==31 && d<31){
            d+=1; 
        }
 
        else if (diasEnMes(m)==31 && m!=12 && d==31){
            d=1;
            m+=1; 
        }

        else if (diasEnMes(m)==28 && d<28){
            d+=1; 
        }
        else if (diasEnMes(m)==28 && d==28){
             d=1;
             m+=1; 
        }
        else if (m==12 && d==31){ //este if es solo por ser el último día del año,
                                  //en donde cambian tanto m como d
             d=1;
             m=1;
        }     
        }

    } 

      
    


