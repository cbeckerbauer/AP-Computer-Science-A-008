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
	private static Picture seagull;
  /** Method to test zeroBlue or at least did */
  public static void testZeroBlue()
  {
   // Picture beach = new Picture("beach.jpg");
    //beach.mirrorVerticalRightToLeft();
    //beach.mirrorHorizontal();
    //beach.mirrorHorizontalBotToTop();
    //beach.mirrorVertical();
    //beach.mirrorDiagonal();
    //beach.explore();
   // beach.zeroBlue();
    //beach.onlyBlue();
    //beach.negate();
    //beach.grayscale();
    //beach.explore();
    
   // Picture water = new Picture("water.jpg");
    //water.grayscale();
    //water.brighten(10);
    //water.negate();
    //water.explore();
    //water.highlightBlue();
    //water.explore();
    //water.boost();
    //water.onlyRed(75);
    //water.zeroBlue();
    //water.explore();
    //water.edgeDetection3(5);
    //water.smoothColor();
    //water.explore();
    
    
    //Picture snow = new Picture("snowman.jpg");
    
    //snow.mirrorArms();
    
    //snow.explore();
    
    //seagull = new Picture("seagull.jpg");
    //seagull.explore();
   //mirrorGull();
    //seagull.explore();
    
    //Picture collage = new Picture(1000,1000);
    //collage.collage2();
    //collage.explore();
    Picture swan = new Picture("swan.jpg");
    //swan.explore();
    swan.edgeDetection(8);
   // swan.explore();
  //  swan.smooth2(0.1);
   // swan.explore();
    
    Picture swan3 = new Picture("swan.jpg");
    swan3.edgeDetection2(8);
    //swan3.explore();
    
    Picture swan2 = new Picture("swan.jpg");
    swan2.explore();
    swan2.edgeDetection4(8);
    //swan2.explore();
    swan2.smooth2(0.1);
    swan2.explore();
    

  }
  
  
  public static void mirrorGull() {
	  Pixel[][] pixels = seagull.getPixels2D();
	    Pixel leftPixel = null;
	    Pixel rightPixel = null;
	    int width = pixels[0].length;
	    for (int row = 0; row < pixels.length; row++)
	    {
	      for (int col = 234; col < 350; col++)
	      {
	        rightPixel = pixels[row][col];
	        leftPixel = pixels[row][350 - 1 - col];
	        leftPixel.setColor(rightPixel.getColor());
	      }
	    } 
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
    testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}