
package br.uefs.ecomp.winMonster.util;

public class Fila implements IFila{

    private Celula primeiro; //primeiro elemento da fila
    private Celula ultimo; //último elemento da fila
    private int tamanho = 0 ; //contador de elementos
    
    public Fila(){
		primeiro = null; //inicializa o primeiro e o último como nulos
		ultimo = null;
	}
    
    @Override
    public boolean estaVazia() { //método para verificar se a lista está vazia
    	if(primeiro == null){ //caso a primeira posição esteja vazia
    		return true; //retorne true
    	} //caso contrário
        return false; //retorne false
    }

    @Override
    public int obterTamanho() { //método pra obter o número de elementos da fila
        return tamanho; //retorna o contador da classe
    }

    @Override
    public void inserirFinal(Object o) { //método para inserir elementos na fila
    	Celula celulaNova=new Celula(o); //cria uma nova célula chamada "celulaNova" com o objeto recebido pelo método
        if (primeiro==null) {
            primeiro=celulaNova;
            ultimo=primeiro;
        }
        else {
            ultimo.setProximo(celulaNova);
            ultimo=celulaNova;
        }
        tamanho ++;
    }

    @Override
    public Object removerInicio() {
        Celula auxiliar = null;
        if(primeiro!=null) {
            auxiliar = primeiro;
            if(primeiro == ultimo) {
            	auxiliar=primeiro;
                primeiro = null;
                ultimo = null;
            } else {
            	auxiliar=primeiro;
                primeiro = primeiro.getProximo();
            }
            tamanho--;
        }
        return auxiliar.getObjeto();
    }

    @Override
    public Object recuperarInicio() {
        return primeiro.getObjeto();
    }

    @Override
    public Iterador iterador() {
        MeuIterador iterador = new MeuIterador(primeiro);
        return iterador;
    }
    
}
