package csc2620_unit2_28practice;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author stuetzlec
 */
class GUIDemo extends JFrame {

    private CanvasPanel canvas;
    private int curColor = 0;
    private Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, 
        Color.GREEN, Color.YELLOW, Color.DARK_GRAY, Color.PINK,
        Color.WHITE, Color.CYAN, Color.MAGENTA, Color.ORANGE};

    GUIDemo() {
        super("Graphics Demonstration");
        this.setLayout(new BorderLayout());

        canvas = new CanvasPanel(this);
        this.add(canvas, BorderLayout.CENTER);

        // Make the 'q' key quit, and the 'c' and 'r' keys change color
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 'q' || e.getKeyChar() == 'Q') {
                    System.exit(0);
                }
                if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
                    curColor++;
                    curColor %= colors.length;
                }
                // Get a random color
                if(e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
                    Random rng = new Random();
                    curColor = rng.nextInt(colors.length);
                }
            }
        });
    }
    
    public Color getCurrentColor() {
        return colors[curColor];
    }
}
