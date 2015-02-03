/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view.renderer;

import com.chat.view.resource.Resource;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Marwa
 */
public class EditorPanelRenderer implements ListCellRenderer<String> {

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel fontLabel = new JLabel(value);
        fontLabel.setOpaque(true);
        Font font = null;
        font = new Font(value, Font.BOLD, 17);
        fontLabel.setFont(font);

        if (isSelected) {
            fontLabel.setBackground(Resource.HIGHLIGHT_COLOR);
        } else {
            fontLabel.setBackground(Color.WHITE);
        }

        return fontLabel;
    }

}


