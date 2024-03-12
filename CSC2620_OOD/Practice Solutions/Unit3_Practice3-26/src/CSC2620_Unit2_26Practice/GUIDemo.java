package CSC2620_Unit2_26Practice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author stetz
 */
class GUIDemo extends JFrame {

    private JTextField field1, field2, phone;
    private JTextArea area;
    private ArrayList<EventObject> events;

    public GUIDemo() {
        super("Unit 3.26 Practice");
        this.setLayout(new FlowLayout());
        events = new ArrayList<>();

        // Practice 3.26 #2
        // Set up the text fields
        field1 = new JTextField("", 30);
        field2 = new JTextField("", 30);
        field2.setEditable(false);
        this.add(field1);
        this.add(field2);

        // Practice 3.26 #3
        // Set up the text area
        area = new JTextArea(30, 80);
        area.setLineWrap(true);
        this.add(new JScrollPane(area));
        // Add the mouse click and key handler to the frame itself
        this.addMouseListener(new ClickHandler());
        this.addKeyListener(new KeyHandler());

        // Practice 3.26 #4
        // Add the phone number text field, and add a key listener to it
        this.add(new JLabel("Phone Number:"));
        phone = new JTextField("", 30);
        phone.addKeyListener(new KeyHandler());
        this.add(phone);
        
        // Add a quit button
        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(quit);
    }

    private class ClickHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                field2.setText(field1.getText().toUpperCase());
                // NOTE: This might not be "BUTTON3" on all architectures
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                field2.setText(field1.getText().toLowerCase());
            }

            eventHelper(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            eventHelper(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            eventHelper(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            eventHelper(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            eventHelper(e);
        }
    }

    private class MouseMotionHandler implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            eventHelper(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            eventHelper(e);
        }

    }

    private class KeyHandler implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            eventHelper(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            eventHelper(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            eventHelper(e);
            
            if (phone.hasFocus() && !(Character.isDigit(e.getKeyChar()) || e.getKeyChar() == '-')) {
                JOptionPane.showMessageDialog(phone, e.getKeyChar() + " is not a digit or a -");
            }
        }
    }

    private void eventHelper(EventObject e) {
        events.add(e);
        area.setText(area.getText() + "\n" + e.toString());
    }
 
}
