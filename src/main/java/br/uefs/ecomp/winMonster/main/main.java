package br.uefs.ecomp.winMonster.main;

import br.uefs.ecomp.winMonster.view.EscolherArquivo;
import br.uefs.ecomp.winMonster.view.GUI;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.uefs.ecomp.winMonster.controller.*;
import br.uefs.ecomp.winMonster.exceptions.CaractereInexistenteException;
import br.uefs.ecomp.winMonster.exceptions.DescompactarStringNulaException;
import br.uefs.ecomp.winMonster.util.Arvore;
import br.uefs.ecomp.winMonster.util.Fila;

public class main {
	public static void main (String[] args) throws IOException, DescompactarStringNulaException {
		//Tire o comentário do teste que quiser usar e comente o outro
		
		
		//TESTES PARA A GUI
		
		AdministradorController controller = new AdministradorController();
		GUI gui = new GUI(controller);
		
		
		//TESTES PARA CONSOLE
		
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
		
//		JFileChooser fc = new JFileChooser();
//		fc.showOpenDialog(null);
//		controller.descompacta("g 010   011 z 100 l 101 u 00 a 11 \n\nd8e788318a46ff18a89deb24290bcfa3\n\n1101000110111110000101");
		
	}
}