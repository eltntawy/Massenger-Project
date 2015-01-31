/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chat.view;

import com.chat.model.Message;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

/**
 *
 * @author yomna
 */
public class ChatEditorPane extends javax.swing.JPanel {

    /**
     * Creates new form ChatEditorPane
     */
    private String SenderName;
    private String message;
    private String time;
    private Font font;
    private Color color;
    private int size;
    Map<Color, String> colorMap = new HashMap<Color, String>();
    
    public ChatEditorPane() {
        initComponents();
        
        
    }

/*    public ChatEditorPane(String SenderName, String message, String time) {
        this.SenderName = SenderName;
        this.message = message;
        this.time = time;
        colorMap.put(Color.BLACK, "Black");   
        colorMap.put(Color.RED, "Red");
        colorMap.put(Color.GREEN, "Green");
        colorMap.put(Color.BLUE, "Blue");
        colorMap.put(Color.GRAY, "Gray");
        colorMap.put(Color.CYAN, "Cyan");
        String htmlfont = font.getFontName();
        String colorName = colorMap.get(color);
        String htmlTxt = "<html><body>"
                + "<FONT face="+htmlfont+" color="+colorName+" size="+size+"><b>"
                +SenderName+"<br>"+message+"<p align = right>"+time+"</p></body></html>";
        
        JEditorPane chatEditorPane = new JEditorPane("text/html", null);
        chatEditorPane.setPreferredSize(new Dimension(350, 100));
        chatEditorPane.setText(htmlTxt);
        this.add(chatEditorPane);
        chatEditorPane.setEditable(false);
    }
    
    

    public ChatEditorPane(String SenderName, String message, String time, Font font) {
        this.SenderName = SenderName;
        this.message = message;
        this.time = time;
        this.font = font;
        String htmlfont = font.getFontName();
        String colorName = colorMap.get(color);
        String htmlTxt = "<html><body>"
                + "<FONT face="+htmlfont+" color="+colorName+" size="+size+"><b>"
                +SenderName+"<br>"+message+"<p align = right>"+time+"</p></body></html>";
        
        JEditorPane chatEditorPane = new JEditorPane("text/html", null);
        chatEditorPane.setPreferredSize(new Dimension(350, 100));
        chatEditorPane.setText(htmlTxt);
        this.add(chatEditorPane);
        chatEditorPane.setEditable(false);
    }

    public ChatEditorPane(String SenderName, String message, String time, Color color) {
        this.SenderName = SenderName;
        this.message = message;
        this.time = time;
        this.color = color;
        
        String htmlfont = font.getFontName();
        String colorName = colorMap.get(color);
        String htmlTxt = "<html><body>"
                + "<FONT face="+htmlfont+" color="+colorName+" size="+size+"><b>"
                +SenderName+"<br>"+message+"<p align = right>"+time+"</p></body></html>";
        
        JEditorPane chatEditorPane = new JEditorPane("text/html", null);
        chatEditorPane.setPreferredSize(new Dimension(350, 100));
        chatEditorPane.setText(htmlTxt);
        this.add(chatEditorPane);
        chatEditorPane.setEditable(false);
    }

    public ChatEditorPane(String SenderName, String message, String time, int size) {
        this.SenderName = SenderName;
        this.message = message;
        this.time = time;
        this.size = size;
        
        String htmlfont = font.getFontName();
        String colorName = colorMap.get(color);
        String htmlTxt = "<html><body>"
                + "<FONT face="+htmlfont+" color="+colorName+" size="+size+"><b>"
                +SenderName+"<br>"+message+"<p align = right>"+time+"</p></body></html>";
        
        JEditorPane chatEditorPane = new JEditorPane("text/html", null);
        chatEditorPane.setPreferredSize(new Dimension(350, 100));
        chatEditorPane.setText(htmlTxt);
        this.add(chatEditorPane);
        chatEditorPane.setEditable(false);
    }
*/
    public ChatEditorPane(String SenderName, String message, String time, Font font, Color color) {
        this.SenderName = SenderName;
        this.message = message;
        this.time = time;
        this.font = font;
        this.color = color;
        
        String htmlfont = font.getFontName();
        
        String colorName = colorMap.get(color);
        String msg = "";
        String htmlTxt = "<html><body>"
                + "<FONT face="+htmlfont+" color="+colorName+" size="+size+"><b>"
                +SenderName+"<br>"+message+"<p align = right>"+time+"</p></body></html>";
        msg += htmlTxt;
        JEditorPane chatEditorPane = new JEditorPane("text/html", null);
        ////chatEditorPane.setPreferredSize(new Dimension(400, 300));
        chatEditorPane.setText(msg);
        this.add(chatEditorPane);
        chatEditorPane.setEditable(false);
        
    }

   /* public ChatEditorPane(String SenderName, String message, String time, Font font, int size) {
        this.SenderName = SenderName;
        this.message = message;
        this.time = time;
        this.font = font;
        this.size = size;
        
        String htmlfont = font.getFontName();
        String colorName = colorMap.get(color);
        String htmlTxt = "<html><body>"
                + "<FONT face="+htmlfont+" color="+colorName+" size="+size+"><b>"
                +SenderName+"<br>"+message+"<p align = right>"+time+"</p></body></html>";
        
        JEditorPane chatEditorPane = new JEditorPane("text/html", null);
        chatEditorPane.setPreferredSize(new Dimension(350, 100));
        chatEditorPane.setText(htmlTxt);
        this.add(chatEditorPane);
        chatEditorPane.setEditable(false);
    }

    public ChatEditorPane(String SenderName, String message, String time, Color color, int size) {
        this.SenderName = SenderName;
        this.message = message;
        this.time = time;
        this.color = color;
        this.size = size;
        
        String htmlfont = font.getFontName();
        String colorName = colorMap.get(color);
        String htmlTxt = "<html><body>"
                + "<FONT face="+htmlfont+" color="+colorName+" size="+size+"><b>"
                +SenderName+"<br>"+message+"<p align = right>"+time+"</p></body></html>";
        
        JEditorPane chatEditorPane = new JEditorPane("text/html", null);
        chatEditorPane.setPreferredSize(new Dimension(350, 100));
        chatEditorPane.setText(htmlTxt);
        this.add(chatEditorPane);
        chatEditorPane.setEditable(false);
    }
    
    */
    
    
    
     JEditorPane chatEditorPane = new JEditorPane("text/html", null);
    
    public ChatEditorPane(String SenderName, String message, String time, Font font, Color color, int size) {
        
        this.SenderName = SenderName;
        this.message = message;
        this.time = time;
        this.font = font;
        this.color = color;
        this.size = size;
        String htmlfont = font.getFontName();
        
        colorMap.put(Color.BLACK, "Black");   
        colorMap.put(Color.RED, "Red");
        colorMap.put(Color.GREEN, "Green");
        colorMap.put(Color.BLUE, "Blue");
        colorMap.put(Color.GRAY, "Gray");
        colorMap.put(Color.CYAN, "Cyan");
        String colorName = colorMap.get(color);
        String msg = "";
        String htmlTxt = "<html><body>"
                + "<FONT face="+htmlfont+" color="+colorName+" size="+size+"><b>"
                +SenderName+" : "+message+"<p align = right>"+time+"</p></body></html>";
        
       msg += htmlTxt;
        //chatEditorPane.setPreferredSize(new Dimension(350, 100));
        chatEditorPane.setText(htmlTxt);
        this.setSize(new Dimension(300, 400));
        this.add(chatEditorPane);
        chatEditorPane.setEditable(false);
        System.out.println(time);
        System.out.println("|||||||||||||||");
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(new java.awt.Dimension(400, 300));
        setMinimumSize(new java.awt.Dimension(400, 300));
        setPreferredSize(new java.awt.Dimension(400, 300));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
