package UT2.ejerciciosClase.Ejer1;

import java.util.Random;

public class HiloImprimir extends Thread{
    private volatile boolean keep, stop;

    @Override
    public void run() {
        keep = false;
        stop = false;

        while(!stop){
            while (keep) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                letraAleatoria();
            }
        }
    }

    public synchronized void suspender(){
        keep = false;
    }

    public synchronized void empezar(){
        keep = true;
    }

    public synchronized void apagar(){
        stop = true;
    }

    private static void letraAleatoria() {
        int caracteres = (int) (Math.random() *20) +2;
        
            int codigoASCII = (int) Math.floor(Math.random() * (122 - 97) +97);
            System.out.println("" + (char) codigoASCII);
        
    }
    
}
