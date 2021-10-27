package aplicativo;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Startup {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		Partida partida = new Partida();
		
		while(true) {
			
			try {
				IU.limparTela();
				IU.imprimirTabuleiro(partida.getPecas());	
				System.out.println();
				System.out.println("Origem: ");
				PosicaoXadrez origem = IU.lerjogada(sc);
				System.out.println();
				System.out.println("Destino: ");
				PosicaoXadrez destino = IU.lerjogada(sc);
				
				PecaXadrez pecaCapturada = partida.executarMovimento(origem, destino);
			}
			catch (XadrezException e) {
				System.out.println(e.getMessage());
				System.out.println("Pressione enter para prosseguir");
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				System.out.println("Pressione enter para prosseguir");
				sc.nextLine();
			}
		}

		
	}

}
	