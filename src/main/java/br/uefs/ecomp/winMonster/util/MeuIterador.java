package br.uefs.ecomp.winMonster.util;

/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 06/04/2016

Declaro que este c�digo foi elaborado por esta dupla e n�o cont�m nenhum

trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo

de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte

do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.

 ******************************************************************************************/

import br.uefs.ecomp.winMonster.util.Celula;//importo a classe celula generica
import br.uefs.ecomp.winMonster.util.Iterador;//importo a intefacer interador
/**
 * 
 * @author  Alyson Dantas e Bruno Menezes
 * Meu iterador para trabalhar dentro das listas
 */
public class MeuIterador implements Iterador {//Meu iterador implementa a interface Iterador
	private Celula auxiliar;//crio uma celula auxiliar para trabalhar dentro do iterador
	private Celula primeira;//crio um celula para sempre ser a primeira celula da lista encadeada
	/**
	 * Construtor para receber primeira celula
	 * @param primeira
	 */
	public MeuIterador(Object primeira){//construtor do meu iterador recebe a primeira celula da lista como objeto
		auxiliar=(Celula)primeira;//auxiliar recebe a primeira celula
		this.primeira=(Celula)primeira;//primeira recebe a primeira celula
	}

	@Override
	/**
	 * Metodo para verificar se existe proxima celula ou n�o
	 */
	public boolean temProximo() {
		return auxiliar!=null;//se auxiliar for diferente de null � porque existe outra celula
	}

	@Override
	/**
	 * Metodo para retornar celula atuala e passar pra proxima
	 */
	public Object obterProximo() {
		Celula aux2 = auxiliar;//aux2 copia a c�lula atual
		auxiliar=auxiliar.getProxDir();//celula atual passa pra proxima
		return aux2;//retorna aux2
	}
	/**
	 * metodo para fazer com que o iterador retorne para o inicio da lista
	 */
	public void reiniciar() {
		auxiliar = primeira;//auxiliar recebe a primeira
	}
}