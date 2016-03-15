package br.uefs.ecomp.winMonster.util;

/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas

Componente Curricular: MI - Algoritmos II

Concluido em: 12/12/2015

Declaro que este c�digo foi elaborado por mim de forma individual e n�o cont�m nenhum

trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo

de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte

do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.

******************************************************************************************/
/**
 * 
 * @author Alyson
 * Classe para a celula generica
 */
public class CelulaArvore {//Classe para um celula generica
	private CelulaArvore direita;//Referencia para a celula filha da direita
	private CelulaArvore esquerda;//Referencia para a celula filha da esquerda
	private String caractere="";//Objeto generico que vai estar dentro da celula
	/**
	 * Metodo construtor da classe obrigando que venha um objeto quando criar celula
	 * @param o
	 */
	public CelulaArvore(){
		
	}
	//Os dois a baixo tem o mesmo papel que o get e set acima.
	/**
	 * @return the direita
	 */
	public CelulaArvore getDireita() {
		return direita;
	}
	/**
	 * @param direita the direita to set
	 */
	public void setDireita(CelulaArvore direita) {
		this.direita = direita;
	}
	/**
	 * @return the esquerda
	 */
	public CelulaArvore getEsquerda() {
		return esquerda;
	}
	/**
	 * @param esquerda the esquerda to set
	 */
	public void setEsquerda(CelulaArvore esquerda) {
		this.esquerda = esquerda;
	}
	/**
	 * @return the caractere
	 */
	public String getCaractere() {
		return caractere;
	}
	/**
	 * @param caractere the caractere to set
	 */
	public void setCaractere(String caractere) {
		this.caractere = caractere;
	}
}
