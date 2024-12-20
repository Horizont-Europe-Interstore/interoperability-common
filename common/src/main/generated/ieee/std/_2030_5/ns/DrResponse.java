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
 * A response to a Demand Response Load Control (EndDeviceControl) message.
 * 
 * <p>Java class for DrResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}Response"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ApplianceLoadReduction" type="{urn:ieee:std:2030.5:ns}ApplianceLoadReduction" minOccurs="0"/&gt;
 *         &lt;element name="AppliedTargetReduction" type="{urn:ieee:std:2030.5:ns}AppliedTargetReduction" minOccurs="0"/&gt;
 *         &lt;element name="DutyCycle" type="{urn:ieee:std:2030.5:ns}DutyCycle" minOccurs="0"/&gt;
 *         &lt;element name="Offset" type="{urn:ieee:std:2030.5:ns}Offset" minOccurs="0"/&gt;
 *         &lt;element name="overrideDuration" type="{urn:ieee:std:2030.5:ns}UInt16" minOccurs="0"/&gt;
 *         &lt;element name="SetPoint" type="{urn:ieee:std:2030.5:ns}SetPoint" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrResponse", propOrder = {
    "applianceLoadReduction",
    "appliedTargetReduction",
    "dutyCycle",
    "offset",
    "overrideDuration",
    "setPoint"
})
public class DrResponse
    extends Response
{

    @XmlElement(name = "ApplianceLoadReduction")
    protected ApplianceLoadReduction applianceLoadReduction;
    @XmlElement(name = "AppliedTargetReduction")
    protected AppliedTargetReduction appliedTargetReduction;
    @XmlElement(name = "DutyCycle")
    protected DutyCycle dutyCycle;
    @XmlElement(name = "Offset")
    protected Offset offset;
    @XmlSchemaType(name = "unsignedShort")
    protected Integer overrideDuration;
    @XmlElement(name = "SetPoint")
    protected SetPoint setPoint;

    /**
     * Gets the value of the applianceLoadReduction property.
     * 
     * @return
     *     possible object is
     *     {@link ApplianceLoadReduction }
     *     
     */
    public ApplianceLoadReduction getApplianceLoadReduction() {
        return applianceLoadReduction;
    }

    /**
     * Sets the value of the applianceLoadReduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplianceLoadReduction }
     *     
     */
    public void setApplianceLoadReduction(ApplianceLoadReduction value) {
        this.applianceLoadReduction = value;
    }

    /**
     * Gets the value of the appliedTargetReduction property.
     * 
     * @return
     *     possible object is
     *     {@link AppliedTargetReduction }
     *     
     */
    public AppliedTargetReduction getAppliedTargetReduction() {
        return appliedTargetReduction;
    }

    /**
     * Sets the value of the appliedTargetReduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppliedTargetReduction }
     *     
     */
    public void setAppliedTargetReduction(AppliedTargetReduction value) {
        this.appliedTargetReduction = value;
    }

    /**
     * Gets the value of the dutyCycle property.
     * 
     * @return
     *     possible object is
     *     {@link DutyCycle }
     *     
     */
    public DutyCycle getDutyCycle() {
        return dutyCycle;
    }

    /**
     * Sets the value of the dutyCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link DutyCycle }
     *     
     */
    public void setDutyCycle(DutyCycle value) {
        this.dutyCycle = value;
    }

    /**
     * Gets the value of the offset property.
     * 
     * @return
     *     possible object is
     *     {@link Offset }
     *     
     */
    public Offset getOffset() {
        return offset;
    }

    /**
     * Sets the value of the offset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Offset }
     *     
     */
    public void setOffset(Offset value) {
        this.offset = value;
    }

    /**
     * Gets the value of the overrideDuration property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOverrideDuration() {
        return overrideDuration;
    }

    /**
     * Sets the value of the overrideDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOverrideDuration(Integer value) {
        this.overrideDuration = value;
    }

    /**
     * Gets the value of the setPoint property.
     * 
     * @return
     *     possible object is
     *     {@link SetPoint }
     *     
     */
    public SetPoint getSetPoint() {
        return setPoint;
    }

    /**
     * Sets the value of the setPoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link SetPoint }
     *     
     */
    public void setSetPoint(SetPoint value) {
        this.setPoint = value;
    }

}
