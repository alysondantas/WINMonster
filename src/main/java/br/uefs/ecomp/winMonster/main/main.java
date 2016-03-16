package br.uefs.ecomp.winMonster.main;

import br.uefs.ecomp.winMonster.view.GUI;

import java.io.IOException;

import br.uefs.ecomp.winMonster.controller.*;

public class main {
	public static void main (String[] args) {
		//GUI gui = new GUI();
		AdministradorController controller = new AdministradorController();
		try {
			String str = controller.lerArquivo("C:\\temp.txt");
			System.out.println(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}