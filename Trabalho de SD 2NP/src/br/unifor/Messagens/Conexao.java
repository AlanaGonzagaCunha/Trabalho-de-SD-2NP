package br.unifor.Messagens;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextField;

import br.unifor.configurações.Configuracoes;
import br.unifor.controle.Controle;
import br.unifor.swing.Janela;

public class Conexao {
	private Controle controle;
	private Socket conexao;
	private String nomeConexao;
	private Thread recebeMensagens;
	private Configuracoes configuracoes;
	private boolean ativa;
	static int cont=0;

	int somaCpu, somaMemoria, somaBloqueio;

	public Conexao(Socket conexao, Controle controle, Configuracoes c) {

		this.conexao = conexao;
		this.controle = controle;
		this.configuracoes = c;
		ativa = true;

		this.escutaMensagem();
	}

	public void escutaMensagem() {
		RecebeMessagem agente = new RecebeMessagem(this);
		this.recebeMensagens = new Thread(agente);
		this.recebeMensagens.start();
	}

	boolean conexQtd = false;

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public void enviaMensagemConexao(String mensagem) {

		try {
			cont++;
			System.out
					.println("\n Enviando mensagem para " + this.getConexao().getInetAddress().getHostAddress() + "\n");
			Janela.txtArea
					.append("\n Enviando mensagem para " + this.getConexao().getInetAddress().getHostAddress() + "\n");

			PrintStream saida;
			saida = new PrintStream(this.getConexao().getOutputStream());
			System.out.println("Messagem recebida: " + mensagem + "\n");
			Janela.txtArea.append("Messagem recebida: " + mensagem + "\n");
			saida.println(mensagem);

			String[] vetorSeparador = mensagem.split("\\|");
			String cpu = vetorSeparador[0];
			String memoria = vetorSeparador[1];
			String b = vetorSeparador[2];

			String v[] = b.split("\\>");
			String bloqueio = v[0];

			Janela.txtArea.append("Valores recebidos de: " + "\n Cpu: " + cpu + "," + "\n Memoria: " + memoria + ","
					+ "\n Bloqueio: " + bloqueio + "\n");
			System.out.println("Valores recebidos de: " + "\n Cpu: " + cpu + "," + " Memoria: " + memoria + ","
					+ " Bloqueio: " + bloqueio + "\n");

				somaCpu = (Integer.parseInt(controle.getConfiguracoesControle().getCpu()) + (Integer.parseInt(cpu)));
				somaMemoria = (Integer.parseInt(controle.getConfiguracoesControle().getMemoria())
						+ (Integer.parseInt(memoria)));
				somaBloqueio = (Integer.parseInt(controle.getConfiguracoesControle().getBloqueio())
						+ (Integer.parseInt(bloqueio)));
				System.out.println("teste"+somaCpu);
						

				Janela.txtArea.append("Soma sistema:" + "\n Cpu Sistema: " + somaCpu + ", " + "\n Memória Sistema: "
						+ somaMemoria + ", " + "\n Bloqueio Sistema: " + somaBloqueio + "\n");
				System.out.println("Soma sistema:" + "\n Cpu Sistema: " + somaCpu + ", " + "Memória Sistema: "
						+ somaMemoria + ", " + "Bloqueio Sistema: " + somaBloqueio + "\n");

				controle.getConfiguracoesControle().setCpu("" + somaCpu);
				controle.getConfiguracoesControle().setMemoria("" + somaMemoria);
				controle.getConfiguracoesControle().setBloqueio("" + somaBloqueio);

				Janela.sistExibeCPU.setText("" + somaCpu);
				Janela.sistExibeMemoria.setText("" + somaMemoria);
				Janela.sistExibeBloqueio.setText("" + somaBloqueio);

			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getIP() {
		return this.conexao.getInetAddress().getHostAddress();
	}

	public Controle getControle() {
		return controle;
	}

	public void setControle(Controle controle) {
		this.controle = controle;
	}

	public Socket getConexao() {
		return conexao;
	}

	public void setConexao(Socket conexao) {
		this.conexao = conexao;
	}

	public String getNomeConexao() {
		return nomeConexao;
	}

	public void setNomeConexao(String nomeConexao) {
		this.nomeConexao = nomeConexao;
	}

	public Thread getRecebeMensagens() {
		return recebeMensagens;
	}

	public void setRecebeMensagens(Thread recebeMensagens) {
		this.recebeMensagens = recebeMensagens;
	}

	public Configuracoes getConfiguracoes() {
		return configuracoes;
	}

	public void setConfiguracoes(Configuracoes configuracoes) {
		this.configuracoes = configuracoes;
	}

}
