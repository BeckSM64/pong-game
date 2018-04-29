package com.becksm64.pong;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MKeyInput extends KeyAdapter{
	
	Menu m;
  
	public MKeyInput(Menu m){
		this.m = m;//Set menu to passed menu
	}

	public void keyPressed(KeyEvent e){
		m.keyPressed(e);
	}
	  
	public void keyReleased(KeyEvent e){
		m.keyReleased(e);
	}
}
