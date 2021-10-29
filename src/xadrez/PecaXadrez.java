package xadrez;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Cor cor;
	private int contadorMovimentos;

	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	public void incrementarContadorMovimentos() {
		contadorMovimentos++;
	}
	public void decrementarContadorMovimentos() {
		contadorMovimentos--;
	}
	public int getContadorMovimentos() {
		return contadorMovimentos;
	}
	protected boolean temPecaAdversaria(Posicao posicao) {
		PecaXadrez p = (PecaXadrez) getTabuleiro().getPeca(posicao);
		return p != null && p.getCor() != cor;
	}
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.toPosicaoXadrez(posicao);
	}
}
