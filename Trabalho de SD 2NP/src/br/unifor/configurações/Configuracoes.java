package br.unifor.configurações;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Configuracoes {
	private String porta;
	private String ip;
	private String cpu, memoria;
	private boolean ativa;

	public Configuracoes(String porta, String cpu, String memoria) {
		this.porta = porta;
		this.memoria= memoria;
		this.cpu= cpu;
		descobrirIpv4daRede();
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

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

}
