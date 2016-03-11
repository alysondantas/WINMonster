package br.uefs.ecomp.winMonster.util;

public class Arvore {
	CelulaArvore raiz;
	int tamanho=0;
	public boolean estaVazia() {
		return raiz==null;//retorna true se a primeira celula for nula
	}
	public int obterTamanho() {
		return tamanho;//quando sair do laço retorna o tamanho da lista
	}
	//Não garanto que nada disso funcione corretamente
	public CelulaArvore encontra(int key){
		CelulaArvore atual=raiz;
		while(atual.getChave()!=key){
			if(key<atual.getChave()){
				atual=atual.getEsquerda();
				if(atual==null){
					return null;
				}else{
					atual=atual.getDireita();
					if(atual==null){
						return null;
					}
				}
			}
		}
		return atual;
	}

	public void inserir(CelulaArvore celula){
		CelulaArvore novaCelula=celula;
		if(raiz==null){
			raiz=novaCelula;
		}else{
			CelulaArvore atual=raiz;
			CelulaArvore pai;
			while(true){
				pai=atual;
				if(celula.getChave()<atual.getChave()){
					atual=atual.getEsquerda();
					if(atual==null){
						pai.setEsquerda(novaCelula);
						return;
					}
				}else{
					atual=atual.getDireita();
					if(atual==null){
						pai.setDireita(novaCelula);
						return;
					}
				}
			}
		}
	}



	/*	public void inserir2(CelulaArvore node, int valor) {
		//Verifica se o valor a ser inserido é menor que o nodo corrente da árovre, se sim vai para subarvore esquerda
		if (valor < node.getChave()) {
			//Se tiver elemento no nodo esquerdo continua a busca
			if (node.getEsquerda() != null) {
				inserir2(node.getEsquerda(), valor);
			} else {
				//Se nodo esquerdo vazio insere o novo nodo aqui
				System.out.println("  Inserindo " + valor + " a esquerda de " + node.valor);
				node.getEsquerda() = new CelulaArvore(valor);
			}
			//Verifica se o valor a ser inserido é maior que o nodo corrente da árvore, se sim vai para subarvore direita
		} else if (valor > node.valor) {
			//Se tiver elemento no nodo direito continua a busca
			if (node.direita != null) {
				inserir2(node.direita, valor);
			} else {
				//Se nodo direito vazio insere o novo nodo aqui
				System.out.println("  Inserindo " + valor + " a direita de " + node.valor);
				node.direita = new No(valor);
			}
		}*/
}