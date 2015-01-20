/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view;

/**
 *
 * @author ALHUDA
 */
public class AppMenuBar extends javax.swing.JFrame {

    /**
     * Creates new form AppMenuBar
     */
    public AppMenuBar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        appNameMenu = new javax.swing.JMenu();
        newConversationMenuItem = new javax.swing.JMenuItem();
        fileTransferMenuItem = new javax.swing.JMenuItem();
        settingsMenuItem = new javax.swing.JMenuItem();
        HelpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        signOutMenuItem = new javax.swing.JMenuItem();
        QuitMenuItem = new javax.swing.JMenuItem();
        contactsMenu = new javax.swing.JMenu();
        addContactMenuItem = new javax.swing.JMenuItem();
        EditMenuItem = new javax.swing.JMenuItem();
        viewMenuItem = new javax.swing.JMenuItem();
        RemoveMenuItem = new javax.swing.JMenuItem();
        themesMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        appNameMenu.setText("appname");

        newConversationMenuItem.setText("New conversation");
        appNameMenu.add(newConversationMenuItem);

        fileTransferMenuItem.setText("file transfer");
        appNameMenu.add(fileTransferMenuItem);

        settingsMenuItem.setText("settings");
        appNameMenu.add(settingsMenuItem);

        HelpMenuItem.setText("Help");
        appNameMenu.add(HelpMenuItem);

        aboutMenuItem.setText("about");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        appNameMenu.add(aboutMenuItem);

        signOutMenuItem.setText("signout");
        appNameMenu.add(signOutMenuItem);

        QuitMenuItem.setText("Quit");
        appNameMenu.add(QuitMenuItem);

        jMenuBar1.add(appNameMenu);

        contactsMenu.setText("contacts");

        addContactMenuItem.setText("add contact");
        contactsMenu.add(addContactMenuItem);

        EditMenuItem.setText("Edit");
        contactsMenu.add(EditMenuItem);

        viewMenuItem.setText("view");
        contactsMenu.add(viewMenuItem);

        RemoveMenuItem.setText("Remove");
        contactsMenu.add(RemoveMenuItem);

        jMenuBar1.add(contactsMenu);

        themesMenu.setText("themes");
        jMenuBar1.add(themesMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(AppMenuBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppMenuBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppMenuBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppMenuBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AppMenuBar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem EditMenuItem;
    private javax.swing.JMenuItem HelpMenuItem;
    private javax.swing.JMenuItem QuitMenuItem;
    private javax.swing.JMenuItem RemoveMenuItem;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem addContactMenuItem;
    private javax.swing.JMenu appNameMenu;
    private javax.swing.JMenu contactsMenu;
    private javax.swing.JMenuItem fileTransferMenuItem;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem newConversationMenuItem;
    private javax.swing.JMenuItem settingsMenuItem;
    private javax.swing.JMenuItem signOutMenuItem;
    private javax.swing.JMenu themesMenu;
    private javax.swing.JMenuItem viewMenuItem;
    // End of variables declaration//GEN-END:variables
}
