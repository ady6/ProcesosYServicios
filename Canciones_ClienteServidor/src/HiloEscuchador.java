import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class HiloEscuchador implements Runnable {
	private Thread hilo;
	private static int numCliente = 0;
	private Socket enchufeAlCliente;
	private Canciones canciones;
	private ObjectInputStream entrada;
	private ObjectOutputStream salida;

	public HiloEscuchador(Socket cliente, Canciones canciones) throws IOException {
		numCliente++;
		hilo = new Thread(this, "Cliente" + numCliente);
		this.enchufeAlCliente = cliente;
		this.canciones = canciones;
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
				if (peticion.trim().equals("FIN")) {
					salida.writeObject("Hasta pronto, gracias por establecer conexión");
					System.out.println(hilo.getName() + " ha cerrado la comunicación");
				} else {
					// Atiende aquí la petición del cliente
					// Debes enviar al cliente una canción o un ArrayList de canciones
					// lo que envíes irá en función de la petición recibida.
					System.out.println(hilo.getName() + " elige la opción número: " + peticion + "\n");
					// Enviamos el objeto correspondiente segun la peticion consultada
					
					
					Canciones misCanciones = new Canciones();
					String[] partes = peticion.split("-");
					String numero = partes[0];
					String grupo = partes[1];
					
					if (numero.trim().equals("1")) {
						Cancion unaCancion = misCanciones.cancionAzar();
						if (unaCancion == null) {
							salida.writeObject("No se ha podido encontrar cancion");
						} else {
							salida.writeObject(unaCancion);
						}

					} else if (peticion.trim().equals(partes[0]+"-"+partes[1])) {
						ArrayList<Cancion> buscar = misCanciones.getCancionesGrupo(partes[1]);
						salida.writeObject(buscar);

						
					} else if (peticion.trim().equals(partes[0]+"-"+partes[1])) {
						ArrayList<Cancion> buscar = misCanciones.getCancionesTitulo(partes[1]);
								salida.writeObject(buscar);

					} else if (peticion.trim().equals("4")) {
						for (int i = 0; i < misCanciones.getListaDistribucion().size(); i++) {
							Cancion TodasLasCanciones = misCanciones.getListaDistribucion().get(i);
							salida.writeObject(TodasLasCanciones);
						}
						
					}else if (peticion.trim().equals("5")) {
						entrada.close();
						salida.close();
						enchufeAlCliente.close();
						}
					}

			
			}while ((!peticion.trim().equals("FIN")));
			entrada.close();
			salida.close();
			enchufeAlCliente.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}