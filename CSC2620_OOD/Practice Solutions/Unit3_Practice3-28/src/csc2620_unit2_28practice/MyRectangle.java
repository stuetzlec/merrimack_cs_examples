package csc2620_unit2_28practice;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 *
 * @author stuetzlec
 */
public class MyRectangle {
    private final My2DPoint[] corners;
    private final Color col;
    
    public MyRectangle(int TX, int TY, int BX, int BY, Color c) {
        corners = new My2DPoint[2];
        corners[0] = new My2DPoint(TX, TY);
        corners[1] = new My2DPoint(BX, BY);
        
        col = c;
    }

    /**
     * This constructor clones everything from the original rectangle
     *   except the bottom right corner
     * @param original The original rectangle to clone
     * @param newBX The new X coordinate of the bottom right
     * @param newBY The new Y coordinate of the bottom right
     */
    public MyRectangle(MyRectangle original, int newBX, int newBY) {
        this.corners = new My2DPoint[2];
        this.corners[0] = original.corners[0];
        this.corners[1] = new My2DPoint(newBX, newBY);
        this.col = original.col;
    }
    
    /**
     * The rectangles method to render to a Graphics2D canvas
     * @param g The Graphics2D context to render to
     */
    public void draw( Graphics2D g ) {
        g.setColor( col );
        g.drawRect(corners[0].getX(), corners[0].getY(), 
                corners[1].getX() - corners[0].getX(), 
                corners[1].getY() - corners[0].getY());
    }    
}
