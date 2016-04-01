package br.uefs.ecomp.winMonster.controller;

import br.uefs.ecomp.winMonster.model.*;
import br.uefs.ecomp.winMonster.util.*;
import br.uefs.ecomp.winMonster.controller.*;
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
