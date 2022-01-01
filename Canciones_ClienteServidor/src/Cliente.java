import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
	private static ObjectInputStream entrada;
	private static ObjectOutputStream salida;

	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(" APLICACIÓN CLIENTE");
		System.out.println("-----------------------------------");
		Scanner lector = new Scanner(System.in);
		try {
			Socket cliente = new Socket();
			InetSocketAddress direccionServidor = new InetSocketAddress("192.168.34.55", 2000);
			System.out.println("Esperando a que el servidor acepte la conexión");
			cliente.connect(direccionServidor);
			// Conectamos con el servidor.
			System.out.println("Comunicación establecida con el servidor");

			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

			String peticion = "";
			while (!peticion.equals("FIN") || (!peticion.equals("5"))) {
				System.out.println("\n\n1. Una sola canción (escriba '1')");
				System.out.println("2. Canciones de un grupo (escriba '2-NombreDelGrupo')");
				System.out.println("3. Canciones por título (escriba '3-NombreDelTítulo')");
				System.out.println("4. Todas las canciones de la lista(escriba '4')");
				System.out.println("5. Desconectar(escriba '5')");
				// Desarrollar aquí la lógica de cada opción
				// para que el cliente se comunique con el servidor para obtener
				// lo que necesita.
				
				System.out.println("\n¿Qué opción eliges?");
				peticion = lector.nextLine();
				if (peticion.length()==1) {
					peticion = peticion+"-nada";
				}

				String[] partes = peticion.split("-");
				String numero = partes[0];
				String grupo = partes[1];

				if (numero.trim().equals("1")) {
					// Envio consulta al servidor
					salida.writeObject(peticion);
					// Recibo respuesta del servidor
					Object unaCancion = (Cancion) entrada.readObject();
					System.out.println(unaCancion);

				} else if (numero.trim().equals("2")) {
					// Envio consulta al servidor
					salida.writeObject(peticion);
					// Recibo respuesta del servidor
					ArrayList<Cancion> buscar = (ArrayList<Cancion>)entrada.readObject();
					System.out.println("Las canciones de " + partes[1] + ":");
					for (Cancion c : buscar) {
						System.out.println(c);
					}

				} else if (numero.trim().equals("3")) {
					// Envio consulta al servidor
					salida.writeObject(peticion);
					// Recibo respuesta del servidor
					ArrayList<Cancion> buscar = (ArrayList<Cancion>)entrada.readObject();
					System.out.println("Los títulos con " + partes[1] + ":");
					for (Cancion c : buscar) {
						System.out.println(c);
					}


				}else if(numero.trim().equals("4")) {
					// Envio consulta al servidor
					salida.writeObject(peticion);
					// Recibo respuesta del servidor
					Canciones misCanciones = new Canciones();
					for (int i = 0; i < misCanciones.getListaDistribucion().size(); i++) {
					Object TodasLasCanciones = (Cancion) entrada.readObject();
					System.out.println(TodasLasCanciones);
					}
				}else if(numero.trim().equals("5")) {
						// Envio consulta al servidor
						salida.writeObject(peticion);
						// Recibo respuesta del servidor
						entrada.close();
						salida.close();
						cliente.close();
						System.out.println("Comunicación cerrada");
				}else {
					System.out.println("El número introducido no es correcto");
					
				}

				}
			
		entrada.close();
		salida.close();
		cliente.close();
		System.out.println("Comunicación cerrada");
	} catch (UnknownHostException e) {
		System.out.println("No se puede establecer comunicación con el servidor");
		System.out.println(e.getMessage());
	} catch (IOException e) {
		System.out.println("Error de E/S");
		System.out.println(e.getMessage());
	}
	lector.close();
}
}