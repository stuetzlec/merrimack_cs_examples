package csc2620_practice7.pkg3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author stuetzlec
 */
class Square extends Thread {
    private int size; // Size of the square
    private int x;    // Top right x-coordinate
    private int y;    // Top right y-coordinate
    private int xChange = 0;
    private int yChange = 0;
    private Color c;
    private int speed;
    private int lifetime;
    
    public Square(int _x, int _y, int _size, Color _c, int _s, int _lt){
        x = _x;
        y = _y;
        size = _size;
        c = _c;
        speed = _s;
        lifetime = _lt;
        
        randomize();
    }
    
    /**
     * This method randomizes the direction the square is moving
     */
    public void randomize() {
        // Create an initial random trajectory
        Random rng = new Random();
        xChange = rng.nextInt(7) - 3;
        if( xChange == 0 ) xChange = 1;
        yChange = rng.nextInt(7) - 3;
        if( yChange == 0 ) yChange = 1;
    }
    
    /**
     * This method reverses the direction of travel of the square
     */
    public void switchDirection(){
        xChange = -1*xChange;
        x += 10 * xChange;
        yChange = -1*yChange;
        y += 10 * yChange;
    }
    
    /**
     * This method actually changes the location of the square according to
     *   the movement direction.
     */
    public void move(){
        if(lifetime != -1 && lifetime > 0){
            lifetime--;
        }
        int xBot = x + size;
        int yBot = y + size;
        if( x < 0 || x > 1000 || xBot < 0 || xBot > 1000 )
            xChange = -1 * xChange;
        x += xChange;
        if( y < 0 || y > 800 || yBot < 0 || yBot > 800 )
            yChange = -1 * yChange;
        y += yChange;
    }
    
    /**
     * Given a graphics context to use to draw, this method draws the square
     * @param g the graphics context used to draw the square
     */
    public void draw(Graphics g){
        g.setColor(c);
        g.drawRect(x, y, size, size);
    }
    
    /**
     * Returns an array representing the xMin, xMax, yMin, and yMax coordinates
     * @return an array representing the xMin, xMax, yMin, and yMax coordinates
     */
    public int[] getBounds(){
        int[] b = { x, x+size, y, y+size };
        return b;
    }
    
    @Override
    public void run() {
        while(true){
            move();
            try {
                Thread.sleep( 1000 / speed );
            } catch (InterruptedException ex) {

            }
        }
    }

    public boolean readyToDie() {
        return lifetime == 0;
    }
}
