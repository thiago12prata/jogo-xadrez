package aplicativo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;
import xadrez.XadrezException;

public class Startup {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		Partida partida = new Partida();
		List<PecaXadrez> capturadas = new ArrayList<PecaXadrez>();
		
		while(!partida.getXequeMate()) {
			
			try {
				IU.limparTela();
				IU.imprimirPartida(partida, capturadas);	
				System.out.println();
				System.out.println("Origem: ");
				PosicaoXadrez origem = IU.lerjogada(sc);
				
				boolean[][] movimentosPosiveis = partida.movimentosPossiveis(origem);
				IU.limparTela();
				IU.imprimirTabuleiro(partida.getPecas(), movimentosPosiveis);
				
				System.out.println();
				System.out.println("Destino: ");
				PosicaoXadrez destino = IU.lerjogada(sc);									
				PecaXadrez pecaCapturada = partida.executarMovimento(origem, destino);
				
				if (pecaCapturada!= null) {
					capturadas.add(pecaCapturada);
				}
				}
			catch (XadrezException e) {
				System.out.println(e.getMessage());
				System.out.println("Pressione enter para prosseguir");
				sc.nextLine();
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				System.out.println("Pressione enter para prosseguir");
				sc.nextLine();
				sc.nextLine();
			}
		}
		IU.limparTela();
		IU.imprimirPartida(partida, capturadas);
	}

}
	