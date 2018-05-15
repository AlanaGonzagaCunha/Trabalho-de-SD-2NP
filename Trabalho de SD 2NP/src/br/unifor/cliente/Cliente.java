package br.unifor.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Pattern;

import br.unifor.Messagens.Conexao;
import br.unifor.configurações.Configuracoes;
import br.unifor.controle.Controle;

public class Cliente {
	private final String SEPARADOR = "";

	private static Controle controle;
	private static Configuracoes configuracoes;

	public Cliente(Controle controle, Configuracoes configuracoes) {
		this.controle = controle;
		this.configuracoes= configuracoes;
	}

	public void somatorio(String a) {

		System.out.println("Usa o separador para pegar as variáveis... ");
		String[] vetorSeparador = a.split("\\|");

		String cpuRecebida = vetorSeparador[0];
		String memoriaRecebida = vetorSeparador[1];
		String b = vetorSeparador[2];

		String v[] = b.split("\\>");
		String bloqueioRecebida = v[0];

		int somaCpu = 0, somaMemoria = 0, somaBloqueio = 0;
	
		
		somaCpu = Integer.parseInt(configuracoes.getCpu())
				+ Integer.parseInt(controle.getConfiguracoesControle().getCpu());
		somaMemoria = Integer.parseInt(configuracoes.getMemoria())
				+ Integer.parseInt(controle.getConfiguracoesControle().getMemoria());
		somaBloqueio = Integer.parseInt(configuracoes.getBloqueio())
				+ Integer.parseInt(controle.getConfiguracoesControle().getBloqueio());

		System.out.println("\n Valores somados... ");
		System.out.println("\n CPU SISTEMA: " + somaCpu);
		System.out.println("\n MEMORIA: SISTEMA: " + somaMemoria);
		System.out.println("\n BLOQUEIO SISTEMA: " + somaBloqueio);

		controle.getConfiguracoesControle().setCpu("" + somaCpu);
		controle.getConfiguracoesControle().setMemoria("" + somaMemoria);
		controle.getConfiguracoesControle().setBloqueio("" + somaBloqueio);
	}

	public void enviaMessagem(String mgs) {

		System.out.println("O cliente conectou ao servidor, envia separador para todos conectados: " + mgs);

		for (Conexao conexao : this.controle.getConexoes()) {
			
				conexao.enviaMensagemConexao(mgs);
			
		

		}
	}

}