package eu.linesofcode.taggerbot.client.data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for tdemand complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="tdemand">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="demandType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="demandKey" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="demandValue" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class Tdemand {

    protected String id;
    protected String name;
    protected String demandType;
    protected List<String> demandKey;
    protected List<String> demandValue;

    /**
     * Gets the value of the id property.
     * 
     * @return possible object is {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return possible object is {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the demandType property.
     * 
     * @return possible object is {@link String }
     */
    public String getDemandType() {
        return demandType;
    }

    /**
     * Sets the value of the demandType property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setDemandType(String value) {
        this.demandType = value;
    }

    /**
     * Gets the value of the demandKey property.
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the demandKey property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDemandKey().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     */
    public List<String> getDemandKey() {
        if (demandKey == null) {
            demandKey = new ArrayList<String>();
        }
        return this.demandKey;
    }

    /**
     * Gets the value of the demandValue property.
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the demandValue property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDemandValue().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     */
    public List<String> getDemandValue() {
        if (demandValue == null) {
            demandValue = new ArrayList<String>();
        }
        return this.demandValue;
    }

}
