package ui;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;
import static utils.Constant.UI.OlumeButon.*;

public class OlumeButton extends PauseButton{

	private BufferedImage[] imgs;
	private BufferedImage slider;
	private int index =0;
	private boolean mouseOver, mousePressed;
	private int buttonX,minX,maxX;
	
	public OlumeButton(int x, int y, int width, int height) {
		super(x+width/2, y, OLUME_WIDTH, height);
		bounds.x -= OLUME_WIDTH/2;
		buttonX = x+ width/2;
		this.x =x;
		this.width = width;
		minX =x+ OLUME_WIDTH/2;
		maxX = x+width -OLUME_WIDTH/2;
		loadimgs();
	}
	private void loadimgs() {
		BufferedImage temp = LoadSave.GetSpriteImg(LoadSave.OLUME_BUTTONS);
		imgs = new BufferedImage[3];
		for(int i=0;i<imgs.length;i++) {
			imgs[i] = temp.getSubimage(i*OLUME_DEFAULT_WIDTH, 0, OLUME_DEFAULT_WIDTH, OLUME_DEFAULT_HEIGHT);
		}
		slider = temp.getSubimage(3*OLUME_DEFAULT_WIDTH, 0, SLIDER_DEFAULT_WIDTH, OLUME_DEFAULT_HEIGHT);
		
	}

	public void update() {
		index=0;
		if(mouseOver)
			index =1;
		if(mousePressed)
			index = 2;
	}
	public void draw(Graphics g) {
		g.drawImage(slider, x, y, width, height, null);
		g.drawImage(imgs[index], buttonX- OLUME_WIDTH/2, y, OLUME_WIDTH, height, null);
	}

	public void changeX(int x) {
		if(x <minX)
			buttonX =minX;
		else if (x> maxX)
			buttonX = maxX;
		else
			buttonX =x;
		
		bounds.x = buttonX- OLUME_WIDTH/2;
	}
	
	public void resetbools() {
		mouseOver = false;
		mousePressed = false;
	}

	public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

}
