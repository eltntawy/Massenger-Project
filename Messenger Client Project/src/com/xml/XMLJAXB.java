/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml;

import com.xml.MessageHistory;
import java.io.File;
import java.net.URL;
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
            MsgBody msg = factory.createMsgBody();
            msg.setText("Hello");
            Marshaller marsh = context.createMarshaller();
            marsh.marshal(element, (new File("Msg.xml")));
        } catch (JAXBException ex) {
           // ex.printStackTrace();
            Logger.getLogger(XMLJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new XMLJAXB();
    }
}
