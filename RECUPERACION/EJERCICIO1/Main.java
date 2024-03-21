import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Programa principal.
 * Se encarga de recoger las entradas del usuario mediante un Scanner y aplicar las distintas opciones
 */
public class Main{
    public static void main(String[] args) {
        boolean fin = false;
        Scanner sc = new Scanner(System.in);
        String opcion;

        List<HiloGestionador> hilos = new ArrayList<>(); // Lista para los distintos hilos que ejecuntan los procesos (Para que sea asincrono)

        while (!fin){
            opcion = sc.next();

            switch (opcion) {
                // Si la opcion es :q, para todo directamente
                case ":q":
                    pararHilos(hilos);
                    fin = true;
                    break;


                // Si la opcion es :wq, cambia la salida estandar al fichero de texto y pone la variable fin a true para que termine el programa
                case ":wq":
                    try {
                        PrintStream out = new PrintStream(new FileOutputStream("resultados.txt"));
                        System.setOut(out);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return;
                    }

                    fin = true;
                    break;

                // Si no es un comando, inicia el hilo con lo escrito
                default:
                    hilos.add(new HiloGestionador(opcion));
                    new Thread(hilos.get(hilos.size() -1)).start();
            }
        }
    }

    /**
     * Llama al metodo parar() de cada hilo, que cierra el proceso abierto por el hilp y lo cierra
     * @param hilos
     */
    private static void pararHilos(List<HiloGestionador> hilos){
        for (HiloGestionador hilo : hilos){
            try {
                hilo.parar();
            } catch (Exception e){

            }
        }
    }
}


/**
 * Hilo para gestionar un proceso
 * Se inicializa con el constructor y ejecuta el proceso, capturando la salida para mostrarla por la salida estandar
 */
class HiloGestionador implements Runnable{

    private String opcion;
    volatile Process p1;

    public HiloGestionador(String opcion){
        this.opcion = opcion;
    }

    @Override
    public void run() {
        try {
            // Inicia el proceso
            ProcessBuilder pb = new ProcessBuilder("java", "LeerQuijote", opcion);
            p1 = pb.start();

            // Captura de la salida del proceso
            InputStream inputStream = p1.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Muestra la salida del proceso por la salida estandar
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo para parar el proceso y el hilo
     */
    public void parar(){
        if (p1 != null) {
            p1.destroy(); //Para el proceso
        }
        System.exit(0); //Termina el programa
    }
}