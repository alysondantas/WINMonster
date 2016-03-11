
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
        if (primeiro==null) { //caso a primeira posição esteja vazia
            primeiro=celulaNova; //atribui a primeira posição da fila à célula nova
            ultimo=primeiro; //aponta a referência do último para o primeiro, já que só tem 1 elemento na fila
        }
        else { //caso já tenham elementos cadastrados
            ultimo.setProximo(celulaNova); //aponta a referência do próximo do último elemento da lista para a nova célula criada
            ultimo=celulaNova; //define a nova célula como último elemento 
        }
        tamanho ++; //acresce um no contador de elementos
    }

    @Override
    public Object removerInicio() { //método para remover um elemento da fila
        Celula auxiliar = null; //cria uma celula para auxiliar nas remoções e inicializa ela como nulo
        if(primeiro!=null) { //caso existam elementos cadastrados na fila
            auxiliar = primeiro; //copia o primeiro elemento no auxiliar
            if(primeiro == ultimo) { //caso só exista um elemento cadastrado na fila
            	auxiliar=primeiro; //copia o primeiro elemento no auxiliar
                primeiro = null; //deleta o primeiro elemento
                ultimo = null; //deleta o último elemento
            } else { //caso tenha mais de um elemento na fila
            	auxiliar=primeiro; //copia o primeiro no auxiliar
                primeiro = primeiro.getProximo(); //passa a primeira posição para o segundo elemento da fila
            }
            tamanho--; //decresce um do contador de elementos
        }
        return auxiliar.getObjeto(); //retorna a cópia do elemento removido
    }

    @Override
    public Object recuperarInicio() { //método para retornar o primeiro elemento da fila
        return primeiro.getObjeto(); //retorna o objeto existente dentro do primeiro elemento da fila
    }

    @Override
    public Iterador iterador() { //método para criar um iterador da fila
        MeuIterador iterador = new MeuIterador(primeiro); //cria um novo iterador passando o primeiro elemento da fila como parâmetro
        return iterador; //retorna o iterador criado
    }
    
}
