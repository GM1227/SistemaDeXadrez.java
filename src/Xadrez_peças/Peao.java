package Xadrez_peças;

import Tabuleiro.Tabuleiro;
import Tabuleiro.Posicao;
import Xadrez.partidaDeXadrez;
import Xadrez.pecaDeXadrez;
import Xadrez.Cor;

public class Peao extends pecaDeXadrez {

	private partidaDeXadrez partidadexadrez;
	
	public Peao(Tabuleiro tabuleiro, Cor cor, partidaDeXadrez partidadexadrez) {
		super(tabuleiro, cor);
		this.partidadexadrez = partidadexadrez;
	}

	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);

		if (getCor() == Cor.BRANCO) {
			p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.definirValores(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().pecaExiste(p2) && getContaMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
			p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}	
			
			// #specialmove en passant white
			if (posicao.getLinha() == 3) {
				Posicao left = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(left) && temOponente(left) && getTabuleiro().peca(left) == partidadexadrez.getEnPassantVulnerable()) {
					mat[left.getLinha() - 1][left.getColuna()] = true;
				}
				Posicao right = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(right) && temOponente(right) && getTabuleiro().peca(right) == partidadexadrez.getEnPassantVulnerable()) {
					mat[right.getLinha() - 1][right.getColuna()] = true;
				}
			}
		}
		else {
			p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.definirValores(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p) && getTabuleiro().posicaoExiste(p2) && !getTabuleiro().pecaExiste(p2) && getContaMovimento() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}			
			p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			// #specialmove en passant black
			if (posicao.getLinha() == 4) {
				Posicao left = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(left) && temOponente(left) && getTabuleiro().peca(left) == partidadexadrez.getEnPassantVulnerable()) {
					mat[left.getLinha() + 1][left.getColuna()] = true;
				}
				Posicao right = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(right) && temOponente(right) && getTabuleiro().peca(right) == partidadexadrez.getEnPassantVulnerable()) {
					mat[right.getLinha() + 1][right.getColuna()] = true;
				}
			}			
		}
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}
	
}
