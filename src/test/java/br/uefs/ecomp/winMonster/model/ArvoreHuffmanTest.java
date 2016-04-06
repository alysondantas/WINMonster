package br.uefs.ecomp.winMonster.model;

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
import br.uefs.ecomp.winMonster.exceptions.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class ArvoreHuffmanTest {
	private Fila dicionario;
	private Fila fila;
	private Arvore arv = new Arvore();
	private CriarObjetos criarObjetos = new CriarObjetos();


	@Test
	public void testInserirHuffmanSucesso() {
		fila = new Fila();
		dicionario = new Fila();
		criarObjetos.inserirFilaPrioridade(fila);
		criarObjetos.inserirDicionario(dicionario);

		arv = new Arvore();

		assertEquals(5, fila.obterTamanho());
		assertEquals(5, dicionario.obterTamanho());

		try{
			arv=arv.inserirHuffman(fila);
		}catch(FilaVaziaException cause){
			fail();
		}


		try{
			arv.construirDicionario(dicionario);
		}catch(FilaVaziaException cause){
			fail();
		}catch(CaractereInexistenteException cause){
			fail();
		}catch(CelulaNulaException cause){
			fail();
		}

		String binario="";
		String caractere="a";
		Celula aux=null;
		MeuIterador iterador=dicionario.iterador();
		while(iterador.temProximo()){
			aux = (Celula) iterador.obterProximo();
			if(aux.getCaractere().equals(caractere)){
				binario=aux.getBinario();
			}
		}
		assertEquals("11", binario);

		binario="";
		caractere="u";
		aux=null;
		iterador=dicionario.iterador();
		while(iterador.temProximo()){
			aux=(Celula) iterador.obterProximo();
			if(aux.getCaractere().equals(caractere)){
				binario=aux.getBinario();
			}
		}
		assertEquals("11", aux.getBinario());

		binario="";
		caractere="z";
		aux=null;
		iterador=dicionario.iterador();
		while(iterador.temProximo()){
			aux=(Celula) iterador.obterProximo();
			if(aux.getCaractere().equals(caractere)){
				binario=aux.getBinario();
			}
		}
		assertEquals("11", aux.getBinario());

		assertEquals(1, fila.obterTamanho());
		assertEquals(5, dicionario.obterTamanho());

	}

	@Test
	public void testInserirHuffmanFilanula() {
		fila=null;
		dicionario=null;

		try{
			arv.inserirHuffman(fila);
			fail();
		}catch(FilaVaziaException cause){
			assertTrue(true);
		}

		try{
			arv.construirDicionario(dicionario);
			fail();
		}catch(FilaVaziaException cause){
			assertTrue(true);
		}catch(CaractereInexistenteException cause){
			fail();
		}catch(CelulaNulaException cause){
			fail();
		}

		assertEquals(null, fila);
		assertEquals(null, dicionario);

	}


	@Test
	public void testInserirHuffmanCaractereInexistente() {
		fila = new Fila();
		dicionario = new Fila();

		String str1="";
		Celula celula = new Celula();
		celula.setChave(3);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		fila.inserir(3, celula);

		celula = new Celula();
		celula.setChave(3);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		dicionario.inserir(3, celula);

		try{
			arv.inserirHuffman(fila);
		}catch(FilaVaziaException cause){
			fail();
		}

		try{
			arv.construirDicionario(dicionario);
			fail();
		}catch(FilaVaziaException cause){
			fail();
		}catch(CaractereInexistenteException cause){
			assertTrue(true);
		}catch(CelulaNulaException cause){
			assertTrue(true);
		}

	}

	@Test
	public void testInserirHuffmanRaizNula() {
		fila = new Fila();
		dicionario = new Fila();

		String str1="a";
		Celula celula = new Celula();
		celula.setChave(3);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		fila.inserir(3, celula);

		celula = new Celula();
		celula.setChave(3);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		dicionario.inserir(3, celula);

		arv.colocaRaiz(null);

		try{
			arv.inserirHuffman(fila);
		}catch(FilaVaziaException cause){
			fail();
		}

		try{
			arv.construirDicionario(dicionario);
			fail();
		}catch(FilaVaziaException cause){
			fail();
		}catch(CaractereInexistenteException cause){
			assertTrue(true);
		}catch(CelulaNulaException cause){
			assertTrue(true);
		}

	}
}
