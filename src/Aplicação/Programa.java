package Aplicação;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.PosicaoXadrez;
import Xadrez.XadrezExcecoes;
import Xadrez.partidaDeXadrez;
import Xadrez.pecaDeXadrez;

public class Programa {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		partidaDeXadrez partidaXadrez=new partidaDeXadrez();
		while(true) {
			try {
				UI.clearScreen();
				UI.printTabuleiro(partidaXadrez.pegarPecas());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem=UI.lerPosicao(scan);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = UI.lerPosicao(scan);
				pecaDeXadrez capturarPeca=partidaXadrez.realizarJogadas(origem, destino);
		}
			catch(XadrezExcecoes e) {
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e ) {
				System.out.println(e.getMessage());
			}
			}
	}

}
