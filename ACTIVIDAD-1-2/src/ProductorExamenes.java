
import java.time.LocalDateTime;

public class ProductorExamenes implements Runnable {
	private BufferExamenes buffer;
	private static int numeroExamen = 0;
	private Thread hilo;
	
	public ProductorExamenes(BufferExamenes buffer) {
		
		numeroExamen++;// incrementa el contador de ex�menes
		hilo = new Thread(this, "E" + numeroExamen);// construye el hilo
		this.buffer = buffer;// establece el valor de la propiedad buffer
		hilo.start();// inicia el hilo
		
		}

	@Override
	public void run() {
		int aa = LocalDateTime.now().getYear();
		
			String codigo = this.hilo.getName() + "-" +aa;//genera c�digo de examen

			buffer.fabricarNuevoExamen(codigo);//a�ade nuevo c�digo al buffer de ex�menes

			System.out.println("Producido examen "+codigo);//mensaje informando
		
	}	
}
