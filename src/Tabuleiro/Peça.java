package Tabuleiro;

public abstract class Peça {
	protected Posicao posicao;
	private Tabuleiro tabuleiro;
	public Peça(Tabuleiro tabuleiro) {
		super();
		this.tabuleiro = tabuleiro;
		posicao=null;
	}
	protected Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	public abstract boolean[][] possiveisMovimentos();
	public boolean possivelmovimento(Posicao posicao) {
		return possiveisMovimentos()[posicao.getLinha()][posicao.getColuna()];
	}
	public boolean movimentoPossivelOuN() {
		boolean[][] matriz=possiveisMovimentos();
		for(int l=0;l<matriz.length;l++) {
			for(int c=0;c<matriz.length;c++) {
				if(matriz[l][c]) {
					return true;
					
				}
			}
		}
		return false;
	}
	
}
