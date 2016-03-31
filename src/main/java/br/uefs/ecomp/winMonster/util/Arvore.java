package br.uefs.ecomp.winMonster.util;

import br.uefs.ecomp.winMonster.model.*;

import br.uefs.ecomp.winMonster.exceptions.*;

public class Arvore {

	Celula raiz; //elemento inicial da �rvore
	int tamanho; //n�mero de elementos da �rvore

	public Arvore (){ //construtor padr�o para a �rvore que inicializa sua ra�z como nulo e seu tamanho como zero
		this.raiz = null;
		this.tamanho = 0;
	}

	public boolean estaVazia() {
		return raiz == null;//retorna true se a primeira celula for nula
	}
	public int obterTamanho() {
		return tamanho;//quando sair do la�o retorna o tamanho da lista
	}


	public Celula retornaRaiz(){//metodo para retornar a raiz
		return raiz;//retorno da raiz
	}

	public void colocaRaiz(Celula raiz1){//metodo set para raiz
		this.raiz = raiz1;//mudando a raiz para a nova
	}

	public Arvore inserirHuffman(Fila filaprioridade) throws FilaVaziaException{//metodo para remover os dois elementos da fila e colocar em uma arvore
		if(filaprioridade == null){
			throw new FilaVaziaException();//se a fila for nula retorna a exce��o
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
				novaCelula.setChave(frequencia);//frequencia � colocada na nova celula da arvore
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
			throw new FilaVaziaException();//se a fila estiver vazia retorno a exce��o
		}
		
	}

	public void construirDicionario(Fila dicionario) throws FilaVaziaException, CaractereInexistenteException, CelulaNulaException{//metodo para criar o dicionario na estrutura copia da fila original
		if(dicionario == null || dicionario.obterTamanho() < 1){
			throw new FilaVaziaException();
		}
		MeuIterador iterador=(MeuIterador) dicionario.iterador(); //crio um iterador pra percorrer a estrutura dicionario
		Celula aux;//auxiliar do tipo celula pra recuperar o objeto que esta no iterador
		String caractere;//caractere que esta na celula
		String binario="";//novo binario do caractere
		boolean verificador=true;//boolean para verificar no binario do caractere se encontrou ou n�o uma celula folha
		while(iterador.temProximo()){//enquanto existir proximo
			aux=(Celula) iterador.obterProximo();//aux recebe o atual e iterador passa pro proximo
			if(aux.getObjeto() instanceof String){//se o objeto que esta dentro da celula for uma string
				caractere=(String) aux.getObjeto();//caractere recebe o caractere que esta dentro do objeto da celula
				if(caractere.equals("")){
					throw new CaractereInexistenteException();
				}
				aux.setCaractere(caractere);//o caractere � colocado dentro da celula no atributo caractere
			}
			caractere = aux.getCaractere();//caractere recebe o caractere que esta dentro do auxiliar
			verificador = true;//verificador recebe verdadeiro novamente
			binario = "";//variavel binario � reiniciada
			BinarioDoCaractere binarioCarectere = new BinarioDoCaractere();//novo objeto do tipo binariocaractere � criado
			binarioCarectere.setBinario(binario);//ele recebe o binario reiniciado
			binarioCarectere.setVerificador(verificador);//recebe o verificador com true
			if(raiz == null){
				throw new CelulaNulaException();
			}
			binarioCarectere = pegarCaractere(caractere, raiz, binarioCarectere);//binario recebe o binario criado pelo metodo que percorre a arvore
			binario = binarioCarectere.getBinario();//binario construido do caractere � recebido
			aux.setBinario(binario);//binario � colocado dentro da celula auxiliar
		}
	}

	public BinarioDoCaractere pegarCaractere(String caractere, Celula celula, BinarioDoCaractere binarioCarectere) throws CaractereInexistenteException, CelulaNulaException{
		String binario = binarioCarectere.getBinario();//string binario recebe o binario que esta dentro do binarioCaractere
		boolean verificador=binarioCarectere.isVerificador();//verificador recebe o verificador passado dentro do binarioCaractere
		if(celula==null){//interrompe a recursividade e lan�a exce��o se vinher uma celula nula.
			throw new CaractereInexistenteException();
		}else if(verificador==false){//se o verificador for false interrompe a recurs�o e retorna o binario formado
			return binarioCarectere;//retorna o binario completo
		} else{
			//para esquerda
			if(celula.getAntEsq()==null && celula.getProxDir()==null){//interrompe a recursividade e coloca o caractere no dicionario
				if(celula.getCaractere().equals(caractere)){//se o caractere da celula for igual ao caractere passado
					verificador=false;//verificador recebe false
					binarioCarectere.setVerificador(verificador);//verificador do binario � alterado
					return binarioCarectere;//retorna o binario completo
				}else{
					return binarioCarectere;//retorna o binario completo
				}
			}
			if(verificador == false) { //sa�da da fun��o, caso o elemento j� tenha sido encontrado
				return binarioCarectere; //retorna o bin�rio correto
			}
			binario = binario + "0";//binario recebe mais um 0
			binarioCarectere.setBinario(binario);// binario � colocado dentro do binarioCaractere
			binarioCarectere.setVerificador(verificador);//verificador � colocado dentro do binarioCaractere
			binarioCarectere = pegarCaractere(caractere,celula.getAntEsq(),binarioCarectere);//binarioCaractere recebe outro apos chamar o metodo recursivo passando o caractere, a celula da esquerda da atual e o binario do caractere atual
			binario = binarioCarectere.getBinario();//binario recebe o binario retornado dentro do binarioCaractere
			verificador = binarioCarectere.isVerificador();//verificador recebe o verificador retornado dentro do binarioCaractere
			if(verificador == false) { //sa�da da fun��o, caso o elemento j� tenha sido encontrado
				return binarioCarectere; //retorna o bin�rio correto
			}
			binario = binario.substring(0, binario.length()-1);//remove um elemento do binario
			binarioCarectere.setBinario(binario);//binario do binarioCaractere � atualizado
			
			//para direita se n�o for esquerda
			if(celula.getAntEsq()== null && celula.getProxDir()== null){//interrompe a recursividade e coloca o caractere no dicionario
				if(celula.getCaractere().equals(caractere)){//se o caractere da celula for igual ao caractere passado
					verificador = false;//verificador recebe false
					binarioCarectere.setVerificador(verificador);//verificador do binario � alterado
					return binarioCarectere;//retorna o binario completo
				}else{
					return binarioCarectere;//retorna o binario incompleto
				}
			}
			binario=binario+"1";//binario recebe mais um 1
			binarioCarectere.setBinario(binario);// binario � colocado dentro do binarioCaractere
			binarioCarectere.setVerificador(verificador);//verificador � colocado dentro do binarioCaractere
			binarioCarectere = pegarCaractere(caractere,celula.getProxDir(),binarioCarectere);//binarioCaractere recebe outro apos chamar o metodo recursivo passando o caractere, a celula da esquerda da atual e o binario do caractere atual
			binario = binarioCarectere.getBinario();//binario recebe o binario retornado dentro do binarioCaractere
			verificador = binarioCarectere.isVerificador();//verificador recebe o verificador retornado dentro do binarioCaractere
			if(verificador == false) { //sa�da da fun��o, caso o elemento j� tenha sido encontrado
				return binarioCarectere; //retorna o bin�rio correto
			}
			binario = binario.substring(0, binario.length()-1);//remove um elemento do binario
			binarioCarectere.setBinario(binario);//binario do binarioCaractere � atualizado
			
			return binarioCarectere;//retorna o binarioCaractere pro metodo recusivo que chamou
		}
	}

}