package csc2620;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

public class GUIDemo extends JFrame {
    private JPanel mousePanel;  // Panel which mouse events occur
    private JLabel statusBar;   // Label that displays event info
    
    public GUIDemo() {
        super( "Demonstrating Mouse Events" );
        
        mousePanel = new JPanel();
        mousePanel.setBackground( Color.WHITE );
        this.add( mousePanel, BorderLayout.CENTER );
        
        statusBar = new JLabel("Mouse Outside JPanel");
        this.add( statusBar, BorderLayout.SOUTH );
        
        // Create and register listener for mouse and mouse motion
        MouseHandler handler = new MouseHandler();
        mousePanel.addMouseListener( handler );
        mousePanel.addMouseMotionListener( handler );
    }
    
    private class MouseHandler implements MouseListener,
            MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            statusBar.setText(String.format("Clicked at [%d %d]",
                    e.getX(), e.getY()));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            statusBar.setText(String.format("Pressed at [%d %d]",
                    e.getX(), e.getY()));        }

        @Override
        public void mouseReleased(MouseEvent e) {
            statusBar.setText(String.format("Released at [%d %d]",
                    e.getX(), e.getY()));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            statusBar.setText(String.format("Entered [%d %d]",
                    e.getX(), e.getY()));
            mousePanel.setBackground( Color.GREEN );
        }

        @Override
        public void mouseExited(MouseEvent e) {
            statusBar.setText(String.format("Outside JPanel"));
            mousePanel.setBackground( Color.WHITE );
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            statusBar.setText(String.format("Dragged at [%d %d]",
                    e.getX(), e.getY()));
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            statusBar.setText(String.format("Moved at [%d %d]",
                    e.getX(), e.getY()));        }
    }
}