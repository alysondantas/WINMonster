package br.uefs.ecomp.winMonster.view;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import br.uefs.ecomp.winMonster.controller.AdministradorController;

public class md5Action implements ActionListener{

	AdministradorController controller = new AdministradorController();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String md5Ant = "";
		String md5Nov = "";
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setDialogTitle(" Abrir Arquivo Original ");
		JOptionPane.showMessageDialog(null, "Escolha o arquivo .monster compactado");
		int resposta = fc.showOpenDialog(null);
		if (resposta == JFileChooser.APPROVE_OPTION) {
			try {
				md5Ant = controller.recuperarMd5(fc.getSelectedFile().getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Agora escolha o arquivo descompactado");
				int resp2 = fc.showOpenDialog(null);
				if(resp2 == JFileChooser.APPROVE_OPTION)
				{
						String texto = controller.lerArquivo(fc.getSelectedFile().getAbsolutePath());
						md5Nov = controller.md5(texto);
						if(md5Ant.equals(md5Nov)) {
							JOptionPane.showMessageDialog(null, "Os MD5 são iguais");
						}
						else {
							JOptionPane.showMessageDialog(null, "Os MD5 são diferentes");
						}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
