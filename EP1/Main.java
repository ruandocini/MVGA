package EP1;
import java.util.*;
import EP1.Matriz;

public class Main {

	// metodo principal.

	public static void main(String [] args){

		Scanner in = new Scanner(System.in);	// Scanner para facilitar a leitura de dados a partir da entrada padrao.
		String operacao = in.next();		// le, usando o scanner, a string que determina qual operacao deve ser realizada.
		int n = in.nextInt();			// le a dimensão da matriz a ser manipulada pela operacao escolhida.
		Matriz matriz = new Matriz(n, n);
		int max_size_line = n;
		Matriz agregada = new Matriz(n, 1);

		for (int x=0; x < n; x++){
			for (int y=0; y < n; y++){
				
				String val = in.next();
				int position = Integer.parseInt(val);
				matriz.set(x,y,position);
			}
			if (in.hasNext()){
				agregada.set(x,0,in.nextInt());
			}
			// System.out.print(x+" "+y+" "+position+"\n");
			// System.out.println(position);
		}
		
		matriz.imprime();
		agregada.imprime();

		// if("resolve".equals(operacao)){
		// 	matriz.calculaSolucao();
		// }
		// else if("inverte".equals(operacao)){
		// 	matriz.calculaInversa();
		// 	}
		// else if("determinante".equals(operacao)){
		// 	double result = matriz.determinante(matriz);
		// 	System.out.println(result);
		// }
		// else {
		// 	System.out.println("Operação desconhecida!");
		// 	System.exit(1);
		// }
	}
}