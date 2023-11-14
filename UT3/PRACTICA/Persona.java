import java.io.Serializable;

public class Persona implements Serializable {
    private String name;

    public Persona(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
