package csc2620_unit2_28practice;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author stuetzlec
 */
public class CanvasPanel extends JPanel {

    ArrayList<MyRectangle> rectanglesToDraw;
    MyRectangle currentlyDrawingRectangle; // A reference to the rectangle being drawn
    GUIDemo parentFrame;

    public CanvasPanel(GUIDemo p) {
        rectanglesToDraw = new ArrayList();
        parentFrame = p;
        
        DragHandler handler = new DragHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Draw each rectangle (using Java's "foreach" loop)
        rectanglesToDraw.forEach((r) -> {
            r.draw(g2);
        });

        if (currentlyDrawingRectangle != null) {
            currentlyDrawingRectangle.draw(g2);
        }
    }

    /**
     * The handler for the click and drag, extends the MouseAdapter class which
     * contains all of the mouse and mouse motion interface methods
     */
    private class DragHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            // If you press the mouse button, start a new rectangle
            currentlyDrawingRectangle = new MyRectangle(e.getX(), e.getY(),
                    e.getX(), e.getY(), parentFrame.getCurrentColor());

            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Add the current rectangle to the list of rectangles to draw
            //   Have to check for null because mouseReleased is called
            //   several times with each release
            if (currentlyDrawingRectangle != null) {
                rectanglesToDraw.add(currentlyDrawingRectangle);

                // Reset the current rectangle
                currentlyDrawingRectangle = null;
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            currentlyDrawingRectangle = 
                    new MyRectangle(currentlyDrawingRectangle, e.getX(), e.getY());
            repaint();
        }
    }

}
