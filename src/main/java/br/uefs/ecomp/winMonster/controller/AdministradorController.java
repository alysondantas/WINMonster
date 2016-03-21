package br.uefs.ecomp.winMonster.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import br.uefs.ecomp.winMonster.util.*;
import br.uefs.ecomp.winMonster.model.*;
import br.uefs.ecomp.winMonster.exceptions.*;

public class AdministradorController {
	Arvore arvoreHuffman= new Arvore();
	Fila fila = new Fila(); //crio uma fila para salvar a prioridade
	Fila dicionario = new Fila();
	String arquivoOriginal="";

	public Fila getDicionario(){
		return dicionario;
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

	public void escreverArquivo(String texto, String local, String nome) throws IOException { //método deixado aqui só para inspiração. Não vai ser usado do jeito que está descrito no momento
		local = local + nome; //anexo o nome do arquivo ao local que ele será escrito
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(local)); //crio um novo objeto para escrita de arquivos e passo como parâmetro um novo objeto de escrita do arquivo no local especificado
		buffWrite.append(texto); //anexo essa string no arquivo de texto
		buffWrite.close(); //fecho o arquivo aberto
	}

	public Fila gerarPrioridade(String linha) throws IOException { //método para gerar uma fila de prioridade a partir de uma String recebida
		boolean copiaNula = false; //variável para verificar se é necesário criar uma fila cópia. Caso ela continue como false, vai ser gerada uma fila cópia igual a fila a ser retornada
		if(dicionario == null) { //caso a cópia passada seja nula, marque a variável como true
			copiaNula = true;
		}
		while(!linha.isEmpty()) { //enquanto a string não estiver vazia
			int cont = 1; //variável para armazenar quantas vezes a letra se repete na string
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
			linha = linha.replaceAll(aux, ""); //remove todas as ocorrências da primeira letra na string
		} //repete o ciclo
		return fila; //retorno a fila com cada letra da string separada em células com suas respectivas chaves
	}

	public void imprimirFila(Fila fila) {
		MeuIterador it = fila.iterador();
		Celula primeiro = fila.getPrimeiro();
		Celula ultimo = fila.getUltimo();
		Celula aux3 = (Celula)primeiro.getObjeto();
		System.out.println("Primeiro " + aux3.getCaractere());
		aux3 = (Celula)ultimo.getObjeto();
		System.out.println("Ultimo " + aux3.getCaractere());
		System.out.println("Tamanho " + fila.obterTamanho() + "\n");
		while(it.temProximo()) {
			Celula aux = (Celula)it.obterProximo();
			Celula aux2 = (Celula)aux.getObjeto();
			System.out.println("Caractere " + aux2.getCaractere());
			System.out.println("Frequência " + aux.getChave());
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

	public String criaArquivo() throws CaractereInexistenteException, IOException{
		imprimirFila(fila);
		arvoreHuffman = new Arvore();
		arvoreHuffman = arvoreHuffman.inserirHuffman(fila);
		arvoreHuffman.construirDicionario(dicionario);
		Celula celulaCaractere;
		String caractere;
		String novoBinario = "";
		String binariodic;
		String md = md5(arquivoOriginal);
		String dic = "";
		String arquivoNovo = "";
		MeuIterador iterador;
		for (int j = 0; j < arquivoOriginal.length(); j++) { //Repetição: Início = Segunda letra da String; Condição = Até o fim da String
			iterador=dicionario.iterador();
			while(iterador.temProximo()){
				celulaCaractere = (Celula) iterador.obterProximo();
				caractere = celulaCaractere.getCaractere();
				binariodic = celulaCaractere.getBinario();
				if (caractere.charAt(0) == arquivoOriginal.charAt(j)) { //caso a letra do dicionario seja igual a letra atual
					novoBinario=novoBinario + binariodic;//binario concatena com o binario do dicionario
				}
			}
		} //repete o ciclo
		iterador=dicionario.iterador();
		while(iterador.temProximo()){
			celulaCaractere = (Celula) iterador.obterProximo();
			caractere = celulaCaractere.getCaractere();
			binariodic = celulaCaractere.getBinario();
			dic = dic + caractere + " " + binariodic + " ";
		}
		arquivoNovo = dic + "\n" + "\n" + md + "\n" + "\n" + novoBinario;
		return arquivoNovo;
	}

	public String descompacta(String local) throws IOException, DescompactarStringNulaException{
		String arquivo=lerArquivo(local);//recebe o arquivo a ser descompactado pelo seu local
		dicionario = new Fila();
		MeuIterador it;
		String dic = "";
		String binario = "";
		String md5= "";

		////////////SEPARAÇÃO DA STRING DO ARQUIVO ORIGINAL//////////////////
		
		//separo a partir da última ocorrência de "\n\n" até o fim da string e salvo na String binario
		binario = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//recorto o pedaço que eu acabei de salvar em "binario" da String do arquivo original
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n"));
		//separo novamente a partir da última ocorrência de "\n\n" até o fim da string e salvo na String md5
		md5 = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//recorto o pedaço que eu salvei em md5 da string do arquivo original
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n"));
		//o restante que sobrou da string original é só o dicionário
		dic = arquivo;
		
		/////////////SEPARAÇÃO DO DICIONÁRIO////////////////
		
		//enquanto a String do dicionário não estiver vazia
		while(!dic.isEmpty()) {
			//Salvo o primeiro caractere da String numa String auxiliar chamada String caractere
			String caractere = dic.substring(0, 1); 
			//recorto o que eu acabei de salvar da String do dicionario + o próximo " "
			dic = dic.substring(2, dic.length());
			//Salvo em uma String binariodic tudo o que vier desde o começo de "dicionario" até o próximo " ", ou seja, o próximo binário
			String binariodic = dic.substring(0,dic.indexOf(" "));
			//recorto o que eu acabei de salvar da String dicionário + o próximo " "
			dic = dic.substring(0, dic.indexOf(" ") + 1);
			//Crio uma nova célula de frequência 1 com o caractere que eu salvei anteriormente como conteúdo
			Celula cel = new Celula(0, caractere);
			//digo que o binário dessa nova célula é o binariodic
			cel.setBinario(binariodic);
			//insiro na fila de dicionário a nova célula criada
			dicionario.inserir(1, cel);
		}
		
		////////////TRADUÇÃO DO BINÁRIO RECEBIDO///////////////
		String aux = binario.substring(0, 1);
		String traducao = "";
		it = dicionario.iterador();
		while(it.temProximo()) {
			Celula caux = (Celula)it.obterProximo();
			String binaux = caux.getBinario();
			if(caux.getChave() == 0) {
				for(int i = 1; aux != binaux && i < binario.length(); i++) {
					aux = binario.substring(0, i);
				}
				if (aux == binaux) {
					traducao = traducao + (String)caux.getObjeto();
					caux.setChave(1);
				}
			}
		}
		
		return md5;
		}
	
	public String compactarArquivo(String local){
		fila.limpar();
		dicionario.limpar();
		try {
			gerarPrioridade(lerArquivo(local));
			String arq = criaArquivo();
			return arq;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CaractereInexistenteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
