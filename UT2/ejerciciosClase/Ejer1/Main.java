package UT2.ejerciciosClase.Ejer1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HiloImprimir hiloImprimir = new HiloImprimir();
        hiloImprimir.start();

        Scanner sc = new Scanner(System.in);
        boolean vivo = true;

        while (vivo) {
            switch (sc.next()) {
                case "i":
                    hiloImprimir.empezar();
                    break;
                case "q":
                    hiloImprimir.apagar();;
                    vivo = false;
                    break;
                case "s":
                    hiloImprimir.suspender();;
                    break;
                
                
                default:
                    break;
            }
        }
    }


}
