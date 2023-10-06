/** Librerias */
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/** Main
 * La clase Main es la primera en ser ejecutada; es el programa principal.
 * Se encargará de ejecutar un proceso llamado ListaNumeros que nos devolverá una lista de números
 * 
 */
public class Main {
    public static void main(String[] args) {
        try {

            // Utilizamos ExecutorService para crear un nuevo hilo. Con ese hilo, ejecutamos y guardamos en una variable Future el proceso ListaNumeros
            ExecutorService s = Executors.newFixedThreadPool(1);
            Future<Integer[]> listaNumeros = s.submit(new ListaNumeros());

            // Llamamos a la función imprimirNumeros y cerramos el hilo. 
            imprimirNumeros(listaNumeros, 20);
            s.shutdown();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    /**imprimirNumeros
     * La función imprimirNumeros recibe una lista y un limite.
     * El límite se encarga de decir cuantos números finales se van a imprimir
     */
    private static void imprimirNumeros(Future<Integer[]> listaNumeros, int limite) throws ExecutionException, InterruptedException {
        for (int i = listaNumeros.get().length - limite; i < listaNumeros.get().length; i++){
            System.out.println(listaNumeros.get()[i]);
        }
    }

}
