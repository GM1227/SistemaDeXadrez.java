package Xadrez;

import Tabuleiro.Peca;
import Tabuleiro.Posicao;
import Tabuleiro.Tabuleiro;
import Xadrez.pecaDeXadrez;
import Xadrez_peças.Rei;
import Xadrez_peças.Torre;
import Xadrez_peças.Bispo;
import Xadrez_peças.Cavalo;
import Xadrez_peças.Peao;
import Xadrez_peças.Rainha;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;




public class partidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	private boolean checkMate;
	private pecaDeXadrez enPassantVulnerable;
	private pecaDeXadrez promover;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> capturarPecas = new ArrayList<>();
	
	public partidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCO;
		initialSetup();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public pecaDeXadrez getEnPassantVulnerable() {
		return enPassantVulnerable;
	}
	
	public pecaDeXadrez getPromover() {
		return promover;
	}
	
	public pecaDeXadrez[][] getPecas() {
		pecaDeXadrez[][] mat = new pecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i=0; i<tabuleiro.getLinhas(); i++) {
			for (int j=0; j<tabuleiro.getColunas(); j++) {
				mat[i][j] = (pecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possiveisMovimentos(PosicaoXadrez posicaoDeOrigem) {
		Posicao posicao = posicaoDeOrigem.toPosicao();
		validandoPosicaoDeOrigem(posicao);
		return tabuleiro.peca(posicao).possiveisMovimentos();
	}
	
	public pecaDeXadrez realizarJogadas(PosicaoXadrez posicaoDeOrigem, PosicaoXadrez posicaoDeDestino) {
		Posicao origem= posicaoDeOrigem.toPosicao();
		Posicao destino = posicaoDeDestino.toPosicao();
		validandoPosicaoDeOrigem(origem);
		validandoPosicaoDeDestino(origem, destino);
		Peca capturarPeca = fazerMovimento(origem, destino);
		
		if (testCheck(jogadorAtual)) {
			desfazerMovi(origem, destino, capturarPeca);
			throw new XadrezExcecoes("Não se pode pôr em cheque");
		}
		
		pecaDeXadrez moverPeca = (pecaDeXadrez)tabuleiro.peca(destino);
		
		
		promover = null;
		if (moverPeca instanceof Peao) {
			if ((moverPeca.getCor() == Cor.BRANCO && destino.getLinha() == 0) || (moverPeca.getCor() == Cor.PRETO && destino.getLinha() == 7)) {
				promover = (pecaDeXadrez)tabuleiro.peca(destino);
				promover = substituirPecaPromovida("Q");
			}
		}
		
		check = (testCheck(oponente(jogadorAtual))) ? true : false;

		if (testCheckMate(oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
			proximoTurno();
		}
		
		
		if (moverPeca instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
			enPassantVulnerable = moverPeca;
		}
		else {
			enPassantVulnerable = null;
		}
		
		return (pecaDeXadrez)capturarPeca;
	}

	public pecaDeXadrez substituirPecaPromovida(String tipo) {
		if (promover == null) {
			throw new IllegalStateException("Não há nenhuma peça a ser promovida");
		}
		if (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") & !tipo.equals("R")) {
			return promover;
		}
		
		Posicao pos = promover.getPosicaoDoXadrez().toPosicao();
		Peca p = tabuleiro.removerPeca(pos);
		pecasNoTabuleiro.remove(p);
		
		pecaDeXadrez novaPeca = novaPeca(tipo, promover.getCor());
		tabuleiro.lugarPeca(novaPeca, pos);
		pecasNoTabuleiro.add(novaPeca);
		
		return novaPeca;
	}
	
	private pecaDeXadrez novaPeca(String type, Cor cor) {
		if (type.equals("B")) return new Bispo(tabuleiro, cor);
		if (type.equals("C")) return new Cavalo(tabuleiro, cor);
		if (type.equals("R")) return new Rainha(tabuleiro, cor);
		return new Torre(tabuleiro, cor);
	}
	
	private Peca fazerMovimento(Posicao origem, Posicao destino) {
		pecaDeXadrez p = (pecaDeXadrez)tabuleiro.removerPeca(origem);
		p.aumentarContaMovimento();
		Peca capturarPeca = tabuleiro.removerPeca(destino);
		tabuleiro.lugarPeca(p, destino);
		
		if (capturarPeca != null) {
			pecasNoTabuleiro.remove(capturarPeca);
			capturarPecas.add(capturarPeca);
		}
		
		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			pecaDeXadrez torre = (pecaDeXadrez)tabuleiro.removerPeca(origemT);
			tabuleiro.lugarPeca(torre, destinoT);
			torre.aumentarContaMovimento();
		}

		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			pecaDeXadrez torre = (pecaDeXadrez)tabuleiro.removerPeca(origemT);
			tabuleiro.lugarPeca(torre, destinoT);
			torre.aumentarContaMovimento();
		}		
		
		
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && capturarPeca == null) {
				Posicao peaoPosicao;
				if (p.getCor() == Cor.BRANCO) {
					peaoPosicao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				}
				else {
					peaoPosicao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				capturarPeca = tabuleiro.removerPeca(peaoPosicao);
				capturarPecas.add(capturarPeca);
				pecasNoTabuleiro.remove(capturarPeca);
			}
		}
		
		return capturarPeca;
	}
	
	private void desfazerMovi(Posicao origem, Posicao destino, Peca capturarPeca) {
		pecaDeXadrez p = (pecaDeXadrez)tabuleiro.removerPeca(destino);
		p.diminuirContaMovimento();
		tabuleiro.lugarPeca(p, origem);
		
		if (capturarPeca != null) {
			tabuleiro.lugarPeca(capturarPeca, destino);
			capturarPecas.remove(capturarPeca);
			pecasNoTabuleiro.add(capturarPeca);
		}

		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			pecaDeXadrez torre = (pecaDeXadrez)tabuleiro.removerPeca(destinoT);
			tabuleiro.lugarPeca(torre, origemT);
			torre.diminuirContaMovimento();
		}

		
		if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			pecaDeXadrez torre = (pecaDeXadrez)tabuleiro.removerPeca(destinoT);
			tabuleiro.lugarPeca(torre, origemT);
			torre.diminuirContaMovimento();
		}
		
	
		if (p instanceof Peao) {
			if (origem.getColuna() != destino.getColuna() && capturarPeca == enPassantVulnerable) {
				pecaDeXadrez peao = (pecaDeXadrez)tabuleiro.removerPeca(destino);
				Posicao peaoPosicao;
				if (p.getCor() == Cor.BRANCO) {
					peaoPosicao = new Posicao(3, destino.getColuna());
				}
				else {
					peaoPosicao = new Posicao(4, destino.getColuna());
				}
				tabuleiro.lugarPeca(peao, peaoPosicao);
			}
		}
	}
	
	private void validandoPosicaoDeOrigem(Posicao posicao) {
		if (!tabuleiro.pecaExiste(posicao)) {
			throw new XadrezExcecoes("Não há nenhuma peça sobre essa posição ");
		}
		if (jogadorAtual != ((pecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadrezExcecoes("A peça escolhida não é sua");
		}
		if (!tabuleiro.peca(posicao).movimentoPossivelOuN()) {
			throw new XadrezExcecoes("Não há movimentos possíveis para a peça escolhida");
			
	}
		}
	
	private void validandoPosicaoDeDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).possivelmovimento(destino)) {
			throw new XadrezExcecoes("A peça escolhida não se pode mover para essa posição");
		}
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private Cor oponente(Cor color) {
		return (color == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	
	private pecaDeXadrez Rei (Cor cor) {
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((pecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for (Peca p : list) {
			if (p instanceof Rei) {
				return (pecaDeXadrez)p;
			}
		}
		throw new IllegalStateException("Não há rei " + cor + "no tabuleiro");
	}
	
	private boolean testCheck(Cor cor) {
		Posicao ReiPosicao = Rei(cor).getPosicaoDoXadrez().toPosicao();
		List<Peca> opponentPieces = pecasNoTabuleiro.stream().filter(x -> ((pecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : opponentPieces) {
			boolean[][] mat = p.possiveisMovimentos();
			if (mat[ReiPosicao.getLinha()][ReiPosicao.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Cor color) {
		if (!testCheck(color)) {
			return false;
		}
		List<Peca> list = pecasNoTabuleiro.stream().filter(x -> ((pecaDeXadrez)x).getCor() == color).collect(Collectors.toList());
		for (Peca p : list) {
			boolean[][] mat = p.possiveisMovimentos();
			for (int i=0; i<tabuleiro.getLinhas(); i++) {
				for (int j=0; j<tabuleiro.getColunas(); j++) {
					if (mat[i][j]) {
						Posicao origem = ((pecaDeXadrez)p).getPosicaoDoXadrez().toPosicao();	/////
						Posicao destino = new Posicao(i, j);
						Peca capturarPeca = fazerMovimento(origem, destino);
						boolean testCheck = testCheck(color);
						desfazerMovi(origem, destino, capturarPeca);
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}	
	
	private void colocarPeca(char coluna, int linha, pecaDeXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosicaoXadrez(coluna, linha).toPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void initialSetup() {
		colocarPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
		colocarPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
		colocarPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
		colocarPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
		colocarPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocarPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        colocarPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocarPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        colocarPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO, this));
        //---------------------------
        colocarPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
        colocarPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocarPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
        colocarPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
        colocarPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocarPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
        colocarPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        colocarPeca('a', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('b', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('c', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('d', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('e', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('f', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('g', 7, new Peao(tabuleiro, Cor.PRETO, this));
        colocarPeca('h', 7, new Peao(tabuleiro, Cor.PRETO, this));
	}
}
