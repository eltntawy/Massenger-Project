/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chat.view;

import com.chat.model.Message;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JEditorPane;

/**
 *
 * @author yomna
 */
public class ConversationEditorPane extends JEditorPane{
    
    Message message;
    Font font;
    Color color;
    String Txt;
    
    public ConversationEditorPane(Message message, Font font, Color color) {
        this.setContentType("text/html");
        this.message = message;
        this.font = font;
        this.color = color;
        String htmlfont = font.getName();
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        String htmlcolor = "rgb("+red+","+green+","+blue+")";
        int size = font.getSize();
        
        String htmlTxt = "<html><body>"
                + "<FONT face="+htmlfont+" color="+htmlcolor+" size="+size+"><b>"
                +message.getSenderName()+" : "+message.getMessage()+"</b><p align = right>"+message.getTime()
                +"</p></FONT></body></html>";
        Txt = "<FONT face="+htmlfont+" color="+htmlcolor+" size="+size+"><b>"
                +message.getSenderName()+" : "+message.getMessage()+"</b><p align = right>"+message.getTime()
                +"</p></FONT>";
        //this.setText(htmlTxt);
        this.setEditable(false);
        
    }
    public void AppendText (Message message, Font font, Color color){
        
        
        this.message = message;
        this.font = font;
        this.color = color;
        String htmlfont = font.getName();
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        String htmlcolor = "rgb("+red+","+green+","+blue+")";
        int size = font.getSize();
        Txt = "<html><body>"+Txt;
        String msg = "<FONT face="+htmlfont+" color="+htmlcolor+" size="+size+"><b>"
                +message.getSenderName()+" : "+message.getMessage()+"</b><p align = right>"+message.getTime()
                +"</p></FONT></body></html>";
        Txt += msg;
        this.setText(Txt);
        this.setEditable(false);
    }
   
}
