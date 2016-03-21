package br.uefs.ecomp.winMonster.view;

import java.awt.*;
import javax.swing.*;

public class Splash extends JWindow {
        
// Este � um m�todo simples para mostrar uma tela de apresent��o
// no centro da tela durante a quantidade de tempo passada no construtor
    public void showSplash() {        
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);
        
        // Configura a posi��o e o tamanho da janela
        int largura = 500;
        int altura = 400;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-largura)/2;
        int y = (screen.height-altura)/2;
        setBounds(x,y,largura,altura);
        
        // Constr�i o splash screen
        JLabel label = new JLabel(new ImageIcon("src\\main\\java\\br\\uefs\\ecomp\\winMonster\\imgs\\Splash.png"));
        content.add(label, BorderLayout.CENTER);
        /*Color oraRed = new Color(156, 20, 20,  255);
        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));  */      
        // Torna vis�vel
        setVisible(true);
        
        // Espera ate que os recursos estejam carregados
        try { Thread.sleep(1500); } catch (Exception e) {}        
        setVisible(false);        
    }
    
}