import java.io.File;
import java.io.IOException;

public class Lanzador {

	public static void main(String[] args) {
		
		ProcessBuilder proceso1, proceso2, proceso3;

		//procesos para triangulos cuyo argumentos son 5, 7 y 9 respectivamente
		proceso1 = new ProcessBuilder("java", "Triangulo", "5");
		proceso2 = new ProcessBuilder("java", "Triangulo", "7");
		proceso3 = new ProcessBuilder("java", "Triangulo", "9");

		//archivos que se crearan al ejecutar el Lanzador
		proceso1.redirectError(new File("errores1.txt"));
		proceso1.redirectOutput(new File("triangulo5.txt"));

		proceso2.redirectError(new File("errores2.txt"));
		proceso2.redirectOutput(new File("triangulo7.txt"));
		
		proceso3.redirectError(new File("errores3.txt"));
		proceso3.redirectOutput(new File("triangulo9.txt"));

		//inicializa los procesos e informa de examinar los archivos creados una vez escrito en la cmd "java Lanzador"
		try {
			proceso1.start();
			proceso2.start();
			proceso3.start();
			System.out.println("Los procesos se han lanzado con éxito");
			System.out.println("Examina los archivos errores(1,2 o 3).txt y triangulo(5, 7 o 9).txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}