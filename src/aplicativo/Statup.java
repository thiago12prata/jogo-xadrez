package aplicativo;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Partida;

public class Statup {

	public static void main(String[] args) {
	
		Tabuleiro tabuleiro = new Tabuleiro(8, 8);
		Partida partida = new Partida();
		IU.imprimirTabuleiro(partida.getPecas());	
	}

}
