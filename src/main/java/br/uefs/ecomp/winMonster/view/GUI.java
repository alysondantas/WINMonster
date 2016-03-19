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
	private JLabel label;
	private JTextField nome;
	private JFileChooser file;
	private String nomeSalvo = "";

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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label = new JLabel(" Caminho do Arquivo: ");
		button1 = new JButton("Compactar");
		nome = new JTextField();
		nome.setEditable(false);
		label.setBounds(80, 30, 120, 30);
		nome.setBounds(80, 60, 130, 30);
		button1.setBounds(95, 190, 100, 30);
		button1.addActionListener(new CompactarAction(nome, controller));
		add(button1);
		add(nome);
		add(label);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			//códigos de teste futuros aqui
		}
	}

}
