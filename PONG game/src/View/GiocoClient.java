package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import Model.Ball;
import Model.Slider;
/**
 * Il metodo GiocoClient rappresenta la parte grafica del gioco del client
 * @author Claudio,Adis
 * @version 1.0
 */
public class GiocoClient extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Pallina del gioco
	 */
	private Ball pallina;
	/**
	 * Barra del client e del server
	 */
	private Slider serverPlayer,clientPlayer;
	/**
	 * Variabile impostata a 'false' fino a quando uno dei giocatori non vince o lascia la partita
	 */
	private boolean flag=false;
	/**
	 * Font personalizzato
	 */
	private Font font;
	/**
	 * metodo costruttore
	 * @param clientPlayer
	 */
	public GiocoClient(Slider clientPlayer) {
		this.setLayout(null);
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
		pallina=new Ball(100, 100, 4, 4, 20);
		this.clientPlayer=clientPlayer;
		flag=false;
		
	}
	/**
	 * Area grafica
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));//reimposto dimensioni linea
        g2.draw(new Line2D.Float(this.getSize().width/2, 0, this.getSize().width/2, this.getSize().height));
        g.setFont(font);
        if(serverPlayer!=null) {
        	g.drawString(serverPlayer.getName(), (this.getSize().width/2)-200, this.getSize().height-50);
			g.fillRect(serverPlayer.getPosX(), serverPlayer.getPosY(), serverPlayer.getLunghezza(), serverPlayer.getAltezza());
        }
        g.drawString(clientPlayer.getName(), (this.getSize().width/2)+100, this.getSize().height-50);	
        g.drawString(pallina.getServerPoints(), (this.getSize().width/2)-150, 30);
        g.drawString(pallina.getClientPoints(), (this.getSize().width/2)+100, 30);
		g.fillRect(clientPlayer.getPosX(), clientPlayer.getPosY(), clientPlayer.getLunghezza(), clientPlayer.getAltezza());
		g.fillOval(pallina.getX(), pallina.getY(), pallina.getRadius(), pallina.getRadius());
		setBackground(Color.black);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		this.clientPlayer.setD(new Dimension(this.getSize().width, this.getSize().height));
		this.pallina.setD(new Dimension(this.getSize().width, this.getSize().height));

		while(!flag)	{
			clientPlayer.move();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
			if(pallina.getClientPoints().compareTo("5") == 0 || pallina.getServerPoints().compareTo("5") == 0) {
				flag=true;
			}
		}
	}
	
	 ///////////////////////////
	 // - Getters & Setters - //
	///////////////////////////
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	

	public Ball getPallina() {
		return pallina;
	}
	
	public void setPallina(Ball pallina) {
		this.pallina = pallina;
	}


	public Slider getClientPlayer() {
		return clientPlayer;
	}

	public void setServerPlayer(Slider serverPlayer) {
		this.serverPlayer = serverPlayer;
	}
}
