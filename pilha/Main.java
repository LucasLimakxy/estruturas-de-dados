/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;

import java.util.Scanner;

/**
 *
 * @author Lucas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        Pilha s = new Pilha();
        Scanner scanner = new Scanner(System.in);
        int op = 1;
        
        //Menu
        while(op != 0){
            System.out.println("Insira sua opção:");
            System.out.println("1 - Push");
            System.out.println("2 - Pop");
            System.out.println("3 - Top");
            System.out.println("4 - Print");
            System.out.println("5 - Size");
            System.out.println("0 - Sair");
            
             op = scanner.nextInt();
             
             switch(op){
                 case 1:
                     System.out.print("Digite um valor:");
                     int val = scanner.nextInt();
                     s.push(val);
                     break;
                 case 2:
                     if(!s.isEmpty()){
                        System.out.println("Elemento removido: "+s.pop());
                     }else{
                        System.out.println("A pilha está vazia");
                     }
                     break;
                 case 3:
                     if(!s.isEmpty()){
                        System.out.println("Topo: "+s.top());
                     }else{
                        System.out.println("A pilha está vazia");
                     }
                     break;
                 case 4:
                     s.Print();
                     break;
                 case 5:
                     System.out.println("Tamanho: "+s.tamanho());;
                     break;
                 case 0:                     
                     System.exit(0);                     
                 default:
                     System.out.println("Valor inválido");
             }
        }       
        
    }
    
}
