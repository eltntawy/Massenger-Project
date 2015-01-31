
package com.test.chat;

import com.chat.view.AppMenuBarMenuBar;
import java.awt.*;


public class testMenuBar extends javax.swing.JFrame {
    
    AppMenuBarMenuBar testTool;
    
    public testMenuBar() {
//        initComponents();
//        testTool = new AppMenuBarMenuBar(this);
//        //testTool.setSize(100,100);
//        Dimension d= new Dimension(10,30);
//        //Dimension d1= new Dimension(10,10);
//        testTool.setPreferredSize(d);
//        //testTool.setMaximumSize(d);
//        //testTool.setMinimumSize(d1);
//        setJMenuBar(testTool);
//        
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new testMenuBar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
