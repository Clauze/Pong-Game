package Control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;
import Model.Ball;
import Model.Slider;
import View.ClientWait;
import View.Finestra;
import View.GiocoClient;
/**
 * La classe Client gestisce le comunicazioni con il server
 * @author Claudio
 *@version 1.0
 */
public class Client implements Runnable{
	
	/**
	 * socket con il server
	 */
	private Socket socket;
	/**
	 * Schermata del gioco
	 */
	private Finestra f;
	/**
	 * indirizzo ip del server
	 */
	private String ipServer;
	/**
	 * username del client
	 */
	private String userName;
	/**
	 * thread per l'aggiornamento della grafica 
	 */
	private GiocoClient gC=null;
	/**
	 * la variabile è uguale a 'true' fino a quando il server è raggiungibile
	 */
	private boolean flag=true;
	/**
	 * numero di porta per connettersi al server, è impostata di default la porta 9999
	 */
	private int porta;
	/**
	 * variabile per controllare lo stato del server al termine della partita
	 */
	private int serverPlay=0;
	/**
	 * flusso input socket
	 */
	private ObjectInputStream streamInput;
	/**
	 * flusso output socket
	 */
	private ObjectOutputStream streamOut;
	/**
	 * costruttore con porta di default
	 * @param f
	 * @param userName
	 * @param ipServer
	 */
	public Client(Finestra f, String userName, String ipServer) {
		this.f=f;
		this.ipServer=ipServer;
		this.userName=userName;
		porta=9999;
		serverPlay=0;
	}
	/**
	 * costruttore con porta personalizzata
	 * @param f
	 * @param userName
	 * @param ipServer
	 * @param porta
	 */
	public Client(Finestra f, String userName, String ipServer,int porta) {
		this.f=f;
		this.userName=userName;
		this.ipServer=ipServer;
		this.porta=porta;
		serverPlay=0;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		f.getcW().getLblNewLabel_3().setVisible(true);
		f.getcW().revalidate();
		gC=new GiocoClient(new Slider(userName, (int)(1280 - 79), 0));
		
		try {
			
			socket= new Socket(ipServer, porta);//connessione al server
			streamOut= new ObjectOutputStream(socket.getOutputStream());//apro stream di output			
			streamOut.writeObject(gC.getClientPlayer());
			streamInput= new ObjectInputStream(socket.getInputStream());//apro stream di input
			Ball pallina= (Ball) streamInput.readObject();
			Slider s=(Slider) streamInput.readObject();
			f.setSize(1280,720);//imposto dimensioni schermo
			f.changePanel(gC);
			f.getgC().setServerPlayer(s);
			f.getgC().setPallina(pallina);
			new Thread(gC).start();
			serverPlay=0;

			if(socket.isConnected()) {
				
				while(flag) {
					
					streamInput= new ObjectInputStream(socket.getInputStream());
					pallina= (Ball) streamInput.readObject();
					s=(Slider) streamInput.readObject();
					f.getgC().setServerPlayer(s);
					f.getgC().setPallina(pallina);
					streamOut= new ObjectOutputStream(socket.getOutputStream());
					streamOut.writeObject(f.getgC().getClientPlayer());
					
					if(pallina.getClientPoints().compareTo("5") == 0) {	//controllo se il giocatore del client ha vinto		
						
						new Thread(new WaitingServer(this)).start();//creo thread per attendere risposta dal server per contrinuare la partita

						JOptionPane.showMessageDialog(null, "complimenti sei il vincitore");
						
						if(serverPlay!=-1) {//controllo se il server ha già inviato una risposta

							int res=JOptionPane.showConfirmDialog(null, "vuoi giocare ancora?","gioca ancora",JOptionPane.YES_NO_OPTION);
						
							if(res==JOptionPane.YES_OPTION) {//contorllo se il giocatore vuole giocare ancora
																
								streamOut= new ObjectOutputStream(socket.getOutputStream());
								streamOut.writeObject(true);//indico al server che voglio giocare ancora
								pallina.setClientPoints(0);//reimposto punti client e server
								pallina.setServerPoints(0);
								
								while(serverPlay==0) {//aspetto risposta dal server
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
									}
								}
									
								if(serverPlay==1) {//controllo se il giocatore del server vuole giocare ancora
									serverPlay=0;
									f.getgC().setFlag(false);
									new Thread(gC).start();
								}
								else {
									JOptionPane.showMessageDialog(null, s.getName()+" ha lasciato la partita");
									flag=false;
								}
							}
							
							else {//lascio la partita
								streamOut= new ObjectOutputStream(socket.getOutputStream());
								streamOut.writeObject(false);
								flag=false;
							}
						}
						else {
							JOptionPane.showMessageDialog(null, s.getName()+" ha lasciato la partita");
							flag=false;
						}
						
						
					}
					else if(pallina.getServerPoints().compareTo("5") == 0) {//controllo se il giocatore del server ha vinto
						
						new Thread(new WaitingServer(this)).start();//creo thread per attendere risposta dal server per contrinuare la partita

						JOptionPane.showMessageDialog(null,s.getName()+" è il vincitore");
							
						if(serverPlay!=-1) {//controllo se il server ha già inviato una risposta

							int res=JOptionPane.showConfirmDialog(null, "vuoi giocare ancora?","gioca ancora",JOptionPane.YES_NO_OPTION);
						
							if(res==JOptionPane.YES_OPTION) {//contorllo se il giocatore vuole giocare ancora
								
								if(serverPlay!=-1) {//controllo se il server ha già inviato una risposta
									
									streamOut= new ObjectOutputStream(socket.getOutputStream());
									streamOut.writeObject(true);//indico al server che voglio giocare ancora
									pallina.setClientPoints(0);//reimposto punti client e server
									pallina.setServerPoints(0);
									
									while(serverPlay==0) {//aspetto risposta dal server
										try {
											Thread.sleep(100);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
										}
									}
									
									if(serverPlay==1) {//controllo se il giocatore del server vuole giocare ancora
										serverPlay=0;
										f.getgC().setFlag(false);
										new Thread(gC).start();
									}
									else {
										JOptionPane.showMessageDialog(null, s.getName()+" ha lasciato la partita");
										flag=false;
									}
								}
								else {
									JOptionPane.showMessageDialog(null, s.getName()+" ha lasciato la partita");
									flag=false;
								}
							}
							else {//lascio la partita 
								streamOut= new ObjectOutputStream(socket.getOutputStream());
								streamOut.writeObject(false);
								flag=false;
							}
						}
						else {
							JOptionPane.showMessageDialog(null, s.getName()+" ha lasciato la partita");
							flag=false;
						}
						
					}
					else {
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
						}
					}
				}
			}
			
			//chiudo connessioni e reinserisco schermata di attesa del client
			
			ClientWait cW=new ClientWait();
			f.getgC().setFlag(true);
			f.changePanel(cW);
			new ControllerClient(f);
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "server non raggiungibile","errore server",JOptionPane.ERROR_MESSAGE);
			ClientWait cW=new ClientWait();
			f.changePanel(cW);
			new ControllerClient(f);
			if(f.getgC()!=null) {
				f.getgC().setFlag(true);
			}
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "errore ricezione dati","errore server",JOptionPane.ERROR_MESSAGE);
			ClientWait cW=new ClientWait();
			f.changePanel(cW);
			new ControllerClient(f);
			if(f.getgC()!=null) {
				f.getG().setFlag(true);
			}
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
			
			}
		}
	}
	/**
	 * Metodo per impostare valore variabile serverPlay
	 * @param serverPlay
	 */
	public void setServerPlay(int serverPlay) {
		this.serverPlay = serverPlay;
	}
	
	public ObjectInputStream getStreamInput() {
		try {
			return 	new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	
}
