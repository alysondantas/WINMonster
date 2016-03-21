package br.uefs.ecomp.winMonster.view;

import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//classe só para testes. Apagar antes de enviar
public class EscolherArquivo {
	public String buscar() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setDialogTitle(" Abrir Arquivo ");
		int resposta = fc.showOpenDialog(null);
		if (resposta == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile().getAbsolutePath();
		}
		else {
			return "";
		}
	}
}
