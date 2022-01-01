import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) {
		Registry registry;
		Scanner lector = new Scanner(System.in);
		try {
			registry = LocateRegistry.getRegistry("127.0.0.1", 5055);
			System.out.println("Hemos obtenido el registro");
			PersonasInterfaceRMI personas = (PersonasInterfaceRMI) registry.lookup("miMusica");
			System.out.println("Hemos obtenido el objeto remoto");
			System.out.println(); // Retorno de carro.
			String buscado;
			String opcion;
			do {
				escribirMenu();
				opcion = lector.nextLine().toUpperCase();
				switch (opcion) {
				case "1":
					System.out.println(personas.mostrarTodos());
					break;
				case "2":
					System.out.println(personas.personaAzar());
					break;
				case "3":
					System.out.println("Escribe Codigo de la persona: ");
					buscado = lector.nextLine();
					System.out.println(personas.buscarPorCodigo(buscado));
					break;
				case "4":
					System.out.println("Escribe DNI de la persona: ");
					buscado = lector.nextLine();
					System.out.println(personas.buscarPorDNI(buscado));
					break;
				case "5":
					System.out.println("Escribe nombre de la persona: ");
					buscado = lector.nextLine();
					System.out.println(personas.buscarPorNombre(buscado));
					break;
				case "6":
					System.out.println("Escribe paterno de la persona: ");
					buscado = lector.nextLine();
					System.out.println(personas.buscarPorPaterno(buscado));
					break;
				case "7":
					System.out.println("Escribe materno de la persona: ");
					buscado = lector.nextLine();
					System.out.println(personas.buscarPorMaterno(buscado));
					break;
				case "8":
					System.out.println("Programa finalizado");
					break;
				default:
					System.out.println("Opción incorrecta");
				}
			} while (!opcion.equals("8"));
		} catch (RemoteException | NotBoundException e) {
			System.out.println(e.getMessage());
		}
		lector.close();
	}

	private static void escribirMenu() {
		System.out.println();
		System.out.println("1- Mostrar todos los usuarios");
		System.out.println("2- Mostrar una persona al azar");
		System.out.println("Búsqueda de personas");
		System.out.println("--------------------------");
		System.out.println("3 = Por Codigo");
		System.out.println("4 = Por DNI");
		System.out.println("5 = Por Nombre");
		System.out.println("6 = Por Apellido Paterno");
		System.out.println("7 = Por Apellido Materno");
		System.out.println("--------------------------");
		System.out.println("¿Qué opción eliges?");
	}
}
