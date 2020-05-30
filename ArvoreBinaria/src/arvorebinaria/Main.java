/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebinaria;

import java.util.Scanner;

/**
 *
 * @author lucas
 */
public class Main {
    public static void main(String[] args) {
        ArvoreBinaria g = new ArvoreBinaria();
        Scanner scanner = new Scanner(System.in);
        int op = 1;
        
        //Menu
        while(op != 0) {
            System.out.println("Insira sua opção:");
            System.out.println("1 - Inserir No");
            System.out.println("2 - Imprimir");
            System.out.println("3 - Busca");
            System.out.println("4 - Excluir");
            System.out.println("0 - Sair");
            
             op = scanner.nextInt();
             
             switch(op) {
                case 1:
                     System.out.print("Digite um valor para o No:");
                     int val = scanner.nextInt();
                     
                     if(g.getRaiz() == null) {
                         g.setRaiz(new No(val));
                     } else {
                         g.inserir(g.getRaiz(), val);
                     }    
                     
                     g.emordem(g.getRaiz());
                     System.out.println("\n");
                     break;
                case 2:
                     g.emordem(g.getRaiz());
                     System.out.println("\n");
                     break;  
                case 3: 
                    System.out.print("Digite um valor para a busca:");
                     int busca = scanner.nextInt();
                     
                     if(g.busca(g.getRaiz(), busca, false)) {
                         System.out.println("Sua busca foi encontrada:" + busca);
                     }else {
                        System.out.println("Sua busca não foi encontrada");
                     }
                     break;
                case 4: 
                    System.out.print("Digite um valor para a exclusão:");
                     int excluir = scanner.nextInt();
                     
                     g.excluir(g.getRaiz(), excluir);
                     
                     g.emordem(g.getRaiz());
                     System.out.println("\n");
                     
                     break;
             }
        }
    }
}
