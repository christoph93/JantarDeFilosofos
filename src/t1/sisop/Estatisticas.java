/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package t1.sisop;

/**
 *
 * @author Christoph
 */
public class Estatisticas {
    
    private int idF;
    private int tentouComer, comeu, pensou;
    
    public Estatisticas(int id){
        this.idF = id;
        tentouComer = 0; comeu = 0; pensou = 0;
    }
    
    
    
    public void setContadores(int tentou, int comeu, int pensou){
        tentouComer = tentou;
        this.comeu = comeu;
        this.pensou = pensou;
    }    
    
    
    public String toString(){
        return "Filósofo " + idF + "\n  TENTANDO_COMER: " + tentouComer + "\n  COMER: " + comeu + "\n  PENSANDO: " + pensou;
    }
    
}
