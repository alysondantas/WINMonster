package br.uefs.ecomp.winMonster.controller;

import br.uefs.ecomp.winMonster.exceptions.CaractereInexistenteException;
import br.uefs.ecomp.winMonster.exceptions.CelulaNulaException;
import br.uefs.ecomp.winMonster.exceptions.CriarMD5NuloException;
import br.uefs.ecomp.winMonster.exceptions.FilaVaziaException;

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

import br.uefs.ecomp.winMonster.util.*;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

public class CriarArquivoTeste {
	private AdministradorController controller = new AdministradorController();
	@Test
	public void testGerarArquivoSucesso() {
		Fila filaprioridade = null;
		Fila dicionario = null;
		controller.setArquivoOriginal("agua");
		
		try {
			filaprioridade=controller.gerarPrioridade("agua");
		} catch (IOException e) {
			fail();
		}
		assertEquals(3, filaprioridade.obterTamanho());
		dicionario = controller.getDicionario();
		assertEquals(3, dicionario.obterTamanho());
		
		String novoarquivo="";
		try {
			novoarquivo=controller.criaArquivo();
		} catch (CaractereInexistenteException e) {
			fail();
		} catch (IOException e) {
			fail();
		} catch (FilaVaziaException e) {
			fail();
		} catch (CelulaNulaException e) {
			fail();
		} catch (CriarMD5NuloException e) {
			fail();
		}
		
		assertEquals("g 10 u 11 a 0 \n\nedbce914409ab6a733e2e527fbe4349b\n\n\n\n010110", novoarquivo);

	}
}
