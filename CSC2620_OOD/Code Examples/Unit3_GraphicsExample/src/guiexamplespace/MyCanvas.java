package guiexamplespace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author stuetzlec
 */
public class MyCanvas extends JPanel {

    public MyCanvas() {
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // If you want to use the Graphics2D library:
        Graphics2D g2 = (Graphics2D) g;
        
        g.setColor( Color.RED );
        g.drawRect(10, 10, 200, 200);
        g.setColor(Color.GREEN);
        g.draw3DRect(210, 210, 200, 200, false);
        g.setColor(Color.BLUE);
        g.fillRect(210, 10, 200, 200);
        //g.clearRect(110, 100, 200, 200);
    }

}
