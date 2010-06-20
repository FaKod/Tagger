package eu.linesofcode.taggerbot.client.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

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
 *         &lt;element name="locationTag" type="{}tlocationtag" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root(name = "E_locationTag")
public class ELocationTag {

    @Element
    protected Tlocationtag locationTag;

    /**
     * Gets the value of the locationTag property.
     * 
     * @return possible object is {@link Tlocationtag }
     */
    public Tlocationtag getLocationTag() {
        return locationTag;
    }

    /**
     * Sets the value of the locationTag property.
     * 
     * @param value
     *            allowed object is {@link Tlocationtag }
     */
    public void setLocationTag(Tlocationtag value) {
        this.locationTag = value;
    }

}
