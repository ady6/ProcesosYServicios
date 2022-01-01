import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Triangulo {
	
	public static void main(String[] args) {
		
		//si no pasas argumentos
		if (args.length == 0) {
			System.out.println("Se requiere un argumento");
			return;
		}
		
		int filas = Integer.parseInt(args[0]);
		
		//fecha inicio proceso
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        System.out.println("Fecha de inicio del proceso: "+dtf1.format(LocalDateTime.now())+"\n");
		
        //formacion del triangulo
		for (int i=filas; i>=1; i--) {
			for (int n=1; n<=i; n++) {
				System.out.print(n);
			}
			
			System.out.println();
		}
		
		//fecha fin triangulo
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        System.out.println("\nFecha de finalización del proceso: "+dtf2.format(LocalDateTime.now()));
	}
}
