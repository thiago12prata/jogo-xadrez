package tabuleiro;

public class Tabuleiro {

	private int linha;
	private int coluna;
	private Peca[][] pecas;
	
	public Tabuleiro(int linha, int coluna) {
		if(linha < 1 || coluna < 1) {
			throw new TabuleiroException("Erro ao criar o tabuleiro: � necesario ter ao menos 1 linha e 1 coluna");
		}
		this.linha = linha;
		this.coluna = coluna;
		pecas = new Peca[linha][coluna];
	}

	public int getLinha() {
		return linha;
	}
	public int getColuna() {
		return coluna;
	}
	
	public Peca getPeca(int linha, int coluna) {
		if(!existePosicao(linha, coluna)) {
			throw new TabuleiroException("Erro ao Retornar Pe�a: A posi��o acessada n�o existe");
		}
		return pecas[linha][coluna];
	}
	public Peca getPeca(Posicao posicao) {
		if(!existePosicao(posicao)) {
			throw new TabuleiroException("Erro ao Retornar Pe�a: A posi��o acessada n�o existe");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	public void colocarPeca(Peca peca, Posicao posicao) {
		if(temPeca(posicao)){
			throw new TabuleiroException("Erro ao colocar pe�a: ja tem uma pe�a na posi��o "+ posicao);
		}
		
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
			throw new TabuleiroException("Erro ao Verificar se tem pe�a: A posi��o acessada n�o existe");
		}
		return getPeca(posicao) != null;
	}
}
