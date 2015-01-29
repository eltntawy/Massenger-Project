/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

import com.test.chat.*;
import com.chat.view.ContactPanel;
import com.test.chat.RequestListRenderer;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Marwa
 */
public class getFriendRequestList extends javax.swing.JDialog {

    /**
     * Creates new form getFriendRequestList
     */
    JScrollPane pane = new JScrollPane();
    JList list = new JList();
    JPanel panel = new JPanel();
    JPanel label = new JPanel();
    JLabel lab = new JLabel();
    ArrayList<JPanel> arr = new ArrayList<>();

    public getFriendRequestList(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        FriendRequestPanel pan = new FriendRequestPanel();
        this.setLayout(new CardLayout());
        c.gridx = 0;
        c.gridy = 0;
        lab.setText("Friend List");
        lab.setBackground(Color.WHITE);
        lab.setFont(new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 20));
//        lab.setHorizontalTextPosition(SwingConstants.NORTH);
        panel.add(lab, c);
        panel.setSize(parent.getWidth() * 2, parent.getHeight() * 2);

        for (int i = 0; i < 10; i++) {
            c.gridx = 0;
            c.gridy = i + 2;
            pan = new FriendRequestPanel();
            pan.setLabel(i + "");
            pan.setSize(parent.getWidth(), parent.getHeight());
            pan.revalidate();
            pan.repaint();
            panel.add(pan, c);

            System.out.println("i" + i);
            pane.setViewportView(panel);
            this.getContentPane().add(pane);
            this.getContentPane().revalidate();
            this.getContentPane().repaint();
            pack();
        }
//        this.setLayout(new CardLayout());
//        setSize(345, 222);
//        pan.setSize(parent.getWidth() * 2, parent.getHeight() * 2);
//        this.getContentPane().add(pan);
        this.setLocationRelativeTo(parent);
        this.pack();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Friend List");
        setMaximumSize(new java.awt.Dimension(350, 380));
        setMinimumSize(new java.awt.Dimension(350, 380));
        setPreferredSize(new java.awt.Dimension(350, 380));
        setResizable(false);

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(getFriendRequestList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(getFriendRequestList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(getFriendRequestList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(getFriendRequestList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                getFriendRequestList dialog = new getFriendRequestList(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
