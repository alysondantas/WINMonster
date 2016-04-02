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
	Fila dicionario = new Fila(); //fila para salvar o dicionário
	String arquivoOriginal=""; //String para salvar o conteúdo do arquivo original antes da compactação

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
			linha = buffRead.readLine(); //salvo a linha atual do arquivo na string
			if(linha!=null)
				c = c + "\n"; //acrescento uma quebra de linha em "c" a cada fim de linha em "linha"
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
	
	
	//APAGAR ESSE MÉTODO DEPOIS
	
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
	
	//APAGAR ESSE MÉTODO DEPOIS

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
			System.out.println("Frequência " + aux.getChave());
		}
	} */

	public String md5(String string) throws CriarMD5NuloException{//verificação de integridade atravez de md5
		String novomd5 = "";//inicializa a variavel para receber o novo md5
		if(string == null){//se a string passada for nula é um erro
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
		for (int j = 0; j < arquivoOriginal.length(); j++) { //Repetição: Início = Segunda letra da String; Condição = Até o fim da String
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
		String dic = ""; //String para salvar o dicionário
		String binario = ""; //String para salvar a BitString
		String md5= ""; //String para salvar o md5

		////////////SEPARAÇÃO DA STRING DO ARQUIVO ORIGINAL//////////////////
		
		//separo a partir da última ocorrência de "\n\n" até o fim da string e salvo na String binario
		binario = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//recorto o pedaço que eu acabei de salvar em "binario" da String do arquivo original
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n"));
		//separo novamente a partir da última ocorrência de "\n\n" até o fim da string e salvo na String md5
		//md5 = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//recorto o pedaço que eu salvei em md5 da string do arquivo original
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n"));
		//o restante que sobrou da string original é só o dicionário
		dic = arquivo;
		
		/////////////SEPARAÇÃO DO DICIONÁRIO////////////////
		
		//enquanto a String do dicionário não estiver vazia

		while(!dic.isEmpty()) {
			//Salvo o primeiro caractere da String numa String auxiliar chamada String caractere
			String caractere = dic.substring(0, 1);
			if(caractere.equals("\\")) {
				caractere = dic.substring(0, 2);
				dic = dic.substring(3, dic.length());
			} else
			//recorto o que eu acabei de salvar da String do dicionario + o próximo " "
				dic = dic.substring(2, dic.length());
			//Salvo em uma String binariodic tudo o que vier desde o começo de "dicionario" até o próximo " ", ou seja, o próximo binário
			String binariodic = dic.substring(0,dic.indexOf(" "));
			//recorto o que eu acabei de salvar da String dicionário + o próximo " "
			dic = dic.substring(dic.indexOf(" ") + 1, dic.length());
			//Crio uma nova célula de frequência 1 com o caractere que eu salvei anteriormente como conteúdo
			Celula cel = new Celula(0, caractere);
			//digo que o binário dessa nova célula é o binariodic
			cel.setBinario(binariodic);
			//insiro na fila de dicionário a nova célula criada
			dicionario.inserir(1, cel);
		}
		
		////////////TRADUÇÃO DO BINÁRIO RECEBIDO///////////////
		String traducao = ""; //crio uma string para armazenar a tradução e inicalizo ela como vazia
		it = dicionario.iterador(); //faço it iterar a fila dicionário
		
		while(!binario.isEmpty()) { //enquanto o binário não estiver completamente traduzido
			String aux = binario.substring(0, 1); //crio uma string auxiliar e inicalizo ela com o primeiro caractere da String binario
			while(it.temProximo()) {  //início do ciclo de iteração
				Celula caux = (Celula)it.obterProximo();
				caux = (Celula)caux.getObjeto();
				String binaux = caux.getBinario(); //salvo o binário presente em cada uma das células em binaux
				for(int i = 1; !aux.equals(binaux) && i <= binario.length(); i++) { //crio um ciclo de repetições para ir aumentando a substring de binario de um em um elemento e comparando ele com o binário da célula iterada enquanto essa substring não for igual ao binário da célula. 
					aux = binario.substring(0, i);
				}
				if (aux.equals(binaux)) { //após o fim do ciclo de repetições, caso tenha encontrado uma substring que seja igual ao binário da célula
					String str = (String)caux.getObjeto();
					if(str.equals("\\n")) { //tratamento para caso tenha um \n como objeto da célula
						traducao = traducao + "\n";
					}
					else {
						traducao = traducao + str; //coloco a letra relacionada com o binário encontrada na tradução
					}
					binario = binario.substring(binaux.length(), binario.length()); //retira a parte traduzida do binário
					if(!binario.isEmpty()){ //caso o binário não esteja vazio ainda, prepara ele para um novo ciclo
					aux = binario.substring(0, 1); //igualo a String aux com o primeiro caractere da String binario
					it = dicionario.iterador(); //volta o iterador para o início do dicionario
					}
				}
			} //fim do ciclo de iteração
		}
		
		return traducao;
		}
	
	public String compactarArquivo(String local) throws FilaVaziaException, CelulaNulaException, CriarMD5NuloException, IOException, CaractereInexistenteException{ //método para gerar uma String com o conteúdo para a gravação do arquivo
		fila.limpar(); //limpa a fila do controller
		dicionario.limpar(); //limpa o dicionario do controller
		gerarPrioridade(lerArquivo(local)); //gera a fila com as letras e suas frequências do conteudo lido pelo arquivo no local especificado no parâmetro
		String arq = criaArquivo(); //prepara o conteudo do arquivo para a gravação com dicionario, md5 e binário e salva na String arq
		return arq; //retorna o conteúdo pronto para a gravação
	}
	
	public String recuperarMd5(String local) throws IOException { //método para recuperar o md5 de dentro de um arquivo compactado
		String md5 = ""; //inicializo a String para salvar o md5 como vazia
		String arquivo = lerArquivo(local); //le o conteudo do arquivo no local especificado por parâmetro do método
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n")); //remove o a BitString do arquivo
		md5 = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length()); //remove o md5 e salva ele na String
		return md5; //retorna o md5 salvo
	}

}
