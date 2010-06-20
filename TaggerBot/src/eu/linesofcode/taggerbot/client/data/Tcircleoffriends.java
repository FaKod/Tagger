package eu.linesofcode.taggerbot.client.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * <p>
 * Java class for tcircleoffriends complex type.
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="tcircleoffriends">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="circleoffriendstype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="users" type="{}tidArray"/>
 *         &lt;element name="owningUserId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root(name = "circleOfFriends")
public class Tcircleoffriends {

    @Element
    protected String id;

    @Element
    protected String name;

    @Element
    protected String circleoffriendstype;

    @Element
    protected TidArray users;

    @Element
    protected String owningUserId;

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
     * Gets the value of the circleoffriendstype property.
     * 
     * @return possible object is {@link String }
     */
    public String getCircleoffriendstype() {
        return circleoffriendstype;
    }

    /**
     * Sets the value of the circleoffriendstype property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setCircleoffriendstype(String value) {
        this.circleoffriendstype = value;
    }

    /**
     * Gets the value of the users property.
     * 
     * @return possible object is {@link TidArray }
     */
    public TidArray getUsers() {
        return users;
    }

    /**
     * Sets the value of the users property.
     * 
     * @param value
     *            allowed object is {@link TidArray }
     */
    public void setUsers(TidArray value) {
        this.users = value;
    }

    /**
     * Gets the value of the owningUserId property.
     * 
     * @return possible object is {@link String }
     */
    public String getOwningUserId() {
        return owningUserId;
    }

    /**
     * Sets the value of the owningUserId property.
     * 
     * @param value
     *            allowed object is {@link String }
     */
    public void setOwningUserId(String value) {
        this.owningUserId = value;
    }

}
