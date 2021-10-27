package tabuleiro;

public class Tabuleiro {

	private int linha;
	private int coluna;
	private Peca[][] pecas;
	
	public Tabuleiro(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
		pecas = new Peca[linha][coluna];
	}

	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	public int getColuna() {
		return coluna;
	}
	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	public Peca getPeca(int linha, int coluna) {
		return pecas[linha][coluna];
	}
	public Peca getPeca(Posicao posicao) {
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	public void colocarPeca(Peca peca, Posicao posicao) {
		pecas[posicao.getLinha()][posicao.getColuna()] =  peca;
		peca.posicao = posicao;
	}
	
	private boolean existePosicao(int linha, int coluna) {
		return linha >= 0 && linha < this.linha && coluna >= 0 && coluna < this.coluna;
	}
	public boolean existePosicao(Posicao posicao) {
		return existePosicao(posicao.getLinha(),posicao.getColuna());
	}
	public boolean temPeca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new TabuleiroException("Erro ao Verificar se tem peça: A posição acessada não existe");
		}
		return getPeca(posicao) != null;
	}
	
	public Peca removerPeca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new TabuleiroException("Erro ao remover peça: A posição acessada não existe");
		}
		if(getPeca(posicao) == null) {
			return null;
		}
		else {
			Peca aux = getPeca(posicao);
			aux.posicao = null;
			pecas[posicao.getLinha()][posicao.getColuna()] = null;
			return aux;
		}
	}
}
