package Aplicação;

import java.util.InputMismatchException;
import java.util.Scanner;

import Xadrez.Cor;
import Xadrez.PosicaoXadrez;
import Xadrez.pecaDeXadrez;

public class UI {
	// Referencia das cores... https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
	public static PosicaoXadrez lerPosicao(Scanner scan) {
		try {
		String s=scan.nextLine();
		char coluna= s.charAt(0);
		int linha=Integer.parseInt(s.substring(1));
		return new PosicaoXadrez(coluna,linha);
		}
		catch(RuntimeException e) {
			throw new InputMismatchException("Erro de leitura na posição de xadrez, valores válidos são de a1 até h8");
		}
		}

public static void printTabuleiro(pecaDeXadrez[][] pecas) {
	for(int l=0;l<pecas.length;l++) {
		System.out.print(8-l+" ");
		for(int c=0;c<pecas.length;c++) {
			printPeca(pecas[l][c]);
		}
		System.out.println();
	}
	System.out.println("  a b c d e f g h");
	
}

private static void printPeca(pecaDeXadrez peca) {
    	if (peca == null) {
            System.out.print("-");
        }
        else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	}
 