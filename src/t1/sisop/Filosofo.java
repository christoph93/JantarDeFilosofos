/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1.sisop;

import java.util.concurrent.Semaphore;

/**
 *
 * @author 12104806
 */
public class Filosofo {

    private Estado e;
    private Semaphore g1, g2;

    public Filosofo(Semaphore g1, Semaphore g2) {
        this.e = Estado.PENSANDO;
        this.g1 = g1;
        this.g2 = g2;
    }

    public void tentarComer() throws InterruptedException {
        double t;
        while (true) {
            //pegou primeiro e não pegou segundo
            if (g1.tryAcquire()) {
                if (!g2.tryAcquire()) {
                    g1.release();
                    t = Math.random();
                    Thread.sleep((long) (t * 100));
                } else {
                    comer();
                }
            } else {
                t = Math.random();
                Thread.sleep((long) (t * 100));
            }

        }
    }

    public void pensar() {

    }

    public void comer() {

    }

}
