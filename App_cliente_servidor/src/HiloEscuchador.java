import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class HiloEscuchador implements Runnable {

	private Thread hilo;
	private static int numCliente = 0;
	private Socket enchufeAlCliente;
	private Personas personas;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;

	public HiloEscuchador(Socket cliente, Personas personas) throws IOException {

		numCliente++;
		hilo = new Thread(this, "Cliente" + numCliente);
		this.enchufeAlCliente = cliente;
		this.personas = personas;
		hilo.start();
	}

	@Override

	public void run() {

		System.out.println("Estableciendo comunicación con " + hilo.getName());
		try {
			salida = new ObjectOutputStream(enchufeAlCliente.getOutputStream());
			entrada = new ObjectInputStream(enchufeAlCliente.getInputStream());
			String peticion = "";

			do {
				// Recoge la petición del cliente como un objeto String.
				// La comunicación con el cliente se mantendrá hasta que
				// el cliente envíe la palabra "FIN".

				peticion = (String) entrada.readObject();
				String[] partes = peticion.split("-");
				String numero = partes[0];
				String filtro = "";
				if (partes.length >= 2)
					filtro = partes[1];

				if (peticion.trim().equals("FIN")) {
					salida.writeObject("Hasta pronto, gracias por establecer conexión");
					System.out.println(hilo.getName() + " ha cerrado la comunicación");
				} else {
					switch (numero) {

					case "1":
						Persona p = Personas.leerPersonaPorCodigo(filtro);
						salida.writeObject(p);
						break;

					/*
					case "2":
						ArrayList<Cancion> canciones1 = canciones.getCancionesGrupo(filtro);
						salida.writeObject(canciones1);
						break;

					case "3":
						ArrayList<Cancion> canciones2 = canciones.getCancionesTitulo(filtro);
						salida.writeObject(canciones2);
						break;

					case "4":

						salida.writeObject(canciones.getListaDistribucion());
						break;
						*/
					}
				}

			} while ((!peticion.trim().equals("FIN")));
			entrada.close();
			salida.close();
			enchufeAlCliente.close();

		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}