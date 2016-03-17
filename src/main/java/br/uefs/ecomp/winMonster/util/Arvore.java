package br.uefs.ecomp.winMonster.util;

import br.uefs.ecomp.winMonster.exceptions.CaractereInexistenteException;

public class Arvore {

	Celula raiz; //elemento inicial da árvore
	int tamanho; //número de elementos da árvore

	public Arvore (){ //construtor padrão para a árvore que inicializa sua raíz como nulo e seu tamanho como zero
		this.raiz = null;
		this.tamanho = 0;
	}

	public boolean estaVazia() {
		return raiz==null;//retorna true se a primeira celula for nula
	}
	public int obterTamanho() {
		return tamanho;//quando sair do laço retorna o tamanho da lista
	}


	public Celula retornaRaiz(){//metodo para retornar a raiz
		return raiz;//retorno da raiz
	}

	public void colocaRaiz(Celula raiz1){//metodo set para raiz
		this.raiz=raiz1;//mudando a raiz para a nova
	}

	public Arvore inserirHuffman(Fila filaprioridade){//metodo para remover os dois elementos da fila e colocar em uma arvore
		if(!filaprioridade.estaVazia()){
			if(filaprioridade.obterTamanho()>1){//se existir elemento proximo continua
				int frequencia=0;//frequencia que esta na celula
				Celula obj1 = (Celula) filaprioridade.removerInicio();//filha da esquerda que vem da fila
				Celula obj2 = (Celula) filaprioridade.removerInicio();//filha da esquerda que vem da fila
				Arvore arv1,arv2,nova=new Arvore();//cria tres variaveis de arvore sendo o nova a nova arvore e as outras duas auxiliares
				frequencia= obj1.getChave()+obj2.getChave();//frequencia recebe as frequencias do primeiro e segundo elemento da fila
				Celula novaCelula=new Celula();//cria nova celula da arvore
				arv1=(Arvore) obj1.getObjeto();//arv1 recebe a arvore do primeiro elemento da fila
				novaCelula.setAntEsq(arv1.retornaRaiz());// a referencia da esquerda recebe o primeiro elemento da fila
				arv2=(Arvore) obj2.getObjeto();//arv2 recebe a arvore do segundo elemento da fila
				novaCelula.setProxDir(arv2.retornaRaiz());// a referencia da direita recebe o segundo elemento da fila
				nova.colocaRaiz(novaCelula);//a raiz da arvore agora é a nova celula
				filaprioridade.inserir(frequencia, nova);//insere na fila uma nova celula sendo ela uma arvore
				inserirHuffman(filaprioridade);//recursivo enquanto existir mais que de um elemento na fila
			}
		}
		Celula inicio = (Celula) filaprioridade.recuperarInicio();//pega o primeiro elemento da fila
		Arvore arv3 = (Arvore) inicio.getObjeto();//pega a arvore do primeiro elemento da fila
		return arv3;//retorna a arvore pronta
	}

	public void construirDicionario(Fila dicionario) throws CaractereInexistenteException{//metodo para criar o dicionario na estrutura copia da fila original
		MeuIterador iterador=(MeuIterador) dicionario.iterador(); //crio um iterador pra percorrer a estrutura dicionario
		Celula aux;//auxiliar do tipo celula pra recuperar o objeto que esta no iterador
		String caractere;//caractere que esta na celula
		String binario="";//novo binario do caractere
		while(iterador.temProximo()){//enquanto existir proximo
			aux=(Celula) iterador.obterProximo();//aux recebe o atual e iterador passa pro proximo
			caractere=aux.getCaractere();//caractere recebe o caractere que esta dentro do auxiliar
			binario=pegarCaractere(caractere, raiz, binario);//binario recebe o binario criado pelo metodo que percorre a arvore
			aux.setBinario(binario);//binario é colocado dentro da celula auxiliar
		}
	}

	public String pegarCaractere(String caractere, Celula celula, String binario) throws CaractereInexistenteException{
		if(celula==null){//interrompe a recursividade e coloca o caractere no dicionario
			throw new CaractereInexistenteException();
		}else{
			//para esquerda
			binario=binario+"0";
			if(celula.getAntEsq()==null && celula.getProxDir()==null){//interrompe a recursividade e coloca o caractere no dicionario
				if(celula.getCaractere().equals(caractere)){
					return binario;//retorna o binario completo
				}
			}
			String esq=pegarCaractere(caractere,celula.getAntEsq(),binario);
			binario = binario.substring(0, binario.length()-1);
			//para direita se não for esquerda
			binario=binario+"1";
			String dir=pegarCaractere(caractere,celula.getProxDir(),binario);
			binario = binario.substring(0, binario.length()-1);
			if(esq==null){
				return dir;
			}else{
				return esq;
			}
		}
	}



}