//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.30 at 12:41:08 PM EET 
//


package com.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Login" type="{}Login" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="msgTime" type="{http://www.w3.org/2001/XMLSchema}time" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "login"
})
@XmlRootElement(name = "MessageHistory")
public class MessageHistory {

    @XmlElement(name = "Login", required = true)
    protected List<Login> login;
    @XmlAttribute(name = "msgTime")
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar msgTime;

    /**
     * Gets the value of the login property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the login property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLogin().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Login }
     * 
     * 
     */
    public List<Login> getLogin() {
        if (login == null) {
            login = new ArrayList<Login>();
        }
        return this.login;
    }

    /**
     * Gets the value of the msgTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMsgTime() {
        return msgTime;
    }

    /**
     * Sets the value of the msgTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    
    public void setLogin(List<Login> login) {
        this.login = login;
    }

    public void setMsgTime(XMLGregorianCalendar value) {
        this.msgTime = value;
    }

}