
public class Principal {

	public static void main(String[] args) throws InterruptedException {

		BufferExamenes generador = new BufferExamenes();


		// si no pasas ningún argumento
		if (args.length < 1) {
			System.out.println("Se requiere alumno");
			return;
		}
		
		for (int i = 0; i < args.length; i++){
			new ProductorExamenes(generador);
			new Examinador(args[i],generador);

		
		}

	}
}
