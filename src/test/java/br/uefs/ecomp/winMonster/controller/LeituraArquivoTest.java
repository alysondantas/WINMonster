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
		String local = "src\\test\\java\\br\\uefs\\ecomp\\winMonster\\util\\teste.txt";
		String c = null;
		try {
			c=controller.lerArquivo(local);
		} catch (IOException e) {
			fail();
		}
		
		assertEquals("agua", c);
		
	}
	
	@Test
	public void testLerArquivoInexistente() {
		String local = "src\\test\\java\\br\\uefs\\ecomp\\winMonster\\util\\teste3.txt";
		String c = null;
		try {
			c=controller.lerArquivo(local);
		} catch (IOException e) {
			assertTrue(true);
		}
		
		assertEquals(null, c);	
	}
	
	@Test
	public void testLerArquivoGrandeSucesso() {
		String local = "src\\test\\java\\br\\uefs\\ecomp\\winMonster\\util\\teste2.txt";
		String c = null;
		try {
			c=controller.lerArquivo(local);
		} catch (IOException e) {
			fail();
		}
		
		assertEquals("agua azul com chileno velho e arquivo com string meio grande aqui... Vai que não funciona quando o arquivo tem mais de 10 palvras, numeros como 12345 e outros caracteres como %$#*&¨@!? vai que né?", c);
		
	}
	
	@Test
	public void testLerArquivoVazio() {
		String local = "src\\test\\java\\br\\uefs\\ecomp\\winMonster\\util\\teste4.txt";
		String c = null;
		try {
			c=controller.lerArquivo(local);
		} catch (IOException e) {
			assertTrue(true);
		}
		
		assertEquals("", c);
		
	}
}
