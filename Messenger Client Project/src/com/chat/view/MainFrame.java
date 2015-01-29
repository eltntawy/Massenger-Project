/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.chat.controller.AuthenticationClientController;
import com.chat.rmi.ChatClientService;
import com.chat.rmi.ChatServerService;

import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;

/**
 *
 * @author eltntawy
 */
public class MainFrame extends JFrame implements WindowListener {

    private JPanel currentPane;

    public MainFrame() {

	super("Messenger");

	setSize(315, 700);
	setMinimumSize(new Dimension(315, 600));

	setPreferredSize(new Dimension(315, 600));

	setMinimumSize(new Dimension(315, 500));

	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	initLookAndFeel();

	addWindowListener(this);
    }

    public void removeCurrentPanel() {
	if (currentPane != null) {
	    
	    remove(currentPane);
	    currentPane = null;
	    repaint();
	    validate();
	    setVisible(true);
	}
    }

    public void addCurrentPanel(JPanel panel) {
	if(currentPane != null) {
	    removeCurrentPanel();
	}
	
	
	add(panel);
	currentPane = panel;
	repaint();
	validate();
	setVisible(true);

    }

    void renderSplashFrame(Graphics2D g, int frame) {
	final String[] comps = { "", ".", "..", "..." };
	g.setComposite(AlphaComposite.Clear);
	g.fillRect(0, 0, 512, 512);
	g.setPaintMode();
	g.setColor(Color.black);
	g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	g.drawString("Loading" + comps[(frame / 5) % 4] + "", 250, 300);
    }

    public void initSplashScreen() {

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
	this.toFront();

    }

    public void initLookAndFeel() {
	try {

	    // UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
	    // UIManager.installLookAndFeel("SeaGlass",
	    // "com.seaglasslookandfeel.SeaGlassLookAndFeel");

	    // UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
	    UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());

	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	SwingUtilities.invokeLater(new Runnable() {

	    public void run() {
		// TODO Auto-generated method stub
		SwingUtilities.updateComponentTreeUI(MainFrame.this);
	    }
	});
    }

    @Override
    public void windowOpened(WindowEvent e) {
	// throw new UnsupportedOperationException("Not supported yet."); //To
	// change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
	
    }

    @Override
    public void windowClosed(WindowEvent e) {
	// throw new UnsupportedOperationException("Not supported yet."); //To
	// change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
	// throw new UnsupportedOperationException("Not supported yet."); //To
	// change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
	// throw new UnsupportedOperationException("Not supported yet."); //To
	// change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
	// throw new UnsupportedOperationException("Not supported yet."); //To
	// change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
	// throw new UnsupportedOperationException("Not supported yet."); //To
	// change body of generated methods, choose Tools | Templates.
    }

}
