import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture. This class inherits from SimplePicture and
 * allows the student to add functionality to the Picture class.
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture {

	private Pixel[][] grid;

	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments
	 */
	public Picture() {
		/*
		 * not needed but use it to show students the implicit call to super() child
		 * constructors always call a parent constructor
		 */
		super();
		grid = this.getPixels2D();
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
		grid = this.getPixels2D();
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height the height of the desired picture
	 * @param width  the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
		grid = this.getPixels2D();
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
		grid = this.getPixels2D();
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
		grid = this.getPixels2D();
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * 
	 * @return a string with information about the picture such as fileName, height
	 *         and width.
	 */
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(0);
			}
		}
	}

	/**
	 * Method that mirrors the picture around a vertical mirror in the center of the
	 * picture from left to right
	 */
	public void mirrorVertical() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple() {
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++) {
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++) {

				leftPixel = pixels[row][col];
				rightPixel = pixels[row][mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	/**
	 * copy from the passed fromPic to the specified startRow and startCol in the
	 * current picture
	 * 
	 * @param fromPic  the picture to copy from
	 * @param startRow the start row to copy to
	 * @param startCol the start col to copy to
	 */
	public void copy(Picture fromPic, int startRow, int startCol) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; fromRow < fromPixels.length
				&& toRow < toPixels.length; fromRow++, toRow++) {
			for (int fromCol = 0, toCol = startCol; fromCol < fromPixels[0].length
					&& toCol < toPixels[0].length; fromCol++, toCol++) {
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}
	}

	public void copy(Picture fromPic, int row, int col, int startRow, int startCol, int endRow, int endCol) {
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int r = startRow; r < endRow; r++) {
			for (int c = startCol; c < endCol; c++) {
				toPixels[row + r][col + c].setColor(fromPixels[r][c].getColor());
			}
		}
	}

	/** Method to create a collage of several pictures */
	public void createCollage() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 0, 0);
		this.copy(flower2, 100, 0);
		this.copy(flower1, 200, 0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue, 300, 0);
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}

	/**
	 * Method to show large changes in color
	 * 
	 * @param edgeDist the distance for finding edges
	 */
	public void edgeDetection(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
	}

	public void edgeDetection2(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel bottomPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 0; row < pixels.length - 1; row++) {
			for (int col = 0; col < pixels[0].length - 1; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 1];
				bottomPixel = pixels[row + 1][col];
				rightColor = rightPixel.getColor();
				if (leftPixel.colorDistance(rightColor) > edgeDist || bottomPixel.colorDistance(rightColor) > edgeDist)
					leftPixel.setColor(Color.BLACK);
				else
					leftPixel.setColor(Color.WHITE);
			}
		}
	}

	public void edgeDetection3(int edgeDist) {
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 1; row < pixels.length - 1; row++) {
			for (int col = 1; col < pixels[0].length - 1; col++) {
				Pixel[][] near = getNear(row, col);
				rightColor = pixels[row][col].getColor();
				for (Pixel[] pArr : near) {
					for (Pixel p : pArr) {
						if (p.colorDistance(rightColor) > edgeDist)
							pixels[row][col].setColor(Color.BLACK);
						else
							pixels[row][col].setColor(Color.WHITE);
					}
				}
			}
		}
	}
	
	public void edgeDetection4(double amplifier) {
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 1; row < pixels.length - 1; row++) {
			for (int col = 1; col < pixels[0].length - 1; col++) {
				Pixel[][] near = getNear(row, col);
				rightColor = pixels[row][col].getColor();
				for (Pixel[] pArr : near) {
					for (Pixel p : pArr) {
						int average1 = (int) (rightColor.getRed() + rightColor.getGreen() + rightColor.getBlue()) / 3;
						int average2 = (int) (p.getRed() + p.getGreen() + p.getBlue()) / 3;
						//int distance = (int) ((255 - (Math.abs(average1-average2) * amplifier));
						int difference = Math.abs(average1-average2);
						double adjAmp = amplifier/(double)difference;
						int distance = (int) (255.0 - (difference*amplifier));
						if(distance >= 0) {
							pixels[row][col].setColor(new Color(distance, distance, distance));
						}else {
							pixels[row][col].setColor(new Color(0, 0, 0));
							//System.out.println("COLOR OVERFLOW: " + distance);
						}
					}
				}
			}
		}
	}
	

	public void smooth() {
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 1; row < pixels.length - 1; row++) {
			for (int col = 1; col < pixels[0].length - 1; col++) {
				Pixel[][] near = getNear(row, col);
				float total = 0f;
				for (Pixel[] pArr : near) {
					for (Pixel p : pArr) {
						total += (float) p.getAverage() / 255f;
					}
				}
				total /= 9;
				for (Pixel[] pArr : near) {
					for (Pixel p : pArr) {
						p.setColor(new Color(total, total, total));
					}
				}
			}
		}
	}
	
	public void smooth2(double amp) {
		if(amp > 1) {
			amp = 1;
		}
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int row = 1; row < pixels.length - 1; row++) {
			for (int col = 1; col < pixels[0].length - 1; col++) {
				Pixel[][] near = getNear(row, col);
				float total = 0f;
				for (Pixel[] pArr : near) {
					for (Pixel p : pArr) {
						total += (float) p.getAverage() / 255f;
					}
				}
				total /= 9;
				for (Pixel[] pArr : near) {
					for (Pixel p : pArr) {
						float local = (float)((((float)p.getAverage()/255) * (1-amp)) + total*amp);
						if(local <= 1 && local >= 0)
							p.setColor(new Color(local, local, local));
						else {
							System.out.println("LOCAL: " + local);
						}
					}
				}
			}
		}
	}

	public void smoothColor() {
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 1; row < pixels.length - 1; row++) {
			for (int col = 1; col < pixels[0].length - 1; col++) {
				Pixel[][] near = getNear(row, col);
				double totalR = 0.0;
				double totalG = 0.0;
				double totalB = 0.0;
				for (Pixel[] pArr : near) {
					for (Pixel p : pArr) {
						totalR += (double) p.getRed() / 255.0;
						totalG += (double) p.getGreen() / 255.0;
						totalB += (double) p.getBlue() / 255.0;
					}
				}
				totalR /= 9;
				totalG /= 9;
				totalB /= 9;
				for (Pixel[] pArr : near) {
					for (Pixel p : pArr) {
						p.setColor(new Color((float) totalR, (float) totalG, (float) totalB));
					}
				}
			}
		}
	}

	public void smoothKeepEdge() {
		Pixel[][] pixels = this.getPixels2D();
		for (int row = 1; row < pixels.length - 1; row++) {
			for (int col = 1; col < pixels[0].length - 1; col++) {
				Pixel[][] near = getNear(row, col);
				double totalR = 0.0;
				double totalG = 0.0;
				double totalB = 0.0;
				for (Pixel[] pArr : near) {
					for (Pixel p : pArr) {
						if (p.colorDistance(pixels[row][col].getColor()) < 5) {
							totalR += (double) p.getRed() / 255.0;
							totalG += (double) p.getGreen() / 255.0;
							totalB += (double) p.getBlue() / 255.0;
						}
					}
				}
				totalR /= 9;
				totalG /= 9;
				totalB /= 9;
				for (Pixel[] pArr : near) {
					for (Pixel p : pArr) {
						p.setColor(new Color((float) totalR, (float) totalG, (float) totalB));
					}
				}
			}
		}
	}

	public Pixel[][] getNear(int row, int col) {
		// System.out.println(grid.length);
		Pixel[][] tempZone = new Pixel[3][3];
		for (int r = -1; r < tempZone.length - 1; r++) {
			for (int c = -1; c < tempZone[0].length - 1; c++) {
				tempZone[r + 1][c + 1] = grid[row + r][col + c];
			}
		}
		return tempZone;
	}

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}

	public void onlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
				pixelObj.setGreen(0);
			}
		}

	}

	public void negate() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(255 - pixelObj.getRed());
				pixelObj.setGreen(255 - pixelObj.getGreen());
				pixelObj.setBlue(255 - pixelObj.getBlue());
			}
		}

	}

	public void grayscale() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel p : rowArray) {
				int average = (int) (p.getRed() + p.getGreen() + p.getBlue()) / 3;
				p.setRed(average);
				p.setGreen(average);
				p.setBlue(average);
			}
		}

	}

	public void onlyRed(int inc) {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed((pixelObj.getRed() + inc) % 255);
				pixelObj.setBlue(Math.abs(pixelObj.getBlue() - inc / 2));
				pixelObj.setGreen(Math.abs(pixelObj.getGreen() - inc / 2));

			}
		}

	}

	public void boost() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed((pixelObj.getRed() + 50) % 255);
				pixelObj.setGreen((pixelObj.getGreen() + 10) % 255);
				pixelObj.setBlue(Math.abs(pixelObj.getBlue() - 25));

			}
		}

	}

	public void highlightBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[0].length; c++) {
				int average = (int) (pixels[r][c].getRed() + pixels[r][c].getGreen() + pixels[r][c].getBlue()) / 3;
				if (pixels[r][c].getGreen() < pixels[r][c].getBlue() && pixels[r][c].getRed() < pixels[r][c].getBlue()
						&& average < 150) {

				} else if (c > 500 && c < 550 && r > 202 && r < 276) {
					if (pixels[r][c].getBlue() < 153 && pixels[r][c].getGreen() < 160) {
						pixels[r][c].setColor(new Color(17, 169, 177));
					} else {
						pixels[r][c].setRed(pixels[r][c].getRed() + 100);
						pixels[r][c].setBlue(pixels[r][c].getBlue() - 30);
						pixels[r][c].setGreen(pixels[r][c].getGreen() - 30);
					}
				} else {
					pixels[r][c].setRed(pixels[r][c].getRed() + 100);
					pixels[r][c].setBlue(pixels[r][c].getBlue() - 30);
					pixels[r][c].setGreen(pixels[r][c].getGreen() - 30);
				}
			}
		}

	}

	public void mirrorHorizontal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		for (int col = 0; col < pixels[0].length; col++) {
			for (int row = 0; row < height / 2; row++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[height - 1 - row][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}

	}

	public void mirrorVerticalRightToLeft() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 0; row < pixels.length; row++) {
			for (int col = 0; col < width / 2; col++) {
				rightPixel = pixels[row][col];
				leftPixel = pixels[row][width - 1 - col];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void mirrorHorizontalBotToTop() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		for (int col = 0; col < pixels[0].length; col++) {
			for (int row = 0; row < height / 2; row++) {
				bottomPixel = pixels[row][col];
				topPixel = pixels[height - 1 - row][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}

	}

	public void mirrorDiagonal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int height = pixels.length;
		// int width = pixels[0].length;
		for (int col = 0; col < pixels[0].length && col < height; col++) {
			for (int row = 0; row < height && row < pixels[0].length; row++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[col][row];
				bottomPixel.setColor(topPixel.getColor());
			}
		}

	}

	public void mirrorArms() {// between 160-200 row, and 100-300 col, and between 170-200, 240-300
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		for (int col = 100; col < 170; col++) {
			for (int row = 160; row < 180; row++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[200 - 1 - (row - 160)][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
		for (int col = 240; col < 300; col++) {
			for (int row = 175; row < 205; row++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[205 - 1 - (row - 175)][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}

	public void collage2() {
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1, 300, 0, 0, 0, flower1.getHeight() / 2, flower1.getWidth() / 2);
		this.copy(flower2, 100, 0);
		flower1.edgeDetection2(50);
		flower2.onlyBlue();
		this.copy(flower1, 400, 0);
		this.copy(flower2, 500, 0);
		this.mirrorDiagonal();
		this.write("collage.jpg");
	}

	public void brighten(int inc) {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed((pixelObj.getRed() + inc) % 255);
				pixelObj.setBlue((pixelObj.getBlue() + inc) % 255);
				pixelObj.setGreen((pixelObj.getGreen() + inc) % 255);

			}
		}

	}

	public void brightenBlue(int inc) {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue((pixelObj.getBlue() + inc) % 255);
			}
		}
	}

} // this } is the end of class Picture, put all new methods before this, oops, I
	// should really start reading these
