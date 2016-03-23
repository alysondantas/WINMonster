package br.uefs.ecomp.winMonster.util;

import br.uefs.ecomp.winMonster.model.*;
import br.uefs.ecomp.winMonster.util.*;

public class CriarObjetos {
	String str="agua azul";
	
	public Fila inserirFilaPrioridade(Fila fila){
		String str1="a";
		Celula celula = new Celula();
		celula.setChave(3);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		fila.inserir(3, celula);
		
		str1="g";
		celula = new Celula();
		celula.setChave(1);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		fila.inserir(1, celula);
		
		str1="u";
		celula = new Celula();
		celula.setChave(2);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		fila.inserir(2, celula);
		
		str1="z";
		celula = new Celula();
		celula.setChave(1);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		fila.inserir(1, celula);
		
		str1="l";
		celula = new Celula();
		celula.setChave(1);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		fila.inserir(1, celula);
		
		str1="\n";
		celula = new Celula();
		celula.setChave(1);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		fila.inserir(1, celula);
		
		return null;
	}
}
