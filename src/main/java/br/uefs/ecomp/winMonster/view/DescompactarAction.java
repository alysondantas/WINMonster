package br.uefs.ecomp.winMonster.view;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.uefs.ecomp.winMonster.controller.AdministradorController;
import br.uefs.ecomp.winMonster.exceptions.CriarMD5NuloException;
import br.uefs.ecomp.winMonster.exceptions.DescompactarStringNulaException;

public class DescompactarAction implements ActionListener{
	
	AdministradorController controller;
	
	public DescompactarAction(AdministradorController controller) {
		this.controller = controller; //iguala a inst�ncia do controller da classe � inst�ncia recebida
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser(); //cria um novo selecionador de arquivos
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY); //configura o selecionador para s� receber arquivos
		fc.setDialogTitle(" Abrir Arquivo "); //define o t�tulo da janela de sele��o
        fc.setFileFilter(new FileNameExtensionFilter("Arquivos .MONSTER", "monster")); //filtro do tipo de arquivos que podem ser abertos
		int resposta = fc.showOpenDialog(null); //abre a janela de sele��o e guarda a a��o do usu�rio em resposta
		if (resposta == JFileChooser.APPROVE_OPTION) { //caso o usu�rio tenha selecionado um arquivo
			try {
				String arquivo = controller.lerArquivo(fc.getSelectedFile().getAbsolutePath()); //salva o conte�do do arquivo selecionado em String arquivo
				JOptionPane.showMessageDialog(null, "Descompactando, por favor aguarde...");
				String traducao = controller.descompacta(arquivo); //salva o texto descompactado em String traducao
				String nomeArq = fc.getSelectedFile().getName(); //salva o nome do arquivo que est� na localiza��o do arquivo selecionado na String nomeArq
				String local = fc.getSelectedFile().getPath().replace(nomeArq, ""); //salva o local do arquivo selecionado removendo o nome do arquivo na String local
				nomeArq = nomeArq.substring(0, nomeArq.lastIndexOf(".monster")); //tira o ".monster" do nome do arquivo
				if (controller.verifMd5(controller.md5(traducao), controller.recuperarMd5(fc.getSelectedFile().getAbsolutePath())) == true) { //verifica��o de integridade ao fim da descompacta��o, comparando o md5 gerado atrav�s do texto traduzido com o j� armazenado no arquivo compactado
					JOptionPane.showMessageDialog(null, "Descompacta��o efeituada com sucesso!\n\nN�o houve perda de integridade no arquivo"); //caso os md5 sejam iguais
				} else {
					JOptionPane.showMessageDialog(null, "Descompacta��o falhou!\n\nHouve perda de integridade no arquivo"); //caso os md5 sejam diferentes
				}
				controller.escreverArquivo(traducao, local, nomeArq); //chama o m�todo de escreverArquivo no controller passando o texto compactado com dicion�rio e md5, o local do arquivo e o novo nome concatenado com a extens�o ".monster"
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (DescompactarStringNulaException e2) {
				e2.printStackTrace();
			} catch (CriarMD5NuloException e1) {
				e1.printStackTrace();
			}
		}
	}
}
