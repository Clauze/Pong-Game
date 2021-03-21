package Model;

import java.awt.Dimension;
import java.io.Serializable;
/**
 * La classe Slider rappresenta la barra dei due giocatori 
 * @author Claudio
 *@version 1.0
 */
public class Slider implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * nome del giocatore
	 */
	private String name;
	/**
	 * posizione asse x della barra 
	 */
	private int posX;
	/**
	 * posizione asse y della barra
	 */
	private int posY;
	/**
	 * velocità movimento della barra
	 */
	private int velY;
	/**
	 * lunghezza della barra
	 */
	private int lunghezza;
	/**
	 * altezza della barra 
	 */
	private int altezza;
	/**
	 * Dimensioni schermo
	 */
	private Dimension d;
	/**
	 * Costruttore barra
	 * @param name
	 * @param posX
	 * @param posY
	 */
	public Slider(String name,int posX,int posY) {
		this.name=name;
		this.posX = posX;
		this.posY = posY;
		this.velY = 0;
		this.lunghezza = 30;
		this.altezza = 120;
	}

	 ///////////////////////////
	 // - Getters & Setters - //
	///////////////////////////
	
	public Dimension getD() {
		return d;
	}

	public void setD(Dimension d) {
		this.d = d;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosY(int velY) {
		this.posY += velY;
	}
	
	public void setPosX(int posX) {
		this.posX= posX;
	}
	
	public int getPosY() {
		return posY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getVelY() {
		return velY;
	}	

	public int getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(int lunghezza) {
		this.lunghezza = lunghezza;
	}

	public int getAltezza() {
		return altezza;
	}

	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Metodo per muovere la barra
	 */
	public void move() {
		this.setPosY(velY);
		if(this.posY < 0) {
			this.posY=0;
		}
		else if(this.posY > d.height - this.altezza) {
			this.posY= d.height - this.altezza;
		}
	}
}
