package utils;

import java.awt.geom.Rectangle2D;

import main.Game;

public class Helpmethods {

	public static boolean canMovehere(double x,double y,double width,double height,int[][] lvldata) {
		if(!isSolid(x, y, lvldata))
if(!isSolid(x+width, y+height, lvldata))
	if(!isSolid(x+width, y, lvldata))
		if(!isSolid(x, y+height, lvldata))
			return true;
		
		return false;
	}
	private static boolean  isSolid(double x, double y,int[][]lvlData ){
		if(x<0 || x>= Game.GAME_WIDTH)
			return true;
		if(y<0 || y>=Game.GAME_HEIGHT)
			return true;
		double xIndex =x / Game.TILES_SIZE;
		double yIndex = y / Game.TILES_SIZE;
		
		int value = lvlData[(int)yIndex][(int)xIndex];
		if(value>=48 || value<=0 || value !=11) 
			return true;
			
	
			return false;
	}
	public static double getentityXPostoWall(Rectangle2D.Double hitbox,double xspeed) {
		int currentTile = (int)(hitbox.x/Game.TILES_SIZE);
		if(xspeed>0) {
			//right
			int tilexpos = currentTile*Game.TILES_SIZE;
			int xOffset =(int)(Game.TILES_SIZE-hitbox.width);
			return tilexpos+xOffset-1;
		}
		else {
			//left
			return currentTile*Game.TILES_SIZE;
		}
	}
	public static double getEntityYPosUnderRoofOrAboveFlore(Rectangle2D.Double hitbox,double airspeed) {
		int currentTile = (int)(hitbox.y/Game.TILES_SIZE);
		
		if(airspeed>0) {
			//falling + touching floor
			int tileypos = currentTile*Game.TILES_SIZE;
			int yOffset = (int)(Game.TILES_SIZE- hitbox.height);
			return tileypos+ yOffset-1;
		}else {
			//jumping
			return currentTile*Game.TILES_SIZE;
		}
	}
	public static boolean IsEntityOnFloor(Rectangle2D.Double hitbox, int[][] lvldata) {
		// Check the pixel below bottomleft and bottomright
		if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvldata))
			if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvldata))
				return false;

		return true;

	}
}
