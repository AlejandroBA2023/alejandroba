import java.util.Random;

public class Main{
        public static void main(String[] args){
                try {
                        // DECLARACION Y EJECUCION DEL PROCESO
                        Process p = Runtime.getRuntime().exec("/bin/bash esperarNsegundos.sh " + args[0]);

                        // LLAMA A LA FUNCION LETRA ALEATORIA MIENTRAS EL PROCESO ESTA VIVO
                        while (p.isAlive()) {
                            letraAleatoria();
                        }

                        // FINALIZACION DEL PROCESO
                        p.waitFor();
                        p.destroy();

                } catch (Exception e) {
                        e.printStackTrace();
                }

        }



         //* FUNCION RECUPERADA Y MODIFICADA DE "https://www.baeldung.com/java-random-string"
        private static void letraAleatoria() {
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 1;
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            System.out.print(generatedString);
        }

}