package Xadrez_peças;

import Tabuleiro.Tabuleiro;
import Xadrez.Cor;
import Xadrez.pecaDeXadrez;

public class Rei extends pecaDeXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	@Override
	public String toString() {
		return "R";
	}
	@Override
	public boolean[][] possiveisMovimentos() {
		boolean[][] matriz=new boolean[getTabuleiro().getLinhas() ][getTabuleiro().getColunas()];
		return matriz;
	}
}
