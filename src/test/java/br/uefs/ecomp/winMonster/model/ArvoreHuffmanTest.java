package br.uefs.ecomp.winMonster.model;

import br.uefs.ecomp.winMonster.model.*;
import br.uefs.ecomp.winMonster.util.*;
import br.uefs.ecomp.winMonster.controller.*;
import br.uefs.ecomp.winMonster.exceptions.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArvoreHuffmanTest {
	private AdministradorController controller;
	private Fila dicionario;
	private Fila fila;
	private Arvore arv = new Arvore();
	private CriarObjetos criarObjetos = new CriarObjetos();
	
	
	@Test
	public void testInserirHuffmanSucesso() {
		fila = new Fila();
		dicionario = new Fila();
		criarObjetos.inserirFilaPrioridade(dicionario);
		criarObjetos.inserirFilaPrioridade(fila);
		
		arv = new Arvore();
		
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
		
		String binario="1221";
		String caractere="a";
		Celula aux=null;
		Celula aux2=null;
		MeuIterador iterador=dicionario.iterador();
		while(iterador.temProximo()){
			aux = (Celula) iterador.obterProximo();
			aux2 = (Celula) aux.getObjeto();
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
		assertEquals("01", aux.getBinario());
		
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
		assertEquals("101", aux.getBinario());
		
		assertEquals(5, fila.obterTamanho());
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
