package Xadrez;

import Tabuleiro.Tabuleiro;
import Tabuleiro.Peca;
import Tabuleiro.Posicao;

public abstract class pecaDeXadrez extends Peca {

	private Cor cor;
	private int contaMovimento;

	public pecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContaMovimento() {
		return contaMovimento;
	}
	
	protected void aumentarContaMovimento() {
		contaMovimento++;
	}

	protected void diminuirContaMovimento() {
		contaMovimento--;
	}

	public PosicaoXadrez getPosicaoDoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
	}
	
	protected boolean temOponente(Posicao posicao) {
		pecaDeXadrez p = (pecaDeXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}