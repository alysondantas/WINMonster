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
		
		this.setLayout(null);
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		button1 = new JButton("Compactar");
		button2 = new JButton("Descompactar");
		button3 = new JButton("Verificar md5");
		splash = new Splash();
		
		button1.setBounds(80, 30, 130, 60);
		button2.setBounds(80, 90, 130, 60);
		button3.setBounds(80, 150, 130, 60);
		
		button1.addActionListener(new CompactarAction(controller));
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Ainda não implementado! :P");
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Ainda não implementado! :P");
			}
		});
		
		splash.showSplash();
		
		add(button1);
		add(button2);
		add(button3);
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			//códigos de teste futuros aqui
		}
	}

}
