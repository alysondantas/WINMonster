package br.uefs.ecomp.winMonster.controller;

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

public class GerarPrioridadeTest {
	private AdministradorController controller = new AdministradorController();

	@Test
	public void testGerarFilasSucesso1() {
		Fila filaprioridade = null;
		Fila dicionario = null;
		try {
			filaprioridade=controller.gerarPrioridade("agua");
		} catch (IOException e) {
			fail();
		}
		assertEquals(3, filaprioridade.obterTamanho());
		dicionario = controller.getDicionario();
		assertEquals(3, dicionario.obterTamanho());
	}

	@Test
	public void testGerarFilasSucesso2() {
		Fila filaprioridade = null;
		Fila dicionario = null;
		try {
			filaprioridade=controller.gerarPrioridade("agua azul chinelo velho");
		} catch (IOException e) {
			fail();
		}
		assertEquals(13, filaprioridade.obterTamanho());
		dicionario = controller.getDicionario();
		assertEquals(13, dicionario.obterTamanho());
	}

	@Test
	public void testGerarFilasSucesso3() {
		Fila filaprioridade = null;
		Fila dicionario = null;
		try {
			filaprioridade=controller.gerarPrioridade("agua azul com chinelo velho vai virar agua preta!");
		} catch (IOException e) {
			fail();
		}
		assertEquals(18, filaprioridade.obterTamanho());
		dicionario = controller.getDicionario();
		assertEquals(18, dicionario.obterTamanho());
	}

	@Test
	public void testGerarFilasSucesso4() {
		Fila filaprioridade = null;
		Fila dicionario = null;
		try {
			filaprioridade=controller.gerarPrioridade("");
		} catch (IOException e) {
			fail();
		}
		assertEquals(0, filaprioridade.obterTamanho());
		dicionario = controller.getDicionario();
		assertEquals(0, dicionario.obterTamanho());
	}
}
