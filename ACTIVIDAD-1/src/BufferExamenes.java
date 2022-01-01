
import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {
	private Queue<String> colaExamenes;
	
	public BufferExamenes() {
		colaExamenes = new LinkedList<String>();
	}
	
	public synchronized void fabricarNuevoExamen(String codigo) {// fabricar nuevo examen
		
		colaExamenes.add(codigo);// paso el codigo como argumento a la cola

		notify();// libera el hilo que está intentando consumir un nuevo examen

	}

	public synchronized String consumirExamen() {// suministrar un examen
		
		while (colaExamenes.isEmpty()) {
			try {
				wait(1000);// pausa hasta que se haya fabricado algún examen
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		if (!colaExamenes.isEmpty()) {
			String frase = colaExamenes.remove();// saca un examen
			return frase;// lo entrega como retorno de la función
		} else {
			return null;
		}
	}
}
