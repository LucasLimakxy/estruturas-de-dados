/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;

/**
 *
 * @author Lucas de Lima Rodrigues
 */
public class Pilha {
    private int tamanho;
    private Object[] pilha;
    private int constant;

    public Pilha() throws Exception {
        this(0);
    }
    
    public Pilha(int constant) throws Exception {
        if (constant < 0) {
            System.out.println("Constant parameter should be greater than 0.");            
        }
        this.constant = constant;
        this.tamanho = 0;
        this.pilha = new Object[20];
    }
     
    public Object pop() throws Exception {
        if (this.isEmpty()) {
            return null;
        }
        Object o = this.pilha[--tamanho];
        this.pilha[tamanho] = null;
        return o;
    }

    public void push(Object o) {
        if (tamanho == this.pilha.length) {
            if (this.constant == 0) {
                this.duplicate();
            }
            else {
                this.expand();
            }
        }
        this.pilha[tamanho++] = o;
    }

    public Object top() {
        if (this.isEmpty()) {
            return null;
        }
        return this.pilha[tamanho-1];
    }

    public int tamanho() {
        return this.tamanho;
    }

    public boolean isEmpty() {
        return this.tamanho == 0;
    }
    
    private void expand() {
        this.pilha = this.makePilha(this.pilha, this.tamanho + this.constant);
    }
    
    private void duplicate() {
        this.pilha = this.makePilha(this.pilha, this.tamanho * 2);
    }
    
    private Object[] makePilha(Object[] pilha, int newSize) {
        Object[] s = new Object[newSize];
        for (int i = 0; i < pilha.length; i++) {
            s[i] = pilha[i];
        }
        return s;
    }
    
    public void Print(){
        if(this.isEmpty()){
            System.out.println("A pilha está vazia.");
        }
        for (int i = this.tamanho -1; i >= 0; i--) {
            System.out.println(this.pilha[i]+"\n___");            
        }
    }
}