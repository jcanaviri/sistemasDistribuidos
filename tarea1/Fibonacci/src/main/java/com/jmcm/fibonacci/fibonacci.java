
package com.jmcm.fibonacci;

import java.util.Scanner;

public class fibonacci {
    public static void main(String[] args){
        
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Introduzca la posicion del fibonacci: ");
        int posicion = entrada.nextInt();
        
        int fibo = getFibo(posicion);
        
        System.out.println("El fibonacci es: " + fibo);
        
    }
    
    public static int getFibo(int n){
        if(n < 2)
            return n;
        else
            return getFibo(n-1) + getFibo(n-2);
    }
    
}
