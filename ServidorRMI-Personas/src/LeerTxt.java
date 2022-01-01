import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeerTxt {
	
	
	public static ArrayList<Persona> leerPersona(String nombreRutaArchivoTexto) {
        String codigo,nombre,paterno, materno;
        int dni;
        String registro;
        String[] campo;
        ArrayList arraylistlectura;
        Persona persona;

        File f;
        FileReader fr;
        fr = null;
        BufferedReader br;
        
        try {
            arraylistlectura = new ArrayList();
            // (1) CREAR UN OBJETO DEL ARCHIVO
            f = new File(nombreRutaArchivoTexto);
            // (2) ABRE UN FLUJO DE ENTRADA DESDE UN ARCHIVO (LECTURA)
            fr = new FileReader(f);
            // (3) OBTENER LA INFORMACION POR EL FLUJO ENTRADA DESDE UN ARCHIVO
            br = new BufferedReader(fr);
            while ((registro = br.readLine()) != null) {
                campo = registro.split(";");
                codigo = campo[0];
                dni = Integer.parseInt(campo[1]);
                nombre = campo[2];
                paterno = campo[3];
                materno = campo[4];
                persona = new Persona(codigo, dni, nombre, paterno, materno);
                arraylistlectura.add(persona);
            }
        } catch (IOException e) {
            arraylistlectura = null;
        } //(3) CIERRA EL FLUJO DE ENTRADA DESDE UN ARCHIVO
        finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e) {
                arraylistlectura = null;
            }
        }
        return arraylistlectura;
    }
	
	
}
