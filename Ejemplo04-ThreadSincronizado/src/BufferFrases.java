import java.util.LinkedList;
import java.util.Queue;

public class BufferFrases {
	
	private static String frases[] = {
		"El talento es importante, pero son las horas pr�cticas las que marcan la diferencia",
		"Piensa si donde vas ahora te acerca a donde quieres estar ma�ana",
		"Cuando tu mente est� confundida tu coraz�n sabe la respuesta",
		"Lo que m�s mala suerte da es ser supersticioso",
		"La suerte es lo que sucede cuando la preparaci�n se encuentra con la oportunidad",
		"La suerte es tenacidad en conseguir tu prop�sito",
		"Suerte a menudo puede significar simplemente aprovechar una situaci�n en el momento adecuado",
		"Una acci�n vale m�s que todo un mundo de promesas",
		"Si buscas resultados distintos, no hagas siempre lo mismo",
		"Una pi�a y un Mario Bros os acompa�an en vuestras andanzas"
	};
	
	private Queue<String> cola;

	public BufferFrases() {
		cola = new LinkedList<String>();
	}
	
	
	
	public synchronized void poner() {
		int azar = (int) (Math.random() * 10);
		cola.add(azar + " - " + frases[azar]);
		notify();
		//ponemos el sem�foro en verde
		//liberando la tarea detenida por wait
	}

	public synchronized String sacar() {
		while (cola.isEmpty()) {
			try {
				wait(1000);
				//ponemos el sem�foro en rojo
				//paramos el hilo o tarea
				//parada max de 1000s o hasta q sem�foro sea verde
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
