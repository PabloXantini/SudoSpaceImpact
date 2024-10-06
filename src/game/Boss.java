package game;

import java.awt.Graphics;

public abstract class Boss {
	//Variables
		private int posX;
		private int posY;
		//Variables temporales
		private int ancho;
		private int alto;
	public Boss() {}
	public Boss(int X, int Y, int A, int B) {
		posX=X;
		posY=Y;
		ancho=A;
		alto=B;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public int getCX() {
		int cx = getPosX()-getAncho()/2;
		return cx;
	}
	public int getCY() {
		int cy = getPosY()-getAlto()/2;
		return cy;
	}
	/**
	 * Sobreescribe este método para dibujar al jefe 
	 */
	public abstract void mostrar(Graphics g);
	/**
	 * Sobreescribe este método para determinar los movimientos del jefe
	 */
	public abstract void mover();
	/**
	 * Sobrescribe este método para determinar los ataques del jefe
	 */
	public abstract void atacar();
}
