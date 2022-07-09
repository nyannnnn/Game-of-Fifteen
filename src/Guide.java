//Guide Screen
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Guide {

	JButton returnM;
	JFrame frame;
	JPanel myPanel;
	JLabel background;
	
	public Guide () {
		//Initalizes the frame
		frame = new JFrame ("Guide");
		frame.setPreferredSize(new Dimension(430, 700));
		frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        //Background picture
		background = new JLabel (new ImageIcon("guide.jpg"));
	    Dimension size = background.getPreferredSize();
	    background.setBounds(-150, 0, size.width, size.height);
		
	    returnM = new JButton(new ImageIcon("button.jpg"));
		returnM.setLocation(50,50);
		returnM.setVisible(true);
		returnM.setBounds(0, 0, 80, 20);
		//If returnM is triggered, go back to main screen
		returnM.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						main screen = new main();
						screen.frame.setVisible(true);
						frame.setVisible(false);
					}
				}
				);
	    
		myPanel = new JPanel ();
		myPanel.add(returnM);
		myPanel.add(background);
		myPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		myPanel.setLayout(null);
		frame.add (myPanel);
		frame.pack ();
		frame.setVisible (true);

	}

	public static void main(String[]args) {
		
		Guide main = new Guide();
		
	}

	
	
}
