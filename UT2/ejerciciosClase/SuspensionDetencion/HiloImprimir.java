class HiloImprimir extends Thread{

volatile boolean execute = true;
volatile boolean suspend = false;

@Override
public void run() {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    while(execute) { // Mientras execute sea True...
	    
	    if( !suspend ){ // Si suspend no es True...
            System.out.println(chars.charAt( (int)( Math.random() * 26 ) ));
            try{ 
                Thread.sleep(1000);
            } catch (Exception e){;}
        }
    }
    System.exit(0);
}


public synchronized void suspender() {
	suspend = true;
}

public synchronized void reactivate() {
	suspend = false;
}

public synchronized void parar() {
    execute = false;
}
}