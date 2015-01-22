/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.chat;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author yomna
 */
public class Main {
    public static void main(String[] args) throws Exception {
    JEditorPane editor = new JEditorPane("text/html",
        "<H1>A!</H1><P><FONT COLOR=blue>blue</FONT></P>");
    editor.setEditable(false);
    JScrollPane pane = new JScrollPane(editor);
    JFrame f = new JFrame("HTML Demo");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(pane);
    f.setSize(800, 600);
    f.setVisible(true);
    }
}
