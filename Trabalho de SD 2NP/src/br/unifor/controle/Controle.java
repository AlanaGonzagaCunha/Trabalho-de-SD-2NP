package br.unifor.controle;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextField;

import br.unifor.Messagens.Conexao;
import br.unifor.cliente.*;
import br.unifor.configurações.Configuracoes;
import br.unifor.servidor.RecebeConexaoServidor;
import br.unifor.servidor.Servidor;
import br.unifor.swing.Janela;

public class Controle {

	private String SEPARADOR = "";
	private Cliente cliente;
	private static Configuracoes configuracoes;
	private static ArrayList<Conexao> conexoes = new ArrayList<Conexao>();

	public String getSEPARADOR() {
		return SEPARADOR;
	}

	public void setSEPARADOR(String SEPARADOR) {
		SEPARADOR = SEPARADOR;
	}

	public Controle() {
		this.configuracoes = new Configuracoes("12345", "0", "0", "0");
		Servidor servidor = new Servidor(this);
		cliente = new Cliente(this, configuracoes);
		verificaRede();

	}

	public void conectaServidor(String ip) {
		RecebeConexaoCliente agente = new RecebeConexaoCliente(ip, 12345, this);

		Thread agenteConectarComServidor = new Thread(agente);
		agenteConectarComServidor.start();
	}

	public void recebeConexao(Socket socket) {
		String ipNovaConexao = socket.getInetAddress().getHostAddress();

		Janela.txtArea.append("Conexão " + ipNovaConexao + " adicionada a lista. \n");
		Janela.modelo.insertRow(Janela.cont, new String[] { ipNovaConexao, "" + socket.getPort() });
		System.out.println("Conexão " + ipNovaConexao + " adicionada a lista.");

		Conexao conexaoJaExistente = this.isConexao(ipNovaConexao);

		if (conexaoJaExistente != null) {
			this.conexoes.remove(conexaoJaExistente);
		}

		Conexao novaConexao = new Conexao(socket, this);

		this.conexoes.add(novaConexao);
		Janela.modelo.addRow(new String[] { novaConexao.getIP(), configuracoes.getPorta() });

	}

	public String getPortaServidor() {

		return this.configuracoes.getPorta();
	}

	public Conexao isConexao(String ip) {

		for (Conexao conexao : this.conexoes) {

			String ipConexao = conexao.getConexao().getInetAddress().getHostAddress();

			if (ip.equals(ipConexao)) {

				return conexao;
			}
		}

		return null;
	}

	public void verificaRede() {

		String ip = this.localizaRede();

		for (int i = 0; i <= 255; i++) {
			String verificaIPs = ip + String.valueOf(i);

			if (this.configuracoes.getIp().equals(verificaIPs)) {
				System.out.println("Já existe conexao aberta no ip: " + verificaIPs);

				}
			 else {

				Conexao conexao = this.isConexao(verificaIPs);

				if (conexao == null) {
					System.out.println("Ip: " + verificaIPs);

					this.conectaServidor(verificaIPs);
				}

			}
		}

	}

	public String localizaRede() {

		System.out.println("IP: " + this.configuracoes.getIp());
		String ip = this.configuracoes.getIp();
		String[] vetorIP = ip.split("\\.");

		return vetorIP[0] + "." + vetorIP[1] + "." + vetorIP[2] + ".";
	}

	public ArrayList<Conexao> getConexoes() {
		return conexoes;
	}

	public void setConexoes(ArrayList<Conexao> conexoes) {
		this.conexoes = conexoes;
	}

	public void verficaConexao() {

		System.out.println(conexoes.size());
	}

	public void enviaMensagemControle(String sep) {
		this.cliente.enviaMessagem(sep);
	}

	public Configuracoes getConfiguracoesControle() {
		return this.configuracoes;
	}

}
