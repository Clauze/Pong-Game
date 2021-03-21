package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import View.Finestra;
import View.Gioco;
/**
 * La classe Controller gestisce le iterazione con la schermata iniziale del gioco
 * @author Claudio
 *@version 1.0
 */
public class Controller implements ActionListener,KeyListener,WindowListener{
	/**
	 * schermata del gioco
	 */
	private Finestra frame;
	/**
	 * Metodo costruttore
	 * @param frame
	 */
	public Controller(Finestra frame) {
		super();
		this.frame = frame;
		frame.addKeyListener(this);
		frame.getBtnCrea().addActionListener(this);
		frame.getBtnUnisciti().addActionListener(this);
		frame.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==frame.getBtnCrea()) {
			new ControllerServerWait(frame);
			
		}
		else {
			new ControllerClient(frame);
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(frame.getgC()==null) {
				frame.getG().getserverPlayer().setVelY(-10);
			}
			else {
				frame.getgC().getClientPlayer().setVelY(-10);
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(frame.getgC()==null) {
				frame.getG().getserverPlayer().setVelY(10);
			}
			else {
				frame.getgC().getClientPlayer().setVelY(10);
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== KeyEvent.VK_UP) {
			if(frame.getgC()==null) {
				frame.getG().getserverPlayer().setVelY(0);
			}
			else {
				frame.getgC().getClientPlayer().setVelY(0);
			}
		}
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			if(frame.getgC()==null) {
				frame.getG().getserverPlayer().setVelY(0);
			}
			else {
				frame.getgC().getClientPlayer().setVelY(0);
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		int a=JOptionPane.showConfirmDialog(null, "sei sicuro di voler uscire?","vuoi uscire",JOptionPane.YES_NO_OPTION);
		if(a == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		else {
			frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
