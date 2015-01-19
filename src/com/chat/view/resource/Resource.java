/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.view.resource;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author eltntawy
 */
public class Resource {

    public final static ImageIcon IMAGE_AVAILABLE = new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/status_online.png"));
    public final static ImageIcon IMAGE_BUSY = new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/status_busy.png"));
    public final static ImageIcon IMAGE_AWAY = new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/status_away.png"));
    public final static ImageIcon IMAGE_OFFLINE = new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/status_offline.png"));

    public final static ImageIcon IMAGE_DEFAULT_USER = new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/messenger.png"));

    public final static ImageIcon IMAGE_AVAILABLE_SMALL = new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/status_online_small.png"));
    public final static ImageIcon IMAGE_BUSY_SMALL = new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/status_busy_small.png"));
    public final static ImageIcon IMAGE_AWAY_SMALL = new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/status_away_small.png"));
    public final static ImageIcon IMAGE_OFFLINE_SMALL = new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/status_offline_small.png"));

    public final static Color HIGHLIGHT_COLOR = new Color(51, 153, 255);

    /**
     *
     *
     * @param imageName : is relative URL for image
     * @return : return an Icon object for image name in path "/com/chat/view/resource/img/"
     *
     * @throws maybe throw NullPointerException
     */
    public static Icon getImage(String imageName) {
        return new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/" + imageName));
    }

    /**
     *
     *  
     * @param iconName : is relative URL for image
     * @return : return an Icon object for image name in path "/com/chat/view/resource/img/"
     *
     * @throws maybe throw NullPointerException
     */
    public static Icon getIcon(String iconName) {
        return new ImageIcon(Resource.class.getResource("/com/chat/view/resource/img/" + iconName));
    }
}
