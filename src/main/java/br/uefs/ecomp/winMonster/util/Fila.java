    
    /**
     * Componente Curricular: Módulo Integrado de Programação
     * Autor: Bruno Menezes de Lima e Bernardo Oliveira Rosa
     * Data:  07/03/2016
     *
     * Declaro que este código foi elaborado por nós de forma individual e
     * não contém nenhum trecho de código de outro colega ou de outro autor,
     * tais como provindos de livros e apostilas, e páginas ou documentos
     * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
     * uma citação para o  não a minha está destacado com  autor e a fonte do
     * código, e estou ciente que estes trechos não serão considerados para fins
     * de avaliação. Alguns trechos do código podem coincidir com de outros
     * colegas pois estes foram discutidos em sessões tutorias.
     */

package br.uefs.ecomp.winMonster.util;

public class Fila implements IFila{

    private Celula primeiro; //primeira posição da fila
    private Celula ultimo; //último elemento da fila
    private int tamanho = 0 ; //contador de elementos

        public Fila() {
            this.primeiro = null;           //Inicializa o primeiro elemento como nulo
            this.ultimo = this.primeiro;    //e iguala o último ao primeiro
            this.tamanho = 0;        //além de inicializar o contador como 0    
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
            Celula no = new Celula(this.ultimo, null, o); //Cria um novo nó usando o último da lista como anterior, nulo como próximo e com o objeto o como conteúdo
            if (!estaVazia()) {
                this.ultimo.setProximo(no); //diz que o próximo do último da lista é o novo nó
                this.ultimo = no; //aponta a última posição para esse novo nó
                tamanho++;
            } else {
                this.primeiro = no; //aponta o primeiro da lista para o novo nú
                this.ultimo = this.primeiro; //como só tem 1 elemento, o último se torna o primeiro
                tamanho++;
            }
        }

        @Override
        public Object removerInicio() {
            if (tamanho == 1) { //se só existir um elemento na lista
                Celula aux = this.primeiro; //cria uma cópia do elemento a ser removido pra retorno
                this.primeiro = this.primeiro.getProximo(); //o primeiro agora aponta pra o próximo do primeiro elemento
                tamanho--;
                return aux; //retorna a cópia do elemento removido
            } else if (!estaVazia()) { 
                Celula aux = this.primeiro; //cópia do elemento a ser removido
                this.primeiro = this.primeiro.getProximo();     //a cabeça da lista se torna o próximo elemento
                this.primeiro.setAnterior(null); //direciona o anterior da nova cabeça pra nulo
                tamanho--;
                return aux; //retorna a cópia
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
            return new MeuIterador(this.primeiro); //retorna um iterador com a cabeça da lista

        }

    }
    

    
    ///////////////////////////////////////// Classe antiga //////////////////////////////////////////////
    
    
    
    /*public Fila(){
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
    
}*/
