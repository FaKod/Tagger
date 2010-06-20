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
 *         &lt;element name="user" type="{}tuser"/>
 *         &lt;element name="userInfo" type="{}tuserinfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@Root(name = "E_userAndUserInfo")
public class EUserAndUserInfo {

    @Element
    protected Tuser user;

    @Element
    protected Tuserinfo userInfo;

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

    /**
     * Gets the value of the userInfo property.
     * 
     * @return possible object is {@link Tuserinfo }
     */
    public Tuserinfo getUserInfo() {
        return userInfo;
    }

    /**
     * Sets the value of the userInfo property.
     * 
     * @param value
     *            allowed object is {@link Tuserinfo }
     */
    public void setUserInfo(Tuserinfo value) {
        this.userInfo = value;
    }

}
