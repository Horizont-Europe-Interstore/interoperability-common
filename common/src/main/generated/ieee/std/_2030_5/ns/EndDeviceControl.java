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
 * Instructs an EndDevice to perform a specified action.
 * 
 * <p>Java class for EndDeviceControl complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EndDeviceControl"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}RandomizableEvent"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ApplianceLoadReduction" type="{urn:ieee:std:2030.5:ns}ApplianceLoadReduction" minOccurs="0"/&gt;
 *         &lt;element name="deviceCategory" type="{urn:ieee:std:2030.5:ns}DeviceCategoryType"/&gt;
 *         &lt;element name="drProgramMandatory" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="DutyCycle" type="{urn:ieee:std:2030.5:ns}DutyCycle" minOccurs="0"/&gt;
 *         &lt;element name="loadShiftForward" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="Offset" type="{urn:ieee:std:2030.5:ns}Offset" minOccurs="0"/&gt;
 *         &lt;element name="overrideDuration" type="{urn:ieee:std:2030.5:ns}UInt16" minOccurs="0"/&gt;
 *         &lt;element name="SetPoint" type="{urn:ieee:std:2030.5:ns}SetPoint" minOccurs="0"/&gt;
 *         &lt;element name="TargetReduction" type="{urn:ieee:std:2030.5:ns}TargetReduction" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndDeviceControl", propOrder = {
    "applianceLoadReduction",
    "deviceCategory",
    "drProgramMandatory",
    "dutyCycle",
    "loadShiftForward",
    "offset",
    "overrideDuration",
    "setPoint",
    "targetReduction"
})
public class EndDeviceControl
    extends RandomizableEvent
{

    @XmlElement(name = "ApplianceLoadReduction")
    protected ApplianceLoadReduction applianceLoadReduction;
    @XmlElement(required = true)
    protected DeviceCategoryType deviceCategory;
    protected boolean drProgramMandatory;
    @XmlElement(name = "DutyCycle")
    protected DutyCycle dutyCycle;
    protected boolean loadShiftForward;
    @XmlElement(name = "Offset")
    protected Offset offset;
    @XmlSchemaType(name = "unsignedShort")
    protected Integer overrideDuration;
    @XmlElement(name = "SetPoint")
    protected SetPoint setPoint;
    @XmlElement(name = "TargetReduction")
    protected TargetReduction targetReduction;

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

    /**
     * Gets the value of the drProgramMandatory property.
     * 
     */
    public boolean isDrProgramMandatory() {
        return drProgramMandatory;
    }

    /**
     * Sets the value of the drProgramMandatory property.
     * 
     */
    public void setDrProgramMandatory(boolean value) {
        this.drProgramMandatory = value;
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
     * Gets the value of the loadShiftForward property.
     * 
     */
    public boolean isLoadShiftForward() {
        return loadShiftForward;
    }

    /**
     * Sets the value of the loadShiftForward property.
     * 
     */
    public void setLoadShiftForward(boolean value) {
        this.loadShiftForward = value;
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

    /**
     * Gets the value of the targetReduction property.
     * 
     * @return
     *     possible object is
     *     {@link TargetReduction }
     *     
     */
    public TargetReduction getTargetReduction() {
        return targetReduction;
    }

    /**
     * Sets the value of the targetReduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link TargetReduction }
     *     
     */
    public void setTargetReduction(TargetReduction value) {
        this.targetReduction = value;
    }

}
