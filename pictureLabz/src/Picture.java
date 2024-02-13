import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List;
public class Picture extends SimplePicture {
	private String message;
	public Picture pic;
	public ArrayList<Character> chars;
	public ArrayList<Integer> ints;
	public ArrayList<Integer> decodeInts;
	public ArrayList<Character> decodeChars;
	public ArrayList<String> bianary;
	public ArrayList<Integer> individualBianary;
	public ArrayList<Integer> decodeIndividualBianary;
	public ArrayList<String> decodeBianary;
	private String endMessage;
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
		Picture pic = new Picture("beach.jpg");
		pic.explore();
//		pic.easy(pic);
		pic.hard(pic);
	}
	
	public void hard(Picture pic) {
		this.bianary = new ArrayList<String>();
		this.chars = new ArrayList<Character>();
		this.ints = new ArrayList<Integer>();
		this.decodeChars = new ArrayList<Character>();
		this.decodeInts = new ArrayList<Integer>();
		this.individualBianary = new ArrayList<Integer>();
		this.decodeIndividualBianary = new ArrayList<Integer>();
		this.decodeBianary = new ArrayList<String>();
		this.pic = pic;
		this.message = "Hello World";
		this.endMessage = "";
		encodeH();
	}
	
	public void easy(Picture pic) {
		this.chars = new ArrayList<Character>();
		this.ints = new ArrayList<Integer>();
		this.decodeChars = new ArrayList<Character>();
		this.decodeInts = new ArrayList<Integer>();
		this.pic = pic;
		this.message = "Hello World";
		this.endMessage = "";
		encodeE();
	}
	
	public void encodeH() {
		for(char ch : message.toCharArray()) {
			chars.add(ch);
		}
		for(char ar : chars) {
			ints.add((int)(ar));
		}
		for(int i : ints) {
			bianary.add(Integer.toBinaryString(i));
		}
		for(int i = 0; i < bianary.size(); i++) {
			if(bianary.get(i).length() == 6) {
				String temp = "0" + bianary.get(i);
				bianary.set(i, temp);
			}
		}
		Pixel[][] pixels = pic.getPixels2D();
		for(int i = 0; i < bianary.size(); i++) {
			String temp = bianary.get(i);
			for(int j = 0; j < temp.length(); j++) {
				individualBianary.add(Integer.parseInt(String.valueOf(temp.charAt(j))));
			}
		}
		int col = 0;
		int count = 0;
		while(col < pixels.length && count < individualBianary.size()) {
			pixels[0][col].setBlue(individualBianary.get(count));
			count++;
			col++;
		}
		pic.explore();
		System.out.println("Done Encodeing");
		decodeH();
	}
	
	public void encodeE() {
		for(char ch : message.toCharArray()) {
			chars.add(ch);
		}
		for(char ar : chars) {
			ints.add((int)(ar));
		}
		Pixel[][] pixels = pic.getPixels2D();
		int col = 0;
		int i = 0;
		while(col < pixels[0].length && i < ints.size()) {
			pixels[0][col].setRed(ints.get(i));
			col++;
			i++;
		}
		
		pic.explore();
		System.out.println("Done Encodeing");
		decodeE();
	}
	
	public void decodeH() {
		Pixel[][] pixels = pic.getPixels2D();
		for(int col = 0; col < individualBianary.size(); col++) {
			decodeIndividualBianary.add(pixels[0][col].getBlue());
		}
		String temp = "";
		double convertedDouble = 0;
		for(int count = 0; count < decodeIndividualBianary.size(); count += 7) {
			temp = "";
			for(int j = 0; j < 7; j++) {
				if(count + j < decodeIndividualBianary.size()) {
					temp += decodeIndividualBianary.get(count + j);
				}
			}
			decodeBianary.add(temp);
		}
		int convertedInt = 0;
		for(String str : decodeBianary) {
			convertedInt = 0;
			convertedDouble = 0;
			for(int i = 0; i < str.length(); i++) {
				if(str.charAt(i) == '1') {
					int len = str.length() - 1 - i;
					convertedDouble += Math.pow(2, len);
				}
			}
			convertedInt = (int)(convertedDouble);
			decodeInts.add(convertedInt);
		}
		for(int i : decodeInts) {
			decodeChars.add((char)(i));
		}
		for(char c : decodeChars) {
			endMessage += c;
		}
		System.out.println(endMessage);
	}
	
	public void decodeE() {
		Pixel[][] pixels = pic.getPixels2D();
			for(int col = 0; col < ints.size(); col++) {
				decodeInts.add(pixels[0][col].getRed());
			}
		for(int i : decodeInts) {
			decodeChars.add((char)(i));
		}
		for(char c : decodeChars) {
			endMessage += c;
		}
		System.out.println(endMessage);
	}

}