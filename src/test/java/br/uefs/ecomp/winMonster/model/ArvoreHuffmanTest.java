package br.uefs.ecomp.winMonster.model;

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
