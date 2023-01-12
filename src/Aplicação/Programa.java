package Aplicação;

import Xadrez.partidaDeXadrez;

public class Programa {

	public static void main(String[] args) {
		partidaDeXadrez partidaXadrez=new partidaDeXadrez();
		UI.printTabuleiro(partidaXadrez.pegarPecas());

	}

}
