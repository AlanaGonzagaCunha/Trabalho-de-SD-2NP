package br.unifor.servidor;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RecebeConexaoServidor implements Runnable {
	private Servidor server;
	
	public Servidor getServer() {
		return server;
	}

	public void setServer(Servidor server) {
		this.server = server;
	}

	public RecebeConexaoServidor(Servidor servidor) {
		this.server= servidor;
	
	}
	@Override
	public void run() {
		Socket cliente;
		
		while(true) {
		try {
			System.out.println("Esperando novas conex�es...");
			cliente = server.getServidor().accept();
			System.out.println("Nova conex�o com o cliente: " + cliente.getInetAddress().getHostAddress()+ " adicionada!");
			this.server.recebeConexao(cliente);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		}
		

	}

	

}
