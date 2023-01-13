package Xadrez;

import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import Xadrez_peças.Rei;
import Xadrez_peças.torre;

public class partidaDeXadrez {
	private Tabuleiro tabuleiro;
	public partidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		configuracaoInicial();
	}
	public pecaDeXadrez[][] pegarPecas(){
		pecaDeXadrez[][] matriz=new pecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		
		for (int l=0;l<tabuleiro.getLinhas();l++) {
			
			for(int c=0;c<tabuleiro.getColunas();c++) {
				matriz[l][c]=(pecaDeXadrez)tabuleiro.peca(l,c);
			}
		}
		
		return matriz;
		
	}
	private void colocarPeca(char coluna, int linha,pecaDeXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna,linha).toPosicao() );
	}
	private void configuracaoInicial() {
		colocarPeca('a', 8, new torre(tabuleiro, Cor.BRANCO));
		colocarPeca('a', 1, new torre(tabuleiro, Cor.PRETO));
		colocarPeca('h', 7, new torre(tabuleiro, Cor.BRANCO));
		colocarPeca('h', 1, new torre(tabuleiro, Cor.PRETO));
		colocarPeca('e', 8, new Rei(tabuleiro, Cor.BRANCO));
		colocarPeca('e', 1, new Rei(tabuleiro, Cor.PRETO));
	}
	

}
