package quicksort;

/**
 *
 * @author Lucas de Lima Rodrigues
 */
public class Quicksort {
    public static void quickSort(int v[], int esquerda, int direita) {
        int esq = esquerda, dir = direita, pivo = v[(esq + dir) / 2], aux;
        
        while (esq <= dir) {
            while (v[esq] < pivo) {
                esq++;
            }
            while (v[dir] > pivo) {
                dir--;
            }            
            if (esq <= dir) {
                aux = v[esq];
                v[esq] = v[dir];
                v[dir] = aux;
                esq++;
                dir--;
            }
        }
        if (dir > esquerda)
            quickSort(v, esquerda, dir);
        if (esq < direita)
            quickSort(v, esq, direita);
    }
    public static void main(String args[]) {
        //Vetor com valores arbitrários
        int vetor[] = { 80, 25, 15, 75, 76, 3, 4, 6, 8, 1, 30 };        
        quickSort(vetor, 0, vetor.length - 1);
        //Imprimindo o vetor já ordenado
        for (int i = 0; i < vetor.length; i++) {
                System.out.print("|" + vetor[i]);
        }
    }    
}