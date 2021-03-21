package Control;

import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * La classe Server gestisce l'attesa di una risposta del server in caso voglia riprendere la partita
 * @author Claudio
 *@version 1.0
 */
public class WaitingServer implements Runnable{
	/**
	 * oggetto client per impostare valori dopo risposta server
	 */
	private Client s;
	/**
	 * metodo costruttore
	 * @param s
	 * @param socket
	 */
	public WaitingServer(Client s) {
		super();
		this.s = s;
	}




	@Override
	public void run() {
		// TODO Auto-generated method stub

		Boolean flag;
		try {
			ObjectInputStream streamInput= s.getStreamInput();//stream per ricevere risposta server
			if(streamInput!=null) {
				flag = (Boolean) streamInput.readObject();
				
				if(flag) {//se il server da risposta affermativa per continuare partita
					s.setServerPlay(1);
				}
				else {
					s.setServerPlay(-1);
				}
			}
			
		//	obIn.reset();
		} catch (ClassNotFoundException | IOException e) {
		// TODO Auto-generated catch block
			
		}		
	}

}
