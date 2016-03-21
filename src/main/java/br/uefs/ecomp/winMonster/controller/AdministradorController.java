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


	public String lerArquivo(String local) throws IOException { //m�todo que l� um arquivo de texto e converte todo o seu conte�do para uma String
		FileReader arq = new FileReader(local); //inicializo arq como a leitura de arquivos no local especificado
		BufferedReader buffRead = new BufferedReader(arq); //crio um novo objeto BuffReader e passo para ele a leitura do local em arq
		String linha = buffRead.readLine(); //crio uma string auxiliar e j� armazeno a primeira linha do arquivo
		String c = ""; //crio uma string auxiliar para armazenar o conte�do da string

		while(linha != null) { //enquanto n�o chegar no fim do arquivo
			c = c + linha; //salvo o a letra da string na posi��o atual
			c = c + "\n"; //acrescento uma quebra de linha em "c" a cada fim de linha em "linha"
			linha = buffRead.readLine(); //salvo a linha atual do arquivo na string
		} arq.close(); //fecho o arquivo para a leitura

		arquivoOriginal=c;//Uma varaivel local recebe a string com todo o conteudo do arquivo
		return c; //retorna a String com todo o conte�do do arquivo de texto
	}

	public void escreverArquivo(String texto, String local, String nome) throws IOException { //m�todo deixado aqui s� para inspira��o. N�o vai ser usado do jeito que est� descrito no momento
		local = local + nome; //anexo o nome do arquivo ao local que ele ser� escrito
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(local)); //crio um novo objeto para escrita de arquivos e passo como par�metro um novo objeto de escrita do arquivo no local especificado
		buffWrite.append(texto); //anexo essa string no arquivo de texto
		buffWrite.close(); //fecho o arquivo aberto
	}

	public Fila gerarPrioridade(String linha) throws IOException { //m�todo para gerar uma fila de prioridade a partir de uma String recebida
		boolean copiaNula = false; //vari�vel para verificar se � neces�rio criar uma fila c�pia. Caso ela continue como false, vai ser gerada uma fila c�pia igual a fila a ser retornada
		if(dicionario == null) { //caso a c�pia passada seja nula, marque a vari�vel como true
			copiaNula = true;
		}
		while(!linha.isEmpty()) { //enquanto a string n�o estiver vazia
			int cont = 1; //vari�vel para armazenar quantas vezes a letra se repete na string
			String aux; //string auxiliar para ajudar nas opera��es
			for (int j = 1; j < linha.length(); j++) { //Repeti��o: In�cio = Segunda letra da String; Condi��o = At� o fim da String
				aux = Character.toString(linha.charAt(j)); //Converte o caractere da string na posi��o atual para uma string separada para poder ser manipulado
				if (linha.charAt(0) == linha.charAt(j)) { //caso a primeira letra seja igual a letra atual
					cont++; //incrementa um no contador
				}
			}
			Celula auxCel = new Celula();
			auxCel.setCaractere(linha.substring(0,1));
			fila.inserir(cont, auxCel); //crio uma string somente com a primeira letra de linha e insiro ela na fila, passando tamb�m o n�mero de vezes que a letra se repete como chave
			if(copiaNula == false) //caso a c�pia n�o seja nula, preencha a fila c�pia
				dicionario.inserir(cont, linha.substring(0,1)); //preencho a c�pia com o mesmo conte�do da fila original
			aux = Character.toString(linha.charAt(0)); //atribui agora a primeira letra da string ao valor de aux
			linha = linha.replaceAll(aux, ""); //remove todas as ocorr�ncias da primeira letra na string
		} //repete o ciclo
		return fila; //retorno a fila com cada letra da string separada em c�lulas com suas respectivas chaves
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
			System.out.println("Frequ�ncia " + aux.getChave());
		}
	}

	public String md5(String string){//pra ser bem sicero.. n�o sei explicar isso ainda n�o
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
		for (int j = 0; j < arquivoOriginal.length(); j++) { //Repeti��o: In�cio = Segunda letra da String; Condi��o = At� o fim da String
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

		////////////SEPARA��O DA STRING DO ARQUIVO ORIGINAL//////////////////
		
		//separo a partir da �ltima ocorr�ncia de "\n\n" at� o fim da string e salvo na String binario
		binario = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//recorto o peda�o que eu acabei de salvar em "binario" da String do arquivo original
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n"));
		//separo novamente a partir da �ltima ocorr�ncia de "\n\n" at� o fim da string e salvo na String md5
		md5 = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//recorto o peda�o que eu salvei em md5 da string do arquivo original
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n"));
		//o restante que sobrou da string original � s� o dicion�rio
		dic = arquivo;
		
		/////////////SEPARA��O DO DICION�RIO////////////////
		
		//enquanto a String do dicion�rio n�o estiver vazia
		while(!dic.isEmpty()) {
			//Salvo o primeiro caractere da String numa String auxiliar chamada String caractere
			String caractere = dic.substring(0, 1); 
			//recorto o que eu acabei de salvar da String do dicionario + o pr�ximo " "
			dic = dic.substring(2, dic.length());
			//Salvo em uma String binariodic tudo o que vier desde o come�o de "dicionario" at� o pr�ximo " ", ou seja, o pr�ximo bin�rio
			String binariodic = dic.substring(0,dic.indexOf(" "));
			//recorto o que eu acabei de salvar da String dicion�rio + o pr�ximo " "
			dic = dic.substring(0, dic.indexOf(" ") + 1);
			//Crio uma nova c�lula de frequ�ncia 1 com o caractere que eu salvei anteriormente como conte�do
			Celula cel = new Celula(0, caractere);
			//digo que o bin�rio dessa nova c�lula � o binariodic
			cel.setBinario(binariodic);
			//insiro na fila de dicion�rio a nova c�lula criada
			dicionario.inserir(1, cel);
		}
		
		////////////TRADU��O DO BIN�RIO RECEBIDO///////////////
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
