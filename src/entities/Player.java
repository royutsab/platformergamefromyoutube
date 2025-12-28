package entities;



import static utils.Constant.playerconstants.GetSpiritAmount;
import static utils.Constant.playerconstants.*;
import static utils.Helpmethods.*;
//import static utils.Constant.playerconstants.RUNNING;
//import static utils.Constant.playerconstants.ATTACK_1;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class Player extends Entity{
	
	private BufferedImage[][] Animations;
private int aniTick,aniIndex,aniSpeed= 25;
	
	private int playerAction = IDLE;
//	private int playerDir =-1;
	 private boolean moving = false,attacking = false; 
	 private boolean right,left,up,down,jump;
	 private float playerSpeed =1f * Game.SCALE;
	 private int[][] lvldata;
	 private double xDrawOffset = 21 * Game.SCALE;
	 private double yDrawOffset =4* Game.SCALE;
	 
	 //jumping /gravity
	 private double airSpeed =0;
	 private double gravity = 0.04*Game.SCALE;
	 private double jumpSpeed = -2.25f*Game.SCALE;
	 private double fallSpeedAfterColision = 0.5*Game.SCALE;
	 private boolean inair = false;
	
	public Player(double d, double e,int width, int height) {
		super(d, e, width, height);
		loadAnimation();
		inithitbox(d, e, (int)(20*Game.SCALE), (int)(27*Game.SCALE));
	}


public void render(Graphics g) {
	g.drawImage(Animations[playerAction][aniIndex], (int)(hitbox.x- xDrawOffset),(int)(hitbox.y-yDrawOffset), width,height, null);
	drawhitbox(g);
}


 private void updateanimationtick() {
	 aniTick++;
	 if(aniTick>= aniSpeed) {
	 	aniTick=0;
	 	aniIndex++;
	 	if(aniIndex>= GetSpiritAmount(playerAction)) {
	 		aniIndex=0;
	 		attacking = false;
	 	}
	 		
	 }
	 	
	 }
 private void setAnimation() {
	 int startAni = playerAction;
	 
		if(moving)
			playerAction = RUNNING;
		else 
			playerAction = IDLE;
		if (inair) {
			if (airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALLING;
		}
		if(attacking) 
			playerAction = ATTACK_1;
		if(startAni != playerAction) {
			resetAniTick();
		}
		
 }
 private void resetAniTick() {
	aniTick =0;
	aniIndex =0;
	
}


private void updatePositon() {
		moving = false;
		
		if(jump)
			jump();
		if(!left && !right && !inair)
			return;
		
		double xspeed =0;
		
	if(left ) 
	xspeed -=playerSpeed;	
	if(right) 
		xspeed +=playerSpeed;	
	
	if (!inair)
		if(!IsEntityOnFloor(hitbox, lvldata))
			inair = true;
	if(inair) {
		if(canMovehere(hitbox.x, hitbox.y+airSpeed, hitbox.width, hitbox.height, lvldata)) {
			hitbox.y+=airSpeed;
		airSpeed +=gravity;
		updatexPos(xspeed);
		}else {
			hitbox.y = getEntityYPosUnderRoofOrAboveFlore(hitbox,airSpeed);
			if(airSpeed>0) {
				resetInair();
			}else {
				airSpeed = fallSpeedAfterColision;
				updatexPos(xspeed);
			}
		}
	}else 
		updatexPos(xspeed);
	
	moving=true;
	
	
	
	}
private void jump() {
	if(inair)
		return;
	inair = true;
	airSpeed = jumpSpeed;
	
}


private void resetInair() {
	inair=false;
	airSpeed =0;
	
	
}


private void updatexPos(double xspeed) {
	if(canMovehere(hitbox.x+xspeed, hitbox.y, hitbox.width, hitbox.height, lvldata)) {
	hitbox.x +=xspeed;
	}
	else {
		hitbox.x = getentityXPostoWall(hitbox,xspeed);
	}
	
}


private void loadAnimation() {
	
		 BufferedImage img  = LoadSave.GetSpriteImg(LoadSave.PLAYER_SPRITE);
		Animations = new BufferedImage[9][6];
		
		for(int j=0;j<Animations.length;j++) {
		for(int i =0;i<Animations[j].length;i++) {
			Animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
		}
		}
		
		
	} 
public void loadlvldata(int[][] lvldata) {
	this.lvldata = lvldata;
	if(!IsEntityOnFloor(hitbox, lvldata))
		inair = true;
}

public void update() {

	updatePositon();
	//updatehitbox();
	updateanimationtick();
	setAnimation();
	
}

public void resetPlayerDirection() {
	left=false;
	right = false;
	down = false;
	up = false;
}
public void setAttacking(boolean attacking) {
	this.attacking = attacking;
}

public boolean isRight() {
	return right;
}


public void setRight(boolean right) {
	this.right = right;
}


public boolean isLeft() {
	return left;
}


public void setLeft(boolean left) {
	this.left = left;
}


public boolean isUp() {
	return up;
}


public void setUp(boolean up) {
	this.up = up;
}


public boolean isDown() {
	return down;
}


public void setDown(boolean down) {
	this.down = down;
}
public void setJump(boolean jump) {
	this.jump = jump;
}
	
}
