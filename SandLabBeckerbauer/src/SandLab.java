import java.awt.*;
import java.util.*;

public class SandLab{
  public static void main(String[] args){
    SandLab lab = new SandLab(120, 80);
    lab.run();
  }
  
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int LAVA = 4;
  public static final int STEAM = 5;
  public static final int SNOW = 6;
  public static final int DIRT = 7;
  public static final int GRASS = 8; 
  public static final int DEADGRASS = 9;
  public static final int ICE = 10;
  public static final int SMOKE = 11;
  public static final int ASH = 12;
  public static final int SURPRISE = 13;
  public static final int RANDOMIZE = 14;
  public static final Color BLACKEMPTY = new Color(0, 0, 0);
  public static final Color GREYMETAL = new Color(208, 213, 219);
  public static final Color TANSAND = new Color(194, 178, 128);
  public static final Color BLUEWATER = new Color(0, 173, 238);
  public static final Color BLUEICE = new Color(125, 218, 224);
  public static final Color REDLAVA = new Color(207, 16, 32);
  public static final Color WHITESTEAM = new Color(212, 241, 249);
  public static final Color WHITESNOW = new Color(255, 250, 250);
  public static final Color BROWNDIRT = new Color(155, 118, 83);
  public static final Color GREENGRASS = new Color(86, 125, 70);
  public static final Color BROWNDEADGRASS = new Color(112, 84, 62);
  public static final Color GREYSMOKE = new Color(111, 121, 121);
  public static final Color BROWNASH = new Color(52, 47, 37);
  public static int gravity = 1; 
  public static Color[] colors = {BLACKEMPTY,GREYMETAL,TANSAND, BLUEWATER, REDLAVA, WHITESTEAM, WHITESNOW, BROWNDIRT, GREENGRASS, BROWNDEADGRASS, BLUEICE, GREYSMOKE, BROWNASH, BLACKEMPTY, BLACKEMPTY};
  
  //do not add any more fields
  private int[][] grid;
  private SandDisplay display;
  
  public SandLab(int numRows, int numCols){
    String[] names;
    names = new String[15];
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Sand";
    names[WATER] = "Water";
    names[LAVA] = "Lava";
    names[STEAM] = "Steam";
    names[SNOW] = "Snow";
    names[DIRT] = "Dirt";
    names[GRASS] = "Grass";
    names[DEADGRASS] = "Dead Grass";
    names[ICE] = "Ice";
    names[SMOKE] = "Smoke";
    names[ASH] = "Ash";
    names[SURPRISE] = "Surprise Me";
    names[RANDOMIZE] = "Randomize Particles";
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
    this.grid = new int[numRows][numCols];
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool){
	  grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay(){
	  for(int i = 0; i < grid.length; i++) {
		 for(int q = 0; q < grid[i].length; q++) {
			if(inBounds(i, q)) {
				display.setColor(i, q, colors[grid[i][q]]);
			}
		 }
	  }
  }

  //called repeatedly.
  //causes one random particle to maybe do something.
  public void step(){
	  int randRow = (int)(Math.random() * grid.length); 
	  int randCol = (int)(Math.random() * grid[0].length);
	  int particle = grid[randRow][randCol]; 
	  if(particle == SAND) {
		  sand(randRow, randCol);
	  }
	  else if(particle == WATER) {
		  water(randRow, randCol);
	  }
	  else if(particle == LAVA) {
		  lava(randRow, randCol);
	  }
	  else if(particle == STEAM) {
		  steam(randRow, randCol);
	  }
	  else if(particle == SNOW) {
		  snow(randRow, randCol);
	  }
	  else if(particle == DIRT) {
		  dirt(randRow, randCol);
	  }
	  else if(particle == GRASS) {
		  grass(randRow, randCol);
	  }
	  else if(particle == DEADGRASS) {
		  deadGrass(randRow, randCol);
	  }
	  else if(particle == ICE) {
		  ice(randRow, randCol);
	  }
	  else if(particle == SMOKE) {
		  smoke(randRow, randCol);
	  }
	  else if(particle == ASH) {
		  ash(randRow, randCol);
	  }
	  else if(particle == SURPRISE) {
		  int rand = (int)(Math.random() * 14);
		  setCell(randRow, randCol, rand);
	  }
	  else if(particle == RANDOMIZE) {
		  for(int i = 0; i < grid.length; i++) {
			  for(int q = 0; q < grid[i].length; q++) {
				  int rand = (int)(Math.random() * 13) + 1;
				  if(!(grid[i][q] == EMPTY || grid[i][q] == SURPRISE || grid[i][q] == RANDOMIZE)) {
					  grid[i][q] = rand;
				  }
			  }
		  }
	  }
  }
  
  public void ash(int row, int col) {
	  display.setNewSpeed(50);
	  boolean down = isEmpty(row + gravity, col);
	  boolean left = isEmpty(row + gravity, col - 1);
	  boolean right = isEmpty(row + gravity, col + 1);
	  boolean downWater = isWater(row + gravity, col);
	  boolean leftWater = isWater(row + gravity, col - 1);
	  boolean rightWater = isWater(row + gravity, col + 1);
	  boolean downLava = isLava(row + gravity, col);
	  boolean leftLava = isLava(row + gravity, col - 1);
	  boolean rightLava = isLava(row + gravity, col + 1);
	  boolean downSnow = isSnow(row + gravity, col);
	  boolean leftSnow = isSnow(row + gravity, col - 1);
	  boolean rightSnow = isSnow(row + gravity, col + 1);
	  boolean downGrass = isGrass(row + gravity, col);
	  boolean leftGrass = isGrass(row + gravity, col - 1);
	  boolean rightGrass = isGrass(row + gravity, col + 1);
	  boolean downIce = isIce(row + gravity, col);
	  boolean leftIce = isIce(row + gravity, col - 1);
	  boolean rightIce = isIce(row + gravity, col + 1);
	  boolean downDirt = isDirt(row + gravity, col);
	  boolean leftDirt = isDirt(row + gravity, col - 1);
	  boolean rightDirt = isDirt(row + gravity, col + 1);
	  if(left && right) {
		  boolean rand = Math.random() * 1 > 0.5;
		  left = rand ? true : false;
		  right = rand ? false : true;
	  }
	  if(leftWater && rightWater) {
		  boolean randWater = Math.random() * 1 > 0.5;
		  leftWater = randWater ? true : false;
		  rightWater = randWater ? false : true;
	  }
	  if(leftLava && rightLava) {
		  boolean randLava = Math.random() * 1 > 0.5;
		  leftLava = randLava ? true : false;
		  rightLava = randLava ? false : true;
	  }
	  if(leftSnow && rightSnow) {
		  boolean randSnow = Math.random() * 1 > 0.5;
		  leftSnow = randSnow ? true : false;
		  rightSnow = randSnow ? false : true;
	  }
	  if(leftIce && rightIce) {
		  boolean randIce = Math.random() * 1 > 0.5;
		  leftIce = randIce ? true : false;
		  rightIce = randIce ? false : true;
	  }
	  if(leftGrass && rightGrass) {
		  boolean randGrass = Math.random() * 1 > 0.5;
		  leftGrass = randGrass ? true : false;
		  rightGrass = randGrass ? false : true;
	  }
	  if(leftDirt && rightDirt) {
		  boolean randDirt = Math.random() * 1 > 0.5;
		  leftDirt = randDirt ? true : false;
		  rightDirt = randDirt ? false : true;
	  }
	  if(downWater) {
		  setCell(row + gravity, col, ASH);
	  }
	  else if(leftWater) {
		  setCell(row + gravity, col - 1, ASH);
	  }
	  else if(rightWater) {
		  setCell(row + gravity, col + 1, ASH);
	  }
	  else if(downGrass) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftGrass) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightGrass) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  else if(downDirt) {
		  setCell(row + gravity, col, SAND);
	  }
	  else if(leftDirt) {
		  setCell(row + gravity, col - 1, SAND);
	  }
	  else if(rightDirt) {
		  setCell(row + gravity, col + 1, SAND);
	  }
	  else if(downSnow) {
		  setCell(row + gravity, col, WATER);
	  }
	  else if(leftSnow) {
		  setCell(row + gravity, col - 1, WATER);
	  }
	  else if(rightSnow) {
		  setCell(row + gravity, col + 1, WATER);
	  }
	  else if(downIce) {
		  setCell(row + gravity, col, WATER);
	  }
	  else if(leftIce) {
		  setCell(row + gravity, col - 1, WATER);
	  }
	  else if(rightIce) {
		  setCell(row + gravity, col + 1, WATER);
	  }
	  else if(downLava) {
		  setCell(row + gravity, col, ASH);
	  }
	  else if(leftLava) {
		  setCell(row + gravity, col - 1, ASH);
	  }
	  else if(rightLava) {
		  setCell(row + gravity, col + 1, ASH);
	  }
	  else if(down) {
		  setCell(row + gravity, col, ASH);
	  }
	  else if(left) {
		  setCell(row + gravity, col - 1, ASH);
	  }
	  else if(right) {
		  setCell(row + gravity, col + 1, ASH);
	  }
	  if(downWater || leftWater || rightWater) {
		  setCell(row, col, WATER);
	  }
	  else if(downGrass || leftGrass || rightGrass) {
		  setCell(row, col, ASH);
	  }
	  else if(downSnow || leftSnow || rightSnow) {
		  setCell(row, col, ASH);
	  }
	  else if(downIce || leftIce || rightIce) {
		  setCell(row, col, ASH);
	  }
	  else if(downLava || leftLava || rightLava) {
		  setCell(row, col, LAVA);
	  }
	  else if(down || left || right) {
		  setCell(row, col, EMPTY);
	  }
  }
  
  public void smoke(int row, int col) {
	  display.setNewSpeed(15);
	  boolean down = isEmpty(row - gravity, col);
	  boolean left = isEmpty(row, col - 1);
	  boolean right = isEmpty(row, col + 1);
	  boolean downSteam = isSteam(row - gravity, col);
	  boolean leftSteam = isSteam(row, col - 1);
	  boolean rightSteam = isSteam(row, col + 1);
	  if(left && right) {
		  boolean rand = Math.random() * 1 > 0.5;
		  left = rand ? true : false;
		  right = rand ? false : true;
	  }
	  if(leftSteam && rightSteam) {
		  boolean randSteam = Math.random() * 1 > 0.5;
		  leftSteam = randSteam ? true : false;
		  rightSteam = randSteam ? false : true;
	  }
	  if(downSteam) {
		  setCell(row - gravity, col, SMOKE);
	  }
	  else if(leftSteam) {
		  setCell(row, col - 1, SMOKE);
	  }
	  else if(rightSteam) {
		  setCell(row, col + 1, SMOKE);
	  }
	  else if(down) {
		  setCell(row - gravity, col, SMOKE);
	  }
	  else if(left) {
		  setCell(row, col - 1, SMOKE);
	  }
	  else if(right) {
		  setCell(row, col + 1, SMOKE);
	  }
	  if(downSteam || leftSteam || rightSteam) {
		  setCell(row, col, STEAM);
	  }
	  else if(down || left || right) {
		  setCell(row, col, EMPTY);
	  }
  }
  
  public void deadGrass(int row, int col) {
	  display.setNewSpeed(15);
	  boolean down = isEmpty(row + gravity, col);
	  boolean left = isEmpty(row + gravity, col - 1);
	  boolean right = isEmpty(row + gravity, col + 1);
	  boolean downWater = isWater(row + gravity, col);
	  boolean leftWater = isWater(row + gravity, col - 1);
	  boolean rightWater = isWater(row + gravity, col + 1);
	  boolean downLava = isLava(row + gravity, col);
	  boolean leftLava = isLava(row + gravity, col - 1);
	  boolean rightLava = isLava(row + gravity, col + 1);
	  boolean downDirt = isDirt(row + gravity, col);
	  boolean leftDirt = isDirt(row + gravity, col - 1);
	  boolean rightDirt = isDirt(row + gravity, col + 1);
	  if(left && right) {
		  boolean rand = Math.random() * 1 > 0.5;
		  left = rand ? true : false;
		  right = rand ? false : true;
	  }
	  if(leftWater && rightWater) {
		  boolean randWater = Math.random() * 1 > 0.5;
		  leftWater = randWater ? true : false;
		  rightWater = randWater ? false : true;
	  }
	  if(leftLava && rightLava) {
		  boolean randLava = Math.random() * 1 > 0.5;
		  leftLava = randLava ? true : false;
		  rightLava = randLava ? false : true;
	  }
	  if(leftDirt && rightDirt) {
		  boolean randDirt = Math.random() * 1 > 0.5;
		  leftDirt = randDirt ? true : false;
		  rightDirt = randDirt ? false : true;
	  }
	  if(downWater) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftWater) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightWater) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  else if(downDirt) {
		  setCell(row + gravity, col, GRASS);
	  }
	  else if(leftDirt) {
		  setCell(row + gravity, col - 1, GRASS);
	  }
	  else if(rightDirt) {
		  setCell(row + gravity, col + 1, GRASS);
	  }
	  else if(downLava) {
		  setCell(row + gravity, col, ASH);
		  smoke(row, col);
	  }
	  else if(leftLava) {
		  setCell(row + gravity, col - 1, ASH);
		  smoke(row, col);
	  }
	  else if(rightLava) {
		  setCell(row + gravity, col + 1, ASH);
		  smoke(row, col);
	  }
	  else if(down) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(left) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(right) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  if(downWater || leftWater || rightWater) {
		  setCell(row, col, WATER);
	  }
	  else if(downLava || leftLava || rightLava) {
		  setCell(row, col, LAVA);
	  }
	  else if(down || left || right) {
		  setCell(row, col, EMPTY);
	  }
  }
  
  public void grass(int row, int col) {
	  display.setNewSpeed(12);
	  boolean down = isEmpty(row + gravity, col);
	  boolean left = isEmpty(row + gravity, col - 1);
	  boolean right = isEmpty(row + gravity, col + 1);
	  boolean downWater = isWater(row + gravity, col);
	  boolean leftWater = isWater(row + gravity, col - 1);
	  boolean rightWater = isWater(row + gravity, col + 1);
	  boolean downLava = isLava(row + gravity, col);
	  boolean leftLava = isLava(row + gravity, col - 1);
	  boolean rightLava = isLava(row + gravity, col + 1);
	  boolean downDirt = isDirt(row + gravity, col);
	  boolean leftDirt = isDirt(row + gravity, col - 1);
	  boolean rightDirt = isDirt(row + gravity, col + 1);
	  boolean downDeadGrass = isDeadGrass(row + gravity, col);
	  boolean leftDeadGrass = isDeadGrass(row + gravity, col - 1);
	  boolean rightDeadGrass = isDeadGrass(row + gravity, col + 1);
	  boolean downAsh = isAsh(row + gravity, col);
	  boolean leftAsh = isAsh(row + gravity, col - 1);
	  boolean rightAsh = isAsh(row + gravity, col + 1);
	  boolean downSnow = isSnow(row + gravity, col);
	  boolean leftSnow = isSnow(row + gravity, col - 1);
	  boolean rightSnow = isSnow(row + gravity, col + 1);
	  boolean downIce = isIce(row + gravity, col);
	  boolean leftIce = isIce(row + gravity, col - 1);
	  boolean rightIce = isIce(row + gravity, col + 1);
	  boolean downSand = isSand(row + gravity, col);
	  boolean leftSand = isSand(row + gravity, col - 1);
	  boolean rightSand = isSand(row + gravity, col + 1);
	  if(left && right) {
		  boolean rand = Math.random() * 1 > 0.5;
		  left = rand ? true : false;
		  right = rand ? false : true;
	  }
	  if(leftWater && rightWater) {
		  boolean randWater = Math.random() * 1 > 0.5;
		  leftWater = randWater ? true : false;
		  rightWater = randWater ? false : true;
	  }
	  if(leftLava && rightLava) {
		  boolean randLava = Math.random() * 1 > 0.5;
		  leftLava = randLava ? true : false;
		  rightLava = randLava ? false : true;
	  }
	  if(leftDirt && rightDirt) {
		  boolean randDirt = Math.random() * 1 > 0.5;
		  leftDirt = randDirt ? true : false;
		  rightDirt = randDirt ? false : true;
	  }
	  if(leftAsh && rightAsh) {
		  boolean randAsh = Math.random() * 1 > 0.5;
		  leftAsh = randAsh ? true : false;
		  rightAsh = randAsh ? false : true;
	  }
	  if(leftSnow && rightSnow) {
		  boolean randSnow = Math.random() * 1 > 0.5;
		  leftSnow = randSnow ? true : false;
		  rightSnow = randSnow ? false : true;
	  }
	  if(leftIce && rightIce) {
		  boolean randIce = Math.random() * 1 > 0.5;
		  leftIce = randIce ? true : false;
		  rightIce = randIce ? false : true;
	  }
	  if(leftSand && rightSand) {
		  boolean randSand = Math.random() * 1 > 0.5;
		  leftSand = randSand ? true : false;
		  rightSand = randSand ? false : true;
	  }
	  if(downWater) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftWater) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightWater) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  else if(downAsh) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftAsh) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightAsh) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  if(downSnow) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftSnow) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightSnow) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  if(downIce) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftIce) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightIce) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  if(downSand) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftSand) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightSand) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  else if(down) {
		  setCell(row + gravity, col, GRASS);
	  }
	  else if(left) {
		  setCell(row + gravity, col - 1, GRASS);
	  }
	  else if(right) {
		  setCell(row + gravity, col + 1, GRASS);
	  }
	  if(downDirt || leftDirt || rightDirt) {
		  setCell(row, col, GRASS);
	  }
	  if(downDeadGrass || leftDeadGrass || rightDeadGrass) {
		  setCell(row, col, DEADGRASS);
	  }
	  else if(downWater || leftWater || rightWater) {
		  setCell(row, col, WATER);
	  }
	  else if(downAsh || leftAsh || rightAsh) {
		  setCell(row, col, ASH);
	  }
	  else if(downSnow || leftSnow || rightSnow) {
		  setCell(row, col, SNOW);
	  }
	  else if(downIce || leftIce || rightIce) {
		  setCell(row, col, ICE);
	  }
	  else if(downSand || leftSand || rightSand) {
		  setCell(row, col, SAND);
	  }
	  else if(downLava || leftLava || rightLava) {
		  setCell(row, col, DEADGRASS);
		  deadGrass(row, col);
	  }
	  else if(down || left || right) {
		  setCell(row, col, EMPTY);
	  }
  }
  
  public void dirt(int row, int col) {
	  display.setNewSpeed(40);
	  boolean down = isEmpty(row + gravity, col);
	  boolean left = isEmpty(row + gravity, col - 1);
	  boolean right = isEmpty(row + gravity, col + 1);
	  boolean downWater = isWater(row + gravity, col);
	  boolean leftWater = isWater(row + gravity, col - 1);
	  boolean rightWater = isWater(row + gravity, col + 1);
	  boolean downLava = isLava(row + gravity, col);
	  boolean leftLava = isLava(row + gravity, col - 1);
	  boolean rightLava = isLava(row + gravity, col + 1);
	  boolean downAsh = isAsh(row + gravity, col);
	  boolean leftAsh = isAsh(row + gravity, col - 1);
	  boolean rightAsh = isAsh(row + gravity, col + 1);
	  boolean downDeadGrass = isDeadGrass(row + gravity, col);
	  boolean leftDeadGrass = isDeadGrass(row + gravity, col - 1);
	  boolean rightDeadGrass = isDeadGrass(row + gravity, col + 1);
	  if(left && right) {
		  boolean rand = Math.random() * 1 > 0.5;
		  left = rand ? true : false;
		  right = rand ? false : true;
	  }
	  if(leftWater && rightWater) {
		  boolean randWater = Math.random() * 1 > 0.5;
		  leftWater = randWater ? true : false;
		  rightWater = randWater ? false : true;
	  }
	  if(leftLava && rightLava) {
		  boolean randLava = Math.random() * 1 > 0.5;
		  leftLava = randLava ? true : false;
		  rightLava = randLava ? false : true;
	  }	  
	  if(leftAsh && rightAsh) {
		  boolean randAsh = Math.random() * 1 > 0.5;
		  leftAsh = randAsh ? true : false;
		  rightAsh = randAsh ? false : true;
	  }
	  if(leftDeadGrass && rightDeadGrass) {
		  boolean randDeadGrass = Math.random() * 1 > 0.5;
		  leftDeadGrass = randDeadGrass ? true : false;
		  rightDeadGrass = randDeadGrass ? false : true;
	  }
	  if(downWater) {
		  setCell(row + gravity, col, DIRT);
	  }
	  else if(leftWater) {
		  setCell(row + gravity, col - 1, DIRT);
	  }
	  else if(rightWater) {
		  setCell(row + gravity, col + 1, DIRT);
	  }
	  else if(downLava) {
		  setCell(row + gravity, col, SAND);
	  }
	  else if(leftLava) {
		  setCell(row + gravity, col - 1, SAND);
	  }
	  else if(rightLava) {
		  setCell(row + gravity, col + 1, SAND);
	  }
	  else if(downDeadGrass) {
		  setCell(row + gravity, col, GRASS);
	  }
	  else if(leftDeadGrass) {
		  setCell(row + gravity, col - 1, GRASS);
	  }
	  else if(rightDeadGrass) {
		  setCell(row + gravity, col + 1, GRASS);
	  }
	  else if(downAsh) {
		  setCell(row + gravity, col, SAND);
	  }
	  else if(leftAsh) {
		  setCell(row + gravity, col - 1, SAND);
	  }
	  else if(rightAsh) {
		  setCell(row + gravity, col + 1, SAND);
	  }
	  else if(down) {
		  setCell(row + gravity, col, DIRT);
	  }
	  else if(left) {
		  setCell(row + gravity, col - 1, DIRT);
	  }
	  else if(right) {
		  setCell(row + gravity, col + 1, DIRT);
	  }
	  if(downWater || leftWater || rightWater) {
		  setCell(row, col, WATER);
	  }
	  else if(downAsh || leftAsh || rightAsh) {
		  setCell(row, col, ASH);
	  }
	  else if(downLava || leftLava || rightLava) {
		  setCell(row, col, LAVA);
	  }
	  else if(down || left || right) {
		  setCell(row, col, EMPTY);
	  }
  }
  
  public void ice(int row, int col) {
	  setCell(row, col, ICE);
  }
  
  public void snow(int row, int col) {
	  display.setNewSpeed(8);
	  boolean down = isEmpty(row + gravity, col);
	  boolean left = isEmpty(row + gravity, col - 1);
	  boolean right = isEmpty(row + gravity, col + 1);
	  boolean downWater = isWater(row + gravity, col);
	  boolean leftWater = isWater(row, col - 1);
	  boolean rightWater = isWater(row, col + 1);
	  boolean leftDownWater = isWater(row + gravity, col - 1);
	  boolean rightDownWater = isWater(row + gravity, col + 1);
	  boolean downLava = isLava(row + gravity, col);
	  boolean leftLava = isLava(row + gravity, col - 1);
	  boolean rightLava = isLava(row + gravity, col + 1);
	  boolean downAsh = isAsh(row + gravity, col);
	  boolean leftAsh = isAsh(row + gravity, col - 1);
	  boolean rightAsh = isAsh(row + gravity, col + 1);
	  boolean downGrass = isGrass(row + gravity, col);
	  boolean leftGrass = isGrass(row + gravity, col - 1);
	  boolean rightGrass = isGrass(row + gravity, col + 1);
	  boolean leftSnow = isSnow(row + gravity, col - 1);
	  boolean rightSnow = isSnow(row +gravity, col - 1);
	  boolean downDeadGrass = isDeadGrass(row + gravity, col);
	  boolean leftDeadGrass = isDeadGrass(row + gravity, col - 1);
	  boolean rightDeadGrass = isDeadGrass(row + gravity, col + 1);
	  if(left && right) {
		  boolean rand = Math.random() * 1 > 0.5;
		  left = rand ? true : false;
		  right = rand ? false : true;
	  }
	  if(leftGrass && rightGrass) {
		  boolean randGrass = Math.random() * 1 > 0.5;
		  leftGrass = randGrass ? true : false;
		  rightGrass = randGrass ? false : true;
	  }
	  if(leftWater && rightWater) {
		  boolean randWater = Math.random() * 1 > 0.5;
		  leftWater = randWater ? true : false;
		  rightWater = randWater ? false : true;
	  }
	  if(leftDownWater && rightDownWater) {
		  boolean randDownWater = Math.random() * 1 > 0.5;
		  leftDownWater = randDownWater ? true : false;
		  rightDownWater = randDownWater ? false : true;
	  }
	  if(leftLava && rightLava) {
		  boolean randLava = Math.random() * 1 > 0.5;
		  leftLava = randLava ? true : false;
		  rightLava = randLava ? false : true;
	  }
	  if(leftAsh && rightAsh) {
		  boolean randAsh = Math.random() * 1 > 0.5;
		  leftAsh = randAsh ? true : false;
		  rightAsh = randAsh ? false : true;
	  }
	  if(leftSnow && rightSnow) {
		  boolean randSnow = Math.random() * 1 > 0.5;
		  leftSnow = randSnow ? true : false;
		  rightSnow = randSnow ? false : true;
	  }
	  if(downWater) {
		  ice(row + gravity, col);
	  }
	  else if(leftWater) {
		  ice(row, col - 1);
	  }
	  else if(rightWater) {
		  ice(row, col + 1);
	  }
	  else if(leftDownWater) {
		  ice(row + gravity, col - 1);
	  }
	  else if(rightDownWater) {
		  ice(row + gravity, col + 1);
	  }
	  else if(downLava) {
		  setCell(row + gravity, col, WATER);
	  }
	  else if(leftLava) {
		  setCell(row + gravity, col - 1, WATER);
	  }
	  else if(rightLava) {
		  setCell(row + gravity, col + 1, WATER);
	  }
	  else if(downGrass) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftGrass) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightGrass) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  else if(downAsh) {
		  setCell(row + gravity, col, WATER);
	  }
	  else if(leftAsh) {
		  setCell(row + gravity, col - 1, WATER);
	  }
	  else if(rightAsh) {
		  setCell(row + gravity, col + 1, WATER);
	  }
	  else if(down) {
		  setCell(row + gravity, col, SNOW);
	  }
	  else if(left) {
		  setCell(row + gravity, col - 1, SNOW);
	  }
	  else if(right) {
		  setCell(row + gravity, col + 1, SNOW);
	  }
	  else if(leftSnow) {
		  setCell(row, col, SNOW);
	  }
	  else if(rightSnow) {
		  setCell(row, col, SNOW);
	  }
	  if(downWater || leftDownWater || rightDownWater || leftWater || rightWater) {
		  setCell(row, col, SNOW);
	  }
	  else if(downGrass || leftGrass || rightGrass) {
		  setCell(row, col, SNOW);
	  }
	  else if(downDeadGrass || leftDeadGrass || rightDeadGrass) {
		  setCell(row, col, SNOW);
	  }
	  else if(downLava || leftLava || rightLava) {
		  setCell(row, col, LAVA);
	  }
	  else if(downAsh || leftAsh || rightAsh) {
		  setCell(row, col, ASH);
	  }
	  else if(down || left || right) {
		  setCell(row, col, EMPTY);
	  }
  }
  
  public void steam(int row, int col) {
	  display.setNewSpeed(15);
	  boolean down = isEmpty(row - gravity, col);
	  boolean left = isEmpty(row, col - 1);
	  boolean right = isEmpty(row, col + 1);
	  if(left && right) {
		  boolean rand = Math.random() * 1 > 0.5;
		  left = rand ? true : false;
		  right = rand ? false : true;
	  }
	  if(down) {
		  setCell(row - gravity, col, STEAM);
	  }
	  else if(left) {
		  setCell(row, col - 1, STEAM);
	  }
	  else if(right) {
		  setCell(row, col + 1, STEAM);
	  }
	  if(down || left || right) {
		  setCell(row, col, EMPTY);
	  }
  }
  
  public void lava(int row, int col) {
	  display.setNewSpeed(25);
	  boolean down = isEmpty(row + gravity, col);
	  boolean left = isEmpty(row + gravity, col - 1);
	  boolean right = isEmpty(row + gravity, col + 1);
	  boolean downWater = isWater(row + gravity, col);
	  boolean leftWater = isWater(row + gravity, col - 1);
	  boolean rightWater = isWater(row + gravity, col + 1);
	  boolean downSnow = isSnow(row + gravity, col);
	  boolean leftSnow = isSnow(row + gravity, col - 1);
	  boolean rightSnow = isSnow(row + gravity, col + 1);
	  boolean downIce = isIce(row + gravity, col);
	  boolean leftIce = isIce(row + gravity, col - 1);
	  boolean rightIce = isIce(row + gravity, col - 1);
	  boolean downGrass = isGrass(row + gravity, col);
	  boolean leftGrass = isGrass(row + gravity, col - 1);
	  boolean rightGrass = isGrass(row + gravity, col + 1);
	  if(left && right) {
		  boolean rand = Math.random() * 1 > 0.5;
		  left = rand ? true : false;
		  right = rand ? false : true;
	  }
	  if(leftGrass && rightGrass) {
		  boolean randGrass = Math.random() * 1 > 0.5;
		  leftGrass = randGrass ? true : false;
		  rightGrass = randGrass ? false : true;
	  }
	  if(leftWater && rightWater) {
		  boolean randWater = Math.random() * 1 > 0.5;
		  leftWater = randWater ? true : false;
		  rightWater = randWater ? false : true;
	  }
	  if(leftSnow && rightSnow) {
		  boolean randSnow = Math.random() * 1 > 0.5;
		  leftSnow = randSnow ? true : false;
		  rightSnow = randSnow ? false : true;
	  }
	  if(leftIce && rightIce) {
		  boolean randIce = Math.random() * 1 > 0.5;
		  leftIce = randIce ? true : false;
		  rightIce = randIce ? false : true;
	  }
	  if(downSnow) {
		  setCell(row + gravity, col, WATER);
		  water(row + gravity, col);
		  lava(row + gravity, col + 1);
	  }
	  else if(leftSnow) {
		  setCell(row + gravity, col - 1, WATER);
		  water(row + gravity, col - 1);
		  lava(row + gravity, col + 1);
	  }
	  else if(rightSnow) {
		  setCell(row + gravity, col + 1, WATER);
		  water(row + gravity, col + 1);
		  lava(row + gravity, col + 1);
	  }
	  else if(downIce) {
		  setCell(row + gravity, col, WATER);
		  water(row + gravity, col);
		  lava(row + gravity, col);
	  }
	  else if(leftIce) {
		  setCell(row + gravity, col - 1, WATER);
		  water(row + gravity, col - 1);
		  lava(row + gravity, col - 1);
	  }
	  else if(rightIce) {
		  setCell(row + gravity, col + 1, WATER);
		  water(row + gravity, col + 1);
		  lava(row + gravity, col + 1);
	  }
	  else if(downGrass) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftGrass) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightGrass) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  else if(downWater) {
		  setCell(row + gravity, col, SAND);
		  steam(row, col);
	  }
	  else if(leftWater) {
		  setCell(row + gravity, col - 1, SAND);
		  steam(row, col);
	  }
	  else if(rightWater) {
		  setCell(row + gravity, col + 1, SAND);
		  steam(row, col);
	  }
	  else if(down) {
		  setCell(row + gravity, col, LAVA);
	  }
	  else if(left) {
		  setCell(row + gravity, col - 1, LAVA);
	  }
	  else if(right) {
		  setCell(row + gravity, col + 1, LAVA);
	  }
	  if(downSnow || leftSnow || rightSnow || downIce || leftIce || rightIce) {
		  setCell(row, col, LAVA);
	  }
	  else if(downGrass || leftGrass || rightGrass) {
		  setCell(row, col, LAVA);
	  }
	  else if(downWater || leftWater || rightWater) {
		  setCell(row, col, WATER);
	  }
	  else if(down || left || right) {
		  setCell(row, col, EMPTY);
	  }
  }
  
  public void water(int row, int col) {
	  display.setNewSpeed(50);
	  boolean down = isEmpty(row + gravity, col);
	  boolean left = isEmpty(row, col - 1);
	  boolean right = isEmpty(row, col + 1);
	  boolean downLava = isLava(row + gravity, col);
	  boolean leftLava = isLava(row + gravity, col - 1);
	  boolean rightLava = isLava(row + gravity, col + 1);
	  boolean downSnow = isSnow(row + gravity, col);
	  boolean leftSnow = isSnow(row + gravity, col - 1);
	  boolean rightSnow = isSnow(row + gravity, col + 1);
	  boolean downGrass = isGrass(row + gravity, col);
	  boolean leftGrass = isGrass(row + gravity, col - 1);
	  boolean rightGrass = isGrass(row + gravity, col + 1);
	  if(left && right) {
		  boolean rand = Math.random() * 1 > 0.5;
		  left = rand ? true : false;
		  right = rand ? false : true;
	  }
	  if(leftLava && rightLava) {
		  boolean randLava = Math.random() * 1 > 0.5;
		  leftLava = randLava ? true : false;
		  rightLava = randLava ? false : true;
	  }
	  if(leftSnow && rightSnow) {
		  boolean randSnow = Math.random() * 1 > 0.5;
		  leftSnow = randSnow ? true : false;
		  rightSnow = randSnow ? false : true;
	  }
	  if(leftGrass && rightGrass) {
		  boolean randGrass = Math.random() * 1 > 0.5;
		  leftGrass = randGrass ? true : false;
		  rightGrass = randGrass ? false : true;
	  }
	  
	  if(downLava) {
		  setCell(row + gravity, col, SAND);
		  steam(row, col);
	  }
	  else if(leftLava) {
		  setCell(row + gravity, col, SAND);
		  steam(row, col);
	  }
	  else if(rightLava) {
		  setCell(row + gravity, col, SAND);
		  steam(row, col);
	  }
	  else if(downGrass) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftGrass) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightGrass) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  else if(down) {
		  setCell(row + gravity, col, WATER);
	  }
	  else if(left) {
		  setCell(row, col - 1, WATER);
	  }
	  else if(right) {
		  setCell(row, col + 1, WATER);
	  }
	  if(downSnow || leftSnow || rightSnow) {
		  setCell(row, col, ICE);
	  }
	  else if(downGrass || leftGrass || rightGrass) {
		  setCell(row, col, WATER);
	  }
	  else if(downLava || leftLava || rightLava) {
		  setCell(row, col, SAND);
	  }
	  else if(down || left || right) {
		  setCell(row, col, EMPTY);
	  }
  }
  
  public void sand(int row, int col) {
	  display.setNewSpeed(50);
	  boolean down = isEmpty(row + gravity, col);
	  boolean left = isEmpty(row + gravity, col - 1);
	  boolean right = isEmpty(row + gravity, col + 1);
	  boolean downWater = isWater(row + gravity, col);
	  boolean leftWater = isWater(row + gravity, col - 1);
	  boolean rightWater = isWater(row + gravity, col + 1);
	  boolean downLava = isLava(row + gravity, col);
	  boolean leftLava = isLava(row + gravity, col - 1);
	  boolean rightLava = isLava(row + gravity, col + 1);
	  boolean downSnow = isSnow(row + gravity, col);
	  boolean leftSnow = isSnow(row + gravity, col - 1);
	  boolean rightSnow = isSnow(row + gravity, col + 1);
	  boolean downGrass = isGrass(row + gravity, col);
	  boolean leftGrass = isGrass(row + gravity, col - 1);
	  boolean rightGrass = isGrass(row + gravity, col + 1);
	  if(left && right) {
		  boolean rand = Math.random() * 1 > 0.5;
		  left = rand ? true : false;
		  right = rand ? false : true;
	  }
	  if(leftWater && rightWater) {
		  boolean randWater = Math.random() * 1 > 0.5;
		  leftWater = randWater ? true : false;
		  rightWater = randWater ? false : true;
	  }
	  if(leftLava && rightLava) {
		  boolean randLava = Math.random() * 1 > 0.5;
		  leftLava = randLava ? true : false;
		  rightLava = randLava ? false : true;
	  }
	  if(leftSnow && rightSnow) {
		  boolean randSnow = Math.random() * 1 > 0.5;
		  leftSnow = randSnow ? true : false;
		  rightSnow = randSnow ? false : true;
	  }
	  if(leftGrass && rightGrass) {
		  boolean randGrass = Math.random() * 1 > 0.5;
		  leftGrass = randGrass ? true : false;
		  rightGrass = randGrass ? false : true;
	  }
	  if(downWater) {
		  setCell(row + gravity, col, SAND);
	  }
	  else if(leftWater) {
		  setCell(row + gravity, col - 1, SAND);
	  }
	  else if(rightWater) {
		  setCell(row + gravity, col + 1, SAND);
	  }
	  else if(downGrass) {
		  setCell(row + gravity, col, DEADGRASS);
	  }
	  else if(leftGrass) {
		  setCell(row + gravity, col - 1, DEADGRASS);
	  }
	  else if(rightGrass) {
		  setCell(row + gravity, col + 1, DEADGRASS);
	  }
	  else if(downSnow) {
		  setCell(row + gravity, col, SAND);
	  }
	  else if(leftSnow) {
		  setCell(row + gravity, col - 1, SAND);
	  }
	  else if(rightSnow) {
		  setCell(row + gravity, col + 1, SAND);
	  }
	  else if(downLava) {
		  setCell(row + gravity, col, SAND);
	  }
	  else if(leftLava) {
		  setCell(row + gravity, col - 1, SAND);
	  }
	  else if(rightLava) {
		  setCell(row + gravity, col + 1, SAND);
	  }
	  else if(down) {
		  setCell(row + gravity, col, SAND);
	  }
	  else if(left) {
		  setCell(row + gravity, col - 1, SAND);
	  }
	  else if(right) {
		  setCell(row + gravity, col + 1, SAND);
	  }
	  if(downWater || leftWater || rightWater) {
		  setCell(row, col, WATER);
	  }
	  else if(downGrass || leftGrass || rightGrass) {
		  setCell(row, col, SAND);
	  }
	  else if(downSnow || leftSnow || rightSnow) {
		  setCell(row, col, SNOW);
	  }
	  else if(downLava || leftLava || rightLava) {
		  setCell(row, col, LAVA);
	  }
	  else if(down || left || right) {
		  setCell(row, col, EMPTY);
	  }
  }
  
  public boolean isAsh(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == ASH);
  }
  
  public boolean isSmoke(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == SMOKE);
  }
  
  public boolean isSteam(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == STEAM);
  }
  
  public boolean isDeadGrass(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == DEADGRASS);
  }
  
  public boolean isGrass(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == GRASS);
  }
  
  public boolean isDirt(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == DIRT);
  }
  
  public boolean isIce(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == ICE);
  }
  
  public boolean isSnow(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == SNOW);
  }
  
  public boolean isLava(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == LAVA);
  }
  
  public boolean isSand(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == SAND);
  }
  
  public boolean isWater(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == WATER);
  }
  
  public boolean inBounds(int row, int col) {
	  return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
  }
  
  public boolean isEmpty(int row, int col) {
	  return inBounds(row, col) && (grid[row][col] == EMPTY);
  }
  
  public void setCell(int row, int col, int element) {
	  if(inBounds(row, col)) {
		  grid[row][col] = element;
	  }
  }
  
  //do not modify
  public void run(){
    while (true){
      gravity = display.getGravity();
      for (int i = 0; i < display.getSpeed(); i++)
        step();
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }
}
