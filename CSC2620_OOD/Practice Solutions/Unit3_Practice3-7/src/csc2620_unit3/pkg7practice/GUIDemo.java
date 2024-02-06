package csc2620_unit3.pkg7practice;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author stuetzlec
 */
class GUIDemo extends JFrame {

    JPanel imagesPanel;
    // Three images and their captions
    JLabel[] imagesAndCaptions = new JLabel[6];
    String[] imageFiles = {"images/dice.jpg",
        "images/cubes.jpg", "images/figures.jpg"};
    String[] captions = {"Here are some 4-sided dice.",
        "Here are some yellow cubes.", "Here are some figures from a game."};

    public GUIDemo(String title) {
        super(title);
        setLayout(new GridLayout(2, 1, 5, 5));

        // Create the images panel
        imagesPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        for (int i = 0; i < 3; i++) {
            createImageAndCaption(imageFiles[i], captions[i], i);
        }
        for (int i = 0; i < 6; i++) {
            imagesPanel.add(imagesAndCaptions[i]);
        }

        JLabel bigCaption
                = new JLabel("Some images from various table-top games.",
                        SwingConstants.CENTER);
        Font font = bigCaption.getFont();
        Font boldFont = new Font(font.getFontName(), Font.BOLD, 26);
        bigCaption.setFont(boldFont);
        this.add(bigCaption);
        this.add(imagesPanel);
    }

    private JLabel loadImage(String filename) {
        Image img = null;
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException ex) {
            System.err.println("ERROR READING IMAGE FILE " + filename);
        }
        return new JLabel(new ImageIcon(img), SwingConstants.CENTER);
    }

    private void createImageAndCaption(String filename, String caption, int imageIndex) {
        imagesAndCaptions[imageIndex] = loadImage(filename);
        imagesAndCaptions[imageIndex + 3]
                = new JLabel(caption, SwingConstants.CENTER);
    }
}
