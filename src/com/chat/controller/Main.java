package com.chat.controller;

import com.chat.view.MainFrame;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import javax.swing.JFrame;

public class Main {

    
    static void renderSplashFrame(Graphics2D g, int frame) {
	final String[] comps = { "", ".", "..","..."};
	g.setComposite(AlphaComposite.Clear);
	g.fillRect(0, 0, 512, 512);
	g.setPaintMode();
	g.setColor(Color.black);
	g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	g.drawString("Loading" + comps[(frame / 5) % 4] + "", 250, 300);
    }

    public static void initSplashScreen(JFrame frame) {

	SplashScreen splashScreen = SplashScreen.getSplashScreen();

	if (splashScreen == null) {
	    System.err.println("Error: Messengers.initSplashScreen() cannot initialize SplashScreen");
	    return;
	}

	Graphics2D graphics = splashScreen.createGraphics();

	for (int i = 0; i < 80; i++) {

	    renderSplashFrame(graphics, i);
	    splashScreen.update();
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	splashScreen.close();
	frame.toFront();
        

    }
    
    public static void main(String arg[]) {

       
        MainFrame frame = new MainFrame();
        initSplashScreen(frame);
        frame.initSignInPanel();
        
        

    }
}
