package com.becksm64.pong;

import java.awt.Color;
import java.awt.Graphics2D;

public class Score extends GlobalPosition {

	int score;
	String scoreText;
	
	public Score(int x, int y) {
		
		super(x, y);
		scoreText = "0";
		score = 0;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics2D g2d){
		
	     g2d.setColor(Color.white);
	     g2d.drawString(scoreText, x, y);
	}
	
	public int getScore() {
		
		return score;
	}
	
	public void reset() {
		scoreText = "0";
		score = 0;
	}
}
