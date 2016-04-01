package br.uefs.ecomp.winMonster.controller;

import br.uefs.ecomp.winMonster.model.*;
import br.uefs.ecomp.winMonster.util.*;
import br.uefs.ecomp.winMonster.controller.*;
import br.uefs.ecomp.winMonster.exceptions.*;
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
