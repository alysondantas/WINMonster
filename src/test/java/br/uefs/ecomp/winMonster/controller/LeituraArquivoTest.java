package br.uefs.ecomp.winMonster.controller;

import br.uefs.ecomp.winMonster.model.*;
import br.uefs.ecomp.winMonster.util.*;
import br.uefs.ecomp.winMonster.controller.*;
import br.uefs.ecomp.winMonster.exceptions.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class LeituraArquivoTest {
	private AdministradorController controller = new AdministradorController();
	
	@Test
	public void testLerArquivoSucesso() {
		String local = "";
		String c = null;
		try {
			controller.lerArquivo(local);
		} catch (IOException e) {
			fail();
		}
		
		assertEquals("agua", c);
		
	}
}
