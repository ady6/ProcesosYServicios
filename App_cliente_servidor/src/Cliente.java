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
		System.out.println(" APLICACI�N CLIENTE");
		System.out.println("-----------------------------------");
		Scanner lector = new Scanner(System.in);
		try {
			Socket cliente = new Socket();
			InetSocketAddress direccionServidor = new InetSocketAddress("192.168.34.55", 2000);
			System.out.println("Esperando a que el servidor acepte la conexi�n");
			cliente.connect(direccionServidor);
			// Conectamos con el servidor.
			System.out.println("Comunicaci�n establecida con el servidor");
			
			salida = new ObjectOutputStream(cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());
			
			String peticion = "";
			while (!peticion.equals("FIN")) {
				System.out.println("\n\n1. Buscar persona por c�digo");
				System.out.println("2. Introduzca el nombre de la persona a buscar");
				System.out.println("3. Introduzca el apellido paterno de la persona a buscar");
				System.out.println("4. Introduzca el apellido materno de la persona a buscar");
				System.out.println("5. Introduzca 5 para desconectar");	
				System.out.println("\n�Qu� opci�n eliges(1-5)?");
				peticion = lector.nextLine();

				switch (peticion) {
				case "1":
					System.out.println("Introduzca el c�digo de la persona a buscar");
					String codigo = lector.nextLine();
					salida.writeObject("1-"+codigo);
					Persona p = (Persona) entrada.readObject();
					System.out.println("Los datos de la persona con c�digo "+p.getCodigoPersona()+" son: DNI ("+ p.getDni()+"), NOMBRE ("+
        					p.getNombre()+"), APELLIDO PATERNO ("+ p.getPaterno()+") Y APELLIDO MATERNO ("+ p.getMaterno()+")");
					break;
					/*
				case "2":
					System.out.println("�Nombre del grupo?");
					String grupo = lector.nextLine();
					salida.writeObject("2-"+grupo);
					ArrayList<Cancion> canciones = (ArrayList<Cancion>) entrada.readObject();
					for (Cancion ca : canciones) {
						System.out.println(ca);
					}
					break;
				case "3":
					System.out.println("�Nombre de la canci�n?");
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
					System.out.println("Opci�n incorrecta");
				}
														*/
			}
			}
			entrada.close();
			salida.close();
			cliente.close();
			System.out.println("Comunicaci�n cerrada");
		} catch (UnknownHostException e) {
			System.out.println("No se puede establecer comunicaci�n con el servidor");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S");
			System.out.println(e.getMessage());
		}
		lector.close();
	}
}