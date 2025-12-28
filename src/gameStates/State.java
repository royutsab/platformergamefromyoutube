package gameStates;


import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public class State {
	protected Game game;




public State(Game game) {
	// TODO Auto-generated constructor stub
	this.game = game;
}

public boolean IsIn(MouseEvent e,MenuButton mb) {
	return mb.getbounds().contains(e.getX(),e.getY());
}

public Game getGame() {
return game;
}





}

