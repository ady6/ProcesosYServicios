import java.util.LinkedList;
import java.util.Queue;

public class BufferFrases {
	
	private static String frases[] = {
		"El talento es importante, pero son las horas prácticas las que marcan la diferencia",
		"Piensa si donde vas ahora te acerca a donde quieres estar mañana",
		"Cuando tu mente está confundida tu corazón sabe la respuesta",
		"Lo que más mala suerte da es ser supersticioso",
		"La suerte es lo que sucede cuando la preparación se encuentra con la oportunidad",
		"La suerte es tenacidad en conseguir tu propósito",
		"Suerte a menudo puede significar simplemente aprovechar una situación en el momento adecuado",
		"Una acción vale más que todo un mundo de promesas",
		"Si buscas resultados distintos, no hagas siempre lo mismo",
		"Una piña y un Mario Bros os acompañan en vuestras andanzas"
	};
	
	private Queue<String> cola;

	public BufferFrases() {
		cola = new LinkedList<String>();
	}
	
	
	
	public synchronized void poner() {
		int azar = (int) (Math.random() * 10);
		cola.add(azar + " - " + frases[azar]);
		notify();
		//ponemos el semáforo en verde
		//liberando la tarea detenida por wait
	}

	public synchronized String sacar() {
		while (cola.isEmpty()) {
			try {
				wait(1000);
				//ponemos el semáforo en rojo
				//paramos el hilo o tarea
				//parada max de 1000s o hasta q semáforo sea verde
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		if (!cola.isEmpty()) {
			String frase = cola.remove();
			return frase;
		} else {
			return null;
		}
	}

}
