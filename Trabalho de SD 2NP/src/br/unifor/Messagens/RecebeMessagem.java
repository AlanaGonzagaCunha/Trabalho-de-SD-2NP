package br.unifor.Messagens;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import br.unifor.configurações.Configuracoes;
import br.unifor.controle.Controle;

public class RecebeMessagem implements Runnable {

	private Conexao conexao;

	public RecebeMessagem(Conexao conexao) {
		this.conexao = conexao;
	}

	@Override
	public void run() {

			Socket cliente;
			
			try {
				
				Scanner entrada = new Scanner(conexao.getConexao().getInputStream());
				
				while(entrada.hasNextLine()) {
					this.conexao.recebeMensagem(entrada.nextLine() );
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
					
}

