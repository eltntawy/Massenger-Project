package com.chat.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LinearGradientPaint;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import com.chat.model.User;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.NoFixedFacet;

public class NotificationPopup extends JWindow {
    private final LinearGradientPaint lpg;

    private static int count=0; 
    User user ;
    public NotificationPopup(User u) {
	this.user = u;
	//setUndecorated(true);
	setSize(300, 60);

	// size of the screen
	final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	// height of the task bar
	final Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
	final int taskBarSize = scnMax.bottom;

	int y = (screenSize.height - taskBarSize - getHeight()) - count*60;
	setLocation(screenSize.width - getWidth(),y );
	count++;
	// background paint
	lpg = new LinearGradientPaint(0, 0, 0, getHeight() / 2, 
		new float[] { 0f, 0.5f, 1f }, 
		new Color[] { 
	    new Color(1f, 1f, 1f), 
	    new Color(1f, 1f, 1f), 
	    new Color(1f, 1f, 1f) 
	    });
	

	// blue background panel
	setContentPane(new BackgroundPanel());
	init(user);
	
	new Thread() {
	  @Override
	public void run() {
	    // TODO Auto-generated method stub
	    for(int i = 0 ; i < 30;i ++ ) {
		try {
		    Thread.sleep(1000);
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	    dispose();
	    count--;
	}  
	}.start();
	
	this.setAlwaysOnTop(true);
    }

    public void setUser(User user) {

	init(user);
    }

    private void init(User user) {

	final Container c = getContentPane();
	c.setLayout(new GridBagLayout());

	final GridBagConstraints constraints = new GridBagConstraints();
	constraints.gridx = 0;
	constraints.gridy = 0;
	constraints.weightx = 1.0f;
	constraints.weighty = 1.0f;
	constraints.insets = new Insets(5, 5, 5, 5);
	constraints.fill = GridBagConstraints.BOTH;

	// final JLabel l = new JLabel("You have got 2 new Messages.");
	ContactPanel contactPanel = new ContactPanel(user);
	contactPanel.setOpaque(false);

	c.add(contactPanel, constraints);

	constraints.gridx++;
	constraints.weightx = 0f;
	constraints.weighty = 0f;
	constraints.fill = GridBagConstraints.NONE;
	constraints.anchor = GridBagConstraints.NORTH;

	final JButton b = new JButton(new AbstractAction("x") {

	    @Override
	    public void actionPerformed(final ActionEvent e) {
		dispose();
		count--;
	    }
	});

	b.setOpaque(false);
	b.setMargin(new Insets(1, 4, 1, 4));
	b.setFocusable(false);

	c.add(b, constraints);

	setVisible(true);

    }

    private class BackgroundPanel extends JPanel {
	public BackgroundPanel() {
	    setOpaque(true);
	}

	@Override
	protected void paintComponent(final Graphics g) {
	    final Graphics2D g2d = (Graphics2D) g;
	    // background
	    g2d.setPaint(lpg);
	    g2d.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
	    g2d.setColor(Color.BLACK);

	    // border
	    g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
    }

    public static void main(final String[] args) {

	User user = new User();
	NotificationPopup notification = new NotificationPopup(user);

    }
}