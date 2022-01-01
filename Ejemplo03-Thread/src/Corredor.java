/* UNA ALTERNATIVA (HERENCIA)
public class Corredor extends Thread {
 
	public Corredor(String nombre, int prioridad) {
		
		super(nombre);
		this.setPriority(prioridad);
		
		this.start(); // crea el hilo de ejecuci�n y llama al m�todo run()
		
	}
 
	@Override
	public void run() {
		for (int i=1; i<=10; i++) {
			System.out.println(this.getName() + " va por el kil�metro " + i);
		}
		 
		System.out.println(this.getName() + " ha llegado a la meta");	
	}
}
*/

//OTRA ALTERNATIVA (INTERFAZ)
public class Corredor implements Runnable {
	private Thread hilo;
 
	public Corredor(String nombre, int prioridad) {
		hilo = new Thread(this, nombre);
		hilo.setPriority(prioridad);
		hilo.start();
	}
 
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println(hilo.getName() + " va por el kil�metro " + i);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(hilo.getName() + " ha llegado a la meta");
	}
}


