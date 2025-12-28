package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class LevelManager {
private Game game;
private BufferedImage[] levelsprite ;
private Level levelOne;
	
	public LevelManager(Game game) {
		this.game = game;
//		
importOutsideSprite();
levelOne = new Level(LoadSave.GetLevelData());
	}
	private void importOutsideSprite() {
		BufferedImage img = LoadSave.GetSpriteImg(LoadSave.LEVEL_SPRITE);
		levelsprite = new BufferedImage[48];
		for(int j=0;j<4;j++) {
			for(int i=0;i<12;i++) {
				int index = j*12+ i;
				levelsprite[index] = img.getSubimage(i*32, j*32, 32, 32);
			}
		}
	}
	public  void draw(Graphics g) {
		for(int j=0;j< game.TILES_IN_HEIGHT;j++) {
			for(int i=0;i<game.TILES_IN_WIDTH;i++) {
				int index = levelOne.getspriteindex(i, j);
				g.drawImage(levelsprite[index], game.TILES_SIZE*i, game.TILES_SIZE*j, game.TILES_SIZE, game.TILES_SIZE, null);
			}
		}
	}
	public void update() {
		
	}
	public Level getcurrentlevel() {
		return levelOne;
	}
}
