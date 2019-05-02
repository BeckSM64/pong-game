package com.becksm64.pong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
	
	static int WIDTH = 1500;
	static int HEIGHT = 900;
	static JFrame frame;
	static Game game;
	
	public static void main(String[] args) {
		
		//Main
		frame = new JFrame("Pong Game");
		frame.pack();
		frame.setSize(WIDTH, HEIGHT);	
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    game = new Game();
	    frame.add(game);
		frame.setVisible(true);
		
	}
	
	/*public static void reset() {
		
		//Reset all objects and return to initial state
		game.reset();
	}*/
}
