package br.uefs.ecomp.winMonster.main;

import br.uefs.ecomp.winMonster.view.GUI;


import br.uefs.ecomp.winMonster.controller.*;


public class main {
	public static void main (String[] args) {
		AdministradorController controller = new AdministradorController();
		GUI gui = new GUI(controller);		
	}
}