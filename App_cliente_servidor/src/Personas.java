import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Personas {

	private ArrayList<Persona> listaPersonas;
	
	public static List<Persona> leerArchivo(String nombreRutaArchivo) {
		List<Persona> listaPersonas = new ArrayList<Persona>();
		File f;
		FileReader fr;
		BufferedReader br;
		try {
			f = new File(nombreRutaArchivo);
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String registro;
			String partes[];
			Persona persona;
			while ((registro = br.readLine()) != null) {
				partes = registro.split(";");
				persona = new Persona(partes[0], Integer.parseInt(partes[1]), partes[2], partes[3], partes[4]);
				listaPersonas.add(persona);
			}
		} catch (Exception e) {
			listaPersonas = null;
		}
		return listaPersonas;
	}

	public ArrayList<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(ArrayList<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public static Persona leerPersonaPorCodigo(String s) {

		String nra = "../data/Personas.csv";

		List<Persona> listaPersonas = leerArchivo(nra);

		if (listaPersonas != null) {
			for (int i = 0; i < listaPersonas.size(); i++) {
				Persona p = listaPersonas.get(i);
				if (s.equalsIgnoreCase(p.getCodigoPersona())) {
					return p;
				}
			}
		}
		return null;

	}
}