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
 *         &lt;element name="planIdArray" type="{}tidArray" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root(name = "E_planIds")
public class EPlanIds {

    @Element
    protected TidArray planIdArray;

    /**
     * Gets the value of the planIdArray property.
     * 
     * @return possible object is {@link TidArray }
     */
    public TidArray getPlanIdArray() {
        return planIdArray;
    }

    /**
     * Sets the value of the planIdArray property.
     * 
     * @param value
     *            allowed object is {@link TidArray }
     */
    public void setPlanIdArray(TidArray value) {
        this.planIdArray = value;
    }

}
