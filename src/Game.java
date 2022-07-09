//Game Screen
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class Game implements ActionListener{

	//puzzle declaration
	public boolean winstate = false;
	static JFrame frame;
	public static JButton [][] board = new JButton[4][4];
	public int empcell = 16;
	static JPanel gameP = new JPanel();
	static JPanel mainP = new JPanel();
	static JPanel buttonP = new JPanel();
	JLabel background;
	JButton returnM;
	//Timer declaration
	int elapsedTime = 0;
	int seconds =0;
	int minutes =0;
	int miliseconds =0;
	JLabel timeLabel = new JLabel();
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String miliseconds_string = String.format("%02d", miliseconds);
	//setting up the actual timer
	Timer timer = new Timer(15, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			elapsedTime=elapsedTime+1000;
			minutes = (elapsedTime/3600000) % 60;
			seconds = (elapsedTime/60000) % 60;
			miliseconds = (elapsedTime/1000) % 60;
			seconds_string = String.format("%02d", seconds);
			minutes_string = String.format("%02d", minutes);
			miliseconds_string = String.format("%02d", miliseconds);
			timeLabel.setText(minutes_string+":"+seconds_string+":"+miliseconds_string);
		}
	}
			);


	//Constructor
	//Setting up all the frame and JPanels
	public Game (){
		frame = new JFrame ("Can you solve this?");
		frame.setPreferredSize(new Dimension(600, 700));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		timeLabel.setText(minutes_string+":"+seconds_string+":"+miliseconds_string);
		timeLabel.setBounds(100,100,50,20);
		timeLabel.setLocation(177, 160);

		background = new JLabel (new ImageIcon("background.jpg"));
		Dimension size = background.getPreferredSize();
	    background.setBounds(0, 0, size.width, size.height);
		
		gameP.setSize(250,250);
		gameP.setLocation(175, 200);
		gameP.setLayout(new GridLayout(4,4));

		buttonP.setLayout(null);
		buttonP.add(returnM);

		mainP.setSize(700,700);
		mainP.setLayout(null);
		mainP.add(timeLabel);
		mainP.add(background);
		timer.start();
		frame.add(gameP,BorderLayout.CENTER);
		frame.add(mainP);
		frame.add(buttonP);
		frame.pack ();
		frame.setVisible (true);
		jboard();
	}

	//Description: Creates the board and calls solvable() to see if the board is solvable, if not, then re-shuffle the board until it is solvable.
	//Parameter: Nothing
	//Return: The shuffled board with numbers
	private  void jboard() {
		//Initializes the arralist
		ArrayList<Integer> shuf = new ArrayList<Integer>(16);
		shuf = new ArrayList<Integer>(16);
		for (int i = 0; i < 16; i++) {
			shuf.add(i, i+1);	
		}
		//Contiunes to shuffle the list and see if the list is solvable, if not, continue shuffling until it is solvable.
		while(true) {
			Collections.shuffle(shuf);
			if(solvable(shuf)) {
				break;
			}
		}
		System.out.println(shuf);

		//Applies the values in the list to the actual board
		int num = 0;
		int pos = shuf.indexOf(16);
		for(int x = 0; x < 4; x ++) {
			for(int y = 0; y < 4; y++) {
				board[x][y] = new JButton(String.valueOf(shuf.get(num)));
				num++;
				if(num-1 == pos) {
					board[x][y].setVisible(false);
					empcell = pos;
				}
				board[x][y].setBackground(Color.BLACK);
				board[x][y].setForeground(Color.white);
				board[x][y].addActionListener(this);
				gameP.add(board[x][y]);
			}
		}

	}

	//Description: Sees if an action is performed. Then calls move() and checks finish();
	//Parameter: nothing
	//Return: nothing
	public void actionPerformed(ActionEvent event) throws IllegalArgumentException {
		JButton buttonPressed = (JButton) event.getSource();
		int index = index(buttonPressed.getText());
		int row = index / 4;
		int column = index % 4;

		Move(row , column);
		if(finish()) {
			JOptionPane.showMessageDialog(null, "Congratuation, your final scroe is " + minutes_string+":"+seconds_string+":"+miliseconds_string);
		}
	}

	//Description: Gets the index of the current JButton
	//Parameter: the number pressed
	//Return: the index of the number pressed
	public int index(String num) {
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length;y++) {
				if(board[x][y].getText().equals(num)) {
					return ((x * 4) + y);
				}
			}
		}
		return -1;
	}

	//Description: Uses the given row and column and the empty cell information to find the next cell avalible to move to
	//Parameter: The row and column of the current cell
	//Return: True if it can be moved, false if not.
	//Took reference from https://github.com/amadamala/fifteen-puzzle/blob/master/JavaVersion/FifteenPuzzle.java
	public boolean Move(int row, int col) {
		//Gets the row of the current empty cell
		int empcellrol = empcell / 4;
		//Gets the column of the current empty cell
		int empcellcol = empcell % 4;
		//Finds the difference between row and col
		int rowdif = empcellrol - row;
		int coldif = empcellcol - col;
		boolean isInRow = (row == empcellrol);
		boolean isInCol = (col == empcellcol);
		boolean isNotDiagonal = (isInRow || isInCol);

		//If the empty cell is diagonal to the current cell, then it just skips it.
		if (isNotDiagonal) {
			int diff = Math.abs(coldif);

			//Moves to the right
			if (coldif > 0 & isInRow) {
				for (int i = 0; i < diff; i++) {
					board[empcellrol][empcellcol - i].setText(
							board[empcellrol][empcellcol - (i + 1)].getText());
				}
			}
			//Moves to the left
			else if (coldif < 0 & isInRow) {
				for (int i = 0; i < diff; i++) {
					board[empcellrol][empcellcol + i].setText(
							board[empcellrol][empcellcol + (i + 1)].getText());
				}

			}
			diff = Math.abs(rowdif);

			//Moves up
			if (rowdif < 0 & isInCol) {
				for (int i = 0; i < diff; i++) {
					board[empcellrol + i][empcellcol].setText(
							board[empcellrol + (i + 1)][empcellcol].getText());
				}

			}
			//Moves down
			else if (rowdif > 0 & isInCol) {
				for (int i = 0; i < diff; i++) {
					board[empcellrol - i][empcellcol].setText(
							board[empcellrol - (i + 1)][empcellcol].getText());
				}
			}

			//Switching the cells
			board[empcellrol][empcellcol].setVisible(true);
			board[row][col].setText(Integer.toString(0));
			board[row][col].setVisible(false);
			empcell = (row * 4)+col;
		}

		return true;
	}

	//Description: Checks if the board is complete by comparing the original board to the win state.
	//Parameter: Nothing
	//Return: True if the board is completed, false if not.
	private boolean finish() {
		int [] win = new int [16];
		for(int i = 1; i < win.length; i++) {
			win[i-1] = i;
		}
		for(int i = 15; i >= 0; i--) {
			if(win[i] != (Integer.parseInt(board[i/4][i%4].getText()))) {
				return false;
			}
		}
		timer.stop();
		return true;
	}

	//Description: Checks if the board is solvable by checks various of conditions
	//Parameter: The puzzle in arraylist form
	//Return: True if the puzzle is solvable, false if not.
	//Code taken from https://stackoverflow.com/questions/34570344/check-if-15-puzzle-is-solvable
	//Original Author: user4918296
	private boolean solvable(ArrayList<Integer> puzzle)
	{
		int parity = 0;
		int gridWidth = (int) Math.sqrt(16);
		int row = 0; // the current row we are on
		int blankRow = 0; // the row with the blank tile

		for (int i = 0; i < 16; i++)
		{
			if (i % gridWidth == 0) { // advance to next row
				row++;
			}
			if (puzzle.get(i) == 0) { // the blank tile
				blankRow = row; // save the row on which encountered
				continue;
			}
			for (int j = i + 1; j < 16; j++)
			{
				if (puzzle.get(i) >puzzle.get(j) && puzzle.get(j) != 0)
				{
					parity++;
				}
			}
		}

		if (gridWidth % 2 == 0) { // even grid
			if (blankRow % 2 == 0) { // blank on odd row; counting from bottom
				return parity % 2 == 0;
			} else { // blank on even row; counting from bottom
				return parity % 2 != 0;
			}
		} else { // odd grid
			return parity % 2 == 0;
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
	}

}