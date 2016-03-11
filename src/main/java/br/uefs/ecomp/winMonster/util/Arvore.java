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
		CelulaArvore novaCelula;
	}
	
}
