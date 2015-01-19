/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view.renderer;

import com.chat.model.Status;
import com.chat.model.User;
import com.chat.view.ContactPanel;
import com.chat.view.StatusCellPanel;
import com.chat.view.resource.Resource;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class ContactListCellRender implements ListCellRenderer<User> {

        @Override
        public Component getListCellRendererComponent(JList<? extends User> list, User value, int index, boolean isSelected, boolean cellHasFocus) {

            ContactPanel contact = new ContactPanel();

            contact.setUserPicture((ImageIcon) value.getUserPicture());
            contact.setUserName(value.getUserName());
            contact.setUserPicture(value.getUserPicture());

            if (value.getStatus() == User.AVAILABLE) {
                contact.setUserStatusImage(Resource.IMAGE_AVAILABLE);
            } else if (value.getStatus() == User.BUSY) {
                contact.setUserStatusImage(Resource.IMAGE_BUSY);
            } else if (value.getStatus() == User.AWAY) {
                contact.setUserStatusImage(Resource.IMAGE_AWAY);
            } else if (value.getStatus() == User.OFFLINE) {
                contact.setUserStatusImage(Resource.IMAGE_OFFLINE);
            }

            if (isSelected) {
                contact.setBackground(Resource.HIGHLIGHT_COLOR);
            }

            return contact;

        }
    }
