package com.chat.view;

import com.chat.controller.MessengerClientController;
import com.chat.model.User;
import com.chat.view.model.ListComboBoxModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    List<User> userList = null;

    public AppMenuBarMenuBar(JFrame frame, MessengerClientController messengerController) {
        parentFrame = frame;
        initComponents();
        this.messengerController = messengerController;
    }

    private void initComponents() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        jMenuBar1 = new javax.swing.JMenuBar();
        appNameMenu = new javax.swing.JMenu();
        help = new javax.swing.JMenu();
        helpItem = new javax.swing.JMenuItem();
        newConversationMenuItem = new javax.swing.JMenuItem();
        fileTransferMenuItem = new javax.swing.JMenuItem();
        settingsMenuItem = new javax.swing.JMenuItem();
        HelpMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        signOutMenuItem = new javax.swing.JMenuItem();
        QuitMenuItem = new javax.swing.JMenuItem();
        contactsMenu = new javax.swing.JMenu();
        addContactMenuItem = new javax.swing.JMenuItem();
        showContactMenuItem = new javax.swing.JMenuItem();
        EditMenuItem = new javax.swing.JMenuItem();
        viewMenuItem = new javax.swing.JMenuItem();
        RemoveMenuItem = new javax.swing.JMenuItem();
        themesMenu = new javax.swing.JMenu();

        newConversationMenuItem.addActionListener(this);
        fileTransferMenuItem.addActionListener(this);
        settingsMenuItem.addActionListener(this);
        HelpMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                HelpDialogeForm dialoge=new HelpDialogeForm(parentFrame, true);
                dialoge.setLocationRelativeTo(parentFrame);
                dialoge.setVisible(true);
                dialoge.dispose();
            }
        });
        aboutMenuItem.addActionListener(this);
        signOutMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    messengerController.doSignOut();
                    parentFrame.setJMenuBar(null);
                    parentFrame.revalidate();
                } catch (RemoteException | SQLException ex) {
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
                    } catch (RemoteException | SQLException ex) {
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
        showContactMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    userList = messengerController.getRequestContactList();
                    getFriendRequestList xc = new getFriendRequestList(parentFrame, true, userList, messengerController);
                    xc.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(AppMenuBarMenuBar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AppMenuBarMenuBar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        EditMenuItem.addActionListener(this);
        viewMenuItem.addActionListener(this);
        RemoveMenuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField txt = messengerController.getItemfocus();
                txt.requestFocusInWindow();
                User user = messengerController.getPanel().getSelectedValueFromList();
                try {
                    boolean isFriend = messengerController.isFriendOfUser(messengerController.getUser(),user);
                    if (messengerController.checkUserId(user) && isFriend) {
                        ConfirmRemoveFriendDialoge1 confirm = new ConfirmRemoveFriendDialoge1(parentFrame, true, user);

                        confirm.setVisible(true);

                        boolean flag = confirm.getPressedbutton();

                        if (flag) {
                            try {
                                
                                messengerController.DeleteContactFromUser(user);
                               // ((ListComboBoxModel<User>)user).removeElement(user);

                                messengerController.initContactListForOtherUser(user);
                                messengerController.getPanel().initContactList();
                            } catch (RemoteException ex) {
                                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (SQLException ex) {
                                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {

                        }
                    } else {
                	
                	messengerController.getPanel().errMessage("you can not remove this contact");
                	
                    }

                } catch (RemoteException | SQLException ex) {
                    Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
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

        helpItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    AboutTheMessenger about=new AboutTheMessenger(parentFrame, true);
                    about.setLocationRelativeTo(parentFrame);
                    about.setVisible(true);
                    about.dispose();
            }
        });

        appNameMenu.setText("Appname");

//        newConversationMenuItem.setText("New conversation");
//        appNameMenu.add(newConversationMenuItem);
//
//        fileTransferMenuItem.setText("file transfer");
//        appNameMenu.add(fileTransferMenuItem);
//
//        settingsMenuItem.setText("settings");
//        appNameMenu.add(settingsMenuItem);
        HelpMenuItem.setText("Help ...");
        appNameMenu.add(HelpMenuItem);

//        aboutMenuItem.setText("about");
//
//        appNameMenu.add(aboutMenuItem);
        signOutMenuItem.setText("Signout");
        appNameMenu.add(signOutMenuItem);

        QuitMenuItem.setText("Quit");
        appNameMenu.add(QuitMenuItem);

        this.add(appNameMenu);

        contactsMenu.setText("Contacts");

        addContactMenuItem.setText("Add contact");
        contactsMenu.add(addContactMenuItem);

        showContactMenuItem.setText("Show Friend Request...");
        contactsMenu.add(showContactMenuItem);

//        EditMenuItem.setText("Edit");
//        contactsMenu.add(EditMenuItem);
//
//        viewMenuItem.setText("view");
//        contactsMenu.add(viewMenuItem);
        RemoveMenuItem.setText("Remove");
        contactsMenu.add(RemoveMenuItem);

        this.add(contactsMenu);

        themesMenu.setText("Themes");
        this.add(themesMenu);

        help.setText("About");
        help.add(helpItem);
        helpItem.setText("About ...");
        this.add(help);

    }

    private javax.swing.JMenuItem EditMenuItem;
    private javax.swing.JMenuItem HelpMenuItem;
    private javax.swing.JMenuItem QuitMenuItem;
    private javax.swing.JMenuItem RemoveMenuItem;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem addContactMenuItem;
    private javax.swing.JMenuItem showContactMenuItem;
    private javax.swing.JMenu appNameMenu;
    private javax.swing.JMenu contactsMenu;
    private javax.swing.JMenuItem fileTransferMenuItem;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem newConversationMenuItem;
    private javax.swing.JMenuItem settingsMenuItem;
    private javax.swing.JMenuItem signOutMenuItem;
    private javax.swing.JMenu themesMenu;
    private javax.swing.JMenuItem viewMenuItem;
    private javax.swing.JMenu help;
    private javax.swing.JMenuItem helpItem;

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
