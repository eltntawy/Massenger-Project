package com.chat.view;

import java.awt.Container;
import java.awt.MenuBar;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

/**
 *
 * @author ALHUDA
 */
public class AppMenuBarMenuBar extends JMenuBar
 {

public AppMenuBarMenuBar() {
        initComponents();
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

        

        /*javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

       */ 
    
    
    
    
    
    
    
    }

    
}
