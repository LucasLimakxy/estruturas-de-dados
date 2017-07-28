/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertsort;

/**
 *
 * @author Lucas de Lima Rodrigues
 */
public class InsertSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] vetor = {5,3,1,4,2,6,7,8,10,9};
        vetor = insertSort(vetor);
        //Imprime o Array ordenado
        for (int i = 0; i < vetor.length; i++){
            System.out.print("|"+vetor[i]+"|");
        }
    }
    
    public static int[] insertSort(int[] vetor) {
        int j;
        int key;
        int i;
        //Percorre o Array
        for (j = 1; j < vetor.length; j++){
            //Guarda o valor atual de j
            key = vetor[j];            
            //Percorre os elementos anteriores à j até 0, enquanto os valores forem menor que vetor[j]
            for (i = j - 1; (i >= 0) && (vetor[i] > key); i--){                
                vetor[i + 1] = vetor[i];
            }
            vetor[i + 1] = key;
        }
        return vetor;
    }    
}