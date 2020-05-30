/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class AVL {
    
    public static int INSERCAO = 1;
    public static int REMOCAO = -1;
    
    private No raiz;
    private int tamanho;
    
    public No getRaiz() {
        return raiz;
    }
    
    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

    public AVL() {
        this.tamanho = 0;
    }

    public boolean EVazia() {
        return this.getRaiz() == null;
    }
    
    public No pesquisar(int chave) {
        if (this.getRaiz() == null) {
            return this.getRaiz();
        }
        return pesquisar(chave, this.getRaiz());
    }

    private No pesquisar(int chave, No no) {
        if (no.EExterno()) {
            return no;
        }
        if (chave < no.getChave()) {
            return no.getEsquerdo() != null ? this.pesquisar(chave, no.getEsquerdo()) : no;                
        } else if (chave == no.getChave()) {
            return no;
        }
        else if (chave > no.getChave()) {
            return no.getDireito() != null ? this.pesquisar(chave, no.getDireito()) : no;
        }
        return null;
    }
    
    public No remover(int chave) {
        if (this.getRaiz() == null) {
            return null;
        }
        return this.remover(chave, this.getRaiz());
    }

    public No remover(int chave, No no) {
        if (no == null) {
            return null;
        }
        if (chave < no.getChave()) {
            return this.remover(chave, no.getEsquerdo());
        } else if (chave > no.getChave()) {
            return remover(chave, no.getDireito());
        } else {
            // Se tem os dois filhos
            if (no.getEsquerdo() != null && no.getDireito() != null) {
                No retorno = no;
                No novo = no.getDireito();

                while (novo.getEsquerdo() != null && no.getDireito() != null) {
                    novo = novo.getEsquerdo();
                }
                novo = remover(novo.getChave(), no);
                no.setChave(novo.getChave());
                
                return retorno;
            //Se tem apenas o filho esquerdo    
            } else if (no.getEsquerdo() != null) {
                No removido = no;
                atualizarFatorBalanceamento(no, REMOCAO);

                if (no.getPai() == null) {
                    this.setRaiz(no.getEsquerdo());
                } else {
                    if (no.getPai().getChave() < no.getChave()) {
                        no.getPai().setDireito(no.getEsquerdo());
                    }
                    else {
                        no.getPai().setEsquerdo(no.getEsquerdo());
                    }
                }
                no.getEsquerdo().setPai(no.getPai());

                return removido;
            //Se tem apenas o filho direito
            } else if (no.getDireito() != null) {
                No removido = no;
                atualizarFatorBalanceamento(no, REMOCAO);
                if (no.getPai() == null) {
                    this.setRaiz(no.getDireito());
                }
                else {
                    if (no.getPai().getChave() < no.getChave()) {
                        no.getPai().setDireito(no.getDireito());
                    }
                    else {
                        no.getPai().setEsquerdo(no.getDireito());
                    }
                }
                no.getDireito().setPai(no.getPai());

                return removido;
            //Se não tem filho
            } else {
                No pai = no.getPai();

                if (pai == null) {
                    this.setRaiz(null);
                    return no;
                }
                atualizarFatorBalanceamento(no, REMOCAO);
                no.setPai(null);
                if (no.getChave() > pai.getChave()) {
                    pai.setDireito(null);
                }
                else {
                    pai.setEsquerdo(null);
                }
                return no;
            }
        }
    }

    public void atualizarFatorBalanceamento(No no, int operacao) {
        No pai = no.getPai();
        if (no == null || pai == null) return;
        
        if (operacao == AVL.INSERCAO) {
            
            if (no.getChave() > pai.getChave()) {
                pai.setFatorBalanceamento(pai.getFatorBalanceamento() - 1);
            } else {
                pai.setFatorBalanceamento(pai.getFatorBalanceamento() + 1);
            }
            
            if (pai.getFatorBalanceamento() != 0) {
                if (pai.getFatorBalanceamento() > 1 || pai.getFatorBalanceamento() < -1) {
                    balancearArvore(pai);
                } else {
                    atualizarFatorBalanceamento(pai, operacao);
                }
            } 
        } else if (operacao == AVL.REMOCAO) {
            if (no.getChave() > pai.getChave()) {
                pai.setFatorBalanceamento(pai.getFatorBalanceamento() + 1);
            } else {
                pai.setFatorBalanceamento(pai.getFatorBalanceamento() - 1);
            }
            
            if ((pai.getFatorBalanceamento() == -2) || pai.getFatorBalanceamento() == 2 ) {
                balancearArvore(pai);
                atualizarFatorBalanceamento(pai.getPai(), operacao);
            } else if (pai.getFatorBalanceamento() == 0) {
                atualizarFatorBalanceamento(pai, operacao);
            }
        }
    }

    public void rotacaoEsquerdaSimples(int chave) {
        No no = this.pesquisar(chave);
        if (no != null) rotacaoEsquerdaSimples(no);
    }
    
    private No rotacaoEsquerdaSimples(No no) {
        No direito = no.getDireito();
        No pai = no.getPai();
        No novoDireito = direito.getEsquerdo();
        
        if (pai != null) {
            if (no.getChave() > pai.getChave())
                pai.setDireito(direito);
            else
                pai.setEsquerdo(direito);
        } else {
            this.setRaiz(direito);
        }
        direito.setPai(pai);
        direito.setEsquerdo(no);
        
        no.setPai(direito);
        no.setDireito(novoDireito);

        if (novoDireito != null) {
            novoDireito.setPai(no);
        }
        
        no.setFatorBalanceamento(no.getFatorBalanceamento() + 1 - Math.min(direito.getFatorBalanceamento(), 0));
        direito.setFatorBalanceamento(direito.getFatorBalanceamento() + 1 + Math.max(no.getFatorBalanceamento(), 0));

        return no;        
    }
    
    public void rotacaoDireitaSimples(int chave) {
        No no = this.pesquisar(chave);
        if (no != null) rotacaoDireitaSimples(no);
    }
    
    private No rotacaoDireitaSimples(No no) {
        No esquerdo = no.getEsquerdo();
        No pai = no.getPai();
        No novoEsquerdo = esquerdo.getDireito();
        
        if (pai != null) {
            if (no.getChave() > pai.getChave())
                pai.setDireito(esquerdo);
            else
                pai.setEsquerdo(esquerdo);
        } else {
            this.setRaiz(esquerdo);
        }
        esquerdo.setPai(pai);
        esquerdo.setDireito(no);
        
        no.setPai(esquerdo);
        no.setEsquerdo(novoEsquerdo);

        if (novoEsquerdo != null) {
            novoEsquerdo.setPai(no);
        }
        
        no.setFatorBalanceamento(no.getFatorBalanceamento() - 1 - Math.max(esquerdo.getFatorBalanceamento(), 0));
        esquerdo.setFatorBalanceamento(esquerdo.getFatorBalanceamento() - 1 + Math.min(no.getFatorBalanceamento(), 0));

        return no;
    }
    
    public void rotacaoEsquerdaDupla(int chave) {
        No no = this.pesquisar(chave);
        if (no != null) rotacaoEsquerdaDupla(no);
    }
    
    private No rotacaoEsquerdaDupla(No no) {   
        No b = no.getDireito();
        
        this.rotacaoDireitaSimples(b);
        this.rotacaoEsquerdaSimples(no);
        
        return no;
    }
    
    public void rotacaoDireitaDupla(int chave) {
        No no = this.pesquisar(chave);
        if (no != null) rotacaoDireitaDupla(no);
    }
    
    private No rotacaoDireitaDupla(No no) {
        No b = no.getEsquerdo();
        
        this.rotacaoEsquerdaSimples(b);
        this.rotacaoDireitaSimples(no);
        
        return no;
    }
    
    private void balancearArvore(No no) {
        
        int fator = no.getFatorBalanceamento();
        
        switch (fator) {
            case 2:
                if (no.getEsquerdo() != null && no.getEsquerdo().getFatorBalanceamento() < 0) {
                    this.rotacaoDireitaDupla(no);
                } else {
                    this.rotacaoDireitaSimples(no);
                }
            break;
            case -2:
                if (no.getDireito()!= null && no.getDireito().getFatorBalanceamento() > 0) {
                    this.rotacaoEsquerdaDupla(no);
                } else {
                    this.rotacaoEsquerdaSimples(no);
                }
            break;                    
        }
    }
    
    public void inserir(int chave) {
        this.inserir(chave, null);
    }
    
    public void inserir(int chave, Object valor) {
        this.tamanho++;
        No novoNo = new No(chave, valor);

        if (this.getRaiz() == null) {
            this.setRaiz(novoNo);
            return;
        }

        No no = this.pesquisar(chave);
        
        System.out.println("O nó pai é:" + no.getChave());

        if (no.getChave() != chave) {
            novoNo.setPai(no);

            if (chave > no.getChave()) {
                no.setDireito(novoNo);
            } else {
                no.setEsquerdo(novoNo);
            }
            atualizarFatorBalanceamento(novoNo, INSERCAO);
        }
    }
    
    public List<No> getFilhos() {
        List<No> filhos = new ArrayList<No>();

        this.getFilhos(filhos, this.getRaiz());

        return filhos;
    }

    private void getFilhos(List<No> filhos, No no) {
        if (no != null) {
            if (no.EInterno()) {
                this.getFilhos(filhos, no.getEsquerdo());
            }
            filhos.add(no);
            if (no.EInterno()) {
                this.getFilhos(filhos, no.getDireito());
            }
        }
    }

    public int getAltura() {
        return this.getRaiz() != null ? this.getRaiz().getAltura() : 0;
    }

    @Override
    public String toString() {
        String retorno = new String("\nAVL: ");
        retorno += "\n\n";
        
        if (this.getRaiz() == null) {
            retorno += "A árvore está vazia!\n";
        } else {
            for (int i = 0; i <= this.getAltura(); i++) {
                for (int j = 0; j <= this.getFilhos().size() + 1; j++) {
                    boolean ok = false;
                    int index = 0;

                    for (No filho : this.getFilhos()) {
                        if (filho.getProfundidade() == i && index + 1 == j) {
                            retorno += String.format("%03d[%d]", filho.getChave(), filho.getFatorBalanceamento());
                            ok = true;
                            break;
                        }
                        index++;
                    }

                    if (ok) continue;

                    retorno += "------";
                }
                retorno += "\n";
            }
        }
        retorno += "\n";
        
        return retorno;
    }
}