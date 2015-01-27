/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view.renderer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Marwa
 */
public class EditorPanelColorRenderer implements ListCellRenderer<String> {

    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel colorLabel = new JLabel(value);
        colorLabel.setOpaque(true);
        Color selectedColor = null;

        if (isSelected) {
            switch (value) {
                case "RED":
                    selectedColor = Color.RED;
                    break;
                case "GREEN":
                    selectedColor = Color.GREEN;
                    break;
                case "BLUE":
                    selectedColor = Color.BLUE;
                    break;
                case "BLACK":
                    selectedColor = Color.BLACK;
                    break;
            }
            colorLabel.setBackground(selectedColor);
            colorLabel.setForeground(Color.WHITE);

        } else {
            colorLabel.setBackground(Color.WHITE);
        }
        return colorLabel;
    }

}
