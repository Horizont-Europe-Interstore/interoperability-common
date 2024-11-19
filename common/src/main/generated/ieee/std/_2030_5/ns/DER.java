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
 * Contains links to DER resources.
 * 
 * <p>Java class for DER complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DER"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}SubscribableResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AssociatedDERProgramListLink" type="{urn:ieee:std:2030.5:ns}AssociatedDERProgramListLink" minOccurs="0"/&gt;
 *         &lt;element name="AssociatedUsagePointLink" type="{urn:ieee:std:2030.5:ns}AssociatedUsagePointLink" minOccurs="0"/&gt;
 *         &lt;element name="CurrentDERProgramLink" type="{urn:ieee:std:2030.5:ns}CurrentDERProgramLink" minOccurs="0"/&gt;
 *         &lt;element name="DERAvailabilityLink" type="{urn:ieee:std:2030.5:ns}DERAvailabilityLink" minOccurs="0"/&gt;
 *         &lt;element name="DERCapabilityLink" type="{urn:ieee:std:2030.5:ns}DERCapabilityLink" minOccurs="0"/&gt;
 *         &lt;element name="DERSettingsLink" type="{urn:ieee:std:2030.5:ns}DERSettingsLink" minOccurs="0"/&gt;
 *         &lt;element name="DERStatusLink" type="{urn:ieee:std:2030.5:ns}DERStatusLink" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DER", propOrder = {
    "associatedDERProgramListLink",
    "associatedUsagePointLink",
    "currentDERProgramLink",
    "derAvailabilityLink",
    "derCapabilityLink",
    "derSettingsLink",
    "derStatusLink"
})
public class DER
    extends SubscribableResource
{

    @XmlElement(name = "AssociatedDERProgramListLink")
    protected AssociatedDERProgramListLink associatedDERProgramListLink;
    @XmlElement(name = "AssociatedUsagePointLink")
    protected AssociatedUsagePointLink associatedUsagePointLink;
    @XmlElement(name = "CurrentDERProgramLink")
    protected CurrentDERProgramLink currentDERProgramLink;
    @XmlElement(name = "DERAvailabilityLink")
    protected DERAvailabilityLink derAvailabilityLink;
    @XmlElement(name = "DERCapabilityLink")
    protected DERCapabilityLink derCapabilityLink;
    @XmlElement(name = "DERSettingsLink")
    protected DERSettingsLink derSettingsLink;
    @XmlElement(name = "DERStatusLink")
    protected DERStatusLink derStatusLink;

    /**
     * Gets the value of the associatedDERProgramListLink property.
     * 
     * @return
     *     possible object is
     *     {@link AssociatedDERProgramListLink }
     *     
     */
    public AssociatedDERProgramListLink getAssociatedDERProgramListLink() {
        return associatedDERProgramListLink;
    }

    /**
     * Sets the value of the associatedDERProgramListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssociatedDERProgramListLink }
     *     
     */
    public void setAssociatedDERProgramListLink(AssociatedDERProgramListLink value) {
        this.associatedDERProgramListLink = value;
    }

    /**
     * Gets the value of the associatedUsagePointLink property.
     * 
     * @return
     *     possible object is
     *     {@link AssociatedUsagePointLink }
     *     
     */
    public AssociatedUsagePointLink getAssociatedUsagePointLink() {
        return associatedUsagePointLink;
    }

    /**
     * Sets the value of the associatedUsagePointLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssociatedUsagePointLink }
     *     
     */
    public void setAssociatedUsagePointLink(AssociatedUsagePointLink value) {
        this.associatedUsagePointLink = value;
    }

    /**
     * Gets the value of the currentDERProgramLink property.
     * 
     * @return
     *     possible object is
     *     {@link CurrentDERProgramLink }
     *     
     */
    public CurrentDERProgramLink getCurrentDERProgramLink() {
        return currentDERProgramLink;
    }

    /**
     * Sets the value of the currentDERProgramLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrentDERProgramLink }
     *     
     */
    public void setCurrentDERProgramLink(CurrentDERProgramLink value) {
        this.currentDERProgramLink = value;
    }

    /**
     * Gets the value of the derAvailabilityLink property.
     * 
     * @return
     *     possible object is
     *     {@link DERAvailabilityLink }
     *     
     */
    public DERAvailabilityLink getDERAvailabilityLink() {
        return derAvailabilityLink;
    }

    /**
     * Sets the value of the derAvailabilityLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link DERAvailabilityLink }
     *     
     */
    public void setDERAvailabilityLink(DERAvailabilityLink value) {
        this.derAvailabilityLink = value;
    }

    /**
     * Gets the value of the derCapabilityLink property.
     * 
     * @return
     *     possible object is
     *     {@link DERCapabilityLink }
     *     
     */
    public DERCapabilityLink getDERCapabilityLink() {
        return derCapabilityLink;
    }

    /**
     * Sets the value of the derCapabilityLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link DERCapabilityLink }
     *     
     */
    public void setDERCapabilityLink(DERCapabilityLink value) {
        this.derCapabilityLink = value;
    }

    /**
     * Gets the value of the derSettingsLink property.
     * 
     * @return
     *     possible object is
     *     {@link DERSettingsLink }
     *     
     */
    public DERSettingsLink getDERSettingsLink() {
        return derSettingsLink;
    }

    /**
     * Sets the value of the derSettingsLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link DERSettingsLink }
     *     
     */
    public void setDERSettingsLink(DERSettingsLink value) {
        this.derSettingsLink = value;
    }

    /**
     * Gets the value of the derStatusLink property.
     * 
     * @return
     *     possible object is
     *     {@link DERStatusLink }
     *     
     */
    public DERStatusLink getDERStatusLink() {
        return derStatusLink;
    }

    /**
     * Sets the value of the derStatusLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link DERStatusLink }
     *     
     */
    public void setDERStatusLink(DERStatusLink value) {
        this.derStatusLink = value;
    }

}
