package Xadrez;

import Tabuleiro.Tabuleiro;

public class partidaDeXadrez {
	private Tabuleiro tabuleiro;
	public partidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
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
	

}
