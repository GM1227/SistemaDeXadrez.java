package Xadrez;

import Tabuleiro.Peça;
import Tabuleiro.Tabuleiro;

public abstract class pecaDeXadrez extends Peça {
	private Cor cor;

	public pecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	

}
