
class Hilos{
	public static void main(String[] args){
		System.out.println("hola mundo");
		Saluda saluda = new Saluda( 2, 3);
		Thread h = new Thread(saluda);
		h.start();
		try{h.join();} catch(Exception e){}
		//try {Thread.sleep(100);} catch(Exception e){}
		System.out.println(saluda.suma());
	}
}
