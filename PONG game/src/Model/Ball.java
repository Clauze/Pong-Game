package Model;

import java.awt.Dimension;
import java.io.Serializable;
/**
 * La classe Ball è la pallina del gioco
 * @author Claudio
 *@version 1.0
 */
public class Ball implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Posizione sull'asse delle x della pallina
	 */
	private int x;			 
	/**
	 * Posizione sull'asse delle Y della pallina
	 */
	private int y;		
	/**
	 * Velocità rispetto all'asse delle X della pallina
	 */
	private double xv;      	 
	/**
	 * Velocità rispetto all'asse delle Y della pallina
	 */
	private double yv;
	/**
	 * Raggio della pallina
	 */
	private int radius;		
	/**
	 * Punti del giocatore del client
	 */
	private int clientPoints=0;
	/**
	 * Punti del giocatore del server
	 */
	private int serverPoints=0;
	/**
	 * Dimensioni schermo
	 */
	private Dimension d;
	
	public Ball(int x, int y, double xv, double yv, int radius) {
		super();
		this.x = x;
		this.y = y;
		this.xv = xv;
		this.yv = yv;
		this.radius = radius;
	}
 
	/**
	 * Metodo per muovere la pallina e farla rimbalzare sui bordi
	 */
	public void move(){
		x += xv;
		y += yv;
		if(x > (d.width - radius)){ // - Calibrate the screen layer - //
			serverPoints+=1;
			x= (d.width-radius); // - set the position of the ball
			xv = xv * -1; // - set the velocity of the ball

		}
		
		else if(x < 0){  // - Calibrate the screen layer - //
			x = 0;
			clientPoints+=1;
			xv = xv *-1;
		}
		
		if(y < 0){ // - Calibrate the screen layer - //
			y = 0;
			yv = yv * -1;
		}
		
		else if(y > (d.height - radius)) // - Calibrate the screen layer - //
		{
			y = (d.height-radius); 
			yv = yv * -1;
			
		}
		

	}
	
	  ///////////////////////////
	 // - Getters & Setters - //
	///////////////////////////
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getXv() {
		return xv;
	}
	public void setXv(double xv) {
		this.xv = xv;
	}
	public double getYv() {
		return yv;
	}
	public void setYv(double yv) {
		this.yv = yv;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public void setClientPoints(int clientPoints) {
		this.clientPoints = clientPoints;
	}

	public void setServerPoints(int serverPoints) {
		this.serverPoints = serverPoints;
	}
	
	public void setD(Dimension d) {
		this.d = d;
	}

	public String getClientPoints() {
		return String.valueOf(clientPoints);
	}

	public String getServerPoints() {
		return String.valueOf(serverPoints);
	}

	
}
