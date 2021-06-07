package com.novidadesJava10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        connectAndPrintURL();
        //printarNomeCompleto("ze", "favaro");
        //printarSoma(1,5,7);
    }
    /*
    Anotações
    var não pode ser utilizado em nivel de classe e nem como parametro, é apenas
    uma variavel local.
     */

    public static void connectAndPrintURL() throws IOException {
        try{
        var url = new URL("https://docs.oracle.com/javase/10/language/");
        var urlConnection = url.openConnection();

        try(var bufferedReader = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));){
            System.out.println(bufferedReader.lines().collect(Collectors.joining())
                    .replaceAll(">", ">\n"));
        }}catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void printarNomeCompleto(String nome, String sobrenome) {
        var nomeCompleto = nome + " " + sobrenome;
        System.out.println(nomeCompleto);
    }

    public static void printarSoma(int... numeros) {
        var soma = 0;
        var soma1 = 0;
        if(numeros.length > 0) for(var numero : numeros) soma += numero;
        if(numeros.length > 0) for(var i = 0; i < numeros.length; i++)soma1 += numeros[i];
        System.out.println(soma);
        System.out.println(soma1);
    }
}
