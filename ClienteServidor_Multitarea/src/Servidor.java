import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		System.out.println("APLICACI�N DE SERVIDOR MULTITAREA");
		System.out.println("----------------------------------");
		try {
			ServerSocket servidor = new ServerSocket();
			InetSocketAddress direccion = new InetSocketAddress("192.168.34.55", 2000);
			servidor.bind(direccion);
			System.out.println("Servidor listo para aceptar solicitudes");
			System.out.println("Direcci�n IP: " + direccion.getAddress());
			while (true) {
				Socket enchufeAlCliente = servidor.accept();
				System.out.println("Comunicaci�n entrante");
				new HiloEscuchador(enchufeAlCliente);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}