/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucas
 */
public class Aresta {
    private Vertice inicio;
    private Vertice fim;
    private Object relacao;
    private Object relacao_inversa;
    
    public Aresta(Vertice a, Vertice b, Object relacao) {
        this.inicio = a;
        this.fim = b;
        this.relacao = relacao;
    }

    public Vertice getInicio() {
        return inicio;
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public Vertice getFim() {
        return fim;
    }

    public void setFim(Vertice fim) {
        this.fim = fim;
    }

    public Object getRelacao() {
        return relacao;
    }

    public void setRelacao(Object relacao) {
        this.relacao = relacao;
    }

    /**
     * @return the relacao_inversa
     */
    public Object getRelacao_inversa() {
        return relacao_inversa;
    }

    /**
     * @param relacao_inversa the relacao_inversa to set
     */
    public void setRelacao_inversa(Object relacao_inversa) {
        this.relacao_inversa = relacao_inversa;
    }
}
