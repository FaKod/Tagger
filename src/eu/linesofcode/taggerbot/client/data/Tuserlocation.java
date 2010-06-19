package eu.linesofcode.taggerbot.client.data;

/**
 * <p>
 * Java class for tuserlocation complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="tuserlocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="direction" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="speed" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
public class Tuserlocation {

    protected String userId;
    protected double latitude;
    protected double longitude;
    protected double direction;
    protected double speed;

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

    /**
     * Gets the value of the latitude property.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     */
    public void setLatitude(double value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the longitude property.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     */
    public void setLongitude(double value) {
        this.longitude = value;
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
     * Gets the value of the speed property.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the value of the speed property.
     */
    public void setSpeed(double value) {
        this.speed = value;
    }

}
