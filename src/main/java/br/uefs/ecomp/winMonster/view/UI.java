package br.uefs.ecomp.winMonster.view;

import java.io.IOException;

import javax.swing.JOptionPane;

public class UI {
	 public void mensagem() {
		 JOptionPane.showMessageDialog(null, "oi");
	 }
	 public static void main(String[] args) throws IOException {
		 new UI().mensagem();
	 }

}
