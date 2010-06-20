package eu.linesofcode.taggerbot.client.data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for tfeaturetoobserve complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="tfeaturetoobserve">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="featuretype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="featurepoints" type="{}tfeaturepoint" maxOccurs="unbounded"/>
 *         &lt;element name="planId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class Tfeaturetoobserve {

    protected String id;
    protected String name;
    protected String featuretype;
    protected List<Tfeaturepoint> featurepoints;
    protected String planId;

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
     * Gets the value of the featuretype property.
     * 
     * @return possible object is {@link String }
     */
    public String getFeaturetype() {
        return featuretype;
    }

    /**
     * Sets the value of the featuretype property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setFeaturetype(String value) {
        this.featuretype = value;
    }

    /**
     * Gets the value of the featurepoints property.
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the featurepoints property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getFeaturepoints().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Tfeaturepoint }
     */
    public List<Tfeaturepoint> getFeaturepoints() {
        if (featurepoints == null) {
            featurepoints = new ArrayList<Tfeaturepoint>();
        }
        return this.featurepoints;
    }

    /**
     * Gets the value of the planId property.
     * 
     * @return possible object is {@link String }
     */
    public String getPlanId() {
        return planId;
    }

    /**
     * Sets the value of the planId property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setPlanId(String value) {
        this.planId = value;
    }

}
