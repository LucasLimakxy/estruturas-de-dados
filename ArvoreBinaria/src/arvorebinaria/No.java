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
public class No {
    private No pai;
    private No esquerda;
    private No direita;
    private int valor;

    public No(int valor) {
        this.valor = valor;
    }
    /**
     * @return the value
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param value the value to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the esquerda
     */
    public No getEsquerda() {
        return esquerda;
    }

    /**
     * @param esquerda the esquerda to set
     */
    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * @return the direita
     */
    public No getDireita() {
        return direita;
    }

    /**
     * @param direita the direita to set
     */
    public void setDireita(No direita) {
        this.direita = direita;
    }

    /**
     * @return the pai
     */
    public No getPai() {
        return pai;
    }

    /**
     * @param pai the pai to set
     */
    public void setPai(No pai) {
        this.pai = pai;
    }
}
