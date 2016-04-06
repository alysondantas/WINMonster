package br.uefs.ecomp.winMonster.controller;

/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 06/04/2016

Declaro que este código foi elaborado por esta dupla e não contém nenhum

trecho de código de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código

de outra autoria que não a minha está destacado com uma citação para o autor e a fonte

do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ******************************************************************************************/

import br.uefs.ecomp.winMonster.exceptions.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class VerificarIntegridadeTest {
	private AdministradorController controller = new AdministradorController();

	@Test
	public void testIntegridadeSucesso() {
		String string = "aguaazul";
		String md5 = null;
		try {
			md5 = controller.md5(string);
		} catch (CriarMD5NuloException cause) {
			fail();
		}
		assertEquals("44afc15091216f3de10d3efd5d035071", md5);
	}

	@Test
	public void testIntegridadeVazio() {
		String string = "";
		String md5 = null;
		try {
			md5 = controller.md5(string);
		} catch (CriarMD5NuloException cause) {
			assertTrue(true);
		}
		assertEquals("d41d8cd98f00b204e9800998ecf8427e", md5);
	}

	@Test
	public void testIntegridadeNulo() {
		String string = null;
		String md5 = null;
		try {
			md5 = controller.md5(string);
		} catch (CriarMD5NuloException cause) {
			assertTrue(true);
		}
		assertEquals(null, md5);
	}

}
