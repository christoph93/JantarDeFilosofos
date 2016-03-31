/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1.sisop;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 12104806
 */
public class Filosofo implements Runnable {

    private Estado estado;
    private Semaphore g1, g2;
    private int id;
    
    private int contadorTENTADO_COMER, contadorPENSANDO, contadorCOMENDO;

    public Filosofo(Semaphore g1, Semaphore g2, int id, boolean run) {
        this.estado = Estado.TENTANDO_COMER;
        this.g1 = g1;
        this.g2 = g2;
        this.id = id;
        contadorTENTADO_COMER = 0;
        contadorCOMENDO = 0;
        contadorPENSANDO = 0;
    }

    @Override
    public void run() {
        try {
            System.out.println("Iniciando filosofo " + id);
            tentarComer();
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tentarComer() throws InterruptedException {        
        double t;
        while (this.estado == Estado.TENTANDO_COMER) {            
            //tenta pegar o primeiro garfo
            if (g1.tryAcquire()) {
                //se pegou os primeiro, tenta pegar o segundo
                if (!g2.tryAcquire()) {
                    //se não conseguiu pegar o segundo, larga o primeiro e tenta de novo
                    g1.release();
                    t = Math.random();
                    Thread.sleep((long) (t * 100));
                } else {
                    //já tem o primeio e conseguiu pegar o segundo
                    comer();
                }
            } else {
                //não conseguiu pegar o primeiro
                t = Math.random();
                Thread.sleep((long) (t * 100));
            }

        }
    }

    public void pensar() {        
        try {
            this.estado = Estado.PENSANDO;
            contadorPENSANDO++;
            Thread.sleep(500);
            this.estado = Estado.TENTANDO_COMER;
            contadorTENTADO_COMER++;
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comer() {       
        try {
            this.estado = Estado.COMENDO;
            contadorCOMENDO++;
            Thread.sleep(100);
            System.out.println("Filósofo " + id + " comeu! Liberando garfos.");
            g1.release();
            g2.release();
            pensar();
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int[] getContadores(){
        int[] conts = {contadorPENSANDO, contadorTENTADO_COMER, contadorCOMENDO};
        return conts;
    }

    public String toString(){
        return "Filósofo " + id + " " + this.estado + "\n       g1: " +
                g1.availablePermits()+ " g2: " + g2.availablePermits();
    }
    
}
