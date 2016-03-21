package br.uefs.ecomp.winMonster.view;

import java.io.File;
import java.io.IOException;
import javax.swing.*;

import br.uefs.ecomp.winMonster.controller.AdministradorController;

import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
	private AdministradorController controller;

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JFileChooser file;
	private Splash splash;
	
	public GUI(AdministradorController controller) {
		super("WinMONSTER");
		this.controller = controller;
		
		//remover essa parte do programa no futuro
		try { //remover depois
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		//Configura��es da janela do programa
		this.setLayout(null);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//inicializa��o dos componentes da janela
		button1 = new JButton("Compactar");
		button2 = new JButton("Descompactar");
		button3 = new JButton("Verificar md5");
		splash = new Splash();
		
		//configura��o da localiza��o dos componentes
		button1.setBounds(80, 30, 130, 60);
		button2.setBounds(80, 90, 130, 60);
		button3.setBounds(80, 150, 130, 60);
		
		//implementa��o das a��es dos bot�es
		button1.addActionListener(new CompactarAction(controller));
		//bot�o 2 e bot�o 3 n�o implementados
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Ainda n�o implementado! :P");
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Ainda n�o implementado! :P");
			}
		});
		
		//exibi��o da Splash
		splash.showSplash();
		
		//organiza��o dos componentes da janela
		add(button1);
		add(button2);
		add(button3);
		
		//fazer a janela aparecer para o usu�rio
		this.setVisible(true);
	}

	//a��o que eu deixei s� para testes futuros. Vou retirar antes de enviar o projeto final
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			//c�digos de teste futuros aqui
		}
	}

}
