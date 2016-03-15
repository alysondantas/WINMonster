package br.uefs.ecomp.winMonster.util;

public class Arvore {

	CelulaArvore raiz; //elemento inicial da árvore
	int tamanho; //número de elementos da árvore

	public Arvore (){ //construtor padrão para a árvore que inicializa sua raíz como nulo e seu tamanho como zero
		this.raiz = null;
		this.tamanho = 0;
	}

	public boolean estaVazia() {
		return raiz==null;//retorna true se a primeira celula for nula
	}
	public int obterTamanho() {
		return tamanho;//quando sair do laço retorna o tamanho da lista
	}
	//Não garanto que nada disso funcione corretamente
	/*public CelulaArvore encontra(int key){
		CelulaArvore atual=raiz;
		while(atual.getChave()!=key){
			if(key<atual.getChave()){
				atual=atual.getEsquerda();
				if(atual==null){
					return null;
				}else{
					atual=atual.getDireita();
					if(atual==null){
						return null;
					}
				}
			}
		}
		return atual;
	}*/

	/*public void inserir(CelulaArvore celula){
		CelulaArvore novaCelula=celula;
		if(raiz==null){
			raiz=novaCelula;
		}else{
			CelulaArvore atual=raiz;
			CelulaArvore pai;
			while(true){
				pai=atual;
				if(celula.getChave()<atual.getChave()){
					atual=atual.getEsquerda();
					if(atual==null){
						pai.setEsquerda(novaCelula);
						return;
					}
				}else{
					atual=atual.getDireita();
					if(atual==null){
						pai.setDireita(novaCelula);
						return;
					}
				}
			}
		}
	}*/

	public CelulaArvore retornaRaiz(){
		return raiz;
	}

	public void colocaRaiz(CelulaArvore raiz1){
		this.raiz=raiz1;
	}

	public Arvore inserirHuffman(Fila filaprioridade){//metodo para remover os dois elementos da fila e colocar em uma arvore
		if(!filaprioridade.estaVazia()){
			if(filaprioridade.obterTamanho()>1){//se existir elemento proximo continua
				int frequencia=0;//frequencia que esta na celula
				Celula obj1 = (Celula) filaprioridade.removerInicio();//filha da esquerda que vem da fila
				Celula obj2 = (Celula) filaprioridade.removerInicio();//filha da esquerda que vem da fila
				Arvore arv1,arv2,nova=new Arvore();//cria tres variaveis de arvore sendo o nova a nova arvore e as outras duas auxiliares
				frequencia= obj1.getChave()+obj2.getChave();//frequencia recebe as frequencias do primeiro e segundo elemento da fila
				CelulaArvore novaCelula=new CelulaArvore();//cria nova celula da arvore
				arv1=(Arvore) obj1.getObjeto();//arv1 recebe a arvore do primeiro elemento da fila
				novaCelula.setEsquerda(arv1.retornaRaiz());// a referencia da esquerda recebe o primeiro elemento da fila
				arv2=(Arvore) obj2.getObjeto();//arv2 recebe a arvore do segundo elemento da fila
				novaCelula.setDireita(arv2.retornaRaiz());// a referencia da direita recebe o segundo elemento da fila
				nova.colocaRaiz(novaCelula);//a raiz da arvore agora é a nova celula
				filaprioridade.inserir(frequencia, nova);//insere na fila uma nova celula sendo ela uma arvore
				inserirHuffman(filaprioridade);//recursivo enquanto existir mais que de um elemento na fila
			}
		}
		Celula inicio = (Celula) filaprioridade.recuperarInicio();//pega o primeiro elemento da fila
		Arvore arv3 = (Arvore) inicio.getObjeto();//pega a arvore do primeiro elemento da fila
		return arv3;//retorna a arvore pronta
	}


	/*	public void inserir2(CelulaArvore node, int valor) {
		//Verifica se o valor a ser inserido é menor que o nodo corrente da árovre, se sim vai para subarvore esquerda
		if (valor < node.getChave()) {
			//Se tiver elemento no nodo esquerdo continua a busca
			if (node.getEsquerda() != null) {
				inserir2(node.getEsquerda(), valor);
			} else {
				//Se nodo esquerdo vazio insere o novo nodo aqui
				System.out.println("  Inserindo " + valor + " a esquerda de " + node.valor);
				node.getEsquerda() = new CelulaArvore(valor);
			}
			//Verifica se o valor a ser inserido é maior que o nodo corrente da árvore, se sim vai para subarvore direita
		} else if (valor > node.valor) {
			//Se tiver elemento no nodo direito continua a busca
			if (node.direita != null) {
				inserir2(node.direita, valor);
			} else {
				//Se nodo direito vazio insere o novo nodo aqui
				System.out.println("  Inserindo " + valor + " a direita de " + node.valor);
				node.direita = new No(valor);
			}
		}*/
}