/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml;

import com.chat.model.Message;
import com.chat.model.User;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Marwa
 */
public class XMLJAXB {

    public XMLJAXB() {
        User user = new User(2, "aya", "Ibraheem", "254", null, 1);
        User userOut = new User(4, "marwa", "xyz", "2554", null, 0);
        Message msg = new Message(user, userOut, "xyz", "125");
        Font font = new Font("Dialoge", Font.BOLD, 15);
        appendMsg(msg);
    }

    public void appendMsg(Message message) {

        List<UserTo> setUserList = null;
        List<Login> setLogList = null;
        ObjectFactory fact = new ObjectFactory();
        boolean flag = false;
        File xmlFile = new File("XMLMessenger.xml");
        try {

            JAXBContext context = JAXBContext.newInstance("com.xml");
            Unmarshaller unmarsh = context.createUnmarshaller();
            MessageHistory element = (MessageHistory) unmarsh.unmarshal(xmlFile);

            setLogList = element.getLogin();
            for (int i = 0; i < element.getLogin().size(); i++) {
                setUserList = setLogList.get(i).getUserTo();

                //to Create New Body
                if (message.getSenderName().getUserId() == setLogList.get(i).getUserID()) {
                    {

                        if (setUserList.get(i).getUserID() == message.getReceiverName().getUserId()) {

                            setUserList.get(i).getBody().getText().add(message.getMessage());

                        } // to Create new UserTo
                        else {

                            UserTo to = fact.createUserTo();
                            to.setUserID(message.getReceiverName().getUserId());
                            to.setUserName(message.getReceiverName().getUserName());
                            MsgBody Newbody = new MsgBody();
                            Newbody.getText().add(message.getMessage());
                            to.setBody(Newbody);
                            setLogList.get(i).getUserTo().add(to);

                        }
                    }
                } else {
                    flag = true;
                }
            }
            if (flag) {
                Login logUser = fact.createLogin();
                logUser.setUserID(message.getSenderName().getUserId());
                logUser.setUserName(message.getSenderName().getUserName());
                UserTo to = new UserTo();
                to.setUserID(message.getReceiverName().getUserId());
                to.setUserName(message.getReceiverName().getUserName());
                MsgBody Newbody = new MsgBody();
                Newbody.getText().add(message.getMessage());
                to.setBody(Newbody);
                logUser.getUserTo().add(to);
                element.getLogin().add(logUser);
            }

            Marshaller marsh = context.createMarshaller();
            marsh.marshal(element, xmlFile);
            //new PrintWriter(new BufferedWriter(new FileWriter("XMLMessenger.xml", true))
        } catch (JAXBException ex) {
            Logger.getLogger(XMLJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new XMLJAXB();

    }
}
