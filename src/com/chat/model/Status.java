package com.chat.model;

import com.chat.view.resource.Resource;
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

    public Status(int status) {
       
        
         if (status == User.AVAILABLE)
        {
            this.statusIcon = Resource.IMAGE_AVAILABLE;
            this.statusText= "Available";
            this.status = status;
        } else if (status == User.AWAY)
        {
            this.statusIcon = Resource.IMAGE_AWAY;
            this.statusText = "Away";
            this.status = status;
        } else if (status == User.BUSY)
        {
            this.statusIcon = Resource.IMAGE_BUSY;
            this.statusText = "Busy";
            this.status = status;
        } else if (status == User.OFFLINE)
        {
            this.statusIcon = Resource.IMAGE_OFFLINE;
            this.statusText = "Offline";
            this.status = status;
        }
              
    }

    
    public static Status getStatusIcon (int status){
        
        Status state = new Status(status);
        return state;
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
