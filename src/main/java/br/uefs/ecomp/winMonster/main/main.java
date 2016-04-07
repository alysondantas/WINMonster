package br.uefs.ecomp.winMonster.main;

import br.uefs.ecomp.winMonster.view.GUI;


import br.uefs.ecomp.winMonster.controller.*;


public class main {
	public static void main (String[] args) {
		//Tire o comentário do teste que quiser usar e comente o outro
		
		
		//TESTES PARA A GUI
		
		AdministradorController controller = new AdministradorController();
		GUI gui = new GUI(controller);
//		String aux = controller.lerArquivo("C:\\Users\\Megazord\\Desktop\\teste.txt.monster");
//		System.out.println(aux);
//		System.out.println("oi");
//		String aux = controller.compactarArquivo("C:\\Users\\Megazord\\Desktop\\teste.txt");
//		JOptionPane.showMessageDialog(null, aux);
		
	}
}