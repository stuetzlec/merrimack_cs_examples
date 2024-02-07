package guiexamplespace;

import java.awt.GridLayout;
import javax.swing.JFrame;

public class GUIDemo extends JFrame {
    
    private MyCanvas graphicsPanel;
    
    public GUIDemo(){
        super("Graphics demo");
        this.setLayout(new GridLayout(1,1));
        
        graphicsPanel = new MyCanvas();
        this.add(graphicsPanel);        
    }
    
}