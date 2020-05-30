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

public class ArvoreDePesquisa {
    public static No raiz;

    public ArvoreDePesquisa() {
        this.raiz = null;
    }

    public boolean buscar(int id) {
        No atual = raiz;
        while (atual != null) {
            if (atual.getChave() == id) {
                return true;
            } else if (atual.getChave() > id) {
                atual = atual.getEsquerdo();
            } else {
                atual = atual.getDireito();
            }
        }
        return false;
    }

    public void delete(int id) {
        No pai = raiz;
        No atual = raiz;
        boolean ehFilhoEsquerdo = false;
        while (atual.getChave() != id) {
            pai = atual;
            if (atual.getChave() > id) {
                ehFilhoEsquerdo = true;
                atual = atual.getEsquerdo();
            } else {
                ehFilhoEsquerdo = false;
                atual = atual.getDireito();
            }
            if (atual == null) {
                return;
            }
        }
        if (atual.getEsquerdo() == null && atual.getDireito() == null) {
            if (atual == raiz) {
                raiz = null;
            }
            if (ehFilhoEsquerdo == true) {
                pai.setEsquerdo(null);
            } else {
                pai.setDireito(null);
            }
        } 
        else if (atual.getDireito() == null) {
            if (atual == raiz) {
                raiz = atual.getEsquerdo();
            } else if (ehFilhoEsquerdo) {
                pai.setEsquerdo(atual.getEsquerdo());
            } else {
                pai.setDireito(atual.getEsquerdo());
            }
        } else if (atual.getEsquerdo() == null) {
            if (atual == raiz) {
                raiz = atual.getDireito();
            } else if (ehFilhoEsquerdo) {
                pai.setEsquerdo(atual.getDireito());
            } else {
                pai.setDireito(atual.getDireito());
            }
        } else if (atual.getEsquerdo() != null && atual.getDireito() != null) {

            No sucessor = getSucessor(atual);
            if (atual == raiz) {
                raiz = sucessor;
            } else if (ehFilhoEsquerdo) {
                pai.setEsquerdo(sucessor);
                sucessor.setPai(pai);
            } else {
                pai.setDireito(sucessor);
                sucessor.setPai(pai);
            }
            sucessor.setEsquerdo(atual.getEsquerdo());
        }
        return;
    }

    public No getSucessor(No deleteNo) {
        No sucessor = null;
        No sucessorPai = null;
        No atual = deleteNo.getDireito();
        while (atual != null) {
            sucessorPai = sucessor;
            sucessor = atual;
            atual = atual.getEsquerdo();
        }        
        if (sucessor != deleteNo.getDireito()) {
            sucessorPai.setEsquerdo(sucessor.getDireito());
            if (sucessorPai.getEsquerdo() != null) sucessorPai.getEsquerdo().setPai(sucessorPai);
            sucessor.setDireito(deleteNo.getDireito());
            if (sucessor.getDireito() != null) sucessor.getDireito().setPai(sucessor);
        } else {
            sucessor.getPai().setDireito(null);
        }
        return sucessor;
    }

    public void inserir(int id) {
        No newNo = new No(id);
        if (raiz == null) {
            raiz = newNo;
            return;
        }
        No atual = raiz;
        No pai = null;
        while (true) {
            pai = atual;
            if (id < atual.getChave()) {
                atual = atual.getEsquerdo();
                if (atual == null) {
                    pai.setEsquerdo(newNo);
                    return;
                }
            } else {
                atual = atual.getDireito();
                if (atual == null) {
                    pai.setDireito(newNo);
                    return;
                }
            }
        }
    }

    public static void imprimir(No raiz) {
        if (raiz != null) {
            imprimir(raiz.getEsquerdo());
            System.out.print(raiz.getChave());
            imprimir(raiz.getDireito());
        }
    }
}
