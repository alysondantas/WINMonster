/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 06/04/2016

Declaro que este c�digo foi elaborado por esta dupla e n�o cont�m nenhum

trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo

de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte

do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.

 ******************************************************************************************/

package br.uefs.ecomp.winMonster.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import br.uefs.ecomp.winMonster.util.*;
import br.uefs.ecomp.winMonster.exceptions.*;

public class AdministradorController {
	Arvore arvoreHuffman= new Arvore();
	Fila fila = new Fila(); //crio uma fila para salvar a prioridade
	Fila dicionario = new Fila(); //fila para salvar o dicion�rio
	String arquivoOriginal=""; //String para salvar o conte�do do arquivo original antes da compacta��o

	public Fila getDicionario(){
		return dicionario;
	}
	
	public void setArquivoOriginal(String arqmod){
		arquivoOriginal = arqmod;
	}

	public String lerArquivo(String local) throws IOException { //m�todo que l� um arquivo de texto e converte todo o seu conte�do para uma String
		Reader arq = new InputStreamReader(new FileInputStream(local), "ISO-8859-1"); //inicializo arq como a leitura de arquivos no local especificado no padr�o ISO-8859-1
		BufferedReader buffRead = new BufferedReader(arq); //crio um novo objeto BuffReader e passo para ele a leitura do local em arq
		String linha = buffRead.readLine(); //crio uma string auxiliar e j� armazeno a primeira linha do arquivo
		String c = ""; //crio uma string auxiliar para armazenar o conte�do da string
		
		while(linha != null) { //enquanto n�o chegar no fim do arquivo
			c = c + linha; //salvo o a letra da string na posi��o atual
			linha = buffRead.readLine(); //salvo a linha atual do arquivo na string
			if(linha!=null)
				c = c + "\n"; //acrescento uma quebra de linha em "c" a cada fim de linha em "linha"
		}
		buffRead.close(); //fecho a leitura do arquivo

		arquivoOriginal=c;//Uma varaivel local recebe a string com todo o conteudo do arquivo
		return c; //retorna a String com todo o conte�do do arquivo de texto
	}

	public void escreverArquivo(String texto, String local, String nome) throws IOException { //m�todo deixado aqui s� para inspira��o. N�o vai ser usado do jeito que est� descrito no momento
		local = local + nome; //anexo o nome do arquivo ao local que ele ser� escrito
		BufferedWriter buffWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(local), "ISO-8859-1")); //crio um novo objeto para escrita de arquivos e passo como par�metro um novo objeto de escrita do arquivo no local especificado no padr�o ISO-8859-1
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
			linha = linha.replaceAll(Pattern.quote(aux), ""); //remove todas as ocorr�ncias da primeira letra na string
		} //repete o ciclo
		return fila; //retorno a fila com cada letra da string separada em c�lulas com suas respectivas chaves
	}

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
	
	public boolean verifMd5(String md5a, String md5b) { //m�todo para comparar 2 md5s
		if (md5a.equals(md5b)) //caso sejam iguais
			return true;
		else //caso sejam diferentes
			return false;		
	}

	public String criaArquivo() throws CaractereInexistenteException, IOException, FilaVaziaException, CelulaNulaException, CriarMD5NuloException{//Metodo para criar a string do arquivo compactado com dicionario e md5
		arvoreHuffman = new Arvore(); // arvore de huffman � instanciada
		arvoreHuffman = arvoreHuffman.inserirHuffman(fila); // algoritimo da arvore de huffman recebe a fila de prioridade e retornam a arvore pronta
		arvoreHuffman.construirDicionario(dicionario); // pela arvore � criado o dicionario dela com o binario de cada caractere
		Celula celulaCaractere; //celula que esta dentro do dicionario
		String caractere; //string para pegar o caractere
		String novoBinario = ""; //string para o novo binario
		String binariodic; //binario que vai estar dentro do dicionario
		String md = md5(arquivoOriginal); // md5 recebe o md5 do arquivo original
		String dic = ""; //dicionario
		String arquivoNovo = ""; //string que � o novo arquivo
		MeuIterador iterador; //iterador
		for (int j = 0; j < arquivoOriginal.length(); j++) { //Repeti��o: In�cio = Segunda letra da String; Condi��o = At� o fim da String
			iterador=dicionario.iterador(); // iterador recebe a primeira celula do dicionario
			while(iterador.temProximo()){ //enquanto existir proximo
				celulaCaractere = (Celula) iterador.obterProximo(); //celula recebe a atual e iterador passa pro proximo
				caractere = celulaCaractere.getCaractere(); //caractere recebe o caractere da celula atual
				binariodic = celulaCaractere.getBinario(); // binario do dicionario o binario correspondente ao caractere atual
				if (caractere.charAt(0) == arquivoOriginal.charAt(j)) { //caso a letra do dicionario seja igual a letra atual
					novoBinario = novoBinario + binariodic;//binario concatena com o binario do dicionario
				}
			}
		} //repete o ciclo
		iterador=dicionario.iterador(); //iterador � reiniciado com o primeiro elemento do dicionario para escrever ele no arquivo
		while(iterador.temProximo()){ //enquanto existir proximo
			celulaCaractere = (Celula) iterador.obterProximo(); //celula recebe a atual e iterador passa pro proximo
			caractere = celulaCaractere.getCaractere(); //caractere recebe o caractere atual
			if(caractere.equals("\n")) //se for uma quebra de linha
				caractere = "\\n"; // adiciona um \ a mais para entrar na string sem possiveis conflitos
			binariodic = celulaCaractere.getBinario(); //binario do dicionario recebe o binario atual
			dic = dic + caractere + " " + binariodic + " "; //dicionario concatena ele mais o caractere mais o binario respectivo
		} //repete o ciclo
		
		novoBinario = converterBits(novoBinario);//novoBinario recebe a string convertida e compactada
		
		arquivoNovo = dic + "\n" + "\n" + md + "\n" + "\n" + novoBinario; //novo arquivo recebe o dicionario o md5 e o novoBinario com quebras de linha para diferenciar um do outro
		return arquivoNovo; //retorna o novo arquivo para ser escrito.
	}

	public String descompacta(String arquivo) throws IOException, DescompactarStringNulaException{
		dicionario = new Fila();
		MeuIterador it;
		String dic = ""; //String para salvar o dicion�rio
		String binario = ""; //String para salvar a BitString
		String resto = "";
		String md5= ""; //String para salvar o md5

		////////////SEPARA��O DA STRING DO ARQUIVO ORIGINAL//////////////////
		
		//separo a partir da �ltima ocorr�ncia de "\n\n" at� o fim da string e salvo na String resto
		resto = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//recorto o peda�o que eu acabei de salvar em "resto" da String do arquivo original
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n"));
		//separo novamente a partir da �ltima ocorr�ncia de "\n\n" at� o fim da string e salvo na String binario
		binario = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//converto o texto criptografado em uma BitString
		binario = converterTexto(binario);
		//acrescento o resto da BitString salvo no arquivo no binario
		if (!resto.equals("2"))
		binario = binario + resto;
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
			if(dic.substring(0,2).equals("\\n")) {
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
				for(int i = 1; !aux.equals(binaux) && i <= binaux.length(); i++) { //crio um ciclo de repeti��es para ir aumentando a substring de binario de um em um elemento e comparando ele com o bin�rio da c�lula iterada enquanto essa substring n�o for igual ao bin�rio da c�lula. 
					if(aux.length() < binario.length()) //condi��o para impedir que o bin�rio auxiliar fique maior do que o bin�rio total restante
						aux = binario.substring(0, i); //aumenta o tamanho de aux
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
				else { //caso n�o tenha encontrado nenhuma substring igual ao 
					if(!binario.isEmpty()){ //caso o bin�rio n�o esteja vazio ainda, prepara ele para um novo ciclo
						aux = binario.substring(0, 1); //igualo a String aux com o primeiro caractere da String binario
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
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n")); //remove o resto do arquivo
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n")); //remove a BitString compactada do arquivo
		md5 = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length()); //remove o md5 e salva ele na String
		return md5; //retorna o md5 salvo
	}

	public String converterBits(String bits) { //m�todo para compactar a BitString do arquivo de texto em caracteres
		String conversao = ""; //string para salvar a convers�o inicializada como vazia
		while(bits.length() >= 7) { //enquanto tiver conjuntos de 7 ou mais bits na BitString
			String byteAux = bits.substring(0, 7); //salva esse conjunto na String byteAux
			byteAux = "1" + byteAux; //incrementa 1 no come�o para garantir que nenhum bit vai ser perdido
			int intAux = Integer.parseInt(byteAux, 2); //converte o byteAux para inteiro
			char[] aux = Character.toChars(intAux); //encontra o caractere relacionado com esse inteiro
			String charAux = Character.toString(aux[0]); //converte o caractere em String
			conversao = conversao + charAux; //concatena o caractere com a String convers�o
			bits = bits.substring(7, bits.length()); //remove o conjunto de bits convertido
		}
			if (bits.length() == 0) { //caso n�o tenha sobrado resto
				conversao = conversao + "\n\n" + 2; //acrescenta 2 no lugar do resto
			}
			else { //caso tenha sobrado resto
				conversao = conversao + "\n\n" + bits; //ao fim da tradu��o, acrescenta o resto dos bits que n�o foram convertidos em uma outra sess�o do arquivo
			}
		
		return conversao; //retorna a BitString compactada
	}
	
	public String converterTexto(String texto) { //m�todo para converter a BitString compactada de volta para os bits
		String conversao = ""; //string para salvar a convers�o incializada como vazia
		while(!texto.isEmpty()) { //enquanto ainda tiverem caracteres para serem traduzidos
			String charAux = texto.substring(0, 1); //captura o primeiro caractere e salva ele na String charAux
			int intAux = charAux.codePointAt(0); //encontra o inteiro correspondente com o caractere
			String byteAux = Integer.toBinaryString(intAux); //converte o inteiro em uma string de bin�rios
			byteAux = byteAux.substring(1, byteAux.length()); //remove o primeiro 1 adicionado no bin�rio no  metodo converterBits para que n�o fossem perdidos bits
			conversao = conversao + byteAux; //concatena os bin�rios com a String conversao
			texto = texto.substring(1, texto.length()); // remove o caractere convertido
		}
		
		return conversao; //retorna a BitString descompactada
	}
}
