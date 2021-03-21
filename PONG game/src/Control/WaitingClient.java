package Control;

import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * La classe Server gestisce l'attesa di una risposta del client in caso voglia riprendere la partita
 * @author Claudio
 *@version 1.0
 */
public class WaitingClient implements Runnable{
	/**
	 * oggetto server per impostare valori dopo risposta client
	 */
	private Server s;
	/**
	 * metodo costruttore
	 * @param s
	 * @param socket
	 */

	public WaitingClient(Server s) {
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
					s.setClientPlay(1);
				}
				else {
					s.setClientPlay(-1);
				}
			}
			//obIn.reset();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			
		}		
	}

}
