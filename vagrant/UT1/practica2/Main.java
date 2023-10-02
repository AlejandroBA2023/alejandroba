import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        try {
            ExecutorService s = Executors.newFixedThreadPool(1);

            Future<Integer[]> listaNumeros = s.submit(new ListaNumeros());

            imprimirNumeros(listaNumeros, 20);
            s.shutdown();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void imprimirNumeros(Future<Integer[]> listaNumeros, int limite) throws ExecutionException, InterruptedException {
        for (int i = listaNumeros.get().length - limite; i < listaNumeros.get().length; i++){
            System.out.println(listaNumeros.get()[i]);
        }
    }

}
