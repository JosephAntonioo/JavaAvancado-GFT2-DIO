package com.processamentoAssincronoEParalelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main2 {
    private static final ExecutorService pessoasParaExecutarAtividade = Executors.newFixedThreadPool(3);
    public static void main(String[] args) throws InterruptedException {
        Casa casa = new Casa(new Quarto());
        List<Future<String>> futuros =
                new CopyOnWriteArrayList<>(casa.obterAfazeresDaCasa().stream()
                        .map(atividade -> pessoasParaExecutarAtividade.submit(() -> {
                            try{
                                return atividade.realizar();
                            } catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            return null;
                        })
                    ).collect(Collectors.toList()));

        while(true){
            int numeroAtividadesNaoFinalizadas = 0;
            for(Future<?> futuro : futuros) {
                if (futuro.isDone()) {
                    try{
                        System.out.println("Parabens voce terminou de " + futuro.get());
                        futuros.remove(futuro);
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    numeroAtividadesNaoFinalizadas++;
                }
            }
            if(futuros.stream().allMatch(Future::isDone)) {
                break;
            }

            System.out.println("Numero de atvs não finalizdas: " + numeroAtividadesNaoFinalizadas);
            Thread.sleep(500);
        }
        pessoasParaExecutarAtividade.shutdown();
    }
}

class Casa{
    private List<Comodo> comodos;

    Casa(Comodo... comodos) { this.comodos = Arrays.asList(comodos);}

    List<Atividade>  obterAfazeresDaCasa(){
        return this.comodos.stream().map(Comodo::obterAfazersDoComodo)
                .reduce(new ArrayList<Atividade>(), (pivo, atividades) ->{
                    pivo.addAll(atividades);
                    return pivo;
                });
    }
}

interface Atividade{
    String realizar() throws InterruptedException;
}

abstract class Comodo{
    abstract List<Atividade> obterAfazersDoComodo();
}

class Quarto extends Comodo{
    @Override
    List<Atividade> obterAfazersDoComodo() {
        return Arrays.asList(
                this::getArrumarACama,
                this::varrerQuarto,
                this::arrumarGuardaRoupa
        );
    }
    private String arrumarGuardaRoupa() throws InterruptedException {
        Thread.sleep(500);
        String arrumar_o_guarda_roupa = "arrumar o guarda roupa";
        System.out.println(arrumar_o_guarda_roupa);
        return arrumar_o_guarda_roupa;
    }
    private String varrerQuarto() throws InterruptedException {
        Thread.sleep(600);
        String varrer_o_quarto = "varrer o quarto";
        System.out.println(varrer_o_quarto);
        return varrer_o_quarto;
    }
    private String getArrumarACama() throws InterruptedException {
        Thread.sleep(700);
        String arrumar_a_cama = "Arrumar a cama";
        System.out.println(arrumar_a_cama);
        return arrumar_a_cama;
    }
}


