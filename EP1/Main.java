
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

		if("resolve".equals(operacao)){
			n = n + 1;
		}

		for (int x=0; x < n; x++){
			int acumulator = 0;
			for (int y=0; y < n; y++){
				if (in.hasNext() && acumulator == max_size_line){
					String val = in.next();
					int position = Integer.parseInt(val);
					agregada.set(x,0,position);

				} else {
					if (in.hasNext()){
						String val = in.next();
						int position = Integer.parseInt(val);
						matriz.set(x,y,position);
						// System.out.println(acumulator);
						// System.out.println(position);
						acumulator++;
					}
					// System.out.println(n);
					// System.out.println(acumulator);
				}
			}
			// System.out.print(x+" "+y+" "+position+"\n");
			// System.out.println(position);
		}
		
		// matriz.imprime();
		// agregada.imprime();

		if("resolve".equals(operacao)){
			matriz.formaEscalonadaReduzida(agregada);
			//inifnity -> sem solucao
			//nan -> multiplas soluções
			String solution = "";
			for (int i=0; i < matriz.getColumnsLen(); i++){
				if (Double.isInfinite(agregada.get(i,0))){
					solution = "sistema sem solução";	
					break;		
				} else if (Double.isNaN(agregada.get(i,0))){
					solution = "sistema possui diversas soluções";
					break;
				} else {
					solution = "sistema possui uma solução";
				}
			}

			if (solution.equals("sistema sem solução")){
				System.out.println("sistema sem solução");
			} else if (solution.equals("sistema possui diversas soluções")){
				System.out.println("sistema possui diversas soluções");
			} else {
				agregada.imprime();
			}
		}	
		else if("inverte".equals(operacao)){
			double det = matriz.determinante(matriz);
			if (det == 0){
				System.out.println("sistema sem solução");
			} else {
				// matriz.inverte(matriz);
				matriz.imprime();
			}
		}
		else if("determinante".equals(operacao)){
			double result = matriz.determinante(matriz);
			System.out.println(result);
		}
		else {
			System.out.println("Operação desconhecida!");
			System.exit(1);
		}
		in.close();
	}
}