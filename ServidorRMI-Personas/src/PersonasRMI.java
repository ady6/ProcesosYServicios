import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PersonasRMI extends UnicastRemoteObject implements PersonasInterfaceRMI {
	private static final long serialVersionUID = 1L;
	private ArrayList<Persona> personas = new ArrayList<>();
	
	protected PersonasRMI() throws RemoteException {
		String nra ="data/Personas.txt";
		personas = LeerTxt.leerPersona(nra);
	}

	
	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}

	@Override
	public ArrayList<String> mostrarTodos() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String personaAzar() throws RemoteException {
		String resultado ="Error";
		if(personas != null) {
			int i = (int) (Math.random()*personas.size());
			
			Persona p = personas.get(i);
			resultado = p.toString();
		}
		return resultado;
	}
	@Override
	public String buscarPorCodigo(String codigoPersona) throws RemoteException {
		String resultado ="";
		if(personas != null) {
			for (int i = 0; i < personas.size(); i++) {
				Persona p = personas.get(i);
				if(codigoPersona.equalsIgnoreCase(p.getCodigoPersona())) {
					resultado=p.toString();
				}else {
					resultado="Error, código no encontrado";
				}
			}
		}
		return resultado;
	}
	@Override
	public String buscarPorDNI(String dni) throws RemoteException {
		String resultado ="";
		if(personas != null) {
			for (int i = 0; i < personas.size(); i++) {
				Persona p = personas.get(i);
				if(Integer.parseInt(dni) == p.getDni()) {
					resultado=p.toString();
				}else {
					resultado="Error, dni no encontrado";
				}
			}
		}
		return resultado;	}
	@Override
	public String buscarPorNombre(String nombre) throws RemoteException {
		String resultado ="";
		if(personas != null) {
			for (int i = 0; i < personas.size(); i++) {
				Persona p = personas.get(i);
				if(nombre.equalsIgnoreCase(p.getNombre())) {
					resultado=p.toString();
				}else {
					resultado="Error, nombre no encontrado";
				}
			}
		}
		return resultado;
	}
	@Override
	public String buscarPorPaterno(String paterno) throws RemoteException {
		String resultado ="";
		if(personas != null) {
			for (int i = 0; i < personas.size(); i++) {
				Persona p = personas.get(i);
				if(paterno.equalsIgnoreCase(p.getPaterno())) {
					resultado=p.toString();
				}else {
					resultado="Error, apellido paterno no encontrado";
				}
			}
		}
		return resultado;
	}
	@Override
	public String buscarPorMaterno(String materno) throws RemoteException {
		String resultado ="";
		if(personas != null) {
			for (int i = 0; i < personas.size(); i++) {
				Persona p = personas.get(i);
				if(materno.equalsIgnoreCase(p.getMaterno())) {
					resultado=p.toString();
				}else {
					resultado="Error, apellido materno no encontrado";
				}
			}
		}
		return resultado;
	}
	
}
