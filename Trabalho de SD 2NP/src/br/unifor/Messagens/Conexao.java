package br.unifor.Messagens;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import br.unifor.configurações.Configuracoes;
import br.unifor.controle.Controle;

public class Conexao {
	private Controle controle;
	private Socket conexao;
	private String nomeConexao;
	private Thread recebeMensagens;
	private Configuracoes configuracoes;

	public Conexao(Socket conexao, Controle controle) {

		this.conexao = conexao;
		this.controle = controle;
		this.configuracoes = new Configuracoes("0", "0");

		escutaMensagem();
	}

	public void escutaMensagem() {
		RecebeMessagem agente = new RecebeMessagem(this);
		this.recebeMensagens = new Thread(agente);
		this.recebeMensagens.start();
	}

	public void enviaMensagem(String mensagem) {

		try {
			
			System.out.println("Enviando mensagem para " + this.getConexao().getInetAddress().getHostAddress());
			PrintStream saida;
			saida = new PrintStream(this.getConexao().getOutputStream());
			saida.println(mensagem);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void recebeMensagem(String mensagem) {
		String ip= getIP();
	//	this.recebeMensagem(ip, mensagem);
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
