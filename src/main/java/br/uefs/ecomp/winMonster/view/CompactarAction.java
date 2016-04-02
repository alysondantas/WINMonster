package br.uefs.ecomp.winMonster.view;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import br.uefs.ecomp.winMonster.controller.AdministradorController;
import br.uefs.ecomp.winMonster.exceptions.CaractereInexistenteException;
import br.uefs.ecomp.winMonster.exceptions.CelulaNulaException;
import br.uefs.ecomp.winMonster.exceptions.CriarMD5NuloException;
import br.uefs.ecomp.winMonster.exceptions.FilaVaziaException;

public class CompactarAction implements ActionListener {

	AdministradorController controller;
	
	public CompactarAction(AdministradorController controller) {
		this.controller = controller; //iguala a instância do controller da classe à instância recebida
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser(); //cria um novo selecionador de arquivos
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY); //configura o selecionador para só receber arquivos
		fc.setDialogTitle(" Abrir Arquivo "); //define o título da janela de seleção
		int resposta = fc.showOpenDialog(null); //abre a janela de seleção e guarda a ação do usuário em resposta
		if (resposta == JFileChooser.APPROVE_OPTION) { //caso o usuário tenha selecionado um arquivo
			String texto;
			try {
				texto = controller.compactarArquivo(fc.getSelectedFile().getAbsolutePath());  //chama o método compactarArquivo do controller, passa o local do arquivo selecionado para ele e salva o texto do arquivo compactado na String texto
				JOptionPane.showMessageDialog(null, texto); //teste para exibir o texto recebido
				String nomeArq = fc.getSelectedFile().getName(); //salva o nome do arquivo que está na localização do arquivo selecionado na String nomeArq
				JOptionPane.showMessageDialog(null, nomeArq); //teste para exibir o nome do arquivo
				String local = fc.getSelectedFile().getPath().replace(nomeArq, ""); //salva o local do arquivo selecionado removendo o nome do arquivo na String local
				JOptionPane.showMessageDialog(null, local); //teste para exibir o local do arquivo
				try { 
					controller.escreverArquivo(texto, local, nomeArq + ".monster"); //chama o método de escreverArquivo no controller passando o texto compactado com dicionário e md5, o local do arquivo e o novo nome concatenado com a extensão ".monster"
//					controller.escreverBits(texto, local + nomeArq + ".monster");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "IOException");
					e1.printStackTrace();
				}
			} catch (FilaVaziaException | CelulaNulaException | CriarMD5NuloException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (CaractereInexistenteException e2) {
				e2.printStackTrace();
			}
		}
	}

}
