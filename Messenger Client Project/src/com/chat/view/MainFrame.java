/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.chat.controller.AuthenticationClientController;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackMoonLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;

/**
 *
 * @author eltntawy
 */
public class MainFrame extends JFrame implements WindowListener {

    private JPanel currentPane;
    private AuthenticationClientController authenticationClientController;
    private boolean isExit = false;

    public MainFrame() {

	super("Messenger");

	setSize(340, 700);
	setMinimumSize(new Dimension(340, 600));

	setPreferredSize(new Dimension(340, 600));

	setMinimumSize(new Dimension(340, 500));

	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
	if (currentPane != null) {
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
	    System.err
		    .println("Error: Messengers.initSplashScreen() cannot initialize SplashScreen");
	    return;
	}

	Graphics2D graphics = splashScreen.createGraphics();

	for (int i = 0; i < 80; i++) {

	    renderSplashFrame(graphics, i);
	    splashScreen.update();
	    try {
		Thread.sleep(10);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	splashScreen.close();
	this.toFront();

    }

    public void initLookAndFeel() {
	try {

	    UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
	    // UIManager.installLookAndFeel("SeaGlass",
	    // "com.seaglasslookandfeel.SeaGlassLookAndFeel");
	    // UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
	    // UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
	    // UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());

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
	try {
	    if ((this.authenticationClientController != null && isExit)
		    || (this.authenticationClientController == null && !isExit)
		    || (this.authenticationClientController != null && !isExit)) {

		if (JOptionPane
			.showConfirmDialog(this, "Are you sure to exit?") == JOptionPane.OK_OPTION) {
		    if (this.authenticationClientController != null)
			this.authenticationClientController.doSignOut();

		    this.dispose();
		    System.exit(0);
		}
	    } else {
		if (JOptionPane.showConfirmDialog(this,
			"Are you sure to sign out?") == JOptionPane.OK_OPTION) {
		    this.authenticationClientController.doSignOut();
		    this.authenticationClientController.showSignIn();
		    isExit = true;
		}

	    }
	} catch (RemoteException | SQLException ex) {
	    ex.printStackTrace();
	    this.dispose();
	}
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

    public AuthenticationClientController getAuthenticationClientController() {
	return authenticationClientController;
    }

    public void setAuthenticationClientController(
	    AuthenticationClientController authenticationClientController) {
	this.authenticationClientController = authenticationClientController;
    }

}
