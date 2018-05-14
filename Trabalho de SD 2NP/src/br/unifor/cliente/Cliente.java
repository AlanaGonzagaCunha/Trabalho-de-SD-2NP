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
	
	public void somatorio(String a) {
		
		System.out.println("Usa o separador para pegar as variáveis... ");
		String[] vetorSeparador = a.split("|");

		String cpu = vetorSeparador[0];
		String memoria = vetorSeparador[2];
		String bloqueio = vetorSeparador[4];

		System.out.println("CPU: " + cpu);
		System.out.println("MEMORIA: " + memoria);
		System.out.println("BLOQUEIO:" + bloqueio);

		int cpuConexao = 0, memoriaConexao = 0, bloqueioConexao = 0;

		for (Conexao c : this.controle.getConexoes()) {
			cpuConexao = Integer.parseInt(c.getConfiguracoes().getCpu());
			memoriaConexao = Integer.parseInt(c.getConfiguracoes().getMemoria());
			bloqueioConexao = Integer.parseInt(c.getConfiguracoes().getBloqueio());

			cpuConexao += Integer.parseInt(cpu);
			memoriaConexao += Integer.parseInt(memoria);
			bloqueioConexao += Integer.parseInt(bloqueio);

		}
		controle.getConfiguracoesControle().setCpu(""+cpuConexao);
		controle.getConfiguracoesControle().setMemoria(""+memoriaConexao);
		controle.getConfiguracoesControle().setBloqueio(""+bloqueioConexao);
		
		
	}

	public void enviaMessagem(String mgs) {

			System.out.println("O cliente conectou ao servidor, envia separador para todos conectados: " +mgs);

			for(Conexao conexao: this.controle.getConexoes()) {
				
				conexao.enviaMensagem(mgs);

			}
	}

	

	public void enviaConfiguracoesCliente(String configuracoesMaquina) {

		
	}

}
