package br.uefs.ecomp.winMonster.main;

import br.uefs.ecomp.winMonster.view.EscolherArquivo;
import br.uefs.ecomp.winMonster.view.GUI;

import java.io.IOException;

import br.uefs.ecomp.winMonster.controller.*;
import br.uefs.ecomp.winMonster.exceptions.CaractereInexistenteException;
import br.uefs.ecomp.winMonster.util.Arvore;
import br.uefs.ecomp.winMonster.util.Fila;

public class main {
	public static void main (String[] args) {
		/*AdministradorController controller = new AdministradorController();
		GUI gui = new GUI(controller);*/
		AdministradorController controller = new AdministradorController();
		try {
			String str = controller.lerArquivo("C:\\temp.txt");
			System.out.println(str);
			//Fila copia = new Fila();
			Fila copia=controller.getFilaPrioridade();
			Fila fila = controller.gerarPrioridade(str);
			Arvore arvore = new Arvore();
			arvore.inserirHuffman(fila);
			try {
				arvore.construirDicionario(copia);
			} catch (CaractereInexistenteException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}