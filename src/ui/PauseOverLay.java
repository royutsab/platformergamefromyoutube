package ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gameStates.GameState;
import gameStates.Playing;
import main.Game;
import utils.Constant.UI.URMButtons;
import utils.LoadSave;
import static utils.Constant.UI.PauseButtons.*;
import static utils.Constant.UI.URMButtons.*;
import static utils.Constant.UI.OlumeButon.*;

public class PauseOverLay {
	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW,bgH;
	private SoundButton MusicButton, sfxButton;
	private UrmButton menuB, replayB, unpauseB;
	private OlumeButton olumeButton;
	
	public PauseOverLay( Playing playing) {
		this.playing = playing;
		loadBackground();
		CreateSoundButtons();
		CreateURMButton();
		CreateOlume();
	}
	
	private void CreateOlume() {
		int oX = (int)(Game.SCALE*309);
		int oY =(int)(278* Game.SCALE);
		olumeButton = new OlumeButton(oX, oY, SLIDER_WIDTH,OLUME_HEIGHT);
	}

	private void CreateURMButton() {
		int menuX = (int)(313*Game.SCALE);
		int replayX =(int)(387 *Game.SCALE);
		int unpauseX =(int)(462* Game.SCALE);
		int bY = (int)(325* Game.SCALE);
		
		menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
		replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
		unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
	}

	private void CreateSoundButtons() {
		int soundX =(int)(450* Game.SCALE);
		int musicY = (int)(140* Game.SCALE);
		int sfxY = (int)(186* Game.SCALE);
		MusicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteImg(LoadSave.PAUSE_BACKGROUND);
		bgW =  (int)(backgroundImg.getWidth()* Game.SCALE);
		bgH =  (int)(backgroundImg.getHeight()* Game.SCALE);
		bgX = Game.GAME_WIDTH/2- bgW/2;
		bgY = (int)(25* Game.SCALE);
		
	}

	public void update() {
		
		MusicButton.update();
		sfxButton.update();
		
		menuB.update();
		replayB.update();
		unpauseB.update();
		
		olumeButton.update();
	}
	
	public void draw(Graphics g) {
		//background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
		
		//sound buttons
		MusicButton.draw(g);
		sfxButton.draw(g);
		
		//urm buttons
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
		
		//olume button
		olumeButton.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) {
		if(olumeButton.isMousePressed()) {
			olumeButton.changeX(e.getX());
		}
		
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		if(isIn(e, MusicButton))
			MusicButton.setMousePressed(true);
		else if(isIn(e, sfxButton))
			sfxButton.setMousePressed(true);
		else if(isIn(e, menuB))
			menuB.setMousePressed(true);
		else if(isIn(e, replayB))
			replayB.setMousePressed(true);
		else if(isIn(e, unpauseB))
			unpauseB.setMousePressed(true);
		else if(isIn(e, olumeButton))
			olumeButton.setMousePressed(true);
	}

	
	public void mouseReleased(MouseEvent e) {
		
		if(isIn(e, MusicButton)) {
			if(MusicButton.isMousePressed()) {
				MusicButton.setMuted(!MusicButton.isMuted());
			}
		}		
		else if(isIn(e, sfxButton)) {
			if(sfxButton.isMousePressed()) {
				sfxButton.setMuted(!sfxButton.isMuted());
			}
		}
		else if(isIn(e,menuB)) {
			if(menuB.isMousePressed()) {
				GameState.state = GameState.MENU;
				playing.unpausedGame();
			}
		}
		else if(isIn(e, replayB)) {
			if(replayB.isMousePressed()) {
				System.out.println("REplay hahaha.");
			}
		}
		else if(isIn(e, unpauseB )) {
			if(unpauseB.isMousePressed()) {
			playing.unpausedGame();
			}
		}
			
MusicButton.resetbools();
sfxButton.resetbools();
menuB.resetbools();
replayB.resetbools();
unpauseB.resetbools();
olumeButton.resetbools();
	}

	
	public void mouseMoved(MouseEvent e) {
		MusicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		unpauseB.setMouseOver(false);
		olumeButton.setMouseOver(false);
		
		if(isIn(e, MusicButton))
			MusicButton.setMouseOver(true);
		else if(isIn(e, sfxButton)) 
			sfxButton.setMouseOver(true);
		else if(isIn(e, menuB)) 
			menuB.setMouseOver(true);
		else if(isIn(e, replayB)) 
			replayB.setMouseOver(true);
		else if(isIn(e, unpauseB)) 
			unpauseB.setMouseOver(true);
		else if(isIn(e, olumeButton)) 
			olumeButton.setMouseOver(true);
			
	}
	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}


}
