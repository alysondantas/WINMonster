package br.uefs.ecomp.winMonster.util;

import br.uefs.ecomp.winMonster.model.*;
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
				frequencia= obj1.getChave()+obj2.getChave();//frequencia recebe as frequencias do primeiro e segundo elemento da fila
				obj1=(Celula) obj1.getObjeto();
				obj2=(Celula) obj2.getObjeto();
				Celula novaCelula=new Celula();//cria nova celula da arvore
				novaCelula.setChave(frequencia);
				novaCelula.setAntEsq(obj2);// a referencia da esquerda recebe o primeiro elemento da fila
				novaCelula.setProxDir(obj1);// a referencia da direita recebe o segundo elemento da fila
				filaprioridade.inserir(frequencia, novaCelula);//insere na fila uma nova celula sendo ela uma arvore
				inserirHuffman(filaprioridade);//recursivo enquanto existir mais que de um elemento na fila
			}
			Celula inicio = (Celula) filaprioridade.recuperarInicio();//pega o primeiro elemento da fila
			Arvore arv = new Arvore();
			arv.colocaRaiz((Celula)inicio.getObjeto());//pega a arvore do primeiro elemento da fila
			return arv;//retorna a arvore pronta
		}
		return null;
	}

	public void construirDicionario(Fila dicionario) throws CaractereInexistenteException{//metodo para criar o dicionario na estrutura copia da fila original
		MeuIterador iterador=(MeuIterador) dicionario.iterador(); //crio um iterador pra percorrer a estrutura dicionario
		Celula aux;//auxiliar do tipo celula pra recuperar o objeto que esta no iterador
		String caractere;//caractere que esta na celula
		String binario="";//novo binario do caractere
		boolean verificador=true;
		while(iterador.temProximo()){//enquanto existir proximo
			aux=(Celula) iterador.obterProximo();//aux recebe o atual e iterador passa pro proximo
			if(aux.getObjeto() instanceof String){
				caractere=(String) aux.getObjeto();
				aux.setCaractere(caractere);
			}
			caractere=aux.getCaractere();//caractere recebe o caractere que esta dentro do auxiliar
			verificador=true;
			binario = "";
			BinarioDoCaractere binarioCarectere = new BinarioDoCaractere();
			binarioCarectere.setBinario(binario);
			binarioCarectere.setVerificador(verificador);
			binarioCarectere=pegarCaractere(caractere, raiz, binarioCarectere);//binario recebe o binario criado pelo metodo que percorre a arvore
			binario=binarioCarectere.getBinario();
			aux.setBinario(binario);//binario é colocado dentro da celula auxiliar
		}
	}

	public BinarioDoCaractere pegarCaractere(String caractere, Celula celula, BinarioDoCaractere binarioCarectere) throws CaractereInexistenteException{
		String binario=binarioCarectere.getBinario();
		boolean verificador=binarioCarectere.isVerificador();
		if(celula==null){//interrompe a recursividade e coloca o caractere no dicionario
			throw new CaractereInexistenteException();
		}else if(verificador==false){
			return binarioCarectere;
		} else{
			//para esquerda
			binario=binario+"0";
			if(celula.getAntEsq()==null && celula.getProxDir()==null){//interrompe a recursividade e coloca o caractere no dicionario
				if(celula.getCaractere().equals(caractere)){
					verificador=false;
					binarioCarectere.setVerificador(verificador);
					return binarioCarectere;//retorna o binario completo
				}else{
					return binarioCarectere;
				}
			}
			binarioCarectere.setBinario(binario);
			binarioCarectere.setVerificador(verificador);
			BinarioDoCaractere binarioesq= new BinarioDoCaractere();
			binarioesq.setVerificador(verificador);
			binarioesq=pegarCaractere(caractere,celula.getAntEsq(),binarioCarectere);
			String esq=binarioesq.getBinario();
			binario = binario.substring(0, binario.length()-1);
			if(verificador == false) { //saída da função, caso o elemento já tenha sido encontrado
				return binarioCarectere; //retorna o binário correto
			}
			//para direita se não for esquerda
			binario=binario+"1";
			if(celula.getAntEsq()==null && celula.getProxDir()==null){//interrompe a recursividade e coloca o caractere no dicionario
				if(celula.getCaractere().equals(caractere)){
					verificador=false;
					binarioCarectere.setVerificador(verificador);
					return binarioCarectere;//retorna o binario completo
				}else{
					return binarioCarectere;
				}
			}
			binarioCarectere.setBinario(binario);
			binarioCarectere.setVerificador(verificador);
			BinarioDoCaractere binariodir= new BinarioDoCaractere();
			binariodir.setVerificador(verificador);
			binariodir=pegarCaractere(caractere,celula.getProxDir(),binarioCarectere);
			String dir=binariodir.getBinario();
			binario = binario.substring(0, binario.length()-1);
			if(esq==null){
				return binariodir;
			}else{
				return binarioesq;
			}
		}
	}



}