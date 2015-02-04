/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xml;

import com.chat.model.User;

import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;

/**
 *
 * @author Marwa
 */
public class XMLJAXB {

    private final static String schemaLocation = "SessionSchema.xsd";
    private static String schemaData = "<?xml version=\"1.0\"?>\n"
            + "<!--\n"
            + "To change this license header, choose License Headers in Project Properties.\n"
            + "To change this template file, choose Tools | Templates\n"
            + "and open the template in the editor.\n"
            + "-->\n"
            + "\n"
            + "<xs:schema version=\"1.0\"\n"
            + "           xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"\n"
            + "           elementFormDefault=\"qualified\">\n"
            + "    \n"
            + "    \n"
            + "    \n"
            + "    <xs:attributeGroup  name=\"UserGroup\">\n"
            + "        <xs:attribute name=\"userName\" type=\"xs:string\"/>\n"
            + "        <xs:attribute name=\"userID\" type=\"xs:integer\"/>\n"
            + "    </xs:attributeGroup>\n"
            + "    \n"
            + "    \n"
            + "    <xs:complexType name=\"MsgBody\" >\n"
            + "        <xs:sequence>\n"
            + "            <xs:element name=\"Text\" type=\"xs:string\"/>\n"
            + "        </xs:sequence>\n"
            + "        <xs:attribute name=\"font\" type=\"xs:string\"/>\n"
            + "        <xs:attribute name=\"size\" type=\"xs:integer\"/>\n"
            + "        <xs:attribute name=\"Color\" type=\"xs:string\"/>\n"
            + "    </xs:complexType>\n"
            + "    \n"
            + "    <xs:complexType name=\"UserTo\">\n"
            + "        <xs:sequence>\n"
            + "            <xs:element name=\"Body\" type=\"MsgBody\"/>\n"
            + "        </xs:sequence>\n"
            + "        <xs:attributeGroup ref=\"UserGroup\"/>\n"
            + "    </xs:complexType>\n"
            + "    \n"
            + "    <xs:complexType name=\"Login\">\n"
            + "        <xs:sequence>\n"
            + "            <xs:element name=\"UserTo\" type=\"UserTo\" minOccurs=\"1\" maxOccurs=\"unbounded\"/>\n"
            + "        </xs:sequence>\n"
            + "        <xs:attributeGroup ref=\"UserGroup\" />\n"
            + "    </xs:complexType>\n"
            + " \n"
            + "    \n"
            + "    <xs:element name=\"MessageHistory\">\n"
            + "        <xs:complexType>\n"
            + "            <xs:sequence>\n"
            + "                <xs:element name=\"Login\" type=\"Login\" minOccurs=\"1\" maxOccurs=\"unbounded\"/>\n"
            + "            </xs:sequence>\n"
            + "            <xs:attribute name=\"msgTime\" type=\"xs:time\"/>\n"
            + "        </xs:complexType>\n"
            + "    </xs:element>\n"
            + "\n"
            + "</xs:schema>\n"
            + "";

    private static String xsltData = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "\n"
            + "<!--\n"
            + "    Document   : Messenger_StyleSheet.xsl\n"
            + "    Created on : January 31, 2015, 9:43 AM\n"
            + "    Author     : Marwa\n"
            + "    Description:\n"
            + "        Purpose of transformation follows.\n"
            + "-->\n"
            + "\n"
            + "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
            + "    <xsl:output method=\"html\"/>\n"
            + "\n"
            + "    <!-- TODO customize transformation rules \n"
            + "         syntax recommendation http://www.w3.org/TR/xslt \n"
            + "    -->\n"
            + "    <xsl:template match=\"/\">\n"
            + "        <html>\n"
            + "            <head>\n"
            + "                <title>Messenger_StyleSheet.xsl</title>\n"
            + "                <style>\n"
            + "                    #ms{\n"
            + "                    background-color: #F0F09E;\n"
            + "                    color: #9E0808;\n"
            + "                    }\n"
            + "                    .t_login{\n"
            + "                    width: 100%;\n"
            + "                    }\n"
            + "                    .login{\n"
            + "                    background-color: #9E0808;\n"
            + "                    color: #F0F09E;\n"
            + "                    }\n"
            + "                    .t_users{\n"
            + "                    width: 100%;\n"
            + "                    border: 1px solid #9E0808;\n"
            + "                    margin-bottom: 5px;\n"
            + "                    margin-top: 5px;\n"
            + "                    }\n"
            + "                    .users{\n"
            + "                    background-color: #F0F09E;\n"
            + "                    color: #9E0808;\n"
            + "                    }\n"
            + "                </style>\n"
            + "            </head>\n"
            + "            <body>\n"
            + "                <div id=\"ms\">\n"
            + "                    <h1>Message History</h1>\n"
            + "                </div>\n"
            + "                <table class=\"t_login\">\n"
            + "                    <xsl:for-each  select=\"MessageHistory/Login\">\n"
            + "                        <tr class=\"login\">\n"
            + "                            <td>\n"
            + "                                Login :<xsl:value-of select=\"@userName\" />\n"
            + "                            </td>\n"
            + "                        </tr>\n"
            + "                        <tr>\n"
            + "                            <td>\n"
            + "                                <xsl:for-each select=\"./UserTo\" >\n"
            + "                                    <table class=\"t_users\">\n"
            + "                                        <tr class=\"users\">\n"
            + "                                            <td>\n"
            + "                                                User Name :<xsl:value-of select=\"@userName\" />\n"
            + "                                            </td>\n"
            + "                                        </tr>\n"
            + "                                        <tr class=\"users\">\n"
            + "                                            <td>\n"
            + "                                                Messages :\n"
            + "                                                <xsl:for-each select=\"./Body/Text\" >\n"
            + "                                                    <table>\n"
            + "                                                        <tr class=\"users\">\n"
            + "                                                            <td>\n"
            + "                                                                <xsl:value-of select=\".\" />\n"
            + "                                                            </td>\n"
            + "                                                        </tr>\n"
            + "                                                    </table>\n"
            + "                                                </xsl:for-each>\n"
            + "                                            </td>\n"
            + "                                        </tr>\n"
            + "                                    </table> \n"
            + "                                </xsl:for-each>\n"
            + "                            </td>\n"
            + "                        </tr> \n"
            + "                    </xsl:for-each>             \n"
            + "                </table>\n"
            + "            </body>\n"
            + "        </html>\n"
            + "    </xsl:template>\n"
            + "    \n"
            + "   \n"
            + "\n"
            + "</xsl:stylesheet>\n"
            + "";

    public XMLJAXB() {

        User user = new User(30, "aya", "Ibraheem", "254", null, 1);
        User userOut = new User(20, "marwa", "xyz", "2554", null, 0);
        Message msg = new Message(user, userOut, "Heeey", "125");
        Font font = new Font("Dialoge", Font.BOLD, 15);
        saveFileSchemaOrXslt(System.getProperty("user.home"));
        appendMsg(msg);

    }

    public static void saveFileSchemaOrXslt(String OutputFile) {
        BufferedWriter bw = null;
        try {
            File file = new File(OutputFile + "/Messenger-Project/SessionSchema.xsd");
            if(!file.exists()) {
        	file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(schemaData.toCharArray());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
        	if(bw != null) {
        	    bw.flush();
        	    bw.close();
        	}
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
        }
        BufferedWriter bw2 = null;
        try {
            File file = new File(OutputFile + "/Messenger-Project/Sessionstylesheet.xsl");
            if(!file.exists()) {
        	file.createNewFile();
            }
            bw2 = new BufferedWriter(new FileWriter(file));
            bw2.write(xsltData.toCharArray());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
        	if(bw != null) {
        	    bw2.flush();
        	    bw2.close();
        	}
        	    
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public synchronized static void appendMsg(Message message) {

        try {
            saveFileSchemaOrXslt(System.getProperty("user.home"));
            
            List<UserTo> setUserList = null;
            List<Login> setLogList = null;
            ObjectFactory fact = new ObjectFactory();
            Schema schema;
            boolean flag = false;
            
            File xmlFile = new File(System.getProperty("user.home")+"//Messenger-Project/XMLMessenger.xml");
            
            JAXBContext context = JAXBContext.newInstance("com.xml");
            Unmarshaller unmarsh = context.createUnmarshaller();
            MessageHistory element = null;
            if (!xmlFile.exists()) {
                try {
                    xmlFile.createNewFile();
                    String xmlData = "<MessageHistory\n"
                            + "    xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>\n"
                            + "</MessageHistory>\n"
                            + "";
                    StringReader xmlReaderData;
                    xmlReaderData = new StringReader(xmlData);
                    
                    element = (MessageHistory) unmarsh.unmarshal(xmlReaderData);
                } catch (IOException ex) {
                    Logger.getLogger(XMLJAXB.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JAXBException ex) {
                    Logger.getLogger(XMLJAXB.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    element = (MessageHistory) unmarsh.unmarshal(xmlFile);
                } catch (JAXBException ex) {
                    Logger.getLogger(XMLJAXB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                
        	if(element == null) {
        	    element = fact.createMessageHistory();
        	}
                setLogList = element.getLogin();
                
                for (int i = 0; i < element.getLogin().size(); i++) {
                    setUserList = setLogList.get(i).getUserTo();
                    
                    
                    
                    //to Create New Body
                    if (message.getSenderName().getUserId() == setLogList.get(i).getUserID()) {
                        {
                            flag = true;
                            setUserList = Collections.synchronizedList(setUserList);
                            boolean isFoundBefore = false;
                            for (UserTo setUserList1 : setUserList) {
                                if (setUserList1.getUserID() == message.getReceiverName().getUserId()) {
                                    setUserList1.getBody().getText().add(message.getMessage());
                                    isFoundBefore = true;
                                } // to Create new UserTo
                            }
                            
                            for(UserTo setUserList1 : setUserList) {
                        	if(!isFoundBefore) {
                                    UserTo to = fact.createUserTo();
                                    to.setUserID(message.getReceiverName().getUserId());
                                    to.setUserName(message.getReceiverName().getUserName());
                                    MsgBody Newbody = new MsgBody();
                                    Newbody.getText().add(message.getMessage());
                                    to.setBody(Newbody);
                                    setLogList.get(i).getUserTo().add(to);
                                    break;
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
                //  SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                //schema = schemaFactory.newSchema(new File("FirstExampel.xsd"));
                Marshaller marsh = context.createMarshaller();
                marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                //marsh.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, "src\\com.xml\\FirstExampel.xsd");
                marsh.setProperty(Marshaller.JAXB_FRAGMENT, false);
                // marsh.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml-stylesheet type=\"text/xsl\" href=\"Messenger_StyleSheet.xsl\" ?>");
                
                marsh.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, schemaLocation);
                marsh.setProperty("com.sun.xml.internal.bind.xmlHeaders", "<?xml-stylesheet type=\"text/xsl\" href=\"Sessionstylesheet.xsl\" ?>");
                // validator.validate((Source) element);
                // marsh.setSchema(schema);
                
                marsh.marshal(element, xmlFile);
                
                //new PrintWriter(new BufferedWriter(new FileWriter("XMLMessenger.xml", true))
            } catch (JAXBException ex) {
                Logger.getLogger(XMLJAXB.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (JAXBException ex) {
            Logger.getLogger(XMLJAXB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new XMLJAXB();

    }

}
