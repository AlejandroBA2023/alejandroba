import java.io.Serializable;

/**
 * class Mensajae
 * 
 * Guarda los datos de un posible mensaje
 * Debe ser serializable debido a que hay que serializarlo para enviarlo mediante los ObjectStreams
 */
public class Mensaje implements Serializable {
    // Variables
    private String command;
    private Object message;

    // Contructores

    // Constructor con un mensaje String
    public Mensaje(String command, String message) {
        this.command = command;
        this.message = new String(message);
    }

    // Constructor con un mensaje Persona
    public Mensaje(String command, Persona persona) {
        this.command = command;
        this.message = persona;
    }

    // Getters

    public Object getMessage() {
        return message;
    }

    public String getCommand() {
        return command;
    }
}
