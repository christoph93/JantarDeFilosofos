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
public class T1Sisop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Semaphore g1 = new Semaphore(1);
        Semaphore g2 = new Semaphore(1);
        Semaphore g3 = new Semaphore(1);
        Semaphore g4 = new Semaphore(1);
        
        Filosofo f1 = new Filosofo(g1,g2);
        Filosofo f2 = new Filosofo(g2,g3);
        Filosofo f3 = new Filosofo(g3,g4);
        Filosofo f4 = new Filosofo(g4,g1);
    }
    
}
