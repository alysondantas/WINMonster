package br.uefs.ecomp.winMonster.util;

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

public class CriarObjetos {
	String str="aguaazul";

	public void inserirFilaPrioridade(Fila fila){
		String str1="a";
		Celula celula = new Celula();
		celula.setChave(3);
		celula.setObjeto(str1);
		celula.setCaractere(str1);
		fila.inserir(3, celula);

		str1="g";
		Celula celulag = new Celula();
		celulag.setChave(1);
		celulag.setObjeto(str1);
		celulag.setCaractere(str1);
		fila.inserir(1, celulag);

		str1="u";
		Celula celulau = new Celula();
		celulau.setChave(2);
		celulau.setObjeto(str1);
		celulau.setCaractere(str1);
		fila.inserir(2, celulau);

		str1="z";
		Celula celulaz = new Celula();
		celulaz.setChave(1);
		celulaz.setObjeto(str1);
		celulaz.setCaractere(str1);
		fila.inserir(1, celulaz);

		str1="l";
		Celula celulal = new Celula();
		celulal.setChave(1);
		celulal.setObjeto(str1);
		celulal.setCaractere(str1);
		fila.inserir(1, celulal);

	}

	public void inserirDicionario(Fila fila){
		String str1="a";
		fila.inserir(3, str1);

		str1="g";
		fila.inserir(1, str1);

		str1="u";
		fila.inserir(2, str1);

		str1="z";
		fila.inserir(1, str1);

		str1="l";
		fila.inserir(1, str1);

	}
}
