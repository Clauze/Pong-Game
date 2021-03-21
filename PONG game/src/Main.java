import Control.Controller;
import View.Finestra;

/**
 * Classe main
 * @author Claudio
 *
 */
public class Main {
	/**
	 * Metodo main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Finestra f=new Finestra();
		f.setVisible(true);
		new Controller(f);
		
	}		
}
