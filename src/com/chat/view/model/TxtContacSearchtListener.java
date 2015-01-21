/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view.model;

import com.chat.model.User;
import com.chat.view.ChatFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author eltntawy
 */
public class TxtContacSearchtListener extends KeyAdapter {

    JList<User> filterList;
    ListComboBoxModel<User> oldModel;
    ListComboBoxModel<User> model;

    public TxtContacSearchtListener(JList<User> filterList) {
        this.filterList = filterList;
        oldModel = new ListComboBoxModel<User>();
        model = (ListComboBoxModel<User>) filterList.getModel();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() instanceof JTextField) {
            int index = filterList.getSelectedIndex();

            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // TODO fire action to open chat with current contact
                
                ChatFrame chatFrame = new ChatFrame();
                
                chatFrame.setVisible(true);
                
                System.out.println("Open chat with : " + filterList.getSelectedValue());
            } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                if (0 < index) {
                    filterList.setSelectedIndex(index - 1);
                }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                if (index < filterList.getModel().getSize()) {
                    filterList.setSelectedIndex(index + 1);
                }
            } else {
                JTextField txtField = (JTextField) e.getSource();
                listFilter(txtField.getText());
            }
        }
    }

    public void listFilter(String enteredText) {
        java.util.List<User> filterArray = new ArrayList<User>();
        for (int i = 0; i < model.getSize(); i++) {
            String name = ((User) model.getElementAt(i)).getFullName();
            String userName = ((User) model.getElementAt(i)).getUserName();
            
                if (name.toLowerCase().contains(enteredText.toLowerCase())) {

                    filterArray.add((User) model.getElementAt(i));
                    

                }

        }
        if (filterArray.size() >= 0) {
            filterList.setModel(new ListComboBoxModel<User>(filterArray));

            if (filterArray.size() > 0) {
                filterList.setSelectedValue(filterArray.get(0), true);
            }
        }
    }

}
