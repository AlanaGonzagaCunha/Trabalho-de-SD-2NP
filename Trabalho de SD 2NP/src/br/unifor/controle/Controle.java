package br.unifor.controle;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import br.unifor.cliente.*;
import br.unifor.cliente.RecebeConexaoCliente;
import br.unifor.configurações.Configuracoes;
import br.unifor.servidor.Servidor;

public class Controle {
	private Cliente cliente;
	private Servidor servidor;

	private static ArrayList<Socket> conexoes = new ArrayList<Socket>();
	private static Configuracoes configuracoes;

	public Controle() {
		this.configuracoes = new Configuracoes("12345");
		Servidor servidor = new Servidor(this);
		verificaRede();
	}

	public String getPortaServidor() {

		return this.configuracoes.getPorta();
	}

	public void adicionaConexao(Socket con) {
		System.out.println("Nova conexão foi adionada: " + con.getInetAddress().getHostAddress());
		this.conexoes.add(con);
	}

	public String isConexao(String ip) {

		for (Socket s : conexoes) {
			if (s.getInetAddress().getHostAddress().equals(ip)) {
				return s.getInetAddress().getHostAddress();
			}
		}
		return "";
	}

	public void verificaRede() {

		System.out.println("Verificar se há conexões abertas na rede... ");
		String ip = this.localizaRede();

		for (int i = 0; i <= 255; i++) {
			String verificaIPs = ip+String.valueOf(i);

			if (this.configuracoes.getIp().equals(verificaIPs)) {
				System.out.println("Já existe conexao aberta no ip: " + verificaIPs);

			} else {
				if (this.isConexao(verificaIPs).equals(verificaIPs)) {
					System.out.println("Já existe conexao aberta no ip: " + verificaIPs);

				} else {
					System.out.println("Meu ip: "+verificaIPs);
					RecebeConexaoCliente con = new RecebeConexaoCliente(verificaIPs, Integer.parseInt(configuracoes.getPorta()), this);

					Thread conectarServidor = new Thread(con);
					conectarServidor.start();
				}
			}
		}

		System.out.println("Fim de verificação... ");

	}

	public String localizaRede() {

		System.out.println("IP: " + this.configuracoes.getIp());
		String ip = this.configuracoes.getIp();
		String[] vetorIP = ip.split("\\.");
		System.out.println("Endereço de ip base: " + vetorIP[0] + "." + vetorIP[1] + "." + vetorIP[2] + ".");

		return vetorIP[0] + "." + vetorIP[1] + "." + vetorIP[2] + ".";
	}

	public void enviaMessagem(String mgs) {
		System.out.println("Total de servidores conectados: " + this.conexoes.size());

		for (Socket c : conexoes) {
			try {
				System.out.println("Enviando messgens, para " + c.getInetAddress().getHostAddress());
				PrintStream ps = new PrintStream(c.getOutputStream());

				ps.println(mgs);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public ArrayList<Socket> getConexoes() {
		return conexoes;
	}

	public void setConexoes(ArrayList<Socket> conexoes) {
		this.conexoes = conexoes;
	}

}
