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

	public void descompacta(String local) throws IOException, DescompactarStringNulaException{
		String arquivo=lerArquivo(local);//recebe o arquivo a ser descompactado pelo seu local
		boolean terminouDic = false;
		boolean ecaractere = true;
		boolean terminouMd5 = false;
		boolean terminouArq = false;
		dicionario = new Fila();
		Celula celulaCaractere;
		MeuIterador iterador=dicionario.iterador();
		String binariodic = "";
		String caracteredic = "";
		String md5Antigo = "";
		String binarioCompactado = "";
		String arquivoDescompactado = "";

		for (int j = 0; j < arquivo.length(); j++) { //Repetição: Início = Segunda letra da String; Condição = Até o fim da String
			if(terminouDic == false){
				if(ecaractere == true){
					caracteredic = caracteredic + arquivo.charAt(j);
					if(Character.toString(arquivo.charAt(j)) == " "){
						ecaractere = false;
					}
				}else{
					if(Character.toString(arquivo.charAt(j)) != " " && Character.toString(arquivo.charAt(j)) != "\n"){
						binariodic=binariodic + arquivo.charAt(j);
					}else if(Character.toString(arquivo.charAt(j)) == " "){
						dicionario.inserir(j, caracteredic);
						iterador.reiniciar();
						while(iterador.temProximo()){
							celulaCaractere=(Celula) iterador.obterProximo();
							if(celulaCaractere.getChave()==j){
								celulaCaractere.setBinario(binariodic);
								celulaCaractere.setCaractere(caracteredic);
							}
						}
						ecaractere = true;
					}else{
						terminouDic = true;
					}
				}
			}else if(terminouMd5 == false){
				if(Character.toString(arquivo.charAt(j)) != "\n"){
					md5Antigo= md5Antigo + arquivo.charAt(j);
				}else{
					terminouMd5=true;
				}
			}else{
				binarioCompactado = binarioCompactado + arquivo.charAt(j);
			}
		} //repete o ciclo

		if(binarioCompactado == null || dicionario == null || md5Antigo == null){
			throw new DescompactarStringNulaException();
		}else{
			iterador.reiniciar();
			for (int j = 0; j < binarioCompactado.length(); j++) { //Repetição: Início = Segunda letra da String; Condição = Até o fim da String
				while(iterador.temProximo()){
					celulaCaractere = (Celula) iterador.obterProximo();
					caracteredic = celulaCaractere.getCaractere();
					binariodic = celulaCaractere.getBinario();
					if (caracteredic.charAt(0) == binarioCompactado.charAt(j)) { //caso a letra do dicionario seja igual a letra atual
						arquivoDescompactado=arquivoDescompactado + binariodic;//binario concatena com o binario do dicionario
					}
				}
			} //repete o ciclo
		}
		//falta escrever o novo arquivo que esta dentro do arquivoDescompactado
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
