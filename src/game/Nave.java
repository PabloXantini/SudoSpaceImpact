package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;


import javax.swing.JPanel;

public class Nave implements KeyListener{
	//Variables
	private int posX;
	private int posY;
	//Variables temporales
	private int ancho;
	private int alto;
	//Añada archivo para imagen de nave
	//Variables de métodos
	enum MOVE{
		UP,
		DOWN,
		LEFT,
		RIGHT,
		ATTACK
	}
	private Map<Integer,MOVE> moves =  new HashMap<>();
	private EnumMap<MOVE, Boolean> movesActivados = new EnumMap<>(MOVE.class);
	//Variables de prueba
	public Nave() {}
	public Nave(int X, int Y) {
		map(moves);
		posX=X;
		posY=Y;
		//Variables temporales del constructor
		ancho=40;
		alto=40;
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
	public void mostrar(JPanel panel, Graphics g){
		g.drawRect(posX, posY, ancho, alto);
	}
	public void map(Map<Integer,MOVE> moves) {
		//Movimientos
		moves.put(KeyEvent.VK_UP,MOVE.UP);
		moves.put(KeyEvent.VK_DOWN,MOVE.DOWN);
		moves.put(KeyEvent.VK_LEFT,MOVE.LEFT);
		moves.put(KeyEvent.VK_RIGHT,MOVE.RIGHT);
		//Ataques
		moves.put(KeyEvent.VK_ENTER, MOVE.ATTACK);
	}
	//Metodos de prueba
	//Fin de metodos de prueba
	private void mover(MOVE move, int dis) {
		switch(move) {
			case UP:
				setPosY(getPosY()-dis);
				break;
			case DOWN:
				setPosY(getPosY()+dis);
				break;
			case LEFT:
				setPosX(getPosX()-dis);
				break;
			case RIGHT:
				setPosX(getPosX()+dis);
				break;
			case ATTACK:
				atacar();
		}
	}
	private void getMove(int step) {
		//Aqui va comprobar si mis teclas presionadas tienen alguna tecla
		for(Map.Entry<MOVE, Boolean> entry:movesActivados.entrySet()) {
			if(entry.getValue()) {
				mover(entry.getKey(),step);
				System.out.println(entry.getKey());
			}
		}
	}
	private void atacar() {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int cTecla=e.getKeyCode();
		MOVE adMove=moves.get(cTecla);
		try {
			movesActivados.put(adMove, true);
		}catch(NullPointerException ne) {
			
		}
		getMove(10);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int cTecla=e.getKeyCode();
		MOVE adMove=moves.get(cTecla);
		try {
			movesActivados.put(adMove, false);
		}catch(NullPointerException ne) {
			
		}
	}
}