package stego;

import java.util.Scanner;
import java.util.ArrayList;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List;

public class easy extends Picture{

	private int red;
	private Scanner scan;
	private String message;
	public Picture pic;
	private ArrayList<Character> chars;
	private ArrayList<Integer> ints;
	
	public easy() {
		this.chars = new ArrayList<Character>();
		this.ints = new ArrayList<Integer>();
		this.red = 0;
		this.pic = new Picture("beach.jpg");
		pic.explore();
		this.message = "Hello World";
		this.scan = new Scanner(System.in);
//		askForMessage();
	}
	
	public void askForMessage() {
		System.out.println("What message would you like to encode?");
		message = scan.nextLine();
		System.out.println("Here is your image that we will encode on.");
		pic.explore();
		System.out.println("We will now encode your message onto this photo.");
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
	}
	
}
