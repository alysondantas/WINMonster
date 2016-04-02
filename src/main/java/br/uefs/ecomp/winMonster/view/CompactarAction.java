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
		this.controller = controller; //iguala a inst�ncia do controller da classe � inst�ncia recebida
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser(); //cria um novo selecionador de arquivos
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY); //configura o selecionador para s� receber arquivos
		fc.setDialogTitle(" Abrir Arquivo "); //define o t�tulo da janela de sele��o
		int resposta = fc.showOpenDialog(null); //abre a janela de sele��o e guarda a a��o do usu�rio em resposta
		if (resposta == JFileChooser.APPROVE_OPTION) { //caso o usu�rio tenha selecionado um arquivo
			String texto;
			try {
				texto = controller.compactarArquivo(fc.getSelectedFile().getAbsolutePath());  //chama o m�todo compactarArquivo do controller, passa o local do arquivo selecionado para ele e salva o texto do arquivo compactado na String texto
				JOptionPane.showMessageDialog(null, texto); //teste para exibir o texto recebido
				String nomeArq = fc.getSelectedFile().getName(); //salva o nome do arquivo que est� na localiza��o do arquivo selecionado na String nomeArq
				JOptionPane.showMessageDialog(null, nomeArq); //teste para exibir o nome do arquivo
				String local = fc.getSelectedFile().getPath().replace(nomeArq, ""); //salva o local do arquivo selecionado removendo o nome do arquivo na String local
				JOptionPane.showMessageDialog(null, local); //teste para exibir o local do arquivo
				try { 
					controller.escreverArquivo(texto, local, nomeArq + ".monster"); //chama o m�todo de escreverArquivo no controller passando o texto compactado com dicion�rio e md5, o local do arquivo e o novo nome concatenado com a extens�o ".monster"
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
