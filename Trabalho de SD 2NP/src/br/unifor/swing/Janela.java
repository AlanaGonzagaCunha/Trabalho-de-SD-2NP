package br.unifor.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.unifor.configurações.Configuracoes;
import br.unifor.controle.Controle;

public class Janela extends JFrame {
	
	public static int cont=0;
	public static JFrame janela;
	private JButton btnEnviar;
	private JPanel painelLog;
	public static JTextArea txtArea;
	private JScrollPane scrollTxt;
	private JScrollPane scrollTabela;
	private JTextArea exibeMgs;
	private JLabel sistema;
	private JLabel sistemaCPU;
	private JLabel sistemaMemoria;
	private JLabel sistemaBloqueio;
	public static JTextField sistExibeCPU;
	public static JTextField sistExibeMemoria;
	public static JTextField sistExibeBloqueio;
	private JLabel local;
	private JLabel localCPU;
	private JLabel localMemoria;
	private JLabel localBloqueio;
	private JTextField localExibeCPU;
	private JTextField localExibeMemoria;
	private JTextField localExibeBloqueio;
	public static JTable tabela;
	private static Controle controle;
	private String SEPARADOR = "";
	String colunas[] = { "IP", "PORTA" };
	public static DefaultTableModel modelo;
	static Configuracoes c;

	String cpuLocal, memoriaLocal, bloqueioLocal;
	int somaCpu, somaMemoria, somaBloqueio;

	public static void main(String[] args) {
		controle = new Controle();
		janela = new Janela();
		c = new Configuracoes("0", "0", "0");
	}

	public Janela() {
		montaJanela();
	}

	public void montaJanela() {
		janela = new JFrame();
		btnEnviar = new JButton("Enviar");
		painelLog = new JPanel();
		local = new JLabel("Local: ");
		localCPU = new JLabel("CPU: ");
		localMemoria = new JLabel("Memória: ");
		localBloqueio = new JLabel("Bloqueio: ");
		localExibeCPU = new JTextField();
		localExibeMemoria = new JTextField();
		localExibeBloqueio = new JTextField();
		sistema = new JLabel("Sistema: ");
		sistemaCPU = new JLabel("CPU: ");
		sistemaMemoria = new JLabel("Memória: ");
		sistemaBloqueio = new JLabel("Bloqueio: ");
		sistExibeCPU = new JTextField();
		sistExibeMemoria = new JTextField();
		sistExibeBloqueio = new JTextField();
		txtArea = new JTextArea();
		scrollTxt = new JScrollPane(txtArea);
		modelo = new DefaultTableModel(colunas, 3);
		tabela = new JTable(modelo);

		setTitle("Trabalho de SD");
		setVisible(true);
		setSize(600, 470);
		setLocation(200, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);

		local.setLocation(25, 250);
		local.setSize(150, 150);
		localCPU.setLocation(25, 280);
		localCPU.setSize(150, 150);
		localMemoria.setLocation(25, 310);
		localMemoria.setSize(150, 150);
		localBloqueio.setLocation(25, 340);
		localBloqueio.setSize(150, 150);
		localExibeCPU.setLocation(100, 345);
		localExibeCPU.setSize(50, 20);
		localExibeMemoria.setLocation(100, 375);
		localExibeMemoria.setSize(50, 20);
		localExibeBloqueio.setLocation(100, 405);
		localExibeBloqueio.setSize(50, 20);

		sistema.setLocation(250, 250);
		sistema.setSize(150, 150);
		sistemaCPU.setLocation(250, 280);
		sistemaCPU.setSize(150, 150);
		sistemaMemoria.setLocation(250, 310);
		sistemaMemoria.setSize(150, 150);
		sistemaBloqueio.setLocation(250, 340);
		sistemaBloqueio.setSize(150, 150);
		sistExibeCPU.setSize(50, 20);
		sistExibeCPU.setLocation(320, 345);
		sistExibeMemoria.setLocation(320, 375);
		sistExibeMemoria.setSize(50, 20);
		sistExibeBloqueio.setLocation(320, 405);
		sistExibeBloqueio.setSize(50, 20);
		sistExibeCPU.setEnabled(false);
		sistExibeMemoria.setEnabled(false);
		sistExibeBloqueio.setEnabled(false);

		btnEnviar.setSize(100, 110);
		btnEnviar.setLocation(450, 325);

		tabela.setLocation(330, 100);
		tabela.setSize(250, 80);

		modelo.insertRow(cont, new String[] { "IP:", "Porta:" });
	
		cont++;
		
		modelo.insertRow(cont, new String[] { controle.getConfiguracoesControle().getIp(),
				controle.getConfiguracoesControle().getPorta() });
		cont++;
		
		scrollTxt.setBounds(10, 30, 300, 250);
		scrollTxt.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTxt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		repaint();

		add(local);
		add(localCPU);
		add(localMemoria);
		add(localBloqueio);
		add(localExibeCPU);
		add(localExibeMemoria);
		add(localExibeBloqueio);
		add(btnEnviar);
		add(sistema);
		add(sistemaCPU);
		add(sistemaMemoria);
		add(sistemaBloqueio);
		add(sistExibeCPU);
		add(sistExibeMemoria);
		add(sistExibeBloqueio);
		add(tabela);

		getContentPane().add(scrollTxt);
		repaint();
		tratarEventos();
	}

	public boolean isCellEditable(int linha, int col) {
		return false;
	}

	public void tratarEventos() {

		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (!localExibeCPU.getText().isEmpty() && !localExibeCPU.isFocusOwner()
						&& !localExibeMemoria.getText().isEmpty() && !localExibeMemoria.isFocusOwner()
						&& !localExibeBloqueio.getText().isEmpty() && !localExibeBloqueio.isFocusOwner()) {

					cpuLocal = localExibeCPU.getText();
					memoriaLocal = localExibeMemoria.getText();
					bloqueioLocal = localExibeBloqueio.getText();

					SEPARADOR = cpuLocal + "|" + memoriaLocal + "|" + bloqueioLocal + ">";

					System.out.println("Separador: " + SEPARADOR);
					txtArea.append("\n Separador: " + SEPARADOR + "\n");

					if (controle.verficaConexao()) {
						System.out.println("VÁRIAS conexões");

						controle.enviaMensagemControle(SEPARADOR);

					} else {
						
						System.out.println("ÚNICA conexão!");

						somaCpu+=Integer.parseInt(cpuLocal);
						somaMemoria+=Integer.parseInt(memoriaLocal);
						somaBloqueio+=Integer.parseInt(bloqueioLocal);
							
						controle.getConfiguracoesControle().setCpu(""+somaCpu);
						controle.getConfiguracoesControle().setMemoria(""+somaMemoria	);
						controle.getConfiguracoesControle().setBloqueio(""+somaBloqueio);

						txtArea.append("\n Cpu Local: " + cpuLocal + "\n");
						txtArea.append("\n Memória Local: " + memoriaLocal + "\n");
						txtArea.append("\n Bloqueio Local: " + bloqueioLocal + "\n");

						sistExibeCPU.setText(""+somaCpu);
						sistExibeMemoria.setText(""+somaMemoria);
						sistExibeBloqueio.setText(""+somaBloqueio);
						repaint();

						}
					}

					localExibeCPU.setText("");
					localExibeMemoria.setText("");
					localExibeBloqueio.setText("");
					repaint();

				
			}

		});

	}

}
