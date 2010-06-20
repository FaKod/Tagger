package eu.linesofcode.taggerbot.client.data;

/**
 * <p>
 * Java class for twaypoint complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="twaypoint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="point" type="{}tlocation"/>
 *         &lt;element name="infotext" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="demandId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class Twaypoint {

    protected String id;
    protected String name;
    protected Tlocation point;
    protected String infotext;
    protected String demandId;
    protected String date;
    protected String time;

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
     * Gets the value of the point property.
     * 
     * @return possible object is {@link Tlocation }
     */
    public Tlocation getPoint() {
        return point;
    }

    /**
     * Sets the value of the point property.
     * 
     * @param value
     *            allowed object is {@link Tlocation }
     */
    public void setPoint(Tlocation value) {
        this.point = value;
    }

    /**
     * Gets the value of the infotext property.
     * 
     * @return possible object is {@link String }
     */
    public String getInfotext() {
        return infotext;
    }

    /**
     * Sets the value of the infotext property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setInfotext(String value) {
        this.infotext = value;
    }

    /**
     * Gets the value of the demandId property.
     * 
     * @return possible object is {@link String }
     */
    public String getDemandId() {
        return demandId;
    }

    /**
     * Sets the value of the demandId property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setDemandId(String value) {
        this.demandId = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return possible object is {@link String }
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return possible object is {@link String }
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setTime(String value) {
        this.time = value;
    }

}
