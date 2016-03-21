package br.uefs.ecomp.winMonster.main;

import br.uefs.ecomp.winMonster.view.EscolherArquivo;
import br.uefs.ecomp.winMonster.view.GUI;

import java.io.IOException;

import javax.swing.JOptionPane;

import br.uefs.ecomp.winMonster.controller.*;
import br.uefs.ecomp.winMonster.exceptions.CaractereInexistenteException;
import br.uefs.ecomp.winMonster.util.Arvore;
import br.uefs.ecomp.winMonster.util.Fila;

public class main {
	public static void main (String[] args) {
		AdministradorController controller = new AdministradorController();
		GUI gui = new GUI(controller);
		/*AdministradorController controller = new AdministradorController();
		try {
			String str = controller.lerArquivo("C:\\temp.txt");
			System.out.println(str);
			//Fila copia = new Fila();
			Fila fila = controller.gerarPrioridade(str);
			controller.imprimirFila(fila);
			Fila copia = controller.getDicionario();
			Arvore arvore = new Arvore();
			arvore = arvore.inserirHuffman(fila);
			try {
				arvore.construirDicionario(copia);
			} catch (CaractereInexistenteException e) {
				JOptionPane.showMessageDialog(null, "Erro 1");
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro 2");
			e.printStackTrace();
		}
		*/
	}
}