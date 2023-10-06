public class Main{
    public static void main(String[] args) {
        try {
            String suma;
            String nombre = "Alejandro Bermejo Álvarez";

            // Primer proceso
            ProcessBuilder pb1 = new ProcessBuilder("/bin/bash", "proceso1.sh");
            Process p1 = pb1.start();

            // Segundo proceso
            ProcessBuilder pb2 = new ProcessBuilder("java", "Proceso2", "1", "2");
            Process p2 = pb2.start();
            p2.waitFor(); // Espero a que el programa termine

            suma = p2.inputReader().readLine(); // Capturo la salida del programa

            // Imprimo el resultado
            System.out.println("El resultado es: " + suma + "\nMi nombre completo es: " + nombre);


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}