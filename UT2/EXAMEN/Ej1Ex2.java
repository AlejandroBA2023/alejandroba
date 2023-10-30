import java.util.ArrayList;
import java.util.List;


public class Ej1Ex2 {
    public static void main(String[] args) {
        // Ejercicio 1
        Runnable ej1Ex2 = new HiloEj1(1, 30, 20);
        ej1Ex2.run();
    }
}

class HiloEj1 implements Runnable{
    private List<Integer> lista;
    private final int MIN;
    private final int MAX;
    private final int DUREZA;

    public HiloEj1(int MIN, int MAX, int DUREZA){
        this.MIN = MIN;
        this.MAX = MAX;
        this.DUREZA = DUREZA;
    }

    @Override
    public void run() {
        lista = new ArrayList<>();
        for(int i = MIN; i <= MAX; i++){
            int r = 0;

            for(int j = 1; j < DUREZA; j++){
                r = i * j;
                r = r / j;
            }
        lista.add(r);
        }
        System.out.println(lista.size());
    }
    
}
