
public class recursionASCII {
	
//	public static void printRows(String thing, int rows, int end) {
//		if(end > 0) {
//			printLine(thing, ++rows);
//			System.out.println();
//			printRows(thing, rows, --end);
//		}
//	}
//	
//	public static void printDownRows(String thing, int rows, int end) {
//		if(end > 0) {
//			printLine(thing, --rows);
//			System.out.println();
//			printDownRows(thing, rows, --end);
//		}
//	}
	
//	public static void printLine(String thing, int length) {
//		if(length > 0) {
//			if(length == 1) {
//				System.out.print(thing + " ");
//			}
//			else {
//				System.out.print("  ");
//			}
//			printLine(thing, --length);
//		}
//	}
	
	public static void printLine(String thing, String thing2, int leftSpace, int middleChar, int rightSpace) {
		if(leftSpace > 0) {
			System.out.print(thing2 + " ");
			printLine(thing, thing2, --leftSpace, middleChar, rightSpace);
		}
		else if(middleChar > 0) {
			System.out.print(thing + " ");
			printLine(thing, thing2, leftSpace, --middleChar, rightSpace);
		}
		else if(rightSpace > 0) {
			System.out.print(thing2 + " ");
			printLine(thing, thing2, leftSpace, middleChar, --rightSpace);
		}
	}
	
	public static void printTop(String thing, String thing2, int leftSpace, int middleChar, int rightSpace, int height) {
		if(height > 0) {
			printLine(thing, thing2, leftSpace, middleChar, rightSpace);
			printLine(thing, thing2, rightSpace, middleChar, leftSpace);
			System.out.println();
			printTop(thing, thing2, --leftSpace, middleChar, ++rightSpace, --height);
		}
	}
	
	public static void printBottom(String thing, String thing2, int leftSpace, int middleChar, int rightSpace, int height) {
		if(height > 0) {
			printLine(thing, thing2, leftSpace, middleChar, rightSpace);
			printLine(thing, thing2, rightSpace, middleChar, leftSpace);
			System.out.println();
			printBottom(thing, thing2, ++leftSpace, middleChar, --rightSpace, --height);
		}
	}
	
	public static void printSides(String thing, String thing2, int width, int height) {
		if(height > 0) {
			printLine(thing2, thing, 1, (width - 2), 1);
			System.out.println();
			printSides(thing, thing2, width, --height);
		}
	}
	
	public static void printHorizontals(String thing, String thing2, int width) {
		printLine(thing, thing2, 0, width, 0);
	}

	public static void main(String [] args) {
		printTop("+", " ", 8, 1, 0, 9);
		printBottom("+", " ", 0, 1, 8, 9);
		System.out.println("\n\n");
		printHorizontals("+", " ", 9);
		System.out.println();
		printSides("+", " ", 9, 9);
		printHorizontals("+", " ", 9);
	}
	
}