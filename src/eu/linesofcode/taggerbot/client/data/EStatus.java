package eu.linesofcode.taggerbot.client.data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Java class for anonymous complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="statusKey" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="statusValue" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="location" type="{}tlocation" minOccurs="0"/>
 *         &lt;element name="demand" type="{}tdemand" minOccurs="0"/>
 *         &lt;element name="htmlText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="distance" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="azimuth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class EStatus {

    protected List<String> statusKey;
    protected List<String> statusValue;
    protected Tlocation location;
    protected Tdemand demand;
    protected String htmlText;
    protected double distance;
    protected double azimuth;

    /**
     * Gets the value of the statusKey property.
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the statusKey property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getStatusKey().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     */
    public List<String> getStatusKey() {
        if (statusKey == null) {
            statusKey = new ArrayList<String>();
        }
        return this.statusKey;
    }

    /**
     * Gets the value of the statusValue property.
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the statusValue property.
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getStatusValue().add(newItem);
     * </pre>
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     */
    public List<String> getStatusValue() {
        if (statusValue == null) {
            statusValue = new ArrayList<String>();
        }
        return this.statusValue;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return possible object is {@link Tlocation }
     */
    public Tlocation getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *            allowed object is {@link Tlocation }
     */
    public void setLocation(Tlocation value) {
        this.location = value;
    }

    /**
     * Gets the value of the demand property.
     * 
     * @return possible object is {@link Tdemand }
     */
    public Tdemand getDemand() {
        return demand;
    }

    /**
     * Sets the value of the demand property.
     * 
     * @param value
     *            allowed object is {@link Tdemand }
     */
    public void setDemand(Tdemand value) {
        this.demand = value;
    }

    /**
     * Gets the value of the htmlText property.
     * 
     * @return possible object is {@link String }
     */
    public String getHtmlText() {
        return htmlText;
    }

    /**
     * Sets the value of the htmlText property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setHtmlText(String value) {
        this.htmlText = value;
    }

    /**
     * Gets the value of the distance property.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Sets the value of the distance property.
     */
    public void setDistance(double value) {
        this.distance = value;
    }

    /**
     * Gets the value of the azimuth property.
     */
    public double getAzimuth() {
        return azimuth;
    }

    /**
     * Sets the value of the azimuth property.
     */
    public void setAzimuth(double value) {
        this.azimuth = value;
    }

}
