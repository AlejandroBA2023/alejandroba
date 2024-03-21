import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LeerQuijote{
    public static void main(String[] args) {
        char caracter = args[0].charAt(0); // recupera el primer caracter de los argumentos
        File quijote = new File("el_quijote.txt"); //abre el fichero donde se encuentra el quijote

        leerQuijote(quijote, caracter);
    }

    /**
     * Esta funcion se encarga de coger el quijote, leerlo caracter por caracter y compararlo con el caracter que le hemos
     * pasado, si coincide, aumenta el contador. Al terminar, lo muestra por la salida estandar
     */
    public static void leerQuijote(File quijote, char letra){
        try (FileReader fr = new FileReader(quijote)) {
            int content;
            int total = 0;

            while ((content = fr.read()) != -1) {
                if ((char) content == letra){
                    total++;
                }
            }

            System.out.println("carácter " + letra + " => " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}