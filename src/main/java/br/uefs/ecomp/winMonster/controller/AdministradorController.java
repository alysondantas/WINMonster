package br.uefs.ecomp.winMonster.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import br.uefs.ecomp.winMonster.util.Arvore;
import br.uefs.ecomp.winMonster.util.Celula;
import br.uefs.ecomp.winMonster.util.CelulaArvore;
import br.uefs.ecomp.winMonster.util.Fila;
import br.uefs.ecomp.winMonster.util.MeuIterador;

public class AdministradorController {
	public String lerArquivo(String local) throws IOException { //método que lê um arquivo de texto e converte todo o seu conteúdo para uma String
		BufferedReader buffRead = new BufferedReader(new FileReader(local)); //crio um novo objeto para leitura de arquivos e passo como parâmetro a leitura do arquivo no local especificado
		String linha = ""; //crio uma string auxiliar e a inicializo como vazia
		while(linha != null) { //enquanto não chegar no fim do arquivo
			linha = buffRead.readLine(); //salvo a linha atual do arquivo na string
			for(int i = 0; i < linha.length(); i++) { //percorro letra por letra do arquivo, até o seu fim
				String c = ""; //c
				c = c + linha.charAt(i); //salvo o a letra da string na posição atual
			}
		}
		return linha; //retorna a String com todo o conteúdo do arquivo de texto
	}

	public void escreverArquivo(String local) throws IOException { //método deixado aqui só para inspiração. Não vai ser usado do jeito que está descrito no momento
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(local)); //crio um novo objeto para escrita de arquivos e passo como parâmetro um novo objeto de escrita do arquivo no local especificado
		String linha = ""; //crio uma string auxiliar para gravar a linha do arquivo e a inicializo como vazia
		Scanner in = new Scanner(System.in); //inicializo um objeto para capturar os dados de entrada do usuário
		System.out.println("Escreva algo: "); 
		linha = in.nextLine(); //capturo a próxima string que o usuário digitar
		buffWrite.append(linha + "\n"); //anexo essa string no arquivo de texto
		buffWrite.close(); //fecho o arquivo aberto
	}
	
	public Fila gerarPrioridade(String linha) throws IOException { //método para gerar uma fila de prioridade a partir de uma String recebida
		Fila fila = new Fila(); //crio uma fila para salvar a prioridade
		while(linha != "") { //enquanto a string não estiver vazia
			int cont = 0; //variável para armazenar quantas vezes a letra se repete na string
			String aux; //string auxiliar para ajudar nas operações
			for (int j = 1; j < linha.length(); j++) { //Repetição: Início = Segunda letra da String; Condição = Até o fim da String
				aux = Character.toString(linha.charAt(j)); //Converte o caractere da string na posição atual para uma string separada para poder ser manipulado
				if (linha.charAt(0) == linha.charAt(j)) { //caso a primeira letra seja igual a letra atual
					cont++; //incrementa um no contador
				}
			}
			fila.inserir(cont, linha.substring(0, 1)); //crio uma string somente com a primeira letra de linha e insiro ela na fila, passando também o número de vezes que a letra se repete como chave
			aux = Character.toString(linha.charAt(0)); //atribui agora a primeira letra da string ao valor de aux
			linha = linha.replace(aux, ""); //remove todas as ocorrências da primeira letra na string
		} //repete o ciclo
		return fila; //retorno a fila com cada letra da string separada em células com suas respectivas chaves
	}

	
}
