package br.unifor.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextField;

import br.unifor.Messagens.Conexao;
import br.unifor.configurações.Configuracoes;
import br.unifor.controle.Controle;
import br.unifor.swing.Janela;

public class Cliente {

	private static Controle controle;
	private static Configuracoes configuracoes;

	public Cliente(Controle controle, Configuracoes configuracoes) {
		this.controle = controle;
		this.configuracoes= configuracoes;
	}



	public void enviaMessagem(String mgs) {

		System.out.println("O cliente conectou ao servidor, envia separador para todos conectados: " + mgs );
		Janela.txtArea.append("O cliente conectou ao servidor, envia separador para todos conectados: " + mgs +"\n");
		
		for (Conexao conexao : this.controle.getConexoes()) {
			
				conexao.enviaMensagemConexao(mgs);
			
		

		}
	}

}