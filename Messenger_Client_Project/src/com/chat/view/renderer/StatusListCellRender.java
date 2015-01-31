package com.chat.view.renderer;

import com.chat.model.Status;
import com.chat.model.User;
import com.chat.view.StatusCellPanel;
import com.chat.view.resource.Resource;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class StatusListCellRender implements ListCellRenderer<Status> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Status> list, Status value, int index, boolean isSelected, boolean cellHasFocus) {

        StatusCellPanel status = new StatusCellPanel();

        if (value.getStatus() == User.AVAILABLE) {
            status.setStatusIcon(Resource.IMAGE_AVAILABLE_SMALL);
            status.setStatusText(value.getStatusText());

        } else if (value.getStatus() == User.BUSY) {
            status.setStatusIcon(Resource.IMAGE_BUSY_SMALL);
            status.setStatusText(value.getStatusText());
        } else if (value.getStatus() == User.AWAY) {
            status.setStatusIcon(Resource.IMAGE_AWAY_SMALL);
            status.setStatusText(value.getStatusText());
        } else if (value.getStatus() == User.OFFLINE) {
            status.setStatusIcon(Resource.IMAGE_OFFLINE_SMALL);
            status.setStatusText(value.getStatusText());
        }else if (value.getStatus() == User.SIGNOUT) {
            status.setStatusIcon(Resource.IMAGE_SIGNOUT);
            status.setStatusText(value.getStatusText());
        }

        if (isSelected) {
            status.setBackground(Resource.HIGHLIGHT_COLOR);
        }

        return status;

    }
}
