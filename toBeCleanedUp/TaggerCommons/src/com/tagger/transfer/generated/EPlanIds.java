//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.01.23 at 02:47:10 PM MEZ 
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
 *         &lt;element name="planIdArray" type="{}tidArray" minOccurs="0"/>
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
    "planIdArray"
})
@XmlRootElement(name = "E_planIds")
public class EPlanIds {

    protected TidArray planIdArray;

    /**
     * Gets the value of the planIdArray property.
     * 
     * @return
     *     possible object is
     *     {@link TidArray }
     *     
     */
    public TidArray getPlanIdArray() {
        return planIdArray;
    }

    /**
     * Sets the value of the planIdArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link TidArray }
     *     
     */
    public void setPlanIdArray(TidArray value) {
        this.planIdArray = value;
    }

}
