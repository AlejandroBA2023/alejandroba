public class Main{
        public static void main(String[] args){
                try {
                        //ProcessBuilder pb = new ProcessBuilder("/vagrant/procesos1/esperarNsegundos.sh", "4");
                        //Process p = pb.start();
                        Process p = Runtime.getRuntime().exec("/bin/kate");
                        while (p.isAlive()) {
                            letraAleatoria();
                        }
                        p.waitFor();
                        p.destroy();
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

        private static void letraAleatoria() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 1;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
    }

}