public class Main {
    public static void main(String[] args) {
            try {
                System.out.println("a<sdas");
                Process p = Runtime.getRuntime().exec("/bin/bash script.sh");
                p.waitFor();
                System.out.println("a<sdas");
            }catch (Exception e){
                System.err.println(e.getMessage());
            }
    }

}