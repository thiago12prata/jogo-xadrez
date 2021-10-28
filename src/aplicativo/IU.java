package aplicativo;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class IU {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

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

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void limparTela() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static PosicaoXadrez lerjogada(Scanner sc) {
		try {
			String s = sc.next();
			char coluna = s.charAt(0);
			coluna = Character.toLowerCase(coluna);
			int linha = Integer.parseInt(s.substring(1));
			return new PosicaoXadrez(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro na entrada dos dados voce deve informar como nos formatos exemplo  A1 ou a1, somente de A1 a H8");
		}
		
	}

	private static void imprimirPeca(PecaXadrez peca, boolean background) {
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
			if (peca == null) {
				System.out.print("--" + ANSI_RESET);
			} else {
				if (peca.getCor() == Cor.BRANCO) {
					System.out.print(ANSI_WHITE + peca + ANSI_RESET);
				} else {
					System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
				}
			}
			System.out.print(" ");
	}

	public static void imprimirPartida(Partida partida, List<PecaXadrez> capturadas) {
		imprimirTabuleiro(partida.getPecas());
		System.out.println();
		imprimirPecasCapturadas(capturadas);
		System.out.println();
		System.out.println("Turno: " + partida.getTurno());
		if(!partida.getXequeMate()) {
			System.out.println("Esperamdo o jogador: " + partida.getJogadorAtual());	
			if(partida.getXeque()) {
				System.out.println("O jogador "+ partida.getJogadorAtual() +" esta em xeque");
			}
		}
		else {
			System.out.println("XEQUEMATE!!!");
			System.out.println("Vencedor: "+ partida.getJogadorAtual());
		}
	}
	
	public static void imprimirTabuleiro(PecaXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas[i].length; j++) {
				imprimirPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  A  B  C  D  E  F  G  H");
	}
	
	public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPosiveis) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas[i].length; j++) {
				imprimirPeca(pecas[i][j], movimentosPosiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  A  B  C  D  E  F  G  H");
	}
	
	private static void imprimirPecasCapturadas(List<PecaXadrez> capturadas) {
		List<PecaXadrez> brancas = capturadas.stream().filter( x -> x.getCor()==Cor.BRANCO).collect(Collectors.toList());
		List<PecaXadrez> pretas = capturadas.stream().filter( x -> x.getCor()==Cor.PRETO).collect(Collectors.toList());
		System.out.println("Pecas Capturadas:");
		System.out.print("Brancas: ");
		System.out.println(ANSI_WHITE);
		System.out.println(Arrays.toString(brancas.toArray()));
		System.out.println(ANSI_RESET);
		System.out.print("Pretas: ");
		System.out.println(ANSI_YELLOW);
		System.out.println(Arrays.toString(pretas.toArray()));
		System.out.println(ANSI_RESET);
	}
}
