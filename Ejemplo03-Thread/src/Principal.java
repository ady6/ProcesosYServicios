
public class Principal {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Hola Mundo"); //esto se ejecuta en el contexto de un hilo
		Thread hilo = Thread.currentThread(); // capturando el hilo principal
		System.out.println(hilo.toString()); // mostramos información del hilo
		
		Thread.sleep(1000); // hacemos un retardo de un segundo
		
		hilo.setPriority(3); // cambiamos la prioridad del hilo
		hilo.setName("HiloPrincipal"); // cambiamos el nombre del hilo
		System.out.println(hilo.toString()); // mostramos información del hilo
		
		System.out.println(hilo.getThreadGroup().getName()); // nombre del grupo al que pertenece el hilo
		
		new Corredor("Corredor1",5);
		new Corredor("Corredor2",4);
		new Corredor("Corredor3",5);


	}

}
