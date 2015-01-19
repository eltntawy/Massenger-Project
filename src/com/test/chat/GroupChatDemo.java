/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.chat;

import com.chat.view.GroupChatPanel;
import javax.swing.JFrame;

/**
 *
 * @author eltntawy
 */
public class GroupChatDemo {
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        
        f.add(new GroupChatPanel());
        
        f.setSize(600,400);
        
        f.setVisible(true);
    }
    
}
