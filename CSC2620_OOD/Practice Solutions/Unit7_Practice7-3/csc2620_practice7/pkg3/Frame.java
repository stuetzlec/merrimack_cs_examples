package csc2620_practice7.pkg3;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 *
 * @author stuetzlec
 */
public class Frame extends JFrame {
    private Canvas c;
    
    public Frame() {
        super("Practice 7.3");
        setLayout( new GridLayout(1,1) );
        
        c = new Canvas();
        c.setPreferredSize(new Dimension(1000, 800));
        add(c);
    }
    
}
