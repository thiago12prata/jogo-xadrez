package xadrez;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Peca;
import tabuleiro.Posicao;
import tabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {

	private Tabuleiro tabuleiro;
	private int turno;
	private Cor jogadorAtual;
	private List<Peca> pecasNoTabuleiro;
	private List<Peca> pecasCapturadas;
	private boolean xeque;

	public Partida() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual= Cor.BRANCO;
		pecasNoTabuleiro = new ArrayList<>();
		pecasCapturadas = new ArrayList<>();
		setupInicial();
	}	
		
	public boolean getXeque() {
		return xeque;
	}
	public int getTurno() {
		return turno;
	}
	public Cor getJogadorAtual() {
		return jogadorAtual;
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
	public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
		Posicao posicao = posicaoOrigem.toPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.getPeca(posicao).movimentosPossiveis();
	}
	public PecaXadrez executarMovimento(PosicaoXadrez posicaoOrigem, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoOrigem.toPosicao();
		Posicao destino = posicaoDestino.toPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = realizarMovimento(origem, destino);
		if(estaEmXeque(jogadorAtual)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new XadrezException("Jogada invalida: Este movimento te coloca em xeque");
		}
		xeque = (estaEmXeque(adversario(jogadorAtual)));
		proximoTurno();
		return (PecaXadrez)pecaCapturada;
	}
	private Peca realizarMovimento(Posicao origem,Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, destino);
		if(pecaCapturada!=null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		Peca p = tabuleiro.removerPeca(destino);
		tabuleiro.colocarPeca(p, origem);
		if(pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	private void validarPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.temPeca(posicao)) {
			throw new XadrezException("Erro ao validar Posição: Não tem peça na origem");
		}
		if(jogadorAtual != ((PecaXadrez)tabuleiro.getPeca(posicao)).getCor()) {
			throw new XadrezException("Erro ao validar Posição: A peça escolhida não é sua");
		}
		if(!tabuleiro.getPeca(posicao).temAlgumMovPossivel()) {
			throw new XadrezException("Erro ao validar Posição: Não tem movimentos possiveis para essa peça");
		}
	}	
	private void validarPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.getPeca(origem).movimentoEPossivel(destino)) {
			throw new XadrezException("A peca escolhida não pode ser movida para a posição escolhida");
		}
	}
	private void colocarNovaPeca(char coluna, int row, PecaXadrez peca) {
		tabuleiro.colocarPeca(peca, new PosicaoXadrez(coluna, row).toPosicao());
		pecasNoTabuleiro.add(peca);
	}
	private void setupInicial() {
		colocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
		colocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

		colocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
		colocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));
	}
	
	private void proximoTurno() {
		turno++;
		jogadorAtual = (jogadorAtual== Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	private Cor adversario(Cor cor) {
		return (cor  == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
	}
	private PecaXadrez getReibyCor(Cor cor) {
		List<Peca> list =  pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : list){
			if(p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe um rei da cor "+ cor +" no tabuleiro");
	}
	private boolean estaEmXeque(Cor cor) {
		Posicao posicaoRei = getReibyCor(cor).getPosicaoXadrez().toPosicao();
		List<Peca> pecasAdversarias = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == adversario(cor)).collect(Collectors.toList());
		for(Peca p : pecasAdversarias) {
			boolean[][] mat = p.movimentosPossiveis();
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
}
