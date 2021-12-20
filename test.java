import EP1.Matriz;

public class test {

    public static void main(String[] args) {

        Integer lin = 3;
        Integer col = 3;
        
        Matriz test_matriz = new Matriz(lin, col);
            
        for (int i = 0; i < lin; i++) {
            for (int j = 0; j < col; j++) {
                test_matriz.setValorDeElemento(i, j, i+j+1);
            }
        }

        test_matriz.setValorDeElemento(0,0,1);
        test_matriz.setValorDeElemento(0,1,2);
        test_matriz.setValorDeElemento(0,2,1);
        test_matriz.setValorDeElemento(1,0,1);
        test_matriz.setValorDeElemento(1,1,3);
        test_matriz.setValorDeElemento(1,2,2);
        test_matriz.setValorDeElemento(2,0,1);
        test_matriz.setValorDeElemento(2,1,4);
        test_matriz.setValorDeElemento(2,2,2);

        // 1 2 1
        // 1 3 2
        // 1 4 2
        
        // test_matriz.imprime();

        // test_matriz.imprime();
        // test_matriz.combinaLinhas(0,1,10.0);
        
        // int linesToSwitch[] = {0, 1};
        // for (int j = 0; j < test_matriz.getColumnsLen(); j++) {
        //     test_matriz.set(linesToSwitch[0], j, duplicate_matriz.get(linesToSwitch[1], j));
        //     test_matriz.set(linesToSwitch[1], j, duplicate_matriz.get(linesToSwitch[0], j));
        // }
    
        System.out.println(test_matriz.determinante(test_matriz));

        // test_matriz.imprime();
    }

    public static void teste() {
        Matriz test_matriz = new Matriz(2, 2);
        Matriz id = Matriz.identidade(1);
        id.imprimirMatriz();
    }
    
}
