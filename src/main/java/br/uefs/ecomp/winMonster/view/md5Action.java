package br.uefs.ecomp.winMonster.view;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.uefs.ecomp.winMonster.controller.AdministradorController;
import br.uefs.ecomp.winMonster.exceptions.CriarMD5NuloException;

public class md5Action implements ActionListener{

	AdministradorController controller = new AdministradorController(); //crio um controller novo
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String md5Ant = ""; //crio 2 strings auxiliares pra guardar os md5s do arquivo compactado e descompactado
		String md5Nov = "";
		JFileChooser fc = new JFileChooser(); //crio um FileChooser para auxiliar na seleção do arquivo
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY); //por padrão o FileChooser só permitirá a seleção de arquivos
		fc.setDialogTitle(" Abrir Arquivo Original "); //define o título do FileChooser
        fc.setFileFilter(new FileNameExtensionFilter("Arquivos .MONSTER", "monster")); //filtro do tipo de arquivos que podem ser abertos
		JOptionPane.showMessageDialog(null, "Escolha o arquivo .monster compactado");
		int resposta = fc.showOpenDialog(null); //abre o menu de abertura de arquivo e salva a resposta do usuário em int resposta
		if (resposta == JFileChooser.APPROVE_OPTION) { //caso o usuário selecione um arquivo
			try {
				md5Ant = controller.recuperarMd5(fc.getSelectedFile().getAbsolutePath()); //recupera o md5 do arquivo compactado e salva na String md5Ant
				JOptionPane.showMessageDialog(null, "Agora escolha o arquivo descompactado"); //exibe a mensagem para o usuário escolher o arquivo descompactado
				fc.setDialogTitle(" Abrir Arquivo Descompactado "); //redefine o título do FileChooser
				int resp2 = fc.showOpenDialog(null); //abre novamente o menu de abertura de arquivo e salva a resposta do usuário em int resp2
				if(resp2 == JFileChooser.APPROVE_OPTION) { //caso tenha sido selecionado novamente um arquivo
						String texto = controller.lerArquivo(fc.getSelectedFile().getAbsolutePath()); //salva o conteudo do arquivo em String texto
						try {
							md5Nov = controller.md5(texto); //gera um md5 novo a partir da String texto e salva em md5Nov
							if(md5Ant.equals(md5Nov)) { //caso os md5 forem iguais
								JOptionPane.showMessageDialog(null, "Os MD5 são iguais"); //exibe uma mensagem de sucesso
							}
							else { //caso os md5 forem diferentes
								JOptionPane.showMessageDialog(null, "Os MD5 são diferentes"); //exibe uma mensagem de falha
							}
						} catch (CriarMD5NuloException e1) {
							e1.printStackTrace();
						}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
