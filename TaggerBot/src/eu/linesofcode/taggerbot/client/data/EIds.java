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
 *         &lt;element name="idArray" type="{}tidArray" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root(name = "E_Ids")
public class EIds {

    @Element
    protected TidArray idArray;

    /**
     * Gets the value of the idArray property.
     * 
     * @return possible object is {@link TidArray }
     */
    public TidArray getIdArray() {
        return idArray;
    }

    /**
     * Sets the value of the idArray property.
     * 
     * @param value
     *            allowed object is {@link TidArray }
     */
    public void setIdArray(TidArray value) {
        this.idArray = value;
    }

}
