//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.19 at 12:20:19 PM CET 
//


package ieee.std._2030_5.ns;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Status of device
 * 
 * <p>Java class for DeviceStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceStatus"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}Resource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="changedTime" type="{urn:ieee:std:2030.5:ns}TimeType"/&gt;
 *         &lt;element name="onCount" type="{urn:ieee:std:2030.5:ns}UInt16" minOccurs="0"/&gt;
 *         &lt;element name="opState" type="{urn:ieee:std:2030.5:ns}UInt8" minOccurs="0"/&gt;
 *         &lt;element name="opTime" type="{urn:ieee:std:2030.5:ns}UInt32" minOccurs="0"/&gt;
 *         &lt;element name="Temperature" type="{urn:ieee:std:2030.5:ns}Temperature" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="TimeLink" type="{urn:ieee:std:2030.5:ns}TimeLink" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="pollRate" type="{urn:ieee:std:2030.5:ns}UInt32" default="900" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceStatus", propOrder = {
    "changedTime",
    "onCount",
    "opState",
    "opTime",
    "temperature",
    "timeLink"
})
public class DeviceStatus
    extends Resource
{

    @XmlElement(required = true)
    protected TimeType changedTime;
    @XmlSchemaType(name = "unsignedShort")
    protected Integer onCount;
    @XmlSchemaType(name = "unsignedByte")
    protected Short opState;
    @XmlSchemaType(name = "unsignedInt")
    protected Long opTime;
    @XmlElement(name = "Temperature")
    protected List<Temperature> temperature;
    @XmlElement(name = "TimeLink")
    protected TimeLink timeLink;
    @XmlAttribute(name = "pollRate")
    protected Long pollRate;

    /**
     * Gets the value of the changedTime property.
     * 
     * @return
     *     possible object is
     *     {@link TimeType }
     *     
     */
    public TimeType getChangedTime() {
        return changedTime;
    }

    /**
     * Sets the value of the changedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeType }
     *     
     */
    public void setChangedTime(TimeType value) {
        this.changedTime = value;
    }

    /**
     * Gets the value of the onCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOnCount() {
        return onCount;
    }

    /**
     * Sets the value of the onCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOnCount(Integer value) {
        this.onCount = value;
    }

    /**
     * Gets the value of the opState property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getOpState() {
        return opState;
    }

    /**
     * Sets the value of the opState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setOpState(Short value) {
        this.opState = value;
    }

    /**
     * Gets the value of the opTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOpTime() {
        return opTime;
    }

    /**
     * Sets the value of the opTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOpTime(Long value) {
        this.opTime = value;
    }

    /**
     * Gets the value of the temperature property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the temperature property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTemperature().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Temperature }
     * 
     * 
     */
    public List<Temperature> getTemperature() {
        if (temperature == null) {
            temperature = new ArrayList<Temperature>();
        }
        return this.temperature;
    }

    /**
     * Gets the value of the timeLink property.
     * 
     * @return
     *     possible object is
     *     {@link TimeLink }
     *     
     */
    public TimeLink getTimeLink() {
        return timeLink;
    }

    /**
     * Sets the value of the timeLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeLink }
     *     
     */
    public void setTimeLink(TimeLink value) {
        this.timeLink = value;
    }

    /**
     * Gets the value of the pollRate property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getPollRate() {
        if (pollRate == null) {
            return  900L;
        } else {
            return pollRate;
        }
    }

    /**
     * Sets the value of the pollRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPollRate(Long value) {
        this.pollRate = value;
    }

}
