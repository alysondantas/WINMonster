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
		fila=criarObjetos.inserirFilaPrioridade(fila);
		dicionario=criarObjetos.inserirFilaPrioridade(dicionario);
		
		try{
			arv.inserirHuffman(fila);
		}catch(FilaVaziaException cause){
			fail();
		}
		
		try{
			arv.construirDicionario(dicionario);
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
			aux=(Celula) iterador.obterProximo();
			if(aux.getCaractere().equals(caractere)){
				binario=aux.getBinario();
			}
		}
		assertEquals("10", aux.getBinario());
		
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
		assertEquals("111", aux.getBinario());
		
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
		assertEquals("010", aux.getBinario());
		
		assertEquals(6, fila.obterTamanho());
		assertEquals(6, dicionario.obterTamanho());
		
	}
	
	@Test
	public void testInserirHuffmanFilanula() {
		fila=null;
		dicionario=null;
		
		try{
			arv.inserirHuffman(fila);
		}catch(FilaVaziaException cause){
			fail();
		}
		
		try{
			arv.construirDicionario(dicionario);
		}catch(CaractereInexistenteException cause){
			fail();
		}catch(CelulaNulaException cause){
			fail();
		}
		
		assertEquals(0, fila.obterTamanho());
		assertEquals(0, dicionario.obterTamanho());
		
	}
}
