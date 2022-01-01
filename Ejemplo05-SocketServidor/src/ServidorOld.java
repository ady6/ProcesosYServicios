import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorOld {
	public static void main(String[] args) {
		System.out.println(" APLICACIÓN DE SERVIDOR ");
		System.out.println("----------------------------------");
		try {
			ServerSocket servidor = new ServerSocket();
			InetSocketAddress direccion = new InetSocketAddress("192.168.34.55", 2000);
			servidor.bind(direccion);
			System.out.println("Servidor creado");
			System.out.println("Dirección IP: " + direccion.getAddress());
			Scanner lector = new Scanner(System.in);
			String continuar = "S";
			while (continuar.toUpperCase().equals("S")) {
				System.out.println("Servidor esperando solicitud ....");
				Socket enchufeAlCliente = servidor.accept();
				System.out.println("Comunicación entrante");
				InputStream entrada = enchufeAlCliente.getInputStream();
				OutputStream salida = enchufeAlCliente.getOutputStream();
				String texto = "";
				while (!texto.trim().equals("FIN")) {
					byte[] mensaje = new byte[100];
					entrada.read(mensaje);
					texto = new String(mensaje);
					if (texto.trim().equals("FIN")) {
						salida.write("Hasta pronto, gracias por establecer conexión".getBytes());
					} else {
						System.out.println("Cliente dice: " + texto);
						salida.write(("Tu mensaje tiene " + texto.trim().length() + " caracteres").getBytes());
					}
				}
				entrada.close();
				salida.close();
				enchufeAlCliente.close();
				System.out.println("Servidor libre, ¿Atendemos más peticiones (S/N)?");
				continuar = lector.nextLine();
			}
			lector.close();
			servidor.close();
			System.out.println("Comunicación cerrada");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
