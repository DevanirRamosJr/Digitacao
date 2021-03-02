package app;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel {
	private JTextField Tinput;
	private int len;
	private int erro;
	private int score;
	private int init;

	/**
	 * Create the panel.
	 */
	public Game() {
		setLayout(null);
		Color VERY_LIGHT_RED = new Color(255,102,102);
		init = 0;
		
		JLabel Lqtd = new JLabel("Score:");
		Lqtd.setFont(new Font("Times New Roman", Font.BOLD, 26));
		Lqtd.setBounds(0, 11, 71, 33);
		add(Lqtd);
		
		JLabel Lquant = new JLabel("Quant:");
		Lquant.setFont(new Font("Times New Roman", Font.BOLD, 26));
		Lquant.setBounds(340, 11, 86, 33);
		add(Lquant);
		
		JLabel Lq = new JLabel("00");
		Lq.setHorizontalAlignment(SwingConstants.CENTER);
		Lq.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		Lq.setBounds(425, 12, 41, 33);
		add(Lq);
		
		JLabel Lscore = new JLabel("0");
		Lscore.setHorizontalAlignment(SwingConstants.CENTER);
		Lscore.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		Lscore.setBounds(75, 12, 71, 33);
		add(Lscore);
		
		JLabel Lword = new JLabel("Palavra");
		Lword.setHorizontalAlignment(SwingConstants.CENTER);
		Lword.setFont(new Font("Times New Roman", Font.BOLD, 34));
		Lword.setBounds(0, 102, 470, 48);
		add(Lword);
		Lword.setVisible(false);
		
		JLabel candy = new JLabel("<html>Palavra</html>");
		candy.setHorizontalAlignment(SwingConstants.CENTER);
		candy.setFont(new Font("Times New Roman", Font.BOLD, 34));
		candy.setBounds(0, 101, 470, 48);
		add(candy);
		
		ArrayList<String> coisas = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("br-utf8.txt"), "UTF-8"));
			String linha;
			try {
				linha = reader.readLine();
				while (linha != null) {
					coisas.add(linha);
					linha = reader.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(coisas.size());
		
		
		Tinput = new JTextField();
		Tinput.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				check();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				check();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				check();
			}
			
			private void check() {
				Runnable doCheck = new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						String palavra = Lword.getText().substring(0, Tinput.getText().length());
						String resto = Lword.getText().substring(Tinput.getText().length());
						if (Tinput.getText().equals(palavra)) {
							Lword.setVisible(false);
							candy.setText("<html><font color = green>" + palavra + "</font>" + resto + "</html>");
							System.out.println("if ---- " + palavra);
							if (Tinput.getText().length() == len) {
								//necessário para que o input fique vermelho,
								//sem essa checagem ele fica branco imediatamente
							}
							else {
								Tinput.setBackground(Color.WHITE);
								len = 500;
							}
							if (Tinput.getText().length() == Lword.getText().length()) {
								if (init == 0) {
									score -= 70;
								}
								init += 1;
								Lq.setText(String.valueOf(init - 1));
								score += Tinput.getText().length() * 10;
								Lscore.setText(String.valueOf(score));
								Random rand = new Random();
								String w = coisas.get(rand.nextInt(coisas.size()));
								System.out.println("palavra - " + w);
								if (Character.isUpperCase(w.charAt(0))) {
									System.out.println("caiu no upper");
									if (rand.nextInt(2) == 0) {
										Lword.setText(w);
										Tinput.setText("");
									}
									else {
										String low = w.substring(0,1).toLowerCase() + w.substring(1);
										Lword.setText(low);
										Tinput.setText("");
									}
								}
								else {
									System.out.println("caiu no lower");
									if (rand.nextInt(2) > 0) {
										String cap = w.substring(0,1).toUpperCase() + w.substring(1);
										Lword.setText(cap);
										Tinput.setText("");
									}
									else {
										Lword.setText(w);
										Tinput.setText("");
									}
								}

							}
						}
						else {
							Tinput.setBackground(VERY_LIGHT_RED);
							System.out.println("vermelho");
							if (init == 0) {
								score += 10;
							}
							score -= 10;
							Lscore.setText(String.valueOf(score));
							len = Tinput.getText().length() - 1;
							String palavra_input = Tinput.getText().substring(0, Tinput.getText().length() - 1);
							System.out.println(palavra_input);
							Tinput.setText(palavra_input);
						}
					}
					
				};
				SwingUtilities.invokeLater(doCheck);
			}
		});
		Tinput.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		Tinput.setBounds(105, 194, 268, 36);
		add(Tinput);
		Tinput.setColumns(10);

	}
	
	public void field_focus() {
		Tinput.requestFocus();
	}
}
