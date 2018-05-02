package br.unifor.configurações;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Configuracoes {
	private  String porta;
	private  String ip;

	public Configuracoes(String porta) {
		this.porta = porta;
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

}
