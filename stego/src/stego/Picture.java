package stego;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; 
public class Picture extends SimplePicture {
	private int red;
	private Scanner scan;
	private String message;
	public Picture pic;
	public ArrayList<Character> chars;
	public ArrayList<Integer> ints;
	public Picture() {
		super();
	}
	public Picture(String fileName) {
		super(fileName);
	}
	public Picture(int height, int width) {
		super(width, height);
	}
	public Picture(Picture copyPicture) {
		super(copyPicture);
	}
	public Picture(BufferedImage image) {
		super(image);
	}
	public String toString() {
		String output = "Picture, filename " + getFileName() + " height " + getHeight() + " width " + getWidth();
		return output;

	}
	
	public static void main(String[] args) {
		Picture pic = new Picture("beach.png");
		pic.easy(pic);
	}
	
	public void easy(Picture pic) {
		this.chars = new ArrayList<Character>();
		this.ints = new ArrayList<Integer>();
		this.red = 0;
		this.pic = pic;
		this.message = "Hello World";
		this.scan = new Scanner(System.in);
		encode();
	}
	
	public void encode() {
		for(char ch : message.toCharArray()) {
			chars.add(ch);
		}
		for(char ar : chars) {
			ints.add((int)(ar));
		}
		Pixel[][] pixels = pic.getPixels2D();
		for(int row = 0; row < pixels.length; row++) {
			for(int col = 0; col < pixels[row].length; col++) {
				for(int i : ints) {
					pixels[row][col].setRed(i);
				}
			}
		}
		pic.explore();
	}

} // this } is the end of class Picture, put all new methods before this
