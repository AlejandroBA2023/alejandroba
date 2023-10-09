public class Saluda implements Runnable {
    private String nombre;
    private int a;
    private int b;
    private int suma;

    // Constructor
    Saluda(String nombre){ this.nombre = nombre; }
    Saluda(int a, int b){ this.a = a; this.b = b;}
    // Ejecutor
    @Override
    public void run(){
	  this.suma =  a + b;
	  for( int i = 0 ; i < 10 ; i++ ){
	    System.out.println("Soy un hilo independiente de la clase " + Thread.currentThread().getName()
			       + " a " + nombre);
	  }
    } // método run()

    public int suma(){
	return this.suma;
    }
} // clase Saluda
