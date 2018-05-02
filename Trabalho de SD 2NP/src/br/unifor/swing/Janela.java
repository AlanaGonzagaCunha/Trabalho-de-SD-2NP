package br.unifor.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.unifor.controle.Controle;

public class Janela extends JFrame {

	private JFrame janela;
	private JButton btnEnviar;
	private JPanel painelChat;
	private JTextArea txtArea;
	private JScrollPane scrollTxt;
	private JTextArea exibeMgs;
	private static Controle controle;

	public static void main(String[] args) {
		controle = new Controle();
		Janela janela = new Janela();
	}

	public Janela() {
		montaJanela();
	}

	public void montaJanela() {
		janela = new JFrame();
		btnEnviar = new JButton("Enviar");
		painelChat = new JPanel();
		txtArea = new JTextArea();
		exibeMgs = new JTextArea();

		scrollTxt = new JScrollPane(txtArea);

		setTitle("Trabalho de SD");
		setVisible(true);
		setSize(600, 500);
		setLocation(200, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		btnEnviar.setSize(100, 110);
		btnEnviar.setLocation(480, 345);

		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setSize(460, 110);
		txtArea.setLocation(10, 345);

		exibeMgs.setSize(300, 330);
		exibeMgs.setLocation(10, 10);
		exibeMgs.setEditable(false);

		scrollTxt.setBounds(10, 30, 560, 290);
		scrollTxt.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTxt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollTxt.setViewportView(exibeMgs);
		repaint();

		add(btnEnviar);
		add(txtArea);
		getContentPane().add(scrollTxt);

		repaint();
		tratarEventos();

	}

	private void tratarEventos() {
		txtArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {

					if (!txtArea.getText().isEmpty()) {
						String mensagem = txtArea.getText();
						System.out.println("Enviando mensagem: " + mensagem);
						controle.enviaMessagem(mensagem);
						exibeMgs.append(mensagem + "\n");
						txtArea.setText("");

					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (!txtArea.getText().isEmpty() && !txtArea.isFocusOwner()) {
					String mensagem = txtArea.getText();
					System.out.println("Enviando mensagem: " + mensagem);
					controle.enviaMessagem(mensagem + " ");
					exibeMgs.append(mensagem);
					txtArea.setText("");

				}
			}
		});
	}
}
