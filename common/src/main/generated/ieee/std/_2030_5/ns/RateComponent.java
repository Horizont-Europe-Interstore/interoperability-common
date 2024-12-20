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
import jakarta.xml.bind.annotation.XmlType;


/**
 * Specifies the applicable charges for a single component of the rate, which could be generation price or consumption price, for example.
 * 
 * <p>Java class for RateComponent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RateComponent"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}IdentifiedObject"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ActiveTimeTariffIntervalListLink" type="{urn:ieee:std:2030.5:ns}ActiveTimeTariffIntervalListLink" minOccurs="0"/&gt;
 *         &lt;element name="flowRateEndLimit" type="{urn:ieee:std:2030.5:ns}UnitValueType" minOccurs="0"/&gt;
 *         &lt;element name="flowRateStartLimit" type="{urn:ieee:std:2030.5:ns}UnitValueType" minOccurs="0"/&gt;
 *         &lt;element name="ReadingTypeLink" type="{urn:ieee:std:2030.5:ns}ReadingTypeLink"/&gt;
 *         &lt;element name="roleFlags" type="{urn:ieee:std:2030.5:ns}RoleFlagsType"/&gt;
 *         &lt;element name="TimeTariffIntervalListLink" type="{urn:ieee:std:2030.5:ns}TimeTariffIntervalListLink"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RateComponent", propOrder = {
    "activeTimeTariffIntervalListLink",
    "flowRateEndLimit",
    "flowRateStartLimit",
    "readingTypeLink",
    "roleFlags",
    "timeTariffIntervalListLink"
})
public class RateComponent
    extends IdentifiedObject
{

    @XmlElement(name = "ActiveTimeTariffIntervalListLink")
    protected ActiveTimeTariffIntervalListLink activeTimeTariffIntervalListLink;
    protected UnitValueType flowRateEndLimit;
    protected UnitValueType flowRateStartLimit;
    @XmlElement(name = "ReadingTypeLink", required = true)
    protected ReadingTypeLink readingTypeLink;
    @XmlElement(required = true)
    protected RoleFlagsType roleFlags;
    @XmlElement(name = "TimeTariffIntervalListLink", required = true)
    protected TimeTariffIntervalListLink timeTariffIntervalListLink;

    /**
     * Gets the value of the activeTimeTariffIntervalListLink property.
     * 
     * @return
     *     possible object is
     *     {@link ActiveTimeTariffIntervalListLink }
     *     
     */
    public ActiveTimeTariffIntervalListLink getActiveTimeTariffIntervalListLink() {
        return activeTimeTariffIntervalListLink;
    }

    /**
     * Sets the value of the activeTimeTariffIntervalListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActiveTimeTariffIntervalListLink }
     *     
     */
    public void setActiveTimeTariffIntervalListLink(ActiveTimeTariffIntervalListLink value) {
        this.activeTimeTariffIntervalListLink = value;
    }

    /**
     * Gets the value of the flowRateEndLimit property.
     * 
     * @return
     *     possible object is
     *     {@link UnitValueType }
     *     
     */
    public UnitValueType getFlowRateEndLimit() {
        return flowRateEndLimit;
    }

    /**
     * Sets the value of the flowRateEndLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitValueType }
     *     
     */
    public void setFlowRateEndLimit(UnitValueType value) {
        this.flowRateEndLimit = value;
    }

    /**
     * Gets the value of the flowRateStartLimit property.
     * 
     * @return
     *     possible object is
     *     {@link UnitValueType }
     *     
     */
    public UnitValueType getFlowRateStartLimit() {
        return flowRateStartLimit;
    }

    /**
     * Sets the value of the flowRateStartLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitValueType }
     *     
     */
    public void setFlowRateStartLimit(UnitValueType value) {
        this.flowRateStartLimit = value;
    }

    /**
     * Gets the value of the readingTypeLink property.
     * 
     * @return
     *     possible object is
     *     {@link ReadingTypeLink }
     *     
     */
    public ReadingTypeLink getReadingTypeLink() {
        return readingTypeLink;
    }

    /**
     * Sets the value of the readingTypeLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReadingTypeLink }
     *     
     */
    public void setReadingTypeLink(ReadingTypeLink value) {
        this.readingTypeLink = value;
    }

    /**
     * Gets the value of the roleFlags property.
     * 
     * @return
     *     possible object is
     *     {@link RoleFlagsType }
     *     
     */
    public RoleFlagsType getRoleFlags() {
        return roleFlags;
    }

    /**
     * Sets the value of the roleFlags property.
     * 
     * @param value
     *     allowed object is
     *     {@link RoleFlagsType }
     *     
     */
    public void setRoleFlags(RoleFlagsType value) {
        this.roleFlags = value;
    }

    /**
     * Gets the value of the timeTariffIntervalListLink property.
     * 
     * @return
     *     possible object is
     *     {@link TimeTariffIntervalListLink }
     *     
     */
    public TimeTariffIntervalListLink getTimeTariffIntervalListLink() {
        return timeTariffIntervalListLink;
    }

    /**
     * Sets the value of the timeTariffIntervalListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeTariffIntervalListLink }
     *     
     */
    public void setTimeTariffIntervalListLink(TimeTariffIntervalListLink value) {
        this.timeTariffIntervalListLink = value;
    }

}
