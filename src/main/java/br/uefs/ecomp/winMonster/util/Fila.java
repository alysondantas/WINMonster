
package br.uefs.ecomp.winMonster.util;

public class Fila implements IFila{

    private Celula primeiro; //primeiro elemento da fila
    private Celula ultimo; //�ltimo elemento da fila
    private int tamanho = 0 ; //contador de elementos
    
    public Fila(){
		primeiro = null; //inicializa o primeiro e o �ltimo como nulos
		ultimo = null;
	}
    
    @Override
    public boolean estaVazia() { //m�todo para verificar se a lista est� vazia
    	if(primeiro == null){ //caso a primeira posi��o esteja vazia
    		return true; //retorne true
    	} //caso contr�rio
        return false; //retorne false
    }

    @Override
    public int obterTamanho() { //m�todo pra obter o n�mero de elementos da fila
        return tamanho; //retorna o contador da classe
    }

    @Override
    public void inserirFinal(Object o) { //m�todo para inserir elementos na fila
    	Celula celulaNova=new Celula(o); //cria uma nova c�lula chamada "celulaNova" com o objeto recebido pelo m�todo
        if (primeiro==null) { //caso a primeira posi��o esteja vazia
            primeiro=celulaNova; //atribui a primeira posi��o da fila � c�lula nova
            ultimo=primeiro; //aponta a refer�ncia do �ltimo para o primeiro, j� que s� tem 1 elemento na fila
        }
        else { //caso j� tenham elementos cadastrados
            ultimo.setProximo(celulaNova); //aponta a refer�ncia do pr�ximo do �ltimo elemento da lista para a nova c�lula criada
            ultimo=celulaNova; //define a nova c�lula como �ltimo elemento
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
