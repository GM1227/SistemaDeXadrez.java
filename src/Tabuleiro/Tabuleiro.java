package Tabuleiro;

public class Tabuleiro {
	private int linhas;
	private int colunas;
	private Peça[][] pecas;

	public Tabuleiro(int linhas, int colunas) {
		if(linhas<1||colunas<1) {
			throw new TabuleiroExcecoes("Erro criando tabuleiro: é necessário que haja pelo menos 1 linha e 1 coluna ");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas =new Peça[linhas][colunas];

	}

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	public Peça peca(int linha,int coluna) {
		if(!posicaoExiste(linha,coluna)) {
			throw new TabuleiroExcecoes("Esta posição não está no tabuleiro"); 
		}
		return pecas[linha][coluna];
	}
	
	public Peça peca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroExcecoes("Esta posição não está no tabuleiro"); 
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];

	}
	
	public void lugarPeca(Peça peca,Posicao posicao) {
		if(existepeca(posicao)) {
			throw new TabuleiroExcecoes("Já existe uma peça sobre a posição"); 
			}
		pecas[posicao.getLinha()][posicao.getColuna()]= peca;
		peca.posicao=posicao;
	}
	
	private boolean posicaoExiste(int linha, int coluna) {
		return linha>=0 && linha<=linhas && coluna>=0 && coluna<=colunas; 
	}
	
	public boolean posicaoExiste(Posicao posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getLinha());
	}
	
	public boolean existepeca(Posicao posicao) {
		if(!posicaoExiste(posicao)) {
			throw new TabuleiroExcecoes("Esta posição não está no tabuleiro");
			}
		return peca(posicao)!=null;
	}




}
