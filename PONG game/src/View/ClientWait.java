package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
/**
 * La classe ClientWait è la schermata di attesa del client per connettersi al server
 * @author Claudio,Adis
 *@version 1.0
 */
public class ClientWait extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	/**
	 * Nome del giocatore
	 */
	private JTextField textFieldNickName;
	private JLabel lblNewLabel_1;
	private JPanel panel_6;
	private JPanel panel_7;
	/**
	 * RadioButton per impostare manualmente la porta del server
	 */
	private JRadioButton rdbtnManual;
	/**
	 * RadioButton per impostare automaticament la porta del server
	 */
	private JRadioButton rdbtnAuto;
	/**
	 * Area di testo per inserire indirizzo del server
	 */
	private JTextField textFieldIPServer;
	private JPanel panel_8;
	private JLabel lblNewLabel_2;
	/**
	 * Area di testo dove inserire porta server
	 */
	private JTextField textFieldPortaServer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Bottone per connettersi al server
	 */
	private JButton btnConnetti;
	private JLabel lblNewLabel_3;
	/**
	 * Bottone per tornare indietro
	 */
	private JButton btnIndietro;
	/**
	 * Font personalizzato
	 */
	private Font font;
	/**
	 * Create the panel.
	 */
	public ClientWait() {
		setLayout(new BorderLayout(0, 0));
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("bin\\PixelMplus10-Regular.ttf")).deriveFont(30f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		btnConnetti = new JButton("Connetti");
		btnConnetti.setFont(font);
		panel.add(btnConnetti);
		
		btnIndietro = new JButton("Indietro");
		btnIndietro.setFont(font);
		panel.add(btnIndietro);
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.EAST);
		
		panel_3 = new JPanel();
		add(panel_3, BorderLayout.CENTER);
		panel_3.setAlignmentX(CENTER_ALIGNMENT);
		panel_3.setAlignmentY(CENTER_ALIGNMENT);
		panel_3.setLayout(new GridLayout(5, 1, 0, 0));
		
		panel_6 = new JPanel();
		panel_3.add(panel_6);
		
		lblNewLabel_3 = new JLabel("Mi connetto al server ...");
		lblNewLabel_3.setFont(font);
		lblNewLabel_3.setVisible(false);
		panel_6.add(lblNewLabel_3);
		
		panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("Inserire nickname");
		lblNewLabel.setFont(font);
		panel_4.add(lblNewLabel);
		
		textFieldNickName = new JTextField();
		textFieldNickName.setFont(font);
		textFieldNickName.addKeyListener(new KeyAdapter() {
	    	public void keyTyped(KeyEvent e) {
	    		if (textFieldNickName.getText().length() >= 10 )
	    			e.consume();
	   		}
	    });
		panel_4.add(textFieldNickName);
		textFieldNickName.setColumns(10);
		
		panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel_1 = new JLabel("Inserire ip server");
		lblNewLabel_1.setFont(font);
		panel_5.add(lblNewLabel_1);
		
		textFieldIPServer = new JTextField();
		textFieldIPServer.setFont(font);
		panel_5.add(textFieldIPServer);
		textFieldIPServer.setColumns(10);
		
		panel_8 = new JPanel();
		panel_3.add(panel_8);
		
		lblNewLabel_2 = new JLabel("Inserire porta server");
		lblNewLabel_2.setVisible(false);
		lblNewLabel_2.setFont(font);
		panel_8.add(lblNewLabel_2);
		
		textFieldPortaServer = new JTextField();
		textFieldPortaServer.setVisible(false);
		textFieldPortaServer.addKeyListener(new KeyAdapter() {
	    	public void keyTyped(KeyEvent e) {
	    		if (textFieldPortaServer.getText().length() >= 5 )
	    			e.consume();
	   		}
	    });
		textFieldPortaServer.setFont(font);
		textFieldPortaServer.setColumns(10);
		panel_8.add(textFieldPortaServer);
		
		panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		rdbtnAuto = new JRadioButton("automatico");
		rdbtnAuto.setFont(font);
		buttonGroup.add(rdbtnAuto);
		panel_7.add(rdbtnAuto);
		
		rdbtnManual = new JRadioButton("manuale");
		rdbtnManual.setFont(font);
		buttonGroup.add(rdbtnManual);
		panel_7.add(rdbtnManual);

	}
	
	 ///////////////////////////
	 // - Getters & Setters - //
	///////////////////////////
	
	public JLabel getLblNewLabel_3() {
		return lblNewLabel_3;
	}
	
	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}
	
	public JTextField getTextFieldNickName() {
		return textFieldNickName;
	}
	
	public JRadioButton getRdbtnManual() {
		return rdbtnManual;
	}
	
	public JRadioButton getRdbtnAuto() {
		return rdbtnAuto;
	}
	
	public JTextField getTextFieldIPServer() {
		return textFieldIPServer;
	}
	
	public JTextField getTextFieldPortaServer() {
		return textFieldPortaServer;
	}
	
	public JButton getBtnConnetti() {
		return btnConnetti;
	}

	public JButton getBtnIndietro() {
		return btnIndietro;
	}	
	
	
}
