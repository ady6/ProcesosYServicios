import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HiloEscuchador implements Runnable {
	private Thread hilo;
	private static int numCliente = 0;
	private Socket enchufeAlCliente;

	public HiloEscuchador(Socket cliente) {
		numCliente++;
		hilo = new Thread(this, "Cliente" + numCliente);
		this.enchufeAlCliente = cliente;
		hilo.start();
	}

	@Override
	public void run() {
		System.out.println("Estableciendo comunicación con " + hilo.getName());
		try {
			InputStream entrada = enchufeAlCliente.getInputStream();
			OutputStream salida = enchufeAlCliente.getOutputStream();
			String texto = "";
			while (!texto.trim().equals("FIN")) {
				byte[] mensaje = new byte[100];
				entrada.read(mensaje);
				texto = new String(mensaje);
				if (texto.trim().equals("FIN")) {
					salida.write("Hasta pronto, gracias por establecer conexión".getBytes());
					System.out.println(hilo.getName() + " ha cerrado la comunicación");
				} else {
					System.out.println(hilo.getName() + " dice: " + texto);
					String[] partes;
					partes = texto.split("-");
					float peso = Float.parseFloat(partes[0]);
					float altura = Float.parseFloat(partes[1]);
					float IMC = peso / ((altura) * (altura));

					if (IMC < 18.5) {
						salida.write(("Tu IMC es de " + IMC
								+ ", estás por debajo de lo saludable te hace falta un buen cocido").getBytes());

					} else if (IMC > 18.5 && IMC < 24.9) {
						salida.write(("Tu IMC es de " + IMC + ", estás sano sigue así :)").getBytes());

					} else if (IMC > 25 && IMC < 29.9) {
						salida.write(
								("Tu IMC es de " + IMC + ", tienes sobrepeso hora de apuntarse al gym").getBytes());

					} else if (IMC > 30 && IMC < 34.9) {
						salida.write(
								("Tu IMC es de " + IMC + ", tienes obesidad I hora de cuidar tu salud :(").getBytes());

					} else if (IMC > 35 && IMC < 39.9) {
						salida.write(
								("Tu IMC es de " + IMC + ", tienes obesidad II hora de tratarlo en cuanto antes, ánimo")
										.getBytes());

					} else {
						salida.write(
								("Tu IMC es de " + IMC + ", tienes obesidad III por favor busca ayuda por tu salud")
										.getBytes());
						salida.write(("Tu mensaje tiene " + texto.trim().length() + " caracteres").getBytes());
					}
				}
				entrada.close();
				salida.close();
				enchufeAlCliente.close();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
