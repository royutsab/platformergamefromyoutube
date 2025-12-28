package main;

import java.awt.Graphics;


import gameStates.GameState;
import gameStates.Menu;
import gameStates.Playing;


public class Game implements Runnable{
	private GameWindow gamewindo;
	private Gamepanel gamepanel; 
	private Thread gameThread;
	private final int FPS_SET = 120; 
	private final int UPS_SET = 200;
	
	private Playing playing;
	private Menu menu;
	
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE =1.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILES_SIZE =(int) (TILES_DEFAULT_SIZE*SCALE);
	public final static int GAME_WIDTH = TILES_SIZE *TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	public Game() {
		initClasses();
		gamepanel = new Gamepanel(this);
	gamewindo =	new GameWindow(gamepanel);
gamepanel.requestFocus();

startGameLoop();

	
}
	private void initClasses() {
		menu = new Menu(this);
		playing = new Playing(this);
	}
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	private void update() {
		
		switch(GameState.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case OPTIONS:
		case QUIT:
		default:
		System.exit(0);;
			break;
		
		}
	}
	public void render(Graphics g) {
		
		switch(GameState.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
		
			break;
		default:
			break;
		
		}
	}
	@Override
	public void run() {
		
		double timePerFrame = 1000000000 / FPS_SET;
		double timeperupdate = 1000000000 / UPS_SET;
		
		
		long prevoiusTime = System.nanoTime();
		
		int frames =0;
		int update =0;
		long lastCheck = System.currentTimeMillis();
		
		double DeltaU =0;
		double deltaF =0;
		
		while(true) {
			
			long CurrentTime =System.nanoTime();
			
			DeltaU += (CurrentTime-prevoiusTime)/timeperupdate;
			deltaF +=  (CurrentTime-prevoiusTime)/timePerFrame;
			prevoiusTime = CurrentTime;
			
			if(DeltaU>=1) {
				update();
				update++;
				DeltaU--;
			}
			if(deltaF>=1) {
				gamepanel.repaint();
				
				frames++;
				deltaF--;
			}
			
			
			if(System.currentTimeMillis() - lastCheck >=1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: "+frames+" | UPS: "+update);
				frames =0;
				update=0;
			}
		}
		
		
	}
	public void windowFocusLost() {
		if(GameState.state == GameState.PLAYING)
			playing.getPlayer().resetPlayerDirection();
	}
public Menu getMenu() {
	return menu;
}
public Playing getPlaying() {
return playing;
}
}
