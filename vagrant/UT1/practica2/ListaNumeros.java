import java.util.concurrent.Callable;

  
/** ListaNumeros
 * Proceso que crea y devuelve una lista de números del 0 al 9999
 * Tiene implementado Callable para que pueda devolver la lista en su salida
*/
public class ListaNumeros implements Callable<Integer[]> {

    /**call
     * Este método es el principal de la función, ya que es el que incia cuando la llamamos desde el programa principal
     * Override se pone para indicar que está obligado a sobrescribirse; si pusieramos mal la función, no daría un error al compilar 
     */
    @Override
    public Integer[] call() {
        Integer[] numeros = new Integer[10000];

        for (int i = 0; i < numeros.length; i++){
            numeros[i] = i;
        }

        return numeros;
    }
}
