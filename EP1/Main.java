package EP1;
import java.util.*;

public class Main {

	// metodo principal.

	public static void main(String [] args){

		Scanner in = new Scanner(System.in);	// Scanner para facilitar a leitura de dados a partir da entrada padrao.
		String operacao = in.next();		// le, usando o scanner, a string que determina qual operacao deve ser realizada.
		int n = in.nextInt();			// le a dimensão da matriz a ser manipulada pela operacao escolhida.
		Matriz test_matriz = new Matriz(n, n);

		while(in.hasNext()){

			for (int x=0; x < n; x++){
				for (int y=0; y < n; y++){
					int position = in.nextInt();
					test_matriz.set(x,y,position);
					// System.out.print(x+" "+y+" "+position+"\n");
					// System.out.println(position);
				}
			}
		}

		if("resolve".equals(operacao)){

		}
		else if("inverte".equals(operacao)){

		}
		else if("determinante".equals(operacao)){
			double result = test_matriz.determinante(test_matriz);
			System.out.println(result);
		}
		else {
			System.out.println("Operação desconhecida!");
			System.exit(1);
		}
	}
}