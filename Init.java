package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class Init extends JFrame {

	private JButton Bvoltar;
	
	private JPanel panel_main;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Init frame = new Init();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Init() {
		setTitle("Digitação");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 500, 400);
		panel_main = new JPanel();
		panel_main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel_main);
		panel_main.setLayout(new CardLayout(0, 0));
		CardLayout cl = (CardLayout)(panel_main.getLayout());
		
		JPanel panel_init = new JPanel();
		panel_main.add(panel_init, "0");
		panel_init.setLayout(null);
		
		JPanel panel_info = new JPanel();
		panel_main.add(panel_info, "1");
		panel_info.setLayout(null);
	
		Game panel_game = new Game();
		panel_main.add(panel_game, "2");
		
		
		JLabel lblNewLabel = new JLabel("Digita\u00E7\u00E3o");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 42));
		lblNewLabel.setBounds(0, 11, 484, 50);
		panel_init.add(lblNewLabel);
		
		JButton Binit = new JButton("Iniciar");
		Binit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panel_main, "2");
				panel_game.field_focus();
			}
		});
		Binit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
		        if (keyCode == KeyEvent.VK_ENTER) {
					cl.show(panel_main, "2");
					panel_game.field_focus();
		        }
	        }
		});
		Binit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				Binit.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Binit.setBackground(null);
			}
		});
		Binit.setFont(new Font("Times New Roman", Font.BOLD, 30));
		Binit.setBounds(160, 125, 162, 58);
		panel_init.add(Binit);
		
		JButton Binfo = new JButton("Instru\u00E7\u00F5es");
		Binfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panel_main, "1");
				panel_info.add(Bvoltar);
				Bvoltar.requestFocus();
			}
		});
		Binfo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
		        if (keyCode == KeyEvent.VK_ENTER) {
					cl.show(panel_main, "1");
					panel_info.add(Bvoltar);
					Bvoltar.requestFocus();
		        }
	        }
		});
		Binfo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				Binfo.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Binfo.setBackground(null);
			}
		});
		Binfo.setFont(new Font("Times New Roman", Font.BOLD, 28));
		Binfo.setBounds(160, 235, 162, 58);
		panel_init.add(Binfo);
		
		
		
		
		
		JLabel Linfo_title = new JLabel("Instru\u00E7\u00F5es");
		Linfo_title.setHorizontalAlignment(SwingConstants.CENTER);
		Linfo_title.setFont(new Font("Times New Roman", Font.BOLD, 42));
		Linfo_title.setBounds(0, 11, 484, 50);
		panel_info.add(Linfo_title);
		
		Bvoltar = new JButton("Voltar");
		Bvoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panel_main, "0");
				Bvoltar.setBounds(10, 10, 65, 23);
				Binit.requestFocus();
			}
		});
		Bvoltar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
		        if (keyCode == KeyEvent.VK_ENTER) {
					cl.show(panel_main, "0");
					Bvoltar.setBounds(10, 10, 65, 23);
					Binit.requestFocus();
		        }
	        }
		});
		Bvoltar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				Bvoltar.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void focusLost(FocusEvent e) {
				Bvoltar.setBackground(null);
			}
		});
		Bvoltar.setFont(new Font("Times New Roman", Font.BOLD, 10));
		Bvoltar.setBounds(10, 10, 65, 23);
		panel_info.add(Bvoltar);
		
		String info = ("Ao iniciar você terá que digitar \"Palavra\" de exemplo, quando ela for digitada a pontuação começa."
				+ "\n\nCompletando uma palavra você ganha pontos.\nTambém é mostrado a quantidade de palavras digitadas."
				+ "\n\nPode usar \"Tab\" e \"Enter\" para navegação.");
		JLabel Linfo = new JLabel(MultilineLabel(info));
		Linfo.setHorizontalAlignment(SwingConstants.LEFT);
		Linfo.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		Linfo.setBounds(20, 72, 440, 248);
		panel_info.add(Linfo);
		
	}
	
	public static String MultilineLabel(String orig)
	{
	    return "<html>" + orig.replaceAll("\n", "<br>") + "</html>";
	}
}
