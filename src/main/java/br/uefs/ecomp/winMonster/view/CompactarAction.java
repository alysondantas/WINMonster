package br.uefs.ecomp.winMonster.view;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import br.uefs.ecomp.winMonster.controller.AdministradorController;

public class CompactarAction implements ActionListener {

	JTextField textField;
	AdministradorController controller;
	
	public CompactarAction(AdministradorController controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setDialogTitle(" Abrir Arquivo ");
		int resposta = fc.showOpenDialog(null);
		if (resposta == JFileChooser.APPROVE_OPTION) {
			String texto = controller.compactarArquivo(fc.getSelectedFile().getAbsolutePath());
			JOptionPane.showMessageDialog(null, texto);
			String nomeArq = fc.getSelectedFile().getName();
			JOptionPane.showMessageDialog(null, nomeArq);
			String local = fc.getSelectedFile().getPath().replace(nomeArq, "");
			JOptionPane.showMessageDialog(null, local);
			try {
				controller.escreverArquivo(texto, local, nomeArq + ".monster");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		/*else {
			JOptionPane.showMessageDialog(null, "Erro");
		}*/
	}

}
