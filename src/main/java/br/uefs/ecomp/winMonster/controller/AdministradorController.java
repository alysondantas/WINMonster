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
	Fila dicionario = new Fila(); //fila para salvar o dicionário
	String arquivoOriginal=""; //String para salvar o conteúdo do arquivo original antes da compactação

	public Fila getDicionario(){
		return dicionario;
	}
	
	public void setArquivoOriginal(String arqmod){
		arquivoOriginal = arqmod;
	}

	public String lerArquivo(String local) throws IOException { //método que lê um arquivo de texto e converte todo o seu conteúdo para uma String
		Reader arq = new InputStreamReader(new FileInputStream(local), "ISO-8859-1"); //inicializo arq como a leitura de arquivos no local especificado no padrão ISO-8859-1
		BufferedReader buffRead = new BufferedReader(arq); //crio um novo objeto BuffReader e passo para ele a leitura do local em arq
		String linha = buffRead.readLine(); //crio uma string auxiliar e já armazeno a primeira linha do arquivo
		String c = ""; //crio uma string auxiliar para armazenar o conteúdo da string
		
		while(linha != null) { //enquanto não chegar no fim do arquivo
			c = c + linha; //salvo o a letra da string na posição atual
			linha = buffRead.readLine(); //salvo a linha atual do arquivo na string
			if(linha!=null)
				c = c + "\n"; //acrescento uma quebra de linha em "c" a cada fim de linha em "linha"
		}
		buffRead.close(); //fecho a leitura do arquivo

		arquivoOriginal=c;//Uma varaivel local recebe a string com todo o conteudo do arquivo
		return c; //retorna a String com todo o conteúdo do arquivo de texto
	}

	public void escreverArquivo(String texto, String local, String nome) throws IOException { //método deixado aqui só para inspiração. Não vai ser usado do jeito que está descrito no momento
		local = local + nome; //anexo o nome do arquivo ao local que ele será escrito
		BufferedWriter buffWrite = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(local), "ISO-8859-1")); //crio um novo objeto para escrita de arquivos e passo como parâmetro um novo objeto de escrita do arquivo no local especificado no padrão ISO-8859-1
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
			linha = linha.replaceAll(Pattern.quote(aux), ""); //remove todas as ocorrências da primeira letra na string
		} //repete o ciclo
		return fila; //retorno a fila com cada letra da string separada em células com suas respectivas chaves
	}

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
	
	public boolean verifMd5(String md5a, String md5b) { //método para comparar 2 md5s
		if (md5a.equals(md5b)) //caso sejam iguais
			return true;
		else //caso sejam diferentes
			return false;		
	}

	public String criaArquivo() throws CaractereInexistenteException, IOException, FilaVaziaException, CelulaNulaException, CriarMD5NuloException{//Metodo para criar a string do arquivo compactado com dicionario e md5
		arvoreHuffman = new Arvore(); // arvore de huffman é instanciada
		arvoreHuffman = arvoreHuffman.inserirHuffman(fila); // algoritimo da arvore de huffman recebe a fila de prioridade e retornam a arvore pronta
		arvoreHuffman.construirDicionario(dicionario); // pela arvore é criado o dicionario dela com o binario de cada caractere
		Celula celulaCaractere; //celula que esta dentro do dicionario
		String caractere; //string para pegar o caractere
		String novoBinario = ""; //string para o novo binario
		String binariodic; //binario que vai estar dentro do dicionario
		String md = md5(arquivoOriginal); // md5 recebe o md5 do arquivo original
		String dic = ""; //dicionario
		String arquivoNovo = ""; //string que é o novo arquivo
		MeuIterador iterador; //iterador
		for (int j = 0; j < arquivoOriginal.length(); j++) { //Repetição: Início = Segunda letra da String; Condição = Até o fim da String
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
		iterador=dicionario.iterador(); //iterador é reiniciado com o primeiro elemento do dicionario para escrever ele no arquivo
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
		String dic = ""; //String para salvar o dicionário
		String binario = ""; //String para salvar a BitString
		String resto = "";
		String md5= ""; //String para salvar o md5

		////////////SEPARAÇÃO DA STRING DO ARQUIVO ORIGINAL//////////////////
		
		//separo a partir da última ocorrência de "\n\n" até o fim da string e salvo na String resto
		resto = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//recorto o pedaço que eu acabei de salvar em "resto" da String do arquivo original
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n"));
		//separo novamente a partir da última ocorrência de "\n\n" até o fim da string e salvo na String binario
		binario = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length());
		//converto o texto criptografado em uma BitString
		binario = converterTexto(binario);
		//acrescento o resto da BitString salvo no arquivo no binario
		if (!resto.equals("2"))
		binario = binario + resto;
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
			if(dic.substring(0,2).equals("\\n")) {
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
				for(int i = 1; !aux.equals(binaux) && i <= binaux.length(); i++) { //crio um ciclo de repetições para ir aumentando a substring de binario de um em um elemento e comparando ele com o binário da célula iterada enquanto essa substring não for igual ao binário da célula. 
					if(aux.length() < binario.length()) //condição para impedir que o binário auxiliar fique maior do que o binário total restante
						aux = binario.substring(0, i); //aumenta o tamanho de aux
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
				else { //caso não tenha encontrado nenhuma substring igual ao 
					if(!binario.isEmpty()){ //caso o binário não esteja vazio ainda, prepara ele para um novo ciclo
						aux = binario.substring(0, 1); //igualo a String aux com o primeiro caractere da String binario
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
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n")); //remove o resto do arquivo
		arquivo = arquivo.substring(0, arquivo.lastIndexOf("\n\n")); //remove a BitString compactada do arquivo
		md5 = arquivo.substring(arquivo.lastIndexOf("\n\n") + 2, arquivo.length()); //remove o md5 e salva ele na String
		return md5; //retorna o md5 salvo
	}

	public String converterBits(String bits) { //método para compactar a BitString do arquivo de texto em caracteres
		String conversao = ""; //string para salvar a conversão inicializada como vazia
		while(bits.length() >= 7) { //enquanto tiver conjuntos de 7 ou mais bits na BitString
			String byteAux = bits.substring(0, 7); //salva esse conjunto na String byteAux
			byteAux = "1" + byteAux; //incrementa 1 no começo para garantir que nenhum bit vai ser perdido
			int intAux = Integer.parseInt(byteAux, 2); //converte o byteAux para inteiro
			char[] aux = Character.toChars(intAux); //encontra o caractere relacionado com esse inteiro
			String charAux = Character.toString(aux[0]); //converte o caractere em String
			conversao = conversao + charAux; //concatena o caractere com a String conversão
			bits = bits.substring(7, bits.length()); //remove o conjunto de bits convertido
		}
			if (bits.length() == 0) { //caso não tenha sobrado resto
				conversao = conversao + "\n\n" + 2; //acrescenta 2 no lugar do resto
			}
			else { //caso tenha sobrado resto
				conversao = conversao + "\n\n" + bits; //ao fim da tradução, acrescenta o resto dos bits que não foram convertidos em uma outra sessão do arquivo
			}
		
		return conversao; //retorna a BitString compactada
	}
	
	public String converterTexto(String texto) { //método para converter a BitString compactada de volta para os bits
		String conversao = ""; //string para salvar a conversão incializada como vazia
		while(!texto.isEmpty()) { //enquanto ainda tiverem caracteres para serem traduzidos
			String charAux = texto.substring(0, 1); //captura o primeiro caractere e salva ele na String charAux
			int intAux = charAux.codePointAt(0); //encontra o inteiro correspondente com o caractere
			String byteAux = Integer.toBinaryString(intAux); //converte o inteiro em uma string de binários
			byteAux = byteAux.substring(1, byteAux.length()); //remove o primeiro 1 adicionado no binário no  metodo converterBits para que não fossem perdidos bits
			conversao = conversao + byteAux; //concatena os binários com a String conversao
			texto = texto.substring(1, texto.length()); // remove o caractere convertido
		}
		
		return conversao; //retorna a BitString descompactada
	}
}
