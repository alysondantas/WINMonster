package br.uefs.ecomp.winMonster.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import br.uefs.ecomp.winMonster.util.*;
import br.uefs.ecomp.winMonster.exceptions.*;

public class AdministradorController {
	Arvore arvoreHuffman= new Arvore();
	Fila fila = new Fila(); //crio uma fila para salvar a prioridade
	Fila dicionario = new Fila();
	String arquivoOriginal="";

	public Fila getFilaPrioridade(){
		return fila;
	}


	public String lerArquivo(String local) throws IOException { //método que lê um arquivo de texto e converte todo o seu conteúdo para uma String
		FileReader arq = new FileReader(local); //inicializo arq como a leitura de arquivos no local especificado
		BufferedReader buffRead = new BufferedReader(arq); //crio um novo objeto BuffReader e passo para ele a leitura do local em arq
		String linha = buffRead.readLine(); //crio uma string auxiliar e já armazeno a primeira linha do arquivo
		String c = ""; //crio uma string auxiliar para armazenar o conteúdo da string

		while(linha != null) { //enquanto não chegar no fim do arquivo
			c = c + linha; //salvo o a letra da string na posição atual
			c = c + "\n"; //acrescento uma quebra de linha em "c" a cada fim de linha em "linha"
			linha = buffRead.readLine(); //salvo a linha atual do arquivo na string
		} arq.close(); //fecho o arquivo para a leitura

		arquivoOriginal=c;//Uma varaivel local recebe a string com todo o conteudo do arquivo
		return c; //retorna a String com todo o conteúdo do arquivo de texto
	}

	public void escreverArquivo(String local) throws IOException { //método deixado aqui só para inspiração. Não vai ser usado do jeito que está descrito no momento
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(local)); //crio um novo objeto para escrita de arquivos e passo como parâmetro um novo objeto de escrita do arquivo no local especificado
		String linha = ""; //crio uma string auxiliar para gravar a linha do arquivo e a inicializo como vazia
		Scanner in = new Scanner(System.in); //inicializo um objeto para capturar os dados de entrada do usuário
		System.out.println("Escreva algo: "); 
		linha = in.nextLine(); //capturo a próxima string que o usuário digitar
		buffWrite.append(linha); //anexo essa string no arquivo de texto
		buffWrite.close(); //fecho o arquivo aberto
	}

	public Fila gerarPrioridade(String linha) throws IOException { //método para gerar uma fila de prioridade a partir de uma String recebida
		boolean copiaNula = false; //variável para verificar se é necesário criar uma fila cópia. Caso ela continue como false, vai ser gerada uma fila cópia igual a fila a ser retornada
		if(dicionario == null) { //caso a cópia passada seja nula, marque a variável como true
			copiaNula = true;
		}
		while(linha != "") { //enquanto a string não estiver vazia
			int cont = 0; //variável para armazenar quantas vezes a letra se repete na string
			String aux; //string auxiliar para ajudar nas operações
			for (int j = 1; j < linha.length(); j++) { //Repetição: Início = Segunda letra da String; Condição = Até o fim da String
				aux = Character.toString(linha.charAt(j)); //Converte o caractere da string na posição atual para uma string separada para poder ser manipulado
				if (linha.charAt(0) == linha.charAt(j)) { //caso a primeira letra seja igual a letra atual
					cont++; //incrementa um no contador
				}
			}
			Celula auxCel = new Celula();
			auxCel.setCaractere(linha.substring(0,1));
			fila.inserir(cont, auxCel); //crio uma string somente com a primeira letra de linha e insiro ela na fila, passando também o número de vezes que a letra se repete como chave
			if(copiaNula == false) //caso a cópia não seja nula, preencha a fila cópia
				dicionario.inserir(cont, linha.substring(0,1)); //preencho a cópia com o mesmo conteúdo da fila original
			aux = Character.toString(linha.charAt(0)); //atribui agora a primeira letra da string ao valor de aux
			linha = linha.replace(aux, ""); //remove todas as ocorrências da primeira letra na string
		} //repete o ciclo
		return fila; //retorno a fila com cada letra da string separada em células com suas respectivas chaves
	}

	public void imprimirFila(Fila fila) {
		MeuIterador it = fila.iterador();
		while(it.temProximo()) {
			Celula aux = (Celula)it.obterProximo();
			System.out.println(aux.getCaractere());
			System.out.println(aux.getChave());
			System.out.println(aux.getBinario());
		}
	}

	public String md5(String string){//pra ser bem sicero.. não sei explicar isso ainda não
		String novomd5 = "";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger hash = new BigInteger(1, md.digest(string.getBytes()));
		novomd5 = hash.toString(16);			
		return novomd5;
	}

	public void criaArquivo() throws CaractereInexistenteException{
		arvoreHuffman=arvoreHuffman.inserirHuffman(fila);
		arvoreHuffman.construirDicionario(dicionario);
		Celula celulaCaractere;
		String caractere;
		String novoBinario="";
		String binariodic;
		MeuIterador iterador=dicionario.iterador();
		for (int j = 0; j < arquivoOriginal.length(); j++) { //Repetição: Início = Segunda letra da String; Condição = Até o fim da String
			while(iterador.temProximo()){
				celulaCaractere=(Celula) iterador.obterProximo();
				caractere=celulaCaractere.getCaractere();
				binariodic=celulaCaractere.getBinario();
				if (caractere.charAt(0) == arquivoOriginal.charAt(j)) { //caso a letra do dicionario seja igual a letra atual
					novoBinario=novoBinario+binariodic;//binario concatena com o binario do dicionario
				}
			}
		} //repete o ciclo
	}
}
