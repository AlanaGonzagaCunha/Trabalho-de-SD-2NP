package br.unifor.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import br.unifor.controle.Controle;

public class Cliente {
	
	private static Controle controle;

	public Cliente(Controle controle) {
		this.controle= controle;
	}


	public void enviaMessagem(String mgs) {

			System.out.println("O cliente conectou ao servidor, envia messagem: " +mgs);

			for(Socket socket: controle.getConexoes()) {
				System.out.println("Enviando mensagem para: "+socket.getInetAddress().getHostAddress());
				try {
					PrintStream ps = new PrintStream(socket.getOutputStream());
					ps.println(mgs);

				} catch (IOException e) {
					e.printStackTrace();
				}

			}
	}

}
