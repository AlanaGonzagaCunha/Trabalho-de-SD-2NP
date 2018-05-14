package br.unifor.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.Scanner;

import br.unifor.controle.Controle;

public class Servidor {

	ServerSocket servidor;
	public static Controle control;
	private static Thread thread;

	public Servidor(Controle controle) {
		this.control = controle;
		iniciaServicos(control.getPortaServidor());
	}

	public void recebeConexao(Socket cliente) {
		this.getControl().recebeConexao(cliente);
	}

	public void iniciaServicos(String porta) {
		try {
			System.out.println("Porta: "+porta);
			this.servidor = new ServerSocket(Integer.parseInt(porta));

			RecebeConexaoServidor recebeConexao = new RecebeConexaoServidor(this);
			thread = new Thread(recebeConexao);
			thread.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Controle getControl() {
		return control;
	}

	public static void setControl(Controle control) {
		Servidor.control = control;
	}

	public ServerSocket getServidor() {
		return servidor;
	}

	public void setServidor(ServerSocket servidor) {
		this.servidor = servidor;
	}

	

}
