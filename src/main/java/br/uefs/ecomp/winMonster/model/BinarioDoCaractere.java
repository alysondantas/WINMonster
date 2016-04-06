package br.uefs.ecomp.winMonster.model;
/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas  e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 06/04/2016

Declaro que este código foi elaborado por esta dupla e não contém nenhum

trecho de código de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código

de outra autoria que não a minha está destacado com uma citação para o autor e a fonte

do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ******************************************************************************************/

/**
 * 
 * @author Alyson Dantas e Bruno Menezes
 *Classe para passar um verificador junto ao binario que vai dar break na recursão da criação de caracteres
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
