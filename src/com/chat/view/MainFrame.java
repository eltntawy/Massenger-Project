/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author eltntawy
 */
public class MainFrame extends JFrame {

    public MainFrame() {

        super("Messenger");
        initLookAndFeel(this);
        setSize(315, 700);
        setMinimumSize(new Dimension(315, 600));

        setPreferredSize(new Dimension(315, 600));

        setMinimumSize(new Dimension(315, 500));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void initMainMessangerPanel() {

        add(new MainPanel());

        setVisible(true);
    }

    public void initSignInPanel() {

        add(new SignInPanel(this));
        setResizable(false);
        setVisible(true);
    }

    public void initSignupPanel() {

        add(new SignUpPanel(this));
        setVisible(true);
    }

    public void initForgitPasswordPanel() {

        setVisible(true);
    }

    public static void initLookAndFeel(final JFrame frame) {
        try {

            //UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
            //UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
            //UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
            UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());

        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });
    }

}
