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
 *         &lt;element name="circleOfFriends" type="{}tcircleoffriends"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root(name = "E_circleOfFriends")
public class ECircleOfFriends {

    @Element
    protected Tcircleoffriends circleOfFriends;

    /**
     * Gets the value of the circleOfFriends property.
     * 
     * @return possible object is {@link Tcircleoffriends }
     */
    public Tcircleoffriends getCircleOfFriends() {
        return circleOfFriends;
    }

    /**
     * Sets the value of the circleOfFriends property.
     * 
     * @param value
     *            allowed object is {@link Tcircleoffriends }
     */
    public void setCircleOfFriends(Tcircleoffriends value) {
        this.circleOfFriends = value;
    }

}
