//Student Name: Max Luo
//Assignment #: ISU final Assignment
//Date: June 19, 2020
//This is a game that I made which is called the game of fifteen or fifteen puzzle. It is basically a puzzle game that scrambels the puzzle and requires you to re-order the tiles from 1 to 15.

//Main Screen
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class main implements MouseListener{

	JFrame frame;
	JPanel myPanel;
	JLabel background;
	
	public main () {
		//Initalizes the frame
		frame = new JFrame ("Main Menu");
		frame.setPreferredSize(new Dimension(430, 700));
		frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        //Background picture
		background = new JLabel (new ImageIcon("Game Of Fifteen.png"));
	    Dimension size = background.getPreferredSize();
	    background.setBounds(0, 0, size.width, size.height);
		
		myPanel = new JPanel ();
		myPanel.add(background);
		myPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		myPanel.addMouseListener(this);
		myPanel.setLayout(null);
		frame.add (myPanel);
		frame.pack ();
		frame.setVisible (true);

	}
	
	
	public void mouseClicked (MouseEvent e)
	{	
		//Checks if the mouse click is in any "buttons" boundary.
		int x = e.getX();
		int y = e.getY();
		
		//Play button
		if((x > 98 && x <308) && (y > 251 && y < 293)) {
			Game screen = new Game();
			screen.frame.setVisible(true);
			frame.dispose();
		}
		
		//Guide button
		if((x > 98 && x <308) && (y > 376 && y < 416)) {
			Guide screen = new Guide();
			screen.frame.setVisible(true);
			frame.dispose();
		}
		
		//Settings button
		if((x > 98 && x <308) && (y > 501 && y < 543)) {
			JOptionPane.showMessageDialog(null, "Sorry, this page is still under development");
		}
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main (String [] args){
		new main ();
	}

}