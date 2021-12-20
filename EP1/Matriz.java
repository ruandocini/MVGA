package EP1;
// classe que representa uma matriz de valores do tipo double.

public class Matriz {

	public static final double constante_desprezivel = 0.000001;
	
	private int numero_de_linhas, numero_de_colunas;	
	private double [][] elementos;

	// metodo estatico que cria uma matriz identidade de tamanho n x n.
	public static Matriz identidade(int tamanho) {
		Matriz matriz = new Matriz(tamanho, tamanho);
		for (int i = 0; i < matriz.numero_de_linhas; i++) {
			matriz.elementos[i][i] = 1;
		}
		return matriz;
	}

	// construtor que cria uma matriz de n linhas por m colunas com todas as entradas iguais a zero.
	public Matriz(int numero_de_linhas, int numero_de_colunas) {
		this.numero_de_linhas = numero_de_linhas;
		this.numero_de_colunas = numero_de_colunas;
		this.elementos = new double[this.numero_de_linhas][this.numero_de_colunas];
	}

	public void setValorDeElemento(int i, int j, double valor){

		this.elementos[i][j] = valor;
	}

	public double getValorDeElemento(int i, int j){

		return this.elementos[i][j];
	}

	// metodo que imprime as entradas da matriz.

	public void imprime(){

		for(int i = 0; i < numero_de_linhas; i++){

			for(int j = 0; j < numero_de_colunas; j++){
	
				System.out.printf("%7.2f ", elementos[i][j]);
			}

			System.out.println();
		}
	}

	public Integer getNumeroDeLinhas(){
		return this.numero_de_linhas;
	}

	public Integer getNumeroDeColunas(){
		return this.numero_de_colunas;
	}

	// metodo que imprime a matriz expandida formada pela combinacao da matriz que 
	// chama o metodo com a matriz "agregada" recebida como parametro. Ou seja, cada 
	// linha da matriz impressa possui as entradas da linha correspondente da matriz 
	// que chama o metodo, seguida das entradas da linha correspondente em "agregada".

	public void imprime(Matriz agregada){

		for(int i = 0; i < numero_de_linhas; i++){

			for(int j = 0; j < numero_de_colunas; j++){
	
				System.out.printf("%7.2f ", elementos[i][j]);
			}

			System.out.print(" |");

			for(int j = 0; j < agregada.numero_de_colunas; j++){
	
				System.out.printf("%7.2f ", agregada.elementos[i][j]);
			}

			System.out.println();
		}
	}
	
	// metodo que troca as linhas i1 e i2 de lugar.
	// voltar pra private viu?
	private void trocaLinha(int i1, int i2){
		
		Matriz duplicate_matriz = new Matriz(this.getNumeroDeLinhas(), this.getNumeroDeColunas());

		for (int i = 0; i < this.getNumeroDeLinhas(); i++) {
            for (int j = 0; j < this.getNumeroDeColunas(); j++) {
                duplicate_matriz.setValorDeElemento(i, j, this.getValorDeElemento(i, j));
            }
        }

        for (int j = 0; j < this.getNumeroDeColunas(); j++) {
            this.setValorDeElemento(i1, j, duplicate_matriz.getValorDeElemento(i2, j));
            this.setValorDeElemento(i2, j, duplicate_matriz.getValorDeElemento(i1, j));
        }
	
	}

	// metodo que multiplica as entradas da linha i pelo escalar k

	private void multiplicaLinha(int i, double k){

		for (int j = 0; j < this.getNumeroDeColunas(); j++) {
			this.setValorDeElemento(i, j, this.getValorDeElemento(i, j)*k);
		}
        
	}

	// metodo que faz a seguinte combinacao de duas linhas da matriz:
	//	(linha i1) = (linha i1) + (linha i2 * k)
	private void combinaLinhas(int i1, int i2, double k){

		for (int j = 0; j < this.getNumeroDeColunas(); j++) {
            this.setValorDeElemento(i1, j, this.getValorDeElemento(i1, j)+this.getValorDeElemento(i2, j)*k);
        }
	}

	// metodo que procura, a partir da linha ini, a linha com uma entrada nao nula que
	// esteja o mais a esquerda possivel dentre todas as linhas. Os indices da linha e da 
	// coluna referentes a entrada nao nula encontrada sao devolvidos como retorno do metodo.
	// Este metodo ja esta pronto para voces usarem na implementacao da eliminacao gaussiana
	// e eleminacao de Gauss-Jordan.

	private int [] encontraLinhaPivo(int ini){

		int pivo_col, pivo_lin;

		pivo_lin = numero_de_linhas;
		pivo_col = numero_de_colunas;

		for(int i = ini; i < numero_de_linhas; i++){
		
			int j;
			
			for(j = 0; j < numero_de_colunas; j++) if(Math.abs(elementos[i][j]) > 0) break;

			if(j < pivo_col) {

				pivo_lin = i;
				pivo_col = j;
			}

		}

		return new int [] { pivo_lin, pivo_col };
	}

	// metodo que implementa a eliminacao gaussiana, que coloca a matriz (que chama o metodo)
	// na forma escalonada. As operacoes realizadas para colocar a matriz na forma escalonada 
	// tambem devem ser aplicadas na matriz "agregada" caso esta seja nao nula. Este metodo 
	// tambem deve calcular e devolver o determinante da matriz que invoca o metodo. Assumimos 
	// que a matriz que invoca este metodo eh uma matriz quadrada.

	public double formaEscalonada(Matriz agregada){

		int system_dimension = this.getNumeroDeLinhas();

		for(int i = 0; i < system_dimension; i++){
			// int pivo[] = encontraLinhaPivo(i);
			int linhaPivo = i;
			int pivo[] = encontraLinhaPivo(i);

			// System.out.println(pivo[0]);
			// System.out.println(system_dimension);

			if (pivo[0] != linhaPivo) {
				if (pivo[0] == system_dimension) {
					pivo[0] = pivo[0] -1;
				}
				this.trocaLinha(i, pivo[0]);
			}
			for(int j = i+1; j < system_dimension; j++){
				double multiplier = this.getValorDeElemento(j,i)/this.getValorDeElemento(i,i);
				for(int n = i; n < system_dimension; n++){ 
					this.setValorDeElemento(j,n,this.getValorDeElemento(j,n)-this.getValorDeElemento(i,n)*multiplier);
				}
			}
		}

		// this.imprime();

		return 0.0;
	}

	public double determinante(Matriz agregada){
		this.formaEscalonada(agregada);
		int system_dimension = this.getNumeroDeLinhas();
		double result = 1.0;
		for (int j = 0; j < system_dimension; j++){
			result = result * this.getValorDeElemento(j,j);
		}
		return result;
	}

	public void calculaSolucao(Matriz agregada){
		
		int system_dimension = this.getNumeroDeLinhas();
		int system_solutions[] = new int[system_dimension];

		for(int i = system_dimension-1; i >= 0; i--){
			
			double sum = 0.0;

			for(int j = i+1; j < system_dimension; j++){
				sum += this.getValorDeElemento(i,j)*agregada.getValorDeElemento(j,0);
			}

			agregada.setValorDeElemento(i, 0, (this.getValorDeElemento(i,system_dimension)-sum)/this.getValorDeElemento(i,i));
		}

	}

	// metodo que implementa a eliminacao de Gauss-Jordan, que coloca a matriz (que chama o metodo)
	// na forma escalonada reduzida. As operacoes realizadas para colocar a matriz na forma escalonada 
	// reduzida tambem devem ser aplicadas na matriz "agregada" caso esta seja nao nula. Assumimos que
	// a matriz que invoca esta metodo eh uma matriz quadrada. Não se pode assumir, contudo, que esta
	// matriz ja esteja na forma escalonada (mas voce pode usar o metodo acima para isso).

	public void formaEscalonadaReduzida(Matriz agregada){

		// TODO: implementar este metodo.		
	}
}

// Classe "executavel".
