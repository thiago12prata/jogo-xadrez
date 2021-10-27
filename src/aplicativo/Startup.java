package aplicativo;

import java.util.Scanner;

import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Startup {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		Partida partida = new Partida();
		
		while(true) {
			IU.imprimirTabuleiro(partida.getPecas());	
			System.out.println();
			System.out.println("Origem: ");
			PosicaoXadrez origem = IU.lerjogada(sc);
			
			System.out.println();
			System.out.println("Destino: ");
			PosicaoXadrez destino = IU.lerjogada(sc);
			
			PecaXadrez pecaCapturada = partida.executarMovimento(origem, destino);
			
		}

		
	}

}
	