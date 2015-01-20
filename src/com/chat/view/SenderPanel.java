/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chat.view;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author yomna
 */
public class SenderPanel extends javax.swing.JPanel {

    /**
     * Creates new form SenderPanel
     */
    public SenderPanel() {
        initComponents();
    }

    public Icon getSenderImagelbl() {
        return SenderImagelbl.getIcon();
    }

    public String getSenderNamelbl() {
        return SenderNamelbl.getText();
    }

    public void setSenderImagelbl(Icon UserPicture) {
        this.SenderImagelbl.setIcon(UserPicture);
    }

    public void setSenderNamelbl(String UserName) {
        this.SenderNamelbl.setText(UserName);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SenderNamelbl = new javax.swing.JLabel();
        SenderImagelbl = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SenderNamelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SenderImagelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SenderImagelbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SenderNamelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel SenderImagelbl;
    private javax.swing.JLabel SenderNamelbl;
    // End of variables declaration//GEN-END:variables
}
