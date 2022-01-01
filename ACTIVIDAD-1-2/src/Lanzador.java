import java.io.File;
import java.io.IOException;

public class Lanzador {
	
	public static void main(String[] args) throws InterruptedException {
		
		
		ProcessBuilder examen1, examen2;

		//procesos para examenes
		examen1 = new ProcessBuilder("java","Principal","Pepe","Juan","Luis");
		examen2 = new ProcessBuilder("java","Principal","Rosa","Miguel","Pedro");
		
		//archivos que se crearan al ejecutar Principal
		examen1.redirectError(new File("error1.txt"));
		examen1.redirectOutput(new File("examen1.txt"));

		examen2.redirectError(new File("error2.txt"));
		examen2.redirectOutput(new File("examen2.txt"));

		//inicializa los procesos e informa de examinar los archivos creados
		try {
			examen1.start();
			examen2.start();
			System.out.println("Los examenes se han lanzado con éxito");
			System.out.println("Examina los archivos examen(1, 2).txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
