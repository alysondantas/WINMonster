package br.uefs.ecomp.winMonster.util;

/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 06/04/2016

Declaro que este código foi elaborado por esta dupla e não contém nenhum

trecho de código de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código

de outra autoria que não a minha está destacado com uma citação para o autor e a fonte

do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ******************************************************************************************/

import br.uefs.ecomp.winMonster.model.*;

import br.uefs.ecomp.winMonster.exceptions.*;

/**
 * 
 * @author Alyson Dantas e Bruno Menezes
 *Classe Arvore que trabalha como Arvore de Huffman
 */

public class Arvore {

	Celula raiz; //elemento inicial da árvore
	int tamanho; //número de elementos da árvore

	public Arvore (){ //construtor padrão para a árvore que inicializa sua raíz como nulo e seu tamanho como zero
		this.raiz = null;
		this.tamanho = 0;
	}

	/**
	 * Metodo para verificar se a arvore é vazia
	 * @return
	 */
	public boolean estaVazia() {
		return raiz == null;//retorna true se a primeira celula for nula
	}

	/**
	 * Metodo para obter tamanho da arvore
	 * @return
	 */
	public int obterTamanho() {
		return tamanho;//quando sair do laço retorna o tamanho da lista
	}

	/**
	 * Metodo para retornar a raiz da arvore
	 * @return raiz
	 */
	public Celula retornaRaiz(){//metodo para retornar a raiz
		return raiz;//retorno da raiz
	}

	/**
	 * Metodo para atribuir uma nova raiz a arvore
	 * @param raiz 
	 * @return
	 */
	public void colocaRaiz(Celula raiz1){//metodo set para raiz
		this.raiz = raiz1;//mudando a raiz para a nova
	}

	/**
	 * Metodo para remover da fila e criar as concatenado arvores ate so sobrar um unica arvore
	 * @param filaprioridade
	 * @return
	 * @throws FilaVaziaException
	 */
	public Arvore inserirHuffman(Fila filaprioridade) throws FilaVaziaException{//metodo para remover os dois elementos da fila e colocar em uma arvore
		if(filaprioridade == null){
			throw new FilaVaziaException();//se a fila for nula retorna a exceção
		}
		if(!filaprioridade.estaVazia()){
			if(filaprioridade.obterTamanho() > 1){//se existir elemento proximo continua
				int frequencia = 0;//frequencia que esta na celula
				Celula obj1 = (Celula) filaprioridade.removerInicio();//filha da esquerda que vem da fila
				Celula obj2 = (Celula) filaprioridade.removerInicio();//filha da esquerda que vem da fila
				frequencia = obj1.getChave()+obj2.getChave();//frequencia recebe as frequencias do primeiro e segundo elemento da fila
				obj1=(Celula) obj1.getObjeto();//obj1 recebe a celula da arvore que esta dentro da celula da fila que foi removida
				obj2=(Celula) obj2.getObjeto();//obj2 recebe a celula da arvore que esta dentro da celula da fila que foi removida
				Celula novaCelula = new Celula();//cria nova celula da arvore
				novaCelula.setChave(frequencia);//frequencia é colocada na nova celula da arvore
				novaCelula.setAntEsq(obj1);// a referencia da esquerda recebe o primeiro elemento da fila
				novaCelula.setProxDir(obj2);// a referencia da direita recebe o segundo elemento da fila
				filaprioridade.inserir(frequencia, novaCelula);//insere na fila uma nova celula sendo ela uma arvore
				inserirHuffman(filaprioridade);//recursivo enquanto existir mais que de um elemento na fila
			}
			Celula inicio = (Celula) filaprioridade.recuperarInicio();//pega o primeiro elemento da fila
			Arvore arv = new Arvore();//cria a arvore final de huffman
			arv.colocaRaiz((Celula)inicio.getObjeto());//pega a arvore do primeiro elemento da fila
			return arv;//retorna a arvore pronta
		}else{
			throw new FilaVaziaException();//se a fila estiver vazia retorno a exceção
		}

	}

	/**
	 * Metodo para construir um dicionario dos caracteres que estão no dicionario e na Arvore de Huffman
	 * @param dicionario
	 * @throws FilaVaziaException
	 * @throws CaractereInexistenteException
	 * @throws CelulaNulaException
	 */
	public void construirDicionario(Fila dicionario) throws FilaVaziaException, CaractereInexistenteException, CelulaNulaException{//metodo para criar o dicionario na estrutura copia da fila original
		if(dicionario == null || dicionario.obterTamanho() < 1){
			throw new FilaVaziaException();
		}
		MeuIterador iterador=(MeuIterador) dicionario.iterador(); //crio um iterador pra percorrer a estrutura dicionario
		Celula aux;//auxiliar do tipo celula pra recuperar o objeto que esta no iterador
		String caractere;//caractere que esta na celula
		String binario="";//novo binario do caractere
		boolean verificador=true;//boolean para verificar no binario do caractere se encontrou ou não uma celula folha
		while(iterador.temProximo()){//enquanto existir proximo
			aux=(Celula) iterador.obterProximo();//aux recebe o atual e iterador passa pro proximo
			if(aux.getObjeto() instanceof String){//se o objeto que esta dentro da celula for uma string
				caractere=(String) aux.getObjeto();//caractere recebe o caractere que esta dentro do objeto da celula
				if(caractere.equals("")){
					throw new CaractereInexistenteException();
				}
				aux.setCaractere(caractere);//o caractere é colocado dentro da celula no atributo caractere
			}
			caractere = aux.getCaractere();//caractere recebe o caractere que esta dentro do auxiliar
			verificador = true;//verificador recebe verdadeiro novamente
			binario = "";//variavel binario é reiniciada
			BinarioDoCaractere binarioCarectere = new BinarioDoCaractere();//novo objeto do tipo binariocaractere é criado
			binarioCarectere.setBinario(binario);//ele recebe o binario reiniciado
			binarioCarectere.setVerificador(verificador);//recebe o verificador com true
			if(raiz == null){
				throw new CelulaNulaException();
			}
			binarioCarectere = pegarCaractere(caractere, raiz, binarioCarectere);//binario recebe o binario criado pelo metodo que percorre a arvore
			binario = binarioCarectere.getBinario();//binario construido do caractere é recebido
			aux.setBinario(binario);//binario é colocado dentro da celula auxiliar
		}
	}

	/**
	 * Metodo para pegar um caractere em especifico navegar pela arvore e guardar o caminho que sera o seu futuro binario
	 * @param caractere
	 * @param celula
	 * @param binarioCarectere
	 * @return
	 * @throws CaractereInexistenteException
	 * @throws CelulaNulaException
	 */
	public BinarioDoCaractere pegarCaractere(String caractere, Celula celula, BinarioDoCaractere binarioCarectere) throws CaractereInexistenteException, CelulaNulaException{
		String binario = binarioCarectere.getBinario();//string binario recebe o binario que esta dentro do binarioCaractere
		boolean verificador=binarioCarectere.isVerificador();//verificador recebe o verificador passado dentro do binarioCaractere
		if(celula==null){//interrompe a recursividade e lança exceção se vinher uma celula nula.
			throw new CaractereInexistenteException();
		}else if(verificador==false){//se o verificador for false interrompe a recursão e retorna o binario formado
			return binarioCarectere;//retorna o binario completo
		} else{
			//para esquerda
			if(celula.getAntEsq()==null && celula.getProxDir()==null){//interrompe a recursividade e coloca o caractere no dicionario
				if(celula.getCaractere().equals(caractere)){//se o caractere da celula for igual ao caractere passado
					verificador=false;//verificador recebe false
					binarioCarectere.setVerificador(verificador);//verificador do binario é alterado
					return binarioCarectere;//retorna o binario completo
				}else{
					return binarioCarectere;//retorna o binario completo
				}
			}
			if(verificador == false) { //saída da função, caso o elemento já tenha sido encontrado
				return binarioCarectere; //retorna o binário correto
			}
			binario = binario + "0";//binario recebe mais um 0
			binarioCarectere.setBinario(binario);// binario é colocado dentro do binarioCaractere
			binarioCarectere.setVerificador(verificador);//verificador é colocado dentro do binarioCaractere
			binarioCarectere = pegarCaractere(caractere,celula.getAntEsq(),binarioCarectere);//binarioCaractere recebe outro apos chamar o metodo recursivo passando o caractere, a celula da esquerda da atual e o binario do caractere atual
			binario = binarioCarectere.getBinario();//binario recebe o binario retornado dentro do binarioCaractere
			verificador = binarioCarectere.isVerificador();//verificador recebe o verificador retornado dentro do binarioCaractere
			if(verificador == false) { //saída da função, caso o elemento já tenha sido encontrado
				return binarioCarectere; //retorna o binário correto
			}
			binario = binario.substring(0, binario.length()-1);//remove um elemento do binario
			binarioCarectere.setBinario(binario);//binario do binarioCaractere é atualizado

			//para direita se não for esquerda
			if(celula.getAntEsq()== null && celula.getProxDir()== null){//interrompe a recursividade e coloca o caractere no dicionario
				if(celula.getCaractere().equals(caractere)){//se o caractere da celula for igual ao caractere passado
					verificador = false;//verificador recebe false
					binarioCarectere.setVerificador(verificador);//verificador do binario é alterado
					return binarioCarectere;//retorna o binario completo
				}else{
					return binarioCarectere;//retorna o binario incompleto
				}
			}
			binario=binario+"1";//binario recebe mais um 1
			binarioCarectere.setBinario(binario);// binario é colocado dentro do binarioCaractere
			binarioCarectere.setVerificador(verificador);//verificador é colocado dentro do binarioCaractere
			binarioCarectere = pegarCaractere(caractere,celula.getProxDir(),binarioCarectere);//binarioCaractere recebe outro apos chamar o metodo recursivo passando o caractere, a celula da esquerda da atual e o binario do caractere atual
			binario = binarioCarectere.getBinario();//binario recebe o binario retornado dentro do binarioCaractere
			verificador = binarioCarectere.isVerificador();//verificador recebe o verificador retornado dentro do binarioCaractere
			if(verificador == false) { //saída da função, caso o elemento já tenha sido encontrado
				return binarioCarectere; //retorna o binário correto
			}
			binario = binario.substring(0, binario.length()-1);//remove um elemento do binario
			binarioCarectere.setBinario(binario);//binario do binarioCaractere é atualizado

			return binarioCarectere;//retorna o binarioCaractere pro metodo recusivo que chamou
		}
	}

}