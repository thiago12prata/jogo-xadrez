package xadrez;

import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {

	private Tabuleiro tabuleiro;

	public Partida() {
		tabuleiro = new Tabuleiro(8, 8);
		setupInicial();
	}
	
	public PecaXadrez[][] getPecas() {
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinha()][tabuleiro.getColuna()];
		
		for (int i=0; i<tabuleiro.getLinha(); i++) {
			for (int j=0; j<tabuleiro.getColuna(); j++) {
				mat[i][j] = (PecaXadrez)tabuleiro.getPeca(i, j);
			}
		}
		return mat;
	}
	private void colocarNovaPeca(char coluna, int row, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, row).posicaoXadrezParaPosicao());
	}
	
	private void setupInicial() {
		colocarNovaPeca('b', 6 ,new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 8 ,new Rei(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 1 ,new Rei(tabuleiro, Cor.PRETO));
	}
}
