package br.uefs.ecomp.winMonster.util;

public class Arvore {
	Celula raiz;
	int tamanho=0;
	
	public boolean estaVazia() {
		return raiz==null;//retorna true se a primeira celula for nula
	}
	public int obterTamanho() {
		return tamanho;//quando sair do laço retorna o tamanho da lista
	}
	//test
}
