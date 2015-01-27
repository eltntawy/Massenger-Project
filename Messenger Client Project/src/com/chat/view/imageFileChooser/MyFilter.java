/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view.imageFileChooser;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Marwa
 */
public class MyFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        // throw new UnsupportedOperationException("Not supported yet.");
        //  f.getName().toLowerCase().endsWith(".jpg") || 
        if (f.getName().toLowerCase().endsWith(".png")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDescription() {
        return "JPG Pictures .jpg";
    }
}
