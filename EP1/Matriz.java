package EP1;
// classe que representa uma matriz de valores do tipo double.

public class Matriz {

	// constante para ser usada na comparacao de valores double.
	// Se a diferenca absoluta entre dois valores double for menor
	// do que o valor definido por esta constante, eles devem ser
	// considerados iguais.
	public static final double SMALL = 0.000001;
	
	private int lin, col;	
	private double [][] m;

	// metodo estatico que cria uma matriz identidade de tamanho n x n.

	public static Matriz identidade(int n){

		Matriz mat = new Matriz(n, n);
		for(int i = 0; i < mat.lin; i++) mat.m[i][i] = 1;
		return mat;
	}	

	// construtor que cria uma matriz de n linhas por m colunas com todas as entradas iguais a zero.

	public Matriz(int n, int m){

		this.lin = n;
		this.col = m;
		this.m = new double[lin][col];
	}

	public void set(int i, int j, double valor){

		m[i][j] = valor;
	}

	public double get(int i, int j){

		return m[i][j];
	}

	// metodo que imprime as entradas da matriz.

	public void imprime(){

		for(int i = 0; i < lin; i++){

			for(int j = 0; j < col; j++){
	
				System.out.printf("%7.2f ", m[i][j]);
			}

			System.out.println();
		}
	}

	public Integer getLinesLen(){
		return this.lin;
	}

	public Integer getColumnsLen(){
		return this.col;
	}

	// metodo que imprime a matriz expandida formada pela combinacao da matriz que 
	// chama o metodo com a matriz "agregada" recebida como parametro. Ou seja, cada 
	// linha da matriz impressa possui as entradas da linha correspondente da matriz 
	// que chama o metodo, seguida das entradas da linha correspondente em "agregada".

	public void imprime(Matriz agregada){

		for(int i = 0; i < lin; i++){

			for(int j = 0; j < col; j++){
	
				System.out.printf("%7.2f ", m[i][j]);
			}

			System.out.print(" |");

			for(int j = 0; j < agregada.col; j++){
	
				System.out.printf("%7.2f ", agregada.m[i][j]);
			}

			System.out.println();
		}
	}
	
	// metodo que troca as linhas i1 e i2 de lugar.
	// voltar pra private viu?
	private void trocaLinha(int i1, int i2){
		
		Matriz duplicate_matriz = new Matriz(this.getLinesLen(), this.getColumnsLen());

		for (int i = 0; i < this.getLinesLen(); i++) {
            for (int j = 0; j < this.getColumnsLen(); j++) {
                duplicate_matriz.set(i, j, this.get(i, j));
            }
        }

        for (int j = 0; j < this.getColumnsLen(); j++) {
            this.set(i1, j, duplicate_matriz.get(i2, j));
            this.set(i2, j, duplicate_matriz.get(i1, j));
        }
	
	}

	// metodo que multiplica as entradas da linha i pelo escalar k

	private void multiplicaLinha(int i, double k){

		for (int j = 0; j < this.getColumnsLen(); j++) {
			this.set(i, j, this.get(i, j)*k);
		}
        
	}

	// metodo que faz a seguinte combinacao de duas linhas da matriz:
	//	
	// 	(linha i1) = (linha i1) + (linha i2 * k)
	//
	private void combinaLinhas(int i1, int i2, double k){

		for (int j = 0; j < this.getColumnsLen(); j++) {
            this.set(i1, j, this.get(i1, j)+this.get(i2, j)*k);
        }
	}

	// metodo que procura, a partir da linha ini, a linha com uma entrada nao nula que
	// esteja o mais a esquerda possivel dentre todas as linhas. Os indices da linha e da 
	// coluna referentes a entrada nao nula encontrada sao devolvidos como retorno do metodo.
	// Este metodo ja esta pronto para voces usarem na implementacao da eliminacao gaussiana
	// e eleminacao de Gauss-Jordan.

	private int [] encontraLinhaPivo(int ini){

		int pivo_col, pivo_lin;

		pivo_lin = lin;
		pivo_col = col;

		for(int i = ini; i < lin; i++){
		
			int j;
			
			for(j = 0; j < col; j++) if(Math.abs(m[i][j]) > 0) break;

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

		int system_dimension = this.getLinesLen();

		for(int i = 0; i < system_dimension; i++){
			int linhaPivo = i;
			int pivo[] = encontraLinhaPivo(i);


			if (pivo[0] != linhaPivo) {
				this.trocaLinha(i, pivo[0]);
				agregada.trocaLinha(i, pivo[0]);
			}
			for(int j = i+1; j < system_dimension; j++){
				double multiplier = this.get(j,i)/this.get(i,i);
				for(int n = i; n < system_dimension; n++){ 
					this.set(j,n,this.get(j,n)-this.get(i,n)*multiplier);
				}
				agregada.set(j,0,this.get(j,0)-(this.get(i,0)*multiplier));
			}
		}

		return 0.0;
	}

	// metodo que implementa a eliminacao de Gauss-Jordan, que coloca a matriz (que chama o metodo)
	// na forma escalonada reduzida. As operacoes realizadas para colocar a matriz na forma escalonada 
	// reduzida tambem devem ser aplicadas na matriz "agregada" caso esta seja nao nula. Assumimos que
	// a matriz que invoca esta metodo eh uma matriz quadrada. Não se pode assumir, contudo, que esta
	// matriz ja esteja na forma escalonada (mas voce pode usar o metodo acima para isso).

	public void formaEscalonadaReduzida(Matriz agregada){

        int coluna = 0;
        for (int linha=0; linha<this.m.length; linha++) {
            
            while (this.m[linha][coluna]==0.0){

                boolean trocou = false;
                int i=linha;
                while (!trocou && i<this.m.length) {
                    if(this.m[i][coluna]!=0.0){
                        double[] temp = this.m[i];
                        this.m[i]=this.m[linha];
                        this.m[linha]=temp;
						double temp_r = agregada.m[i][0];
						agregada.m[i][0] = agregada.m[linha][0];
						agregada.m[linha][0] = temp_r;
                        trocou = true;
                    }
                    i++;
                }

				if(linha+1 == this.m.length){
					break;
				}
                
                if (this.m[linha][coluna]==0.0) {
                    coluna++;
                }
            }
            
            if(this.m[linha][coluna]!=1.0) {
                double divisor = this.m[linha][coluna];
                for (int i=coluna; i<this.m[linha].length; i++) {
                    this.m[linha][i] = this.m[linha][i]/divisor;
                }
				agregada.m[linha][0] = agregada.m[linha][0]/divisor;
            }
            
            for (int i=0; i<this.m.length; i++) {
                if (i!=linha && this.m[i][coluna]!=0) {
                    double multiple = 0-this.m[i][coluna];
                    for (int j=coluna; j<this.m[linha].length; j++){
                        this.m[i][j] +=
                            multiple*this.m[linha][j];
                    }
					agregada.m[i][0] += multiple*agregada.m[linha][0];

                }
            }
            coluna++;
        }

    }

	public double determinante(Matriz matriz) {

        if (matriz.getLinesLen() == 1) {
            return matriz.get(0, 0);
        }
        if (matriz.getLinesLen() == 2) {

			return (matriz.get(0, 0) * (matriz.get(1, 1))) - ((matriz.get(0, 1) * (matriz.get(1, 0))));
        }
        double sum = 0;
        for (int i = 0; i < matriz.getLinesLen(); i++) {
            sum = sum + (mudaSinal(i) * (matriz.get(0, i) * (determinante(criaSubMatriz(matriz, 0, i)))));
        }
        return sum;
    }

	public static Matriz criaSubMatriz(Matriz matriz, int exclui_linha, int exclui_col) {
        Matriz mat = new Matriz(matriz.getLinesLen() - 1, matriz.getLinesLen() - 1);
        int r = -1;
        for (int i = 0; i < matriz.getLinesLen(); i++) {
            if (i == exclui_linha) {
                continue;
            }
            r++;
            int c = -1;
            for (int j = 0; j < matriz.getLinesLen(); j++) {
                if (j == exclui_col) {
                    continue;
                }
                mat.set(r, ++c, matriz.get(i, j));
            }
        }
        return mat;
    }

	private static double mudaSinal(int i) {
        if (i % 2 == 0) {
            return 1.0; 
        } else {
            return -1.0;
        }
    }

}

// Classe "executavel".
