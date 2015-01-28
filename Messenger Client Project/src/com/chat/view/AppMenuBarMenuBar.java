package com.chat.view;

import com.chat.controller.MessengerClientController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class AppMenuBarMenuBar extends JMenuBar implements ActionListener {

    JFrame parentFrame;
    MessengerClientController messengerController;

    public AppMenuBarMenuBar(JFrame frame, MessengerClientController messengerController) {
        parentFrame = frame;
        initComponents();
        this.messengerController = messengerController;
    }

    private void initComponents() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

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

        newConversationMenuItem.addActionListener(this);
        fileTransferMenuItem.addActionListener(this);
        settingsMenuItem.addActionListener(this);
        HelpMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);
        signOutMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    messengerController.doSignOut();
                    parentFrame.setJMenuBar(null);
                    parentFrame.revalidate();
                } catch (RemoteException ex) {
                    //  Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                    // JOptionPane.showMessageDialog(parentFrame, ex);
                    ex.printStackTrace();
                }
            }
        });
        QuitMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int x = JOptionPane.showConfirmDialog(parentFrame, "Are you sure ? ");
                if (x == JOptionPane.YES_OPTION) {
                    try {
                        messengerController.doSignOut();
                        parentFrame.setJMenuBar(null);
                        parentFrame.revalidate();
                    } catch (RemoteException ex) {
                        //  Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                        // JOptionPane.showMessageDialog(parentFrame, ex);
                        ex.printStackTrace();
                    }
                }
            }
        });
        contactsMenu.addActionListener(this);
        addContactMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txt = messengerController.getItemfocus();
                txt.requestFocusInWindow();
            }
        });
        EditMenuItem.addActionListener(this);
        viewMenuItem.addActionListener(this);
        RemoveMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txt = messengerController.getItemfocus();
                txt.requestFocusInWindow();
            }
        } );
        themesMenu.addActionListener(this);

        // get system available lookAndFeel
        LookAndFeelInfo[] lookAndFeelArray = UIManager.getInstalledLookAndFeels();

        for (LookAndFeelInfo l : lookAndFeelArray) {

            JMenuItem item = new JMenuItem();
            item.setText(l.getName());
            item.setToolTipText(l.getClassName());

            item.addActionListener(this);
            themesMenu.add(item);

        }

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

        appNameMenu.add(aboutMenuItem);

        signOutMenuItem.setText("signout");
        appNameMenu.add(signOutMenuItem);

        QuitMenuItem.setText("Quit");
        appNameMenu.add(QuitMenuItem);

        this.add(appNameMenu);

        contactsMenu.setText("contacts");

        addContactMenuItem.setText("add contact");
        contactsMenu.add(addContactMenuItem);

        EditMenuItem.setText("Edit");
        contactsMenu.add(EditMenuItem);

        viewMenuItem.setText("view");
        contactsMenu.add(viewMenuItem);

        RemoveMenuItem.setText("Remove");
        contactsMenu.add(RemoveMenuItem);

        this.add(contactsMenu);

        themesMenu.setText("themes");
        this.add(themesMenu);

    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {

            JMenuItem item = (JMenuItem) e.getSource();

            try {
                if (item != null && item.getToolTipText() != null) {
                    UIManager.setLookAndFeel(item.getToolTipText());
                    SwingUtilities.updateComponentTreeUI(parentFrame);
                }
            } catch (Exception e1) {

                e1.printStackTrace();
            }
        }
    }

}
