import java.io.File;
import java.io.IOException;
public class Lanzador {
	public static void main(String[] args) {
		ProcessBuilder proceso1, proceso2;
			
		// A ProcessBuilder le pasamos los mismos argumentos
		// que palabras tengo que escribir en la línea de comandos
		
		proceso1 = new ProcessBuilder("java", "Principal");
		proceso2 = new ProcessBuilder("java", "Principal");
		
		proceso1.redirectError(new File("errores1.txt"));
		proceso1.redirectOutput(new File("salida1.txt"));
		proceso1.redirectInput(new File("entrada1.txt"));
		
		proceso2.redirectError(new File("errores2.txt"));
		proceso2.redirectOutput(new File("salida2.txt"));
		proceso2.redirectInput(new File("entrada2.txt"));
		
		try {
			proceso1.start();
			proceso2.start();
			System.out.println("El proceso ha sido lanzado con éxito");
			System.out.println("Examina los archivos errores.txt y salida.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}






