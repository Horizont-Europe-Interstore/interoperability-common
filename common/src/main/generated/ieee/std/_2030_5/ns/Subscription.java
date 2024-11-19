//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.19 at 12:20:19 PM CET 
//


package ieee.std._2030_5.ns;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Holds the information related to a client subscription to receive updates to a resource automatically.
 * 
 * <p>Java class for Subscription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Subscription"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}SubscriptionBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Condition" type="{urn:ieee:std:2030.5:ns}Condition" minOccurs="0"/&gt;
 *         &lt;element name="encoding" type="{urn:ieee:std:2030.5:ns}UInt8"/&gt;
 *         &lt;element name="level" type="{urn:ieee:std:2030.5:ns}String16"/&gt;
 *         &lt;element name="limit" type="{urn:ieee:std:2030.5:ns}UInt32"/&gt;
 *         &lt;element name="notificationURI" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Subscription", propOrder = {
    "condition",
    "encoding",
    "level",
    "limit",
    "notificationURI"
})
public class Subscription
    extends SubscriptionBase
{

    @XmlElement(name = "Condition")
    protected Condition condition;
    @XmlSchemaType(name = "unsignedByte")
    protected short encoding;
    @XmlElement(required = true)
    protected String level;
    @XmlSchemaType(name = "unsignedInt")
    protected long limit;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String notificationURI;

    /**
     * Gets the value of the condition property.
     * 
     * @return
     *     possible object is
     *     {@link Condition }
     *     
     */
    public Condition getCondition() {
        return condition;
    }

    /**
     * Sets the value of the condition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Condition }
     *     
     */
    public void setCondition(Condition value) {
        this.condition = value;
    }

    /**
     * Gets the value of the encoding property.
     * 
     */
    public short getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     */
    public void setEncoding(short value) {
        this.encoding = value;
    }

    /**
     * Gets the value of the level property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLevel(String value) {
        this.level = value;
    }

    /**
     * Gets the value of the limit property.
     * 
     */
    public long getLimit() {
        return limit;
    }

    /**
     * Sets the value of the limit property.
     * 
     */
    public void setLimit(long value) {
        this.limit = value;
    }

    /**
     * Gets the value of the notificationURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationURI() {
        return notificationURI;
    }

    /**
     * Sets the value of the notificationURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationURI(String value) {
        this.notificationURI = value;
    }

}