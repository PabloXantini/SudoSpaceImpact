package game;

import java.awt.Graphics;
import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.Random;

public class FinalBoss extends Boss{
	private Random rd0 = new Random();
	private int rand0 = 100;
	private int maxTicks1 = 1000+rd0.nextInt(rand0);
	private int counterSMOVE1=0;
	//Banderas
	private boolean Embestida=false;
	//Guardar un movimiento para que no se repita
	private int tempevent=2; //N+1
	enum MOVE{
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	enum SPECIALMOVE{
		VERT,
		RUSH
	}
	enum ATTACKS{
		
	}
	private EnumMap<MOVE, Boolean> flmoves = new EnumMap<>(MOVE.class);
	private EnumMap<SPECIALMOVE, Boolean> flMOVES = new EnumMap<>(SPECIALMOVE.class);
	public FinalBoss(int X, int Y, int A, int B) {
		super(X,Y,A,B);
		map();
	}
	@Override
	public void mostrar(Graphics g) {
		// TODO Auto-generated method stub
		g.drawRect(getCX(), getCY(), getAncho(), getAlto());
	}
	@Override
	public void mover() {
		// TODO Auto-generated method stub
		Random rd = new Random(); 
		//Elige un movimiento
		int numevent = rd.nextInt(2);
		if(numevent==0&&hayMovLibres()&&numevent!=tempevent) {
			tempevent=numevent;
			flMOVES.put(SPECIALMOVE.VERT, true);
		}else if(numevent==1&&hayMovLibres()&&numevent!=tempevent) {
			tempevent=numevent;
			flMOVES.put(SPECIALMOVE.RUSH, true);
		}
		moverVerticalmente(50,200,2);			
		Embestir(700, 1000, 600, 1);
	}
	@Override
	public void atacar() {
		// TODO Auto-generated method stub
	}
	public void map() {
		flmoves.put(MOVE.UP, false);
		flmoves.put(MOVE.DOWN, false);
		flmoves.put(MOVE.LEFT, false);
		flmoves.put(MOVE.RIGHT, false);
		//Este mapeo determinan que movimientos estan ocupados o desocupados
		flMOVES.put(SPECIALMOVE.VERT, false);
		flMOVES.put(SPECIALMOVE.RUSH, false);
	}
	public boolean hayMovLibres() {
		for(Entry<SPECIALMOVE, Boolean> entry : flMOVES.entrySet()) {
			if(entry.getValue()) {
				return false;
			}
		}
		return true;
	}
	public void moverVerticalmente(int minY, int maxY, int step) {
		if(flMOVES.get(SPECIALMOVE.VERT)==true) { //Si el movimiento no esta ocupado
			if(getCY()<=minY) {
				flmoves.put(MOVE.UP, false);
				flmoves.put(MOVE.DOWN, true);
			}else if(getCY()>=maxY) {
				flmoves.put(MOVE.UP, true);
				flmoves.put(MOVE.DOWN, false);
			}
			if(flmoves.get(MOVE.UP)) {
				setPosY(getPosY()-step);
			}
			if(flmoves.get(MOVE.DOWN)){
				setPosY(getPosY()+step);
			}
			counterSMOVE1++;
		}
		if(counterSMOVE1==maxTicks1) {
			flMOVES.put(SPECIALMOVE.VERT, false);
			maxTicks1=1000+rd0.nextInt(rand0);
			counterSMOVE1=0;
		}
	}
	public void Embestir(int XBase, int bottomX, int distanceMax, int step) {
		if(flMOVES.get(SPECIALMOVE.RUSH)==true) { //Si el movimiento no esta ocupado
			//Prepara la embestida
			flmoves.put(MOVE.RIGHT, true);
			if(getCX()>=bottomX) {
				if(Embestida==false) {
					flmoves.put(MOVE.LEFT, true);
					flmoves.put(MOVE.RIGHT, false);
				}
			}else if(getPosX()<=XBase-distanceMax) {
				flmoves.put(MOVE.LEFT, false);
				flmoves.put(MOVE.RIGHT, true);
				Embestida=true;
			}
			if(getPosX()>=XBase&&Embestida==true) {
				flmoves.put(MOVE.LEFT, false);
				flmoves.put(MOVE.RIGHT, false);
				setPosX(XBase);
				flMOVES.put(SPECIALMOVE.RUSH, false);
				Embestida=false;
			}
			if(Embestida==false&&flmoves.get(MOVE.RIGHT)) {
				setPosX(getPosX()+step);
			}else if(Embestida==true&&flmoves.get(MOVE.RIGHT)) {
				setPosX(getPosX()+step*2);
			}
			if(flmoves.get(MOVE.LEFT)) {
				setPosX(getPosX()-step*8);
			}
		}

	}
}
