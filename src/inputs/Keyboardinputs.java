package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gameStates.GameState;
import main.Gamepanel;


public class Keyboardinputs implements KeyListener{
	private Gamepanel gamepanel;
public Keyboardinputs(Gamepanel gamepanel) {
	this.gamepanel = gamepanel;
}
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	switch(GameState.state) {
	case MENU:
		gamepanel.getGame().getMenu().keyPressed(e);
		break;
	case PLAYING:
		gamepanel.getGame().getPlaying().keyPressed(e);
		break;
	default:
		break;
	
	}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(GameState.state) {
		case MENU:
			gamepanel.getGame().getMenu().keyReleased(e);
			break;
		case PLAYING:
			gamepanel.getGame().getPlaying().keyReleased(e);
			break;
		default:
			break;
		
		}
		
	}

}
