package eu.linesofcode.taggerbot.client.data;

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
 *         &lt;element name="user" type="{}tuser"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class EUser {

    protected Tuser user;

    /**
     * Gets the value of the user property.
     * 
     * @return possible object is {@link Tuser }
     */
    public Tuser getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *            allowed object is {@link Tuser }
     */
    public void setUser(Tuser value) {
        this.user = value;
    }

}
