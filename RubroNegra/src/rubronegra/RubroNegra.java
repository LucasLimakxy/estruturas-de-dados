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

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RubroNegra extends ArvoreDePesquisa {
    public No raiz;
    public Map<Integer, List<No>> niveisArvore;
    public final int DUPLO_NEGRO = -1;
    public final int NEGRO = 0;
    public final int RUBRO = 1;
    
    public RubroNegra() {
        this.raiz = null;
    }

    @Override
    public void inserir(int id) {
        // Seta a raiz vermelha
        No newNo = new No(id, RUBRO);
        if (raiz == null) {
            raiz = newNo;
            checarCor(newNo);
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
                    newNo.setPai(pai);
                    checarCor(newNo);
                    return;
                }
            } else {
                atual = atual.getDireito();
                if (atual == null) {
                    pai.setDireito(newNo);
                    newNo.setPai(pai);
                    checarCor(newNo);
                    return;
                }
            }
        }
    }
    
    @Override
    public void delete(int id) {
        No pai = raiz;
        No atual = raiz;
        No sucessor = null;
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
        if (atual.getCor() == NEGRO) atual.setCor(DUPLO_NEGRO);
        if (atual.getEsquerdo() == null && atual.getDireito() == null) {
            if (atual == raiz) {
                raiz = null;
            }
            if (ehFilhoEsquerdo == true) {
                if (atual.getCor() == DUPLO_NEGRO) {
                    checarDuploNegro(atual);
                } else pai.setEsquerdo(null);
            } else {
                if (atual.getCor() == DUPLO_NEGRO) {
                    checarDuploNegro(atual);
                } else pai.setDireito(null);
            }
            return;
        }
        else if (atual.getDireito() == null) {
            if (atual == raiz) {
                raiz = atual.getEsquerdo();
                raiz.setPai(null);
                raiz.setCor(NEGRO);
            } else if (ehFilhoEsquerdo) {
                pai.setEsquerdo(atual.getEsquerdo());
                sucessor = pai.getEsquerdo();
            } else {
                pai.setDireito(atual.getEsquerdo());
                sucessor = pai.getDireito();
            }
        } else if (atual.getEsquerdo() == null) {
            if (atual == raiz) {
                raiz = atual.getDireito();
                raiz.setPai(null);
                raiz.setCor(NEGRO);
            } else if (ehFilhoEsquerdo) {
                pai.setEsquerdo(atual.getDireito());
                sucessor = pai.getEsquerdo();
            } else {
                pai.setDireito(atual.getDireito());
                sucessor = pai.getDireito();
            }
        } else if (atual.getEsquerdo() != null && atual.getDireito() != null) {
            //now we have found the minimum element in the right sub tree
            sucessor = getSucessor(atual);
            if (atual == raiz) {
                raiz = sucessor;
            } else if (ehFilhoEsquerdo) {
                pai.setEsquerdo(sucessor);
            } else {
                pai.setDireito(sucessor);
            }
            sucessor.setEsquerdo(atual.getEsquerdo());
            if (sucessor.getEsquerdo() != null) sucessor.getEsquerdo().setPai(sucessor);
            sucessor.setDireito(atual.getDireito());
            if (sucessor.getDireito() != null) sucessor.getDireito().setPai(sucessor);
        }
       
        if (atual.getCor() == DUPLO_NEGRO && sucessor != null && sucessor.getCor() == RUBRO) {
             // CASO 2: Ne -> Ru
            sucessor.setCor(NEGRO);
            sucessor.setPai(atual.getPai());
            
        } else if (sucessor != null && sucessor.getCor() == NEGRO) {
            // CASO 4: Ru -> Ne
            if (atual.getCor() == RUBRO) sucessor.setCor(RUBRO);
            
            // CASO 3: Ne -> Ne
            No doubleBlackNo = new No(0);
            if (sucessor.getPai().getEsquerdo() == null) {
                sucessor.getPai().setEsquerdo(doubleBlackNo);
            } else if (sucessor.getPai().getDireito() == null) {
                sucessor.getPai().setDireito(doubleBlackNo);
            } else {
                sucessor.getPai().getDireito().setCor(NEGRO);
                sucessor.getPai().getEsquerdo().setCor(NEGRO);
                return;
            }
            doubleBlackNo.setCor(DUPLO_NEGRO);
            doubleBlackNo.setPai(sucessor.getPai());
            sucessor.setEsquerdo(atual.getEsquerdo());
            if (sucessor.getEsquerdo() != null) sucessor.getEsquerdo().setPai(sucessor);
            sucessor.setDireito(atual.getDireito());
            if (sucessor.getDireito() != null) sucessor.getDireito().setPai(sucessor);
            sucessor.setPai(atual.getPai());
            checarDuploNegro(doubleBlackNo);
        }
        // CASO 1: Ru -> Ru        
    }
    
    @Override
    public No getSucessor(No deleteNo) {
        No sucessor = null;
        No atual = deleteNo.getDireito();
        while (atual != null) {
            sucessor = atual;
            atual = atual.getEsquerdo();
        }
        //check if sucessor has the right child, it cannot have left child for sure
        //if it does have the right child, add it to the left of sucessorParent.
        if (sucessor != deleteNo.getDireito()) {
            sucessor.getPai().setEsquerdo(sucessor.getDireito());
            if (sucessor.getPai().getEsquerdo() != null) {
                sucessor.getPai().getEsquerdo().setPai(sucessor.getPai());
            }
        } else {
            if (sucessor.getPai() != null) {
                if (sucessor.getDireito() != null) {
                    sucessor.getPai().setDireito(sucessor.getDireito());
                    sucessor.getPai().getDireito().setCor(sucessor.getCor());
                    sucessor.getPai().getDireito().setPai(sucessor.getPai());
                } else {
                    sucessor.getPai().setDireito(null);
                }
            }
        }
        return sucessor;
    }
    
    private void checarDuploNegro (No atual) {
        No pai = atual.getPai();
        boolean ehFilhoEsquerdo = ehFilhoEsquerdo(atual);
        if (ehFilhoEsquerdo) {
            if (temDoisFilhos(pai) && pai.getCor() == NEGRO && pai.getDireito().getCor() == RUBRO) {
                // 3 CASO 1
                pai.getDireito().setCor(NEGRO);
                pai.setCor(RUBRO);
                simplesEsquerda(pai);
                checarDuploNegro(atual);
            } else if (temDoisFilhos(pai) && pai.getDireito().getCor() == NEGRO && temDoisFilhos(pai.getDireito()) &&
            pai.getDireito().getEsquerdo().getCor() == NEGRO && pai.getDireito().getDireito().getCor() == NEGRO) {
                // 3 CASO 2A
                atual.setCor(NEGRO);
                pai.getDireito().setCor(RUBRO);
                pai.setCor(DUPLO_NEGRO);
                checarDuploNegro(pai);
            } else if (temDoisFilhos(pai) && pai.getCor() == RUBRO && pai.getDireito().getCor() == NEGRO && 
            ((temDoisFilhos(pai.getDireito()) && pai.getDireito().getEsquerdo().getCor() == NEGRO && pai.getDireito().getDireito().getCor() == NEGRO ) || naoTemFilhos(pai.getDireito()))) {
                // 3 CASO 2B - TERMINAL
                pai.setCor(NEGRO);
                pai.getDireito().setCor(RUBRO);
                pai.setEsquerdo(null);
            } else if (temDoisFilhos(pai) && pai.getDireito().getCor() == NEGRO && ((temDoisFilhos(pai.getDireito()) &&
            pai.getDireito().getEsquerdo().getCor() == RUBRO && pai.getDireito().getDireito().getCor() == NEGRO)) || temFilhoEsquerdo(pai.getDireito()) && pai.getDireito().getEsquerdo().getCor() == RUBRO) {
                // 3 CASO 3
                pai.getDireito().getEsquerdo().setCor(NEGRO);
                pai.getDireito().setCor(RUBRO);
                pai.setEsquerdo(null);
                simplesDireita(pai.getDireito());
            } else if (temDoisFilhos(pai) && pai.getDireito().getCor() == NEGRO && pai.getDireito().getDireito() != null && 
            pai.getDireito().getDireito().getCor() == RUBRO) {
                // 3 CASO 4
                pai.getDireito().setCor(pai.getCor());
                pai.getDireito().getDireito().setCor(NEGRO);
                pai.setCor(NEGRO);
                pai.setEsquerdo(null);
                simplesEsquerda(pai);
            }
        } else {
            if (temDoisFilhos(pai) && pai.getEsquerdo().getCor() == RUBRO) {
                // 3 CASO 1
                pai.getEsquerdo().setCor(NEGRO);
                pai.setCor(RUBRO);
                simplesDireita(pai);
                checarDuploNegro(atual);
            } else if (temDoisFilhos(pai) && pai.getEsquerdo().getCor() == NEGRO && temDoisFilhos(pai.getEsquerdo()) &&
            pai.getEsquerdo().getDireito().getCor() == NEGRO && pai.getEsquerdo().getEsquerdo().getCor() == NEGRO) {
                // 3 CASO 2A
                atual.setCor(NEGRO);
                pai.getEsquerdo().setCor(RUBRO);
                pai.setCor(DUPLO_NEGRO);
                checarDuploNegro(pai);
            } else if (temDoisFilhos(pai) && pai.getCor() == RUBRO && pai.getEsquerdo().getCor() == NEGRO && 
            ((temDoisFilhos(pai.getEsquerdo()) && pai.getEsquerdo().getEsquerdo().getCor() == NEGRO && pai.getEsquerdo().getDireito().getCor() == NEGRO ) || naoTemFilhos(pai.getEsquerdo()))) {
                // 3 CASO 2B - TERMINAL
                pai.setCor(NEGRO);
                pai.getEsquerdo().setCor(RUBRO);
                pai.setDireito(null);
            } else if (temDoisFilhos(pai) && pai.getEsquerdo().getCor() == NEGRO && ((temDoisFilhos(pai.getEsquerdo()) &&
            pai.getEsquerdo().getDireito().getCor() == RUBRO && pai.getEsquerdo().getEsquerdo().getCor() == NEGRO) || temFilhoDireito(pai.getEsquerdo()) && pai.getEsquerdo().getDireito().getCor() == RUBRO)) {
                // 3 CASO 3
                pai.getEsquerdo().getDireito().setCor(NEGRO);
                pai.getEsquerdo().setCor(RUBRO);
                pai.setDireito(null);
                simplesEsquerda(pai.getDireito());
            } else if (temDoisFilhos(pai) && pai.getEsquerdo().getCor() == NEGRO && pai.getEsquerdo().getEsquerdo() != null && 
            pai.getEsquerdo().getEsquerdo().getCor() == RUBRO) {
                // 3 CASO 4
                pai.getEsquerdo().setCor(pai.getCor());
                pai.getEsquerdo().getEsquerdo().setCor(NEGRO);
                pai.setCor(NEGRO);
                pai.setDireito(pai.getDireito().getDireito());
                simplesDireita(pai);
            }
        }
    }
    
    // Rotação Simples a Direita(RSD)
    private No simplesDireita( No oldRoot )
    {
        No newRoot = oldRoot.getEsquerdo();
        if (oldRoot.getPai() != null) {
            if (ehFilhoEsquerdo(oldRoot)) oldRoot.getPai().setEsquerdo(newRoot); else oldRoot.getPai().setDireito(newRoot) ;
        } else {
            raiz = newRoot;
        }
        newRoot.setPai(oldRoot.getPai());
        
        oldRoot.setEsquerdo(newRoot.getDireito());
        if (oldRoot.getEsquerdo() != null) oldRoot.getEsquerdo().setPai(oldRoot);
        
        newRoot.setDireito(oldRoot);
        newRoot.getDireito().setPai(newRoot);
        
        return newRoot;
    }

    // Rotação Simples a Esquerda(RSE)
    private No simplesEsquerda( No oldRoot )
    {
        No newRoot = oldRoot.getDireito();
        if (oldRoot.getPai() != null) {
            if (ehFilhoEsquerdo(oldRoot)) oldRoot.getPai().setEsquerdo(newRoot); else oldRoot.getPai().setDireito(newRoot) ;
        } else {
            raiz = newRoot;
        }
        newRoot.setPai(oldRoot.getPai());
        
        oldRoot.setDireito(newRoot.getEsquerdo());
        if (oldRoot.getDireito() != null) oldRoot.getDireito().setPai(oldRoot);
        
        newRoot.setEsquerdo(oldRoot);
        newRoot.getEsquerdo().setPai(newRoot);
        return newRoot;
    }

    // Rotação Dupla a Direita(RDD)
    private No duplaDireita( No oldRoot )
    {
        oldRoot.setEsquerdo(simplesEsquerda( oldRoot.getEsquerdo() )) ;
        return simplesDireita( oldRoot );
    }

    // Rotação Dupla a Esquerda(RDE)
    private No duplaEsquerda( No oldRoot )
    {
        oldRoot.setDireito(simplesDireita( oldRoot.getDireito() ));
        return simplesEsquerda( oldRoot );
    }
    
    public void checarCor(No node) {
        while (node != null) {
            // pai é negro
            if (node.getPai() != null) {
                if (node.getPai().getCor() == 0 && node.getCor() == 1) {
                    // pai negro e filho rubro - ok
                    break;
                } else if (node.getPai().getCor() == 1) {
                    // pai é rubro e filho é rubro - not ok
                    No avo = node.getPai().getPai();
                    if (!ehFilhoEsquerdo(node.getPai()) && (avo.getEsquerdo() == null || (avo.getEsquerdo() != null && avo.getEsquerdo().getCor() == NEGRO))) {
                        if (!ehFilhoEsquerdo(node)) {
                            avo.setCor(1);
                            node.getPai().setCor(0);
                            simplesEsquerda(avo);
                        } else {
                            avo.setCor(1);
                            node.setCor(0);
                            duplaEsquerda(avo);
                        }
                    } else if (ehFilhoEsquerdo(node.getPai()) && (avo.getDireito() == null) || (avo.getDireito() != null && avo.getDireito().getCor() == NEGRO)) {
                        if (ehFilhoEsquerdo(node)) {
                            avo.setCor(1);
                            node.getPai().setCor(0);
                            simplesDireita(avo);
                        } else {
                            avo.setCor(1);
                            node.setCor(0);
                            duplaDireita(avo);
                        }
                    } else if ((ehFilhoEsquerdo(node.getPai()) && avo.getDireito().getCor() == RUBRO) || (!ehFilhoEsquerdo(node.getPai()) && avo.getEsquerdo().getCor() == RUBRO)) {
                        // tio rubro - passa o negro pra baixo e se torna vermelho
                        avo.getEsquerdo().setCor(0);
                        avo.getDireito().setCor(0);
                        avo.setCor(1);
                    }
                    node = avo;
                }
            } else {
                // raiz - pinta de negro sempre
                node.setCor(NEGRO);
                break;
            }
        }
    }
    
    public static void imprimir( No raiz ) {
        if (raiz != null) {
            imprimir(raiz.getEsquerdo());
            System.out.print(" " + raiz.getChave() + "(" + (raiz.getCor() == 0 ? "p" : "v") + ")");
            imprimir(raiz.getDireito());
        }
    }
    
    public void gerarHtml (No raiz, String fileName) {
        niveisArvore = new HashMap<>();
        construirNiveisArvore(raiz, 0);
        int i = 0;
        while (niveisArvore.get(i) != null) {
            if (i==0 || i+1 == niveisArvore.size()) {
                i++;
                continue;
            }
            int maxNoSize = new Double(Math.pow(2, i)).intValue();
            int j;
            for (j = 0; j < niveisArvore.get(i).size(); j++) {
                No node = (No) niveisArvore.get(i).get(j);
                if (node.getEsquerdo() == null) {
                    niveisArvore.get(i+1).add(2*j, new No(0));
                }
                if (node.getDireito() == null) {
                    niveisArvore.get(i+1).add((2*j)+1, new No(0));
                }
            }
            while (j < maxNoSize) {
                niveisArvore.get(i+1).add(new No(0));
                j++;
            }
            i++;
        }
        String htmlOutput = "<!doctype html> <html> <head> <script type='text/javascript' src='jquery-1.7.2.min.js'></script><script type='text/javascript' src='jqSimpleConnect.js'></script> <meta charset='utf-8'> <title>Visualização da árvore Rubro-Negra</title> <meta name='description' content='Visualização da árvore Rubro-Negra'> <meta name='author' content='20151014040004'> <style> .row { width: 100%; text-align: center; } .node { width: 30px; border-radius: 6px; border: 1px solid black; display: inline-table; margin-top: 10px; margin-bottom: 10px; } .redNo { background-color: red;  color: black; } .blackNo { background-color: black; color: white; } </style> </head> <body>";
        htmlOutput += "<div class='row'><div class='node blackNo' id='_" + raiz.getChave() + "'>" + raiz.getChave() + "</div></div>";
        i = 1;
        int startMagin = 80;
        while (niveisArvore.get(i) != null) {
            htmlOutput += "<div class='row'>";
            int margin = new Double((startMagin/i)*1.2).intValue();
            for (No node : niveisArvore.get(i)) {
                if (node.getPai() != null) {
                    htmlOutput += String.format("<div id='%s' style='margin-left:%dpx; margin-right: %dpx;' class='node %s'>%d</div>", String.format("%d_%d", node.getPai().getChave(), node.getChave()), margin, margin, node.getCor() == 1 ? "redNo" : "blackNo", node.getChave());
                } else {
                    htmlOutput += String.format("<div style='margin-left:%dpx; margin-right: %dpx;' class='node'>-</div>", (startMagin/i),(startMagin/i));
                }
            }
            htmlOutput += "</div>";
            i++;
        }
        niveisArvore = null;
        htmlOutput += "<script>$(\".node[id]\").each(function(q,b){ var children = $(\".node[id^=\" + b.id.split(\"_\")[1] + \"_]\"); var i = 0; for (;children[i];) { jqSimpleConnect.connect(\"#\" + b.id, \"#\" + children[i].id, {radius: 2, color: 'black', anchorA: \"vertical\", anchorB: \"vertical\"}); i++; } b.style.zIndex = \"999\"; b.style.position = \"relative\"; }); window.onresize = function () { jqSimpleConnect.repaintAll(); };</script>";
        htmlOutput += "</body></html>";
        salvarArquivo(htmlOutput, fileName);
    }
    
    protected void construirNiveisArvore(No raiz, int level) {
        if (raiz != null) {
            construirNiveisArvore (raiz.getEsquerdo(), level+1);
            if (niveisArvore.get(level) == null) {
                niveisArvore.put(level, new ArrayList<>());
            }
            niveisArvore.get(level).add(raiz);
            construirNiveisArvore (raiz.getDireito(), level+1);
        }
    }
    
    protected void salvarArquivo(String fileContents, String fileName) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(fileName), "utf-8"))) {
            writer.write(fileContents);
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    protected boolean ehFilhoEsquerdo( No node ) {
        return (node.getPai().getEsquerdo() == node);
    }
    
    protected boolean naoTemFilhos (No node) {
        return node.getEsquerdo() == null && node.getDireito() == null;
    }
    
    protected boolean temDoisFilhos (No node) {
        return temFilhoDireito(node) && temFilhoEsquerdo(node);
    }
    
    protected boolean temFilhoEsquerdo(No node) {
        return node.getEsquerdo() != null;
    }
    
    protected boolean temFilhoDireito(No node) {
        return node.getDireito() != null;
    }
}