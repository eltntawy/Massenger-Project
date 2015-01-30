/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml;

import com.chat.model.Message;
import com.chat.model.User;
import java.awt.Font;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.ValidationEventCollector;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Marwa
 */
public class XMLJAXB {

    public XMLJAXB() {
        User user = new User(1, "aya", "Ibraheem", "254", null, 1);
        User userOut = new User(1, "marwa", "xyz", "2554", null, 0);
        Message msg = new Message(user, userOut, "Heeey", "125");
        Font font = new Font("Dialoge", Font.BOLD, 15);
        appendMsg(msg);
    }

    public void appendMsg(Message message) {

        List<UserTo> setUserList = null;
        List<Login> setLogList = null;
        ObjectFactory fact = new ObjectFactory();
        Schema schema;
        boolean flag = false;
        File xmlFile = new File("XMLMessenger.xml");
        try {

            JAXBContext context = JAXBContext.newInstance("com.xml");
            Unmarshaller unmarsh = context.createUnmarshaller();

//            ValidationEventCollector vec = new ValidationEventCollector();
//            unmarsh.setEventHandler(vec);
//         //   unmarsh.setValidating(false);
//
            MessageHistory element = (MessageHistory) unmarsh.unmarshal(xmlFile);
//            if(vec.hasEvents()){
            setLogList = element.getLogin();
            for (int i = 0; i < element.getLogin().size(); i++) {
                setUserList = setLogList.get(i).getUserTo();

                //to Create New Body
                if (message.getSenderName().getUserId() == setLogList.get(i).getUserID()) {
                    {
                        flag = true;
                        for (UserTo setUserList1 : setUserList) {
                            if (setUserList1.getUserID() == message.getReceiverName().getUserId()) {
                                setUserList1.getBody().getText().add(message.getMessage());
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
                    }
                }
            }
            if (!flag) {
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
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(new File("FirstExampel.xsd"));
            Marshaller marsh = context.createMarshaller();
            marsh.marshal(element, xmlFile);

            //new PrintWriter(new BufferedWriter(new FileWriter("XMLMessenger.xml", true))
        } catch (JAXBException ex) {
            Logger.getLogger(XMLJAXB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new XMLJAXB();

    }
}
