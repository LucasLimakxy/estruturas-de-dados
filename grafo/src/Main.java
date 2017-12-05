
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucas
 */
public class Main {
    public static void main(String[] args) {
        Grafo g = new Grafo();
        
        Scanner scanner = new Scanner(System.in);
        int op = 1;
        
        //Menu
        while(op != 0) {
            System.out.println("Insira sua opção:");
            System.out.println("1 - Inserir Vertice");
            System.out.println("2 - Inserir Aresta");
            System.out.println("3 - Remover Vertice");
            System.out.println("4 - Remover Aresta");
            System.out.println("5 - Imprimir Grafo");
            System.out.println("0 - Sair");
            
             op = scanner.nextInt();
             
             switch(op) {
                case 1:
                     System.out.print("Digite um valor para o Vertice:");
                     Object val = scanner.next();
                     Vertice v_insere = g.inserirVertice(val);
                     
                     if(v_insere.getValor().equals(-1)) {
                         System.out.println("\nO vertice digitado já existe!\n");
                         break;
                     }
                     
                     System.out.println(g.Imprimir());
                     break;
                case 2:
                     System.out.print("Digite o valor de um vertice existente:");
                     val = scanner.next();
                     Vertice v1 = g.pesquisarVertice(val);
                     
                     if(v1.getValor().equals(-1)) {
                         System.out.println("\nO vertice digitado não existe!\n");
                         break;
                     }
                     
                     System.out.println("Digite o valor de um segundo vertice existente:");
                     val = scanner.next();
                     Vertice v2 = g.pesquisarVertice(val);
                     
                     if(v2.getValor().equals(-1)) {
                         System.out.println("\nO vertice digitado não existe!\n");
                         break;
                     }
                     
                     g.inserirAresta(v1, v2, v1.getValor().toString() + v2.getValor().toString());
                     
                     System.out.println(g.Imprimir());
                     
                     break;
                case 3:
                    System.out.print("Digite o vertice:");
                    val = scanner.next();
                    Vertice v_excluir = g.pesquisarVertice(val);
                    
                    if(v_excluir.getValor().equals(-1)) {
                        System.out.println("\nO vertice digitado não existe!\n");
                        break;
                    }
                    g.removerVertice(v_excluir);
                    System.out.println(g.Imprimir());
                    
                    break;
                case 4:
                     System.out.print("DDigite o valor de um vertice existente:");
                     val = scanner.next();
                     Vertice v3 = g.pesquisarVertice(val);
                     
                     if(v3.getValor().equals(-1)) {
                         System.out.println("\nO vertice digitado não existe!\n");
                         break;
                     }
                     
                     System.out.println("Digite o valor de um segundo vertice existente:");
                     val = scanner.next();
                     Vertice v4 = g.pesquisarVertice(val);
                     
                     if(v4.getValor().equals(-1)) {
                         System.out.println("\nO vertice digitado não existe!\n");
                         break;
                     }
                     Object relacao = v3.getValor().toString() + v4.getValor().toString();
                     Object relacao_inversa = v4.getValor().toString() + v3.getValor().toString();
                     
                     Aresta a = g.pesquisarAresta(relacao, relacao_inversa);
                     g.removerAresta(a);
                     System.out.println(g.Imprimir());
                     
                     break;
                case 5:
                     System.out.println(g.Imprimir());
                     break;
             }
        }        
    }    
}
