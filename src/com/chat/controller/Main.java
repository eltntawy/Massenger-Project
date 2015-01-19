package com.chat.controller;

import com.chat.view.MainPanel;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaWhiteVisionLookAndFeel;
import com.test.chat.TestLookAndFeelForm;
import java.awt.Dimension;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    
    public static void initLookAndFeel(final JFrame frame) {
        try {

            //UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
            //UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
            
            UIManager.setLookAndFeel(new SyntheticaStandardLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());

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

    public static void main(String arg []) {
	
        
        final JFrame frame = new JFrame("Massanger");
        initLookAndFeel(frame);
        frame.setSize(315,800);
        frame.add(new MainPanel());
        frame.setMinimumSize(new Dimension(315, 500));
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        frame.setVisible(true);
        
        
    }
}
