/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int op = 1;
        
        AVL arvore = new AVL();        
        
        do { 
            System.out.println("1 - Inserir");
            System.out.println("2 - Remover");
            System.out.println("3 - Exibir árvore");
            System.out.println("4 - Limpar Árvore;");
            System.out.println("5 - Altura");
            System.out.println("0 - Sair:");
            System.out.print("\nDigite algum comando: ");
            
            op = scanner.nextInt();

            switch (op) {
                
                case 1:         
                    System.out.print("Digite a chave: ");
                    int chave = scanner.nextInt();
                    arvore.inserir(chave);
                    break;                    
                case 2:
                    System.out.print("Digite a chave: ");
                    int chave2 = scanner.nextInt();
                    arvore.remover(chave2);
                    break;
                case 3:
                    System.out.println(arvore);                    
                    break;                   
                case 4:
                    arvore = new AVL();
                    break;
                case 5:
                    System.out.println("Altura:" + arvore.getAltura());
                    break;
                default:
                    System.out.println("Comando inválido!");
                    break;
            }

        } while (op != 0);
    }
}
