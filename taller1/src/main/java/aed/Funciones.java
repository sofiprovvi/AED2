package aed;

class Funciones {
    int cuadrado(int x) {
        return (x*x);
    }

    double distancia(double x, double y) {
        int numero = 0;
        numero += ((x*x) + (y*y));
        return Math.sqrt(numero);
    }

    boolean divideA(int d, int n) {
        if ((n/d)*d==n) {
            return true;
        }
        return false;
    }

    boolean esPar(int n) {
        return divideA (2,n);
    }

    boolean esBisiesto(int n) {
        return ((divideA (4,n) && ((divideA(100,n))==false)) || (divideA (400,n)));
    }

    int factorialIterativo(int n) {
        int res = 1;
        if (n==0){
           return res; 
        }
        else if (n!=0) {
           for (int i=1; i<(n+1); i++) {
               res *= i;
           } 
        }
        return res;
    }

    int factorialRecursivo(int n) {
        int res = 0;
        if (n==0) {
           res += 1;
        }
        else if (n==1) {
           res += 1; 
        }
        else if (n>1) {
           res += n*(factorialRecursivo(n-1)); 
        }
        return res;
    }

    boolean esPrimo(int n) {
        if (n<=1) {
            return false;
        }
        else {
            for (int i=2; i<n; i++) {
                if ((divideA (i,n))) {
                   return false; 
                   }
                }
             }
        return true;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for (int i = 0; i<numeros.length ; i++) {
            res+=numeros[i];
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int res = 0;
        for (int i=0; i<numeros.length; i++) {
            if (numeros[i]==buscado) {
                res = i;
            }
        }
        return res;
    }

    boolean tienePrimo(int[] numeros) {
        boolean res = false;
        for (int n: numeros) {
            if (esPrimo(n)) {
                res = true;
            }
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        boolean res =true;
        for (int n: numeros) {
            if ((esPar(n))==false) {
                res=false;
            }
        }
        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        if (s1.length() > s2.length()) {
           return false; 
            }
        else {
            for (int i = 0; i<s1.length(); i++) {
                if ((s1.charAt(i))!=(s2.charAt(i))) {
                   return false; 
                   }
                } 
            }               
        return true;  
    }

    String reverso (String s) {
        String reverso1 = "";
        for (int i = s.length()-1; i>=0; i-=1) {
            reverso1 += s.charAt(i);
            }    
        return reverso1; 
    }    

    boolean esSufijo(String s1, String s2) {
      return esPrefijo (reverso(s1),reverso(s2));
    }
}
