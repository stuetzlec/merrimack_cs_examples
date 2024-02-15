package csc2620_unit2_28practice;



/**
 * A class to represent a 2D point using integers (Java's built-in class 
 *   uses double point precision).
 * @author stuetzlec
 */
public class My2DPoint {
    private final int X;
    private final int Y;
    
    public My2DPoint( int x, int y ) {
        X = x;
        Y = y;
    }
    
    public int getX() { return X; }
    public int getY() { return Y; }
}
