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
	}

	/**
	 * Constructor that takes a file name and creates the picture
	 * 
	 * @param fileName
	 *            the name of the file to create the picture from
	 */
	public Picture(String fileName) {
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * 
	 * @param height
	 *            the height of the desired picture
	 * @param width
	 *            the width of the desired picture
	 */
	public Picture(int height, int width) {
		// let the parent class handle this width and height
		super(width, height);
	}

	/**
	 * Constructor that takes a picture and creates a copy of that picture
	 * 
	 * @param copyPicture
	 *            the picture to copy
	 */
	public Picture(Picture copyPicture) {
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * 
	 * @param image
	 *            the buffered image to use
	 */
	public Picture(BufferedImage image) {
		super(image);
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
	 * @param fromPic
	 *            the picture to copy from
	 * @param startRow
	 *            the start row to copy to
	 * @param startCol
	 *            the start col to copy to
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
	 * @param edgeDist
	 *            the distance for finding edges
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

	/*
	 * Main method for testing - each class in Java can have a main method
	 */
	public static void main(String[] args) {
		Picture greenScreen = new Picture("background.png");
		  Picture newBack = new Picture("lightsaber.png");
		  newBack.moveGreen(greenScreen);
		  greenScreen.gradient();
		  greenScreen.explore();
	}

	public void keepOnlyBlue() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
				pixelObj.setGreen(0);
			}
		}
	}

	public void keepOnlyGreen() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setRed(0);
				pixelObj.setBlue(0);
			}
		}
	}

	public void keepOnlyRed() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setGreen(0);
				pixelObj.setBlue(0);
			}
		}
	}

	public void negate() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				pixelObj.setBlue(255 - pixelObj.getBlue());
				pixelObj.setGreen(255 - pixelObj.getGreen());
				pixelObj.setRed(255 - pixelObj.getRed());
			}
		}
	}

	public void grayScale() {
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels) {
			for (Pixel pixelObj : rowArray) {
				int scale = (pixelObj.getBlue() + pixelObj.getRed() + pixelObj.getGreen()) / 3;
				pixelObj.setBlue(scale);
				pixelObj.setGreen(scale);
				pixelObj.setRed(scale);
			}
		}
	}

	public void fixUnderwater(int edgeDist) {
		Pixel leftPixel = null;
		Pixel[][] pixels = this.getPixels2D();

		for (int x = 500; x < 550; x++) {
			for (int y = 218; y < 271; y++) {
				leftPixel = pixels[y][x];
				if (leftPixel.getRed() < 23 && leftPixel.getGreen() < 160)
					leftPixel.setBlue(leftPixel.getBlue() + 50);
				else
					leftPixel.setBlue(leftPixel.getBlue() - 20);

			}
		}

		for (int col = 0; col < pixels[0].length - 1; col++) {
			for (int row = 0; row < 200; row++) {
				leftPixel = pixels[row][col];
				if (leftPixel.getRed() < 25)
					leftPixel.setBlue(leftPixel.getBlue() + 50);
				else
					leftPixel.setBlue(leftPixel.getBlue() - 20);
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

	public void mirrorHorizontal() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		int width = pixels[0].length;
		for (int i = 0; i < pixels.length / 2; i++) {
			for (int j = 0; j < width; j++) {
				topPixel = pixels[i][j];
				bottomPixel = pixels[pixels.length - 1 - i][j];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}

	public void mirrorDiagnol() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel firstPixel = null;
		Pixel secondPixel = null;
		for (int row = 0; row < row + 1 && row < pixels.length; row++) {
			for (int col = 0; col < col + 1 && col < pixels.length; col++) {
				firstPixel = pixels[row][col];
				secondPixel = pixels[col][row];
				secondPixel.setColor(firstPixel.getColor());
			}
		}
	}

	public void mirrorArms() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel topPixel = null;
		Pixel bottomPixel = null;
		for (int row = 156; row < 198; row++) {
			for (int col = 102; col < 292; col++) {
				topPixel = pixels[row][col];
				bottomPixel = pixels[row + 80][col];
				bottomPixel.setColor(topPixel.getColor());
			}
		}
	}

	public void mirrorGull() {
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int width = pixels[0].length;
		for (int row = 233; row < 322; row++) {
			for (int col = 234; col < 345; col++) {
				leftPixel = pixels[row][col];
				rightPixel = pixels[row][col + 150];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	public void chromaKey(Picture newBack) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = newBack.getPixels2D();
		for (int row = 0; row < this.getHeight(); row++) {
			for (int col = 0; col < this.getWidth(); col++) {
				toPixel = toPixels[row][col];
				if (!(toPixel.getBlue() > 250 && toPixel.getGreen() > 250 && toPixel.getRed() > 250)) {
					if (toPixel.getBlue() > toPixel.getRed() && toPixel.getGreen() > 110 && toPixel.getBlue() + 20 > toPixel.getRed() && toPixel.getBlue() - 20 > toPixel.getRed()) {
						if (fromPixels[row][col] != null) {
							fromPixel = fromPixels[row][col];
						}
						toPixel.setColor(fromPixel.getColor());
					}
				}
			}
		}
	}
	
	public void chromadKey(Picture newBack) {
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = newBack.getPixels2D();
		for (int row = 74; row < 666; row++) {
			for (int col = 156; col < 334; col++) {
				toPixel = toPixels[row][col];
				if(toPixel.getGreen() > 100) {
				if (!(toPixel.getBlue() > 250 && toPixel.getGreen() > 250 && toPixel.getRed() > 250) && !(toPixel.getBlue() < 25 && toPixel.getGreen() < 25 && toPixel.getRed() < 25)) {
					if (toPixel.getBlue() > toPixel.getRed() && toPixel.getGreen() > 100 && toPixel.getBlue() + 17 > toPixel.getRed() && toPixel.getBlue() - 17 > toPixel.getRed()) {
						if(toPixel.colorDistance(Color.GREEN) > 75) {
							if (fromPixels[row][col + 250] != null) {
								fromPixel = fromPixels[row][col + 250];
							}
							toPixel.setColor(fromPixel.getColor());
						}
					}
				}
				}
			}
		}
	}
	
	public void moveGreen(Picture newBack) {
		Pixel toPixel = null;
		Pixel[][] fromPixels = this.getPixels2D();
		Pixel[][] toPixels = newBack.getPixels2D();
		for(int row = 123; row < 666; row++) {
			for(int col = 156; col < 334; col++) {
				if(fromPixels[row][col].colorDistance(new Color(62, 141, 66)) > 73) {
					toPixels[row][col+ 250].setColor(fromPixels[row][col].getColor());
				}
			}
		}
		for(int row = 60; row < 122; row++) {
			for(int col = 156; col < 334; col++) {
				if(fromPixels[row][col].colorDistance(new Color(89, 191, 118)) > 80) {
					toPixels[row][col+ 250].setColor(fromPixels[row][col].getColor());
				}
			}
		}
	}
	
	public void decreaseRed() {
		  
	    Pixel pixel = null; // the current pixel
	    int redValue = 0;       // the amount of red

	    // get the array of pixels for this picture object
	    Pixel[] pixels = this.getPixels();

	    // start the index at 0
	    int index = 0;

	    // loop while the index is less than the length of the pixels array
	    while (index < pixels.length) { //while the red is less then the acutal picture, do for each of the colors

	      // get the current pixel at this index
	      pixel = pixels[index];
	      // get the red value at the pixel
	      redValue = pixel.getRed();
	      // set the red value to half what it was
	      redValue = (int) (redValue * 0.1);
	      // set the red for this pixel to the new value
	      pixel.setRed(redValue);
	      // increment the index
	      index++;
	    }
	  }
	
	//(fromPixel.getRed() - j + i*toPixel.getRed())/75,(fromPixel.getGreen() -j+ i*toPixel.getGreen())/75,(fromPixel.getBlue()-j + i*toPixel.getBlue())/75 
	
	public void gradient() {
		int tempRow = 0;
		int tempCol = 0;
		Pixel[][] fromPixels = this.getPixels2D();
		for(int row = 55; row < 396; row++) {
			for(int col = 407; col < 449; col++) {
				if(!(fromPixels[row][col].colorDistance(new Color(228, 222, 216)) > 80) || !(fromPixels[row][col].colorDistance(new Color(130, 164, 133)) > 80)) {
					fromPixels[row][col].setColor(Color.RED);
					if(fromPixels[row][col - 1].getColor() != Color.RED) {
						tempRow = row;
						tempCol = col - 1;
					}
					else {
						tempRow = 0;
						tempCol = 0;
					}
					if(tempRow != 0 && tempCol != 0) {
						for(int i = 50, j = 0; i > 0 && j < 25; i--, j +=2) {
							int startRedValue = fromPixels[tempRow][tempCol].getRed();
							int redValue = fromPixels[tempRow][tempCol - j].getRed();
							Pixel left = fromPixels[tempRow][tempCol - j];
							redValue = (int)(redValue * 0.999);
							left.setRed(redValue);
//							int startGreenValue = fromPixels[row][col].getGreen();
//							int greenValue = fromPixels[row][col - j].getGreen();
//							greenValue = (int)(greenValue * 0.5);
//							left.setGreen(greenValue);
//							int startBlueValue = fromPixels[row][col].getBlue();
//							int blueValue = fromPixels[row][col - j].getBlue();
//							blueValue = (int)(blueValue * 0.5);
//							left.setBlue(blueValue);
						}
				}
				}
			}
		}
		for(int row = 700; row > 0; row--) {
			for(int col = 449; col < 407; col--) {
				if(!(fromPixels[row][col].colorDistance(new Color(228, 222, 216)) > 80) || !(fromPixels[row][col].colorDistance(new Color(130, 164, 133)) > 80)) {
					fromPixels[row][col].setColor(Color.RED);
					for(int i = 0; i < 25; i++) {
						Pixel fromPixel = fromPixels[row][col]; 
						Pixel rightPixel = fromPixels[row][col + i];
						int currentR = fromPixel.getRed();
						int currentG = fromPixel.getGreen();
						int currentB = fromPixel.getBlue();
						int rightR = rightPixel.getRed();
						int rightG = rightPixel.getGreen();
						int rightB = rightPixel.getBlue();
						int averageRightR = (currentR + rightR) * 2;
						int averageRightG = (currentG + rightG)* 2;
						int averageRightB = (currentB + rightB)* 2;
						Color averageRightColor = new Color(averageRightR, averageRightG, averageRightB);
						// create ints for each red green blue, in for loop get rgb from pixel in the direction wanted, add them all multply by 4 divide by 3
						fromPixels[row][col - i].setColor(averageRightColor);
					}
				}
			}
		}
	}
	
	public void chromaSpecificKey(Picture newBack) {
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = newBack.getPixels2D();
		for(int row = 248; row < 465; row++) {
			for(int col = 193; col < 321; col++) {
				if(fromPixels[row][col].getGreen() > 130) {
					toPixels[row][col].setColor(fromPixels[row][col].getColor());
				}
			}
		}
	}
	
	public void chromasnKey(Picture newBack) {
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = newBack.getPixels2D();
		for(int row = 0; row < fromPixels.length; row++) {
			for(int col = 0; col < fromPixels[row].length; col++) {
				if(fromPixels[row][col].colorDistance(Color.GREEN) > 60) {
					toPixels[row][col + 250].setColor(fromPixels[row][col].getColor());
				}
			}
		}
	}
	
	public void chromasKey(Picture newBack) {
//		Pixel fromPixel = null;
//		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = newBack.getPixels2D();
		for (int row = 0; row < fromPixels.length; row++) {
			for (int col = 0; col < fromPixels[row].length; col++) {
//				toPixel = toPixels[row][col];
//				if (!(toPixel.getBlue() > 250 && toPixel.getGreen() > 250 && toPixel.getRed() > 250)) {
//					if (toPixel.getBlue() > toPixel.getRed() && toPixel.getGreen() > 110 && toPixel.getBlue() + 20 > toPixel.getRed() && toPixel.getBlue() - 20 > toPixel.getRed()) {
//						if (fromPixels[row][col] != null) {
//							fromPixel = fromPixels[row][col];
//						}
//						toPixel.setColor(fromPixel.getColor());
//					}
				if((fromPixels[row][col].colorDistance(Color.RED) > 172)) {
				toPixels[row][col].setColor(fromPixels[row][col].getColor());
				}
//				}
			}
		}
	}

	public void edgeDetection2(int edgeDist) {
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		Pixel[][] pixels = this.getPixels2D();
		Color rightColor = null;
		for (int col = 0; col < pixels[0].length; col++) {
			for (int row = 0; row < pixels.length - 1; row++) {
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

} // this } is the end of class Picture, put all new methods before this
