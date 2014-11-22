/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabyalex
 */
public class EjemplohiloPrioridad2 extends Thread{

    public EjemplohiloPrioridad2(String nom) {
        this.setName(nom);
    }
    public void run(){
        System.out.println("Ejecutando ["+getName()+"]");
        for (int i=1;i<=5;i++)
            System.out.println("\t("+getName()+":" +i+")");
    }
    public static void main(String[] args) {
        EjemplohiloPrioridad2 h1 =new EjemplohiloPrioridad2("uno");
        EjemplohiloPrioridad2 h2 =new EjemplohiloPrioridad2("dos");
        EjemplohiloPrioridad2 h3 =new EjemplohiloPrioridad2("tres");
        EjemplohiloPrioridad2 h4 =new EjemplohiloPrioridad2("cuatro");
        EjemplohiloPrioridad2 h5 =new EjemplohiloPrioridad2("cinco");
        //asignar prioridad
        h1.setPriority(Thread.MIN_PRIORITY);
        h2.setPriority(3);
        h3.setPriority(Thread.NORM_PRIORITY);
        h4.setPriority(7);
        h5.setPriority(Thread.MAX_PRIORITY);
        //se ejecutan los hilos
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
    }
    
    
}
