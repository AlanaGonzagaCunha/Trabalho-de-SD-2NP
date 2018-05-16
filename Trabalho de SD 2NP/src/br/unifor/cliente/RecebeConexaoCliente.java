package br.unifor.cliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import br.unifor.controle.Controle;
import br.unifor.swing.Janela;

public class RecebeConexaoCliente implements Runnable {

	String ip;
	int porta;
	Controle controle;

	public RecebeConexaoCliente(String ip, int porta, Controle controle) {
		this.ip = ip;
		this.porta = porta;
		this.controle = controle;
	}

	@Override
	public void run() {

		try {
			Socket abreConexao = new Socket(this.ip, this.porta);
			System.out.println("Cliente conectado no servidor!!!");
			Janela.txtArea.append("Cliente conectado no servidor!!! \n");
			
			this.controle.recebeConexao(abreConexao);

		} catch (IOException e) {
			System.out
					.println("Não há servidor conectado neste ip: " + ip);
		}

	}

}
