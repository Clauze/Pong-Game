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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * La classe ServerWait rappresenta la schermata di attesa del server 
 * @author Claudio,Adis
 * @version 1.0
 */

public class ServerWait extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel LabelIP;
	private JLabel Ip;
	/**
	 * Bottone per ritornare indietro
	 */
	private JButton btnIndietro;
	private JPanel panel_4;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	/**
	 * Area di testo dove inserire il proprio nome
	 */
	private JTextField textFieldNickName;
	private JLabel lblNewLabel_1;
	private JPanel panel_6;
	private JPanel panel_7;
	private JLabel lblNewLabel_2;
	/**
	 * Area di testo dove inserire la porta del server
	 */
	private JTextField textFieldPorta;
	private JPanel panel_8;
	/**
	 * RadioButton per impostare manualmente la porta del server
	 */
	private JRadioButton rdbtnManual;
	/**
	 * RadioButton per impostare automaticament la porta del server
	 */
	private JRadioButton rdbtnAuto;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Bottone per avviare il server
	 */
	private JButton btnConnetti;
	/**
	 * Font personalizzato
	 */
	private Font font;
	/**
	 * Create the panel.
	 */
	public ServerWait() {
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
		
		btnIndietro =  new JButton("Indietro");
		btnIndietro.setFont(font);
		panel.add(btnIndietro);
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		
		LabelIP = new JLabel("il tuo indirizzo ip: ");
		LabelIP.setFont(font);
		panel_1.add(LabelIP);
		
		Ip = new JLabel("");
		Ip.setFont(font);
		panel_1.add(Ip);
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.EAST);
		
		panel_3 = new JPanel();
		add(panel_3, BorderLayout.CENTER);
		panel_3.setAlignmentX(CENTER_ALIGNMENT);
		panel_3.setAlignmentY(CENTER_ALIGNMENT);
		panel_3.setLayout(new GridLayout(5, 1, 0, 0));
		
		panel_6 = new JPanel();
		panel_3.add(panel_6);
		
		panel_4 = new JPanel();
		panel_3.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel = new JLabel("Inserire nickname");
		lblNewLabel.setFont(font);
		panel_4.add(lblNewLabel);
		
		textFieldNickName = new JTextField();
		textFieldNickName.setFont(font);
		
		panel_4.add(textFieldNickName);
		textFieldNickName.setColumns(10);
		textFieldNickName.addKeyListener(new KeyAdapter() {
	    	public void keyTyped(KeyEvent e) {
	    		if (textFieldNickName.getText().length() >= 10 )
	    			e.consume();
	   		}
	    });
		panel_7 = new JPanel();
		panel_3.add(panel_7);
		
		lblNewLabel_2 = new JLabel("Inserire porta server");
		lblNewLabel_2.setVisible(false);;
		lblNewLabel_2.setFont(font);
		panel_7.add(lblNewLabel_2);
		
		textFieldPorta = new JTextField();
		textFieldPorta.setVisible(false);
		textFieldPorta.addKeyListener(new KeyAdapter() {
	    	public void keyTyped(KeyEvent e) {
	    		if (textFieldPorta.getText().length() >= 5 )
	    			e.consume();
	   		}
	    });
		textFieldPorta.setFont(font);
		textFieldPorta.setColumns(10);
		panel_7.add(textFieldPorta);
		
		panel_5 = new JPanel();
		panel_3.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		lblNewLabel_1 = new JLabel("Aspetto un altro giocatore....");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setFont(font);
		panel_5.add(lblNewLabel_1);
		
		panel_8 = new JPanel();
		panel_3.add(panel_8);
		
		rdbtnAuto = new JRadioButton("automatico");
		rdbtnAuto.setFont(font);
		buttonGroup.add(rdbtnAuto);
		panel_8.add(rdbtnAuto);
		
		rdbtnManual = new JRadioButton("manuale");
		rdbtnManual.setFont(font);
		buttonGroup.add(rdbtnManual);
		panel_8.add(rdbtnManual);

	}
	
	 ///////////////////////////
	 // - Getters & Setters - //
	///////////////////////////

	public JLabel getLblNewLabel_2() {
		return lblNewLabel_2;
	}
	
	public JLabel getLblNewLabel_1() {
		return lblNewLabel_1;
	}
	
	public JLabel getIp() {
		return Ip;
	}

	public JButton getBtnIndietro() {
		return btnIndietro;
	}

	public JTextField getTextFieldNickName() {
		return textFieldNickName;
	}

	public JTextField getTextFieldPorta() {
		return textFieldPorta;
	}

	public JRadioButton getRdbtnManual() {
		return rdbtnManual;
	}

	public JRadioButton getRdbtnAuto() {
		return rdbtnAuto;
	}

	public JButton getBtnConnetti() {
		return btnConnetti;
	}
	
	
}
