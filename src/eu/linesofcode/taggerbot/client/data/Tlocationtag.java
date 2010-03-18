package eu.linesofcode.taggerbot.client.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * <p>
 * Java class for tlocationtag complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="tlocationtag">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="infotext" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="point" type="{}tlocation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root(name = "locationTag")
public class Tlocationtag {

    @Element
    protected String id;

    @Element
    protected String name;

    @Element
    protected String infotext;

    @Element
    protected Tlocation point;

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

}
