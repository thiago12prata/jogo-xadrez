package xadrez.pecas;

import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);	
	}

	@Override
	public String toString() {
		return "TR";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinha()][getTabuleiro().getColuna()];	
		Posicao p = new Posicao(0, 0);
		
		//acima
		p.setValores(posicao.getLinha() - 1 , posicao.getColuna());
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getTabuleiro().existePosicao(p) && temPecaAdversaria(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//esquerda
		p.setValores(posicao.getLinha() , posicao.getColuna()-1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if(getTabuleiro().existePosicao(p) && temPecaAdversaria(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//direita
		p.setValores(posicao.getLinha() , posicao.getColuna() + 1);
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if(getTabuleiro().existePosicao(p) && temPecaAdversaria(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//abaixo
		p.setValores(posicao.getLinha() + 1 , posicao.getColuna());
		while (getTabuleiro().existePosicao(p) && !getTabuleiro().temPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().existePosicao(p) && temPecaAdversaria(p)){
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}
	
}
