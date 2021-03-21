package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import View.ClientWait;
import View.Finestra;
/**
 * La classe Client gestisce le iterazione con la schermata di attesa del client
 * @author Claudio
 *@version 1.0
 */
public class ControllerClient implements ActionListener{
	/**
	 * schermata del gioco
	 */
	public Finestra f;
	/**
	 * Metodo costruttore
	 * @param f
	 */
	public ControllerClient(Finestra f) {
		super();
		this.f = f;
		this.f.changePanel(new ClientWait());
		this.f.getcW().getBtnConnetti().addActionListener(this);
		this.f.getcW().getBtnIndietro().addActionListener(this);
		this.f.getcW().getRdbtnAuto().addActionListener(this);
		this.f.getcW().getRdbtnManual().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == f.getcW().getBtnConnetti()) {

			if(!f.getcW().getTextFieldNickName().getText().isBlank()) {//controllo che il campo nome non sia vuoto o ci siano solo spazi

				if(!f.getcW().getTextFieldIPServer().getText().isBlank() && checkIPAddress(f.getcW().getTextFieldIPServer().getText())) {//controllo indirizzo ip
					if(!f.getcW().getRdbtnManual().isSelected() ) {
						
						new Thread(new Client(f,f.getcW().getTextFieldNickName().getText(),f.getcW().getTextFieldIPServer().getText())).start();
					}
					else {
						if(!f.getcW().getTextFieldPortaServer().getText().isBlank() && checkPort(f.getcW().getTextFieldPortaServer().getText())) {
							int port=0;
							try {
								port=Integer.parseInt(f.getcW().getTextFieldPortaServer().getText());//conversione porta server da string a int
							}
							catch (Exception e1) {
								// TODO: handle exception
								JOptionPane.showMessageDialog(null, "formato porta non corretto, impostata porta di default 9999","errore porta",JOptionPane.ERROR_MESSAGE);
								port=9999;
							
							}
							
							new Thread(new Client(f,f.getcW().getTextFieldNickName().getText(),f.getcW().getTextFieldIPServer().getText(),port)).start();
							
						}
						else {
							int port=9999;
							JOptionPane.showMessageDialog(null, "nessuna porta inserita, impostata porta di default 9999","errore porta",JOptionPane.ERROR_MESSAGE);
							
							new Thread(new Client(f,f.getcW().getTextFieldNickName().getText(),f.getcW().getTextFieldIPServer().getText(),port)).start();
						}
					}
					//disabilitati bottoni e aree di testo
					f.getcW().getBtnConnetti().setEnabled(false);
					f.getcW().getRdbtnManual().setEnabled(false);
					f.getcW().getRdbtnAuto().setEnabled(false);
					f.getcW().getTextFieldNickName().setEnabled(false);
					f.getcW().getTextFieldPortaServer().setEnabled(false);
					f.getcW().getBtnIndietro().setEnabled(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "nessun server inserito o indirizzo ip non corretto","inserire server",JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "nessun nome inserito","inserire nome",JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == f.getcW().getBtnIndietro()) {
			f.changePanel();
		}

		else if(e .getSource() == f.getcW().getRdbtnManual()) {
			f.getcW().getTextFieldPortaServer().setVisible(true);
			f.getcW().getLblNewLabel_2().setVisible(true);
			f.getcW().revalidate();
		}
		else if(e .getSource() == f.getcW().getRdbtnAuto()) {
			f.getcW().getTextFieldPortaServer().setVisible(false);
			f.getcW().getLblNewLabel_2().setVisible(false);
			f.getcW().revalidate();
		}
	}
	/**
	 * Metodo per controllare se la porta è scritta correttamente
	 * @param str
	 * @return
	 */
	private boolean checkPort(String str) {  
		  Pattern pPattern = Pattern.compile("\\d{1,5}");  //controllo che il campo porta abbia da 1 a 5 caratteri
		  return pPattern.matcher(str).matches();  
	}  
	
	/**
	 * Metodo per controllare indirizzo ip
	 * @param str
	 * @return
	 */
	private boolean checkIPAddress(String str) { 
		if(str.compareTo("localhost")==0) {
			return true;
		}
		else {
		  Pattern ipPattern = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");  //controllo formato indirizzo ip
		  return ipPattern.matcher(str).matches();  
		}
	}
	
}
