package br.uefs.ecomp.winMonster.util;

/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas

Componente Curricular: MI - Algoritmos II

Concluido em: 12/12/2015

Declaro que este código foi elaborado por mim de forma individual e não contém nenhum

trecho de código de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código

de outra autoria que não a minha está destacado com uma citação para o autor e a fonte

do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

******************************************************************************************/
/**
 * 
 * @author Alyson
 * Classe para a celula generica
 */


public class Celula {//Classe para um celula generica
	private int chave; //valor para representar a prioridade do elemento
	private Celula proxDir;//Referencia para a proxima celula
	private Celula antEsq; //Referência para a célula antEsq
	private Object conteudo;//Objeto generico que vai estar dentro da celula
	private String caractere="";//Objeto generico que vai estar dentro da celula
	private String binario="";
	/**
	 * Metodo construtor da classe obrigando que venha um objeto quando criar celula
	 * @param o
	 */
	
	public Celula(Celula antEsq, Celula proxDir, Object conteudo) {
		this.chave = 0;
        this.proxDir = proxDir;
        this.antEsq = antEsq;
        this.conteudo = conteudo;
    }
	
	public Celula(int chave, Object conteudo) {
		this.chave = chave;
		this.proxDir = null;
		this.antEsq = null;
		this.conteudo = conteudo;
	}
	
	public Celula(String caractere) {
		this.chave = 0;
		this.proxDir = null;
		this.antEsq = null;
		this.caractere = caractere;
	}

    public Celula() { //construtor auxiliar que inicializa o conteúdo como nulo
        this.conteudo = null;
    }

    public Celula getProxDir() {
        return proxDir;
    }

    public void setProxDir(Celula proxDir) {
        this.proxDir = proxDir;
    }

    public Celula getAntEsq() {
        return antEsq;
    }

    public void setAntEsq(Celula antEsq) {
        this.antEsq = antEsq;
    }

    public Object getObjeto() {
        return conteudo;
    }

    public void setObjeto(Object conteudo) {
        this.conteudo = conteudo;
    }

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	/**
	 * @return the caractere
	 */
	public String getCaractere() {
		return caractere;
	}

	/**
	 * @param caractere the caractere to set
	 */
	public void setCaractere(String caractere) {
		this.caractere = caractere;
	}

	/**
	 * @return the binario
	 */
	public String getBinario() {
		return binario;
	}

	/**
	 * @param binario the binario to set
	 */
	public void setBinario(String binario) {
		this.binario = binario;
	}
    
    

} 



//////////////////////////////////Classe antiga////////////////////////////////////////////////



	/*public Celula(Object o){
		this.objeto=o;//objeto recebe object que foi passado
	}
	//Construtores do encapsulamento
	public Celula getProximo() {// para que em outro pacote possa acessar o proximo
		return proximo;//retorna o proximo
	}
	//
	public void setProximo(Celula proximo) {//Para que o ser alterado o proximo por outro pacote
		this.proximo = proximo;//proximo da classe recebe o que foi passado
	}
	//Os dois a baixo tem o mesmo papel que o get e set acima.
	public Object getObjeto() {
		return objeto;
	}
	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}
	//
}*/
