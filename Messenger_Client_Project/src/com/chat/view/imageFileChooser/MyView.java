/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view.imageFileChooser;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileView;

/**
 *
 * @author Marwa
 */
public class MyView extends FileView {

    @Override
    public Icon getIcon(File f) {
        if (f.getName().toLowerCase().endsWith(".png")) {
            Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Public\\Pictures\\Sample Pictures\\22.jpg").getScaledInstance(10, 10, Image.SCALE_SMOOTH);

            ImageIcon ii = new ImageIcon(img);

            return ii;

        } else {
            return null;
        }

    }
}
