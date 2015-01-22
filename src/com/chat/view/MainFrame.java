/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import com.test.chat.TestLookAndFeelForm;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author eltntawy
 */
public class MainFrame extends JFrame  implements WindowListener{

    public MainFrame() {

        super("Messenger");

        setSize(315, 700);
        setMinimumSize(new Dimension(315, 600));

        setPreferredSize(new Dimension(315, 600));

        setMinimumSize(new Dimension(315, 500));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initLookAndFeel(this);

        addWindowListener(this);
    }
    
    
    public void initLookAndFeel (final JFrame frame){
        try {

            //UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
            //UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
            
            //UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
            UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                // TODO Auto-generated method stub
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });
    }

    public void initMainMessangerPanel() {
        setVisible(false);
        
        setResizable(true);
        add(new MainPanel(this));

        setVisible(true);
    }

    public void initSignInPanel() {

        setVisible(false);
        
        add(new SignInPanel(this));
        
        setVisible(true);
    }

    public void initSignupPanel() {
        setVisible(false);
        
        add(new SignUpPanel(this));
        
        setVisible(true);
    }

    public void initForgitPasswordPanel() {
        setVisible(false);
        
        
        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosed(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
