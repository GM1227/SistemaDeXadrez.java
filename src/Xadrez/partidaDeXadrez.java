package Xadrez;

import Tabuleiro.Peça;
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
	public pecaDeXadrez realizarJogadas(PosicaoXadrez posicaoDeOrigem, PosicaoXadrez posicaoDeDestino) {
		Posicao origem=posicaoDeOrigem.toPosicao();
		Posicao destino=posicaoDeDestino.toPosicao();
		validandoPosicaoDeOrigem(origem);
		Peça pecaCapturada = fazerMovimento(origem,destino);
		return (pecaDeXadrez)pecaCapturada;
	}
	private Peça fazerMovimento(Posicao origem, Posicao destino) {
		Peça p=tabuleiro.removerPeca(origem);
		Peça pecaCapturada=tabuleiro.removerPeca(destino);
		tabuleiro.lugarPeca(p, destino);
		return pecaCapturada;
	}
	private void validandoPosicaoDeOrigem(Posicao posicao) {
		if(!tabuleiro.existepeca(posicao)) {
			throw new XadrezExcecoes("Não existe peça na posição de origem");
			
		}
	}
	private void colocarPeca(char coluna, int linha,pecaDeXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna,linha).toPosicao() );
	}
	private void configuracaoInicial() {
		colocarPeca('b', 6, new torre(tabuleiro, Cor.BRANCO));
		colocarPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO));
		colocarPeca('c', 1, new torre(tabuleiro, Cor.BRANCO));
		colocarPeca('c', 2, new torre(tabuleiro, Cor.BRANCO));
		colocarPeca('d', 2, new torre(tabuleiro, Cor.BRANCO));
		colocarPeca('e', 2, new torre(tabuleiro, Cor.BRANCO));
		colocarPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));
		colocarPeca('c', 7, new torre(tabuleiro,Cor.PRETO));
		colocarPeca('c', 8, new torre(tabuleiro,Cor.PRETO));
		colocarPeca('d', 7, new torre(tabuleiro,Cor.PRETO));
		colocarPeca('e', 7, new torre(tabuleiro,Cor.PRETO));
		colocarPeca('e', 8, new torre(tabuleiro,Cor.PRETO));
		colocarPeca('d', 8, new Rei(tabuleiro,Cor.PRETO));
	}
	

}
