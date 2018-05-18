package br.unifor.Messagens;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;

import br.unifor.configurações.Configuracoes;
import br.unifor.controle.Controle;

public class RecebeMessagem implements Runnable {

	private Conexao conexao;
	

	public RecebeMessagem(Conexao conexao) {
		this.conexao = conexao;
		
	}

	@Override
	public void run() {

		try {

			Scanner entrada = new Scanner(conexao.getConexao().getInputStream());

			while (entrada.hasNextLine()) {

				this.conexao.enviaMensagemConexao(entrada.nextLine());
						
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
