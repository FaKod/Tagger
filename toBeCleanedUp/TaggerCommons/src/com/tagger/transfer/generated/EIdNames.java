//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.03.29 at 09:10:52 PM MESZ 
//


package com.tagger.transfer.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="idNameArray" type="{}tidNameArray" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idNameArray"
})
@XmlRootElement(name = "E_IdNames")
public class EIdNames {

    protected TidNameArray idNameArray;

    /**
     * Gets the value of the idNameArray property.
     * 
     * @return
     *     possible object is
     *     {@link TidNameArray }
     *     
     */
    public TidNameArray getIdNameArray() {
        return idNameArray;
    }

    /**
     * Sets the value of the idNameArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link TidNameArray }
     *     
     */
    public void setIdNameArray(TidNameArray value) {
        this.idNameArray = value;
    }

}