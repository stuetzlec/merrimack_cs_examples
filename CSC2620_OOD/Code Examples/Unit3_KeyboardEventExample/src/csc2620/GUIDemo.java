package csc2620;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GUIDemo extends JFrame implements KeyListener {
    private String line1 = "", line2 = "", line3 = "";
    private JTextArea textArea;
    
    public GUIDemo() {
        super( "Keyboard Demo" );
        
        textArea = new JTextArea(10, 15);
        textArea.setText("Press any key.");
        textArea.setEnabled(false);
        textArea.setDisabledTextColor(Color.BLACK);
        this.add( textArea );
     
        addKeyListener(this);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        line1 = String.format("Key Typed: %c", e.getKeyChar() );
        setLines2And3( e );
    }

    @Override
    public void keyPressed(KeyEvent e) {
        line1 = String.format("Key Pressed: %s", 
                KeyEvent.getKeyText( e.getKeyCode() ) );
        setLines2And3( e );
    }

    @Override
    public void keyReleased(KeyEvent e) {
        line1 = String.format("Key Released: %s", 
                KeyEvent.getKeyText( e.getKeyCode() ) );
        setLines2And3( e );
    }
    
    private void setLines2And3( KeyEvent e) {
        line2 = String.format("The key pressed %s an action key", 
                (e.isActionKey() ? "is" : "is not"));
        String temp =
                KeyEvent.getModifiersExText(ICONIFIED);
        line3 = String.format( "Modifier keys pressed: %s", 
                (temp.equals("") ? "none" : temp ) );
        textArea.setText(String.format("%s\n%s\n%s",
                line1, line2, line3 ) );
    }   
}