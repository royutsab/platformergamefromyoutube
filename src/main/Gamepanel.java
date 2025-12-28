package main;


import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.Keyboardinputs;
import inputs.Mouseinputs;


import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class Gamepanel extends JPanel{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Mouseinputs mouseinput;
	private Game game;
public Gamepanel(Game game) {
//	jpanel.setOpaque(true);\
	this.game = game;
	mouseinput = new Mouseinputs(this);
	
	

	
	setPanelsize();
	addKeyListener(new Keyboardinputs(this));
	addMouseListener(mouseinput);
	addMouseMotionListener(mouseinput);
}

	

private void setPanelsize() {
	Dimension sie = new Dimension(GAME_WIDTH,GAME_HEIGHT);
//	Dimension sie = new Dimension(800,500);
	setPreferredSize(sie);
	
	
}
public void UpdateGAme() {
	
}

public void paintComponent(Graphics g) {
	super.paintComponent(g);
	
game.render(g);
}
public Game getGame() {
	return game;
}

}
