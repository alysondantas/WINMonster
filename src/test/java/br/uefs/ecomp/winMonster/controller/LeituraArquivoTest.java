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

	@Test
	public void testLerCaractereEspecialSucesso() {
		String local = "src\\test\\java\\br\\uefs\\ecomp\\winMonster\\util\\teste5.txt";
		String c = null;
		try {
			c=controller.lerArquivo(local);
		} catch (IOException e) {
			fail();
		}

		assertEquals("|\\+-;.,]}[{´~:¢¬°ºª§", c);

	}

	@Test
	public void testLerHTMLSucesso() {
		String local = "src\\test\\java\\br\\uefs\\ecomp\\winMonster\\util\\testehtml.html";
		String c = null;
		try {
			c=controller.lerArquivo(local);
		} catch (IOException e) {
			fail();
		}

		assertEquals("<!DOCTYPE html><html class='html' lang='pt-BR'><head></head><body> <div class='gradient clearfix' id='page'><!-- column --> </div> <!--[if lt IE 9]>  <div class='preload_images'>   <img class='preload' src='images/u1820-r-grad.png' alt=''/>  </div>  <![endif]--></script></script> </body></html>", c);
	}

	@Test
	public void testLerCouCPPSucesso() {
		String local = "src\\test\\java\\br\\uefs\\ecomp\\winMonster\\util\\testec.c";
		String c = null;
		try {
			c=controller.lerArquivo(local);
		} catch (IOException e) {
			fail();
		}

		assertEquals("#include <stdio.h>#include <stdlib.h>/* run this program using the console pauser or add your own getch, system('pause') or input loop */int main(int argc, char *argv[]) {return 0;}", c);
	}
}
