    
    /**
     * Componente Curricular: M�dulo Integrado de Programa��o
     * Autor: Bruno Menezes de Lima e Bernardo Oliveira Rosa
     * Data:  07/03/2016
     *
     * Declaro que este c�digo foi elaborado por n�s de forma individual e
     * n�o cont�m nenhum trecho de c�digo de outro colega ou de outro autor,
     * tais como provindos de livros e apostilas, e p�ginas ou documentos
     * eletr�nicos da Internet. Qualquer trecho de c�digo de outra autoria que
     * uma cita��o para o  n�o a minha est� destacado com  autor e a fonte do
     * c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins
     * de avalia��o. Alguns trechos do c�digo podem coincidir com de outros
     * colegas pois estes foram discutidos em sess�es tutorias.
     */

package br.uefs.ecomp.winMonster.util;

public class Fila implements IFila{

    private Celula primeiro; //primeira posi��o da fila
    private Celula ultimo; //�ltimo elemento da fila
    private int tamanho = 0 ; //contador de elementos

        public Fila() {
            this.primeiro = null;           //Inicializa o primeiro elemento como nulo
            this.ultimo = this.primeiro;    //e iguala o �ltimo ao primeiro
            this.tamanho = 0;        //al�m de inicializar o contador como 0    
        }
        
        @Override
        public boolean estaVazia() {
            if (this.primeiro == null) { //se o primeiro elemento for nulo
                return true;
            }
            return false;
        }

        @Override
        public int obterTamanho() {
            return tamanho;
        }

        @Override
        public void inserirFinal(Object o) {
            Celula no = new Celula(this.ultimo, null, o); //Cria um novo n� usando o �ltimo da lista como anterior, nulo como pr�ximo e com o objeto o como conte�do
            if (!estaVazia()) {
                this.ultimo.setProximo(no); //diz que o pr�ximo do �ltimo da lista � o novo n�
                this.ultimo = no; //aponta a �ltima posi��o para esse novo n�
                tamanho++;
            } else {
                this.primeiro = no; //aponta o primeiro da lista para o novo n�
                this.ultimo = this.primeiro; //como s� tem 1 elemento, o �ltimo se torna o primeiro
                tamanho++;
            }
        }

        @Override
        public Object removerInicio() {
            if (tamanho == 1) { //se s� existir um elemento na lista
                Celula aux = this.primeiro; //cria uma c�pia do elemento a ser removido pra retorno
                this.primeiro = this.primeiro.getProximo(); //o primeiro agora aponta pra o pr�ximo do primeiro elemento
                tamanho--;
                return aux; //retorna a c�pia do elemento removido
            } else if (!estaVazia()) { 
                Celula aux = this.primeiro; //c�pia do elemento a ser removido
                this.primeiro = this.primeiro.getProximo();     //a cabe�a da lista se torna o pr�ximo elemento
                this.primeiro.setAnterior(null); //direciona o anterior da nova cabe�a pra nulo
                tamanho--;
                return aux; //retorna a c�pia
            } else {
                return null;
            }
        }

        @Override
        public Object recuperarInicio() {
            return this.primeiro;
        }

        @Override
        public Iterador iterador() {
            return new MeuIterador(this.primeiro); //retorna um iterador com a cabe�a da lista

        }

    }
    

    
    ///////////////////////////////////////// Classe antiga //////////////////////////////////////////////
    
    
    
    /*public Fila(){
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
    
}*/
