package Control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;
import Model.Ball;
import Model.Slider;
import View.Finestra;
import View.Gioco;
import View.ServerWait;
/**
 * La classe Server gestisce le comunicazioni con il client
 * @author Claudio
 *@version 1.0
 */
public class Server implements Runnable{
	/**
	 * Server socket
	 */
	private ServerSocket serverSocket;
	/**
	 * socket per la connessione al client
	 */
	private Socket socket;
	/**
	 * schermata del gioco
	 */
	private Finestra f;
	/**
	 * Username giocatore del server
	 */
	private String userName;
	/**
	 * porta di default del server
	 */
	private int porta=9999;
	/**
	 * la variabile è uguale a 'true' fino a quando il client è raggiungibile
	 */
	private boolean flag=true;
	/**
	 * variabile per controllare lo stato del client al termine della partita
	 */
	private int clientPlay=0;
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
	 */
	public Server(Finestra f,String userName) {
		super();
		this.f=f;
		this.userName=userName;
		porta=9999;
		flag=true;
		clientPlay=0;
	}
	/**
	 * costruttore con porta personalizzata
	 * @param f
	 * @param userName
	 * @param porta
	 */
	public Server(Finestra f,String userName,int porta) {
		super();
		this.f=f;
		this.userName=userName;
		this.porta=porta;
		flag=true;
		clientPlay=0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			
			if(flag) {
				
				f.getsW().getLblNewLabel_1().setVisible(true);
				f.getsW().revalidate();
				serverSocket = new ServerSocket(porta);//avvio server
				serverSocket.setSoTimeout(60000);//tempo massimo di attesa di una connessione
				Gioco g=new Gioco(new Slider(userName, 30, 0));
				socket=serverSocket.accept();//aspetto rischiesta
				streamInput= new ObjectInputStream(socket.getInputStream());//apro stream di input
				Slider s=(Slider) streamInput.readObject();
				streamOut= new ObjectOutputStream(socket.getOutputStream());//apro stream di output				
				streamOut.writeObject(g.getPallina());
				streamOut.writeObject(g.getserverPlayer());
				f.setSize(1280,720);//imposto dimensioni finestra
				f.changePanel(g);
				f.getG().setClientPlayer(s);
				new Thread(g).start();
				Ball pallina;
				clientPlay=0;
				
				if(socket.isConnected()) {
					
					String cp,sP;//variabili con i punti dei due giocatori

					while(flag) {
						
						pallina=f.getG().getPallina();
						streamOut= new ObjectOutputStream(socket.getOutputStream());
						streamOut.writeObject(pallina);//invio oggetto pallina a client
						cp=pallina.getClientPoints();
						sP=pallina.getServerPoints();
						streamOut.writeObject(f.getG().getserverPlayer());//invio oggetto slider al client
						streamInput= new ObjectInputStream(socket.getInputStream());
						s=(Slider) streamInput.readObject();//ricevo oggetto slider dal client
						f.getG().setClientPlayer(s);
						
						if(cp.compareTo("5") == 0) {//controllo se il client ha vinto
							
							new Thread(new WaitingClient(this)).start();//creo thread per attendere risposta dal client per contrinuare la partita
							
							JOptionPane.showMessageDialog(null, s.getName()+" è il vincitore");
							
							if(clientPlay!=-1) {//controllo se il client ha già inviato una risposta
								
								int res=JOptionPane.showConfirmDialog(null, "vuoi giocare ancora?","gioca ancora",JOptionPane.YES_NO_OPTION);
								
								if(res==JOptionPane.YES_OPTION) {//controllo se il giocatore vuole giocare ancora
									
										
									streamOut= new ObjectOutputStream(socket.getOutputStream());
									streamOut.writeObject(true);//indico al client che voglio giocare ancora
									pallina.setClientPoints(0);//reimposto punti client e server
									pallina.setServerPoints(0);
		
									while(clientPlay==0) {//aspetto risposta dal client
										try {
											Thread.sleep(100);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
										}
									}
										
									if(clientPlay==1) {//controllo se il giocatore del client vuole giocare ancora
										f.getG().setFlag(false);
										new Thread(g).start();
										clientPlay=0;
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
							else{
								JOptionPane.showMessageDialog(null, s.getName()+" ha lasciato la partita");
								flag=false;
							}							
						}
						
						else if(sP.compareTo("5") == 0) {//controllo se il giocatore del server ha vinto
							
							new Thread(new WaitingClient(this)).start();//creo thread per attendere risposta dal client per contrinuare la partita

							JOptionPane.showMessageDialog(null,"complimenti sei il vincitore");
							
							if(clientPlay!=-1) {//controllo se il client ha inviato risposta
								
								int res=JOptionPane.showConfirmDialog(null, "vuoi giocare ancora?","gioca ancora",JOptionPane.YES_NO_OPTION);
								
								if(res==JOptionPane.YES_OPTION) {//controllo se il giocatore del server vuoler giocare ancora
											
									streamOut= new ObjectOutputStream(socket.getOutputStream());
									streamOut.writeObject(true);//indico al client che voglio giocare ancora
									pallina.setClientPoints(0);//reimposto punti client e server
									pallina.setServerPoints(0);
	
									while(clientPlay==0) {//aspetto risposta dal client
										try {
											Thread.sleep(100);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
										}
									}
										
									if(clientPlay==1) {//controllo se il giocatore del client vuole giocare ancora
										f.getG().setFlag(false);
										new Thread(g).start();
										clientPlay=0;
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
							else{
								JOptionPane.showMessageDialog(null, s.getName()+" ha lasciato la partita");
								flag=false;
							}
							
						}
						
						else {
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}

				}
				
				socket.close();
				f.getG().setFlag(true);
				ServerWait sW=new ServerWait();
				f.changePanel(sW);
				new ControllerServerWait(f);
				if(!serverSocket.isClosed()) {
					try {
						serverSocket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
					}
				}
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			flag=false;
			JOptionPane.showMessageDialog(null, "client non raggiungibile","errore client",JOptionPane.ERROR_MESSAGE);
			ServerWait sW=new ServerWait();
			f.changePanel(sW);
			new ControllerServerWait(f);
			if(f.getG()!=null) {
				f.getG().setFlag(true);
			}
			if(!serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			flag=false;
			JOptionPane.showMessageDialog(null, "errore ricezione dati","errore client",JOptionPane.ERROR_MESSAGE);
			ServerWait sW=new ServerWait();
			f.changePanel(sW);
			new ControllerServerWait(f);
			if(f.getG()!=null) {
				f.getG().setFlag(true);
			}
			if(!serverSocket.isClosed()) {
				try {
					serverSocket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				}
			}
		}
		

	}
	/**
	 * Metodo per impostare valore variabile clientPlay
	 * @param clientPlay
	 */
	public void setClientPlay(int clientPlay) {
		this.clientPlay = clientPlay;
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
