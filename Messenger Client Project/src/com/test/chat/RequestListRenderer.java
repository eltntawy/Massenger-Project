/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chat;

import com.chat.model.User;
import com.chat.view.ContactPanel;
import com.chat.view.resource.Resource;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Marwa
 */
public class RequestListRenderer implements ListCellRenderer<String> {

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel request = new JLabel(value);
        JButton confirm = new JButton("Confirm");
        JButton ignore = new JButton("Ignore");
        FriendRequestPanel pan = new FriendRequestPanel();
//        pan.setLayout(new FlowLayout());
//
//        JLabel tst = new JLabel();
//        tst.add(request);
//        tst.add(confirm);
//        tst.add(ignore);
        // tst.add(pan);
        pan.setLabel(value);
        
        pan.setBackground(Color.white);
        if (isSelected) {
            pan.setBackground(Resource.HIGHLIGHT_COLOR);
        }

        return pan;

    }
}
