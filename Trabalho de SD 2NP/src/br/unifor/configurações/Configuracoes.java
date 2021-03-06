package br.unifor.configurações;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Configuracoes {
	private String porta;
	private String ip;
	private String cpu, memoria, bloqueio;

	public Configuracoes(String porta, String cpu, String memoria, String bloqueio) {
		this.porta = porta;
		this.cpu = cpu;
		this.memoria = memoria;
		this.bloqueio= bloqueio;
		descobrirIpv4daRede();
	}

	public Configuracoes(String memoria, String cpu, String bloqueio) {

		this.cpu = cpu;
		this.memoria = memoria;
		this.bloqueio = bloqueio;
		this.porta = porta;

	}

	public void descobrirIpv4daRede() {
		InetAddress iAddress;
		try {

			iAddress = InetAddress.getLocalHost();

			this.setIp(iAddress.getHostAddress());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(String bloqueio) {
		this.bloqueio = bloqueio;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}

	

}
