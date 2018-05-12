package br.unifor.controle;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import br.unifor.Messagens.Conexao;
import br.unifor.cliente.*;
import br.unifor.cliente.RecebeConexaoCliente;
import br.unifor.configurações.Configuracoes;
import br.unifor.servidor.RecebeConexaoServidor;
import br.unifor.servidor.Servidor;

public class Controle {
	private Cliente cliente;
	private Servidor servidor;
	private String SEPARADOR = "";

	private static Configuracoes configuracoes;
	private static ArrayList<Conexao> conexoes = new ArrayList<Conexao>();

	public String getSEPARADOR() {
		return SEPARADOR;
	}

	public void setSEPARADOR(String SEPARADOR) {
		SEPARADOR = SEPARADOR;
	}

	public Controle() {
		this.configuracoes = new Configuracoes("12345", "100", "2000");
		Servidor servidor = new Servidor(this);
		verificaRede();
	}

	public void conectaServidor(String ip) {
		RecebeConexaoCliente agente = new RecebeConexaoCliente(ip, 12345, this);

		Thread agenteConectarComServidor = new Thread(agente);
		agenteConectarComServidor.start();
	}

	public void recebeConexao(Socket socket) {

		String ipNovaConexao = socket.getInetAddress().getHostAddress();

		System.out.println("Conexão " + ipNovaConexao + " adicionada a lista.");

		Conexao conexaoJaExistente = this.isConexao(ipNovaConexao);

		if (conexaoJaExistente != null) {

			this.conexoes.remove(conexaoJaExistente);
		}

		Conexao novaConexao = new Conexao(socket, this);

		this.conexoes.add(novaConexao);

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

			} else {

				Conexao conexao = this.isConexao(verificaIPs);

				if (conexao == null) {
					System.out.println("Meu ip: " + verificaIPs);

					this.conectaServidor(verificaIPs);
				}

			}
		}

	}

	public String localizaRede() {

		System.out.println("IP: " + this.configuracoes.getIp());
		String ip = this.configuracoes.getIp();
		String[] vetorIP = ip.split("\\.");
		System.out.println("Endereço de ip base: " + vetorIP[0] + "." + vetorIP[1] + "." + vetorIP[2] + ".");

		return vetorIP[0] + "." + vetorIP[1] + "." + vetorIP[2] + ".";
	}

	public ArrayList<Conexao> getConexoes() {
		return conexoes;
	}

	public void setConexoes(ArrayList<Conexao> conexoes) {
		this.conexoes = conexoes;
	}

	public boolean verficaConexao() {

		if (this.conexoes.size() > 1) {
			return true;
		}

		return false;
	}

	public void enviaValores(String cpu, String memoria, String bloqueio) {
		
		SEPARADOR=cpu+"|"+memoria+"|"+bloqueio+">";
		
		int cpuConexao = 0, memoriaConexao = 0, bloqueioConexao = 0;

		for (Conexao c : this.conexoes) {
			cpuConexao = Integer.parseInt(c.getConfiguracoes().getCpu());
			memoriaConexao = Integer.parseInt(c.getConfiguracoes().getMemoria());
			bloqueioConexao = Integer.parseInt(c.getConfiguracoes().getBloqueio());

			cpuConexao += Integer.parseInt(cpu);
			memoriaConexao += Integer.parseInt(memoria);
			bloqueioConexao += Integer.parseInt(bloqueio);

		}

		configuracoes.setCpu("" + cpuConexao);
		configuracoes.setMemoria("" + memoriaConexao);
		configuracoes.setBloqueio("" + bloqueioConexao);
		
		//preciso chamar o metodo enviarMessagem(SEPADARADOR) 
		//da conexao para enviar para todos os clientes

	}

	
	

	// public void adicionaConexao(Socket con) {
	// System.out.println("Nova conexão foi adionada: " +
	// con.getInetAddress().getHostAddress());
	// this.conexoes.add(con);
	// }

	// public String isConexao(String ip) {
	//
	// for (Socket s : conexoes) {
	// if (s.getInetAddress().getHostAddress().equals(ip)) {
	// return s.getInetAddress().getHostAddress();
	// }
	// }
	// return "";
	// }

}
