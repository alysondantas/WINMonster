package br.uefs.ecomp.winMonster.view;

import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {

	private JButton button1;
	private JLabel label;
	private JTextField nome;
	private String nomeSalvo = "";

	public GUI() {
		super("WinMONSTER");
		try {
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
		label = new JLabel(" Digite seu nome: ");
		button1 = new JButton("Clique");
		nome = new JTextField();
		label.setBounds(80, 30, 120, 30);
		nome.setBounds(80, 60, 130, 30);
		button1.setBounds(95, 190, 100, 30);
		button1.addActionListener(this);
		add(button1);
		add(nome);
		add(label);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			nomeSalvo = nome.getText();
			JOptionPane.showMessageDialog(null, "Oi " + nomeSalvo);
		}
	}

}
