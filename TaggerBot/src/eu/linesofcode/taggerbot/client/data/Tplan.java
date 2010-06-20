package eu.linesofcode.taggerbot.client.data;

/**
 * <p>
 * Java class for tplan complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="tplan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="observationtype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="routeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class Tplan {

    protected String id;
    protected String name;
    protected String observationtype;
    protected String routeId;
    protected String userId;

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
     * Gets the value of the observationtype property.
     * 
     * @return possible object is {@link String }
     */
    public String getObservationtype() {
        return observationtype;
    }

    /**
     * Sets the value of the observationtype property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setObservationtype(String value) {
        this.observationtype = value;
    }

    /**
     * Gets the value of the routeId property.
     * 
     * @return possible object is {@link String }
     */
    public String getRouteId() {
        return routeId;
    }

    /**
     * Sets the value of the routeId property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setRouteId(String value) {
        this.routeId = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return possible object is {@link String }
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setUserId(String value) {
        this.userId = value;
    }

}
