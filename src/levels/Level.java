package levels;

public class Level {
	private int[][] lvldata;
	
 public Level(int[][] lvldata) {
	this.lvldata = lvldata;
}
 
 public int getspriteindex(int x, int y) {
	 return lvldata[y][x];
 }
 public int[][] getlvldata(){
	 return lvldata;
 }
}
