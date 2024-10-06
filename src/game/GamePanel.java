package game;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel{
	Nave nave;
	FinalBoss FBoss;
	public GamePanel() {
		nave = new Nave(50, 50);
		FBoss = new FinalBoss(700,400,300,400);
		int tiempo=0;
		Timer time = new Timer(10, new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				repaint();
			}
		});
		time.start();
		addKeyListener(nave);
		setFocusable(true);
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		nave.mostrar(this, g);
		FBoss.mostrar(g);
		FBoss.mover();
	}
}
