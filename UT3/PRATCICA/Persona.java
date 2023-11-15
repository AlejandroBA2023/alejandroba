import java.io.Serializable;

/**
 * class Persona
 * 
 * Guarda los datos de una persona
 * Debe ser serializable debido a que hay que serializarlo para enviarlo mediante los ObjectStreams
 * 
 */
public class Persona implements Serializable {
    private String name;
    private int age;

    // Constructor
    public Persona(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Persona(){

    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge(){
        return age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }
}
