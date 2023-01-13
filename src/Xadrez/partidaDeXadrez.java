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
	private void configuracaoInicial() {
		tabuleiro.lugarPeca(new torre(tabuleiro, Cor.BRANCO), new Posicao(0,0));
		tabuleiro.lugarPeca(new torre(tabuleiro, Cor.BRANCO), new Posicao(0,7));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Cor.BRANCO), new Posicao(0,4));
		tabuleiro.lugarPeca(new torre(tabuleiro, Cor.PRETO), new Posicao(7,0));
		tabuleiro.lugarPeca(new torre(tabuleiro, Cor.PRETO), new Posicao(7,7));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Cor.PRETO), new Posicao(7,4));
	}
	

}
