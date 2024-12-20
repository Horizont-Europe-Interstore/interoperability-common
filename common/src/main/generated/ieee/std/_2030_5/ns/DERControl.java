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
 * Distributed Energy Resource (DER) time/event-based control.
 * 
 * <p>Java class for DERControl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DERControl"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}RandomizableEvent"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DERControlBase" type="{urn:ieee:std:2030.5:ns}DERControlBase"/&gt;
 *         &lt;element name="deviceCategory" type="{urn:ieee:std:2030.5:ns}DeviceCategoryType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DERControl", propOrder = {
    "derControlBase",
    "deviceCategory"
})
public class DERControl
    extends RandomizableEvent
{

    @XmlElement(name = "DERControlBase", required = true)
    protected DERControlBase derControlBase;
    protected DeviceCategoryType deviceCategory;

    /**
     * Gets the value of the derControlBase property.
     * 
     * @return
     *     possible object is
     *     {@link DERControlBase }
     *     
     */
    public DERControlBase getDERControlBase() {
        return derControlBase;
    }

    /**
     * Sets the value of the derControlBase property.
     * 
     * @param value
     *     allowed object is
     *     {@link DERControlBase }
     *     
     */
    public void setDERControlBase(DERControlBase value) {
        this.derControlBase = value;
    }

    /**
     * Gets the value of the deviceCategory property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceCategoryType }
     *     
     */
    public DeviceCategoryType getDeviceCategory() {
        return deviceCategory;
    }

    /**
     * Sets the value of the deviceCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceCategoryType }
     *     
     */
    public void setDeviceCategory(DeviceCategoryType value) {
        this.deviceCategory = value;
    }

}
