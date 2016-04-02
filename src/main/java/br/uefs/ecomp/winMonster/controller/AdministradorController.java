package br.uefs.ecomp.winMonster.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.channels.ShutdownChannelGroupException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import br.uefs.ecomp.winMonster.util.*;
import br.uefs.ecomp.winMonster.model.*;
import br.uefs.ecomp.winMonster.exceptions.*;

public class AdministradorController {
	Arvore arvoreHuffman= new Arvore();
	Fila fila = new Fila(); //crio uma fila para salvar a prioridade
	Fila dicionario = new Fila(); //fila para salvar o dicion�rio
	String arquivoOriginal=""; //String para salvar o conte�do do arquivo original antes da compacta��o

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
			linha = buffRead.readLine(); //salvo a linha atual do arquivo na string
			if(linha!=null)
				c = c + "\n"; //acrescento uma quebra de linha em "c" a cada fim de linha em "linha"
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
	
	
	//APAGAR ESSE M�TODO DEPOIS
	
	/* public void escreverBits(String texto, String local) throws IOException {
		int i;
		String binario = texto.substring(texto.lastIndexOf("\n\n") + 2, texto.length());
		boolean [] b = new boolean[binario.length()];
		for (i = 0; i < binario.length(); i++) {
			if (binario.charAt(i) == 0) {
				b[i] = false;
			}
			else {
				b[i] = true;
			}
		}
		FileOutputStream fos = new FileOutputStream(local);
		DataOutputStream dos = new DataOutputStream(fos);
		for (i=0; i< binario.length(); i++) {
			dos.writeBoolean(b[i]);
		}
		dos.close();
	} */

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
	
	//APAGAR ESSE M�TODO DEPOIS

	/* public void imprimirFila(Fila fila) {
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
	} */

	public String md5(String string) throws CriarMD5NuloException{//verifica��o de integridade atravez de md5
		String novomd5 = "";//inicializa a variavel para receber o novo md5
		if(string == null){//se a string passada for nula � um erro
			throw new CriarMD5NuloException();//caso a string do md5 seja nulo
		}
		MessageDigest md = null; // variavel que recebe a criptografia hash com 128 bits para o md5
		try {
			md = MessageDigest.getInstance("MD5"); // md recebe uma instancia do algoritimo do MD (MessageDigest)
		} catch (NoSuchAlgorithmException e) {//se houver erro no algoritomo
			e.printStackTrace();
		}
		BigInteger hash;
		hash = new BigInteger(1, md.digest(string.getBytes()));
		novomd5 = hash.toString(16);	
		if(novomd5 == ""){
			throw new CriarMD5NuloException();//caso a string do md5 seja "" significa que o md5 foi escrito errado
		}
		return novomd5;
	}

	public String criaArquivo() throws CaractereInexistenteException, IOException, FilaVaziaException, CelulaNulaException, CriarMD5NuloException{
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
					novoBinario = novoBinario + binariodic;//binario concatena com o binario do dicionario
				}
			}
		} //repete o ciclo
		iterador=dicionario.iterador();
		while(iterador.temProximo()){
			celulaCaractere = (Celula) iterador.obterProximo();
			caractere = celulaCaractere.getCaractere();
			if(caractere.equals("\n"))
				caractere = "\\n";
			binariodic = celulaCaractere.getBinario();
			dic = dic + caractere + " " + binariodic + " ";
		}
		arquivoNovo = dic + "\n" + "\n" + md + "\n" + "\n" + novoBinario;
		return arquivoNovo;
	}

	public String descompacta(String arquivo) throws IOException, DescompactarStringNulaException{
		dicionario = new Fila();
		MeuIterador it;
		String dic = ""; //String para salvar o dicion�rio
		String binario = ""; //String para salvar a BitString
		String md5= ""; //String para salvar o md5

		////////////SEPARA��O DA STRING DO ARQUIVO ORIGINAL//////////////////
		
		//separo a partir da �ltima ocorr�ncia de "\n\n" at� o fim da string e salvo na String binario
		binario = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//recorto o peda�o que eu acabei de salvar em "binario" da String do arquivo original
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n"));
		//separo novamente a partir da �ltima ocorr�ncia de "\n\n" at� o fim da string e salvo na String md5
		//md5 = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//recorto o peda�o que eu salvei em md5 da string do arquivo original
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n"));
		//o restante que sobrou da string original � s� o dicion�rio
		dic = arquivo;
		
		/////////////SEPARA��O DO DICION�RIO////////////////
		
		//enquanto a String do dicion�rio n�o estiver vazia

		while(!dic.isEmpty()) {
			//Salvo o primeiro caractere da String numa String auxiliar chamada String caractere
			String caractere = dic.substring(0, 1);
			if(caractere.equals("\\")) {
				caractere = dic.substring(0, 2);
				dic = dic.substring(3, dic.length());
			} else
			//recorto o que eu acabei de salvar da String do dicionario + o pr�ximo " "
				dic = dic.substring(2, dic.length());
			//Salvo em uma String binariodic tudo o que vier desde o come�o de "dicionario" at� o pr�ximo " ", ou seja, o pr�ximo bin�rio
			String binariodic = dic.substring(0,dic.indexOf(" "));
			//recorto o que eu acabei de salvar da String dicion�rio + o pr�ximo " "
			dic = dic.substring(dic.indexOf(" ") + 1, dic.length());
			//Crio uma nova c�lula de frequ�ncia 1 com o caractere que eu salvei anteriormente como conte�do
			Celula cel = new Celula(0, caractere);
			//digo que o bin�rio dessa nova c�lula � o binariodic
			cel.setBinario(binariodic);
			//insiro na fila de dicion�rio a nova c�lula criada
			dicionario.inserir(1, cel);
		}
		
		////////////TRADU��O DO BIN�RIO RECEBIDO///////////////
		String traducao = ""; //crio uma string para armazenar a tradu��o e inicalizo ela como vazia
		it = dicionario.iterador(); //fa�o it iterar a fila dicion�rio
		
		while(!binario.isEmpty()) { //enquanto o bin�rio n�o estiver completamente traduzido
			String aux = binario.substring(0, 1); //crio uma string auxiliar e inicalizo ela com o primeiro caractere da String binario
			while(it.temProximo()) {  //in�cio do ciclo de itera��o
				Celula caux = (Celula)it.obterProximo();
				caux = (Celula)caux.getObjeto();
				String binaux = caux.getBinario(); //salvo o bin�rio presente em cada uma das c�lulas em binaux
				for(int i = 1; !aux.equals(binaux) && i <= binario.length(); i++) { //crio um ciclo de repeti��es para ir aumentando a substring de binario de um em um elemento e comparando ele com o bin�rio da c�lula iterada enquanto essa substring n�o for igual ao bin�rio da c�lula. 
					aux = binario.substring(0, i);
				}
				if (aux.equals(binaux)) { //ap�s o fim do ciclo de repeti��es, caso tenha encontrado uma substring que seja igual ao bin�rio da c�lula
					String str = (String)caux.getObjeto();
					if(str.equals("\\n")) { //tratamento para caso tenha um \n como objeto da c�lula
						traducao = traducao + "\n";
					}
					else {
						traducao = traducao + str; //coloco a letra relacionada com o bin�rio encontrada na tradu��o
					}
					binario = binario.substring(binaux.length(), binario.length()); //retira a parte traduzida do bin�rio
					if(!binario.isEmpty()){ //caso o bin�rio n�o esteja vazio ainda, prepara ele para um novo ciclo
					aux = binario.substring(0, 1); //igualo a String aux com o primeiro caractere da String binario
					it = dicionario.iterador(); //volta o iterador para o in�cio do dicionario
					}
				}
			} //fim do ciclo de itera��o
		}
		
		return traducao;
		}
	
	public String compactarArquivo(String local) throws FilaVaziaException, CelulaNulaException, CriarMD5NuloException, IOException, CaractereInexistenteException{ //m�todo para gerar uma String com o conte�do para a grava��o do arquivo
		fila.limpar(); //limpa a fila do controller
		dicionario.limpar(); //limpa o dicionario do controller
		gerarPrioridade(lerArquivo(local)); //gera a fila com as letras e suas frequ�ncias do conteudo lido pelo arquivo no local especificado no par�metro
		String arq = criaArquivo(); //prepara o conteudo do arquivo para a grava��o com dicionario, md5 e bin�rio e salva na String arq
		return arq; //retorna o conte�do pronto para a grava��o
	}
	
	public String recuperarMd5(String local) throws IOException { //m�todo para recuperar o md5 de dentro de um arquivo compactado
		String md5 = ""; //inicializo a String para salvar o md5 como vazia
		String arquivo = lerArquivo(local); //le o conteudo do arquivo no local especificado por par�metro do m�todo
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n")); //remove o a BitString do arquivo
		md5 = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length()); //remove o md5 e salva ele na String
		return md5; //retorna o md5 salvo
	}

}
