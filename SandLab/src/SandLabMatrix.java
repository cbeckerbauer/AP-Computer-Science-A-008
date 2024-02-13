
public class SandLabMatrix {

	private Array<Array<Element>>; //matrix
	private Array<Array<Chunk>>; import java.awt.*;
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
	  public static final Color BLACKEMPTY = new Color(0, 0, 0);
	  public static final Color GREYMETAL = new Color(208, 213, 219);
	  public static final Color TANSAND = new Color(194, 178, 128);
	  public static final Color BLUEWATER = new Color(0, 173, 238);
	  public static final Color REDLAVA = new Color(207, 16, 32);
	  public static final Color WHITESTEAM = new Color(212, 241, 249);
	  public static final Color WHITESNOW = new Color(255, 250, 250);
	  public static final Color BROWNDIRT = new Color(155, 118, 83);
	  public static final Color GREENGRASS = new Color(86, 125, 70);
	  public static final Color BROWNDEADGRASS = new Color(112, 84, 62);
	  public static int gravity = 1; 
	  public static boolean iMovedLeft = false;
	  public static boolean iMovedRight = false;
	  public static boolean iAmDone = false;
	  public static Color[] colors = {BLACKEMPTY,GREYMETAL,TANSAND, BLUEWATER, REDLAVA, WHITESTEAM, WHITESNOW, BROWNDIRT, GREENGRASS, BROWNDEADGRASS};
	  
	  //do not add any more fields
	  private int[][] grid;
	  private SandDisplay display;
	  
	  public SandLab(int numRows, int numCols){
	    String[] names;
	    names = new String[10];
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
				display.setColor(i, q, colors[grid[i][q]]);
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
			  if(iAmDone == false) {
				  sand(randRow, randCol);
				  System.out.println(iAmDone);
			  	  if(iMovedLeft == true && iMovedRight == true) {
				  	iAmDone = true;
			  	  }
			  	  if(iAmDone == true) {
				  	iAmDone = false;
			  	  }
			  }
		  }
	  }
	  
	  public void sand(int row, int col) {
		  if(getBelow(row, col) == 0 || getBelow(row, col) == 3) {
			  grid[row][col] = getBelow(row, col);
			  grid[row + gravity][col] = 2;
		  }
		  else if(getBelow(row, col) == 2) {
			  grid[row][col] = getBelow(row, col);
			  grid[row][col] = 2;
		  }
		  else if(getBelow(row, col) == -1) { 
			  if(iMovedLeft == false) {
				  if(getBelowLeft(row, col - 1) == 0 || getBelowLeft(row, col - 1) == 3) {
					  grid[row][col - 1] = getBelowLeft(row, col);
					  grid[row][col - 1] = 2;
					  iMovedLeft = true;
				  }
			  }
			  else {
				  if(getBelowRight(row, col + 1) == 0 || getBelowRight(row, col + 1) == 3) {
					  grid[row][col + 1] = getBelowRight(row, col);
					  grid[row][col + 1] = 2;
					  iMovedRight = true;
				  }
			  }
		  }
	  }
	  
	  public int getBelow(int row, int col) {
		  if(row + gravity >= grid.length || row + gravity < 0) {
			  return -1; // this is not a place this particle can move
		  }
		  return grid[row + gravity][col]; // this returns the particle below or above if inverted. 
	  }
	  
	  public int getBelowRight(int row, int col) {
		  if(getBelow(row, col) == -1) {
			  if(!(col < grid[col].length && col > 0)) {
				  return -1;
			  }
		  }
		  return grid[row][col];
	  }
	  
	  public int getBelowLeft(int row, int col) {
		  if(getBelow(row, col) == -1) {
			  if(!(col < grid[col].length && col > 0)) {
				  return -1;
			  }
		  }
		  return grid[row][col];
	  }
	  
	//  public void sand(int row, int col) {
//		  int turn = 0;
//		  if(getBelow(row, col) == 0 || getBelow(row, col) == 3) {
//			  grid[row][col] = getBelow(row, col);
//			  grid[row + gravity][col] = 2;
//		  }
//		  else if(getBelow(row, col) == -1){
//			  
//			  
//			  if(getBelowLeft(row, col) == -1) {
//				  System.out.println("Left: No Place aka " + getBelowLeft(row, col));
//			  }
//			  else {
//				  if(getBelowLeft(row, col) == 0) {
//					  System.out.println("I HAVE RUNNER");
//					  if(turn == 0) {
//						  System.out.println("I HAVE RUN");
//						  grid[row][col] = getBelowLeft(row, col);
//						  grid[row][col - 1] = 2;
//						  System.out.println("I moved left.");
//						  turn = 1;
//						  System.out.println("Left: " + getBelowLeft(row, col) + " turn is " + turn);
//					  }
//				  }
//				  else if(getBelowRight(row, col) == -1) {
//					  System.out.println("Right: No Place aka " + getBelowRight(row, col));
//				  }
//				  else {
//					  if(getBelowRight(row, col) == 0 & turn == 1) {
//						  grid[row][col] = getBelowRight(row, col);
//						  grid[row][col + 1] = 2;
//						  System.out.println("I moved right.");
//						  turn = 2;
//					  }
//					  System.out.println("Right: " + getBelowRight(row, col) + " turn is " + turn);
//				  }
//				  if(turn == 2) {
//					  System.out.println("I am done."  + " Turn is " + turn);
//				  }
////		  else if(getBelow(row,col) == 0 || getBelow(row, col) == 3) { //the spot is in bounds and empty
////			  grid[row][col] = getBelow(row, col); // set our current spot empty or water 
////			  grid[row + gravity][col] = 2; 
////		  }
	//  }
//		  }
	//}

	  
	  //do not modify
	  public void run(){
	    while (true){
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

	
}
