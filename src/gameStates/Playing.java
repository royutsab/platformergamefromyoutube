package gameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverLay;

public class Playing extends State implements StateMethods{

	private Player player;
	private LevelManager levelmanager;
	private PauseOverLay pauseOverLay;
	private boolean paused = false;
	
	public Playing(Game game) {
		super(game);
	initClasses();
	}
	
	private void initClasses() {
		levelmanager = new LevelManager(game);
		player  = new Player(200,200,(int)(64*Game.SCALE),(int)(40*Game.SCALE));
		player.loadlvldata(levelmanager.getcurrentlevel().getlvldata());
		pauseOverLay =  new PauseOverLay(this);
	}

	

	@Override
	public void update() {
		if(!paused) {
		levelmanager.update();
		player.update();
		}else {
		pauseOverLay.update();
		}
	}

	@Override
	public void draw(Graphics g) {
		levelmanager.draw(g);
		player.render(g);
		
		if(paused)
		pauseOverLay.draw(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {
			player.setAttacking(true);
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	if(paused)
		pauseOverLay.mousePressed(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(paused)
			pauseOverLay.mouseReleased(e);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(paused)
			pauseOverLay.mouseMoved(e);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()) {
			
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;	
			
		case KeyEvent.VK_D:
		player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
		player.setJump(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused =!paused;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {

			
		case KeyEvent.VK_A:
			player.setLeft(false);;
			break;	
			
		case KeyEvent.VK_D:
		player.setRight(false);;
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);;
			break;
		}
		
	}
	public void mouseDragged(MouseEvent e) {
		if(paused)
			pauseOverLay.mouseDragged(e);
	}
	
	public void unpausedGame() {
		paused = false;
	}
	
	public void windowFocusLost() {
		player.resetPlayerDirection();
	}
	public Player getPlayer() {
		return player;
}
	
	}
