import java.util.ArrayList;
import java.util.List;

public class Prueba {
    public static void main(String[] args) {
        List<Mensaje> mensajes = new ArrayList<>();
        mensajes.add(new Mensaje("SMS", "Buenas tardes"));
        mensajes.add(new Mensaje("SMS", new Persona("Alejandro")));

        for (Mensaje mensaje : mensajes) {
            var msg = mensaje.getMessage();

            if (mensaje.isPersona()) {
                System.out.println("NUEVA PERSONA!!!!!\n");
                System.out.println(((Persona) msg).getName());
            } else {
                System.out.println(msg);
            }
        }

    }
}
