package br.uefs.ecomp.winMonster.model;
/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas  e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 06/04/2016

Declaro que este c�digo foi elaborado por esta dupla e n�o cont�m nenhum

trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo

de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte

do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.

 ******************************************************************************************/

/**
 * 
 * @author Alyson Dantas e Bruno Menezes
 *Classe para passar um verificador junto ao binario que vai dar break na recurs�o da cria��o de caracteres
 */
public class BinarioDoCaractere { //classe para colocar o binario e um verificador para o metodo de huffman
	private String binario;
	private boolean verificador;

	/**
	 * @return o binario
	 */
	public String getBinario() {
		return binario;
	}

	/**
	 * @param binario o binario para mudar
	 */
	public void setBinario(String binario) {
		this.binario = binario;
	}

	/**
	 * @return o verificador
	 */
	public boolean isVerificador() {
		return verificador;
	}

	/**
	 * @param verificador o verificador para mudar
	 */
	public void setVerificador(boolean verificador) {
		this.verificador = verificador;
	}
}
