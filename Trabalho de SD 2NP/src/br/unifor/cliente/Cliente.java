package br.unifor.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import br.unifor.Messagens.Conexao;
import br.unifor.controle.Controle;

public class Cliente {
	private final String SEPARADOR="";
	
	private static Controle controle;

	public Cliente(Controle controle) {
		this.controle= controle;
	}


	public void enviaMessagem(String mgs) {

			System.out.println("O cliente conectou ao servidor, envia messagem: " +mgs);

			for(Conexao conexao: this.controle.getConexoes()) {
				
				conexao.enviaMensagem(mgs);

			}
	}

}
