/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebinaria;

/**
 *
 * @author lucas
 */
public class ArvoreBinaria {
    private No raiz;
    
    public void inserir(No node, int valor) {
       if (node != null) {
        if (valor < node.getValor()) {
            //continua a busca
            if (node.getEsquerda() != null) {
                inserir(node.getEsquerda(), valor);
            } else {
                System.out.println("  Inserindo " + valor + " a esquerda de " + node.getValor());
                No novo_no = new No(valor);
                node.setEsquerda(novo_no) ;
                novo_no.setPai(node);
            }
        } else if (valor > node.getValor()) {
            // continua a busca
            if (node.getDireita() != null) {
                inserir(node.getDireita(), valor);
            } else {
                System.out.println("  Inserindo " + valor + " a direita de " + node.getValor());
                No novo_no = new No(valor);
                node.setDireita(novo_no);
                novo_no.setPai(node);
            }
        }
      }
    }
    
    public boolean busca(No node, int busca, boolean loc) {
        if (node != null && loc == false) {
            if (node.getValor() == busca) {
                loc = true;
            } else if (busca < node.getValor()) {
                loc = busca(node.getEsquerda(), busca, loc);
            } else {
                loc = busca(node.getDireita(), busca, loc);
            }
        }
        return loc;
    }
    
    public No excluir(No node, int busca) {
        if (node.getPai() == null) {
            
        }     
    }
    
    public void emordem(No node) {
        if(node != null){            
            emordem(node.getEsquerda());
            System.out.print(node.getValor() + " ");
            emordem(node.getDireita());
        }
    }

    /**
     * @return the raiz
     */
    public No getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }
}
