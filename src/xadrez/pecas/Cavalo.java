package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez{

	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "CV";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];	
		
		Posicao p = new Posicao(0, 0);
		
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha() - 2 , posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna() + 2);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}	
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}		
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		if(getTabuleiro().existePosicao(p) && podeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
	private boolean podeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
		return p == null || getCor() != p.getCor();
	}
}
