package br.uefs.ecomp.winMonster.view;

import java.awt.*;
import javax.swing.*;

public class Splash extends JWindow {
        
// Este é um método simples para mostrar uma tela de apresentção
// no centro da tela durante a quantidade de tempo passada no construtor
    public void showSplash() {        
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.white);
        
        // Configura a posição e o tamanho da janela
        int width = 500;
        int height =400;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);
        
        // Constrói o splash screen
        JLabel label = new JLabel(new ImageIcon("C:\\Users\\Megazord\\Documents\\GitHub\\WINMonster\\src\\main\\java\\br\\uefs\\ecomp\\winMonster\\imgs\\Splash.png"));
        content.add(label, BorderLayout.CENTER);
        /*Color oraRed = new Color(156, 20, 20,  255);
        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));  */      
        // Torna visível
        setVisible(true);
        
        // Espera ate que os recursos estejam carregados
        try { Thread.sleep(1500); } catch (Exception e) {}        
        setVisible(false);        
    }
    
}