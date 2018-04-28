package com.becksm64.pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Enemy extends GlobalPosition {
	
	int velX, velY;
	int originalX, originalY;
	Rectangle playerBounds;

	public Enemy(int x, int y) {
		
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
		move();
	}
	
	private void move() {
		
		//Check if ball is coming towards the enemy
		if(Game.pongBall.velX > 0) {
			
			//Move the enemy paddle towards the position of the ball
			if(this.y < Game.pongBall.getYPosition()) {
				velY = 4;
			} else {
				velY = -4;
			}
		} else {
			
			//Move the enemy paddle towards the middle
			if(this.y < (Main.HEIGHT / 2) - 120) {//Check if paddle is above the center of the court
				velY = 4;
			} else if (this.y > (Main.HEIGHT / 2) - 120) {
				velY = -4;
			} else if (this.y == (Main.HEIGHT / 2) - 120){
				velY = 0;
			}
		}
	}

	public Rectangle getBounds(){
		
	    return new Rectangle(x, y, 25, 150);
	}
	
	public void draw(Graphics2D g2d){
		
	     //g2d.drawImage(getPlayerImage(), x, y, null);
	     g2d.setColor(Color.white);
	     g2d.fillRect(x, y, 25, 150);
	     g2d.setColor(Color.black);
	     g2d.draw(playerBounds);
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
		velY = 5;
		x = originalX;
		y = originalY;
		playerBounds = getBounds();
	}
}

