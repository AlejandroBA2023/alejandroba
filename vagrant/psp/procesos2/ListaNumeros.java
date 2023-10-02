import java.util.concurrent.Callable;

public class ListaNumeros implements Callable<Integer[]> {

    @Override
    public Integer[] call() {
        Integer[] numeros = new Integer[10000];

        for (int i = 0; i < numeros.length; i++){
            numeros[i] = i;
        }

        return numeros;
    }
}
