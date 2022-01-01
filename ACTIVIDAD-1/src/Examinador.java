import java.util.Random;

public class Examinador implements Runnable {
	private Thread hilo;
	BufferExamenes buffer;
	
	public Thread getHilo() {
		return hilo;
	}

	public Examinador(String alumno, BufferExamenes generador) {
		
		hilo = new Thread(this, alumno);// construye el hilo
		this.buffer = generador;// establece el valor de la propiedad buffer 
		hilo.start();// inicia el hilo

	}
	
	@Override
	public synchronized void run() {
		String codigoExamen = this.buffer.consumirExamen();
		
		if (codigoExamen != null) {
			
			//simulo examen de 10 preguntas, con respuestas al azar
			for (int i = 1; i < 11; i++) {
				
				int min = 1;//rango de azar minimo 1
				int max = 5;//rango de azar maximo 5
				Random random = new Random();//variable numero al azar
				int azar = random.nextInt(max) + min;//establezco rango [1-5]
				
				if (azar == 1) {//si sale 1 es A
					System.out.println(codigoExamen + ";" + hilo.getName() + ";" + " Pregunta " + i + ";A");

				} else if (azar == 2) {//si sale 2 es B
					System.out.println(codigoExamen + ";" + hilo.getName() + ";" + " Pregunta " + i + ";B");

				} else if (azar == 3) {//si sale 3 es C
					System.out.println(codigoExamen + ";" + hilo.getName() + ";" + " Pregunta " + i + ";C");
					
				} else if (azar == 4) {//si sale 4 es D
					System.out.println(codigoExamen + ";" + hilo.getName() + ";" + " Pregunta " + i + ";D");

				} else {//si no, sale 5 y es - (sin respuesta)

					System.out.println(codigoExamen + ";" + hilo.getName() + ";" + " Pregunta " + i + ";-");
				}

			}

		} else {
			System.out.println("Agotado tiempo de espera y " +
								"no hay más exámenes");
		}
	}
}
