package com.becksm64.pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle extends GlobalPosition {
	
	int velX, velY;
	Rectangle playerBounds;
	int originalX, originalY;

	public Paddle(int x, int y) {
		
		super(x, y);
		velX = 0;
		velY = 0;
		originalX = x;
		originalY = y;
		playerBounds = getBounds();
	}
	
	public void update() {
		
		playerBounds = getBounds();
		x += velX;
		y += velY;
		constrainToWindow();
	}
	
	public Rectangle getBounds(){
		
	    return new Rectangle(x, y, 25, 150);
	}
	
	public void draw(Graphics2D g2d){
		
	     //g2d.drawImage(getPlayerImage(), x, y, null);
	     g2d.setColor(Color.white);
	     g2d.fillRect(x, y, 25, 150);
	}
	  
	public int getXPosition(){
		
		return x;
	}
	  
	public int getYPosition(){
		
		return y;
	}
	  
	public void setVelX(int velX){
		
		this.velX = velX;
	}
	  
	public void setVelY(int velY){
		
		this.velY = velY;
	}
	
	public void keyPressed(KeyEvent e){
		
	    //Keyboard input
		int key = e.getKeyCode();
		      
		if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
			velY = -5;
		} else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
			velY = 5;
		}
		
		if(Game.gameOver && key == KeyEvent.VK_Y) {
			Game.reset();
		} else if (Game.gameOver && key == KeyEvent.VK_N){
			
		}
	}
	  
	public void keyReleased(KeyEvent e){
		  
	    //Stops movement when keys are no longer being pressed
	    int key = e.getKeyCode();
	    
	    if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP){
	    	velY = 0;
	    } else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN){
	    	velY = 0;
	    }
	}
	
	public void constrainToWindow() {
			
		//Collision
	    if(y < 0){
	    	y = 0;
	    }
	    if(y > Main.HEIGHT - 180){
	    	y = Main.HEIGHT - 180;
	    }
	}
	
	public void reset() {
		velX = 0;
		velY = 0;
		x = originalX;
		y = originalY;
		playerBounds = getBounds();
	}
}
