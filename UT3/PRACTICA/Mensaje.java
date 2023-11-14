import java.io.Serializable;

public class Mensaje implements Serializable {
    private String command;
    private Object message;

    public Mensaje(String command, String message) {
        this.command = command;
        this.message = new String(message);
    }

    public Mensaje(String command, Persona persona) {
        this.command = command;
        this.message = persona;
    }

    public boolean isPersona() {
        if (message.getClass().getName() == new String().getClass().getName()) {
            return false;
        } else
            return true;
    }

    public Object getMessage() {
        return message;
    }

    public String getCommand() {
        return command;
    }
}
