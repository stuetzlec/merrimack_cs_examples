package csc2620_practice7.pkg3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * This class controls the squares being drawn and animated along with the the
 * collisions.
 *
 * @author stuetzlec
 */
public class Canvas extends JPanel {

    private Square s1 = null;
    private Square s2 = null;
    private ArrayList<Square> particles = new ArrayList();

    public Canvas() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // If this is the first time calling the paint method
        if (s1 == null) {
            s1 = new Square(10, 10, 200, Color.BLUE, 40, -1);
            s2 = new Square(300, 300, 200, Color.MAGENTA, 20, -1);
            s1.start();
            s2.start();
        }

        if (checkCollision(s1, s2)) {
            s1.switchDirection();
            s2.switchDirection();

            explodeParticles();
        }

        g.clearRect(0, 0, 1000, 800);
        s1.draw(g);
        s2.draw(g);
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).readyToDie()) {
                particles.remove(i);
                i--;
            }
        }
        particles.forEach(p -> {
            p.draw(g);
        });

        repaint();
    }

    /**
     * This method checks to determine if two squares that are passed in have
     * collided with each other. NOTE: Thi smethod can/should be implemented
     * better!
     *
     * @param _s1 The first square to check a collision with
     * @param _s2 The second square to check a collision with
     * @return 'true' if the two squares have collided
     */
    private boolean checkCollision(Square _s1, Square _s2) {
        int[] squareA = _s1.getBounds();
        int[] squareB = _s2.getBounds();

        return squareA[0] <= squareB[1] && squareA[1] >= squareB[0]
                && squareA[2] <= squareB[3] && squareA[3] >= squareB[2];
    }

    /**
     * This method creates a series of 25 particles with random directions
     */
    private void explodeParticles() {
        for (int s = 0; s < 25; s++) {
            Square sq = new Square(500, 400, 3, Color.RED, 60, 5000);
            sq.start();
            particles.add(sq);
        }
    }
}
