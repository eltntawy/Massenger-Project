package com.chat.model;

import javax.swing.Icon;

public class Status {


    
    private Icon statusIcon ;
    private String statusText;
    private int status;
    
    public Status(Icon statusIcon, String statusText) {
        this.statusIcon = statusIcon;
        this.statusText = statusText;
    }

    public Status(Icon statusIcon, String statusText, int status) {
        this.statusIcon = statusIcon;
        this.statusText = statusText;
        this.status = status;
    }

    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    
    public Icon getStatusIcon() {
        return statusIcon;
    }

    public void setStatusIcon(Icon statusIcon) {
        this.statusIcon = statusIcon;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
    
    
    public Status() {
	// TODO Auto-generated constructor stub
    }

}
