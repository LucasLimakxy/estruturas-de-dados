
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
public class No {
    
    private No pai;
    private No esquerdo;
    private No direito;
    private int chave;
    private int valor;
    private int cor;


    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public No getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(No esquerdo) {
        this.esquerdo = esquerdo;
    }

    public No getDireito() {
        return direito;
    }

    public void setDireito(No direito) {
        this.direito = direito;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public No(int d){
        this.chave = d;
    }
    public No (int d, int cor) {
        this.chave = d;
        this.cor = cor;
    }
    public No (int d, int cor, No parent) {
        this.chave = d;
        this.pai = parent;
        this.cor = cor;
    }

    public boolean temDireito() {
        return this.getDireito() != null;
    }

    public boolean temEsquerdo() {
        return this.getEsquerdo() != null;
    }

    public boolean EInterno() {
        return this.temDireito()|| this.temEsquerdo();
    }

    public boolean EExterno() {
        return !EInterno();
    }

    public int getProfundidade() {
        return this.getPai() == null ? 0 : 1 + this.getPai().getProfundidade();
    }

    public int getAltura() {
        if (this.EExterno()) {
            return 0;
        }
        else {
            int altura = 0;
            int alturaDireito = 0;
            int alturaEsquerdo = 0;

            if (this.temDireito()) {
                alturaDireito = this.getDireito().getAltura();
            }
            if (this.temEsquerdo()) {
                alturaEsquerdo = this.getEsquerdo().getAltura();
            }

            altura = alturaDireito > alturaEsquerdo ? alturaDireito : alturaEsquerdo;
            return altura + 1;
        }
    }

    /**
     * @return the cor
     */
    public int getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(int cor) {
        this.cor = cor;
    }
}