public class Proceso2 {
    public static void main(String[] args) {
        // Recoge dos numeros mediante args
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);

        // Realiza e imprime la suma
        int suma = num1 + num2;
        System.out.println(suma);
    }
}