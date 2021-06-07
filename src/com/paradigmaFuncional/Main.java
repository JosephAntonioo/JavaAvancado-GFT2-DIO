package com.paradigmaFuncional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.UnaryOperator;

public class Main {
    static Map<Integer, Integer> MAPA_FATORIAL = new HashMap<>();
    public static void main(String[] args) {
	/*
	ParadigmaFuncional
	Programação funcional é o proesso de construir software atrvés de
	composição de funões puras, evitando compartilhamento de estados,
	dados mutáveis e efeitos colaterais. é clarativa ao invés de imperátiva,
	essa é uma definição de Eric Elliot

	Paradigma Imperativo
	É aquele que expressa o código através de comandos ao computador, nele
	é possível ter controle de estado dos objetos:
    */
        /*
        int valor = 10; //instrução | Pegue a memoria, insira o valor 10 na memoria
        int resultado = valor * 3;//instrução | Pega mais um lugar na memoria, e multiplique valor por 3
        System.out.println("O resultado é: " + resultado); // instrução | print na tela o resultado      */
    //Entao o paradigma funcional é aquele que passamos instruções que são executadas instantaneamente , funciona como se fossem ordens de computador

    /*
    Paradigma Funcional
    Damos uma regra, uma declaração de como quereremos que o programa se comporte
    */
    /*  UnaryOperator<Integer> calcularValorVezesTrez =  valor -> valor*3; //um conceito paragdima funcional
        int valor = 10;
        System.out.println("O resultado é: " + calcularValorVezesTrez.apply(10));*/

    /*
    Conceitos fundamentais da programação funcional
    composição de funções: é criar uma nova função através da composição de outras
    vamos criar: função q vai filtar um array, filtrando somente os números pares e multiplicando por dois:
     */
        //paradigma funcional
        int[] valores = {1,2,3,4};
        Arrays.stream(valores)
                .filter(numero -> numero % 2 == 0)
                .map(numero -> numero * 2)
                .forEach(System.out::println);
        //paradigma imperativo
        for(int i = 0; i < valores.length; i++){
            int valor = 0;
            if(valores[i] % 2 == 0){
                valor = valores[i] * 2;
                if(valor != 0){
                    System.out.println(valor);
                }
            }
        }
    /*
    Funcoes puras
    É chamada de pura quando invocada mais de uma vez produz exatamente o mesmo resultado
    */
        BiPredicate<Integer, Integer> verificaSeMaior  =
                (parametro, valorComparacao) -> parametro > valorComparacao;
        System.out.println(verificaSeMaior.test(13, 12));
        System.out.println(verificaSeMaior.test(13, 12));
    /*
    Imutabilidade: Significa que uma vez que uma var que recebeu um valor, vai possuir esse valor para sempre,
    ou quando criamos um objeto ele não pode ser modificado
    */
        int valor = 20;
        UnaryOperator<Integer> retornaDobro = v -> v *2;
        System.out.println(retornaDobro.apply(valor)); //retorna dobro do valor
        System.out.println(valor); //valor nao sera alterado

    /*
    Imperativo x Declarativo
    é muito comum aprender a programar de forma imperativa onde mandamos alguem fazer algo.
    Na programação funcional tentamos programar de forma declarativa, onde declaramos oq desejamos, sem explicitar como
    sera feito.
    */

    /*
    Os lambdas obedecem o conceito do paradigma funcional, com eles podemos facilitar legibilidade do nosso codigo,
    alem disso com a nova API Funcional do java podemos ter uma alta produtividade para lidar com objetos.
    Primeiramente, devemos entender o q sao interfaces funcionais
    Interfaces funcionais são interfaces que possuem apenas um metodo abstrato
    exemplo
    public interface Funcao{
        String gerar(String valor);
    }
    Geralmente as interfaces funcionais possuem uma anotacao em nivel de classe (@FunctionalInterface), par forçar o
    compilador a apontar um erro se a interface nao estiver de acordo com regras de uma interface funcional. Esta
    anotação não obrigatória, pois o compilador consegue reconhecer uma interface em tempo de compilacao
     */
        Funcao gerarUmaSaida = valor1 -> valor1;

    /*
    Estrutura de uma lambda
    InterfaceFuncional nomeVariavel = parametro -> logica
     */
    //Exemplo com a interface Funcao
        //Sem lambda
        Funcao colocarPrefixoSenhorNaString = new Funcao() {
            @Override
            public String gerar(String valor) {
                return "Sr. " + valor;
            }
        };
        System.out.println(colocarPrefixoSenhorNaString.gerar("Menor da VG"));

    //Com lambda - bem mais limpo né zé, entao aprende a codar assim que vai chover proposta de entrevista na dm do email
        Funcao colocarPrefixoSenhorNaStringLambda = valor1 -> "Sr. " + valor1;
        System.out.println(colocarPrefixoSenhorNaStringLambda.gerar("Carlão do caminhão"));

    //Quando uma lambda possui apenas uma instrução no corpo de sua logica não é necessário o usu de chaves
    //Funcao colocarPrefixoSenhorNaStringLambda = valor1 -> "Sr. " + valor1; // exemplo com uma unica instrução
    /*
    Funcao colocarPrefixoSenhorNaStringLambda = valor1 -> {
    String valorComPrefixo = "Sr. " + valor;
    String valorComPrefixoEPontoFinal = valorComPrefixo + ".";
    return valorComPrefixoEPontoFinal;
    }
     */
        Funcao funcao = valor2 -> valor2;
        System.out.println(funcao.gerar("hello moto vrum vrum"));

        Funcao1 funcao1 = valor3 ->{
            System.out.println(valor3);
            System.out.println(valor3);
        };
        funcao1.gerar("Camiseta confortavel");

        Funcao funcao2 = valor4 ->{
            return valor4;
        };
        System.out.println(funcao2.gerar("Ela roubou meu caminhao"));

    /*
    Recursividade
    Na recursividade, uma função chama a si mesma repetidamente, até atingir uma cindição de parada.
    No caso de java, um método chamaa si mesmo, passando para si objetos primitivos. cadas chamad agera uma nova
    entrada na pilha de execução, e alguns dados podem ser disponibilizados em um escopo global ou local, atraves de
    parametros em um escopo global ou local

    Recursividade tem um papel importante em programção funcional, facilitando que evitemos estados mutáveis e
    mantenhamos nosso programa mais declarativo, e menos imperativo
     */
        System.out.println(fatorial(5));
        System.out.println(fatorialA(50));//valores muito alto retorna erro pois o java nao tem suporte para tailcall
     /*
     Memoization: tecnica de otimizacao que consiste no cache do resultado de uma funcao, baseado nos parametros de
     entrada. com isso nas seguintes execuções conseguimos ter uma resposta mais KATCHAU(rapida como relampago marquinhos)
      */
        long I = System.nanoTime();
        System.out.println(fatorialKATCHAUMemoization(15));
        long F = System.nanoTime();
        System.out.println("Fatorial 1 " + (F-I));

        I = System.nanoTime();
        System.out.println(fatorialKATCHAUMemoization(15));
        F = System.nanoTime();
        System.out.println("Fatorial 2 " + (F-I));


    }
    //Relembrando oq é fatorial
    //É ir multiplicando de forma decrescente até o 1
    //Exemplo  de 5 fatorial
    //5 * 4 * 3 * 2 * 1 = 120
    public static int fatorial(int numero){
        if (numero == 1) return numero;
        else return numero * fatorial((numero -1));
    }

    public static double fatorialA(double valor){
        return fatorialComTailCall(valor, 1);
    }

    public static double fatorialComTailCall(double valor, double numero){
        if(valor == 0)return numero;
        return fatorialComTailCall(valor - 1, numero*valor);
    }

    public static Integer fatorialKATCHAUMemoization(Integer value){
        if(value == 1) return value;
        else{
            if(MAPA_FATORIAL.containsKey(value)) return MAPA_FATORIAL.get(value);
            else{
                Integer resultado = value * fatorialKATCHAUMemoization(value -1);
                MAPA_FATORIAL.put(value, resultado);
                return resultado;
            }
        }
    }
}

//@FunctionalInterface //opcional pois o compilador consegue interpratar que é uma interface quando tem apenas um metodo abstrato
interface Funcao {
    String gerar(String valor);
    //se tiver mais de um metodo abstrato não podera usar lambda
    //mas se tiver um metodo default pode usar a lambda suave
}

interface Funcao1 {
    void gerar(String valor);
    //se tiver mais de um metodo abstrato não podera usar lambda
    //mas se tiver um metodo default pode usar a lambda suave
}


