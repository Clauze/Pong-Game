package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import View.Finestra;
import View.ServerWait;
/**
 * La classe ControllerServerWait gestisce la shcermata di attesa del server
 * @author Claudio
 *@version 1.0
 */
public class ControllerServerWait implements ActionListener{
	/**
	 * schermata del gico
	 */
	public Finestra f;
	/**
	 * costruttore del controller della schermata di attesa per il server
	 * @param f
	 */
	public ControllerServerWait(Finestra f) {
		super();
		this.f = f;
		this.f.changePanel(new ServerWait());
		this.f.getsW().getIp().setText(getMyIp());
		this.f.getsW().getBtnConnetti().addActionListener(this);
		this.f.getsW().getBtnIndietro().addActionListener(this);
		this.f.getsW().getRdbtnAuto().addActionListener(this);
		this.f.getsW().getRdbtnManual().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == f.getsW().getBtnConnetti()) {

			if(!f.getsW().getTextFieldNickName().getText().isBlank()) {//controllo che il campo nome non sia vuoto
				
				if(!f.getsW().getRdbtnManual().isSelected()) {
					if(checkPort(9999)) {//controllo se la porta è già usata
						JOptionPane.showMessageDialog(null, " porta già usata","errore porta",JOptionPane.ERROR_MESSAGE);
					}
					else {
						
						new Thread(new Server(f,f.getsW().getTextFieldNickName().getText())).start();
						f.getsW().getBtnConnetti().setEnabled(false);
						f.getsW().getRdbtnManual().setEnabled(false);
						f.getsW().getRdbtnAuto().setEnabled(false);
						f.getsW().getTextFieldNickName().setEnabled(false);
						f.getsW().getTextFieldPorta().setEnabled(false);
						f.getsW().getBtnIndietro().setEnabled(false);
					}
				}
				else {
					if(!f.getsW().getTextFieldPorta().getText().isBlank()) {//controlla che il campo non sia vuoto 
						int port;
						try {
							port=Integer.parseInt(f.getsW().getTextFieldPorta().getText());//conversione numero porta da string a int
							if(checkPort(port)) {
								JOptionPane.showMessageDialog(null, " porta inserita già usata","errore porta",JOptionPane.ERROR_MESSAGE);
							}
							else {
								
								new Thread(new Server(f,f.getsW().getTextFieldNickName().getText(),port)).start();
								//disabilitati bottoni e aree di testo
								f.getsW().getBtnConnetti().setEnabled(false);
								f.getsW().getRdbtnManual().setEnabled(false);
								f.getsW().getRdbtnAuto().setEnabled(false);
								f.getsW().getTextFieldNickName().setEnabled(false);
								f.getsW().getTextFieldPorta().setEnabled(false);
								f.getsW().getBtnIndietro().setEnabled(false);
							}
						}
						catch (Exception e1) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, "formato porta non corretto, impostata porta di default 9999","errore porta",JOptionPane.ERROR_MESSAGE);
							port=9999;
							
							new Thread(new Server(f,f.getsW().getTextFieldNickName().getText(),port)).start();
							//disabilitati bottoni e aree di testo
							f.getsW().getBtnConnetti().setEnabled(false);
							f.getsW().getRdbtnManual().setEnabled(false);
							f.getsW().getRdbtnAuto().setEnabled(false);
							f.getsW().getTextFieldNickName().setEnabled(false);
							f.getsW().getTextFieldPorta().setEnabled(false);
							f.getsW().getBtnIndietro().setEnabled(false);
						}
						
					}
					else {
						int port=9999;
						JOptionPane.showMessageDialog(null, "nessuna porta inserita, impostata porta di default 9999","errore porta",JOptionPane.ERROR_MESSAGE);
						
						
						new Thread(new Server(f,f.getsW().getTextFieldNickName().getText(),port)).start();
						//disabilitati bottoni e aree di testo

						f.getsW().getBtnConnetti().setEnabled(false);
						f.getsW().getRdbtnManual().setEnabled(false);
						f.getsW().getRdbtnAuto().setEnabled(false);
						f.getsW().getTextFieldNickName().setEnabled(false);
						f.getsW().getTextFieldPorta().setEnabled(false);
						f.getsW().getBtnIndietro().setEnabled(false);
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "nessun nome inserito","inserire nome",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource() == f.getsW().getBtnIndietro()) {
			f.changePanel();
		}
		
		else if(e .getSource() == f.getsW().getRdbtnManual()) {
			f.getsW().getTextFieldPorta().setVisible(true);
			f.getsW().getLblNewLabel_2().setVisible(true);
			f.getsW().revalidate();
		}
		else if(e .getSource() == f.getsW().getRdbtnAuto()) {
			f.getsW().getTextFieldPorta().setVisible(false);
			f.getsW().getLblNewLabel_2().setVisible(false);
			f.getsW().revalidate();
		}
	}
	/**
	 * Metodo per ottenere l'indirizzo ip della macchina
	 * @return
	 */
	private String getMyIp() {
		String s=null;

		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress("google.com", 80));//ottengo indirizzo ip 
			s= socket.getLocalAddress().toString();
			s=s.substring(1);
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				s=InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return s;

	}
	/**
	 * Metodo per controllare se la porta inserita non è già occupata 
	 * @param port
	 * @return
	 */
	private boolean checkPort(int port) {
		 boolean result = true;

				try {
					new ServerSocket(port).close();//controllo che la porta inserita non sia già stata utilizzata
				    result = false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			

		  return result; 
	}
}
