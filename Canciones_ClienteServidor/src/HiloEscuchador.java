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
		System.out.println("Estableciendo comunicaci�n con " + hilo.getName());
		try {
			salida = new ObjectOutputStream(enchufeAlCliente.getOutputStream());
			entrada = new ObjectInputStream(enchufeAlCliente.getInputStream());
			String peticion = "";
			do {
				// Recoge la petici�n del cliente como un objeto String.
				// La comunicaci�n con el cliente se mantendr� hasta que
				// el cliente env�e la palabra "FIN".
				peticion = (String) entrada.readObject();
				if (peticion.trim().equals("FIN")) {
					salida.writeObject("Hasta pronto, gracias por establecer conexi�n");
					System.out.println(hilo.getName() + " ha cerrado la comunicaci�n");
				} else {
					// Atiende aqu� la petici�n del cliente
					// Debes enviar al cliente una canci�n o un ArrayList de canciones
					// lo que env�es ir� en funci�n de la petici�n recibida.
					System.out.println(hilo.getName() + " elige la opci�n n�mero: " + peticion + "\n");
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