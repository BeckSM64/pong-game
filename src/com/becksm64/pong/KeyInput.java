package com.becksm64.pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
	Paddle p;
  
	public KeyInput(Paddle p){
		this.p = p;
	}
	  
	public void keyPressed(KeyEvent e){
		p.keyPressed(e);
	}
	  
	public void keyReleased(KeyEvent e){
		p.keyReleased(e);
	}
}