package entities;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entity {

	protected float x,y;
	protected int width,height;
	protected Rectangle2D.Double hitbox;
	public Entity(double d,double e,int width,int height) {
		this.x = (float) d;
		this.y = (float) e;
		this.width = width;
		this.height = height;
		
	}
	protected void drawhitbox(Graphics g) {
		//for debugging hitbox
		g.setColor(Color.PINK);
		g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
	}
	protected void inithitbox(double x,double y,int width,int height) {
		hitbox = new Rectangle2D.Double((int)x,(int)y,width,height);
		
	}
	public Rectangle2D.Double gethitbox() {
		return hitbox;
	}
	
}
