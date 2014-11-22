/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabyalex
 */
public class HiloPrioridad1 extends Thread{
    private int c=0;
    private boolean stopHilo=false;
    public int getContador(){
        return c;
    }
    public void pararHilo(){
        stopHilo =true;
    }
    public void run(){
        while (!stopHilo) c++;
    }
}
