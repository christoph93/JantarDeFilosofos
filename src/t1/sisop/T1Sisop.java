/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1.sisop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 * @author 12104806
 */
public class T1Sisop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // int n = Integer.parseInt(args[0]);
        int n = 5;
        int tempo = 5;
        boolean run = true;
        
        
        long tempoMili = tempo * 1000; 

        ExecutorService executor = Executors.newFixedThreadPool(n);

        //cria os garfos e popula o array de garfos
        Semaphore[] garfos = new Semaphore[n];
        for (int i = 0; i < garfos.length; i++) {
            garfos[i] = new Semaphore(1);
        }

        //cria os filósofos e popula o array de filosfos        
        Filosofo[] filosofos = new Filosofo[n];

        for (int i = 0; i < filosofos.length; i++) {
            if (i == filosofos.length - 1) {
                filosofos[i] = new Filosofo(garfos[i], garfos[0], i);
            } else {
                filosofos[i] = new Filosofo(garfos[i], garfos[i + 1], i);
            }
        }

        //executa as threads
        for (Filosofo f : filosofos) {
            executor.execute(f);
        }
        executor.shutdown();
        
        long t0 = System.currentTimeMillis();
        
        while(System.currentTimeMillis() - t0 < tempoMili){
            
        }
        
        System.out.println("Parando");
        
        for (Filosofo f : filosofos) {
            f.stop();
        }
        
        
        for (Filosofo f : filosofos) {
            f.setContadores();
            System.out.println(f.getEstatisitcas().toString());
        }
        

    }

}
