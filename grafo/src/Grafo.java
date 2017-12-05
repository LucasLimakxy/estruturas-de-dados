
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lucas
 */
public class Grafo {
    private ArrayList<Vertice> vertices = new ArrayList<Vertice>();
    private ArrayList<Aresta> arestas = new ArrayList<Aresta>();
    
    public ArrayList<Vertice> vertices() {
        return this.vertices;
    }
    
    public ArrayList<Aresta> arestas() {
        return this.arestas;
    }
    
    public boolean eAdjacente(Vertice v, Vertice w) {
        for (Aresta a : this.arestas) {
            if ((a.getInicio().equals(v) && a.getFim().equals(w)) || 
                (a.getInicio().equals(w) && a.getFim().equals(v))) {
                return true;
            }
        }
        return false;
    }
        
    public Vertice inserirVertice(Object o) {
        Vertice pesquisa = pesquisarVertice(o);
        if (!pesquisa.getValor().equals(-1)) {
            return new Vertice(-1);
        }
        Vertice v = new Vertice(o);
        vertices.add(v);
        return v;
    }
    
    public Aresta inserirAresta(Vertice v, Vertice w, Object o) {
        Aresta a = new Aresta(v, w, o);
        arestas.add(a);
        return a;
    }
    
    public Object removerVertice(Vertice v) {
        
        for (int i = 0; i < arestas.size(); i++) {
            Aresta a = arestas.get(i);
            if (a.getInicio().equals(v) || a.getFim().equals(v)) {
                arestas.remove(a);
                i--;
            }
        }
        vertices.remove(v);
        
        return v.getValor();
    }
    
    public Object removerAresta(Aresta a) {
        arestas.remove(a);
        return a.getRelacao();
    }   
    
    public Vertice pesquisarVertice(Object vertice) {
        for (Vertice v : vertices) {
            if (v.getValor().equals(vertice)) {;
                return v;
            } 
        }
        return new Vertice(-1);
    }
    
    public Aresta pesquisarAresta(Object relacao, Object relacao_inversa) {
        for (Aresta a : arestas) {
            if (a.getRelacao().equals(relacao) || a.getRelacao().equals(relacao_inversa)) {;
                return a;
            } 
        }
        return new Aresta(new Vertice(-1), new Vertice(-1), null);
    }
        
    public String Imprimir() {
        if (eVazio()) {
            return "Grafo vazio";
        }
        String retorno = "\n|    |";
        for (Vertice v : vertices) {
            retorno += " " + v.getValor() + " |";
        }
        retorno += "\n";
        
        for (Vertice v : vertices) {
            retorno += "| " + v.getValor() + " |";
            for (Vertice w : vertices) {
                retorno += " " + (this.eAdjacente(v,w) ? 1 : 0) + "  |";
            }
            retorno += "\n";
        }
        
        return retorno;
    } 
    public boolean eVazio() {
        if (vertices.size() == 0) {
            return true;
        } 
        return false;
    }
}
