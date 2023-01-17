package Xadrez_peças;

import Tabuleiro.Tabuleiro;
import Tabuleiro.Posicao;
import Xadrez.pecaDeXadrez;
import Xadrez.Cor;

public class Rainha extends pecaDeXadrez {

	public Rainha(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "R";
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// above
		p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// left
		p.definirValores(posicao.getLinha(), posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// right
		p.definirValores(posicao.getLinha(), posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// below
		p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// nw
		p.definirValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.definirValores(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// ne
		p.definirValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.definirValores(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// se
		p.definirValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.definirValores(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// sw
		p.definirValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().pecaExiste(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.definirValores(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && temOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
}

