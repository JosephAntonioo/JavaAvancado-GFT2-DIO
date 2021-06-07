package com.processamentoAssincronoEParalelo;

import java.util.stream.IntStream;

public class Main3 {
    //Parallel Streams usado para grandes lista que não dependendem um obj do outro

    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();
        IntStream.range(1, 1000000).forEach(num -> fatorial(num)); //Serial
        long fim = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + ( fim - inicio));

        long inicio1 = System.currentTimeMillis();
        IntStream.range(1, 1000000).parallel().forEach(num -> fatorial(num)); //Parallel
        long fim1 = System.currentTimeMillis();
        System.out.println("Tempo de execução: " + ( fim1 - inicio1));
    }

    public static long fatorial(long num){
        int fat = 1;
        for(long i = 2; i <= num; i++){
            fat *= i;
        }
        return fat;
    }
}
