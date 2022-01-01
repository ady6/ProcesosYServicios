import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PersonasInterfaceRMI extends Remote{
	public ArrayList<String> mostrarTodos() throws RemoteException;
	public String personaAzar() throws RemoteException;
	public String buscarPorCodigo(String codigoPersona) throws RemoteException;
	public String buscarPorDNI(String dni) throws RemoteException;
	public String buscarPorNombre(String nombre) throws RemoteException;
	public String buscarPorPaterno(String paterno) throws RemoteException;
	public String buscarPorMaterno(String materno) throws RemoteException;
}