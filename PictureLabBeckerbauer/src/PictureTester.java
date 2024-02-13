/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
//    testZeroBlue();
//    testKeepOnlyBlue();
//    testKeepOnlyRed();
//    testKeepOnlyGreen();
//    testNegate();
//    testGrayscale();
//    testFixUnderwater();
//    testMirrorHorizontal(); 
//    testMirrorHorizontalBotToTop();
//    testMirrorVertical();
//    testMirrorTemple();
//    testMirrorArms();
//    testMirrorGull();
//    testMirrorDiagonal();
//    testCollage();
//    testCopy();
//    testEdgeDetection();
//    testEdgeDetection2();
    testChromakey();
//    testEncodeAndDecode();
//    testGetCountRedOverValue(250);
//    testSetRedToHalfValueInTopHalf();
//    testClearBlueOverValue(200);
//    testGetAverageForColumn(0);
  }
  
  private static void testKeepOnlyBlue() {
	    Picture beach = new Picture("beach.jpg");
	    beach.explore();
	    beach.keepOnlyBlue();
	    beach.explore();
		}
  
  private static void testKeepOnlyRed() {
	    Picture beach = new Picture("beach.jpg");
	    beach.explore();
	    beach.keepOnlyRed();
	    beach.explore();
		
  }
  
  private static void testKeepOnlyGreen() {
	    Picture beach = new Picture("beach.jpg");
	    beach.explore();
	    beach.keepOnlyGreen();
	    beach.explore();
	}
  
  private static void testNegate() {
	    Picture beach = new Picture("beach.jpg");
	    beach.explore();
	    beach.negate();
	    beach.explore();
	}
  
  private static void testGrayscale() {
	    Picture beach = new Picture("beach.jpg");
	    beach.grayScale();
	    beach.explore();
	}
  
  private static void testFixUnderwater() {
	    Picture beach = new Picture("water.jpg");
	    beach.explore();
	    beach.fixUnderwater(8);
	    beach.explore();
	}
  
  private static void testMirrorHorizontal() {
	    Picture beach = new Picture("beach.jpg");
	    beach.explore();
	    beach.mirrorHorizontal();
	    beach.explore();
	}
  
  private static void testMirrorHorizontalBotToTop() {
	    Picture beach = new Picture("beach.jpg");
	    beach.explore();
	    beach.mirrorHorizontalBotToTop();
	    beach.explore();
	}
  
  private static void testMirrorArms() {
		Picture snowman = new Picture("snowman.jpg");
		 snowman.explore();
		 snowman.mirrorArms();
		 snowman.explore();
	}
  
  private static void testMirrorGull() {
		Picture gull = new Picture("seagull.jpg");
		gull.explore();
		gull.mirrorGull();
		gull.explore();
	}
  
  private static void testMirrorDiagonal() {
	    Picture beach = new Picture("beach.jpg");
	    beach.explore();
	    beach.mirrorDiagnol();
	    beach.explore();
	}
  
  private static void testEdgeDetection2() {
		Picture gull = new Picture("seagull.jpg");
		gull.explore();
		gull.edgeDetection2(10);
		gull.explore();
	}
  
  private static void testChromakey() {
//	  Picture greengScreen = new Picture("green.jpeg");
//	  Picture newgBack = new Picture("fishes.jpeg");
//	  greengScreen.explore();
//	  greengScreen.chromaKey(newgBack);
//	  greengScreen.explore();
//	  Picture greenzScreen = new Picture("greeny.jpg");
//	  Picture newzBack = new Picture("city.jpg");
//	  greenzScreen.explore();
//	  greenzScreen.chromasKey(newzBack);
//	  greenzScreen.explore();
	  Picture greenScreen = new Picture("background.png");
	  Picture newBack = new Picture("lightsaber.png");
	  newBack.moveGreen(greenScreen);
	  greenScreen.gradient();
//	  newBack.chromadKey(greenScreen);
//	  newBack.chromaSpecificKey(greenScreen);
//	  greenScreen.chromasnKey(newBack);
	  greenScreen.explore();
  }

}