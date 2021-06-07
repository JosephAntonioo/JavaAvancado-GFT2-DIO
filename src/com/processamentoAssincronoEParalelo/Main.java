package com.processamentoAssincronoEParalelo;

public class Main {
    /*
    Conceitos e anotações
    Threads
        Um pequeno programa que trabalho como um subsistema, sendo uma forma de um processo se autodividir em duas ou
        mais tarefas. Essas tarefas multiplas podem ser executadas simultaneamente para rodas mais rápido do que um
        programa em um unico bloco ou praticamente juntas
    Processamento Sincrono
        São todos os processamentos que ocorrem em sequencia(sincronia). Os processos são executados em fila. É preciso
        que um processo termine para que outro processo seja executado. Ex:imagine vc lavando louça e de repente vc se
        lembra que tem que fazer uma ligação. A ligação só poderá ser realizada quando o processo lavar louça terminar.
    Processamento Assincrono
        Quando dois ou mais processos são realizados ao mesmo tempo, é dado o nome de processamento assíncrono. Os
        processos são realizados simultaneamente sem que um processo necessite que outro termine para ser executado.
        Exemplo: lavar louça e falar ao telefone ao mesmo tempo. Se voce nao sabe como fazer isso, preda o telefone,
        entre a cabeça e o ombro e tranha as mão livres para lavar louça
     */
    public static void main(String[] args){
        GerarPDF iniciarGeradorPDF = new GerarPDF();
        BarraDeCarregamento iniciarBarraCarregamento = new BarraDeCarregamento(iniciarGeradorPDF);
        
        iniciarGeradorPDF.start();
        iniciarBarraCarregamento.start();
        
    }
}

class GerarPDF extends Thread{
    @Override
    public void run(){
        try {
            System.out.println("Iniciar geração pdf");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Gerou PDF");
    }
}

class BarraDeCarregamento extends Thread{
    private Thread iniciarGeradorPDF;

    public BarraDeCarregamento(Thread iniciarGeradorPDF) {
        this.iniciarGeradorPDF = iniciarGeradorPDF;
    }

    @Override
    public void run(){
        while (true){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            if(!iniciarGeradorPDF.isAlive()){
                break;
            }
            System.out.println("Loading...");
        }

    }
}



