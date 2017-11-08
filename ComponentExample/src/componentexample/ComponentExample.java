package componentexample;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author MrGunerhanal
 */

// window event Handler class
class MyWindowListener extends WindowAdapter {
public void windowClosing(WindowEvent e) {
System.out.println("Closing window!");
System.exit(0);
}
}
// button event handler class
class MyActionListener implements ActionListener {
private int i=1;
JFrame frame;
MyActionListener(JFrame f) {
frame = f;
}
public void actionPerformed(ActionEvent e) {
System.out.println("Pressed Button " + i++ + "th time!");
if (i % 2 == 0)
frame.getContentPane().setBackground(Color.red);
else
frame.getContentPane().setBackground(Color.white);
}
}
public class ComponentExample {


    public static void main(String[] args) {
        
        JFrame frame = new JFrame("ComponentExample");
JButton button = new JButton("press me");
JPanel jp = new JPanel();
jp.setBackground(Color.white);
// set the content pane to be the newly created JPanel
frame.setContentPane(jp);
frame.getContentPane().add(button);
// register an event handler for frame events
frame.addWindowListener(new MyWindowListener());
// register an event handler for button events
button.addActionListener(new MyActionListener(frame));
frame.setSize(400, 400);
frame.setVisible(true);
    }
    
}
