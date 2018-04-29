package com.becksm64.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Menu {
	
	int optionCounter;
	String playText;
	String quitText;

	public Menu() {
		
		optionCounter = 0;
		playText = "PLAY";
		quitText = "QUIT";
		
	}
	
	public void draw(Graphics g2d) {
		
		Font font = new Font("TimesRoman", Font.PLAIN, 60);
		FontMetrics metrics = g2d.getFontMetrics(font);
        	
		//Check which color to set for menu option based on which option is selected
		if(optionCounter % 2 == 0) {
			g2d.setColor(Color.GREEN);
		} else {
			g2d.setColor(Color.WHITE);
		}
		
        // Determine the X coordinate for the text
		int x = (Main.WIDTH - metrics.stringWidth(playText)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = ((Main.HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g2d.setFont(font);
        g2d.drawString(playText, x, y);
        
        if(optionCounter % 2 == 1) {
			//highlight play
        	g2d.setColor(Color.GREEN);
		} else {
			//highlight quit
			g2d.setColor(Color.WHITE);
		}
        
        // Determine the X coordinate for the text
        x = (Main.WIDTH - metrics.stringWidth(quitText)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        y = ((Main.HEIGHT - metrics.getHeight()) / 2) + metrics.getAscent() + 75;
        // Set the font
        g2d.drawString(quitText, x, y);
        
	}
	
	public void update() {
		
	}
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
	    if(key == KeyEvent.VK_ENTER && Game.gameOver) {
	    	
	    	Game.gameOver = false;//Start the game if enter is pressed on the play option
	    	
	    } else if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) && Game.gameOver) {
	    	
	    	optionCounter++;
	    }
	}
	  
	public void keyReleased(KeyEvent e){
		  
	    
	}
}
