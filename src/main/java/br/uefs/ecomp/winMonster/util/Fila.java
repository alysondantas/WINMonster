
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
        tamanho ++; //acresce um no contador de elementos
    }

    @Override
    public Object removerInicio() { //m�todo para remover um elemento da fila
        Celula auxiliar = null; //cria uma celula para auxiliar nas remo��es e inicializa ela como nulo
        if(primeiro!=null) { //caso existam elementos cadastrados na fila
            auxiliar = primeiro; //copia o primeiro elemento no auxiliar
            if(primeiro == ultimo) { //caso s� exista um elemento cadastrado na fila
            	auxiliar=primeiro; //copia o primeiro elemento no auxiliar
                primeiro = null; //deleta o primeiro elemento
                ultimo = null; //deleta o �ltimo elemento
            } else { //caso tenha mais de um elemento na fila
            	auxiliar=primeiro; //copia o primeiro no auxiliar
                primeiro = primeiro.getProximo(); //passa a primeira posi��o para o segundo elemento da fila
            }
            tamanho--; //decresce um do contador de elementos
        }
        return auxiliar.getObjeto(); //retorna a c�pia do elemento removido
    }

    @Override
    public Object recuperarInicio() { //m�todo para retornar o primeiro elemento da fila
        return primeiro.getObjeto(); //retorna o objeto existente dentro do primeiro elemento da fila
    }

    @Override
    public Iterador iterador() { //m�todo para criar um iterador da fila
        MeuIterador iterador = new MeuIterador(primeiro); //cria um novo iterador passando o primeiro elemento da fila como par�metro
        return iterador; //retorna o iterador criado
    }
    
}
