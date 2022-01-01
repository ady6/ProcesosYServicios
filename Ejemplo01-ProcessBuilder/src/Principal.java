import java.io.File;
import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		
		try {
			
		//clase java para crear procesos
		ProcessBuilder proceso;
		
		//recojo el proceso en un objeto Process
		proceso = new ProcessBuilder("C:/Windows/notepad.exe", "pelis.txt");
		
		//establezco directorio de trabajo para proceso
		proceso.directory(new File("C:\\Datos"));

		//creo y comienzo un proceso
		Process p = proceso.start();
		
		//método que mantiene programa en ejecución
		//en espera de que el proceso se cierre
		p.waitFor();
		
		} catch (IOException | InterruptedException e) {
			
			System.out.println(e.getMessage());
		}
		

	}

}
