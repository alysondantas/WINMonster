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
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		
		//Configurações da janela do programa
		this.setLayout(null); //sem Layout pré-definido
		this.setSize(300, 300); //janela 300 x 300
		this.setLocationRelativeTo(null); //deixa a janela no centro na tela
		this.setResizable(false); //não pode ser redimensionada
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ao fechar a janela, fecha o programa
		
		//inicialização dos componentes da janela
		button1 = new JButton("Compactar");
		button2 = new JButton("Descompactar");
		button3 = new JButton("Verificar md5");
		splash = new Splash();
		
		//configuração da localização e tamanho dos componentes
		button1.setBounds(80, 30, 130, 60);
		button2.setBounds(80, 90, 130, 60);
		button3.setBounds(80, 150, 130, 60);
		
		//implementação das ações dos botões
		button1.addActionListener(new CompactarAction(controller));
		//botão 2 e botão 3 não implementados
		button2.addActionListener(new DescompactarAction(controller));
		button3.addActionListener(new md5Action());
		
		//exibição da Splash
		splash.showSplash();
		
		//organização dos componentes da janela
		add(button1);
		add(button2);
		add(button3);
		
		//fazer a janela aparecer para o usuário
		this.setVisible(true);
	}

	//ação que eu deixei só para testes futuros. Vou retirar antes de enviar o projeto final
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			//códigos de teste futuros aqui
		}
	}

}
