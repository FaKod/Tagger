package eu.linesofcode.taggerbot.client.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * <p>
 * Java class for tuser complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="tuser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="location" type="{}tlocation"/>
 *         &lt;element name="direction" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="locationtags" type="{}tidArray"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root(name = "user")
public class Tuser {

    @Element
    protected String id;

    @Element
    protected String name;

    @Element
    protected Tlocation location;

    @Element
    protected double direction;

    @Element
    protected TidArray locationtags;

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
     * Gets the value of the direction property.
     */
    public double getDirection() {
        return direction;
    }

    /**
     * Sets the value of the direction property.
     */
    public void setDirection(double value) {
        this.direction = value;
    }

    /**
     * Gets the value of the locationtags property.
     * 
     * @return possible object is {@link TidArray }
     */
    public TidArray getLocationtags() {
        return locationtags;
    }

    /**
     * Sets the value of the locationtags property.
     * 
     * @param value
     *            allowed object is {@link TidArray }
     */
    public void setLocationtags(TidArray value) {
        this.locationtags = value;
    }

}
