/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubronegra;

/**
 *
 * @author lucas
 */
public class Main {
    public static void main(String[] args) {
        RubroNegra b = new RubroNegra();
        b.inserir(10);
        b.inserir(20);
        b.inserir(30);
        b.inserir(40);
        b.inserir(50);
        b.inserir(60);
        b.inserir(70);
        b.inserir(80);
        b.delete(30);
        b.delete(20);
        b.delete(10);
//        b.imprimir(b.raiz);
        b.gerarHtml(b.raiz, "rubronegra.html");
    }
}

