import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ej2Ex3 {
    public static void main(String[] args) {
        // Hilo 1: captura de teclado
        Tuberia tuberia = new Tuberia();
        Productor productor = new Productor(tuberia);
        Consumidor consumidor = new Consumidor(tuberia);
        productor.run();
        consumidor.run();
    }
}

class Productor extends Thread {
      private Tuberia tuberia;
      Scanner sc = new Scanner(System.in);
      public Productor( Tuberia t ) {
        // Mantiene una copia propia del objeto compartido
        tuberia = t;
      }

      public void run() {
        String c;

        // Mete 10 letras en la tubería
        while (true) {
            {
            c = sc.next();
            tuberia.lanzar( c );
            try {
                sleep( (int)( Math.random() * 100 ) );
            } catch( InterruptedException e ) { ; }
            }
      }
    }
}

class Tuberia {
      private List<String> buffer = new ArrayList<>();
      
      public synchronized List recoger() {
        notify();

        return buffer ;
      }

      // Método para añadir letras al buffer
      public synchronized void lanzar( String palabra ) {
        // Añade una letra en el primer lugar disponible
        buffer.add(palabra);

        notify();
      }
    }

class Consumidor extends Thread {
      private Tuberia tuberia;

      public Consumidor( Tuberia t ) {
        // Mantiene una copia propia del objeto compartido
        tuberia = t;
      }

      public void run() {
        List lista;

        // Consume 10 letras de la tubería
        while(true) {
            lista = tuberia.recoger();
            // Imprime las letras retiradas
            System.out.println( lista.toString() );
            }
      }
    }