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
			while (!peticion.equals("FIN")) {
				System.out.println("\n\n1. Buscar persona por código");
				System.out.println("2. Introduzca el nombre de la persona a buscar");
				System.out.println("3. Introduzca el apellido paterno de la persona a buscar");
				System.out.println("4. Introduzca el apellido materno de la persona a buscar");
				System.out.println("5. Introduzca 5 para desconectar");	
				System.out.println("\n¿Qué opción eliges(1-5)?");
				peticion = lector.nextLine();

				switch (peticion) {
				case "1":
					System.out.println("Introduzca el código de la persona a buscar");
					String codigo = lector.nextLine();
					salida.writeObject("1-"+codigo);
					Persona p = (Persona) entrada.readObject();
					System.out.println("Los datos de la persona con código "+p.getCodigoPersona()+" son: DNI ("+ p.getDni()+"), NOMBRE ("+
        					p.getNombre()+"), APELLIDO PATERNO ("+ p.getPaterno()+") Y APELLIDO MATERNO ("+ p.getMaterno()+")");
					break;
					/*
				case "2":
					System.out.println("¿Nombre del grupo?");
					String grupo = lector.nextLine();
					salida.writeObject("2-"+grupo);
					ArrayList<Cancion> canciones = (ArrayList<Cancion>) entrada.readObject();
					for (Cancion ca : canciones) {
						System.out.println(ca);
					}
					break;
				case "3":
					System.out.println("¿Nombre de la canción?");
					String nombre = lector.nextLine();
					salida.writeObject("3-"+nombre);
					ArrayList<Cancion> canciones2 = (ArrayList<Cancion>) entrada.readObject();
					for (Cancion ca : canciones2) {
						System.out.println(ca);
					}
					break;
				case "4":
					salida.writeObject("4-nada");
					ArrayList<Cancion> canciones3 = (ArrayList<Cancion>) entrada.readObject();
					for (Cancion ca : canciones3) {
						System.out.println(ca);
					}
					break;

				case "5":
					peticion = "FIN";
					break;

				default:
					System.out.println("Opción incorrecta");
				}
														*/
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