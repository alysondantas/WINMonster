package br.uefs.ecomp.winMonster.view;

import javax.swing.*;

import br.uefs.ecomp.winMonster.controller.AdministradorController;


public class GUI extends JFrame {
	private AdministradorController controller;

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private Splash splash;
	
	public GUI(AdministradorController controller) {
		super("WinMONSTER");
		this.controller = controller;
		
		//Configura o Look and Feel do programa com o padr�o do sistema
		try { 
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
		
		//Configura��es da janela do programa
		this.setLayout(null); //sem Layout pr�-definido
		this.setSize(300, 300); //janela 300 x 300
		this.setLocationRelativeTo(null); //deixa a janela no centro na tela
		this.setResizable(false); //n�o pode ser redimensionada
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ao fechar a janela, fecha o programa
		
		//inicializa��o dos componentes da janela
		button1 = new JButton("Compactar");
		button2 = new JButton("Descompactar");
		button3 = new JButton("Verificar md5");
		splash = new Splash();
		
		//configura��o da localiza��o e tamanho dos componentes
		button1.setBounds(80, 30, 130, 60);
		button2.setBounds(80, 90, 130, 60);
		button3.setBounds(80, 150, 130, 60);
		
		//implementa��o das a��es dos bot�es
		button1.addActionListener(new CompactarAction(controller));
		button2.addActionListener(new DescompactarAction(controller));
		button3.addActionListener(new md5Action());
		
		//exibi��o da Splash
		splash.showSplash();
		
		//organiza��o dos componentes da janela
		add(button1);
		add(button2);
		add(button3);
		
		//fazer a janela aparecer para o usu�rio
		this.setVisible(true);
	}

}
