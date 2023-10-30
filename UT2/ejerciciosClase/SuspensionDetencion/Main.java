import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HiloImprimir hiloImprimir = new HiloImprimir();
        hiloImprimir.start();
        Scanner sc = new Scanner(System.in);
        char opcion = ' ';
        

        while (hiloImprimir.isAlive()) {
            opcion = sc.next().charAt(0);
            switch (opcion) {
                case 'i':
                    hiloImprimir.reactivate();
                    break;

                case 's':
                    hiloImprimir.suspender();
                    break;

                case 'q':
                    hiloImprimir.parar();
                    break;

                case 'r':
                    hiloImprimir.reactivate();
                    break;
            }
        }
    }
}
