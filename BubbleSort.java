/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblesort;

/**
 *
 * @author Lucas de Lima Rodriguess
 */
public class BubbleSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] vetor = {5,3,1,4,2,6,7,8,10,9};
        vetor = bubbleSort(vetor);
        //Imprime o Array ordenado
        for (int i = 0; i < vetor.length; i++){
            System.out.print("|"+vetor[i]+"|");
        }
    }
   
    private static int[] bubbleSort(int vetor[]){
        boolean troca = true;
        int aux;
        while (troca) {
            troca = false;
            for (int i = 0; i < vetor.length - 1; i++) {
                if (vetor[i] > vetor[i + 1]) {
                    aux = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = aux;
                    troca = true;
                }
            }
        }
        return vetor;
    }    
}