package Xadrez_peças;

import Tabuleiro.Tabuleiro;
import Tabuleiro.Posicao;
import Xadrez.partidaDeXadrez;
import Xadrez.pecaDeXadrez;
import Xadrez.Cor;

public class Rei extends pecaDeXadrez {

	private partidaDeXadrez partidadexadrez;
	
	public Rei(Tabuleiro tabuleiro, Cor cor, partidaDeXadrez partidadexadrez) {
		super(tabuleiro, cor);
		this.partidadexadrez = partidadexadrez;
	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Posicao position) {
		pecaDeXadrez p = (pecaDeXadrez)getTabuleiro().peca(position);
		return p == null || p.getCor() != getCor();
	}
	
	private boolean testRookCastling(Posicao position) {
		pecaDeXadrez p = (pecaDeXadrez)getTabuleiro().peca(position);
		return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContaMovimento() == 0;
	}
	
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0, 0);
		
		// above
		p.definirValores(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// below
		p.definirValores(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// left
		p.definirValores(posicao.getLinha(), posicao.getColuna() -1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// right
		p.definirValores(posicao.getLinha(), posicao.getColuna() +1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// nw
		p.definirValores(posicao.getLinha() - 1, posicao.getColuna() -1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		// ne
		p.definirValores(posicao.getLinha() - 1, posicao.getColuna() +1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}


		// sw
		p.definirValores(posicao.getLinha() + 1, posicao.getColuna() -1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		// se
		p.definirValores(posicao.getLinha() + 1, posicao.getColuna() +1);
		if (getTabuleiro().posicaoExiste(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}


		// #specialmove castling
		if (getContaMovimento() == 0 && !partidadexadrez.getCheck()) {
			// #specialmove castling kingside rook
			Posicao posT1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			if (testRookCastling(posT1)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null) {
					mat[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			// #specialmove castling queenside rook
			Posicao posT2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			if (testRookCastling(posT2)) {
				Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				if (getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null) {
					mat[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		return mat;
	}
}
