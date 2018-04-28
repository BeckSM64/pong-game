package com.becksm64.pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball extends GlobalPosition {

	Rectangle ballBounds;
	
	int velX, velY;
	int originalX, originalY;
	
	public Ball(int x, int y) {
		
		super(x, y);
		ballBounds = getBounds();
		velX = 5;
		velY = 5;
	}
	
	public void update() {
		
		//Set the bounds of the ball relative to updated position
		ballBounds = getBounds();
		
		//Keep ball in the window
		constrainToWindow();
		
		//Update position of ball
		x += velX;
		y += velY;
		
		//Check collision with paddles
		collision();
		
	}
	
	public Rectangle getBounds(){
	    return new Rectangle(x, y, 25, 25);
	}
	
	public void draw(Graphics2D g2d){
	     //g2d.drawImage(getPlayerImage(), x, y, null);
	     g2d.setColor(Color.white);
	     g2d.fillRect(x, y, 25, 25);
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
	
	public void constrainToWindow() {
		
	    if(x < 0){
	    	
	    	//Check if ball is off the screen and put it back if it is
	    	x = 300;
	    	y = 100;
	    	velX *= -1;
	    } 
	    if(x > Main.WIDTH - 40){
	    	x = 950;
	    	y = 100;
	    	velX *= -1;
	    }
	    if(y < 0){
	    	velY *= -1;
	    }
	    if(y > Main.HEIGHT - 60){
	    	velY *= -1;
	    }
	}
	
	public void collision(){
		
		if(getBounds().intersects(Game.player1.getBounds()) || getBounds().intersects(Game.computerPlayer.getBounds())){

			velX *= -1;
		}
	}
	
	public void reset() {
		velX = 5;
		velY = 5;
		x = 300;
		y = 700;
		ballBounds = getBounds();
	}
}
