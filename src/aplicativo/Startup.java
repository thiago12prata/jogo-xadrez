package aplicativo;

import xadrez.Partida;

public class Startup {

	public static void main(String[] args) {
	

		Partida partida = new Partida();
		IU.imprimirTabuleiro(partida.getPecas());	
	}

}
	