package com.interfacesFuncionais;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args){
        //Calculo soma = (a,b) -> a+b;
        Calculo somaRef = Integer::sum;
        //System.out.println(executarOperacao(soma, 1, 3));
        //System.out.println(executarOperacao(somaLambda, 1, 3));

        Calculo subtracao = (a,b) -> a-b;
        Calculo divisao = (a,b) -> a/b;
        Calculo multiplicao = (a,b) -> a*b;

        System.out.println(executarOperacao(somaRef, 1, 3));
        System.out.println(executarOperacao(subtracao, 4, 3));
        System.out.println(executarOperacao(divisao, 4, 2));
        System.out.println(executarOperacao(multiplicao, 7, 3));

        Consumer<String> imprimirUmaFrase = System.out::println;
        imprimirUmaFrase.accept("Olá dev que está olhando meu git, espero que não esteja chorando de desgosto :)");


        Function<String, String> retornaNomeAoContrario = texto -> new StringBuilder(texto).reverse().toString();
        System.out.println(retornaNomeAoContrario.apply("Jose"));
        Function<String, Integer> convertStringParaInteiroEDobra = string -> Integer.valueOf(string) * 2;
        System.out.println(convertStringParaInteiroEDobra.apply("3"));

        Predicate<String> estaVazio = String::isEmpty;
        System.out.println(estaVazio.test(""));
        System.out.println("Se retornar false a vaga da GFT é minha");
        System.out.println(estaVazio.test("Se retornar false a vaga da GFT é minha"));
        if(!estaVazio.test("Se retornar false a vaga da GFT é minha")) System.out.println("Hmmmm não tem jeito manda a entrevista que eu domino no peito");

        Supplier<Pessoa> suppliersRef = Pessoa::new;
        Supplier<Pessoa> suppliers = () -> new Pessoa();
        System.out.println(suppliersRef.get());
        System.out.println(suppliers.get());

        String[] nomes = {"Ze", "Antonio", "Favaro", "Trugilio", "Filho"};
        Integer[] numeros = {1,2,3,4,5};
        imprimirNomesFiltrados(nomes);
        imprimirTodosNome(nomes);
        imprimirODobroDeCadaItemDaLista(numeros);

        List<String> listaProfissoes = new ArrayList<>();
        listaProfissoes.add("Dev");
        listaProfissoes.add("Tester");
        listaProfissoes.add("Gerente de projeto");
        listaProfissoes.add("Gerente de qualidade");

        listaProfissoes.stream()
                .filter(profissoes -> profissoes.startsWith("Gerente"))
                .forEach(System.out::println);
    }

    /*
    Funções de alta ordem
    Função que retorna uma função ou que recebe uma função como parametro
    */
    public static int executarOperacao(Calculo calculo, int a, int b){return calculo.calcular(a, b);}


    //String... é a mesma coisa que String[]
    public static void imprimirNomesFiltrados(String... nomes){
        String nomesParaImprimir = "";
        for (String nome : nomes) {
            if (nome.equals("Ze")) nomesParaImprimir += " " + nome;
        }
        System.out.println("Nomes do for: " + nomesParaImprimir);


        String nomesParaImprimirStream = Stream.of(nomes)
                .filter(nome -> nome.equals("Ze"))
                .collect(Collectors.joining());
        System.out.println("Nomes do stram: " + nomesParaImprimirStream);

    }

    public static void imprimirTodosNome(String... nomes){
        for(String nome: nomes) System.out.println("Imprimido vida for " + nome);
        Stream.of(nomes)
                .forEach(nome -> System.out.println("Imprimido via Strem: " + nome));
    }

    public static void imprimirODobroDeCadaItemDaLista(Integer... numeros){
        //for(Integer numero : numeros)System.out.println(numero*2);

        Stream.of(numeros).map(numero -> numero*2)
                .forEach(System.out::println);
    }

}

@FunctionalInterface
interface Calculo{
    public int calcular(int a, int b);
}

class Pessoa{
    private String nome;
    private Integer idade;

    public Pessoa(){
        nome = "ze";
        idade = 20;
    }

    @Override
    public String toString() {
        return String.format("nome: %s, idade: %d",nome,idade);
    }
}
