import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		System.out.println(" APLICACIÓN DE SERVIDOR ");
		System.out.println("----------------------------------");
		try {
			ServerSocket servidor = new ServerSocket();
			InetSocketAddress direccion = new InetSocketAddress("192.168.34.55", 2000);
			
			servidor.bind(direccion);
			// Vinculamos el servidor con la dirección establecida por el objeto InetSocketAddress
			
			System.out.println("Servidor creado y escuchando .... ");
			System.out.println("Dirección IP: " + direccion.getAddress());
			Socket enchufeAlCliente = servidor.accept();
			// Detiene el proceso hasta que un cliente solicite una conexión. 
			
			System.out.println("Comunicación entrante");
			InputStream entrada = enchufeAlCliente.getInputStream();
			OutputStream salida = enchufeAlCliente.getOutputStream();
			// Abrirmos los flujos de entrada y salida para las comunicaciones.
			
			String texto = "";
			while (!texto.trim().equals("FIN")) {
				byte[] mensaje = new byte[100];
				entrada.read(mensaje);
				// Lee mensaje del cliente (array de bytes)
				texto = new String(mensaje);
				if (texto.trim().equals("FIN")) {
					salida.write("Hasta pronto, gracias por establecer conexión".getBytes());
				} else {
					System.out.println("Cliente dice: " + texto);
					
					String[] partes;
					partes = texto.split("-");
					float peso = Float.parseFloat(partes[0]);
					float altura = Float.parseFloat(partes[1]);
					float IMC = peso/((altura)*(altura));
					
					if (IMC<18.5) {
						salida.write(("Tu IMC es de " + IMC + ", estás por debajo de lo saludable te hace falta un buen cocido").getBytes());
					
					}else if (IMC>18.5 && IMC<24.9) {
						salida.write(("Tu IMC es de " + IMC + ", estás sano sigue así :)").getBytes());
					
					}else if (IMC>25 && IMC<29.9) {
						salida.write(("Tu IMC es de " + IMC + ", tienes sobrepeso hora de apuntarse al gym").getBytes());
					
					}else if (IMC>30 && IMC<34.9) {
						salida.write(("Tu IMC es de " + IMC + ", tienes obesidad I hora de cuidar tu salud :(").getBytes());
					
					}else if (IMC>35 && IMC<39.9) {
						salida.write(("Tu IMC es de " + IMC + ", tienes obesidad II hora de tratarlo en cuanto antes, ánimo").getBytes());
					
					}else {
						salida.write(("Tu IMC es de " + IMC + ", tienes obesidad III por favor busca ayuda por tu salud").getBytes());
						
					}
					
		
				}
			}
			entrada.close();
			salida.close();
			enchufeAlCliente.close();
			servidor.close();
			System.out.println("Comunicación cerrada");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

