package com.becksm64.pong;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener {
	
	//Title menu
	Menu titleMenu;
	
	//Player and computer
	static Paddle player1;
	static Enemy computerPlayer;
	
	//Ball
	static Ball pongBall;
	
	//Scores
	static Score playerScore;
	static Score computerScore;
	
	//RGB values for background
	int r = 0;
	int gr = 0;
	int b = 0;
	
	//Timer
	Timer gameLoopTimer;
	
	//Game going boolean
	static boolean gameOver;
	
	//Winner display message
	String winnerText;
	
	//Score limit
	int scoreLimit;
	
	//Keep track of whether the play option has been selected on the title menu
	boolean gameGoing;
	
	public Game() {
		
		setFocusable(true);//Set focus to the game panel on load
		
		titleMenu = new Menu();//Create the title menu
		addKeyListener(new MKeyInput(titleMenu));
		
		player1 = new Paddle(50, 50);//Create new player at specified x and y position
		addKeyListener(new KeyInput(player1));//Enable player controls
		
		pongBall = new Ball(100, 100);
		
		playerScore = new Score(300, 100);
		computerScore = new Score(1150, 100);
		
		computerPlayer = new Enemy(1420, 50);
		
		//Handle game loop timer	       
	    gameLoopTimer = new Timer(10, this);
	    gameLoopTimer.setInitialDelay(1000);
	    gameLoopTimer.start();
	    
	    gameOver = true;//Set game to start
	    winnerText = "";//Set winner text to blank by defualt
	    
	    scoreLimit = 2;
	    
	    gameGoing = false;
	}
	
	/* DRAW TO SCREEN */
	public void paint(Graphics g){
		
		//Set up graphics and paint the screen
	    super.paint(g);
	    Graphics2D g2d = (Graphics2D) g;
	    Stroke defaultStroke = g2d.getStroke();
	    g2d.setColor(new Color(r, gr, b));
	    g2d.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
	    g2d.setColor(Color.white);
	    
	    if(!gameOver) {
	    	
	    	//Draw Court
		    g2d.setColor(Color.white);
		    g2d.setStroke(new BasicStroke(10));
		    Line2D verticalLine = new Line2D.Float(Main.WIDTH / 2, 0,
	        Main.WIDTH / 2, Main.HEIGHT);
		    g2d.draw(verticalLine);
		    Rectangle courtSquare = new Rectangle((Main.WIDTH / 2) - 100,(Main.HEIGHT / 2) - 120, 200, 200);
		    g2d.draw(courtSquare);
	    	
		    //Draw the player and computer based on updated position
		    g2d.setStroke(defaultStroke);
		    player1.draw(g2d);
		    computerPlayer.draw(g2d);
		    
		    //Drawa the ball based on updated position
		    pongBall.draw(g2d);
		    
		    //Draw the scores for the player and the computer
		    playerScore.draw(g2d);
		    computerScore.draw(g2d);
		    
	    } else if(gameOver && playerScore.getScore() == 0 && computerScore.getScore() == 0) {
	    	
	    	titleMenu.draw(g2d);
	    } else {
	    	
	    	Font font = new Font("TimesRoman", Font.PLAIN, 60);
	    	g2d.setFont(font);
	    	//g2d.drawString(winnerText, (Main.WIDTH / 2) - 400, 100);
	    	
	    	FontMetrics metrics = g2d.getFontMetrics(font);
	        // Determine the X coordinate for the text
	        int x = (Main.WIDTH - metrics.stringWidth(winnerText)) / 2;
	        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	        int y = ((Main.HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
	        // Set the font
	        g.setFont(font);
	        // Draw the String
	        g.drawString(winnerText, x, y);
	    }
	}

	/* GAME LOOP */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Check if someone one the game
		if(playerScore.getScore() >= scoreLimit) {
			
			winnerText = "YOU WIN! PLAY AGAIN? Y/N";//Set winner display message
			gameOver = true;//End game
			
		} else if(computerScore.getScore() >= scoreLimit) {
			
			winnerText = "THE COMPUTER WINS! PLAY AGAIN? Y/N";//Set winner display message
			gameOver = true;//End game
		}
		
		//Check if updates need to be made
		if(!gameOver) {
			
			titleMenu.update();//If needed
			player1.update();//Update player's position
			pongBall.update();
			computerPlayer.update();
			playerScore.update();//Update the player's score
			computerScore.update();//UPdate the computer's score
			repaint();//Repaint the screen with updated positions
			
			//Check if the ball touched the leftmost or rightmost side of the screen
			if(Game.pongBall.x > Main.WIDTH - 40) {
				
				playerScore.score += 1;
				playerScore.scoreText = Integer.toString(playerScore.getScore());
				
			} else if(Game.pongBall.x < 0) {
				
				computerScore.score += 1;
				computerScore.scoreText = Integer.toString(computerScore.getScore());
			}
		} else {
			repaint();
			player1.update();//Update player's position
		}
	}
	
	public static void reset() {
		
		//Reset all objects and return to initial state
		player1.reset();
		computerPlayer.reset();
		pongBall.reset();
		playerScore.reset();
		computerScore.reset();
		gameOver = false;
	}

}
