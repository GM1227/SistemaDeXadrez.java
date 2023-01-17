package Aplicação;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrez.PosicaoXadrez;
import Xadrez.XadrezExcecoes;
import Xadrez.partidaDeXadrez;
import Xadrez.pecaDeXadrez;

public class Programa {
		public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		partidaDeXadrez partidaXadrez = new partidaDeXadrez();
		List<pecaDeXadrez> capturada = new ArrayList<>();
		
		while (!partidaXadrez.getCheckMate()) {
			try {
				UI.clearScreen();
				UI.printPartida(partidaXadrez, capturada);;
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = UI.lerPosicao(sc);
				
				boolean[][] possiveisMovimentos = partidaXadrez.possiveisMovimentos(origem);
				UI.clearScreen();
				UI.printtabuleiro(partidaXadrez.getPecas(), possiveisMovimentos);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicao(sc);
				
				pecaDeXadrez capturarPeca = partidaXadrez.realizarJogadas(origem, destino);
				
				if (capturarPeca != null) {
					capturada.add(capturarPeca);
				}
				
				if (partidaXadrez.getPromover() != null) {
					System.out.print("Insira peça para promoção (B/C/T/R): ");
					String tipo = sc.nextLine().toUpperCase();
					while (!tipo.equals("B") && !tipo.equals("C") && !tipo.equals("T") & !tipo.equals("R")) {
						System.out.print("Valor inválido! Insira peça para promoção (C/T/B/R): ");
						tipo = sc.nextLine().toUpperCase();
					}
					partidaXadrez.substituirPecaPromovida(tipo);
				}
			}
			catch (XadrezExcecoes e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		}
	}