import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws Exception {
		Registry registry;
		ITestRMI test = null;
		Scanner lector = new Scanner(System.in);
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1", 5051); // obten el objeto remoto y asígnalo a la
																		// referencia test declarama más arriba
			System.out.println("Hemos obtenido el registro");
			test = (ITestRMI) registry.lookup("miTest");
			System.out.println("Hemos obtenido el objeto remoto");
			System.out.println(); // Retorno de carro.
			String opcion;
			do {
				escribirMenu();
				opcion = lector.nextLine().toUpperCase();
				switch (opcion) {

				case "1":
					System.out.println("Practicar con una pregunta al azar");
					Pregunta p = test.getPregunta();
					String[] respuestas = p.getRespuestas();
					System.out.println(p.getPregunta());
					System.out.println("1. " + respuestas[0]);
					System.out.println("2. " + respuestas[1]);
					System.out.println("3. " + respuestas[2]);
					System.out.println("4. " + respuestas[3]);
					System.out.println("¿Cuál es la respuesta correcta?");
					int correcta = lector.nextInt();
					lector.nextLine(); // Limpiar buffer
					// Completa para evaluar si la respuesta del usuario es correcta o incorrecta
					if (correcta == p.getCorrecta()) {
						System.out.println("La respuesta es correcta! :)");
					} else {
						System.out.println("La respuesta es incorrecta! :(");
					}
					break;

				case "2":
					System.out.println("Practicar con 5 preguntas al azar");
					// Completa para examinar al usuario y obtener la calificación
					// Se utilizará un test con 5 preguntas al azar
					ArrayList<Pregunta> preguntas5 = test.get5Azar();
					int nota = 0;
					for (Pregunta pregunta : preguntas5) {
						respuestas = pregunta.getRespuestas();
						System.out.println(pregunta.getPregunta());
						System.out.println("1. " + respuestas[0]);
						System.out.println("2. " + respuestas[1]);
						System.out.println("3. " + respuestas[2]);
						System.out.println("4. " + respuestas[3]);
						System.out.println("¿Cuál es la respuesta correcta?");
						correcta = lector.nextInt();
						lector.nextLine();
						if (correcta == pregunta.getCorrecta()) {
							System.out.println("La respuesta es correcta! :)");
							nota = nota + 2;
						} else {
							System.out.println("La respuesta es incorrecta! :(");
						}
					}
					System.out.println("\nTest finalizado, tu puntuación final es de: " + nota + " puntos");
					break;

				case "3":
					System.out.println("Hacer test completo");
					// Completa para examinar al usuario y obtener la calificación
					// Se utilizará el test completo
					ArrayList<Pregunta> preguntas = test.getPreguntas();
					nota = 0;
					for (Pregunta pregunta : preguntas) {
						respuestas = pregunta.getRespuestas();
						System.out.println(pregunta.getPregunta());
						System.out.println("1. " + respuestas[0]);
						System.out.println("2. " + respuestas[1]);
						System.out.println("3. " + respuestas[2]);
						System.out.println("4. " + respuestas[3]);
						System.out.println("¿Cuál es la respuesta correcta?");
						correcta = lector.nextInt();
						lector.nextLine();
						if (correcta == pregunta.getCorrecta()) {
							System.out.println("La respuesta es correcta! :)");
							nota = nota + 1;
						} else {
							System.out.println("La respuesta es incorrecta! :(");
						}
					}
					System.out.println("\nTest finalizado, tu puntuación final es de: " + nota + " puntos");
					break;

				case "4":
					System.out.println("Gracias por utilizar nuestro servicio");
					break;
				default:
					System.out.println("Opción incorrecta");
				}
			} while (!opcion.equals("4"));
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}
		lector.close();
	}

	private static void escribirMenu() {
		System.out.println();
		System.out.println("Hacer Test online");
		System.out.println("--------------------------");
		System.out.println("1 = Practicar con una pregunta al azar");
		System.out.println("2 = Practicar con 5 preguntas al azar");
		System.out.println("3 = Hacer test completo");
		System.out.println("4 = Finalizar programa");
		System.out.println("--------------------------");
		System.out.println("¿Qué opción eliges?");
	}
}
