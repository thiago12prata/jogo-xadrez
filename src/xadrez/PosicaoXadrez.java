package xadrez;

import tabuleiro.Posicao;

public class PosicaoXadrez {

	private char coluna;
	private int linha;
	
	public PosicaoXadrez(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h'|| linha < 0 || linha > 8) {
			throw new XadrezException("Erro ao criar a posição: Os valores validos são de a1 a h8");
		}
		this.coluna = coluna;
		this.linha = linha;
	}
	
	public char getColuna() {
		return coluna;
	}
	public void setColuna(char coluna) {
		this.coluna = coluna;
	}
	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	
	protected Posicao toPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoXadrez toPosicaoXadrez(Posicao posicao) {
		return new PosicaoXadrez((char)('a' + posicao.getColuna()),8 - posicao.getLinha());
	}

	@Override
	public String toString() {
		return "" + coluna+linha;
	}
	
	
}
