package br.uefs.ecomp.winMonster.main;

import br.uefs.ecomp.winMonster.view.EscolherArquivo;
import br.uefs.ecomp.winMonster.view.GUI;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import br.uefs.ecomp.winMonster.controller.*;
import br.uefs.ecomp.winMonster.exceptions.CaractereInexistenteException;
import br.uefs.ecomp.winMonster.exceptions.DescompactarStringNulaException;
import br.uefs.ecomp.winMonster.util.Arvore;
import br.uefs.ecomp.winMonster.util.Fila;

public class main {
	public static void main (String[] args) throws IOException, DescompactarStringNulaException {
		//Tire o comentário do teste que quiser usar e comente o outro
		
		
		//TESTES PARA A GUI
		
		AdministradorController controller = new AdministradorController();
		GUI gui = new GUI(controller);
//		controller.gerarPrioridade("rem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dignissim vulputate cursus. Sed porttitor est ut neque pharetra feugiat. Nam facilisis feugiat nisl scelerisque euismod. In sed varius dui. Fusce et sem hendrerit, lobortis arcu eu, bibendum augue. Aenean tristique dictum sem nec tempor. Donec nisl elit, auctor a nunc nec, faucibus maximus elit. Integer dignissim felis sed ultrices interdum. Nam vitae neque in eros iaculis suscipit nec vel diam. Aliquam ullamcorper dictum orci. Curabitur sit amet lacus lorem. Sed ultrices feugiat elementum.\n\nCurabitur euismod ex lectus, vel dignissim tortor luctus eu. Mauris facilisis porta lectus, at convallis lacus egestas et. Phasellus varius auctor nibh eget auctor. Proin condimentum malesuada mi, id sollicitudin metus porta vitae. Nullam in felis orci. Aliquam quis ornare lorem, eget interdum elit. Vestibulum convallis sapien sit amet quam vehicula finibus. Morbi a auctor nulla. Praesent id rhoncus sem, nec varius nunc. Vestibulum porta tortor ut ullamcorper suscipit. Donec ac tempor diam. Donec tincidunt justo vitae risus vestibulum, vitae mattis urna auctor. Nulla finibus neque eget nibh convallis bibendum.\n\nMorbi interdum nisl turpis, ac placerat est faucibus eget. Praesent dapibus a nisl nec euismod. Maecenas sem sem, tempus id vestibulum in, vulputate in nulla. Etiam id eleifend lacus, posuere imperdiet eros. Donec aliquam mi felis, quis tempor magna vestibulum non. Praesent consequat ipsum id ipsum hendrerit, eget molestie turpis imperdiet. Aliquam scelerisque posuere turpis sed luctus. Etiam commodo tincidunt nibh, vel facilisis mauris finibus eu. Vestibulum feugiat mattis lorem. Praesent ullamcorper, augue quis ultricies tincidunt, ex risus pulvinar orci, eget semper erat ipsum id orci. Sed quis pellentesque sapien.\n\nPellentesque ultrices ipsum id enim convallis, a luctus ante tristique. In suscipit interdum magna fringilla rhoncus. Etiam consequat vehicula risus ac porttitor. Fusce laoreet interdum efficitur. In in tortor dolor. Phasellus pulvinar sapien in quam dignissim, et porttitor nisi ornare. Donec sit amet sapien ac risus molestie consectetur. Aenean eu velit efficitur, condimentum sem at, tincidunt erat. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Etiam ipsum sem, placerat sed mi a, posuere rutrum magna. Curabitur finibus tellus at elit dignissim, sed sollicitudin eros cursus. Nulla facilisi. Quisque sit amet nibh auctor, tincidunt risus id, euismod metus.\n\nFusce suscipit, nunc et ullamcorper placerat, turpis tellus tincidunt orci, fermentum aliquam dui orci vitae quam. Aliquam eget diam cursus, maximus justo sed, lacinia ex. Quisque nec porttitor justo, et consequat massa. Aliquam erat volutpat. Nunc pretium fringilla velit, nec fermentum leo euismod sed. Etiam placerat neque ligula, eget pretium magna suscipit ut. Suspendisse ultricies massa sit amet sem porttitor, eget facilisis arcu efficitur. Nullam pulvinar, orci id venenatis vulputate, elit metus sodales lectus, a pellentesque elit eros et tellus. Curabitur molestie dolor nec tellus egestas, in interdum nulla lobortis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;");
		
		//TESTES PARA CONSOLE
		
		/*AdministradorController controller = new AdministradorController();
		try {
			String str = controller.lerArquivo("C:\\temp.txt");
			System.out.println(str);
			//Fila copia = new Fila();
			Fila fila = controller.gerarPrioridade(str);
			controller.imprimirFila(fila);
			Fila copia = controller.getDicionario();
			Arvore arvore = new Arvore();
			arvore = arvore.inserirHuffman(fila);
			try {
				arvore.construirDicionario(copia);
			} catch (CaractereInexistenteException e) {
				JOptionPane.showMessageDialog(null, "Erro 1");
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro 2");
			e.printStackTrace();
		}
		
		*/
		
//		JFileChooser fc = new JFileChooser();
//		fc.showOpenDialog(null);
//		controller.descompacta("g 010   011 z 100 l 101 u 00 a 11 \n\nd8e788318a46ff18a89deb24290bcfa3\n\n1101000110111110000101");
		
	}
}