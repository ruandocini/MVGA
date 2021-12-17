import java.util.*;

public class EP1 {

	// metodo principal.

	public static void main(String [] args){

		Scanner in = new Scanner(System.in);	// Scanner para facilitar a leitura de dados a partir da entrada padrao.
		String operacao = in.next();		// le, usando o scanner, a string que determina qual operacao deve ser realizada.
		int n = in.nextInt();			// le a dimensão da matriz a ser manipulada pela operacao escolhida.

		// TODO: completar este metodo.

		if("resolve".equals(operacao)){

		}
		else if("inverte".equals(operacao)){

		}
		else if("determinante".equals(operacao)){

		}
		else {
			System.out.println("Operação desconhecida!");
			System.exit(1);
		}
	}
}