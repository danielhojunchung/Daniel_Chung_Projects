// The "Connect4" class
/* Name - Michael, Daniel
Date - Jan 10 2022
Teacher - P. Guglielmi

*/
import java.awt.*;
import hsa.Console;
import javax.swing.*;
import java.io.*;


public class Connect4
{
    Console c;
    // The output console
    int playerTurn = 1;


    Color black = new Color (0, 0, 0);
    Color red = new Color (255, 0, 0);
    Color yellow = new Color (232, 232, 0);

    String winnerName[];
    int winnerNumber[];


    Connect4 ()
    {
	c = new Console ();
    }


    private void title (String page)
    {
	c.clear ();
	c.setColor (black);
	c.drawString ("Connect 4 - " + page, 20, 20);
    }


    private boolean checkWin (int lastToken, int playerTurn, int check1, int check2, int[] placement)
    {
	boolean winCondition = false;
	int lastColumn = lastToken % 7;
	int connections = 1;
	int i = check1;
	boolean gridJumping = false;
	boolean sameColor = true;

	while (sameColor == true)
	{

	    if (((lastToken + i - check1) % 7 - 2) < (lastToken + i) % 7 || (lastToken + i) % 7 < (lastToken + i - check1) % 7 + 2)
	    {
		gridJumping = true;
	    }
	    else
	    {
		gridJumping = false;
	    }


	    if (gridJumping == true || lastToken + i > 42 || lastToken + i < 1)
	    {
		sameColor = false;
	    }
	    else if (placement [lastToken + i] == playerTurn)
	    {
		connections++;
		i = i + check2;
	    }
	    else
	    {
		sameColor = false;
	    }

	}

	i = check2;
	sameColor = true;
	gridJumping = false;
	while (sameColor == true)
	{
	    if (((lastToken + i - check2) % 7 - 2) < (lastToken + i) % 7 || (lastToken + i) % 7 < (lastToken + i - check2) % 7 + 2)
	    {
		gridJumping = true;
	    }
	    else
	    {
		gridJumping = false;
	    }

	    if (gridJumping = false || lastToken + i > 42 || lastToken + i < 1)
	    {
		sameColor = false;
	    }
	    else if (placement [lastToken + i] == playerTurn)
	    {
		connections++;
		i = i + check2;
	    }
	    else
	    {
		sameColor = false;
	    }
	}

	if (connections > 3)
	{
	    return true;
	}
	else
	{
	    return false;

	}
    }


    public void intro ()
    {

	c.setColor (black);
	for (int i = 0 ; i < 50 ; i++)
	{
	    for (int j = 0 ; j < i + 2 ; j++)
	    {

		c.drawLine (10, i * 10, j * 12, i * 10);
		try
		{
		    Thread.sleep (50 / i);
		}
		catch (Exception e)
		{
		}
	    }
	}
	c.setColor (black);
	for (int i = 0 ; i < 50 ; i++)
	{
	    for (int j = 0 ; j < i + 2 ; j++)
	    {

		c.drawLine (640, 490 - i * 10, 640 - j * 12, 490 - i * 10);
		try
		{
		    Thread.sleep (50 / i);
		}
		catch (Exception e)
		{
		}
	    }
	}
	c.setFont (new Font ("Century", Font.BOLD, 60));
	c.setColor (red);
	c.drawString ("Connect", 300, 100);
	c.setFont (new Font ("Century", Font.BOLD, 80));
	c.setColor (yellow);
	c.drawString ("4", 500, 200);
	c.setColor (black);
	c.setFont (new Font ("Century", Font.BOLD, 20));
	c.drawString ("Press any key to start", 375, 250);
	c.getChar ();

    }


    public void menuTokenDraw (Color tokenColor, int x, int y)
    {

	c.setColor (tokenColor);
	c.fillOval (x, y, 90, 20);
	c.setColor (black);
	c.drawOval (x, y, 90, 20);
	c.setColor (tokenColor);
	c.fillOval (x, y - 20, 90, 20);
	c.fillRect (x, y - 10, 90, 20);
	c.setColor (black);
	c.drawLine (x, y - 10, x, y + 10);
	c.drawLine (x + 90, y - 10, x + 90, y + 10);
	c.drawOval (x, y - 20, 90, 20);
    }


    public void menu ()
    {
	c.clear ();
	c.setColor (black);
	c.setFont (new Font ("Century", Font.BOLD, 60));
	c.drawString ("Connect 4", 50, 50);
	c.setFont (new Font ("Century", Font.BOLD, 30));
	c.drawString ("Play Game ....... 1", 50, 100);
	c.drawString ("Instructions ..... 2", 50, 150);
	c.drawString ("Top Winners ..... 3", 50, 200);
	c.drawString ("Quit Game ........ 4", 50, 250);

	menuTokenDraw (red, 400, 470);
	menuTokenDraw (yellow, 295, 470);
	menuTokenDraw (red, 320, 445);
	menuTokenDraw (yellow, 420, 445);
	menuTokenDraw (yellow, 360, 420);

	askData ();
    }


    private void askData ()
    {
	int userInput;
	userInput = c.getChar ();

	switch (userInput)
	{
	    case '1':
		playGame ();
		break;

	    case '2':
		instructions ();
		break;

	    case '3':
		highScore ();
		break;

	    case '4':
		goodbye ();
		break;

	    default:
		c.setColor (yellow);
		c.fillRect (150, 150, 340, 200);
		c.setColor (black);
		c.setFont (new Font ("Century", Font.BOLD, 20));
		c.drawString ("Please enter a digit", 220, 200);
		c.drawString ("between 1 and 4", 230, 230);
		c.drawString ("Press any key to continue", 190, 300);
		c.getChar ();
		menu ();
		break;
	}
    }


    public void highScore ()
    {
	try
	{
	    BufferedReader b = new BufferedReader (new FileReader ("highScores.txt"));
	    String line = "";
	    int indices = 0;
	    while (line != null)
	    {
		line = b.readLine ();
		indices++;
	    }
	    b.close ();

	    winnerNumber = new int [indices / 2];
	    winnerName = new String [indices / 2];

	    b = new BufferedReader (new FileReader ("highScores.txt"));

	    for (int i = 0 ; i < indices / 2 ; i++)
	    {
		winnerName [i] = b.readLine ();
		winnerNumber [i] = Integer.parseInt (b.readLine ());
	    }
	    b.close ();
	}
	catch (Exception e)
	{
	}
	
	c.setColor (yellow);
	c.fillRect (0, 0, 640, 500);
	c.setColor (black);

	c.setFont (new Font ("Century", Font.BOLD, 30));
	c.drawString ("Top 10 players", 50, 50);
	
	c.setFont (new Font ("Century", Font.BOLD, 18));
	int i = 0;        
	
	while(winnerName[i] != null && i<10){
	
	    c.drawString(winnerName[i], 50, 100+i*30);
	    c.drawString(winnerNumber[i]+"", 200, 100+i*30);
	    i++;
	}
	c.setFont (new Font ("Century", Font.BOLD, 30));
	c.drawString ("Press any key to continue", 50, 430);
	c.getChar();
	menu();
    }
    
    public void getWinner(){
	c.setColor (yellow);
	c.fillRect (0, 0, 640, 500);
	c.setColor (black);
	c.setFont (new Font ("Century", Font.BOLD, 30));
	c.drawString ("Congratulations player!", 50, 50);
	c.setFont (new Font ("Century", Font.BOLD, 18));
	c.drawString ("Please enter your name (less than 10 characters)", 50, 50);
	
    }


    public void playGame ()
    {
	c.clear ();
	drawBoard ();
	int[] placement = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	boolean error = true;
	boolean playerWin = false;
	char move;
	int[] column = {0, 0, 0, 0, 0, 0, 0};
	while (playerWin == false)
	{
	    while (error)
	    {
		move = c.getChar ();
		switch (move)
		{
		    case '1':
			if (column [0] < 6)
			{
			    if (playerTurn == 1)
			    {
				placement [0 + column [0] * 7] = 1;
			    }
			    else
			    {
				placement [0 + column [0] * 7] = -1;
			    }
			    showToken (1 + column [0] * 7);
			    column [0]++;
			    error = false;

			    playerWin = checkWin (0 + column [0] * 7, playerTurn, -1, 1, placement);
			    playerWin = checkWin (0 + column [0] * 7, playerTurn, -6, 6, placement);
			    playerWin = checkWin (0 + column [0] * 7, playerTurn, -7, 7, placement);
			    playerWin = checkWin (0 + column [0] * 7, playerTurn, -8, 8, placement);
			}
			break;

		    case '2':
			if (column [1] < 6)
			{
			    if (playerTurn == 1)
			    {
				placement [1 + column [1] * 7] = 1;
			    }
			    else
			    {
				placement [1 + column [1] * 7] = -1;
			    }
			    showToken (2 + column [1] * 7);
			    column [1]++;
			    error = false;

			    playerWin = checkWin (1 + column [1] * 7, playerTurn, -1, 1, placement);
			    playerWin = checkWin (1 + column [1] * 7, playerTurn, -6, 6, placement);
			    playerWin = checkWin (1 + column [1] * 7, playerTurn, -7, 7, placement);
			    playerWin = checkWin (1 + column [1] * 7, playerTurn, -8, 8, placement);
			}
			break;


		    case '3':
			if (column [2] < 6)
			{
			    if (playerTurn == 1)
			    {
				placement [2 + column [2] * 7] = 1;
			    }
			    else
			    {
				placement [2 + column [2] * 7] = -1;
			    }
			    showToken (3 + column [2] * 7);
			    column [2]++;
			    error = false;

			    playerWin = checkWin (2 + column [2] * 7, playerTurn, -1, 1, placement);
			    playerWin = checkWin (2 + column [2] * 7, playerTurn, -6, 6, placement);
			    playerWin = checkWin (2 + column [2] * 7, playerTurn, -7, 7, placement);
			    playerWin = checkWin (2 + column [2] * 7, playerTurn, -8, 8, placement);
			}
			break;

		    case '4':
			if (column [3] < 6)
			{
			    if (playerTurn == 1)
			    {
				placement [3 + column [3] * 7] = 1;
			    }
			    else
			    {
				placement [3 + column [3] * 7] = -1;
			    }
			    showToken (4 + column [3] * 7);
			    column [3]++;
			    error = false;
			    playerWin = checkWin (3 + column [3] * 7, playerTurn, -1, 1, placement);
			    playerWin = checkWin (3 + column [3] * 7, playerTurn, -6, 6, placement);
			    playerWin = checkWin (3 + column [3] * 7, playerTurn, -7, 7, placement);
			    playerWin = checkWin (3 + column [3] * 7, playerTurn, -8, 8, placement);
			}
			break;

		    case '5':
			if (column [4] < 6)
			{
			    if (playerTurn == 1)
			    {
				placement [4 + column [4] * 7] = 1;
			    }
			    else
			    {
				placement [4 + column [4] * 7] = -1;
			    }
			    showToken (5 + column [4] * 7);
			    column [4]++;
			    error = false;

			    playerWin = checkWin (4 + column [4] * 7, playerTurn, -1, 1, placement);
			    playerWin = checkWin (4 + column [4] * 7, playerTurn, -6, 6, placement);
			    playerWin = checkWin (4 + column [4] * 7, playerTurn, -7, 7, placement);
			    playerWin = checkWin (4 + column [4] * 7, playerTurn, -8, 8, placement);
			}
			break;

		    case '6':
			if (column [5] < 6)
			{
			    if (playerTurn == 1)
			    {
				placement [5 + column [5] * 7] = 1;
			    }
			    else
			    {
				placement [5 + column [5] * 7] = -1;
			    }
			    showToken (6 + column [5] * 7);
			    column [5]++;
			    error = false;

			    playerWin = checkWin (5 + column [5] * 7, playerTurn, -1, 1, placement);
			    playerWin = checkWin (5 + column [5] * 7, playerTurn, -6, 6, placement);
			    playerWin = checkWin (5 + column [5] * 7, playerTurn, -7, 7, placement);
			    playerWin = checkWin (5 + column [5] * 7, playerTurn, -8, 8, placement);
			}
			break;

		    case '7':
			if (column [6] < 6)
			{
			    if (playerTurn == 1)
			    {
				placement [6 + column [6] * 7] = 1;
			    }
			    else
			    {
				placement [6 + column [6] * 7] = -1;
			    }
			    showToken (7 + column [6] * 7);
			    column [6]++;
			    error = false;

			    playerWin = checkWin (6 + column [6] * 7, playerTurn, -1, 1, placement);
			    playerWin = checkWin (6 + column [6] * 7, playerTurn, -6, 6, placement);
			    playerWin = checkWin (6 + column [6] * 7, playerTurn, -7, 7, placement);
			    playerWin = checkWin (6 + column [6] * 7, playerTurn, -8, 8, placement);
			}
			break;

		    default:
			JOptionPane.showMessageDialog (null, "Error: invalid input. Please enter a valid input.");
			break;
		}
	    }
	    if (playerTurn == 1)
	    {
		playerTurn = -1;
	    }
	    else
	    {
		playerTurn = 1;
	    }
	    showTurn ();
	    error = true;
	}
	getWinner();
    }


    private void showToken (int place)
    {
	if (playerTurn == 1)
	    c.setColor (Color.red);
	else
	    c.setColor (Color.yellow);
	c.fillOval (((place - 1) % 7) * 50 + 150, 360 - (place - (place - 1) % 7) / 7 * 50, 40, 40);
	c.setColor (Color.black);
	c.drawOval (((place - 1) % 7) * 50 + 150, 360 - (place - (place - 1) % 7) / 7 * 50, 40, 40);
	c.drawOval (((place - 1) % 7) * 50 + 149, 359 - (place - (place - 1) % 7) / 7 * 50, 42, 42);
    }


    private void drawBoard ()
    {
	c.setColor (Color.blue);
	c.fillRect (140, 100, 360, 310);
	c.setColor (Color.white);
	for (int i = 1 ; i < 43 ; i++)
	{
	    c.fillOval (((i - 1) % 7) * 50 + 150, 360 - (i - (i - 1) % 7) / 7 * 50, 40, 40);
	}
	showTurn ();
    }


    private void showTurn ()
    {
	c.setColor (Color.white);
	c.fillRect (150, 438, 415, 50);
	if (playerTurn == 1)
	{
	    c.setColor (Color.red);
	    c.fillOval (80, 435, 50, 50);
	    c.setColor (Color.black);
	    c.drawOval (80, 435, 50, 50);
	    c.drawOval (79, 434, 52, 52);
	    c.setFont (new Font ("Century", Font.BOLD, 50));
	    c.drawString ("Player 1's Turn!", 150, 478);
	}
	else
	{
	    c.setColor (Color.yellow);
	    c.fillOval (80, 435, 50, 50);
	    c.setColor (Color.black);
	    c.drawOval (80, 435, 50, 50);
	    c.drawOval (79, 434, 52, 52);
	    c.setFont (new Font ("Century", Font.BOLD, 50));
	    c.drawString ("Player 2's Turn!", 150, 478);
	}
    }


    public void instructions ()
    {
	c.setColor (yellow);
	c.fillRect (0, 0, 640, 500);
	c.setColor (black);
	c.setFont (new Font ("Century", Font.BOLD, 18));
	c.drawString ("Connect 4 is a two-player, adversarial game where the objective", 20, 20);
	c.drawString ("is to place 4 uninterrupted tokens of the same color in a row,", 20, 40);
	c.drawString ("column, or diagonal.", 20, 60);

	c.drawString ("When playing, you will have the option to place tokens on any", 20, 100);
	c.drawString ("column (that is not full). The controls are simple, press the", 20, 120);
	c.drawString ("key which corresponds to your desired column. If there is a", 20, 140);
	c.drawString ("winner, this shall be announced once the winning connect 4 is", 20, 160);
	c.drawString ("made. You will then be returned to the menu, where you can", 20, 180);
	c.drawString ("play again.", 20, 200);

	c.drawString ("At the end of each round, the color of the winning player shall", 20, 240);
	c.drawString ("be recorded. At the menu, this record may be viewed in the high", 20, 260);
	c.drawString ("scores section. Players will have the option to reset their score.", 20, 280);

	c.drawString ("Press any key to return to the menu:", 20, 340);
	c.getChar ();
	menu ();
    }


    public void goodbye ()
    {
	c.setColor (yellow);
	c.fillRect (150, 150, 340, 200);
	c.setColor (black);
	c.setFont (new Font ("Century", Font.BOLD, 20));
	c.drawString ("Thank you for using ", 210, 200);
	c.drawString ("this program.", 250, 230);
	c.drawString ("Press any button to exit.", 190, 300);
	c.getChar ();
	System.exit (0);
    }


    public static void main (String[] args)
    {
	Connect4 m = new Connect4 ();
	//m.intro ();
	m.menu ();

	// Place your program here.  'c' is the output console
    } // main method
} // Connect4 class
