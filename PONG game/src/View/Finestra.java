package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkIJTheme;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Il metodo finestra rappresenta la schermata iniziale del gioco
 * @author Claudio,Adis
 * @version 1.0
 */

public class Finestra extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Titolo del gioco
	 */
	private JLabel lblPong;
	/**
	 * bottone per creare un server
	 */
	private JButton btnCrea;
	/**
	 * Bottone per unirsi a un server
	 */
	private JButton btnUnisciti;
	/**
	 * Bottone per unirsi a una partita
	 */
	private Gioco g=null;
	/**
	 * Parte grafica del gioco del client
	 */
	private GiocoClient gC=null;
	/**
	 * Parte grafica del gioco del server
	 */
	private ServerWait sW=null;
	/**
	 * Schermata di attesa del client
	 */
	private ClientWait cW=null;
	/**
	 * Font personalizzato
	 */
	private Font font;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	/**
	 * Metodo costruttore
	 */
	public Finestra() {

		try {
			UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("bin\\PixelMplus10-Regular.ttf")).deriveFont(60f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BufferedImage icon;
		try {
			icon = ImageIO.read(getClass().getResource("/immagini/pong-logo.jpg"));
			this.setIconImage(icon);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		this.setFocusable(true);
		contentPane.setLayout(new GridLayout(5, 1, 0, 0));
		
		panel_3 = new JPanel();
		contentPane.add(panel_3);
		
		panel = new JPanel();
		contentPane.add(panel);
		lblPong = new JLabel("PONG");
		panel.add(lblPong);
		lblPong.setFont(font);
		lblPong.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1);
		
		btnCrea = new JButton("Crea");
		panel_1.add(btnCrea);
		btnCrea.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		
		panel_2 = new JPanel();
		contentPane.add(panel_2);
		
		btnUnisciti = new JButton("Unisciti");
		panel_2.add(btnUnisciti);
		btnUnisciti.setFont(new Font("OCR A Extended", Font.PLAIN, 40));
		
		panel_4 = new JPanel();
		contentPane.add(panel_4);
	}

	/**
	 * metodo per impostare come pannello la schermata iniziale
	 */

	public void changePanel() {
		this.remove(contentPane);
		this.invalidate();
		this.setContentPane(this.contentPane);
		this.setResizable(true);
		this.revalidate();
		gC=null;
		sW=null;
		cW=null;
	}
	
	/**
	 * metodo per impostare come pannello la schermata del gioco del server
	 */
	
	public void changePanel(Gioco g) {
		this.g=g;
		this.remove(contentPane);
		this.invalidate();
		this.setContentPane(this.g);
		this.setResizable(false);
		this.revalidate();
		gC=null;
		sW=null;
		cW=null;
	}
	
	/**
	 * metodo per impostare come pannello la schermata di attesa del client
	 */
	
	public void changePanel(ClientWait cW) {
		this.cW=cW;
		this.remove(contentPane);
		this.invalidate();
		this.setContentPane(this.cW);
		this.setResizable(true);
		this.revalidate();
		sW=null;
		g=null;
		gC=null;
	}
	
	/**
	 * metodo per impostare come pannello la schermata di attesa del server
	 */
	
	public void changePanel(ServerWait sW) {
		this.sW=sW;
		this.remove(contentPane);
		this.invalidate();
		this.setContentPane(this.sW);
		this.setResizable(true);
		this.revalidate();
		gC=null;
		g=null;
		cW=null;
	}
	
	/**
	 * metodo per impostare come pannello la schermata del gioco del client
	 */
	
	public void changePanel(GiocoClient gC) {
		this.gC=gC;
		this.remove(contentPane);
		this.invalidate();
		this.setContentPane(this.gC);
		this.setResizable(false);
		this.revalidate();
		g=null;
		sW=null;
		cW=null;
	}
	
	 ///////////////////////////
	 // - Getters & Setters - //
	///////////////////////////
	
	public JButton getBtnCrea() {
		return btnCrea;
	}

	public void setBtnCrea(JButton btnCrea) {
		this.btnCrea = btnCrea;
	}

	public JButton getBtnUnisciti() {
		return btnUnisciti;
	}

	public void setBtnUnisciti(JButton btnUnisciti) {
		this.btnUnisciti = btnUnisciti;
	}

	public Gioco getG() {
		return g;
	}

	public GiocoClient getgC() {
		return gC;
	}

	public ServerWait getsW() {
		return sW;
	}

	public ClientWait getcW() {
		return cW;
	}
	
	
}
