package br.uefs.ecomp.winMonster.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import br.uefs.ecomp.winMonster.util.*;
import br.uefs.ecomp.winMonster.exceptions.*;

public class AdministradorController {
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
		
		return c; //retorna a String com todo o conte�do do arquivo de texto
	}

	public void escreverArquivo(String local) throws IOException { //m�todo deixado aqui s� para inspira��o. N�o vai ser usado do jeito que est� descrito no momento
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(local)); //crio um novo objeto para escrita de arquivos e passo como par�metro um novo objeto de escrita do arquivo no local especificado
		String linha = ""; //crio uma string auxiliar para gravar a linha do arquivo e a inicializo como vazia
		Scanner in = new Scanner(System.in); //inicializo um objeto para capturar os dados de entrada do usu�rio
		System.out.println("Escreva algo: "); 
		linha = in.nextLine(); //capturo a pr�xima string que o usu�rio digitar
		buffWrite.append(linha + "\n"); //anexo essa string no arquivo de texto
		buffWrite.close(); //fecho o arquivo aberto
	}
	
	public Fila gerarPrioridade(String linha, Fila copia) throws IOException { //m�todo para gerar uma fila de prioridade a partir de uma String recebida
		Fila fila = new Fila(); //crio uma fila para salvar a prioridade
		boolean copiaNula = false; //vari�vel para verificar se � neces�rio criar uma fila c�pia. Caso ela continue como false, vai ser gerada uma fila c�pia igual a fila a ser retornada
		if(copia == null) { //caso a c�pia passada seja nula, marque a vari�vel como true
			copiaNula = true;
		}
		while(linha != "") { //enquanto a string n�o estiver vazia
			int cont = 0; //vari�vel para armazenar quantas vezes a letra se repete na string
			String aux; //string auxiliar para ajudar nas opera��es
			for (int j = 1; j < linha.length(); j++) { //Repeti��o: In�cio = Segunda letra da String; Condi��o = At� o fim da String
				aux = Character.toString(linha.charAt(j)); //Converte o caractere da string na posi��o atual para uma string separada para poder ser manipulado
				if (linha.charAt(0) == linha.charAt(j)) { //caso a primeira letra seja igual a letra atual
					cont++; //incrementa um no contador
				}
			}
			fila.inserir(cont, linha.substring(0, 1)); //crio uma string somente com a primeira letra de linha e insiro ela na fila, passando tamb�m o n�mero de vezes que a letra se repete como chave
			if(copiaNula == false) //caso a c�pia n�o seja nula, preencha a fila c�pia
				copia.inserir(cont, linha.substring(0, 1)); //preencho a c�pia com o mesmo conte�do da fila original
			aux = Character.toString(linha.charAt(0)); //atribui agora a primeira letra da string ao valor de aux
			linha = linha.replace(aux, ""); //remove todas as ocorr�ncias da primeira letra na string
		} //repete o ciclo
		return fila; //retorno a fila com cada letra da string separada em c�lulas com suas respectivas chaves
	}

}
