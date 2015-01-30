/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml;

import com.chat.model.Message;
import com.xml.MessageHistory;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
        try {
            File xmlFile = new File(getClass().getResource("XMLMessenger.xml").getPath());

            JAXBContext context = JAXBContext.newInstance("com.xml");
            Unmarshaller unmarsh = context.createUnmarshaller();
            MessageHistory element = (MessageHistory) unmarsh.unmarshal(xmlFile);
            System.out.println("marwaaa");
            ObjectFactory factory = new ObjectFactory();
            MessageHistory msg = factory.createMessageHistory();
            Login log=new Login();
            log.setUserID(0);
            log.setUserName("Marwa");
            UserTo user=new UserTo();
            user.setUserID(1);
            user.setUserName("aya");
            MsgBody body=new MsgBody();
            body.setText("Hello My Friend");
            user.setBody(body);
            List<UserTo>userList=new ArrayList<>();
            List<Login>logList=new ArrayList<>();
            userList.add(user);
            logList.add(log);
           log.setUserTo(userList);
           msg.setLogin(logList);
            Marshaller marsh = context.createMarshaller();
            marsh.marshal(msg, (new File("Msg.xml")));
        } catch (JAXBException ex) {
            //ex.printStackTrace();
            Logger.getLogger(XMLJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void appendMsg(Message message){
        
        
    }

    public static void main(String[] args) {
        new XMLJAXB();
    }
}
